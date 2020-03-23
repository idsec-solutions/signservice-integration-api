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

import java.util.List;

import se.idsec.signservice.integration.config.IntegrationServiceDefaultConfiguration;
import se.idsec.signservice.integration.config.PolicyNotFoundException;
import se.idsec.signservice.integration.core.SignatureState;
import se.idsec.signservice.integration.core.error.InputValidationException;
import se.idsec.signservice.integration.core.error.SignServiceIntegrationException;

/**
 * Interface describing the API for the SignService Integration Service.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface SignServiceIntegrationService {

  /**
   * Creates a SignRequest message that is to be posted to the signature service.
   *
   * @param signRequestInput
   *          the requirements and input for how to create the SignRequest
   * @return a SignRequestData object containing the encoded (and signed) SignRequest along with additional parameters
   * @throws InputValidationException
   *           for errors in the supplied input
   * @throws SignServiceIntegrationException
   *           for errors creating the SignRequest
   */
  SignRequestData createSignRequest(final SignRequestInput signRequestInput)
      throws InputValidationException, SignServiceIntegrationException;

  /**
   * When the service that has ordered the signing operation receives the sign response message it should invoke this
   * method to process this response. The processing will validate the signature of each signed document and return the
   * signed document(s) along with metadata about the signature process.
   * 
   * <p>
   * Note: The {@code relayState} must be equal to supplied state ({@link SignatureState#getId()}). This parameter is
   * redundant, but included for pedagogical reasons since the RelayState POST parameter is received.
   * </p>
   * 
   * @param signResponse
   *          the Base64-encoded SignResponse message (from the EidSignResponse POST parameter)
   * @param relayState
   *          the relayState (from the RelayState POST parameter)
   * @param state
   *          the signature state
   * @param parameters
   *          optional processing parameter giving directives about the processing
   * @return a SignatureResult object containing the signed documents and metadata about the signature
   * @throws SignResponseCancelStatusException
   *           the sign service reported that the user cancelled the operation
   * @throws SignResponseErrorStatusException
   *           the sign service reported an error
   * @throws SignServiceIntegrationException
   *           for validation and processing errors
   */
  SignatureResult processSignResponse(final String signResponse, final String relayState,
      final SignatureState state, final SignResponseProcessingParameters parameters)
      throws SignResponseCancelStatusException, SignResponseErrorStatusException, SignServiceIntegrationException;

  /**
   * Given the name of a SignService Integration policy, the method returns the default settings used for this policy.
   *
   * @param policy
   *          the policy name (null is interpreted as the default policy)
   * @return the default service configuration for the given policy
   * @throws PolicyNotFoundException
   *           if the given policy does not exist
   */
  IntegrationServiceDefaultConfiguration getConfiguration(final String policy) throws PolicyNotFoundException;

  /**
   * Returns a list of names of the policies that are defined for this instance of the SignService Integration Service.
   *
   * @return a non-empty list of policy names
   */
  List<String> getPolicies();

  /**
   * Returns the version identifier for the current version of the SignService Integration Service.
   * 
   * @return version string
   */
  String getVersion();

}
