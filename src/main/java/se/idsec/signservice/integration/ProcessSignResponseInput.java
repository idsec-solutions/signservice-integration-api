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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.core.SignatureState;

/**
 * The {@code ProceessSignResponseInput} class is intended to be used when the SignService Integration service is
 * implemented as a REST service. It collects all the parameters of the
 * {@link SignServiceIntegrationService#processSignResponse(String, String, se.idsec.signservice.integration.core.SignatureState, SignResponseProcessingParameters)}
 * method.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ProcessSignResponseInput {

  /**
   * The Base64-encoded SignResponse message (from the EidSignResponse POST parameter).
   * 
   * @param signResponse
   *          the base64-encoded SignResponse
   * @return the base64-encoded SignResponse
   */
  @Setter
  @Getter
  private String signResponse;

  /**
   * The relayState (from the RelayState POST parameter).
   * 
   * @param relayState
   *          the RelayState parameter
   * @return the RelayState parameter
   */
  @Setter
  @Getter
  private String relayState;

  /**
   * The signature state.
   * 
   * @param state
   *          the signature state
   * @return the signature state
   */
  @Setter
  @Getter
  private SignatureState state;

  /**
   * Optional processing parameters giving directives about the processing.
   * 
   * @param parameters
   *          processing parameters
   * @return processing parameters
   */
  @Setter
  @Getter
  private SignResponseProcessingParameters parameters;

  /**
   * Builder for {@code ProcessSignResponseInput} objects.
   */
  public static class ProcessSignResponseInputBuilder implements ObjectBuilder<ProcessSignResponseInput> {
    // Lombok
  }

}
