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

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.idsec.signservice.integration.config.IntegrationServiceDefaultConfiguration;
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
@NoArgsConstructor
@AllArgsConstructor
public class VisiblePdfSignatureRequirement extends VisiblePdfSignatureUserInformation {

  /** Constant for an extension that denotes a "null" visible PDF signature requirement. */
  public static final String NULL_INDICATOR_EXTENSION = "nullVisiblePdfSignatureRequirement";

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
   * Creates a "null" visible PDF signature requirement. This may be used in cases where the signature policy that is
   * being used has a default visible PDF signature requirement (see
   * {@link IntegrationServiceDefaultConfiguration#getDefaultVisiblePdfSignatureRequirement()}) but we, for some reason,
   * don't want to apply this.
   * 
   * @return a VisiblePdfSignatureRequirement instance with the extension value {@value #NULL_INDICATOR_EXTENSION} set
   *         to true
   */
  public static VisiblePdfSignatureRequirement createNullVisiblePdfSignatureRequirement() {
    VisiblePdfSignatureRequirement nullRequirement = new VisiblePdfSignatureRequirement();
    nullRequirement.addExtensionValue(NULL_INDICATOR_EXTENSION, "true");
    return nullRequirement;
  }

  @Builder
  public VisiblePdfSignatureRequirement(final SignerName signerName, final Map<String, String> fieldValues, 
      final Extension extension, final String templateImageRef, final Integer xPosition, final Integer yPosition, 
      final Integer scale, final Integer page) {
    super(signerName, fieldValues, extension);
    this.templateImageRef = templateImageRef;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.scale = scale;
    this.page = page;
  }

  /**
   * Builder for {@code VisiblePdfSignatureRequirement} objects.
   */
  public static class VisiblePdfSignatureRequirementBuilder implements ObjectBuilder<VisiblePdfSignatureRequirement> {
    // Lombok
    
    // Lombok Builder doesn't handle Singular annotations when inheriting ...
    @java.lang.SuppressWarnings("all")
    public VisiblePdfSignatureRequirementBuilder fieldValue(final String fieldValueKey, final String fieldValueValue) {
      if (this.fieldValues == null) {
        this.fieldValues = new HashMap<>();
      }
      this.fieldValues.put(fieldValueKey, fieldValueValue);
      return this;
    }
  }

}
