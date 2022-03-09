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

The integration service API provides a number of parameters that the integraton service can use to select or generate a suitable visible signature image for inclusion within the signed PDF. These parameters are:

Parameter | Description
---|---
**Template reference**| Identifier identifying the base image used to create the final visible signature image.
**Attribute list**| Identifying the ID attributes used to visually present the signer's name (or ID).
**Format string**| Identifying how the signer's name or ID should be presented.
**Parameter Map**| A key value map holding other data that should be used to personalize the visible signature image.

In addition to this content input, each template may also include a representation of the signing time. No data in the API is used to signal inclusion of signing time as this is defined by each template and the signing time is taken from a trusted time source rather than being provided as input through the API.

It is completely up to the implementation of the integration service, how to use this information to form a visible signature image or how to include that in the PDF. PDF is a versatile standard and allows a variety of ways to create visual representation of the electronic signature. The visible signature is in paractice a document extension (incremental update) where the PDF document is ammeded with the signature acro form together with an image that links to the actual signature. What limits the scope is 1) the library used to include the visible signature and 2) the application used to view the signed PDF document.

This guide does not consider special cases such as inclusion of an image representation of a scanned handwritten signature or similar. This is also possible using the present model by creative use of the paramater map where such image representation could be provided as a Base64 encoded binary.

The following steps is a recommended practice that allows complete presonalization of visible signature images with a reasonable level of control when using the standard features in Apache PDF Box:

1. SVG images are used as templates. These templates contains `<text>` elements with search and replacable strings where personalized data should be included. E.g. `<text transform="matrix(1 0 0 1 19.1101 355.83)" class="st2 st4">##SIGNTIME##</text>`, allowing the placeholder string "**##SIGNTIME##**" to be searched and replaced with actual time information.
2. Validate that the requesting service has provided all necessary information to personalize the selected image template.
3. Create a personalized SVG.
4. Transform the SVG image into a suitable format that can be accepted by your PDF library as visible signature image source (such as PNG).
5. Include the visible signature representation in the PDF document before signin. This is necessary since the signature will sign also the visible signature representation.

Note:
 - Wether the image needs conversion from SVG is a matter of the library used. Apache PDF Box relies on registerd BufferedImage readers and has not native support for reading SVG images. This can be handled by converting the SVG to PNG or by registering a suitable image reader.
 - Sveral of the parameters presented in the visible signature image is analogous to entries in the signature dictionary such as M (Signing time), Location, Name and Reason. The ETSI PAdES standard discourages in general the use of all of these fields except the M entry for signing time as it duplicates information in the signature data. The use of this type of information in visible signature images does not imply that these parameters should be entered also in the signature dictionary.



---

Copyright &copy; 2019-2022, [IDsec Solutions AB](http://www.idsec.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
