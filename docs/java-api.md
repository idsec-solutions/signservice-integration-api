![Logo](img/idsec.png)

# Signature Service Integration Service Java API

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Java Signature Service Integration Service API.

---

## Table of contents

1. [**Introduction**](#introduction)

    1.1. [Design Principles](#design-principles)
  
    1.2. [Javadoc](#javadoc)
  
    1.3. [Distribution](#distribution)
  
2. [**Configuration**](#configuration)

3. [**Creating a SignRequest**](#creating-a-signrequest)

    3.1. [SignRequestInput](#signrequestinput)
  
    3.2. [SignRequestData](#signrequestdata)
  
4. [**Processing a SignResponse**](#processing-a-signresponse)

    4.1. [Processing input](#processing-input)
  
    4.2. [SignatureResult](#signatureresult)
  
---

<a name="introduction"></a>
## 1. Introduction

TODO

<a name="design-principles"></a>
### 1.1. Design Principles

TODO

> No specific Jackson or GSON annotations
> Versioning
> Extensible objects (not so much subclassing)

<a name="javadoc"></a>
### 1.2. Javadoc

The generated Javadoc at <https://idsec-solutions.github.io/signservice-integration-api/javadoc/> contains the complete documentation of the Java API.

<a name="distribution"></a>
### 1.3. Distribution

The signservice-integration-api artifact is published to Maven central. Include its dependency by adding
the following to your POM file:

```
<dependency>
  <groupId>se.idsec.signservice.integration</groupId>
  <artifactId>signservice-integration-api</artifactId>
  <version>${signservice-api.version}</version>
</dependency>
```

<a name="configuration"></a>
## 2. Configuration

An instance of a SignService Integration Service can function under one or several policies. Each policy has a configuration containing default settings for how to create sign requests and how to process sign responses, along with fixed settings such as signature certificates and the ID for the SignService.

An implementation probably has more settings per policy, but the settings that are of interest for the sign requester using the API are described in the [IntegrationServiceDefaultConfiguration](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/config/IntegrationServiceDefaultConfiguration.html) interface.

For more information about the configuration of a SignService Integration Service see the [Configuration and Policies](configuration.md) page.

<a name="creating-a-signrequest"></a>
## 3. Creating a SignRequest

The SignService Integration Service handles the complex process of creating a `dss:SignRequest` message that is to be sent to the Signature Service. This process includes:

- Given an input document that is to be signed, calculate its digest and additional information.

- Optionally encrypt the SignMessage for the authentication service (Identity Provider) that should display the message for the user.

- Setup requirements concerning the how the signer (user) should authenticate itself as part of the signature process.

- Setup requirements for how the signature certificate should be issued (i.e, which attributes should be included).

- Tell which type of signature that is requested (XML or PDF), and extensions such as ETSI AdES.


The [SignServiceIntegrationService](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/SignServiceIntegrationService.html) interfaces defines the following method to create a SignRequest:

```
SignRequestData createSignRequest(
   SignRequestInput signRequestInput) throws SignServiceIntegrationException

```

<a name="signrequestinput"></a>
#### 3.1. SignRequestInput

The [SignRequestInput](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/SignRequestInput.html) class is used to set all input in order to create a SignRequest message. Generally, the SignService Integration Service is configured with a set of default values so in the normal case not all the attributes of the [SignRequestInput](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/SignRequestInput.html) instance need to be assigned. Below follows a minimal example of how a SignRequest is created:

```
final byte[] documentBytes = ...;
    
SignRequestInput input = SignRequestInput.builder()
  .signRequesterID("https://qa.test.swedenconnect.se/sp")
  .authnRequirements(AuthnRequirements.builder()
    .authnServiceID("https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com")
    .authnContextRef("http://id.elegnamnden.se/loa/1.0/loa3")
    .requestedSignerAttribute(SignerIdentityAttributeValue.builder()
      .name("urn:oid:1.2.752.29.4.13")
      .value("196911292032")
      .build())
    .build())
  .tbsDocument(TbsDocument.builder()
    .id("doc-1")
    .content(Base64.getEncoder().encodeToString(documentBytes))
    .mimeType(DocumentType.XML)
    .build())
  .signMessageParameters(
    SignMessageParameters.builder()
      .signMessage("I approve this contract")
      .mimeType(SignMessageMimeType.TEXT)
      .mustShow(true)
      .performEncryption(true)
      .displayEntity("https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com")
      .build())
  .build();
  
SignRequestData signRequest = integrationService.createSignRequest(input);
```

So, what is passed in?

- `signRequesterID`: The ID of the requesting service. This is normally the SAML entityID of the service that requests the signature (and to which the user has logged into before signing).

- `authnRequirements`: The authentication requirements that we put on the signer (user) as part of the signature process. See [AuthnRequirements](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/authentication/AuthnRequirements.html).

  - `authnServiceID`: The ID for the authentication service (Identity Provider) who should authenticate the user as part of the signature process. In the common case this is the same IdP that authenticated the user during his or hers login to the requesting service.
  
  - `authnContextRef`: The authentication context reference identifier (an URI) that identifies the context under which the signer should be authenticated. This is the Level of Assurance (LoA).
  
  - `requestedSignerAttributes`: One or more identity attribute values that the sign requestor requires the authentication service (IdP) to validate and deliver (and the signature service to assert). Typically, a sign requester includes the identity attributes that binds the signature operation to the principal that authenticated at the sign requester service, for example the personalIdentityNumber of the principal.
  
- `tbsDocuments`: One or more "To be signed documents" containing the document contents and associated metadata. See [TbsDocument](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/document/TbsDocument.html).

  - `id`: The unique ID for this document (within the current request). If not supplied, the SignService Integration Service will generate one.
  
  - `content`: The Base64-encoded byte string that is the content of the document that is to be signed.
  
  - `mimeType`: The MIME type of the document that is to be signed. Currently "application/xml" and "application/pdf" are supported. See [DocumentType](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/document/DocumentType.html).
  
- `signMessageParameters`: The sign message parameters that is used to build the sign message element that is included in the SignRequest. See [SignMessageParameters](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/signmessage/SignMessageParameters.html).

  - `signMessage`: The sign message (non encrypted) content according to specified mime type.
  
  - `mimeType`: The sign message MIME type. See [SignMessageMimeType](https://idsec-solutions.github.io/signservice-integration-api/javadoc/se/idsec/signservice/integration/signmessage/SignMessageMimeType.html).
  
  - `mustShow`: Specifies if the requester of the signature requires that the sign message is displayed to the user. If the Identity Provider cannot fulfill this requirement it must not proceed.
  
  - `performEncryption`: Tells whether the supplied sign message should be encrypted with `displayEntity` as the recipient.
  
  - `displayEntity`: The ID (SAML entityID) of the entity (IdP) that should display this message.


<a name="signrequestdata"></a>
#### 3.2. SignRequestData

> TODO

<a name="processing-a-signresponse"></a>
## 4. Processing a SignResponse

TODO

<a name="processing-input"></a>
### 4.1. Processing input

TODO  

<a name="signatureresult"></a>
### 4.2. SignatureResult

---

Copyright &copy; 2019-2024, [IDsec Solutions AB](http://www.idsec.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
