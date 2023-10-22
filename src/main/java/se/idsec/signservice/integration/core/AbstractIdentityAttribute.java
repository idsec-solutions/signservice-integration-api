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
package se.idsec.signservice.integration.core;

import java.io.Serializable;

import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;

/**
 * A base class for identity attributes.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
public abstract class AbstractIdentityAttribute implements Serializable {

  /** For serialization. */
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The type of attribute. */
  protected String type;

  /** The attribute name. */
  protected String name;

  /**
   * Default constructor.
   */
  public AbstractIdentityAttribute() {
  }

  /**
   * Constructor setting the type and name.
   *
   * @param type the type of attribute
   * @param name the attribute name
   */
  public AbstractIdentityAttribute(final String type, final String name) {
    this.type = type;
    this.name = name;
  }

  /**
   * Gets the identity attribute type.
   *
   * @return the string identifying the type of attribute represented
   */
  public String getType() {
    return this.type;
  }

  /**
   * Assigns the identity attribute type.
   *
   * @param type the attribute type
   */
  public void setType(final String type) {
    this.type = type;
  }

  /**
   * Gets the attribute name.
   *
   * @return the attribute name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the attribute name.
   *
   * @param name the attribute name
   */
  public void setName(final String name) {
    this.name = name;
  }

}
