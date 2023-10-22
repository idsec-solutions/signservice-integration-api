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
package se.idsec.signservice.integration.core.error;

import java.util.Map;

import se.idsec.signservice.integration.ApiVersion;

/**
 * Exception class for input validation errors.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class InputValidationException extends BadRequestException {

  /** The error code for this type of errors. */
  protected static final ErrorCode.Code ERROR_CODE = new ErrorCode.Code("validation");

  /** For serializing. */
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The name of the object that was validated. */
  private final String objectName;

  /** Underlying errors (field names and error messages). */
  private final Map<String, String> details;

  /**
   * Constructor taking the name of the object being validated.
   *
   * @param objectName the field name of the object being validated
   */
  public InputValidationException(final String objectName) {
    this(objectName, null, null, null);
  }

  /**
   * Constructor taking the name of the object being validated and an error message.
   *
   * @param objectName the field name of the object being validated
   * @param msg the error message
   */
  public InputValidationException(final String objectName, final String msg) {
    this(objectName, msg, null, null);
  }

  /**
   * Constructor taking the name of the object being validated and underlying errors details (field names and error
   * messages).
   *
   * @param objectName the field name of the object being validated
   * @param details underlying error information (field names and error messages)
   */
  public InputValidationException(final String objectName, final Map<String, String> details) {
    this(objectName, null, details, null);
  }

  /**
   * Constructor taking the name of the object being validated, an error message and underlying errors details (field
   * names and error messages).
   *
   * @param objectName the field name of the object being validated
   * @param msg the error message
   * @param details underlying error information (field names and error messages)
   */
  public InputValidationException(final String objectName, final String msg, final Map<String, String> details) {
    this(objectName, msg, details, null);
  }

  /**
   * Constructor taking the name of the object being validated.
   *
   * @param objectName the field name of the object being validated
   * @param cause the cause of the error
   */
  public InputValidationException(final String objectName, final Throwable cause) {
    this(objectName, null, null, cause);
  }

  /**
   * Constructor taking the name of the object being validated and an error message.
   *
   * @param objectName the field name of the object being validated
   * @param msg the error message
   * @param cause the cause of the error
   */
  public InputValidationException(final String objectName, final String msg, final Throwable cause) {
    this(objectName, msg, null, cause);
  }

  /**
   * Constructor taking the name of the object being validated and underlying errors details (field names and error
   * messages).
   *
   * @param objectName the field name of the object being validated
   * @param details underlying error information (field names and error messages)
   * @param cause the cause of the error
   */
  public InputValidationException(final String objectName, final Map<String, String> details, final Throwable cause) {
    this(objectName, null, details, cause);
  }

  /**
   * Constructor taking the name of the object being validated, an error message and underlying errors details (field
   * names and error messages).
   *
   * @param objectName the field name of the object being validated
   * @param msg the error message
   * @param details underlying error information (field names and error messages)
   * @param cause the cause of the error
   */
  public InputValidationException(final String objectName, final String msg, final Map<String, String> details,
      final Throwable cause) {
    super(ERROR_CODE, buildErrorMessage(objectName, msg, details), cause);
    this.objectName = objectName;
    this.details = details;
  }

  /**
   * Gets the name of the object that was validated.
   *
   * @return the name of the validated object
   */
  public String getObjectName() {
    return this.objectName;
  }

  /**
   * Gets the details for the validation error. The map consists of field names for the underlying object that were
   * validated and associated error messages.
   *
   * @return a map of field names and error messages, or null if no details are set
   */
  public Map<String, String> getDetails() {
    return this.details;
  }

  /**
   * Builds an error message.
   *
   * @param objectName the name of the object being validated
   * @param msg optional message
   * @param details error details for underlying errors
   * @return a formatted error message
   */
  private static String buildErrorMessage(final String objectName, final String msg,
      final Map<String, String> details) {
    final StringBuffer sb = new StringBuffer(objectName);
    sb.append(": ");
    sb.append(msg != null ? msg : "Validation error");
    if (details != null && !details.isEmpty()) {
      sb.append(". Details: ").append(details);
    }
    return sb.toString();
  }
}
