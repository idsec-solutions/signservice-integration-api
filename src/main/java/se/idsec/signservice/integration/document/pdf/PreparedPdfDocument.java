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
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.idsec.signservice.integration.ExtendedSignServiceIntegrationService;
import se.idsec.signservice.integration.SignServiceIntegrationService;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.TbsDocument;

import java.io.Serial;

/**
 * The {@code PreparedPdfDocument} is the representation of the object that is returned from
 * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
 * Boolean)}.
 * <p>
 * The {@code preparePdfSignaturePage} method is used to set up a PDF document along with its visible signature
 * requirements ({@link VisiblePdfSignatureRequirement}) before
 * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} is
 * called.
 * </p>
 * <p>
 * The {@code preparePdfSignaturePage} method may also be called without sign pages in order to validate and potentially
 * fix the document before it is signed.
 * </p>
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
  private static final long serialVersionUID = 1691338993275336335L;

  /** The policy under which the data held in this class may be used. */
  private String policy;

  /**
   * If the PDF document passed to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)} was updated this property holds the updated PDf document (in its Base64 encoded form).
   */
  private String updatedPdfDocument;

  /**
   * If the service is running in stateful mode and the call to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)} was made with the {@code returnDocumentReference} parameter set, this field will hold the reference to
   * the PDF document stored by the service.
   */
  private String pdfDocumentReference;

  /**
   * The resulting {@link VisiblePdfSignatureRequirement} object that should be passed as a property in the
   * {@link TbsDocument} holding the PDF document that is passed to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)}.
   */
  private VisiblePdfSignatureRequirement visiblePdfSignatureRequirement;

  /** Report of actions and warnings. */
  private PdfPrepareReport prepareReport;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Gets the policy under which the data held in this class may be used. This is always the same as the policy given in
   * the call to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)}.
   *
   * @return the policy
   */
  @Nonnull
  public String getPolicy() {
    return this.policy;
  }

  /**
   * Assigns the policy under which the data held in this class may be used.
   *
   * @param policy the policy
   */
  public void setPolicy(@Nonnull final String policy) {
    this.policy = policy;
  }

  /**
   * If the PDF document passed to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)} was updated this property holds the updated PDf document (in its Base64 encoded form).
   * <p>
   * If the property is {@code null} it means that the PDF document was not modified by the {@code preparePdfDocument}
   * method.
   * </p>
   *
   * @return the updated PDF document (in Base64 encoded form) or {@code null} if the initial PDF document was not
   *     updated or if document references are used
   */
  @Nullable
  public String getUpdatedPdfDocument() {
    return this.updatedPdfDocument;
  }

  /**
   * If the PDF document passed to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)} was updated this property holds the updated PDf document (in its Base64 encoded form).
   * <p>
   * If the property is {@code null} it means that the PDF document was not modified by the {@code preparePdfDocument}
   * method.
   * </p>
   * <p>
   * <b>Note:</b> This property should only assigned if the service is running in stateful mode and the call to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)} was made with the {@code returnDocumentReference} parameter set.
   * </p>
   *
   * @param updatedPdfDocument updated PDF document (in Base64 encoded form)
   */
  public void setUpdatedPdfDocument(@Nonnull final String updatedPdfDocument) {
    this.updatedPdfDocument = updatedPdfDocument;
  }

  /**
   * If the service is running in stateful mode and the call to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)} was made with the {@code returnDocumentReference} parameter set, this field will hold the reference to
   * the PDF document stored by the service.
   *
   * <p>
   * Note: If document references are used a reference is set in all cases (even if no update of the document was
   * necessary).
   * </p>
   *
   * @return reference to the updated document or {@code null}
   */
  @Nullable
  public String getPdfDocumentReference() {
    return this.pdfDocumentReference;
  }

  @Deprecated
  @Nullable
  public String getUpdatedPdfDocumentReference() {
    return this.getPdfDocumentReference();
  }

  /**
   * If the service is running in stateful mode and the call to
   * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
   * Boolean)} was made with the {@code returnDocumentReference} parameter set, this field will hold the reference to
   * the PDF document stored by the service.
   * <p>
   * The reason for using document references is that a potentially heavy document only has to be uploaded once. Later
   * when including the document in a call to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} the
   * reference is set in {@link TbsDocument#setContentReference(String)}.
   * </p>
   *
   * @param pdfDocumentReference reference to the updated document
   */
  public void setPdfDocumentReference(@Nonnull final String pdfDocumentReference) {
    this.pdfDocumentReference = pdfDocumentReference;
  }

  @Deprecated(forRemoval = true)
  public void setUpdatedPdfDocumentReference(@Nonnull final String pdfDocumentReference) {
    this.setPdfDocumentReference(pdfDocumentReference);
  }

  /**
   * Gets the resulting {@link VisiblePdfSignatureRequirement} object that should be passed as a property in the
   * {@link TbsDocument} holding the PDF document that is passed to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)}.
   *
   * @return a VisiblePdfSignatureRequirement object to be used in a TbsDocument for the PDF document that is about to
   *     be signed with a signature image, or {@code null} if no sign image is being used
   */
  @Nullable
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
      @Nonnull final VisiblePdfSignatureRequirement visiblePdfSignatureRequirement) {
    this.visiblePdfSignatureRequirement = visiblePdfSignatureRequirement;
  }

  /**
   * Gets the actions and warnings for the operation.
   *
   * @return the actions and warnings for the operation, or {@code null} if nothing needs to be reported
   */
  @Nullable
  public PdfPrepareReport getPrepareReport() {
    return this.prepareReport;
  }

  /**
   * Assigns the actions and warnings for the operation.
   *
   * @param prepareReport the actions and warnings for the operation
   */
  public void setPrepareReport(@Nonnull final PdfPrepareReport prepareReport) {
    this.prepareReport = prepareReport;
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public Extension getExtension() {
    return this.extension;
  }

  /** {@inheritDoc} */
  @Override
  public void setExtension(@Nonnull final Extension extension) {
    this.extension = extension;
  }

  /**
   * Builder for {@code PreparedPdfDocument} objects.
   */
  public static class PreparedPdfDocumentBuilder implements ObjectBuilder<PreparedPdfDocument> {
    // Lombok
  }

}
