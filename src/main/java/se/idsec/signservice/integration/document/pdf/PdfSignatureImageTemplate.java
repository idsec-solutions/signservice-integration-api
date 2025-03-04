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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.FileResource;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.TimeZone;

/**
 * Representation of an image template that is used for visible PDF signatures.
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
 * {@link VisiblePdfSignatureUserInformation#setSignerName(se.idsec.signservice.integration.document.pdf.VisiblePdfSignatureUserInformation.SignerName)}.</li>
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
@JsonInclude(Include.NON_NULL)
public class PdfSignatureImageTemplate implements Extensible {

  @Serial
  private static final long serialVersionUID = 1237316790752152415L;

  /** Constant for the name of the special purpose field "signerName". */
  public static final String SIGNER_NAME_FIELD_NAME = "signerName";

  /** Constant for the name of the special purpose field "signingTime". */
  public static final String SIGNING_TIME_FIELD_NAME = "signingTime";

  /** The unique reference for this image template. */
  private String reference;

  /** SVG image file that holds up the image template. */
  private FileResource svgImageFile;

  /**
   * The width (in pixels) for the pixel image that is generated from the template (and inserted into the PDF visible
   * signature flow).
   */
  private Integer width;

  /**
   * The height (in pixels) for the pixel image that is generated from the template (and inserted into the PDF visible
   * signature flow).
   */
  private Integer height;

  /** Flag telling whether the signer name will be included in the visible PDF signature. */
  @Builder.Default
  private boolean includeSignerName = true;

  /**
   * Flag telling whether the signing time will be included in the visible PDF signature.
   */
  @Builder.Default
  private boolean includeSigningTime = false;

  private String timeZoneId;

  /**
   * A map of the field names that are required by the template in the fieldName map in
   * {@link VisiblePdfSignatureRequirement}.
   */
  @Singular
  private Map<String, String> fields;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Gets the unique reference for this image template.
   *
   * @return the unique reference
   */
  public String getReference() {
    return this.reference;
  }

  /**
   * Assigns the unique reference for this image template.
   *
   * @param reference the unique reference
   */
  public void setReference(final String reference) {
    this.reference = reference;
  }

  /**
   * Gets the SVG image expressed as an XML string.
   *
   * @return the SVG image expressed as an XML string
   */
  @JsonIgnore
  public String getImage() {
    if (this.svgImageFile != null) {
      final String svgFile = this.svgImageFile.getContents();
      if (svgFile != null) {
        return new String(Base64.getDecoder().decode(svgFile));
      }
    }
    return null;
  }

  /**
   * Assigns the SVG image.
   *
   * @param image the SVG image expressed as an XML string
   * @deprecated since 1.1.0. Use {@link #setSvgImageFile(FileResource)} instead
   */
  @JsonIgnore
  @Deprecated
  public void setImage(final String image) {
    if (image != null) {
      this.svgImageFile = FileResource.builder()
          .contents(Base64.getEncoder().encodeToString(image.getBytes(StandardCharsets.UTF_8)))
          .build();
    }
  }

  /**
   * Gets the file resource that holds the SVG image file for this template.
   * <p>
   * See also {@link #getImage()} that returns the contents as an XML string directly
   * </p>
   *
   * @return the SVG image file for this template
   */
  public FileResource getSvgImageFile() {
    return this.svgImageFile;
  }

  /**
   * Assigns the file resource that holds the SVG image file for this template.
   *
   * @param svgImageFile the SVG image file for this template
   */
  public void setSvgImageFile(final FileResource svgImageFile) {
    this.svgImageFile = svgImageFile;
  }

  /**
   * Gets the width (in pixels) for the pixel image that is generated from the template (and inserted into the PDF
   * visible signature flow).
   *
   * @return the width (in pixels)
   */
  public Integer getWidth() {
    return this.width;
  }

  /**
   * Assigns the width (in pixels) for the pixel image that is generated from the template (and inserted into the PDF
   * visible signature flow).
   *
   * @param width the width (in pixels)
   */
  public void setWidth(final Integer width) {
    this.width = width;
  }

  /**
   * Gets the height (in pixels) for the pixel image that is generated from the template (and inserted into the PDF
   * visible signature flow).
   *
   * @return the height (in pixels)
   */
  public Integer getHeight() {
    return this.height;
  }

  /**
   * Assigns the height (in pixels) for the pixel image that is generated from the template (and inserted into the PDF
   * visible signature flow).
   *
   * @param height the height (in pixels)
   */
  public void setHeight(final Integer height) {
    this.height = height;
  }

  /**
   * Gets the flag telling whether the signer name will be included in the visible PDF signature.
   *
   * @return tells whether the signer name is included
   */
  public boolean isIncludeSignerName() {
    return this.includeSignerName;
  }

  /**
   * Assigns the flag telling whether the signer name will be included in the visible PDF signature. The default is
   * {@code true}.
   *
   * @param includeSignerName flag telling whether the signer name should be included
   */
  public void setIncludeSignerName(final boolean includeSignerName) {
    this.includeSignerName = includeSignerName;
  }

  /**
   * Gets the flag telling whether the signing time will be included in the visible PDF signature.
   *
   * @return tells whether the signing time will be included
   */
  public boolean isIncludeSigningTime() {
    return this.includeSigningTime;
  }

  /**
   * Sets the flag telling whether the signing time will be included in the visible PDF signature.
   *
   * @param includeSigningTime flag telling whether the signing time should be included
   */
  public void setIncludeSigningTime(final boolean includeSigningTime) {
    this.includeSigningTime = includeSigningTime;
  }

  /**
   * Gets the map of the field names that are required by the template in the fieldName map in
   * {@link VisiblePdfSignatureRequirement}.
   *
   * @return the field names and associated descriptions
   */
  public Map<String, String> getFields() {
    return this.fields;
  }

  /**
   * Assigns the map of the field names that are required by the template in the fieldName map in
   * {@link VisiblePdfSignatureRequirement}.
   *
   * @param fields the field names and associated descriptions
   */
  public void setFields(final Map<String, String> fields) {
    this.fields = fields;
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
   * Gets the identifier for the time zone associated with this template.
   *
   * @return the time zone identifier
   */
  public String getTimeZoneId() {
    return timeZoneId;
  }

  /**
   * Assigns the time zone identifier for this template.
   *
   * @param timeZoneId the time zone identifier
   */
  public void setTimeZoneId(final String timeZoneId) {
    this.timeZoneId = timeZoneId;
  }

  /**
   * Builder for {@code PdfSignatureImageTemplate} objects.
   */
  public static class PdfSignatureImageTemplateBuilder implements ObjectBuilder<PdfSignatureImageTemplate> {
    @SuppressWarnings("unused")
    private boolean includeSignerName = true;
    @SuppressWarnings("unused")
    private boolean includeSigningTime = false;

    // Lombok
  }

}
