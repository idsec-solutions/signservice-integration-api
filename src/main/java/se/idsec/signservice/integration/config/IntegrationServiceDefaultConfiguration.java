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
package se.idsec.signservice.integration.config;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import se.idsec.signservice.integration.SignRequestInput;
import se.idsec.signservice.integration.SignResponseProcessingParameters;
import se.idsec.signservice.integration.SignServiceIntegrationService;
import se.idsec.signservice.integration.certificate.SigningCertificateRequirements;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.SignatureState;
import se.idsec.signservice.integration.document.pdf.PdfSignatureImageTemplate;
import se.idsec.signservice.integration.security.EncryptionParameters;

/**
 * Interface that represents the default settings of a SignService Integration Service policy/instance.
 * 
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface IntegrationServiceDefaultConfiguration extends Extensible {

  /**
   * Gets the integration policy name for which this configuration applies.
   * 
   * @return the policy identifier
   */
  @Nonnull
  String getPolicy();

  /**
   * Gets the default ID of the entity that requests a signature. If SAML is used as the authentication protocol, this
   * is the SAML entityID of the sign requester.
   * <p>
   * This value is used if {@link SignRequestInput#getSignRequesterID()} returns {@code null}.
   * </p>
   * 
   * @return the default sign requester ID
   */
  @Nonnull
  String getDefaultSignRequesterID();

  /**
   * Gets the default URL to which the user agent along with the sign response message should be directed after a
   * signature operation.
   * <p>
   * This value is used if {@link SignRequestInput#getReturnUrl()} returns {@code null}.
   * </p>
   * 
   * @return the default URL to which a sign response is to be returned
   */
  @Nonnull
  String getDefaultReturnUrl();

  /**
   * Gets the default algorithm identifier for the signature algorithm that should be used during signing of specified
   * tasks.
   * <p>
   * This value is used if {@link SignRequestInput#getSignatureAlgorithm()} returns {@code null}.
   * </p>
   * 
   * @return signature algorithm identifier
   */
  @Nonnull
  String getDefaultSignatureAlgorithm();

  /**
   * Gets the entityID of the signature service. If SAML is used as the authentication protocol, this is the SAML
   * entityID of the SAML Service Provider that is running in the signature service.
   * 
   * @return the ID of the signature service
   */
  @Nonnull
  String getSignServiceID();

  /**
   * Gets the default signature service URL to where SignRequest messages should be posted.
   * <p>
   * This value is used if {@link SignRequestInput#getDestinationUrl()} returns {@code null}.
   * </p>
   * 
   * @return the default destination URL of the signature service to where sign messages should be posted
   */
  @Nonnull
  String getDefaultDestinationUrl();

  /**
   * Gets the default signing certificate requirements to use for SignRequest messages created under this
   * policy/configuration.
   * <p>
   * This value is used if {@link SignRequestInput#getCertificateRequirements()} returns {@code null}.
   * </p>
   * 
   * @return the default signing certificate requirements
   */
  @Nonnull
  SigningCertificateRequirements getDefaultCertificateRequirements();

  /**
   * A policy may have one, or more, image templates for visible PDF signatures in its configuration. See
   * {@link PdfSignatureImageTemplate}. This method gets these templates
   * 
   * @return a list of image templates for visible PDF signatures, or null if none exists
   */
  @Nullable
  List<PdfSignatureImageTemplate> getPdfSignatureImageTemplates();

  /**
   * Tells whether the SignService Integration Service is running in stateless mode or not.
   * <p>
   * A SignService Integration Service may execute in a stateless mode, meaning that it does not keep a session state
   * and leaves it up to the caller to maintain the state between calls to
   * {@link SignServiceIntegrationService#createSignRequest(SignRequestInput)} and
   * {@link SignServiceIntegrationService#processSignResponse(String, String, SignatureState, SignResponseProcessingParameters)},
   * or it may execute in a stateful mode, meaning that it keeps the necessary data between calls to
   * {@link SignServiceIntegrationService#createSignRequest(SignRequestInput)} and
   * {@link SignServiceIntegrationService#processSignResponse(String, String, SignatureState, SignResponseProcessingParameters)}
   * and the only thing the caller needs to keep track of its the ID of the signature operation (see
   * {@link SignatureState#getId()}.
   * </p>
   * 
   * @return if the SignService Integration Service is running in stateless mode true is returned, otherwise false
   * @see SignatureState
   */
  boolean isStateless();

  /**
   * Gets the default encryption parameters (algorithms) that is used by the SignService Integration Service when
   * encrypting a SignMessage. The sign requester can not override these values, but the recipient may declare other
   * algorithms to use (in the SAML case, this is done in IdP metadata).
   * 
   * @return the default encryption parameters
   */
  @Nonnull
  EncryptionParameters getDefaultEncryptionParameters();

  /**
   * Gets the signing certificate that the SignService Integration Service uses to sign SignRequest messages.
   * <p>
   * The format on the returned certificate is the Base64-encoding of the DER-encoding.
   * </p>
   * <p>
   * <b>Note:</b> This certificate has nothing to do with the signing certificates that are issued by the sign service.
   * </p>
   * 
   * @return the signature certificate for the SignService Integration Service
   */
  @Nonnull
  String getSignatureCertificate();

}
