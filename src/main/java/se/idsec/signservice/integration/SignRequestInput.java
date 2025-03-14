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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.AuthnRequirements;
import se.idsec.signservice.integration.certificate.SigningCertificateRequirements;
import se.idsec.signservice.integration.config.IntegrationServiceDefaultConfiguration;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.TbsDocument;
import se.idsec.signservice.integration.signmessage.SignMessageParameters;

import java.io.Serial;
import java.util.List;

/**
 * Class that represents the input to a {@link SignServiceIntegrationService#createSignRequest(SignRequestInput)}
 * operation.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SignRequestInput implements Extensible {

  @Serial
  private static final long serialVersionUID = -3516357562768818746L;

  /** The correlation ID to use for this process. */
  private String correlationId;

  /** The SignService policy. */
  private String policy;

  /** The entityID of the entity that requests a signature. */
  private String signRequesterID;

  /**
   * The URL to which the user agent along with the sign response message should be directed after a signature
   * operation.
   */
  private String returnUrl;

  /** The signature service URL to where SignRequest messages should be posted. */
  private String destinationUrl;

  /**
   * The algorithm identifier for the signature algorithm that should be used to sign the specified documents.
   */
  private String signatureAlgorithm;

  /**
   * The requirements that the sign requester has on the principal that is to perform the signature operation.
   */
  private AuthnRequirements authnRequirements;

  /** The requirements for the signature certificate that will be issued by the sign service. */
  private SigningCertificateRequirements certificateRequirements;

  /** The document(s) to be signed along with a per-document signing requirements and parameters. */
  @Singular
  private List<TbsDocument> tbsDocuments;

  /**
   * The sign message parameters that is used to build the sign message element that is included in the SignRequest.
   */
  private SignMessageParameters signMessageParameters;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Constructor.
   */
  public SignRequestInput() {
  }

  /**
   * Gets the correlation ID for the process.
   * <p>
   * See {@link #setCorrelationId(String)} for more information about the correlation ID.
   * </p>
   *
   * @return the correlation ID or null if not has been assigned
   */
  public String getCorrelationId() {
    return this.correlationId;
  }

  /**
   * Assigns the correlation ID for the process.
   * <p>
   * A sign requester may want to have the possibility to track an operation (via logs or by other means). Therefore,
   * the use of correlation ID:s is supported by the SignService Integration Service. If a correlation ID is supplied
   * this will be used in process and audit logs, and will be tied to the actual signature state ID that is generated by
   * the SignService Integration Service.
   * </p>
   * <p>
   * Note: The correlation ID is never sent to the sign service. It is only used within the SignService Integration
   * Service.
   * </p>
   *
   * @param correlationId the correlation ID
   */
  public void setCorrelationId(final String correlationId) {
    this.correlationId = correlationId;
  }

  /**
   * Gets the policy.
   *
   * @return the policy identifier for this operation, or {@code null} (that denotes the default policy)
   */
  public String getPolicy() {
    return this.policy;
  }

  /**
   * Assigns the policy to use.
   * <p>
   * A SignService Integration Service may be running under different policies, where a policy tells which
   * {@link IntegrationServiceDefaultConfiguration} that is active.
   * </p>
   *
   * @param policy the policy identifier to use for the operation
   */
  public void setPolicy(final String policy) {
    this.policy = policy;
  }

  /**
   * Gets the entityID of the entity that requests a signature.
   *
   * @return the sign requester ID, or {@code null} if the default ID should be used
   */
  public String getSignRequesterID() {
    return this.signRequesterID;
  }

  /**
   * Assigns the entityID of the entity that requests a signature.
   * <p>
   * If not provided the SignatureService Integration Service will use the default sign requester ID
   * ({@link IntegrationServiceDefaultConfiguration#getDefaultSignRequesterID()}).
   * </p>
   *
   * @param signRequesterID the sign requester ID
   */
  public void setSignRequesterID(final String signRequesterID) {
    this.signRequesterID = signRequesterID;
  }

  /**
   * Gets the URL to which the user agent along with the sign response message should be directed after a signature
   * operation.
   *
   * @return the URL to which a sign response is to be returned, or {@code null} if the default URL should be used
   */
  public String getReturnUrl() {
    return this.returnUrl;
  }

  /**
   * Assigns the URL to which the user agent along with the sign response message should be directed after a signature
   * operation.
   *
   * <p>
   * If not provided the SignatureService Integration Service will use the default return URL
   * ({@link IntegrationServiceDefaultConfiguration#getDefaultReturnUrl()}).
   * </p>
   *
   * @param returnUrl the URL to which a sign response is to be returned
   */
  public void setReturnUrl(final String returnUrl) {
    this.returnUrl = returnUrl;
  }

  /**
   * Gets the signature service URL to where SignRequest messages should be posted.
   *
   * @return the signature service URL to where SignRequest messages should be posted, or {@code null} if the default
   *     should be used
   */
  public String getDestinationUrl() {
    return this.destinationUrl;
  }

  /**
   * Assigns the signature service URL to where SignRequest messages should be posted.
   *
   * <p>
   * If not provided the SignatureService Integration Service will use the default return URL
   * ({@link IntegrationServiceDefaultConfiguration#getDefaultDestinationUrl()}).
   * </p>
   *
   * @param destinationUrl the signature service URL to where SignRequest messages should be posted
   */
  public void setDestinationUrl(final String destinationUrl) {
    this.destinationUrl = destinationUrl;
  }

  /**
   * Gets the algorithm identifier for the signature algorithm that should be used to sign the specified documents.
   *
   * @return signature algorithm identifier, or {@code null} for default algorithm
   */
  public String getSignatureAlgorithm() {
    return this.signatureAlgorithm;
  }

  /**
   * Assigns the algorithm identifier for the signature algorithm that should be used to sign the specified documents.
   *
   * <p>
   * If not provided the SignatureService Integration Service will use the default signature algorithm
   * ({@link IntegrationServiceDefaultConfiguration#getDefaultSignatureAlgorithm()}).
   * </p>
   *
   * @param signatureAlgorithm signature algorithm identifier
   */
  public void setSignatureAlgorithm(final String signatureAlgorithm) {
    this.signatureAlgorithm = signatureAlgorithm;
  }

  /**
   * Gets the requirements that the sign requester has on the principal that is to perform the signature operation.
   *
   * @return the authentication requirements
   */
  public AuthnRequirements getAuthnRequirements() {
    return this.authnRequirements;
  }

  /**
   * Assigns the requirements that the sign requester has on the principal that is to perform the signature operation.
   *
   * @param authnRequirements authentication requirements
   */
  public void setAuthnRequirements(final AuthnRequirements authnRequirements) {
    this.authnRequirements = authnRequirements;
  }

  /**
   * Gets the requirements for the signature certificate that will be issued by the sign service.
   *
   * @return requirements for the signing certificate, or {@code null} for default requirements
   */
  public SigningCertificateRequirements getCertificateRequirements() {
    return this.certificateRequirements;
  }

  /**
   * Assigns the requirements for the signature certificate that will be issued by the sign service.
   *
   * <p>
   * If not provided the SignatureService Integration Service will use the default requirements
   * ({@link IntegrationServiceDefaultConfiguration#getDefaultCertificateRequirements()}).
   * </p>
   *
   * @param certificateRequirements the requirements for the signing certificate
   */
  public void setCertificateRequirements(final SigningCertificateRequirements certificateRequirements) {
    this.certificateRequirements = certificateRequirements;
  }

  /**
   * Gets the document(s) to be signed along with a per-document signing requirements and parameters.
   *
   * @return a list of To-be-signed documents
   */
  public List<TbsDocument> getTbsDocuments() {
    return this.tbsDocuments;
  }

  /**
   * Assigns the document(s) to be signed along with a per-document signing requirements and parameters. At least one
   * document must be supplied.
   *
   * @param tbsDocuments a list of To-be-signed documents
   */
  public void setTbsDocuments(final List<TbsDocument> tbsDocuments) {
    this.tbsDocuments = tbsDocuments;
  }

  /**
   * Gets the sign message parameters that is used to build the sign message element that is included in the
   * SignRequest.
   *
   * @return the sign message parameters
   */
  public SignMessageParameters getSignMessageParameters() {
    return this.signMessageParameters;
  }

  /**
   * Assigns the sign message parameters that is used to build the sign message element that is included in the
   * SignRequest.
   *
   * @param signMessageParameters the sign message parameters
   */
  public void setSignMessageParameters(final SignMessageParameters signMessageParameters) {
    this.signMessageParameters = signMessageParameters;
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
   * Builder for {@code SignRequestInput}.
   */
  public static class SignRequestInputBuilder implements ObjectBuilder<SignRequestInput> {
    // Lombok
  }
}
