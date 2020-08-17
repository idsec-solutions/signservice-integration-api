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
package se.idsec.signservice.integration;

import se.idsec.signservice.integration.core.error.InputValidationException;
import se.idsec.signservice.integration.core.error.SignServiceIntegrationException;
import se.idsec.signservice.integration.document.TbsDocument;
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
   * A utility method that can be used to prepare a PDF document that is to be signed with a PDF signature image with a
   * PDF signature page holding the signature image(s).
   * <p>
   * A PDF signature image can be inserted into a signed PDF document to make the signature information "visible". The
   * image along with the coordinates that tell where in the PDF document the images should be inserted is represented
   * using a {@link VisiblePdfSignatureRequirement} object. See {@link TbsDocument#getVisiblePdfSignatureRequirement()}.
   * </p>
   * <p>
   * However, for the generic case, where we may not always know how the PDF document we are signing looks like it may
   * be tricky to determine where in the document the PDF signature image should be inserted. For those cases, a
   * dedicated "PDF signature page"may be added to the PDF document before it is added to a {@link TbsDocument} object
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
   * pages is to be used. If a PDF document is to be signed more than once and the PDF signature page supports several
   * signature images, by default no new PDF signature page is added (see
   * {@link PdfSignaturePagePreferences#isAddToAlreadySigned()}. Instead, the {@code preparePdfSignaturePage} method
   * calculates where in the already existing PDF signature page the next signature image will be inserted.
   * </p>
   * 
   * @param policy
   *          the policy under which the operation is performed (see {@link SignRequestInput#getPolicy()})
   * @param pdfDocument
   *          the contents of the PDF document that is to be prepared
   * @param signaturePagePreferences
   *          the PDF signature page preferences
   * @return a PreparedPdfDocument object containing the modified PDF document (if a sign page was added) and the
   *         VisiblePdfSignatureRequirement telling how a signature image should be added
   * @throws InputValidationException
   *           for input validation errors
   * @throws SignServiceIntegrationException
   *           for processing errors
   */
  PreparedPdfDocument preparePdfSignaturePage(final String policy,
      final byte[] pdfDocument,
      final PdfSignaturePagePreferences signaturePagePreferences)
      throws InputValidationException, SignServiceIntegrationException;

}
