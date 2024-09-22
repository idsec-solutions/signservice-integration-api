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
 * Exception class to report that the caller does not have access on a given resource.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class NoAccessException extends SignServiceIntegrationException {

  /** For serialization. */
  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The error code for this type of error. */
  public static final ErrorCode ERROR_CODE = new ErrorCode("security", "no-access");

  /**
   * Constructor assigning the error message.
   *
   * @param message the error message
   */
  public NoAccessException(final String message) {
    super(ERROR_CODE, message);
  }

  /**
   * Constructor assigning the error message and the cause of the error.
   *
   * @param message the error message
   * @param cause the cause of the error
   */
  public NoAccessException(final String message, final Throwable cause) {
    super(ERROR_CODE, message, cause);
  }

  /** {@inheritDoc} */
  @Override
  public int getHttpStatus() {
    return 403;
  }

}
