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
package se.idsec.signservice.integration.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;
import se.idsec.signservice.integration.core.AbstractIdentityAttribute;
import se.idsec.signservice.integration.core.ObjectBuilder;

/**
 * Representation of a signer identity attribute.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class SignerIdentityAttribute extends AbstractIdentityAttribute {

  /** For serialization. */
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** Type indicator for SAML attribute. */
  public static final String SAML_TYPE = "saml";

  /**
   * Default constructor.
   */
  public SignerIdentityAttribute() {
  }

  /**
   * Constructor.
   *
   * @param type the type of the attribute
   * @param name the name of the attribute
   */
  @Builder(builderMethodName = "createBuilder", toBuilder = true)
  public SignerIdentityAttribute(final String type, final String name) {
    super(type, name);
  }

  /**
   * Builder for {@code SignerIdentityAttribute} objects.
   */
  public static class SignerIdentityAttributeBuilder implements ObjectBuilder<SignerIdentityAttribute> {
  }

}
