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

/**
 * Representation of document types (documents for signing).
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public enum DocumentType {

  /** XML document. */
  XML("application/xml"),

  /** PDF document. */
  PDF("application/pdf"),

  /** JSON document. */
  JSON("application/json");

  /**
   * Returns the MIME type for the document type.
   *
   * @return the MIME type
   */
  public String getMimeType() {
    return this.mimeType;
  }

  /**
   * Given a MIME type the method returns the corresponding enum constant.
   *
   * @param mimeType the MIME type
   * @return the enum constant value
   * @throws IllegalArgumentException if no matching enum constant is found
   */
  public static DocumentType fromMimeType(final String mimeType) throws IllegalArgumentException {
    for (final DocumentType t : DocumentType.values()) {
      if (t.getMimeType().equals(mimeType) || t.name().equalsIgnoreCase(mimeType)) {
        return t;
      }
    }
    throw new IllegalArgumentException(String.format("%s is not a supported document type", mimeType));
  }

  /** The mime type. */
  private final String mimeType;

  /**
   * Constructor.
   *
   * @param mimeType the mime type.
   */
  DocumentType(final String mimeType) {
    this.mimeType = mimeType;
  }

}
