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

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Representation of a image template that is used for visible PDF signatures.
 * <p>
 * An image template may have fields in which the SignService Integration Service use to personalize the image. For
 * example the signer name, reason for the signature, date, etc.
 * </p>
 * <p>
 * Two fields have special meaning with pre-defined field names. They are:
 * </p>
 * <ul>
 * <li><b>signerName</b> - Displays the signer name information in the visible PDF signature. The caller does not
 * specify the name directly in the request. Instead the user attribute names that will hold these values after user
 * authentication is given. See
 * {@link VisiblePdfSignatureRequirement#setSignerName(se.idsec.signservice.integration.document.pdf.VisiblePdfSignatureRequirement.SignerName)}.</li>
 * <li><b>signingTime</b> - Includes a time stamp telling when the document was signed in the PDF. The caller does not
 * provide this information. Instead the SignService Integration Service includes this information if the
 * {@link #isIncludeSigningTime()} is set.</li>
 * </ul>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 * @see VisiblePdfSignatureRequirement
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfSignatureImageTemplate implements Extensible {

  /** Constant for the name of the special purpose field "signerName". */
  public static final String SIGNER_NAME_FIELD_NAME = "signerName";

  /** Constant for the name of the special purpose field "signingTime". */
  public static final String SIGNING_TIME_FIELD_NAME = "signingTime";

  /**
   * The unique reference for this image template.
   * 
   * @param reference
   *          unique reference
   * @return the unique reference
   */
  @Getter
  @Setter
  private String reference;

  /**
   * SVG image that is the image template.
   * 
   * @param image
   *          the SVG image expressed as an XML string
   * @return the SVG image expressed as an XML string
   */
  @Getter
  @Setter
  private String image;

  /**
   * The width (in pixels) for the pixel image that is generated from the template (and inserted into the PDF visible signature
   * flow).
   * 
   * @param width
   *          the width (in pixels)
   * @return the width (in pixels)
   */
  @Getter
  @Setter
  private Integer width;

  /**
   * The height (in pixels) for the pixel image that is generated from the template (and inserted into the PDF visible signature
   * flow).
   * 
   * @param height
   *          the width (in pixels)
   * @return the height (in pixels)
   */
  @Getter
  @Setter
  private Integer height;

  /**
   * Flag telling whether the signer name will be included in the visible PDF signature.
   * 
   * @param includeSignerName
   *          flag telling whether the signer name should be included
   * @return tells whether the signer name is included
   */
  @Getter
  @Setter
  private boolean includeSignerName;

  /**
   * Flag telling whether the signing time will be included in the visible PDF signature.
   * 
   * @param includeSigningTime
   *          flag telling whether the signing time should be included
   * @return tells whether the signing time will be included
   */
  @Getter
  @Setter
  private boolean includeSigningTime;

  /**
   * A map of the field names that are required by the template in the fieldName map in {@link VisiblePdfSignatureRequirement}.
   * 
   * @param fields the field names and associated descriptions
   * @return the field names and associated descriptions
   */
  @Getter
  @Setter
  @Singular
  private Map<String, String> fields;
  
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
   * Builder for {@code PdfSignatureImageTemplate} objects.
   */
  public static class PdfSignatureImageTemplateBuilder implements ObjectBuilder<PdfSignatureImageTemplate> {
    // Lombok
  }

}
