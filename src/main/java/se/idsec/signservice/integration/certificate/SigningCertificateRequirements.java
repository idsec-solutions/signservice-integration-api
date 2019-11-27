/*
 * Copyright 2019 IDsec Solutions AB
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * When sending a SignRequest, a sign requester specifies a set of requirements for the signature certificate that is
 * generated by the sign service. This class represents these requirements.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SigningCertificateRequirements implements Extensible {

  /**
   * The requested certificate type.
   * 
   * @param certificateType
   *          requested certificate type
   * @return the requested certificate type
   */
  @Setter
  @Getter
  private CertificateType certificateType;

  /**
   * Defines mappings between the signer's authentication attributes and attributes that are to be placed in the issued
   * signature certificate.
   * 
   * @param attributeMappings
   *          a list of mapping directives of how to map from user/signer attributes to signature certificate attributes
   * @return a list of mapping directives of how to map from user/signer attributes to signature certificate attributes
   */
  @Singular
  @Setter
  @Getter
  private List<CertificateAttributeMapping> attributeMappings;
  
  /**
   * Extensions for the object.
   * 
   * @param extension
   *          the extension object to assign
   */
  @Setter
  private Extension extension;
  
  /** {@inheritDoc} */
  @Override
  public Extension getExtension() {
    return this.extension;
  }  

  /**
   * Builder for {@code SigningCertificateRequirements} objects
   */
  public static class SigningCertificateRequirementsBuilder implements ObjectBuilder<SigningCertificateRequirements> {
    // Lombok
  }

}
