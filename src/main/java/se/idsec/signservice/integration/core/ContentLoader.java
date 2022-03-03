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

import java.io.IOException;

/**
 * Interface for loading the content from a file resource.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface ContentLoader {

  /**
   * Loads the contents of the given resource.
   *
   * <p>
   * Note: The Spring Framework style of representing a resource must be supported by implementations. For example:
   * {@code classpath:xyz.svg} and {@code file:/path/xyz.svg}.
   * </p>
   *
   * @param resource the resource to load
   * @return the contents as a byte array
   * @throws IOException if the contents can not be loaded
   */
  byte[] loadContent(final String resource) throws IOException;

}
