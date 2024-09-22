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
package se.idsec.signservice.integration.core.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import se.idsec.signservice.integration.ApiVersion;

import java.io.Serial;
import java.io.Serializable;

/**
 * When using the SignService Integration Service via a REST API we report errors using error codes (and of course
 * messages). This class defines the structure of the error codes used.
 * <p>
 * All errors are prefixed with {@code "error."} followed by the category of the error, for example {@code bad-request}
 * and the ends with the actual error, for example {@code missing-policy}. In string format this error is then displayed
 * as {@code error.bad-request.missing-policy}.
 * </p>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class ErrorCode implements Serializable {

  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** Prefix for all errors. */
  public static final String ERROR_CODE_PREFIX = "error.";

  /** The category of the error. */
  private String category;

  /** The error code within the category. */
  private String code;

  /**
   * Constructor taking a complete error code string on the format "error.category.code".
   *
   * @param errorCode the error code in string format
   */
  public ErrorCode(final String errorCode) {
    this.parse(errorCode);
  }

  /**
   * Constructor taking the category and the code.
   *
   * @param category the error category
   * @param code the code within the category
   */
  public ErrorCode(final String category, final String code) {
    if (category.contains(".")) {
      throw new IllegalArgumentException("Invalid category - must not contain a '.'");
    }
    this.category = category;
    this.code = code;
  }

  /**
   * Default constructor.
   * <p>
   * Protected since it shouldn't be used by others than JSON deserializers.
   * </p>
   */
  protected ErrorCode() {
  }

  /**
   * Utility method for creating an {@code ErrorCode}.
   *
   * @param category the category
   * @param code the code within the category
   * @return an ErrorCode
   */
  public static ErrorCode error(final String category, final String code) {
    return new ErrorCode(category, code);
  }

  /**
   * Gets the category of the error.
   *
   * @return the category
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Gets the error code within the given category.
   *
   * @return the code
   */
  public String getCode() {
    return this.code;
  }

  /**
   * Gets the error code in string representation.
   *
   * @return the error code
   */
  public String getErrorCode() {
    return ErrorCode.ERROR_CODE_PREFIX + this.category + "." + this.code;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return this.getErrorCode();
  }

  /**
   * Parses a string holding an error code into this object.
   *
   * @param errorCode the error code to parse
   * @throws IllegalArgumentException if the supplied string has an invalid error code format
   */
  private void parse(final String errorCode) throws IllegalArgumentException {
    if (!errorCode.startsWith(ErrorCode.ERROR_CODE_PREFIX)) {
      throw new IllegalArgumentException(String.format("Incorrect format on errorCode '%s'", errorCode));
    }
    final String[] parts = errorCode.split("\\.", 3);
    if (parts.length != 3) {
      throw new IllegalArgumentException(String.format("Incorrect format on errorCode '%s'", errorCode));
    }
    this.category = parts[1];
    this.code = parts[2];
  }

  /**
   * A typed category (for use in exceptions).
   */
  @AllArgsConstructor
  public static class Category {
    @Getter
    private final String category;
  }

  /**
   * A typed code (for use in exceptions).
   */
  @AllArgsConstructor
  public static class Code {
    @Getter
    private final String code;
  }

}
