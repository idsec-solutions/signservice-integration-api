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
package se.idsec.signservice.integration.core;

import se.idsec.signservice.integration.SignRequestInput;
import se.idsec.signservice.integration.SignResponseProcessingParameters;
import se.idsec.signservice.integration.SignServiceIntegrationService;
import se.idsec.signservice.integration.config.IntegrationServiceDefaultConfiguration;

/**
 * The {@code SignatureState} is used to represent the state of an ongoing signature operation. It is generated by the
 * {@link SignServiceIntegrationService#createSignRequest(SignRequestInput)} method and should be supplied as a
 * parameter to
 * {@link SignServiceIntegrationService#processSignResponse(String, String, SignatureState, SignResponseProcessingParameters)}.
 * 
 * <p>
 * If the SignService Integration Service instance/profile is executing in stateless mode (see
 * {@link IntegrationServiceDefaultConfiguration#isStateless()}), the {@link #getState()} holds the session state. In
 * statefull mode the state is stored internally in the SignService Integration Service and will not be part of the
 * resulting {@code SignatureState} object that is returned by
 * {@link SignServiceIntegrationService#createSignRequest(SignRequestInput)}.
 * </p>
 * 
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface SignatureState {

  /**
   * Returns the unique identifier for the signature operation.
   * 
   * @return the signature operation identifier
   */
  String getId();

  /**
   * Holds the session state.
   * 
   * <p>
   * Note: Only the implementation of the SignService Integration Service needs to understand how to interpret this
   * data.
   * </p>
   * 
   * @return the session state, or null
   */
  Object getState();

}
