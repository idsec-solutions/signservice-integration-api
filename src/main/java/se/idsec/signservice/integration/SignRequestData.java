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
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.core.SignatureState;

/**
 * Domain class representing the result of a {@link SignServiceIntegrationService#createSignRequest(SignRequestInput)}
 * call. This class holds the information needed to send a {@code dss:SignRequest} to a signature service.
 * 
 * <p>
 * Chapter 3 of <a href=
 * "https://docs.swedenconnect.se/technical-framework/latest/ELN-0607_-_Implementation_Profile_for_using_DSS_in_Central_Signing_Services.html#http-post-binding">Implementation
 * Profile for using OASIS DSS in Central Signing Services</a> describes how a sign request is transfered to the
 * signature service. Below is an example of an XHTML form:
 * </p>
 * 
 * <pre>
 * {@code
 * <?xml version='1.0' encoding='UTF-8'?>
 * <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>
 * <html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en'>
 * <body onload='document.forms[0].submit()'>
 *   <noscript>
 *     <p><strong>Note:</strong> Since your browser does not support JavaScript,
 *     you must press the Continue button once to proceed.</p>
 *   </noscript>
 *   <form action='https://sig.example.com/signrequest' method='post'>
 *     <input type='hidden' name='Binding' value='POST/XML/1.0'/>
 *     <input type='hidden' name='RelayState' value='56345145a482995d'/>
 *     <input type='hidden' name='EidSignRequest' value='PD94bWC...WVzdD4='/>
 *     <noscript>
 *       <input type='submit' value='Continue'/>
 *     </noscript>
 *   </form>
 * </body>}
 * </pre>
 * 
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SignRequestData implements Extensible {

  /** The default binding. */
  public static final String DEFAULT_BINDING = "POST/XML/1.0";

  /**
   * State for a signature operation.
   * 
   * <p>
   * This state must be maintained by the signature requester and when a sign response has been received from the
   * signature service be supplied in the
   * {@link SignServiceIntegrationService#processSignResponse(String, String, SignatureState, SignResponseProcessingParameters)}
   * call.
   * </p>
   * 
   * @param state the signature state
   * @return the signature state for this operation
   */
  @Setter
  @Getter
  private SignatureState state;

  /**
   * The Base64-encoded SignRequest message that is to be posted to the signature service.
   * 
   * <p>
   * This value should be posted to the signature service in a form where the parameter has the name
   * {@code EidSignRequest}. See example above.
   * </p>
   * 
   * @param signRequest the sign request (in Base64-encoding)
   * @return the encoded SignRequest message
   */
  @Setter
  @Getter
  private String signRequest;

  /**
   * The relay state. This is the same value as the {@code RequestID} attribute of the SignRequest <b>and</b>
   * {@link SignatureState#getId()}.
   * 
   * <p>
   * This value should be posted to the signature service in a form where the parameter has the name {@code RelayState}.
   * See example above.
   * </p>
   * <p>
   * <b>Note:</b> The RelayState used in communication with the signature service has the same name as the parameter
   * used in SAML authentication requests, <b>but</b> in SAML the value is opaque and does not bind to any value in the
   * request as is the case for signature service communication. An unlucky re-use of the term RelayState.
   * </p>
   * 
   * Assigns the relay state variable.
   * 
   * @param relayState the relay state variable
   * @return the relay state value
   */
  @Setter
  @Getter
  private String relayState;

  /**
   * The identifier for the binding of the message that is to be sent.
   * 
   * <p>
   * This value should be posted to the signature service in a form where the parameter has the name {@code Binding}.
   * See example above.
   * </p>
   * <p>
   * Currently, the only supported value is "POST/XML/1.0".
   * </p>
   * 
   * @param binding the binding identifier
   * @return the binding identifier
   */
  @Setter
  @Getter
  @Builder.Default
  private String binding = DEFAULT_BINDING;

  /**
   * The signature service URL to which the SignRequest should be posted.
   * 
   * @param destinationUrl the signature service URL to which the SignRequest should be posted
   * @return signature service destination URL
   */
  @Setter
  @Getter
  private String destinationUrl;

  /** Extensions for the object. */
  private Extension extension;

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
   * Builder for {@code SignRequestData} objects.
   */
  public static class SignRequestDataBuilder implements ObjectBuilder<SignRequestData> {
    private String binding = SignRequestData.DEFAULT_BINDING;

    // Lombok generates the code ...
  }

}
