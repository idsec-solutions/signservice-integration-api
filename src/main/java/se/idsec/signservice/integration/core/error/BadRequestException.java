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

import se.idsec.signservice.integration.ApiVersion;

/**
 * Class that represents a bad request to the SignService Integration Service.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class BadRequestException extends SignServiceIntegrationCategoryException {

  /** The identifier for a bad request category. */
  public static final ErrorCode.Category BAD_REQUEST_ERROR_CATEGORY = new ErrorCode.Category("bad-request");

  /** For serializing. */
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /**
   * Constructor.
   *
   * @param code the error code (within the {@link #BAD_REQUEST_ERROR_CATEGORY} category)
   * @param message the error message
   */
  public BadRequestException(final ErrorCode.Code code, final String message) {
    super(code, message);
  }

  /**
   * Constructor.
   *
   * @param code the error code (within the {@link #BAD_REQUEST_ERROR_CATEGORY} category)
   * @param message the error message
   * @param cause the cause of the error
   */
  public BadRequestException(final ErrorCode.Code code, final String message, final Throwable cause) {
    super(code, message, cause);
  }

  /** {@inheritDoc} */
  @Override
  public int getHttpStatus() {
    return 400;
  }

  /**
   * Returns {@link #BAD_REQUEST_ERROR_CATEGORY}.
   */
  @Override
  protected final ErrorCode.Category getCategory() {
    return BadRequestException.BAD_REQUEST_ERROR_CATEGORY;
  }

}
