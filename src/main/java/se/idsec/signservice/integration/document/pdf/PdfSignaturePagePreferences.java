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
package se.idsec.signservice.integration.document.pdf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.idsec.signservice.integration.ExtendedSignServiceIntegrationService;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Representation of preferences for adding or modifying a {@link PdfSignaturePage}. See
 * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfSignaturePagePreferences implements Extensible {

  /**
   * Reference to a PDF signature page to be inserted (see {@link PdfSignaturePage#getId()}). This signature page must
   * be configured within the current policy. As an alternative to giving the reference an entire
   * {@link PdfSignaturePage} (see {@link #setSignaturePage(PdfSignaturePage)}).
   * 
   * <p>
   * It is an error to specify both {@code signaturePageReference} and {@code signaturePage}. If neither
   * {@code signaturePageReference} or {@code signaturePage} is given, the default signature page for the policy will be
   * used, and if no signature page has been configured for the policy an error is reported.
   * </p>
   * 
   * @param signaturePageReference
   *          the reference of the PDF signature page to be inserted
   * @return a reference of the PDF signature page to be inserted
   */
  @Getter
  @Setter
  private String signaturePageReference;

  /**
   * As an alternative to specifying a {@code signPageReference} (see {@link #setSignaturePageReference(String)}) the
   * actual sign page can be provided in the preferences.
   * 
   * <p>
   * It is an error to specify both {@code signaturePageReference} and {@code signaturePage}. If neither
   * {@code signaturePageReference} or {@code signaturePage} is given, the default signature page for the policy will be
   * used, and if no signature page has been configured for the policy an error is reported.
   * </p>
   * 
   * @param signaturePage
   *          the PDF signature page to add
   * @return the PDF signature page to add
   */
  @Getter
  @Setter
  private PdfSignaturePage signaturePage;

  /**
   * The input regarding the user information that is to be used when generating a
   * {@link VisiblePdfSignatureRequirement} object. Using the object's values (signer name and other field values) and
   * combining with information regarding the PDF signature image found in {@link #getSignaturePageReference()} or
   * {@link #getSignaturePage()} a complete {@link VisiblePdfSignatureRequirement} object can be created.
   * 
   * @param visiblePdfSignatureUserInformation
   *          user information input for creating a VisiblePdfSignatureRequirement object
   * @return user information input for creating a VisiblePdfSignatureRequirement object
   */
  @Getter
  @Setter
  private VisiblePdfSignatureUserInformation visiblePdfSignatureUserInformation;

  /**
   * A {@link PdfSignaturePage} has a limit on how many PDF signature images it can hold (see
   * {@link PdfSignaturePage#getMaxSignatureImages()}). If
   * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}
   * is invoked with a PDF document that contains a number of signature that equals or exceeds the maximum number of
   * allowed signature images ({@link PdfSignaturePage#getMaxSignatureImages()}) for the current PDF signature page the
   * {@code failWhenSignPageFull} property tells whether {@code preparePdfSignaturePage} should fail
   * ({@link PdfSignaturePageFullException}) or whether it should allow proceeding with the signature operation where no
   * PDF signature image is inserted (in that case the resulting {@link PreparedPdfDocument} will contain a "null"
   * {@link VisiblePdfSignatureRequirement} (see
   * {@link VisiblePdfSignatureRequirement#createNullVisiblePdfSignatureRequirement()}).
   * 
   * @param failWhenSignPageFull
   *          whether processing should fail or not when the PDF signature page does not have room for any more sign
   *          images (the default is true)
   * @return whether processing should fail or not when the PDF signature page does not have room for any more sign
   *         images
   */
  @Getter
  @Setter
  @Builder.Default
  private boolean failWhenSignPageFull = true;

  /**
   * Tells where in a PDF document the PDF signature page should be inserted. A value of 1 represents the first page and
   * a value of 0 (or {@code null}) represents the last page. The last page is the default.
   * 
   * @param insertPageAt
   *          the page number in a PDF document where the PDF signature page should be inserted
   * @return the page number in a PDF document where the PDF signature page should be inserted
   */
  @Getter
  @Setter
  @Builder.Default
  private Integer insertPageAt = 0;

  /** Extensions for the object. */
  private Extension extension;

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
    private boolean failWhenSignPageFull = true;

    // Lombok
  }

}
