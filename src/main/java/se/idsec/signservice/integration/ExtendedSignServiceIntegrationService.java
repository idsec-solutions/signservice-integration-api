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
package se.idsec.signservice.integration;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import se.idsec.signservice.integration.core.error.InputValidationException;
import se.idsec.signservice.integration.core.error.SignServiceIntegrationException;
import se.idsec.signservice.integration.document.TbsDocument;
import se.idsec.signservice.integration.document.pdf.PdfAConsistencyCheckException;
import se.idsec.signservice.integration.document.pdf.PdfContainsAcroformException;
import se.idsec.signservice.integration.document.pdf.PdfContainsEncryptionDictionaryException;
import se.idsec.signservice.integration.document.pdf.PdfPrepareSettings;
import se.idsec.signservice.integration.document.pdf.PdfSignaturePageFullException;
import se.idsec.signservice.integration.document.pdf.PdfSignaturePagePreferences;
import se.idsec.signservice.integration.document.pdf.PreparedPdfDocument;
import se.idsec.signservice.integration.document.pdf.VisiblePdfSignatureRequirement;

/**
 * An extension to the interface describing the API for the SignService Integration Service.
 *
 * <p>
 * This extension is optional to implement, but its purpose is to supply the user of the API with utility methods that
 * are useful during signature processing.
 * </p>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface ExtendedSignServiceIntegrationService extends SignServiceIntegrationService {

  /**
   * A utility method that can be used to prepare a PDF document for signing.
   * <p>
   * The main purpose is to prepare a document that is to be signed with a PDF signature page holding the signature
   * image(s), but the method may also be used to ensure that the document is correct with respect to its format, and
   * also have the document "fixed" (see below).
   * </p>
   * <b>Signature pages and visible signatures</b>
   * <p>
   * A PDF signature image can be inserted into a signed PDF document to make the signature information "visible". The
   * image along with the coordinates that tell where in the PDF document the image should be inserted is represented
   * using a {@link VisiblePdfSignatureRequirement} object. See
   * {@link TbsDocument#getVisiblePdfSignatureRequirement()}.
   * </p>
   * <p>
   * However, for the generic case, where we may not always know how the PDF document that we are signing looks like it
   * may be tricky to determine where in the document the PDF signature image should be inserted. For those cases, a
   * dedicated "PDF signature page" may be added to the PDF document before it is added to a {@link TbsDocument} object
   * and sent as input in a signature operation. By using a PDF signature page whose structure and constitution is well
   * known it is easy to include a PDF signature image into any PDF document being signed.
   * </p>
   * <p>
   * Also, since a PDF document can be signed several times, using a PDF signature page it is easy to include signature
   * images for all signatures of the document.
   * </p>
   * <p>
   * The {@code preparePdfSignaturePage} method should invoked before each call to
   * {@link SignServiceIntegrationService#createSignRequest(SignRequestInput)} where PDF documents with PDF signature
   * pages is to be used. If a PDF document is to be signed is signed more than once and the PDF signature page supports
   * several signature images, no new PDF signature page is added. Instead, the {@code preparePdfSignaturePage} method
   * calculates where in the already existing PDF signature page the next signature image will be inserted.
   * </p>
   * <p>
   * If the policy states that PDF/A constistence checks should be done (see
   * {@link PdfPrepareSettings#isEnforcePdfaConsistency()}, the method will enforce that sign pages are PDF/A if the
   * document being signed is PDF/A.
   * </p>
   * <b>Fixing PDF Issues</b>
   * <p>
   * Depending on the settings of the profile configuration's {@link PdfPrepareSettings} the method may adjust the
   * document being signed by:
   * </p>
   * <ul>
   *   <li>Flattening any present Acroforms from the document.</li>
   *   <li>Removing the encryption dictionary from the document, if present.</li>
   * </ul>
   * <b>A note about the returnDocumentReference parameter</b>
   * <p>
   * If the backend service is running in "stateful" mode, meaning that it maintains a state of ongoing operations
   * in the service itself, the processed (prepared) document may be saved in the state, and later be referenced
   * in a call to {@code createSignRequest}. This way a potentially large document only has to be "uploaded" once.
   * </p>
   * <p>
   * By setting the {@code returnDocumentReference} parameter to {@code true} the method will not return the
   * (potentially updated) document, but instead a reference to it (see {@link PreparedPdfDocument#getPdfDocumentReference()}).
   * </p>
   * <p>
   * In "stateful" more the default value for the {@code returnDocumentReference} parameter is {@code true}. In stateless
   * mode, the value of the parameter is ignored.
   * </p>
   *
   * @param policy the policy under which the operation is performed (see {@link SignRequestInput#getPolicy()}). If
   *     {@code null}, the default policy is used.
   * @param pdfDocument the contents of the PDF document that is to be prepared
   * @param signaturePagePreferences the PDF signature page preferences (if {@code null}, no sign page is used)
   * @param returnDocumentReference whether to use document references (see above)
   * @return a PreparedPdfDocument object containing the modified PDF document (if a sign page was added) and the
   *     VisiblePdfSignatureRequirement telling how a signature image should be added
   * @throws InputValidationException for input validation errors
   * @throws PdfSignaturePageFullException if the PDF document contains more signatures than there is room for in
   *     the PDF signature page (and {@link PdfSignaturePagePreferences#isFailWhenSignPageFull()} evaluates to true)
   * @throws PdfAConsistencyCheckException if the policy is configured to enforce PDF/A consistency, and a sign page
   *     that is not PDF/A is attempted to be added to a PDF/A document
   * @throws PdfContainsAcroformException the PDF document contains an Acroform (and policy is not configured to
   *     flatten such forms)
   * @throws PdfContainsEncryptionDictionaryException the PDF document contains an encryption dictionary (and policy
   *     is not configured to remove these)
   * @throws SignServiceIntegrationException for other processing errors
   */
  PreparedPdfDocument preparePdfDocument(@Nullable final String policy, @Nonnull final byte[] pdfDocument,
      @Nullable final PdfSignaturePagePreferences signaturePagePreferences,
      @Nullable final Boolean returnDocumentReference)
      throws InputValidationException, PdfSignaturePageFullException, PdfAConsistencyCheckException,
      PdfContainsAcroformException, PdfContainsEncryptionDictionaryException, SignServiceIntegrationException;

}
