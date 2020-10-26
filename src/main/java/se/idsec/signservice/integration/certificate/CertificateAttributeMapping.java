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
package se.idsec.signservice.integration.certificate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.SignerIdentityAttribute;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Describes how a signer's identity attribute(s) are mapped to a certificate attribute to be placed in the issued
 * signature certificate.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CertificateAttributeMapping {

  /**
   * A list of signer source attributes from where the sign service gets information in order to create the requested
   * certificate attribute. If more than one attribute is given, the order is important as the sign service tries the
   * given source attributes in order.
   * 
   * @param sources a list of source attributes
   * @return a list of source attributes
   */
  @Singular
  @Setter
  @Getter
  private List<SignerIdentityAttribute> sources;

  /**
   * The requested destination attribute gives information about which type of certificate attribute to create, and
   * possibly its default value.
   * 
   * @param destination the destination attribute
   * @return the destination attribute
   */
  @Setter
  @Getter
  private RequestedCertificateAttribute destination;

  /**
   * Builder for {@code CertificateAttributeMapping} objects.
   */
  public static class CertificateAttributeMappingBuilder implements ObjectBuilder<CertificateAttributeMapping> {
    // Lombok
  }

}
