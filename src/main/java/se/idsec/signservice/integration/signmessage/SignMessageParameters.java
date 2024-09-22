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
package se.idsec.signservice.integration.signmessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;
import se.idsec.signservice.integration.authentication.AuthnRequirements;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * A representation of sign message parameters.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SignMessageParameters implements Serializable {

  /** For serialization. */
  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The sign message (non encrypted) content. */
  private String signMessage;

  /**
   * Tells whether the supplied sign message should be encrypted with {@link #getDisplayEntity()} as the recipient.
   */
  private boolean performEncryption;

  /** The sign message MIME type. See {@link SignMessageMimeType} for possible values. */
  private String mimeType;

  /**
   * Specifies if the requester of the signature requires that the sign message is displayed to the user. If the
   * Identity Provider cannot fulfill this requirement it must not proceed.
   */
  private Boolean mustShow;

  /** The ID (SAML entityID) of the entity (IdP) that should display this message. */
  private String displayEntity;

  /**
   * Gets the sign message (non encrypted) content according to specified mime type.
   *
   * @return the sign message
   */
  public String getSignMessage() {
    return this.signMessage;
  }

  /**
   * Assigns the sign message (non encrypted) content according to specified mime type.
   *
   * @param signMessage the sign message
   */
  public void setSignMessage(final String signMessage) {
    this.signMessage = signMessage;
  }

  /**
   * Tells whether the supplied sign message should be encrypted with {@link #getDisplayEntity()} as the recipient.
   *
   * @return tells whether encryption should be performed
   */
  public boolean isPerformEncryption() {
    return this.performEncryption;
  }

  /**
   * Assigns whether the supplied sign message should be encrypted with {@link #getDisplayEntity()} as the recipient.
   *
   * @param performEncryption Tells whether the supplied sign message should be encrypted
   */
  public void setPerformEncryption(final boolean performEncryption) {
    this.performEncryption = performEncryption;
  }

  /**
   * Gets the sign message MIME type. See {@link SignMessageMimeType} for possible values.
   *
   * @return the MIME type, or null which defaults to {@link SignMessageMimeType#TEXT}.
   */
  public String getMimeType() {
    return this.mimeType;
  }

  /**
   * The sign message MIME type. See {@link SignMessageMimeType} for possible values.
   *
   * @param mimeType the MIME type
   */
  public void setMimeType(final String mimeType) {
    final SignMessageMimeType _mimeType = SignMessageMimeType.fromMimeType(mimeType);
    this.mimeType = _mimeType.getMimeType();
  }

  /**
   * The sign message MIME type.
   *
   * @param mimeType the MIME type
   */
  public void setMimeType(final SignMessageMimeType mimeType) {
    this.mimeType = mimeType != null ? mimeType.getMimeType() : null;
  }

  /**
   * Specifies if the requester of the signature requires that the sign message is displayed to the user. If the
   * Identity Provider cannot fulfill this requirement it must not proceed.
   *
   * @return the MustShow flag, or null
   */
  public Boolean getMustShow() {
    return this.mustShow;
  }

  /**
   * Specifies if the requester of the signature requires that the sign message is displayed to the user. If the
   * Identity Provider cannot fulfill this requirement it must not proceed.
   *
   * @param mustShow the MustShow attribute
   */
  public void setMustShow(final Boolean mustShow) {
    this.mustShow = mustShow;
  }

  /**
   * Gets the ID (SAML entityID) of the entity (IdP) that should display this message.
   *
   * @return the entityID of the IdP that should display the message
   */
  public String getDisplayEntity() {
    return this.displayEntity;
  }

  /**
   * Assigns the ID (SAML entityID) of the entity (IdP) that should display this message.
   *
   * <p>
   * Note: The {@code DisplayEntity} attribute of the {@code SignMessage} element is required if the sign message is to
   * be encrypted. In almost all cases, except for some odd Proxy-IdP cases, this is the same value as the ID that is
   * supplied in the authentication requirements ({@link AuthnRequirements}). Therefore, if this method returns
   * {@code null}, and the message should be encrypted, the SignService Integration Service will use the
   * {@link AuthnRequirements#getAuthnServiceID()} value.
   * </p>
   *
   * @param displayEntity the SAML entityID for the display entity
   */
  public void setDisplayEntity(final String displayEntity) {
    this.displayEntity = displayEntity;
  }

  /**
   * A builder for {@code SignMessageParameters}.
   */
  public static class SignMessageParametersBuilder implements ObjectBuilder<SignMessageParameters> {
    // Lombok

    /**
     * The sign message MIME type.
     *
     * @param mimeType the MIME type
     * @return the builder
     */
    public SignMessageParametersBuilder mimeType(final String mimeType) {
      final SignMessageMimeType _mimeType = SignMessageMimeType.fromMimeType(mimeType);
      this.mimeType = _mimeType.getMimeType();
      return this;
    }

    /**
     * The sign message MIME type.
     *
     * @param mimeType the MIME type
     * @return the builder
     */
    public SignMessageParametersBuilder mimeType(final SignMessageMimeType mimeType) {
      this.mimeType = mimeType != null ? mimeType.getMimeType() : null;
      return this;
    }
  }

}
