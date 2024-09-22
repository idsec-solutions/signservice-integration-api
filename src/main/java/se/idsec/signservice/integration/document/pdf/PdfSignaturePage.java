/*
 * Copyright 2019-2023 IDsec Solutions AB
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
import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.FileResource;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;
import java.util.Base64;

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
@JsonInclude(Include.NON_NULL)
public class PdfSignaturePage implements Extensible {

  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The unique ID for this PDF signature page. */
  private String id;

  /** The file resource containing the PDF document that holds the PDF signature page. */
  private FileResource pdfDocument;

  /**
   * If it should be possible to add PDF sign images in several rows to this sign page document the {@code rows}
   * attribute should be assigned to the desired number of rows. The default is {@code 1}.
   */
  @Builder.Default
  private Integer rows = 1;

  /**
   * If it should be possible to add PDF sign images in several columns to this sign page document the {@code columns}
   * attribute should be assigned to the desired number of columns. The default is {@code 1}.
   */
  @Builder.Default
  private Integer columns = 1;

  /** A unique reference of the signature template image that is inserted into this PDF signature page. */
  protected String signatureImageReference;

  /** Configuration that tells where in the PDF signature page the PDF signature image(s) should be inserted. */
  private PdfSignatureImagePlacementConfiguration imagePlacementConfiguration;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Gets the unique ID for this PDF signature page.
   *
   * @return the unique ID for this PDF signature page
   */
  public String getId() {
    return this.id;
  }

  /**
   * Assigns the unique ID for this PDF signature page
   *
   * @param id the unique ID for this PDF signature page
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * Gets the file resource containing the PDF document that holds the PDF signature page.
   *
   * @return the file resource containing the PDF document that holds the PDF signature page
   */
  public FileResource getPdfDocument() {
    return this.pdfDocument;
  }

  /**
   * Assigns the file resource containing the PDF document that holds the PDF signature page.
   *
   * @param pdfDocument the file resource containing the PDF document that holds the PDF signature page
   */
  public void setPdfDocument(final FileResource pdfDocument) {
    this.pdfDocument = pdfDocument;
  }

  /**
   * If it should be possible to add PDF sign images in several rows to this sign page document the {@code rows}
   * attribute should be assigned to the desired number of rows. The default is {@code 1}.
   *
   * @return the number of rows
   */
  public Integer getRows() {
    return this.rows;
  }

  /**
   * If it should be possible to add PDF sign images in several rows to this sign page document the {@code rows}
   * attribute should be assigned to the desired number of rows.
   *
   * @param rows the number of rows
   */
  public void setRows(final Integer rows) {
    this.rows = rows;
  }

  /**
   * If it should be possible to add PDF sign images in several columns to this sign page document the {@code columns}
   * attribute should be assigned to the desired number of columns. The default is {@code 1}.
   *
   * @return the number of columns
   */
  public Integer getColumns() {
    return this.columns;
  }

  /**
   * If it should be possible to add PDF sign images in several columns to this sign page document the {@code columns}
   * attribute should be assigned to the desired number of columns.
   *
   * @param columns the number of columns
   */
  public void setColumns(final Integer columns) {
    this.columns = columns;
  }

  /**
   * Gets the unique reference of the signature template image that is inserted into this PDF signature page.
   *
   * @return the unique reference of the signature template image
   * @see PdfSignatureImageTemplate
   * @see VisiblePdfSignatureRequirement#getTemplateImageRef()
   */
  public String getSignatureImageReference() {
    return this.signatureImageReference;
  }

  /**
   * Assigns the unique reference of the signature template image that is inserted into this PDF signature page.
   *
   * @param signatureImageReference the unique reference of the signature template image
   * @see PdfSignatureImageTemplate
   * @see VisiblePdfSignatureRequirement#getTemplateImageRef()
   */
  public void setSignatureImageReference(final String signatureImageReference) {
    this.signatureImageReference = signatureImageReference;
  }

  /**
   * Gets the configuration that tells where in the PDF signature page the PDF signature image(s) should be inserted.
   *
   * @return the configuration that tells where in the PDF signature page the PDF signature image(s) should be inserted
   */
  public PdfSignatureImagePlacementConfiguration getImagePlacementConfiguration() {
    return this.imagePlacementConfiguration;
  }

  /**
   * Assigns the configuration that tells where in the PDF signature page the PDF signature image(s) should be
   * inserted.
   *
   * @param imagePlacementConfiguration the configuration that tells where in the PDF signature page the PDF
   *     signature image(s) should be inserted.
   */
  public void setImagePlacementConfiguration(
      final PdfSignatureImagePlacementConfiguration imagePlacementConfiguration) {
    this.imagePlacementConfiguration = imagePlacementConfiguration;
  }

  /**
   * A utility method that can be used to get the raw bytes of the PDF document holding the PDF signature page.
   * <p>
   * See also {@link #getPdfDocument()}.
   * </p>
   *
   * @return the bytes of the PDF document, or null if no document is available
   */
  @JsonIgnore
  public byte[] getContents() {
    if (this.pdfDocument != null) {
      final String encodedContents = this.pdfDocument.getContents();
      if (encodedContents != null) {
        return Base64.getDecoder().decode(encodedContents);
      }
    }
    return null;
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
   * Tells how many PDF signature images that may be displayed in the PDF signature page. This is calculated as
   * {@link #getRows()} timws {@link #getColumns()}.
   *
   * @return the maximum number of PDF signature images this page can contain
   */
  public int getMaxSignatureImages() {
    return (this.rows != null ? this.rows : 1) * (this.columns != null ? this.columns : 1);
  }

  /**
   * Builder for {@code PdfSignPagePreferences} objects.
   */
  public static class PdfSignaturePageBuilder implements ObjectBuilder<PdfSignaturePage> {
    @SuppressWarnings("unused")
    private Integer rows = 1;
    @SuppressWarnings("unused")
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

    @Serial
    private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

    /**
     * The X coordinate position (in pixels) of where the first PDF visible signature image should be inserted on the
     * PDF signature page.
     */
    private Integer xPosition;

    /**
     * The Y coordinate position (in pixels) of where the first PDF visible signature image should be inserted on the
     * PDF signature page.
     */
    private Integer yPosition;

    /**
     * The scale of the final visible signature image expressed as zoom percentage. The value -100 represents a 0 sized
     * image, the value 0 represents unaltered size, the value 100 double size and so on. If {@code null}, 0 is
     * assumed.
     */
    @Builder.Default
    private Integer scale = 0;

    /**
     * If the PDF signature page supports more than one column (see {@link PdfSignaturePage#getColumns()}) the
     * {@code xIncrement} property tells how many pixels that should be added to the previously used {@code xPosition}
     * when inserting a PDF signature image in a new column.
     * <p>
     * Note: If the PDF signature page only supports one column this property is ignored.
     * </p>
     */
    private Integer xIncrement;

    /**
     * If the PDF signature page supports more than one row (see {@link PdfSignaturePage#getRows()}) the
     * {@code yIncrement} property tells how many pixels that should be added to the previously used {@code yPosition}
     * when inserting a PDF signature image in a new row.
     * <p>
     * Note: If the PDF signature page only supports one row this property is ignored.
     * </p>
     */
    private Integer yIncrement;

    /**
     * If the PDF signature page document (see {@link PdfSignaturePage#getContents()}) contains more than one page it is
     * possible to use the {@code page} attribute to tell where the PDF signature image(s) should be inserted. A value
     * of 1 represents the first page and a value of 0 represents the last page. The default is {@code 1}.
     * <p>
     * Note: It is only possible to have PDF signature images inserted into <b>one</b> page of the PDF signature page
     * document.
     * </p>
     */
    @Builder.Default
    private Integer page = 1;

    /** Extensions for the object. */
    private Extension extension;

    /**
     * Gets the X coordinate position (in pixels) of where the first PDF visible signature image should be inserted on
     * the PDF signature page.
     *
     * @return the X coordinate position
     */
    public Integer getXPosition() {
      return this.xPosition;
    }

    // Misspelled
    @JsonIgnore
    public Integer getXPositison() {
      return this.getXPosition();
    }

    /**
     * Assigns the X coordinate position (in pixels) of where the first PDF visible signature image should be inserted
     * on the PDF signature page.
     *
     * @param xPosition the X coordinate position
     */
    public void setXPosition(final Integer xPosition) {
      this.xPosition = xPosition;
    }

    // Misspelled
    public void setXPositison(final Integer xPosition) {
      this.setXPosition(xPosition);
    }

    /**
     * Gets the Y coordinate position (in pixels) of where the first PDF visible signature image should be inserted on
     * the PDF signature page.
     *
     * @return the Y coordinate position
     */
    public Integer getYPosition() {
      return this.yPosition;
    }

    /**
     * Assigns the Y coordinate position (in pixels) of where the first PDF visible signature image should be inserted
     * on the PDF signature page.
     *
     * @param yPosition the Y coordinate position
     */
    public void setYPosition(final Integer yPosition) {
      this.yPosition = yPosition;
    }

    /**
     * Gets the scale of the final visible signature image expressed as zoom percentage. The value -100 represents a 0
     * sized image, the value 0 represents unaltered size, the value 100 double size and so on. If {@code null}, 0 is
     * assumed.
     *
     * @return the scale of the final visible signature image
     */
    public Integer getScale() {
      return this.scale;
    }

    /**
     * Assigns the scale of the final visible signature image expressed as zoom percentage. The value -100 represents a
     * 0 sized image, the value 0 represents unaltered size, the value 100 double size and so on. If {@code null}, 0 is
     * assumed.
     *
     * @param scale the scale of the final visible signature image
     */
    public void setScale(final Integer scale) {
      this.scale = scale;
    }

    /**
     * Gets the signature page document page number. If the PDF signature page document (see
     * {@link PdfSignaturePage#getContents()}) contains more than one page it is possible to use the {@code page}
     * attribute to tell where the PDF signature image(s) should be inserted. A value of 1 represents the first page and
     * a value of 0 represents the last page. The default is {@code 1}.
     * <p>
     * Note: If the PDF signature page only supports one row this property is ignored.
     * </p>
     *
     * @return the page number
     */
    public Integer getXIncrement() {
      return this.xIncrement;
    }

    /**
     * Sets the signature page document page number. If the PDF signature page document (see
     * {@link PdfSignaturePage#getContents()}) contains more than one page it is possible to use the {@code page}
     * attribute to tell where the PDF signature image(s) should be inserted. A value of 1 represents the first page and
     * a value of 0 represents the last page. The default is {@code 1}.
     * <p>
     * Note: If the PDF signature page only supports one row this property is ignored.
     * </p>
     *
     * @param xIncrement the page number
     */
    public void setXIncrement(final Integer xIncrement) {
      this.xIncrement = xIncrement;
    }

    /**
     * Gets the additional increment of Y coordinate position (in pixels) that should be added to the previously used
     * {@code yPosition} when inserting a PDF signature image in a new row.
     * <p>
     * Note: If the PDF signature page only supports one row this property is ignored.
     * </p>
     *
     * @return Y coordinate position increment
     */
    public Integer getYIncrement() {
      return this.yIncrement;
    }

    /**
     * Sets the additional increment of Y coordinate position (in pixels) that should be added to the previously used
     * {@code yPosition} when inserting a PDF signature image in a new row.
     *
     * @param yIncrement Y coordinate position increment Note: If the PDF signature page only supports one row this
     *     property is ignored.
     */
    public void setYIncrement(final Integer yIncrement) {
      this.yIncrement = yIncrement;
    }

    /**
     * Gets the signature page document page number. If the PDF signature page document (see
     * {@link PdfSignaturePage#getContents()}) contains more than one page it is possible to use the {@code page}
     * attribute to tell where the PDF signature image(s) should be inserted. A value of 1 represents the first page and
     * a value of 0 represents the last page. The default is {@code 1}.
     * <p>
     * Note: It is only possible to have PDF signature images inserted into <b>one</b> page of the PDF signature page
     * document.
     * </p>
     *
     * @return the page number
     */
    public Integer getPage() {
      return this.page;
    }

    /**
     * Sets the signature page document page number. If the PDF signature page document (see
     * {@link PdfSignaturePage#getContents()}) contains more than one page it is possible to use the {@code page}
     * attribute to tell where the PDF signature image(s) should be inserted. A value of 1 represents the first page and
     * a value of 0 represents the last page. The default is {@code 1}.
     * <p>
     * Note: It is only possible to have PDF signature images inserted into <b>one</b> page of the PDF signature page
     * document.
     * </p>
     *
     * @param page the page number
     */
    public void setPage(final Integer page) {
      this.page = page;
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
     * Builder for {@code PdfSignatureImagePlacementConfiguration} objects.
     */
    public static class PdfSignatureImagePlacementConfigurationBuilder
        implements ObjectBuilder<PdfSignatureImagePlacementConfiguration> {
      @SuppressWarnings("unused")
      private Integer page = 1;
      @SuppressWarnings("unused")
      private Integer scale = 0;

      // Lombok
    }
  }

}
