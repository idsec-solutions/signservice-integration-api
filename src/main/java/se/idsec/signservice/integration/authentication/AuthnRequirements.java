/*
 * Copyright 2019-2023 IDsec Solutions AB
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
package se.idsec.signservice.integration.authentication;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;
import se.idsec.signservice.integration.config.IntegrationServiceDefaultConfiguration;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * A sign requester specifies a set of authentication requirements regarding the signer when sending a SignRequest
 * message. This class represents these requirements.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
public class AuthnRequirements implements Extensible {

  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /**
   * The entityID of the authentication service (Identity Provider) that will authenticate the signer as part of the
   * signature process.
   */
  private String authnServiceID;

  /**
   * An opaque string that can be used to inform the Signing Service about specific requirements regarding the user
   * authentication at the given Identity Provider.
   */
  private String authnProfile;

  /**
   * The authentication context reference identifier(s) (URI(s)) that identifies the context under which the signer
   * should be authenticated. This identifier is often referred to as the "level of assurance" (LoA).
   */
  @Singular
  private List<String> authnContextClassRefs;

  /**
   * A list of identity attribute values that the sign requestor requires the authentication service (IdP) to validate
   * and deliver (and the signature service to assert).
   */
  @Singular
  private List<SignerIdentityAttributeValue> requestedSignerAttributes;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Default constructor.
   */
  public AuthnRequirements() {
  }

  /**
   * Constructor.
   *
   * @param authnServiceID entityID of the authentication service (Identity Provider)
   * @param authnProfile optional authentication profile
   * @param authnContextClassRefs authentication context reference identifier(s)
   * @param requestedSignerAttributes list of identity attribute values that the sign requestor requires the
   *          authentication service (IdP) to validate and deliver (and the signature service to assert)
   * @param extension extensions for the object
   */
  public AuthnRequirements(final String authnServiceID, final String authnProfile,
      final List<String> authnContextClassRefs, final List<SignerIdentityAttributeValue> requestedSignerAttributes,
      final Extension extension) {
    this.authnServiceID = authnServiceID;
    this.authnProfile = authnProfile;
    this.authnContextClassRefs = authnContextClassRefs;
    this.requestedSignerAttributes = requestedSignerAttributes;
    this.extension = extension;
  }

  /**
   * Gets the entityID of the authentication service (Identity Provider) that will authenticate the signer as part of
   * the signature process.
   *
   * @return the entityID of the authentication service to use
   * @see IntegrationServiceDefaultConfiguration#getDefaultAuthnServiceID()
   */
  public String getAuthnServiceID() {
    return this.authnServiceID;
  }

  /**
   * Assigns the entityID of the authentication service (Identity Provider) that will authenticate the signer as part of
   * the signature process.
   *
   * <p>
   * In almost all cases a user is first authenticated before performing a signature, and the entityID is then the ID of
   * the Identity Provider that authenticated the user during login to the service requesting the signature.
   * </p>
   * <p>
   * In the rare cases where a user is not authenticated when the signature is requested, it is the signature
   * requester's responsibility to prompt the user for the authentication service to use, or by other means acquire this
   * information.
   * </p>
   *
   * @param authnServiceID the entityID of the authentication service to use
   */
  public void setAuthnServiceID(final String authnServiceID) {
    this.authnServiceID = authnServiceID;
  }

  /**
   * Gets the authentication profile.
   * <p>
   * This is a an opaque string that can be used to inform the Signing Service about specific requirements regarding the
   * user authentication at the given Identity Provider.
   * </p>
   *
   * @return opaque string representing an authentication profile
   */
  public String getAuthnProfile() {
    return this.authnProfile;
  }

  /**
   * Assigns the authentication profile.
   * <p>
   * This is an opaque string that can be used to inform the Signing Service about specific requirements regarding the
   * user authentication at the given Identity Provider.
   * </p>
   *
   * <p>
   * Note: Before setting this property, ensure that the receiving Signature Service supports version 1.4 of the "DSS
   * Extension for Federated Central Signing Services" specification.
   * </p>
   *
   * @param authnProfile opaque string representing an authentication profile
   */
  public void setAuthnProfile(final String authnProfile) {
    this.authnProfile = authnProfile;
  }

  /**
   * Gets the authentication context reference identifier(s) (URI(s)) that identifies the context under which the signer
   * should be authenticated. This identifier is often referred to as the "level of assurance" (LoA).
   *
   * @return the authentication context reference URI(s)
   * @see IntegrationServiceDefaultConfiguration#getDefaultAuthnContextRef()
   */
  public List<String> getAuthnContextClassRefs() {
    return this.authnContextClassRefs;
  }

  /**
   * Assigns the authentication context reference identifier(s) (URI(s)) that identifies the context under which the
   * signer should be authenticated. This identifier is often referred to as the "level of assurance" (LoA).
   * <p>
   * In the normal case where the user already has been authenticated, the authentication context reference identifier
   * received from the authentication process should be used.
   * </p>
   * <p>
   * If several URI:s are supplied it states that the Signature Service should assert that the user is authenticated
   * according to one of the supplied URI:s.
   * </p>
   * <p>
   * Note: If setting more than one URI, ensure that the receiving Signature Service supports version 1.4 of the "DSS
   * Extension for Federated Central Signing Services" specification.
   * </p>
   *
   * @param authnContextClassRefs the authentication context reference URI(s)
   */
  public void setAuthnContextClassRefs(final List<String> authnContextClassRefs) {
    this.authnContextClassRefs = authnContextClassRefs;
  }

  /**
   * For backwards compatibility. Use {@link #setAuthnContextClassRefs(List)} instead.
   *
   * @param authnContextRef the AuthnContextClassRef URI to add
   */
  @Deprecated
  public void setAuthnContextRef(final String authnContextRef) {
    this.setAuthnContextClassRefs(Arrays.asList(authnContextRef));
  }

  /**
   * Gets the list of identity attribute values that the sign requestor requires the authentication service (IdP) to
   * validate and deliver (and the signature service to assert).
   *
   * @return requestedSignerAttributes a list of requested attribute values
   */
  public List<SignerIdentityAttributeValue> getRequestedSignerAttributes() {
    return this.requestedSignerAttributes;
  }

  /**
   * Assigns the list of identity attribute values that the sign requestor requires the authentication service (IdP) to
   * validate and deliver (and the signature service to assert).
   * <p>
   * Typically, a sign requester includes the identity attributes that binds the signature operation to the principal
   * that authenticated at the sign requester service, for example the personalIdentityNumber of the principal.
   * </p>
   *
   * @param requestedSignerAttributes a list of requested attribute values
   */
  public void setRequestedSignerAttributes(final List<SignerIdentityAttributeValue> requestedSignerAttributes) {
    this.requestedSignerAttributes = requestedSignerAttributes;
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
   * Builder for {@code AuthnRequirements} objects.
   */
  public static class AuthnRequirementsBuilder implements ObjectBuilder<AuthnRequirements> {
    // Lombok

    /**
     * For backwards compatibility. Use {@link #authnContextClassRef(String)} instead.
     *
     * @param authnContextRef the AuthnContextClassRef URI
     * @return the builder
     */
    @Deprecated
    public AuthnRequirementsBuilder authnContextRef(final String authnContextRef) {
      return this.authnContextClassRef(authnContextRef);
    }
  }

}
