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
package se.idsec.signservice.integration.certificate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.SignerIdentityAttribute;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Describes how a signer's identity attribute(s) are mapped to a certificate attribute to be placed in the issued
 * signature certificate.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
public class CertificateAttributeMapping implements Serializable {

  @Serial
  private static final long serialVersionUID = 5475994961681485416L;

  /**
   * A list of signer source attributes from where the sign service gets information in order to create the requested
   * certificate attribute.
   */
  @Singular
  private List<SignerIdentityAttribute> sources;

  /**
   * The requested destination attribute gives information about which type of certificate attribute to create, and
   * possibly its default value.
   */
  private RequestedCertificateAttribute destination;

  /**
   * Default constructor.
   */
  public CertificateAttributeMapping() {
  }

  /**
   * Constructor.
   *
   * @param sources signer source attributes
   * @param destination requested destination attribute
   */
  public CertificateAttributeMapping(
      final List<SignerIdentityAttribute> sources, final RequestedCertificateAttribute destination) {
    this.sources = sources;
    this.destination = destination;
  }

  /**
   * Gets the list of signer source attributes from where the sign service gets information in order to create the
   * requested certificate attribute.
   *
   * @return a list of source attributes
   */
  public List<SignerIdentityAttribute> getSources() {
    return this.sources;
  }

  /**
   * Assigns the list of signer source attributes from where the sign service gets information in order to create the
   * requested certificate attribute. If more than one attribute is given, the order is important as the sign service
   * tries the given source attributes in order.
   *
   * @param sources a list of source attributes
   */
  public void setSources(final List<SignerIdentityAttribute> sources) {
    this.sources = sources;
  }

  /**
   * Gets the requested destination attribute gives information about which type of certificate attribute to create, and
   * possibly its default value.
   *
   * @return the destination attribute
   */
  public RequestedCertificateAttribute getDestination() {
    return this.destination;
  }

  /**
   * Assigns the requested destination attribute gives information about which type of certificate attribute to create,
   * and possibly its default value.
   *
   * @param destination the destination attribute
   */
  public void setDestination(final RequestedCertificateAttribute destination) {
    this.destination = destination;
  }

  /**
   * Builder for {@code CertificateAttributeMapping} objects.
   */
  public static class CertificateAttributeMappingBuilder implements ObjectBuilder<CertificateAttributeMapping> {
    // Lombok
  }

}
