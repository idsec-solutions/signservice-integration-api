# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a pure Java API library (interfaces + model classes, no implementation) for the SignService Integration Service — a component in the Swedish eID Framework that mediates between a requesting service and a Federated Central Signing Service. It handles creating `SignRequest` messages sent to the signature service and processing `SignResponse` messages received back.

Current version: **2.3.1** (Java 17, Maven)

## Build and Test Commands

```bash
# Build and install
mvn clean install

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=ApiVersionTest

# Generate Javadoc
mvn javadoc:javadoc

# Release build (requires GPG key configured)
mvn clean deploy -Prelease
```

## Architecture

### Service Interfaces

The two primary interfaces define the API:

- **`SignServiceIntegrationService`** — Core interface with:
  - `createSignRequest(SignRequestInput, callerId)` → `SignRequestData`
  - `processSignResponse(signResponse, relayState, state, parameters, callerId)` → `SignatureResult`
  - `getConfiguration(policy)` → `IntegrationServiceDefaultConfiguration`
  - `getPolicies()` → `List<String>`

- **`ExtendedSignServiceIntegrationService`** — Extends the core with:
  - `preparePdfDocument(policy, pdfDocument, signaturePagePreferences, returnDocumentReference, callerId)` → `PreparedPdfDocument`

The `callerId` parameter is only needed when the implementation is a stand-alone service in stateful mode.

### Stateless vs. Stateful Modes

A key architectural concept: the service can run in two modes (configured per-policy via `IntegrationServiceDefaultConfiguration.isStateless()`):
- **Stateless**: `SignatureState` carries the full session state back to the caller, who must pass it back during `processSignResponse`.
- **Stateful**: The service keeps state internally; `SignatureState` only contains an operation ID.

`RestClientSignatureState` is the concrete `SignatureState` implementation used for JSON deserialization on REST clients (the interface uses `@JsonDeserialize(as = RestClientSignatureState.class)`).

### Domain Model Patterns

All domain model classes follow consistent conventions:
- Lombok `@Builder(toBuilder = true)`, `@ToString`, `@AllArgsConstructor`, `@NoArgsConstructor` on model classes
- Inner static `XxxBuilder implements ObjectBuilder<Xxx>` — the `ObjectBuilder<T>` interface (in `core`) provides just `build()`
- Explicit getters/setters written manually (not Lombok-generated)
- `@JsonInclude(Include.NON_NULL)` on all JSON-serializable classes
- `@Nonnull`/`@Nullable` from `jakarta.annotation`
- Most domain classes implement `Extensible` (extends `Serializable`) which allows arbitrary name-value pair extensions via `Extension`

### Package Structure

| Package | Contents |
|---|---|
| `se.idsec.signservice.integration` | Core service interfaces, `SignRequestInput`, `SignRequestData`, `SignatureResult`, `ProcessSignResponseInput`, `ApiVersion` |
| `.authentication` | `AuthnRequirements`, `SignerIdentityAttribute`, `SignerAssertionInformation` |
| `.certificate` | `SigningCertificateRequirements`, `CertificateAttributeMapping`, `RequestedCertificateAttribute` |
| `.config` | `IntegrationServiceDefaultConfiguration` (policy defaults), `PolicyNotFoundException` |
| `.core` | `Extensible`, `Extension`, `ObjectBuilder`, `SignatureState`, `RestClientSignatureState`, `ContentLoader`, `FileResource` |
| `.core.error` | Exception hierarchy: `SignServiceIntegrationException` (abstract base), `InputValidationException`, `BadRequestException`, `NoAccessException`, `SignServiceIntegrationCategoryException`; also `ErrorCode`, `SignServiceIntegrationErrorBody` |
| `.document` | `TbsDocument` (to-be-signed document), `SignedDocument`, `DocumentType`, `SignatureDestinationType` |
| `.document.pdf` | `PdfSignaturePage`, `PdfSignatureImageTemplate`, `VisiblePdfSignatureRequirement`, `PdfSignaturePagePreferences`, `PreparedPdfDocument`, `PdfPrepareSettings`, `PdfPrepareReport`, plus PDF-specific exceptions |
| `.document.xml` | `XMLSignatureLocation` |
| `.security` | `EncryptionParameters` |
| `.signmessage` | `SignMessageParameters`, `SignMessageMimeType` |

### Error Handling

Errors use structured `ErrorCode` objects with format `error.{category}.{code}` (e.g. `error.bad-request.missing-policy`). The abstract `SignServiceIntegrationException` carries an `ErrorCode` and an HTTP status code; subclasses either supply a full `ErrorCode` or override `getCategory()` and pass only the code part.

### Version Management

`ApiVersion` hardcodes `MAJOR`, `MINOR`, `PATCH` constants. The `ApiVersionTest` verifies this matches the Maven project version via a filtered `src/test/resources/version.properties` file. **When bumping the version, update both `pom.xml` and `ApiVersion.java`.**
