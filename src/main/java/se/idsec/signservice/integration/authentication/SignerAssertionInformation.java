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
package se.idsec.signservice.integration.authentication;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
 * A representation of the assertion info for the signer's "authentication for signature" that was performed during a
 * signature operation.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignerAssertionInformation implements Extensible {

  /**
   * Identity attributes for the signer.
   * 
   * @param signerAttributes
   *          the identity attributes for the signer
   * @return the identity attributes for the signer
   */
  @Nonnull
  @Setter
  @Getter
  @Singular
  private List<SignerIdentityAttributeValue> signerAttributes;

  /**
   * The time (in millis since epoch) when the SignService authenticated the signer.
   * 
   * @param authnInstant
   *          the authentication instant
   * @return the authentication instant
   */
  @Nonnull
  @Setter
  @Getter
  private long authnInstant;

  /**
   * The entityID of the authentication service (Identity Provider) that will authenticated the signer as part of the
   * signature process.
   *
   * @param authnServiceID
   *          the entityID of the authentication service that authenticated the signer
   * @return the entityID of the authentication service that authenticated the signer
   */
  @Nonnull
  @Setter
  @Getter
  private String authnServiceID;

  /**
   * The authentication context reference identifier (an URI) that identifies the context under which the signer was
   * authenticated. This identifier is often referred to as the "level of assurance" (LoA).
   *
   * @param authnContextRef
   *          the authentication context reference URI
   * @return the authentication context reference URI
   */
  @Nonnull
  @Setter
  @Getter
  private String authnContextRef;

  /**
   * An optional identifier of the type of authentication that was used for the "authentication for signature"
   * operation, e.g. "saml".
   * 
   * @param authnType
   *          authentication type
   * @return the authentication type
   */
  @Nullable
  @Setter
  @Getter
  private String authnType;

  /**
   * Contains the unique ID of the assertion issued for the user's "authentication for signature" operation. See also
   * {@link #getAssertion()}.
   * 
   * @param assertionReference
   *          the assertion ID from the user authentication
   * @return the assertion ID from the user authentication
   */
  @Nonnull
  @Setter
  @Getter
  private String assertionReference;

  /**
   * The assertion issued for the user's "authentication for signature" operation (in Base64 encoded format).
   * <p>
   * This is only set if the assertion was passed back in the SignResponse from the SignService.
   * </p>
   * 
   * @param assertion
   *          the assertion from the user authentication
   * @return the assertion from the user authentication or null
   */
  @Nullable
  @Setter
  @Getter
  private String assertion;

  /**
   * Extensions for the object.
   * 
   * @param extension
   *          the extension object to assign
   */
  @Setter
  private Extension extension;

  /** {@inheritDoc} */
  @Nullable
  @Override
  public Extension getExtension() {
    return this.extension;
  }

  /**
   * Builder for {@code SignerAssertionInformation} objects.
   */
  public static class SignerAssertionInformationBuilder implements ObjectBuilder<SignerAssertionInformation> {
    // Lombok
  }

}
