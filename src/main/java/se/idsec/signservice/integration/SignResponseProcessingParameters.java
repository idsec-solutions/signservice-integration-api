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
package se.idsec.signservice.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Class representing the processing parameters for a call to
 * {@link SignServiceIntegrationService#processSignResponse(String, String, se.idsec.signservice.integration.core.SignatureState, SignResponseProcessingParameters)}.
 * 
 * <p>
 * Note: More settings will be added ...
 * </p>
 * 
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignResponseProcessingParameters implements Extensible {

  // TODO: Add more fields ...

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
   * Builder for {@code SignResponseProcessingParameters} objects.
   */
  public static class SignResponseProcessingParametersBuilder implements ObjectBuilder<SignResponseProcessingParameters> {
    // Lombok
  }

}
