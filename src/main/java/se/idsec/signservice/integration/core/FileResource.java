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

/**
 * A generic representation of a file resource.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface FileResource {

  /**
   * Gets the contents of the file resource as a base64 encoded string.
   * 
   * @return the file resource content as a base64 encoded string or null if no content is available
   */
  String getContents();

  /**
   * Assigns the file resource contents as a base64 encoded string.
   * 
   * @param contents
   *          the base64 encoded contents of the file resource
   */
  void setContents(final String contents);

  /**
   * Gets the description of the file resource.
   * 
   * @return the description of the file resource or null if none is available
   */
  String getDescription();

  /**
   * Assigns the description of the file resource.
   * 
   * @param description
   *          the description of the file resource
   */
  void setDescription(final String description);

}
