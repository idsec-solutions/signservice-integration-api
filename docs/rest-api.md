![Logo](img/idsec.png)

# Signature Service Integration Service REST API

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

REST API for the Signature Service Integration Service.

---

## Table of contents

1. [**Introduction**](#introduction)

    1.1. [Design Principles](#design-principles)
    
2. [**Configuration**](#configuration)

3. [**Creating a SignRequest**](#creating-a-signrequest)

    3.1. [SignRequest Input](#signrequest-input)
    
    3.1.1. [Requesting a Visible PDF Signature](#requesting-a-visible-pdf-signature)
  
    3.2. [SignRequest Data](#signrequest-data)
  
4. [**Processing a SignResponse**](#processing-a-signresponse)

    4.1. [Processing Input](#processing-input)
  
    4.2. [Signature Result](#signature-result)
  
5. [**Errors**](#errors)

    5.1. [Error codes](#error-codes)
  
---

<a name="introduction"></a>
## 1. Introduction

TODO

> Versioning

> Note: Authentication is not defined and should be solved using headers (OAuth2, BasicAuth, ...) or TLS client certificate.

<a name="design-principles"></a>
### 1.1. Design Principles

TODO

<a name="configuration"></a>
## 2. Configuration

TODO

> Describe how the current configuration can be obtained
> Set policy as URL-parameter

<a name="creating-a-signrequest"></a>
## 3. Creating a SignRequest

**POST /v1/create**

<a name="signrequest-input"></a>
### 3.1. SignRequest Input

In the documentation for the [Java API](java-api.md) a code example of how to create a minimal `SignRequestInput`. The JSON structure below is the how this parameter looks in for the REST service:

```
{
  "signRequesterID" : "https://qa.test.swedenconnect.se/sp",
  "authnRequirements" : {
    "authnServiceID" : "https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com",
    "authnContextRef" : "http://id.elegnamnden.se/loa/1.0/loa3",
    "requestedSignerAttributes" : [ {
      "name" : "urn:oid:1.2.752.29.4.13",
      "value" : "196911292032"
    } ]
  },
  "tbsDocuments" : [ {
    "id" : "doc-1",
    "content" : "PE15RG9jPjxWYWx1ZT5BcHByb3ZlPC9WYWx1ZT48L015RG9jPg==",
    "mimeType" : "application/xml"
  } ],
  "signMessageParameters" : {
    "signMessage" : "I approve this contract",
    "performEncryption" : true,
    "mimeType" : "text",
    "mustShow" : true,
    "displayEntity" : "https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com"
  }
}
```

The [Configuration](#configuration) section above describes all the possible default settings. If we want to override them and create a request that is very detailed, we end up with a larger structure.

The example below displays a complete SignRequest Input data structure (where no default values are used):

```
{
  "correlationId" : "28bc2b39-9a62-43b7-8bc2-c1cb6b13bacc",
  "policy" : "swedish-eid",
  "signRequesterID" : "https://qa.test.swedenconnect.se/sp",
  "returnUrl" : "https://qa.test.swedenconnect.se/signresponse",
  "destinationUrl" : "https://sign.idsec.se/request",
  "signatureAlgorithm" : "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256",
  "authnRequirements" : {
    "authnServiceID" : "https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com",
    "authnContextRef" : "http://id.elegnamnden.se/loa/1.0/loa3",
    "requestedSignerAttributes" : [ {
      "type" : "saml",
      "name" : "urn:oid:1.2.752.29.4.13",
      "value" : "196911292032",
      "nameFormat" : "urn:oasis:names:tc:SAML:2.0:attrname-format:uri",
      "attributeValueType" : "string"
    }, {
      "name" : "urn:oid:1.3.6.1.5.5.7.9.1",
      "value" : "1969-11-29"
    } ]
  },
  "certificateRequirements" : {
    "certificateType" : "PKC",
    "attributeMappings" : [ {
      "sources" : [ {
        "type" : "saml",
        "name" : "urn:oid:1.2.752.29.4.13"
      } ],
      "destination" : {
        "type" : "rdn",
        "name" : "2.5.4.5",
        "friendlyName" : "serialNumber",
        "required" : true
      }
    }, {
      "sources" : [ {
        "name" : "urn:oid:2.5.4.6"
      } ],
      "destination" : {
        "type" : "rdn",
        "name" : "urn:oid:2.5.4.6",
        "defaultValue" : "SE",
        "friendlyName" : "country",
        "required" : true
      }
    } ]
  },
  "tbsDocuments" : [ {
    "id" : "doc-1",
    "content" : "PE15RG9jPjxWYWx1ZT5BcHByb3ZlPC9WYWx1ZT48L015RG9jPg==",
    "mimeType" : "application/xml",
    "processingRules" : "rule-xyz",
    "adesRequirement" : {
      "adesFormat" : "EPES",
      "signaturePolicy" : "etsi123"
    }
  } ],
  "signMessageParameters" : {
    "signMessage" : "I approve this contract",
    "performEncryption" : true,
    "mimeType" : "text",
    "mustShow" : true,
    "displayEntity" : "https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com"
  }
}
```

TODO: Describe all settings

<a name="requesting-a-visible-pdf-signature"></a>
#### 3.1.1. Requesting a Visible PDF Signature

> TODO: Explain the feature of visible PDF signatures

```
{
  "signRequesterID" : "https://qa.test.swedenconnect.se/sp",
  "authnRequirements" : {
    "authnServiceID" : "https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com",
    "authnContextRef" : "http://id.elegnamnden.se/loa/1.0/loa3",
    "requestedSignerAttributes" : [ {
      "name" : "urn:oid:1.2.752.29.4.13",
      "value" : "196911292032"
    }, {
      "name" : "urn:oid:2.5.4.42",
      "value" : "Kalle"
    }, {
      "name" : "urn:oid:2.5.4.4",
      "value" : "Kula"
    } ]
  },
  "tbsDocuments" : [ {
    "id" : "doc-1",
    "content" : "QmxhY...hYmxh",
    "mimeType" : "application/pdf",
    "visiblePdfSignatureRequirement" : {
      "templateImageRef" : "companylogo1",
      "signerName" : {
        "signerAttributes" : [ {
          "name" : "urn:oid:2.5.4.42"
        }, {
          "name" : "urn:oid:2.5.4.4"
        }, {
          "name" : "urn:oid:1.2.752.29.4.13"
        } ],
        "formatting" : "%0 %1 (%2)"
      },
      "scale" : 0,
      "page" : 1,
      "fieldValues" : {
        "reason" : "Approval"
      },
      "xposition" : 100,
      "yposition" : 100
    }
  } ],
  "signMessageParameters" : {
    "signMessage" : "I approve this contract",
    "performEncryption" : true,
    "mimeType" : "text",
    "mustShow" : true,
    "displayEntity" : "https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com"
  }
}

```

> TODO: Explain the specific fields


<a name="signrequest-data"></a>
### 3.2. SignRequest Data
  
<a name="processing-a-signresponse"></a>
## 4. Processing a SignResponse

TODO

<a name="processing-input"></a>
### 4.1. Processing Input
  
<a name="signature-result"></a>
### 4.2. Signature Result

<a name="errors"></a>
## 5. Errors

<a name="error-codes"></a>
### 5.1. Error Codes

TODO

---

Copyright &copy; 2019-2022, [IDsec Solutions AB](http://www.idsec.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).