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
package se.idsec.signservice.integration.core.error;

import java.io.Serial;

/**
 * Abstract class for exception that reports errors for a given category. The {@link #getCategory()} method must be
 * overridden.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public abstract class SignServiceIntegrationCategoryException extends SignServiceIntegrationException {

  @Serial
  private static final long serialVersionUID = 1129970870457977277L;

  /**
   * Constructor.
   *
   * @param code the error code (within the given category)
   * @param message the error message
   */
  public SignServiceIntegrationCategoryException(final ErrorCode.Code code, final String message) {
    super(code, message);
  }

  /**
   * Constructor.
   *
   * @param code the error code (within the given category)
   * @param message the error message
   * @param cause the cause of the error
   */
  public SignServiceIntegrationCategoryException(
      final ErrorCode.Code code, final String message, final Throwable cause) {
    super(code, message, cause);
  }

}
