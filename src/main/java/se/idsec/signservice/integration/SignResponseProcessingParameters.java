/*
 * Copyright 2019-2024 IDsec Solutions AB
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.xml.XMLSignatureLocation;

import java.io.Serial;

/**
 * Class representing the processing parameters for a call to
 * {@link SignServiceIntegrationService#processSignResponse(String, String, se.idsec.signservice.integration.core.SignatureState, SignResponseProcessingParameters)}.
 *
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class SignResponseProcessingParameters implements Extensible {

  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /**
   * If an XML document is signed, the caller has the possibility to control where in the document the
   * {@code ds:Signature} element should be inserted. If {@code null}, the signature element will be inserted as the
   * last child of the document root element.
   */
  private XMLSignatureLocation xmlSignatureLocation;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Default constructor.
   */
  public SignResponseProcessingParameters() {
  }

  /**
   * Constructor.
   *
   * @param xmlSignatureLocation insertion position for XML signatures
   * @param extension extensions for the object
   */
  public SignResponseProcessingParameters(final XMLSignatureLocation xmlSignatureLocation, final Extension extension) {
    this.xmlSignatureLocation = xmlSignatureLocation;
    this.extension = extension;
  }

  /**
   * For signed XML documents the method tells where the XML signature is inserted. If {@code null} is returned, this
   * means insert as the last child element.
   *
   * @return XML signature insertion directive
   */
  public XMLSignatureLocation getXmlSignatureLocation() {
    return this.xmlSignatureLocation;
  }

  /**
   * For XML documents, this method assigned where the signature should be inserted. If {@code null}, the signature
   * element will be inserted as the last child of the document root element.
   *
   * @param xmlSignatureLocation XML signature insertion directive
   */
  public void setXmlSignatureLocation(final XMLSignatureLocation xmlSignatureLocation) {
    this.xmlSignatureLocation = xmlSignatureLocation;
  }

  /** {@inheritDoc} */
  @Override
  public Extension getExtension() {
    return this.extension;
  }

  /** {@inheritDoc} */
  @Override
  public void setExtension(final Extension extension) {
    this.extension = extension;
  }

  /**
   * Builder for {@code SignResponseProcessingParameters} objects.
   */
  public static class SignResponseProcessingParametersBuilder
      implements ObjectBuilder<SignResponseProcessingParameters> {
    // Lombok
  }

}
