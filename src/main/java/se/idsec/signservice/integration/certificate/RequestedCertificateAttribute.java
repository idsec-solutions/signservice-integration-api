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
package se.idsec.signservice.integration.certificate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.ToString;
import se.idsec.signservice.integration.core.AbstractIdentityAttribute;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;

/**
 * Represents a requested certificate attribute. The sign requester states that a given principal attribute should be
 * mapped into a certificate attribute. This class represents how the requirements for certificate attributes are
 * represented.
 *
 * <p>
 * This Java implementation uses one class for this purpose even though there are three different attribute types. The
 * reason is that we want to be JSON processor libary agnostic, and working with interfaces and subclasses is hard
 * then.
 * </p>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RequestedCertificateAttribute extends AbstractIdentityAttribute {

  @Serial
  private static final long serialVersionUID = -4866653598772777511L;

  /** The default value to use if no mapping can be found. */
  private String defaultValue;

  /** Friendly name of attribute (optional). */
  private String friendlyName;

  /** Indicates if this attribute must be provided. */
  private Boolean required;

  /**
   * Default constructor.
   */
  public RequestedCertificateAttribute() {
  }

  /**
   * Constructor creating the object with the given type.
   *
   * @param type the type
   */
  public RequestedCertificateAttribute(final RequestedCertificateAttributeType type) {
    this.type = type.getType();
  }

  /**
   * Returns a builder for {@code RequestedCertificateAttribute} objects.
   *
   * @return a RequestedCertificateAttribute builder
   */
  public static RequestedCertificateAttributeBuilder builder() {
    return new RequestedCertificateAttributeBuilder();
  }

  /** {@inheritDoc} */
  @Override
  public void setType(final String type) {
    // Make sure it is a valid type ...
    final RequestedCertificateAttributeType _type = RequestedCertificateAttributeType.fromType(type);
    super.setType(_type.getType());
  }

  /**
   * Assigns the attribute type.
   *
   * @param type the attribute type
   */
  public void setType(final RequestedCertificateAttributeType type) {
    super.setType(type.getType());
  }

  /**
   * Gets the default value to use if no mapping can be found.
   *
   * @return the value to use if no mapping can be found, or {@code null} if no default value has been assigned
   */
  public String getDefaultValue() {
    return this.defaultValue;
  }

  /**
   * Assigns te default value to use if no mapping can be found.
   *
   * @param defaultValue the value to use if no mapping can be found
   */
  public void setDefaultValue(final String defaultValue) {
    this.defaultValue = defaultValue;
  }

  /**
   * Gets the friendly name of attribute.
   *
   * @return the "friendly name" of the attribute for display purposes or {@code null} if not present
   */
  public String getFriendlyName() {
    return this.friendlyName;
  }

  /**
   * Assigns the friendly name of attribute.
   *
   * @param friendlyName the "friendly name" of the attribute for display purposes
   */
  public void setFriendlyName(final String friendlyName) {
    this.friendlyName = friendlyName;
  }

  /**
   * Gets whether this attribute must be provided.
   *
   * @return tells whether the attribute is required
   */
  public Boolean getRequired() {
    return this.required;
  }

  /**
   * Assigns whether this attribute must be provided.
   *
   * @param required whether the attribute is required
   */
  public void setRequired(final Boolean required) {
    this.required = required;
  }

  /**
   * Builder for {@code RequestedCertificateAttribute} objects.
   */
  public static class RequestedCertificateAttributeBuilder implements ObjectBuilder<RequestedCertificateAttribute> {

    /** The object. */
    private final RequestedCertificateAttribute object;

    /**
     * Constructor.
     */
    RequestedCertificateAttributeBuilder() {
      this.object = new RequestedCertificateAttribute();
    }

    /** {@inheritDoc} */
    @Override
    public RequestedCertificateAttribute build() {
      return this.object;
    }

    /**
     * Adds the attribute type.
     *
     * @param type the type
     * @return the builder
     */
    public RequestedCertificateAttributeBuilder type(final RequestedCertificateAttributeType type) {
      this.object.setType(type.getType());
      return this;
    }

    /**
     * Adds the attribute name.
     *
     * @param name the attribute name
     * @return the builder
     */
    public RequestedCertificateAttributeBuilder name(final String name) {
      this.object.setName(name);
      return this;
    }

    /**
     * Adds the attribute default value
     *
     * @param defaultValue the default value
     * @return the builder
     */
    public RequestedCertificateAttributeBuilder defaultValue(final String defaultValue) {
      this.object.setDefaultValue(defaultValue);
      return this;
    }

    /**
     * Adds the attribute friendly name
     *
     * @param friendlyName the friendly name
     * @return the builder
     */
    public RequestedCertificateAttributeBuilder friendlyName(final String friendlyName) {
      this.object.setFriendlyName(friendlyName);
      return this;
    }

    /**
     * Tells whether the attribute is a required attribute
     *
     * @param required flag
     * @return the builder
     */
    public RequestedCertificateAttributeBuilder required(final Boolean required) {
      this.object.setRequired(required);
      return this;
    }
  }

}
