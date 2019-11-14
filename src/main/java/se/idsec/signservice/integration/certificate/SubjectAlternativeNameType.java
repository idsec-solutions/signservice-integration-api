/*
 * Copyright 2019 IDsec Solutions AB
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
 * An enum that represents the different name types that can be stored in an X.509 subject alternative name certificate
 * extension.
 *
 * The following ASN.1 structure is mapped (excluding {@code otherName}):
 *
 * <pre>
 * GeneralName ::= CHOICE {
 *   otherName       [0] INSTANCE OF OTHER-NAME,
 *   rfc822Name      [1] IA5String,
 *   dNSName         [2] IA5String,
 *   x400Address     [3] ORAddress,
 *   directoryName   [4] Name,
 *   ediPartyName    [5] EDIPartyName,
 *   uniformResourceIdentifier [6] IA5String,
 *   IPAddress       [7] OCTET STRING,
 *   registeredID    [8] OBJECT IDENTIFIER
 * }
 * </pre>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public enum SubjectAlternativeNameType {

  /** An RFC 822 name, typically an email address. */
  RFC822_NAME("rfc822Name", 1),

  /** DNS name, for example a host name. */
  DNS_NAME("dNSName", 2),

  /** X.400 address, see <a href="https://en.wikipedia.org/wiki/X.400">X.400</a>. */
  X400_ADDRESS("x400Address", 3),

  /** Directory name */
  DIRECTORY_NAME("directoryName", 4),

  /** EDI party name. Probably never used. */
  EDI_PARTY_NAME("ediPartyName", 5),

  /** An URI. */
  UNIFORM_RESOURCE_IDENTIFIER("uniformResourceIdentifier", 6),

  /** An IP address. */
  IP_ADDRESS("IPAddress", 7),

  /** Registered ID. */
  REGISTERED_ID("registeredID", 8);

  /**
   * Returns the name.
   *
   * @return the name
   */
  public String getTypeName() {
    return this.typeName;
  }

  /**
   * Returns the index (mapping to the ASN.1 {@code GeneralName} choice).
   *
   * @return the index
   */
  public int getIndex() {
    return this.index;
  }

  /**
   * If the supplied name matches {@link #getTypeName()} or {@link #name()} the corresponding enum constant is returned.
   *
   * @param typeName
   *          the type name
   * @return the enum constant
   * @throws IllegalArgumentException
   *           if no matching constant is found
   */
  public static SubjectAlternativeNameType fromTypeName(final String typeName) throws IllegalArgumentException {
    for (final SubjectAlternativeNameType t : SubjectAlternativeNameType.values()) {
      if (t.getTypeName().equalsIgnoreCase(typeName) || t.name().equals(typeName)) {
        return t;
      }
    }
    throw new IllegalArgumentException(String.format("%s is not a valid typeName for SubjectAlternativeNameType", typeName));
  }

  /**
   * Finds the enum constant based on its index number.
   *
   * @param index
   *          the index
   * @return the enum constant
   * @throws IllegalArgumentException
   *           if no matching constant is found
   */
  public static SubjectAlternativeNameType fromIndex(final int index) throws IllegalArgumentException {
    for (final SubjectAlternativeNameType t : SubjectAlternativeNameType.values()) {
      if (t.getIndex() == index) {
        return t;
      }
    }
    throw new IllegalArgumentException(String.format("Index %d does not match a valid name of SubjectAlternativeNameType", index));
  }

  /** The name. */
  private String typeName;

  /** The index mapping to the ASN.1 {@code GeneralName} choice. */
  private int index;

  SubjectAlternativeNameType(final String typeName, final int index) {
    this.typeName = typeName;
    this.index = index;
  }

}
