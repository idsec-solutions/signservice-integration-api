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
package se.idsec.signservice.integration.document;

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
public class SignedDocument implements Extensible {

  /**
   * The unique ID for this document (within the current operation).
   * 
   * @param id
   *          unique ID for this document
   * @return unique ID for this document
   */
  @Setter
  @Getter
  private String id;

  /**
   * The signed document as a Base64-encoded byte string.
   * 
   * @param signedContent
   *          the signed document content (Base64-encoded)
   * @return the signed document content (Base64-encoded)
   */
  @Setter
  @Getter
  private String signedContent;

  /**
   * The MIME type of the signed document. See {@link DocumentType} for the supported types.
   * 
   * @param mimeType
   *          the MIME type for the signed document
   * @return the MIME type for the signed document
   */
  @Setter
  @Getter
  private String mimeType;

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
   * Builder for {@code SignedDocument} objects.
   */
  public static class SignedDocumentBuilder implements ObjectBuilder<SignedDocument> {
    // Lombok
  }

}
