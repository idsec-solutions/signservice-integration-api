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
package se.idsec.signservice.integration;

/**
 * Internal class used for representation of the API version.
 *
 * @author Martin Lindström
 */
public final class ApiVersion {

  private static final int MAJOR = 2;
  private static final int MINOR = 3;
  private static final int PATCH = 1;

  /**
   * Gets the version string.
   *
   * @return the version string
   */
  public static String getVersion() {
    return MAJOR + "." + MINOR + "." + PATCH;
  }

}
