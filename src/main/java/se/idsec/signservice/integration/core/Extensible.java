/*
 * Copyright 2019-2022 IDsec Solutions AB
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

/**
 * Interface that may be inherited for domain objects that should be extensible with name-value pair parameters.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface Extensible {

  /**
   * Returns the extension parameters for the instance.
   *
   * @return the extension, or null if no extensions are set
   */
  Extension getExtension();

  /**
   * Assigns the extension parameters for the instance.
   *
   * @param extension
   *          the extension
   */
  void setExtension(final Extension extension);

  /**
   * Gets an extension value.
   *
   * @param name
   *          the extension name
   * @return the extension value or null if it does not exist
   */
  default String getExtensionValue(final String name) {
    final Extension extension = this.getExtension();
    return extension != null ? extension.get(name) : null;
  }

  /**
   * Adds an extension.
   *
   * @param name
   *          extension name
   * @param value
   *          extension value
   */
  default void addExtensionValue(final String name, final String value) {
    if (this.getExtension() != null) {
      this.getExtension().put(name, value);
    }
    else {
      final Extension extension = new Extension();
      extension.put(name, value);
      this.setExtension(extension);
    }
  }

}
