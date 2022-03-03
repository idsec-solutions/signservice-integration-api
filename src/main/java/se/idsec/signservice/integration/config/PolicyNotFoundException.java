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
package se.idsec.signservice.integration.config;

import se.idsec.signservice.integration.core.error.BadRequestException;
import se.idsec.signservice.integration.core.error.ErrorCode;

/**
 * Exception class for references to a non-existing policy.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class PolicyNotFoundException extends BadRequestException {

  /** The error code for the error. */
  public static final ErrorCode.Code POLICY_NOT_FOUND_CODE = new ErrorCode.Code("missing-policy");

  /** For serializing. */
  private static final long serialVersionUID = -6006665493928493550L;

  /**
   * Constructor.
   *
   * @param message
   *          the error message
   */
  public PolicyNotFoundException(String message) {
    super(POLICY_NOT_FOUND_CODE, message);
  }

  /**
   * Constructor.
   *
   * @param message
   *          the error message
   * @param cause
   *          the cause of the error
   */
  public PolicyNotFoundException(String message, Throwable cause) {
    super(POLICY_NOT_FOUND_CODE, message, cause);
  }

  /** {@inheritDoc} */
  @Override
  public int getHttpStatus() {
    return 404;
  }

}
