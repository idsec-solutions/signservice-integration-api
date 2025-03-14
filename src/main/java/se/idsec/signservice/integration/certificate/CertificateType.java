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
package se.idsec.signservice.integration.certificate;

/**
 * Representation of the different certificate types that are supported by a SignService.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public enum CertificateType {

  /** Public Key Certificate that is not a Qualified Certificate. */
  PKC("PKC"),

  /** Qualified Certificate. */
  QC("QC"),

  /**
   * Qualified Certificate associated with a private key held in a Qualified Signature Creation Device according to
   * eIDAS.
   */
  QC_SSCD("QC/SSCD");

  /**
   * Gets the type.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }

  /**
   * Given a type string the corresponding enum constant is returned.
   *
   * @param type the type
   * @return a CertificateType
   * @throws IllegalArgumentException if no matching enum constant is found
   */
  public static CertificateType fromType(final String type) throws IllegalArgumentException {
    for (final CertificateType t : CertificateType.values()) {
      if (t.getType().equalsIgnoreCase(type) || t.name().equals(type)) {
        return t;
      }
    }
    throw new IllegalArgumentException("%s is not a valid certificate type".formatted(type));
  }

  /** Textual representation of the type. */
  private final String type;

  /**
   * Constructor.
   *
   * @param type the type
   */
  CertificateType(final String type) {
    this.type = type;
  }
}
