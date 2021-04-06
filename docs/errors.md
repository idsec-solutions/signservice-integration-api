![Logo](img/idsec.png)

# Signature Service Integration Service - Error Codes

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Open Source Signature Service Integration Service - Error Codes

---

## Listing of Error Codes

Error codes are represented as `error.<category>.<code>`, where `category` is the type of error and `code` the error code under that category.

### DSS

The `dss` category is used to represent errors that are received from the Signature Service. The exact error from the Signature Service is saved in the `dssError` field of the error body.

| Error code | Description |
| --- |  --- |
| `error.dss.error` | An error was received from the Signature Service. For details, see the `dssError` field of the error body. |
| `error.dss.cancel` | The user cancelled the signature operation while at the Signature Service. |

### Security

The `security` category is used when an invocation to the service is denied due to security checks, for example "not allowed".

| Error code | Description |
| --- |  --- |
| `error.security.no-access` | The caller does not have permissions to access the requested resource. |

### Bad Request

The `bad-request` category represents errors that are reported due to an invalid invocation of the SignService Integration service.

| Error code | Description |
| --- |  --- |
| `error.bad-request.validation` | Validation of the input passed to the service failed. Check the `validationError` field of the error body for details. |
| `error.bad-request.missing-policy` | A policy that does not exist was referred. |
| `error.bad-request.session` | A session related error occurred. |
| `error.bad-request.invalid-call` | Bad request (generic). |

### Document

The `document` category represents document related errors, for example, a malformed document was supplied to the service.

| Error code | Description |
| --- |  --- |
| `error.document.format-error` | Bad format on supplied document. |
| `error.document.pdf` | PDF document error. TODO: Fix |
| `error.document.too-many-signimages` | A PDF document was passed in containing a signature page that does not have any more room for additional signature images. |
| `error.document.invalid-ades-object` | The document contains an invalid AdES object. |
| `error.document.ades-validation-error` | The AdES validation failed (for example the digest check).  |
| `error.document.decode` | Failed to decode document that was passed.  |
| `error.document.encode` | Failed to encode document that was passed.  |
| `error.document.invalid-signature` | The document has an invalid signature. TODO |
| `error.document.sign` | Failed to sign the document. TODO  |

### SignResponse

The `signresponse` category is used to report errors found in a SignResponse message.

| Error code | Description |
| --- |  --- |
| `error.signresponse.invalid-response` | The SignResponse message that is being processed in invalid. |
| `error.signresponse.invalid-authncontext` | The SignResponse contains an authentication context that is not accepted according to the context under which the request was made. |
| `error.signresponse.signature` | The signature of the SignResponse did not verify correctly. |
| `error.signresponse.mismatch-id` | The SignResponse supplied to the service does has an ID that does not match the expected message. |
| `error.signresponse.version` | An unsupported or unexpected version of the SignResponse was received. |
| `error.signresponse.invalid-signercert` | The signature certificate found in the SignResponse is invalid (does not verify correctly). |
| `error.signresponse.expired-response` | The SignResponse has expired and is no longer trustworthy. |
| `error.signresponse.server-processing-time-exceeded-limit` | The time that we allow processing at the server side (Signature Service) to go on has been exceeded. |
| `error.signresponse.state-error` | SignResponse processing failed because the operation state contained data that did not match response. |
| `error.signresponse.signature-processing` | Failed to build valid signed document based on signature found in SignResponse. |
| `error.signresponse.complete-sign` | Generated signature (based on signature in SignResponse) fails signature validation. |

### Encrypt

Specific errors for encryption.

> Note: This will change into internal errors instead ...

| Error code | Description |
| --- |  --- |
| `error.encrypt.metadata-error` | SAML metadata for Identity Provider could not be obtained. |
| `error.encrypt.resolve` | Failed to resolve encryption parameters. |
| `error.encrypt.crypt` | General encryption error. |

### State

State errors.

> Note: This will change into internal errors instead ...

| Error code | Description |
| --- |  --- |
| `error.state.missing-input-state` | No state for the operation could be found. |
| `error.state.format-error` | Bad format on stored processing state. |
| `error.state.not-found` | Could not find processing state. |
| `error.state.policy-error` | State does not match active policy. |

### Internal

The `internal` category represents "internal errors" of the service. Errors of this category may also be reported when the Signature Service delivers something to the integration service that is incorrect.

| Error code | Description |
| --- |  --- |
| `error.internal.invalid-call` | General error. |
| `error.internal.protocol` | A protocol related error occurred. |
| `error.internal.config` | A configuration related error occurred. |
| `error.internal.unsupported-algorithm` | An algorithm used internally was not supported by the security provider in use. |
| `error.internal.signing` | An error occurred when the service attempted to sign data.  |

---

Copyright &copy; 2019-2021, [IDsec Solutions AB](http://www.idsec.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
