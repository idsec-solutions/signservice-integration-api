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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.idsec.signservice.integration.ExtendedSignServiceIntegrationService;
import se.idsec.signservice.integration.SignServiceIntegrationService;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.TbsDocument;

/**
 * The {@code PreparedPdfDocument} is the representation of the object that is returned from
 * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}.
 * The {@code preparePdfSignaturePage} method is used to setup a PDF document along with its visible signature
 * requirements ({@link VisiblePdfSignatureRequirement}) before
 * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)} is called.
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

  /**
   * If the PDF document passed to
   * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}
   * was updated with a PDF signature page this property holds the updated PDf document (in its Base64 encoded form).
   * <p>
   * If the property is {@code null} it means that the PDF document was not modified by {@code preparePdfSignaturePage}.
   * </p>
   * 
   * @param updatedPdfDocument
   *          updated PDF document (in Base64 encoded form)
   * @return the updated PDF document (in Base64 encoded form) or null if the initial PDF document was not updated
   */
  @Setter
  @Getter
  private String updatedPdfDocument;

  /**
   * The resulting {@link VisiblePdfSignatureRequirement} object that should be passed as a property in the
   * {@link TbsDocument} holding the PDF document that is passed to
   * {@link SignServiceIntegrationService#createSignRequest(se.idsec.signservice.integration.SignRequestInput)}.
   * 
   * @param visiblePdfSignatureRequirement
   *          a VisiblePdfSignatureRequirement object to be used in a TbsDocument for the PDF document that is about to
   *          be signed with a signature image
   * @return a VisiblePdfSignatureRequirement object to be used in a TbsDocument for the PDF document that is about to
   *         be signed with a signature image
   */
  @Setter
  @Getter
  private VisiblePdfSignatureRequirement visiblePdfSignatureRequirement;

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
   * Builder for {@code PreparedPdfDocument} objects.
   */
  public static class PreparedPdfDocumentBuilder implements ObjectBuilder<PreparedPdfDocument> {

    // Lombok
  }

}
