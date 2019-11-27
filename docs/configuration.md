![Logo](img/idsec.png)

# Signature Service Integration Service - Configuration and Policies

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Open Source Signature Service Integration Service - Configuration and Policies

---

## Table of contents

1. [**Introduction**](#introduction)
  
2. [**Java API**](#java-api)

3. [**REST API**](#rest-api)

    3.1. [Listing Policies](#listing-policies)
    
    3.2. [Get Default Configuration for a Policy](#get-default-configuration-for-a-policy)
    
4. [**Templates for Visible PDF Signatures**](#templates-for-visible-pdf-signatures)
  
---

<a name="introduction"></a>
## 1. Introduction

A SignService Integration Service runs under one, or more, policies. Each policy holds a configuration
for default settings concerning how a SignRequest message is created.

TODO: more

> **Note:** A SignService Integration Service must have a policy named "default", which is ... the default policy for the service.

<a name="java-api"></a>
## 2. Java API

The main API for the SignService Integration Service, [SignServiceIntegrationService](https://idsec-solutions.github.io/signservice-integration-api/javadoc/latest/se/idsec/signservice/integration/SignServiceIntegrationService.html), offers two methods for getting information about policies and configuration:

* `getPolicies()` - Returns a list of policy identifiers for the policies that the service implements.
* `getConfiguration(String policy)` - Gets the configuration for the given policy.

A user of the API does not have, and should not, have access to all parts of a service configuration. Sensitive keys and such should not be exposed. The interface [IntegrationServiceDefaultConfiguration](https://idsec-solutions.github.io/signservice-integration-api/javadoc/latest/se/idsec/signservice/integration/config/IntegrationServiceDefaultConfiguration.html) describes the data that is "public configuration". See [REST API](#rest-api) below for a detailed description of each field of the configuration.

<a name="rest-api"></a>
## 3. REST API

<a name="listing-policies"></a>
### 3.1. Listing Policies

> TODO: The policies that are active for a ...

<a name="get-default-configuration-for-a-policy"></a>
### 3.2. Get Default Configuration for a Policy


> TODO

```
{
  "policy" : "default",
  "defaultSignRequesterID" : "http://demo.idsec.se",
  "defaultReturnUrl" : "https://demo.idsec.se/signresponse",
  "defaultSignatureAlgorithm" : "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256",
  "signServiceID" : "http://sign.service.com",
  "defaultDestinationUrl" : "https://sign.service.com/req",
  "defaultAuthnServiceID" : "https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com",
  "defaultAuthnContextRef" : "http://id.elegnamnden.se/loa/1.0/loa3",
  "defaultVisiblePdfSignatureRequirement" : {
    "templateImageRef" : "companylogo",
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
  },
  "pdfSignatureImageTemplates" : [ {
    "image" : "bXVtYm9qdW1ibw==",
    "width" : 300,
    "height" : 300,
    "includeSignerName" : true,
    "includeSigningTime" : true,
    "fields" : {
      "reason" : "The reason/purpose of the signature",
      "signerName" : "The signer name",
      "signingTime" : "The time the signature was created"
    }
  } ],
  "stateless" : true,
  "defaultEncryptionParameters" : {
    "dataEncryptionAlgorithm" : "http://www.w3.org/2009/xmlenc11#aes128-gcm",
    "keyTransportEncryptionAlgorithm" : "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p",
    "rsaOaepParameters" : {
      "digestMethod" : "http://www.w3.org/2000/09/xmldsig#sha1",
      "maskGenerationFunction" : "http://www.w3.org/2009/xmlenc11#mgf1sha1"
    }
  },
  "defaultCertificateRequirements" : {
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
  "signatureCertificate" : "MIIFHjCCA...WxCk2T"
}

```

<a name="templates-for-visible-pdf-signatures"></a>
## 4. Templates for Visible PDF Signatures

TODO: Describe how a template is built ...

---

Copyright &copy; 2019, [IDsec Solutions AB](http://www.idsec.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
