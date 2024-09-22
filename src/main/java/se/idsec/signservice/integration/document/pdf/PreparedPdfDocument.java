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
package se.idsec.signservice.integration.document.pdf;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;
import se.idsec.signservice.integration.ExtendedSignServiceIntegrationService;
import se.idsec.signservice.integration.SignServiceIntegrationService;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.TbsDocument;

import java.io.Serial;
import java.util.List;

/**
 * The {@code PreparedPdfDocument} is the representation of the object that is returned from
 * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}.
 * The {@code preparePdfSignaturePage} method is used to set up a PDF document along with its visible signature
 * requirements ({@link VisiblePdfSignatureRequirement}) before
 * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} is
 * called.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PreparedPdfDocument implements Extensible {

  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The policy under which the data held in this class may be used. */
  private String policy;

  /**
   * If the PDF document passed to
   * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}
   * was updated with a PDF signature page this property holds the updated PDf document (in its Base64 encoded form).
   */
  private String updatedPdfDocument;

  /**
   * If {@link PdfSignaturePagePreferences#getReturnDocumentReference()} is {@code true} the updated document will be
   * returned as a reference instead of via a {@link #getUpdatedPdfDocument()} call.
   */
  private String updatedPdfDocumentReference;

  /**
   * The resulting {@link VisiblePdfSignatureRequirement} object that should be passed as a property in the
   * {@link TbsDocument} holding the PDF document that is passed to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)}.
   */
  private VisiblePdfSignatureRequirement visiblePdfSignatureRequirement;

  /**
   * List of fixed issues in the PDF document contained in updatedPdfDocument or referenced by
   * updatedPdfDocumentReference.
   */
  private List<PdfDocumentIssue> fixedIssues;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Gets the policy under which the data held in this class may be used. This is always the same as the policy given in
   * the call to
   * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[],
   * PdfSignaturePagePreferences)}.
   *
   * @return the policy
   */
  public String getPolicy() {
    return this.policy;
  }

  /**
   * Assigns the policy under which the data held in this class may be used.
   *
   * @param policy the policy
   */
  public void setPolicy(final String policy) {
    this.policy = policy;
  }

  /**
   * If the PDF document passed to
   * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}
   * was updated with a PDF signature page this property holds the updated PDf document (in its Base64 encoded form).
   * <p>
   * If the property is {@code null} it means that the PDF document was not modified by
   * {@code preparePdfSignaturePage}.
   * </p>
   *
   * @return the updated PDF document (in Base64 encoded form) or {@code null} if the initial PDF document was not
   *     updated or if document references are used
   */
  public String getUpdatedPdfDocument() {
    return this.updatedPdfDocument;
  }

  /**
   * If the PDF document passed to
   * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}
   * was updated with a PDF signature page this property holds the updated PDf document (in its Base64 encoded form).
   * <p>
   * If the property is {@code null} it means that the PDF document was not modified by
   * {@code preparePdfSignaturePage}.
   * </p>
   * <p>
   * <b>Note:</b> This property is only assigned if {@link PdfSignaturePagePreferences#getReturnDocumentReference()} is
   * {@code false}. If document references should be used the reference for the updated document is obtained by calling
   * {@link #getUpdatedPdfDocumentReference()}.
   * </p>
   *
   * @param updatedPdfDocument updated PDF document (in Base64 encoded form)
   */
  public void setUpdatedPdfDocument(final String updatedPdfDocument) {
    this.updatedPdfDocument = updatedPdfDocument;
  }

  /**
   * If {@link PdfSignaturePagePreferences#getReturnDocumentReference()} is {@code true} the updated document will be
   * returned as a reference instead of via a {@link #getUpdatedPdfDocument()} call. The reason for using document
   * references is that a potentially heavy document only has to be uploaded once. Later when including the document in
   * a call to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} the
   * reference is set in {@link TbsDocument#setContentReference(String)}.
   *
   * <p>
   * Note: If document references are used a reference is set in all cases (even if no update of the document was
   * necessary).
   * </p>
   *
   * @return reference to the updated document or {@code null}
   */
  public String getUpdatedPdfDocumentReference() {
    return this.updatedPdfDocumentReference;
  }

  /**
   * If {@link PdfSignaturePagePreferences#getReturnDocumentReference()} is {@code true} the updated document will be
   * returned as a reference instead of via a {@link #getUpdatedPdfDocument()} call. The reason for using document
   * references is that a potentially heavy document only has to be uploaded once. Later when including the document in
   * a call to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} the
   * reference is set in {@link TbsDocument#setContentReference(String)}.
   *
   * @param updatedPdfDocumentReference reference to the updated document
   */
  public void setUpdatedPdfDocumentReference(final String updatedPdfDocumentReference) {
    this.updatedPdfDocumentReference = updatedPdfDocumentReference;
  }

  /**
   * Gets the resulting {@link VisiblePdfSignatureRequirement} object that should be passed as a property in the
   * {@link TbsDocument} holding the PDF document that is passed to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)}.
   *
   * @return a VisiblePdfSignatureRequirement object to be used in a TbsDocument for the PDF document that is about to
   *     be signed with a signature image
   */
  public VisiblePdfSignatureRequirement getVisiblePdfSignatureRequirement() {
    return this.visiblePdfSignatureRequirement;
  }

  /**
   * Assigns the resulting {@link VisiblePdfSignatureRequirement} object that should be passed as a property in the
   * {@link TbsDocument} holding the PDF document that is passed to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)}.
   *
   * @param visiblePdfSignatureRequirement a VisiblePdfSignatureRequirement object to be used in a TbsDocument for
   *     the PDF document that is about to be signed with a signature image
   */
  public void setVisiblePdfSignatureRequirement(
      final VisiblePdfSignatureRequirement visiblePdfSignatureRequirement) {
    this.visiblePdfSignatureRequirement = visiblePdfSignatureRequirement;
  }

  /**
   * Gets the list of fixed issues in the PDF document contained in updatedPdfDocument or referenced by
   * updatedPdfDocumentReference.
   * <p>
   * This information informs about fixed issues that have changed the document to determine if these changes are OK or
   * whether the signing process should be rejected.
   * </p>
   *
   * @return a list of issues that were fixed in the prepared PDF document, or {@code null}
   */
  public List<PdfDocumentIssue> getFixedIssues() {
    return this.fixedIssues;
  }

  /**
   * Assigns the list of fixed issues in the PDF document contained in updatedPdfDocument or referenced by
   * updatedPdfDocumentReference.
   * <p>
   * This information informs about fixed issues that have changed the document to determine if these changes are OK or
   * whether the signing process should be rejected.
   * </p>
   *
   * @param fixedIssues list of issues that were fixed in the prepared PDF document
   */
  public void setFixedIssues(final List<PdfDocumentIssue> fixedIssues) {
    this.fixedIssues = fixedIssues;
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
   * Builder for {@code PreparedPdfDocument} objects.
   */
  public static class PreparedPdfDocumentBuilder implements ObjectBuilder<PreparedPdfDocument> {

    // Lombok
  }

}
