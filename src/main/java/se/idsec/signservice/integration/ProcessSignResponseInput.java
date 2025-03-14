/*
 * Copyright 2019-2025 IDsec Solutions AB
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
@JsonInclude(Include.NON_NULL)
public class ProcessSignResponseInput {

  /** The Base64-encoded SignResponse message (from the EidSignResponse POST parameter). */
  private String signResponse;

  /**
   * The relayState (read from the RelayState POST parameter).
   */
  private String relayState;

  /** The signature state. */
  private SignatureState state;

  /** Optional processing parameters giving directives about the processing. */
  private SignResponseProcessingParameters parameters;

  /**
   * Default constructor.
   */
  public ProcessSignResponseInput() {
  }

  /**
   * Constructor.
   *
   * @param signResponse the Base64-encoded SignResponse message (from the EidSignResponse POST parameter)
   * @param relayState the relayState (read from the RelayState POST parameter)
   * @param state the signature state
   * @param parameters processing parameters giving directives about the processing (may be {@code null})
   */
  public ProcessSignResponseInput(final String signResponse, final String relayState,
      final SignatureState state, final SignResponseProcessingParameters parameters) {
    this.signResponse = signResponse;
    this.relayState = relayState;
    this.state = state;
    this.parameters = parameters;
  }

  /**
   * Gets the Base64-encoded SignResponse message (read from the EidSignResponse POST parameter).
   *
   * @return the base64-encoded SignResponse
   */
  public String getSignResponse() {
    return this.signResponse;
  }

  /**
   * Assigns the Base64-encoded SignResponse message (read from the EidSignResponse POST parameter).
   *
   * @param signResponse the base64-encoded SignResponse
   */
  public void setSignResponse(final String signResponse) {
    this.signResponse = signResponse;
  }

  /**
   * Gets the relayState (read from the RelayState POST parameter).
   *
   * @return the RelayState parameter
   */
  public String getRelayState() {
    return this.relayState;
  }

  /**
   * Assigns the relayState (read from the RelayState POST parameter).
   *
   * @param relayState the RelayState parameter
   */
  public void setRelayState(final String relayState) {
    this.relayState = relayState;
  }

  /**
   * Gets the signature state.
   *
   * @return the signature state
   */
  public SignatureState getState() {
    return this.state;
  }

  /**
   * Assigns the signature state.
   *
   * @param state the signature state
   */
  public void setState(final SignatureState state) {
    this.state = state;
  }

  /**
   * Gets the optional processing parameters giving directives about the processing.
   *
   * @return processing parameters, or {@code null}
   */
  public SignResponseProcessingParameters getParameters() {
    return this.parameters;
  }

  /**
   * Assigns the processing parameters giving directives about the processing.
   *
   * @param parameters processing parameters
   */
  public void setParameters(final SignResponseProcessingParameters parameters) {
    this.parameters = parameters;
  }

  /**
   * Builder for {@code ProcessSignResponseInput} objects.
   */
  public static class ProcessSignResponseInputBuilder implements ObjectBuilder<ProcessSignResponseInput> {
    // Lombok
  }

}
