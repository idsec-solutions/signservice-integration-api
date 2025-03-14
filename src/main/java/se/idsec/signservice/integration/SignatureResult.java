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

import java.io.Serial;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.SignerAssertionInformation;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.SignedDocument;

/**
 * Representation of the result of a signature operation.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class SignatureResult implements Extensible {

  @Serial
  private static final long serialVersionUID = -1686042989883226299L;

  /** The ID for the signature operation. This ID corresponds to the RequestID of the SignRequest and SignResponse. */
  private String id;

  /** The correlation ID. This is the ID that is used in all logging events. */
  private String correlationId;

  /** The signed documents. */
  @Singular
  private List<SignedDocument> signedDocuments;

  /**
   * Contains information about the signer's "authentication for signature" that was part of the signature operation.
   */
  private SignerAssertionInformation signerAssertionInformation;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Default constructor.
   */
  public SignatureResult() {
  }

  /**
   * Constructor.
   *
   * @param id the ID for the signature operation
   * @param correlationId the correlation ID
   * @param signedDocuments the signed documents
   * @param signerAssertionInformation information about the signer's "authentication for signature" that was part of
   *          the signature operation
   * @param extension extensions for the object
   */
  public SignatureResult(final String id, final String correlationId, final List<SignedDocument> signedDocuments,
      final SignerAssertionInformation signerAssertionInformation, final Extension extension) {
    this.id = id;
    this.correlationId = correlationId;
    this.signedDocuments = signedDocuments;
    this.signerAssertionInformation = signerAssertionInformation;
    this.extension = extension;
  }

  /**
   * Gets the ID for the signature operation. This ID corresponds to the RequestID of the SignRequest and SignResponse.
   *
   * @return the operation ID
   */
  public String getId() {
    return this.id;
  }

  /**
   * Assigns the ID for the signature operation. This ID corresponds to the RequestID of the SignRequest and
   * SignResponse.
   *
   * @param id the operation ID
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * Gets the correlation ID. This is the ID that is used in all logging events.
   *
   * @return the correlation ID
   */
  public String getCorrelationId() {
    return this.correlationId;
  }

  /**
   * Assigns the correlation ID. This is the ID that is used in all logging events.
   *
   * @param correlationId the correlationId to use for this process
   */
  public void setCorrelationId(final String correlationId) {
    this.correlationId = correlationId;
  }

  /**
   * Gets the signed documents.
   *
   * @return the signed documents
   */
  public List<SignedDocument> getSignedDocuments() {
    return this.signedDocuments;
  }

  /**
   * Assigns the signed documents.
   *
   * @param signedDocuments the signed documents
   */
  public void setSignedDocuments(final List<SignedDocument> signedDocuments) {
    this.signedDocuments = signedDocuments;
  }

  /**
   * Gets information about the signer's "authentication for signature" that was part of the signature operation.
   *
   * @return the assertion information
   */
  public SignerAssertionInformation getSignerAssertionInformation() {
    return this.signerAssertionInformation;
  }

  /**
   * Assigns information about the signer's "authentication for signature" that was part of the signature operation.
   *
   * @param signerAssertionInformation assertion information
   */
  public void setSignerAssertionInformation(final SignerAssertionInformation signerAssertionInformation) {
    this.signerAssertionInformation = signerAssertionInformation;
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
   * Builder for {@code SignatureResult} objects.
   */
  public static class SignatureResultBuilder implements ObjectBuilder<SignatureResult> {
    // Lombok
  }

}
