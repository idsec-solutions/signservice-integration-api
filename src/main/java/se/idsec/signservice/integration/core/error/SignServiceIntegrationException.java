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

import se.idsec.signservice.integration.ApiVersion;

import java.io.Serial;

/**
 * Base class for Sign Service Integration exceptions.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public abstract class SignServiceIntegrationException extends Exception {

  /** For serializing. */
  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The error code for this exception. */
  private final ErrorCode errorCode;

  /**
   * Constructor.
   *
   * @param errorCode the error code
   * @param message the error message
   */
  public SignServiceIntegrationException(final ErrorCode errorCode, final String message) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * Constructor.
   *
   * @param errorCode the error code
   * @param message the error message
   * @param cause the cause of the error
   */
  public SignServiceIntegrationException(final ErrorCode errorCode, final String message, final Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  /**
   * Constructor.
   * <p>
   * May only be used if the subclass overrides {@link #getCategory()}.
   * </p>
   *
   * @param code the error code (within the given category)
   * @param message the error message
   */
  protected SignServiceIntegrationException(final ErrorCode.Code code, final String message) {
    super(message);
    if (this.getCategory() == null) {
      throw new IllegalArgumentException("No category configured for exception");
    }
    this.errorCode = new ErrorCode(this.getCategory().getCategory(), code.getCode());
  }

  /**
   * Constructor.
   * <p>
   * May only be used if the subclass overrides {@link #getCategory()}.
   * </p>
   *
   * @param code the error code (within the given category)
   * @param message the error message
   * @param cause the cause of the error
   */
  protected SignServiceIntegrationException(final ErrorCode.Code code, final String message, final Throwable cause) {
    super(message, cause);
    if (this.getCategory() == null) {
      throw new IllegalArgumentException("No category configured for exception");
    }
    this.errorCode = new ErrorCode(this.getCategory().getCategory(), code.getCode());
  }

  /**
   * Gets the error code for this exception
   *
   * @return the error code
   */
  public ErrorCode getErrorCode() {
    return this.errorCode;
  }

  /**
   * If the SignService Integration Service is used from a REST API, the HTTP status may be useful when reporting
   * errors.
   *
   * @return the HTTP status code that should be used when sending an error response for this exception
   */
  public abstract int getHttpStatus();

  /**
   * Override this method if a subclass is tied to a specific error category.
   *
   * @return the error category for this exception
   */
  protected ErrorCode.Category getCategory() {
    return this.errorCode != null ? new ErrorCode.Category(this.errorCode.getCategory()) : null;
  }

}
