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
package se.idsec.signservice.integration.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;
import java.util.List;

/**
 * A representation of the assertion info for the signer's "authentication for signature" that was performed during a
 * signature operation.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class SignerAssertionInformation implements Extensible {

  @Serial
  private static final long serialVersionUID = -2836334101319585416L;

  /** Identity attributes for the signer. */
  @Singular
  private List<SignerIdentityAttributeValue> signerAttributes;

  /** The time (in millis since epoch) when the SignService authenticated the signer. */
  private long authnInstant;

  /**
   * The entityID of the authentication service (Identity Provider) that will authenticate the signer as part of the
   * signature process.
   */
  private String authnServiceID;

  /**
   * The authentication context reference identifier (a URI) that identifies the context under which the signer was
   * authenticated.
   */
  private String authnContextRef;

  /**
   * An optional identifier of the type of authentication that was used for the "authentication for signature"
   * operation, e.g. "saml".
   */
  private String authnType;

  /**
   * Contains the unique ID of the assertion issued for the user's "authentication for signature" operation.
   */
  private String assertionReference;

  /**
   * The assertion issued for the user's "authentication for signature" operation (in Base64 encoded format).
   */
  private String assertion;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Constructor.
   */
  public SignerAssertionInformation() {
  }

  /**
   * Gets the identity attributes for the signer.
   *
   * @return the identity attributes for the signer
   */
  public List<SignerIdentityAttributeValue> getSignerAttributes() {
    return this.signerAttributes;
  }

  /**
   * Assigns the identity attributes for the signer.
   *
   * @param signerAttributes the identity attributes for the signer
   */
  public void setSignerAttributes(final List<SignerIdentityAttributeValue> signerAttributes) {
    this.signerAttributes = signerAttributes;
  }

  /**
   * Gets the time (in millis since epoch) when the SignService authenticated the signer.
   *
   * @return the authentication instant
   */
  public long getAuthnInstant() {
    return this.authnInstant;
  }

  /**
   * Assigns the time (in millis since epoch) when the SignService authenticated the signer.
   *
   * @param authnInstant the authentication instant
   */
  public void setAuthnInstant(final long authnInstant) {
    this.authnInstant = authnInstant;
  }

  /**
   * Gets the entityID of the authentication service (Identity Provider) that will authenticate the signer as part of
   * the signature process.
   *
   * @return the entityID of the authentication service that authenticated the signer
   */
  public String getAuthnServiceID() {
    return this.authnServiceID;
  }

  /**
   * Assigns the entityID of the authentication service (Identity Provider) that will authenticate the signer as part of
   * the signature process.
   *
   * @param authnServiceID the entityID of the authentication service that authenticated the signer
   */
  public void setAuthnServiceID(final String authnServiceID) {
    this.authnServiceID = authnServiceID;
  }

  /**
   * Gets the authentication context reference identifier (a URI) that identifies the context under which the signer was
   * authenticated. This identifier is often referred to as the "level of assurance" (LoA).
   *
   * @return the authentication context reference URI
   */
  public String getAuthnContextRef() {
    return this.authnContextRef;
  }

  /**
   * Assigns the authentication context reference identifier (a URI) that identifies the context under which the signer
   * was authenticated. This identifier is often referred to as the "level of assurance" (LoA).
   *
   * @param authnContextRef the authentication context reference URI
   */
  public void setAuthnContextRef(final String authnContextRef) {
    this.authnContextRef = authnContextRef;
  }

  /**
   * Gets the identifier for the type of authentication that was used for the "authentication for signature" operation,
   * e.g. "saml".
   *
   * @return the authentication type, or {@code null} if not set
   */
  public String getAuthnType() {
    return this.authnType;
  }

  /**
   * Assigns the identifier for the type of authentication that was used for the "authentication for signature"
   * operation, e.g. "saml".
   *
   * @param authnType authentication type
   */
  public void setAuthnType(final String authnType) {
    this.authnType = authnType;
  }

  /**
   * Gets the unique ID of the assertion issued for the user's "authentication for signature" operation. See also
   * {@link #getAssertion()}.
   *
   * @return the assertion ID from the user authentication
   */
  public String getAssertionReference() {
    return this.assertionReference;
  }

  /**
   * Assigns the unique ID of the assertion issued for the user's "authentication for signature" operation.
   *
   * @param assertionReference the assertion ID from the user authentication
   */
  public void setAssertionReference(final String assertionReference) {
    this.assertionReference = assertionReference;
  }

  /**
   * Gets the assertion issued for the user's "authentication for signature" operation (in Base64 encoded format).
   * <p>
   * This is only available if the assertion was passed back in the SignResponse from the SignService.
   * </p>
   *
   * @return the assertion from the user authentication or {@code null}
   */
  public String getAssertion() {
    return this.assertion;
  }

  /**
   * Assigns the assertion issued for the user's "authentication for signature" operation (in Base64 encoded format).
   *
   * @param assertion the assertion from the user authentication
   */
  public void setAssertion(final String assertion) {
    this.assertion = assertion;
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
   * Builder for {@code SignerAssertionInformation} objects.
   */
  public static class SignerAssertionInformationBuilder implements ObjectBuilder<SignerAssertionInformation> {
    // Lombok
  }

}
