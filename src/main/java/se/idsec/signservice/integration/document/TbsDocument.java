/*
 * Copyright 2019-2025 IDsec Solutions AB
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
package se.idsec.signservice.integration.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.pdf.VisiblePdfSignatureRequirement;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a document that is to be signed along with the per-document requirements and parameters.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TbsDocument implements Extensible, Serializable {

  @Serial
  private static final long serialVersionUID = 3224804407700034526L;

  /** The unique ID for this document (within the current request). */
  private String id;

  /** The Base64-encoded byte string that is the content of the document that is to be signed. */
  private String content;

  /** A content reference may be used instead of supplying the actual content. */
  private String contentReference;

  /** The MIME type of the document that is to be signed. */
  private String mimeType;

  /** Optional processing rules used by the sign service to process sign data. */
  private String processingRules;

  /** Specifies of the resulting signature should use an ETSI AdES format. */
  private EtsiAdesRequirement adesRequirement;

  /**
   * If the document that is to be signed is a PDF document, the sign requester may require the resulting PDF to have a
   * "visible PDF signature".
   */
  private VisiblePdfSignatureRequirement visiblePdfSignatureRequirement;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Gets the unique ID for this document (within the current request).
   *
   * @return unique ID for this document, or {@code null} if none has been set
   */
  public String getId() {
    return this.id;
  }

  /**
   * Assigns unique ID for this document (within the current request). If not supplied, the SignService Integration
   * Service will generate one.
   *
   * @param id unique ID for this document
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * Gets the Base64-encoded byte string that is the content of the document that is to be signed.
   *
   * @return the document content (Base64-encoded)
   */
  public String getContent() {
    return this.content;
  }

  /**
   * Assigns the Base64-encoded byte string that is the content of the document that is to be signed.
   *
   * @param content the document content (Base64-encoded)
   */
  public void setContent(final String content) {
    this.content = content;
  }

  /**
   * Gets the content reference may be used instead of supplying the actual content ({@link #setContent(String)}).
   *
   * @return a reference to the content or {@code null}
   */
  public String getContentReference() {
    return this.contentReference;
  }

  /**
   * Assigns a content reference may be used instead of supplying the actual content ({@link #setContent(String)}). This
   * is typically something that is useful when handling large documents. However, this feature is only useable if:
   * <ul>
   * <li>The SignService Integration Service profile is in "stateful mode", and,</li>
   * <li>the document has previously been cached by the SignService Integration Service.</li>
   * </ul>
   * <p>
   * Typically, this is used if the SignService Integration Service is running as a standalone service. When using the
   * API as an integrated Java library in the service it makes less sense.
   * </p>
   *
   * @param contentReference a reference to the content
   */
  public void setContentReference(final String contentReference) {
    this.contentReference = contentReference;
  }

  /**
   * Gets the MIME type of the document that is to be signed. See {@link DocumentType} for the supported types.
   *
   * @return the MIME type
   */
  public String getMimeType() {
    return this.mimeType;
  }

  /**
   * Assigns the MIME type of the document that is to be signed. See {@link DocumentType} for the supported types.
   *
   * @param mimeType the document MIME type
   * @see #setMimeType(DocumentType)
   */
  public void setMimeType(final String mimeType) {
    this.mimeType = mimeType;
  }

  /**
   * Assigns The document type of the document that is to be signed.
   *
   * @param documentType the document type
   */
  public void setMimeType(final DocumentType documentType) {
    this.mimeType = documentType != null ? documentType.getMimeType() : null;
  }

  /**
   * Gets the Optional processing rules used by the sign service to process sign data.
   *
   * @return the processing rules identifier, or {@code null} if none has been set
   */
  public String getProcessingRules() {
    return this.processingRules;
  }

  /**
   * Assigns processing rules used by the sign service to process sign data.
   *
   * @param processingRules the processing rules
   */
  public void setProcessingRules(final String processingRules) {
    this.processingRules = processingRules;
  }

  /**
   * Tells whether the resulting signature should use an ETSI AdES format.
   *
   * @return the AdES requirement or {@code null} if no AdES requirement exists
   */
  public EtsiAdesRequirement getAdesRequirement() {
    return this.adesRequirement;
  }

  /**
   * Specifies whether the resulting signature should use an ETSI AdES format.
   *
   * @param adesRequirement the AdES requirement
   */
  public void setAdesRequirement(final EtsiAdesRequirement adesRequirement) {
    this.adesRequirement = adesRequirement;
  }

  /**
   * If the document that is to be signed is a PDF document, the sign requester may require the resulting PDF to have a
   * "visible PDF signature". The {@code VisiblePdfSignatureRequirement} specifies how this visible indication should be
   * included.
   *
   * @return requirement for visible PDF signatures, or {@code null}
   */
  public VisiblePdfSignatureRequirement getVisiblePdfSignatureRequirement() {
    return this.visiblePdfSignatureRequirement;
  }

  /**
   * If the document that is to be signed is a PDF document, the sign requester may require the resulting PDF to have a
   * "visible PDF signature". The {@code VisiblePdfSignatureRequirement} specifies how this visible indication should be
   * included.
   *
   * <p>
   * Note that a signature policy may have a default visible PDF signature requirement configued, which means that the
   * {@code visiblePdfSignatureRequirement} does not have to be assigned, unless the signature policy default should be
   * overridden. For the special case where a signature policy has a default requirement configured, and the caller does
   * not want a visible PDF signature to be included at all, an {@link VisiblePdfSignatureRequirement} instance with an
   * extension {@link VisiblePdfSignatureRequirement#NULL_INDICATOR_EXTENSION} set to {@code true} should be assigned
   * (see {@link VisiblePdfSignatureRequirement#createNullVisiblePdfSignatureRequirement()}).
   * </p>
   *
   * @param visiblePdfSignatureRequirement requirement for visible PDF signatures
   */
  public void setVisiblePdfSignatureRequirement(
      final VisiblePdfSignatureRequirement visiblePdfSignatureRequirement) {
    this.visiblePdfSignatureRequirement = visiblePdfSignatureRequirement;
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
   * Builder for {@code TbsDocument} objects.
   */
  public static class TbsDocumentBuilder implements ObjectBuilder<TbsDocument> {
    // Lombok will generate code ...

    public TbsDocumentBuilder mimeType(final String mimeType) {
      this.mimeType = mimeType;
      return this;
    }

    public TbsDocumentBuilder mimeType(final DocumentType mimeType) {
      this.mimeType = mimeType != null ? mimeType.getMimeType() : null;
      return this;
    }
  }

  /**
   * Enum reprenting an ETSI AdES format.
   */
  public enum AdesType {
    /** ETSI Basic Electronic Signature format */
    BES,
    /** ETSI Extended Policy Electronic Signature format */
    EPES
  }

  /**
   * Representation of an ETSI AdES signature requirement.
   *
   * @author Martin Lindström (martin@idsec.se)
   * @author Stefan Santesson (stefan@idsec.se)
   */
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class EtsiAdesRequirement implements Extensible, Serializable {

    /** For serialization. */
    @Serial
    private static final long serialVersionUID = 3139149873709105674L;

    /** The ETSI AdES type. */
    private AdesType adesFormat;

    /** The signature policy (required for EPES). */
    private String signaturePolicy;

    /**
     * Optional AdES object as a Base64-encoded byte array.
     *
     * <p>
     * For XML signatures this object must be a {@code ds:Object} having as its only child a
     * {@code xades:QualifyingProperties} element.
     * </p>
     */
    private String adesObject;

    /** Extensions for the object. */
    private Extension extension;

    /**
     * Gets the ETSI AdES type.
     *
     * @return the format
     */
    public AdesType getAdesFormat() {
      return this.adesFormat;
    }

    /**
     * Assigns the ETSI AdES type.
     *
     * @param adesFormat the format
     */
    public void setAdesFormat(final AdesType adesFormat) {
      this.adesFormat = adesFormat;
    }

    /**
     * Gets the signature policy (required for EPES).
     *
     * <p>
     * When signing an XML document, this fields can be left unset if the AdES object is set, and this element contains
     * a {@code SignaturePolicyIdentifier} element.
     * </p>
     *
     * @return the signature policy
     */
    public String getSignaturePolicy() {
      return this.signaturePolicy;
    }

    /**
     * Assigns the signature policy (required for EPES).
     *
     * <p>
     * When signing an XML document, this fields can be left unset if the AdES object is set, and this element contains
     * a {@code SignaturePolicyIdentifier} element.
     * </p>
     *
     * @param signaturePolicy the signature policy
     */
    public void setSignaturePolicy(final String signaturePolicy) {
      this.signaturePolicy = signaturePolicy;
    }

    /**
     * Gets the AdES object as a Base64-encoded byte array.
     *
     * <p>
     * For XML signatures this object must be a {@code ds:Object} having as its only child a
     * {@code xades:QualifyingProperties} element.
     * </p>
     *
     * @return the AdES object or null
     */
    public String getAdesObject() {
      return this.adesObject;
    }

    /**
     * Assigns the AdES object as a Base64-encoded byte array.
     *
     * <p>
     * For XML signatures this object must be a {@code ds:Object} having as its only child a
     * {@code xades:QualifyingProperties} element.
     * </p>
     *
     * @param adesObject the AdES object
     */
    public void setAdesObject(final String adesObject) {
      this.adesObject = adesObject;
    }

    /** {@inheritDoc} */
    @Override
    public Extension getExtension() {
      return this.extension;
    }

    /**
     * Assigns the extensions for the object.
     *
     * @param extension the extensions for this object
     */
    @Override
    public void setExtension(final Extension extension) {
      this.extension = extension;
    }

    /**
     * Builder for {@link EtsiAdesRequirement}.
     */
    public static class EtsiAdesRequirementBuilder implements ObjectBuilder<EtsiAdesRequirement> {
    }

  }

}
