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
package se.idsec.signservice.integration.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Representation of the value of a signer attribute.
 *
 * <p>
 * When a sign requester sends a SignRequest message it includes requirements on the signer regarding authenticated
 * attributes. This class represents these requirements.
 * </p>
 *
 * <p>
 * Currently, the only supported attribute type is SAML. A missing type will represent SAML ({@link #getType()}).
 * </p>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class SignerIdentityAttributeValue extends SignerIdentityAttribute {

  /** For serializing. */
  private static final long serialVersionUID = 3356867580368876329L;

  /** The default name format to use. */
  public static final String DEFAULT_NAME_FORMAT = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri";

  /** The default attribute value type to use. We assume SAML's xs:string. */
  public static final String DEFAULT_ATTRIBUTE_VALUE_TYPE = "string";

  /**
   * The attribute value (in its string representation).
   *
   * @param value
   *          the attribute value (in its string representation)
   * @return the attribute value (in its string representation)
   */
  @Setter
  @Getter
  private String value;

  /**
   * The name format for the attribute. When the attribute type is SAML the default is {@value #DEFAULT_NAME_FORMAT}.
   *
   * @param nameFormat
   *          the (SAML) name format
   * @return the name format for the attribute
   */
  @Setter
  @Getter
  private String nameFormat;

  /**
   * The type for the attribute value. When SAML attributes are represented, this holds the XSI type without the
   * namespace prefix. The default in those cases is {@value #DEFAULT_ATTRIBUTE_VALUE_TYPE}.
   *
   * @param attributeValueType
   *          the attribute value type
   * @return the attribute value type
   */
  @Setter
  @Getter
  private String attributeValueType;

  /**
   * Default constructor.
   */
  public SignerIdentityAttributeValue() {
  }

  /**
   * Constructor.
   *
   * @param type
   *          the type of attribute (if null, SAML is assumed)
   * @param name
   *          the name of the attribute
   * @param value
   *          the value of the attribute
   * @param nameFormat
   *          the name format
   * @param attributeValueType
   *          the value type
   */
  @Builder
  public SignerIdentityAttributeValue(final String type, final String name, final String value,
      final String nameFormat, final String attributeValueType) {
    super(type, name);
    this.value = value;
    this.nameFormat = nameFormat;
    this.attributeValueType = attributeValueType;
  }

  /**
   * Builder for {@code SignerIdentityAttributeValue} objects.
   */
  public static class SignerIdentityAttributeValueBuilder implements ObjectBuilder<SignerIdentityAttributeValue> {
    // Lombok will generate code ...
  }

}
