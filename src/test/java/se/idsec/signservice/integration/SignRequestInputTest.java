/*
 * Copyright 2019-2020 IDsec Solutions AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.idsec.signservice.integration;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import se.idsec.signservice.integration.authentication.AuthnRequirements;
import se.idsec.signservice.integration.authentication.SignerIdentityAttribute;
import se.idsec.signservice.integration.authentication.SignerIdentityAttributeValue;
import se.idsec.signservice.integration.certificate.CertificateAttributeMapping;
import se.idsec.signservice.integration.certificate.CertificateType;
import se.idsec.signservice.integration.certificate.RequestedCertificateAttribute;
import se.idsec.signservice.integration.certificate.RequestedCertificateAttributeType;
import se.idsec.signservice.integration.certificate.SigningCertificateRequirements;
import se.idsec.signservice.integration.document.DocumentType;
import se.idsec.signservice.integration.document.TbsDocument;
import se.idsec.signservice.integration.document.TbsDocument.AdesType;
import se.idsec.signservice.integration.document.TbsDocument.EtsiAdesRequirement;
import se.idsec.signservice.integration.document.pdf.VisiblePdfSignatureRequirement;
import se.idsec.signservice.integration.document.pdf.VisiblePdfSignatureUserInformation.SignerName;
import se.idsec.signservice.integration.signmessage.SignMessageMimeType;
import se.idsec.signservice.integration.signmessage.SignMessageParameters;

/**
 * Test cases for {@code SignRequestInput}.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class SignRequestInputTest {

  @Test
  public void testCompleteJson() throws Exception {

    final String document = "<MyDoc><Value>Approve</Value></MyDoc>";
    final byte[] documentBytes = document.getBytes();

    SignRequestInput input = SignRequestInput.builder()
      .correlationId(UUID.randomUUID().toString())
      .policy("swedish-eid")
      .signRequesterID("https://qa.test.swedenconnect.se/sp")
      .returnUrl("https://qa.test.swedenconnect.se/signresponse")
      .destinationUrl("https://sign.idsec.se/request")
      .signatureAlgorithm("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256")
      .authnRequirements(AuthnRequirements.builder()
        .authnServiceID("https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com")
        .authnContextClassRef("http://id.elegnamnden.se/loa/1.0/loa3")
        .requestedSignerAttributes(Arrays.asList(
          SignerIdentityAttributeValue.builder()
            .type(SignerIdentityAttributeValue.SAML_TYPE)
            .name("urn:oid:1.2.752.29.4.13")
            .value("196911292032")
            .nameFormat(SignerIdentityAttributeValue.DEFAULT_NAME_FORMAT)
            .attributeValueType("string")
            .build(),
          SignerIdentityAttributeValue.builder()
            .name("urn:oid:1.3.6.1.5.5.7.9.1")
            .value("1969-11-29")
            .build()))
        .build())
      .certificateRequirements(SigningCertificateRequirements.builder()
        .certificateType(CertificateType.PKC)
        .attributeMappings(Arrays.asList(
          CertificateAttributeMapping.builder()
            .source(SignerIdentityAttribute.createBuilder().type(SignerIdentityAttribute.SAML_TYPE).name("urn:oid:1.2.752.29.4.13").build())
            .destination(RequestedCertificateAttribute.builder()
              .type(RequestedCertificateAttributeType.RDN)
              .name("2.5.4.5")
              .friendlyName("serialNumber")
              .required(true)
              .build())
            .build(),
          CertificateAttributeMapping.builder()
            .source(SignerIdentityAttribute.createBuilder().name("urn:oid:2.5.4.6").build())
            .destination(RequestedCertificateAttribute.builder()
              .type(RequestedCertificateAttributeType.RDN)
              .name("urn:oid:2.5.4.6")
              .friendlyName("country")
              .required(true)
              .defaultValue("SE")
              .build())
            .build()))
        .build())
      .tbsDocument(TbsDocument.builder()
        .id("doc-1")
        .content(Base64.getEncoder().encodeToString(documentBytes))
        .mimeType(DocumentType.XML)
        .adesRequirement(EtsiAdesRequirement.builder().adesFormat(AdesType.EPES).signaturePolicy("etsi123").build())
        .processingRules("rule-xyz")
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

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);
    ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

    String json = writer.writeValueAsString(input);

    System.out.println(json);

    SignRequestInput input2 = mapper.readValue(json, SignRequestInput.class);
    Assert.assertNotNull(input2);
  }

  @Test
  public void testMinimalJson() throws Exception {

    final String document = "<MyDoc><Value>Approve</Value></MyDoc>";
    final byte[] documentBytes = document.getBytes();

    SignRequestInput input = SignRequestInput.builder()
      .signRequesterID("https://qa.test.swedenconnect.se/sp")
      .authnRequirements(AuthnRequirements.builder()
        .authnServiceID("https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com")
        .authnContextClassRef("http://id.elegnamnden.se/loa/1.0/loa3")
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

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);
    ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

    String json = writer.writeValueAsString(input);

    System.out.println(json);

  }

  @Test
  public void testPdfJson() throws Exception {

    final String document = "Blablablabla";
    final byte[] documentBytes = document.getBytes();

    SignRequestInput input = SignRequestInput.builder()
      .signRequesterID("https://qa.test.swedenconnect.se/sp")
      .authnRequirements(AuthnRequirements.builder()
        .authnServiceID("https://idp-sweden-connect-valfr-2017-ct.test.frejaeid.com")
        .authnContextClassRef("http://id.elegnamnden.se/loa/1.0/loa3")
        .authnContextClassRef("http://id.elegnamnden.se/loa/1.0/loa4")
        .requestedSignerAttribute(SignerIdentityAttributeValue.builder()
          .name("urn:oid:1.2.752.29.4.13")
          .value("196911292032")
          .build())
        .requestedSignerAttribute(SignerIdentityAttributeValue.builder()
          .name("urn:oid:2.5.4.42")
          .value("Kalle")
          .build())
        .requestedSignerAttribute(SignerIdentityAttributeValue.builder()
          .name("urn:oid:2.5.4.4")
          .value("Kula")
          .build())        
        .build())
      .tbsDocument(TbsDocument.builder()
        .id("doc-1")
        .content(Base64.getEncoder().encodeToString(documentBytes))
        .mimeType(DocumentType.PDF)
        .visiblePdfSignatureRequirement(
          VisiblePdfSignatureRequirement.builder()
            .templateImageRef("companylogo1")
            .signerName(SignerName.builder()
              .signerAttribute(SignerIdentityAttributeValue.builder().name("urn:oid:2.5.4.42").build())
              .signerAttribute(SignerIdentityAttributeValue.builder().name("urn:oid:2.5.4.4").build())
              .signerAttribute(SignerIdentityAttribute.createBuilder().name("urn:oid:1.2.752.29.4.13").build())
              .formatting("%0 %1 (%2)")
              .build())
            .fieldValue("reason", "Approval")
            .page(1)
            .scale(0)
            .xPosition(100)
            .yPosition(100)
            .build())          
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

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);
    ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

    String json = writer.writeValueAsString(input);

    System.out.println(json);

  }

}
