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
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Representation of a "PDF signature page".
 * <p>
 * A signature page may be used when signing PDF documents together with "PDF visible signatures" (see
 * {@link PdfSignatureImageTemplate}). In those cases a customized "signature page" may be added to the PDF document
 * before it is signed. This page will then hold the PDF signature image, or images if the document is signed multiple
 * times.
 * </p>
 * <p>
 * The reason for using a PDF signature page is that we can sign any type of PDF document and still be sure that the PDF
 * signature image ends up in the correct place. If no PDF signature page is used we have to have prior knowledge about
 * the PDF document that is about to be signed so that we know where to place the PDF signature image.
 * </p>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfSignaturePage implements Extensible {

  /**
   * The unique ID for this PDF signature page.
   * 
   * @param id
   *          unique ID
   * @return the ID
   */
  @Getter
  @Setter
  private String id;

  /**
   * The contents of the PDF document that holds the PDF signature page.
   * <p>
   * See also {@link #getResource()} and {@link #setResource(String)}.
   * </p>
   * 
   * @param contents
   *          the byte contents of the PDF document in Base64 encoded format
   * @return the byte contents of the PDF document in Base64 encoded format
   */
  @Setter
  @Getter
  private String contents;

  /**
   * A resource string that points at the PDF document that holds the PDF signature page.
   * <p>
   * Note: The Spring Framework style of representing a resource must be supported by implementations. For example:
   * {@code classpath:xyz.pdf} and {@code file:///path/xyz.pdf}.
   * </p>
   * <p>
   * See also {@link #getContents()} and {@link #setContents(String)}.
   * </p>
   * 
   * @param resource
   *          the resource that holds the PDF signature page document
   * @return the resource that holds the PDF signature page document
   */
  @Setter
  @Getter
  private String resource;

  /**
   * Tells how many PDF signature images that may be displayed in the PDF signature page. The default is {@code 1}.
   * 
   * @param maxSignatureImages
   *          the maximum number of PDF signature images this page can contain
   * @return the maximum number of PDF signature images this page can contain
   */
  @Getter
  @Setter
  @Builder.Default
  private Integer maxSignatureImages = 1;

  /**
   * If it should be possible to add PDF sign images in several rows to this sign page document the {@code rows}
   * attribute should assigned to the desired number of rows. The default is {@code 1}.
   * 
   * @param rows
   *          the number of rows of PDF sign images this sign page supports
   * @return the number of rows of PDF sign images this sign page supports
   */
  @Getter
  @Setter
  @Builder.Default
  private Integer rows = 1;

  /**
   * If it should be possible to add PDF sign images in several columns to this sign page document the {@code columns}
   * attribute should assigned to the desired number of columns. The default is {@code 1}.
   * 
   * @param columns
   *          the number of columns of PDF sign images this sign page supports
   * @return the number of columns of PDF sign images this sign page supports
   */
  @Getter
  @Setter
  @Builder.Default
  private Integer columns = 1;

  /**
   * A unique reference of the signature template image that is inserted into this PDF signature page. See
   * {@link PdfSignatureImageTemplate}.
   * 
   * @param signatureImageReference
   *          the unique reference of the signature image template
   * @return the unique reference of the signature image template
   * @see PdfSignatureImageTemplate
   * @see VisiblePdfSignatureRequirement#getTemplateImageRef()
   */
  @Getter
  @Setter
  protected String signatureImageReference;

  /**
   * Configuration that tells where in the PDF signature page the PDF signature image(s) should be inserted.
   * 
   * @param imagePlacementConfiguration
   *          configuration for where in the PDF signature page the PDF signature image(s) should be inserted
   * @return configuration for where in the PDF signature page the PDF signature image(s) should be inserted
   */
  @Getter
  @Setter
  private PdfSignatureImagePlacementConfiguration imagePlacementConfiguration;

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
   * Builder for {@code PdfSignPagePreferences} objects.
   */
  public static class PdfSignaturePageBuilder implements ObjectBuilder<PdfSignaturePage> {
    private Integer maxSignatureImages = 1;
    private Integer rows = 1;
    private Integer columns = 1;

    // Lombok
  }

  /**
   * Representation of the configuration of where in a PDF signature page PDF signature images should be inserted.
   */
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PdfSignatureImagePlacementConfiguration implements Extensible {

    /**
     * The X coordinate position (in pixels) of where the first PDF visible signature image should be inserted on the
     * PDF signature page.
     * 
     * @param xPosition
     *          the initial X coordinate position (in pixels)
     * @return the initial X coordinate position (in pixels)
     */
    @Getter
    @Setter
    private Integer xPosition;

    /**
     * The Y coordinate position (in pixels) of where the first PDF visible signature image should be inserted on the
     * PDF signature page.
     * 
     * @param yPosition
     *          the initial Y coordinate position (in pixels)
     * @return the initial Y coordinate position (in pixels)
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
    @Builder.Default
    private Integer scale = 0;

    /**
     * If the PDF signature page supports more than one column (see {@link PdfSignaturePage#getColumns()}) the
     * {@code xIncrement} property tells how many pixels that should be added to the previously used {@code xPosition}
     * when inserting a PDF signature image in a new column.
     * <p>
     * Note: If the PDF signature page only supports one column this property is ignored.
     * </p>
     * 
     * @param xIncrement
     *          the number of pixels that should be added to the previously used xPosition when inserting a PDF
     *          signature image in a new column
     * @return the number of pixels that should be added to the previously used xPosition when inserting a PDF signature
     *         image in a new column
     */
    @Getter
    @Setter
    private Integer xIncrement;

    /**
     * If the PDF signature page supports more than one row (see {@link PdfSignaturePage#getRows()}) the
     * {@code yIncrement} property tells how many pixels that should be added to the previously used {@code yPosition}
     * when inserting a PDF signature image in a new row.
     * <p>
     * Note: If the PDF signature page only supports one row this property is ignored.
     * </p>
     * 
     * @param yIncrement
     *          the number of pixels that should be added to the previously used yPosition when inserting a PDF
     *          signature image in a new row
     * @return the number of pixels that should be added to the previously used yPosition when inserting a PDF signature
     *         image in a new row
     */
    @Getter
    @Setter
    private Integer yIncrement;

    /**
     * If the PDF signature page document (see {@link PdfSignaturePage#getContents()}) contains more than one page it is
     * possible to use the {@code page} attribute to tell where the PDF signature image(s) should be inserted. A value
     * of 1 represents the first page and a value of 0 represents the last page. The default is {@code 1}.
     * <p>
     * Note: It is only possible to have PDF signature images inserted into <b>one</b> page of the PDF signature page
     * document.
     * </p>
     * 
     * @param page
     *          the page number in the document where sign image(s) should be inserted
     * @return the page number in the document where sign image(s) should be inserted
     */
    @Getter
    @Setter
    @Builder.Default
    private Integer page = 1;

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
     * Builder for {@code PdfSignatureImagePlacementConfiguration} objects.
     */
    public static class PdfSignatureImagePlacementConfigurationBuilder implements ObjectBuilder<PdfSignatureImagePlacementConfiguration> {
      private Integer page = 1;
      private Integer scale = 0;

      // Lombok
    }
  }

}
