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

import java.io.Serial;

/**
 * Exception class that represents non-successful DSS operations.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class SignResponseErrorStatusException extends Exception {

  @Serial
  private static final long serialVersionUID = 5713807960873891492L;

  /** The DSS major result code. */
  private final String majorCode;

  /** The DSS minor result code. */
  private final String minorCode;

  /**
   * Constructor.
   *
   * @param majorCode the DSS major result code
   * @param minorCode the DSS minor result code
   */
  public SignResponseErrorStatusException(final String majorCode, final String minorCode) {
    this(majorCode, minorCode, "DSS error: " + majorCode);
  }

  /**
   * Constructor.
   *
   * @param majorCode the DSS major result code
   * @param minorCode the DSS minor result code
   * @param message the DSS result message
   */
  public SignResponseErrorStatusException(final String majorCode, final String minorCode, final String message) {
    super(message);
    this.majorCode = majorCode;
    this.minorCode = minorCode;
  }

  /**
   * Gets the DSS major status code.
   *
   * @return the DSS major status code
   */
  public String getMajorCode() {
    return this.majorCode;
  }

  /**
   * Gets the DSS minor status code.
   *
   * @return the DSS minor status code
   */
  public String getMinorCode() {
    return this.minorCode;
  }

}
