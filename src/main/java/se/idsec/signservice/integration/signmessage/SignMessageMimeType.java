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
package se.idsec.signservice.integration.signmessage;

/**
 * Enum representing a MIME type for sign messages.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public enum SignMessageMimeType {

  /** Mime type for text */
  TEXT("text"),

  /** Mime type for HTML */
  HTML("text/html"),

  /** Mime type for markdown */
  MARKDOWN("text/markdown");

  /**
   * Gets the textual representation of the MIME type.
   *
   * @return the textual representation of the enum value
   */
  public String getMimeType() {
    return this.mimeType;
  }

  /**
   * Given a MIME type's textual representation, the method returns its enum value.
   *
   * @param mimeType the MIME type
   * @return the SignMessageMimeType
   * @throws IllegalArgumentException if no matching enum constant is found
   */
  public static SignMessageMimeType fromMimeType(final String mimeType) throws IllegalArgumentException {
    for (final SignMessageMimeType t : SignMessageMimeType.values()) {
      if (t.getMimeType().equalsIgnoreCase(mimeType) || t.name().equals(mimeType)) {
        return t;
      }
    }
    throw new IllegalArgumentException(String.format("%s is not a valid MIME type for sign messages", mimeType));
  }

  /** Mime type value */
  private final String mimeType;

  /**
   * Constructor.
   *
   * @param mimeType the MIME type
   */
  SignMessageMimeType(final String mimeType) {
    this.mimeType = mimeType;
  }

}
