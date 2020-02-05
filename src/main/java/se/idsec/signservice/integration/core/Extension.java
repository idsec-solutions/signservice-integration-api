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
package se.idsec.signservice.integration.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an extension to a domain object. An extension is a set of name-value pairs.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class Extension extends HashMap<String, Object> {

  /** For serializing. */
  private static final long serialVersionUID = 8520822824322024498L;

  /**
   * Default constructor.
   */
  public Extension() {
    super();
  }

  /**
   * Copy constructor.
   * 
   * @param m
   *          the map to initialize the object with
   */
  public Extension(Map<? extends String, ? extends Object> m) {
    super(m);
  }

  /**
   * Returns a builder for extension builder.
   * 
   * @return a builder
   */
  public static ExtensionBuilder builder() {
    return new ExtensionBuilder();
  }

  /**
   * Gets the value for extension identified by {@code name}
   * 
   * @param name
   *          the name of the extension
   * @param type
   *          the expected class of the extension value
   * @param <T>
   *          the the expected class of the extension value
   * @return the extension value, or {@code null} if it is not available
   * @throws ClassCastException
   *           if the value exists but is not assignable to type
   */
  public <T> T get(String name, Class<T> type) {
    Object object = this.get(name);
    if (object == null) {
      return null;
    }
    return type.cast(object);
  }

  /**
   * Builder for {@code Extension} objects.
   *
   * @author Martin Lindström (martin@idsec.se)
   * @author Stefan Santesson (stefan@idsec.se)
   */
  public static class ExtensionBuilder implements ObjectBuilder<Extension> {

    /** The object being built. */
    private Extension extension;

    /**
     * Constructor.
     */
    ExtensionBuilder() {
      this.extension = new Extension();
    }

    /** {@inheritDoc} */
    @Override
    public Extension build() {
      return this.extension;
    }

    /**
     * Adds a name-value pair.
     * 
     * @param name
     *          extension name
     * @param value
     *          extension value
     * @return the builder
     */
    ExtensionBuilder add(String name, Object value) {
      this.extension.put(name, value);
      return this;
    }

  }

}
