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
package se.idsec.signservice.integration.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;

/**
 * Represents a signed document.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 * @see TbsDocument
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class SignedDocument implements Extensible {

  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The unique ID for this document (within the current operation). */
  private String id;

  /** The signed document as a Base64-encoded byte string. */
  private String signedContent;

  /** The MIME type of the signed document. See {@link DocumentType} for the supported types. */
  private String mimeType;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Gets the unique ID for this document (within the current operation).
   *
   * @return unique ID for this document
   */
  public String getId() {
    return this.id;
  }

  /**
   * Assigns the unique ID for this document (within the current operation).
   *
   * @param id unique ID for this document
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * Gets the signed document as a Base64-encoded byte string.
   *
   * @return the signed document content (Base64-encoded)
   */
  public String getSignedContent() {
    return this.signedContent;
  }

  /**
   * Assigns the signed document as a Base64-encoded byte string.
   *
   * @param signedContent the signed document content (Base64-encoded)
   */
  public void setSignedContent(final String signedContent) {
    this.signedContent = signedContent;
  }

  /**
   * Gets the MIME type of the signed document. See {@link DocumentType} for the supported types.
   *
   * @return the MIME type for the signed document
   */
  public String getMimeType() {
    return this.mimeType;
  }

  /**
   * Assigns the MIME type of the signed document. See {@link DocumentType} for the supported types.
   *
   * @param mimeType the MIME type for the signed document
   */
  public void setMimeType(final String mimeType) {
    this.mimeType = mimeType;
  }

  /** {@inheritDoc} */
  @Override
  public void setExtension(final Extension extension) {
    this.extension = extension;
  }

  /** {@inheritDoc} */
  @Override
  public Extension getExtension() {
    return this.extension;
  }

  /**
   * Builder for {@code SignedDocument} objects.
   */
  public static class SignedDocumentBuilder implements ObjectBuilder<SignedDocument> {
    // Lombok
  }

}
