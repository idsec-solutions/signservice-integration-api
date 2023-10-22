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

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import se.idsec.signservice.integration.ApiVersion;

/**
 * The {@code SignServiceIntegrationErrorBody} is a representation of a SignService Integration error message when the
 * service is implemented as a REST service.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@JsonInclude(Include.NON_NULL)
@Builder
public class SignServiceIntegrationErrorBody implements Serializable {

  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The HTTP status code. */
  private int status;

  /** The timestamp as milliseconds since epoch. */
  private long timestamp;

  /** The error code. */
  private String errorCode;

  /** Validation error details. */
  private ValidationError validationError;

  /** DSS error details. */
  private DssError dssError;

  /** The error message. */
  private String message;

  /** The requested path. */
  private String path;

  /** The name of the exception class that was thrown for this error. */
  private String exceptionClass;

  /**
   * Default constructor.
   */
  public SignServiceIntegrationErrorBody() {
  }

  /**
   * All-args constructor.
   *
   * @param status the HTTP status code
   * @param timestamp the timestamp as milliseconds since epoch
   * @param errorCode the error code
   * @param validationError validation error details
   * @param dssError the DSS error details
   * @param message the error message
   * @param path the requested path
   * @param exceptionClass the name of the exception class that was thrown for this error
   */
  public SignServiceIntegrationErrorBody(final int status, final long timestamp, final String errorCode,
      final ValidationError validationError, final DssError dssError, final String message, final String path,
      final String exceptionClass) {
    this.status = status;
    this.timestamp = timestamp;
    this.errorCode = errorCode;
    this.validationError = validationError;
    this.dssError = dssError;
    this.message = message;
    this.path = path;
    this.exceptionClass = exceptionClass;
  }

  /**
   * Gets the HTTP status code.
   *
   * @return the HTTP status code
   */
  public int getStatus() {
    return this.status;
  }

  /**
   * Assigns the HTTP status code.
   *
   * @param status the HTTP status code
   */
  public void setStatus(final int status) {
    this.status = status;
  }

  /**
   * Gets the timestamp as milliseconds since epoch.
   *
   * @return the timestamp for the error
   */
  public long getTimestamp() {
    return this.timestamp;
  }

  /**
   * Assigns the timestamp as milliseconds since epoch.
   *
   * @param timestamp the timestamp for the error
   */
  public void setTimestamp(final long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the error code.
   *
   * @return the error code
   */
  public String getErrorCode() {
    return this.errorCode;
  }

  /**
   * Assigns the error code.
   *
   * @param errorCode the error code
   */
  public void setErrorCode(final String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Gets the validation error details.
   *
   * @return the validation error information
   */
  public ValidationError getValidationError() {
    return this.validationError;
  }

  /**
   * Assigns the validation error details. Only set if the error was due to an input validation failure.
   *
   * @param validationError the validation error information
   */
  public void setValidationError(final ValidationError validationError) {
    this.validationError = validationError;
  }

  /**
   * Gets the DSS error details.
   *
   * @return DSS error details
   */
  public DssError getDssError() {
    return this.dssError;
  }

  /**
   * Assigns the DSS error details.
   *
   * @param dssError DSS error details
   */
  public void setDssError(final DssError dssError) {
    this.dssError = dssError;
  }

  /**
   * Gets the error message.
   *
   * @return the error message
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * Assigns the error message.
   *
   * @param message the error message
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * Gets the requested path.
   *
   * @return the requested path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Assigns the requested path.
   *
   * @param path the requested path
   */
  public void setPath(final String path) {
    this.path = path;
  }

  /**
   * Gets the name of the exception class that was thrown for this error.
   *
   * @return exception class
   */
  public String getExceptionClass() {
    return this.exceptionClass;
  }

  /**
   * Assigns the name of the exception class that was thrown for this error.
   *
   * @param exceptionClass exception class
   */
  public void setExceptionClass(final String exceptionClass) {
    this.exceptionClass = exceptionClass;
  }

  /**
   * Representation of validation errors. See {@link InputValidationException}.
   */
  @JsonInclude(Include.NON_NULL)
  @Builder
  public static class ValidationError implements Serializable {

    private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

    /** The name of the object that validation failed for. */
    private String object;

    /** Underlying errors (field names and error messages). */
    private Map<String, String> details;

    /**
     * Default constructor.
     */
    public ValidationError() {
    }

    /**
     * Constructor.
     *
     * @param object name of the object that validation failed for
     * @param details underlying errors (field names and error messages)
     */
    public ValidationError(final String object, final Map<String, String> details) {
      this.object = object;
      this.details = details;
    }

    /**
     * Gets the name of the object that validation failed for.
     *
     * @return the object name
     */
    public String getObject() {
      return this.object;
    }

    /**
     * Assigns the name of the object that validation failed for.
     *
     * @param object object name
     */
    public void setObject(final String object) {
      this.object = object;
    }

    /**
     * Gets the underlying errors (field names and error messages).
     *
     * @return map of underlying errors
     */
    public Map<String, String> getDetails() {
      return this.details;
    }

    /**
     * Assigns the underlying errors (field names and error messages).
     *
     * @param details map of underlying errors
     */
    public void setDetails(final Map<String, String> details) {
      this.details = details;
    }

  }

  /**
   * For DSS errors.
   */
  @JsonInclude(Include.NON_NULL)
  @Builder
  public static class DssError implements Serializable {

    private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

    /** The DSS major result code. */
    private String majorCode;

    /** The DSS minor result code. */
    private String minorCode;

    /**
     * Default constructor.
     */
    public DssError() {
    }

    /**
     * Constructor.
     *
     * @param majorCode the DSS major result code
     * @param minorCode the DSS minor result code
     */
    public DssError(final String majorCode, final String minorCode) {
      this.majorCode = majorCode;
      this.minorCode = minorCode;
    }

    /**
     * Gets the DSS major result code.
     *
     * @return the DSS major result code
     */
    public String getMajorCode() {
      return this.majorCode;
    }

    /**
     * Assigns the DSS major result code.
     *
     * @param majorCode the DSS major result code
     */
    public void setMajorCode(final String majorCode) {
      this.majorCode = majorCode;
    }

    /**
     * Gets the DSS minor result code.
     *
     * @return the DSS minor result code
     */
    public String getMinorCode() {
      return this.minorCode;
    }

    /**
     * Assigns the DSS minor result code.
     *
     * @param minorCode the DSS minor result code
     */
    public void setMinorCode(final String minorCode) {
      this.minorCode = minorCode;
    }

  }

}
