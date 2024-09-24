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
import se.idsec.signservice.integration.ExtendedSignServiceIntegrationService;
import se.idsec.signservice.integration.SignServiceIntegrationService;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.TbsDocument;

import java.io.Serial;

/**
 * Representation of preferences for adding or modifying a {@link PdfSignaturePage}. See
 * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}.
 *
 * <p>
 * Note: If
 * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)} is
 * called several times for the same document, i.e., when the document is signed more than once, the values for
 * {@code signaturePageReference}/{@code signaturePage} and {@code insertPageAt} must be equal to those values used in
 * the first invocation.
 * </p>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PdfSignaturePagePreferences implements Extensible {

  @Serial
  private static final long serialVersionUID = -2804060250278418877L;

  /** Reference to a PDF signature page to be inserted. */
  private String signaturePageReference;

  /**
   * As an alternative to specifying a {@code signPageReference} (see {@link #setSignaturePageReference(String)}) the
   * actual sign page can be provided in the preferences.
   */
  private PdfSignaturePage signaturePage;

  /**
   * The input regarding the user information that is to be used when generating a
   * {@link VisiblePdfSignatureRequirement} object.
   */
  private VisiblePdfSignatureUserInformation visiblePdfSignatureUserInformation;

  /**
   * A {@link PdfSignaturePage} has a limit on how many PDF signature images it can hold. If another image is added we
   * can either fail or ignore this.
   */
  @Builder.Default
  private boolean failWhenSignPageFull = true;

  /**
   * Tells where in a PDF document the PDF signature page should be inserted. A value of 1 represents the first page and
   * a value of 0 (or {@code null}) represents the last page. The last page is the default.
   */
  @Builder.Default
  private Integer insertPageAt = 0;

  /**
   * In the cases where a PDF document already has been signed and a signature page has been inserted, and another
   * signature image is to be added to this page, the caller may use the {@code existingSignaturePageNumber} property to
   * inform the Sign Service support service about the document page number of the sign page. This page number
   * corresponds to the {@link VisiblePdfSignatureRequirement#getPage()} property from the initial call.
   */
  private Integer existingSignaturePageNumber;

  /**
   * A setting that tells that a document reference ({@link PreparedPdfDocument#getUpdatedPdfDocumentReference()})
   * should be returned instead of the updated document itself. Later when the document is passed in a call to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} the
   * {@link TbsDocument#setContentReference(String)} is used instead of adding the entire document. This way a
   * potentially large document only has to be "uploaded" once.
   */
  private Boolean returnDocumentReference;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Gets the reference to a PDF signature page to be inserted (see {@link PdfSignaturePage#getId()}). This signature
   * page must be configured within the current policy. As an alternative to giving the reference an entire
   * {@link PdfSignaturePage} (see {@link #setSignaturePage(PdfSignaturePage)}).
   *
   * @return a reference of the PDF signature page to be inserted
   */
  public String getSignaturePageReference() {
    return this.signaturePageReference;
  }

  /**
   * Assigns a reference to a PDF signature page to be inserted (see {@link PdfSignaturePage#getId()}). This signature
   * page must be configured within the current policy. As an alternative to giving the reference an entire
   * {@link PdfSignaturePage} (see {@link #setSignaturePage(PdfSignaturePage)}).
   *
   * <p>
   * It is an error to specify both {@code signaturePageReference} and {@code signaturePage}. If neither
   * {@code signaturePageReference} or {@code signaturePage} is given, the default signature page for the policy will be
   * used, and if no signature page has been configured for the policy an error is reported.
   * </p>
   *
   * @param signaturePageReference the reference of the PDF signature page to be inserted
   */
  public void setSignaturePageReference(final String signaturePageReference) {
    this.signaturePageReference = signaturePageReference;
  }

  /**
   * Gets the signature page.
   * <p>
   * As an alternative to specifying a {@code signPageReference} (see {@link #setSignaturePageReference(String)}) the
   * actual sign page can be provided in the preferences.
   * </p>
   *
   * @return the PDF signature page to add, or {@code null}
   */
  public PdfSignaturePage getSignaturePage() {
    return this.signaturePage;
  }

  /**
   * As an alternative to specifying a {@code signPageReference} (see {@link #setSignaturePageReference(String)}) the
   * actual sign page can be provided in the preferences. This method adds this page.
   *
   * <p>
   * It is an error to specify both {@code signaturePageReference} and {@code signaturePage}. If neither
   * {@code signaturePageReference} or {@code signaturePage} is given, the default signature page for the policy will be
   * used, and if no signature page has been configured for the policy an error is reported.
   * </p>
   *
   * @param signaturePage the PDF signature page to add
   */
  public void setSignaturePage(final PdfSignaturePage signaturePage) {
    this.signaturePage = signaturePage;
  }

  /**
   * Gets the input regarding the user information that is to be used when generating a
   * {@link VisiblePdfSignatureRequirement} object.
   *
   * @return user information input for creating a VisiblePdfSignatureRequirement object
   */
  public VisiblePdfSignatureUserInformation getVisiblePdfSignatureUserInformation() {
    return this.visiblePdfSignatureUserInformation;
  }

  /**
   * Assigns the input regarding the user information that is to be used when generating a
   * {@link VisiblePdfSignatureRequirement} object. Using the object's values (signer name and other field values) and
   * combining with information regarding the PDF signature image found in {@link #getSignaturePageReference()} or
   * {@link #getSignaturePage()} a complete {@link VisiblePdfSignatureRequirement} object can be created.
   *
   * @param visiblePdfSignatureUserInformation user information input for creating a VisiblePdfSignatureRequirement
   *     object
   */
  public void setVisiblePdfSignatureUserInformation(
      final VisiblePdfSignatureUserInformation visiblePdfSignatureUserInformation) {
    this.visiblePdfSignatureUserInformation = visiblePdfSignatureUserInformation;
  }

  /**
   * A {@link PdfSignaturePage} has a limit on how many PDF signature images it can hold (see
   * {@link PdfSignaturePage#getMaxSignatureImages()}). This method tells whether we should fail (or ignore) when
   * another image is added that does not fit.
   *
   * @return whether processing should fail or not when the PDF signature page does not have room for any more sign
   *     images
   */
  public boolean isFailWhenSignPageFull() {
    return this.failWhenSignPageFull;
  }

  /**
   * A {@link PdfSignaturePage} has a limit on how many PDF signature images it can hold (see
   * {@link PdfSignaturePage#getMaxSignatureImages()}). If
   * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}
   * is invoked with a PDF document that contains a number of signature that equals or exceeds the maximum number of
   * allowed signature images ({@link PdfSignaturePage#getMaxSignatureImages()}) for the current PDF signature page the
   * {@code failWhenSignPageFull} property tells whether {@code preparePdfSignaturePage} should fail
   * ({@link PdfSignaturePageFullException}) or whether it should allow proceeding with the signature operation where no
   * PDF signature image is inserted (in that case the resulting {@link PreparedPdfDocument}) will contain a "null"
   * {@link VisiblePdfSignatureRequirement} (see
   * {@link VisiblePdfSignatureRequirement#createNullVisiblePdfSignatureRequirement()}).
   *
   * @param failWhenSignPageFull whether processing should fail or not when the PDF signature page does not have
   *     room for any more sign images (the default is true)
   */
  public void setFailWhenSignPageFull(final boolean failWhenSignPageFull) {
    this.failWhenSignPageFull = failWhenSignPageFull;
  }

  /**
   * Tells where in a PDF document the PDF signature page should be inserted. A value of 1 represents the first page and
   * a value of 0 (or {@code null}) represents the last page. The last page is the default.
   *
   * @return the page number in a PDF document where the PDF signature page should be inserted
   */
  public Integer getInsertPageAt() {
    return this.insertPageAt;
  }

  /**
   * Tells where in a PDF document the PDF signature page should be inserted. A value of 1 represents the first page and
   * a value of 0 (or {@code null}) represents the last page. The last page is the default.
   * <p>
   * Note: If more than one signature image is added to the sign page and {@code existingSignaturePageNumber} is not
   * set, the value of {@code insertPageAt} MUST be the same between all calls. Thus, the value refers to the page
   * number in the original document, before the sign page was added.
   * </p>
   *
   * @param insertPageAt the page number in a PDF document where the PDF signature page should be inserted
   */
  public void setInsertPageAt(final Integer insertPageAt) {
    this.insertPageAt = insertPageAt;
  }

  /**
   * In the cases where a PDF document already has been signed and a signature page has been inserted, and another
   * signature image is to be added to this page, the caller may use the {@code existingSignaturePageNumber} property to
   * inform the Sign Service support service about the document page number of the sign page. This page number
   * corresponds to the {@link VisiblePdfSignatureRequirement#getPage()} property from the initial call.
   *
   * @return the existing page number
   */
  public Integer getExistingSignaturePageNumber() {
    return this.existingSignaturePageNumber;
  }

  /**
   * In the cases where a PDF document already has been signed and a signature page has been inserted, and another
   * signature image is to be added to this page, the caller may use the {@code existingSignaturePageNumber} property to
   * inform the Sign Service support service about the document page number of the sign page. This page number
   * corresponds to the {@link VisiblePdfSignatureRequirement#getPage()} property from the initial call.
   * <p>
   * If not set, the Sign Service support service will have to calculate the page number based on the
   * {@code insertPageAt} property and {@code signaturePageReference}/{@code signaturePage}.
   * </p>
   *
   * @param existingSignaturePageNumber the existing page number
   */
  public void setExistingSignaturePageNumber(final Integer existingSignaturePageNumber) {
    this.existingSignaturePageNumber = existingSignaturePageNumber;
  }

  /**
   * Gets the setting that tells that a document reference
   * ({@link PreparedPdfDocument#getUpdatedPdfDocumentReference()}) should be returned instead of the updated document
   * itself. Later when the document is passed in a call to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} the
   * {@link TbsDocument#setContentReference(String)} is used instead of adding the entire document. This way a
   * potentially large document only has to be "uploaded" once.
   * <p>
   * A document reference is only returned in the cases when the current SignService Integration policy profile is
   * running in "stateful" mode. It is an error to request a document reference if the policy is stateless.
   * </p>
   *
   * @return whether document references instead of a completely updated document should be returned
   */
  public Boolean getReturnDocumentReference() {
    return this.returnDocumentReference;
  }

  /**
   * Assigns the setting that tells that a document reference
   * ({@link PreparedPdfDocument#getUpdatedPdfDocumentReference()}) should be returned instead of the updated document
   * itself. Later when the document is passed in a call to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} the
   * {@link TbsDocument#setContentReference(String)} is used instead of adding the entire document. This way a
   * potentially large document only has to be "uploaded" once.
   *
   * <p>
   * A document reference is only returned in the cases when the current SignService Integration policy profile is
   * running in "stateful" mode. It is an error to request a document reference if the policy is stateless.
   * </p>
   * <p>
   * The default behaviour is that {@code returnDocumentReference} is {@code true} if the current policy is stateful and
   * {@code false} if it is stateless.
   * </p>
   *
   * @param returnDocumentReference whether document references should be returned
   */
  public void setReturnDocumentReference(final Boolean returnDocumentReference) {
    this.returnDocumentReference = returnDocumentReference;
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
   * Builder for {@code PdfSignaturePagePreferences} objects.
   */
  public static class PdfSignaturePagePreferencesBuilder implements ObjectBuilder<PdfSignaturePagePreferences> {
    @SuppressWarnings("unused")
    private boolean failWhenSignPageFull = true;

    // Lombok
  }

}
