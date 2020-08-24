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
 * Class that represents the input to
 * {@link ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[], PdfSignaturePagePreferences)}
 * when implemented as a REST endpoint.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PreparePdfSignaturePageInput implements Extensible {

  /**
   * The contents of the PDF document that is to be prepared in Base64 encoded format.
   * 
   * @param pdfDocument
   *          the PDF document (in Base64 encoded format)
   * @return the PDF document (in Base64 encoded format)
   */
  @Getter
  @Setter
  private String pdfDocument;

  /**
   * The preferences of how to prepare the PDF document for a PDF signature page and signature image.
   * 
   * @param signaturePagePreferences
   *          preferences of how to prepare the PDF document for a PDF signature page and signature image
   * @return preferences of how to prepare the PDF document for a PDF signature page and signature image
   */
  @Getter
  @Setter
  private PdfSignaturePagePreferences signaturePagePreferences;

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
   * Builder for {@link PreparePdfSignaturePageInput}.
   */
  public static class PreparePdfSignaturePageInputBuilder implements ObjectBuilder<PreparePdfSignaturePageInput> {
    // Lombok
  }

}
