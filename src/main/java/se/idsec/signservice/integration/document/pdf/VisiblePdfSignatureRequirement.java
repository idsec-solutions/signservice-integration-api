/*
 * Copyright 2019 IDsec Solutions AB
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

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.AuthnRequirements;
import se.idsec.signservice.integration.authentication.SignerIdentityAttribute;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Class that represents the requirement for including a visible indication stating that a PDF document has been signed,
 * and by whom.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisiblePdfSignatureRequirement implements Extensible {

  /**
   * A unique reference of the template image that should be by the SignService Integration Service when generating a
   * PDF visible signature. See {@link PdfSignatureImageTemplate}.
   * 
   * @param templateImageRef
   *          the unique reference of the image template
   * @return the unique reference of the image template
   */
  @Getter
  @Setter
  private String templateImageRef;

  /**
   * Name of the signer to be represented in the visible image. This is typically a name of the signer but any suitable identity attribute
   * value may be specified to be part of the signer name. This value is analogous to, and should hold the same value as, a present Name
   * entry in the PDF signature dictionary. If the image template referenced requires a value for signerName, this field is mandatory,
   * otherwise it is optional.
   * 
   * @param signerName
   *          the signer name
   * @return the signer name
   */
  @Getter
  @Setter
  private SignerName signerName;

  /**
   * The X coordinate position (in pixels) of the PDF visible signature image in the PDF document.
   * 
   * @param xPosition
   *          the X coordinate position (in pixels)
   * @return the X coordinate position (in pixels)
   */
  @Getter
  @Setter
  private Integer xPosition;

  /**
   * The Y coordinate position (in pixels) of the PDF visible signature image in the PDF document.
   * 
   * @param yPosition
   *          the Y coordinate position (in pixels)
   * @return the Y coordinate position (in pixels)
   */
  @Getter
  @Setter
  private Integer yPosition;

  /**
   * The scale of the final visible signature image expressed as zoom percentage. The value -100 represents a 0 sized
   * image, the value 0 represents unaltered size, the value 100 double size and so on. If {@code null}, 0 is assumed.
   * 
   * @param scale
   *          the scale of the final visible signature image
   * @return the scale of the final visible signature image
   */
  @Getter
  @Setter
  private Integer scale;

  /**
   * The number of the page where the visible signature should appear. A value of 1 represents the first page and a
   * value of 0 (or {@code null}) represents the last page.
   * 
   * @param page
   *          the page where the visible signature should appear
   * @return the page where the visible signature should appear
   */
  @Getter
  @Setter
  private Integer page;

  /**
   * Apart from the signer name and signing date, a template may use other fields. This map provides the requested fields and values.
   * 
   * @param fieldValues
   *          a map of fields and their values
   * @return a map of fields and their values
   */
  @Getter
  @Setter
  @Singular
  private Map<String, String> fieldValues;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Assigns an extension object with extension parameters.
   * 
   * @param extension
   *          the extension object to assign
   */
  public void setExtension(Extension extension) {
    this.extension = extension;
  }

  /** {@inheritDoc} */
  @Override
  public Extension getExtension() {
    return this.extension;
  }

  /**
   * Builder for {@code VisiblePdfSignatureRequirement} objects.
   */
  public static class VisiblePdfSignatureRequirementBuilder implements ObjectBuilder<VisiblePdfSignatureRequirement> {
    // Lombok
  }

  /**
   * Class representing the input needed to display the signer name in a visible PDF signature.
   */
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignerName {

    /**
     * A list of attribute names that refer to some, or all, attributes supplied in
     * {@link AuthnRequirements#setRequestedSignerAttributes(List)} that are the requirements that the signer requires
     * to be validated as part of the signature operation. The values of the given attributes will be part of the signer
     * name field as they appear in the list (separated by a blank). It is possible to change how the information is
     * displayed by assigning a formatting, see {@link #setFormatting(String)}.
     * 
     * @param signerAttributes
     *          a list of attribute names whose values should be used to display the signer name in the visible PDF
     *          signature
     * @return a list of attribute names whose values should be used to display the signer name in the visible PDF
     *         signature
     */
    @Getter
    @Setter
    @Singular
    private List<SignerIdentityAttribute> signerAttributes;

    /**
     * Optional string the may be supplied to change how the signer's information is displayed. Each list item from
     * {@link #getSignerAttributes()} is referenced by its order (starting from 0) and prefixed by %. For example, the
     * formatting string {@code "%1 %2 (%3)"} may display something like {@code Jim Smith (ID12345)}.
     * 
     * @param formatting
     *          the formatting string
     * @return the formatting string
     */
    @Getter
    @Setter
    private String formatting;

    /**
     * Builder for {@code SignerName} objects.
     */
    public static class SignerNameBuilder implements ObjectBuilder<SignerName> {
      // Lombok
    }

  }
}
