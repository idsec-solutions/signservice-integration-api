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
package se.idsec.signservice.integration.document.pdf;

import se.idsec.signservice.integration.ExtendedSignServiceIntegrationService;
import se.idsec.signservice.integration.core.error.ErrorCode;
import se.idsec.signservice.integration.core.error.SignServiceIntegrationException;

import java.io.Serial;

/**
 * An exception that is reported by
 * {@link ExtendedSignServiceIntegrationService#preparePdfDocument(String, byte[], PdfSignaturePagePreferences,
 * Boolean)} if a sign page that is not PDF/A is added to a PDF/A document.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class PdfAConsistencyCheckException extends SignServiceIntegrationException {

  /** For serializing. */
  @Serial
  private static final long serialVersionUID = 8421167955748866207L;

  /** The error code for this exception. */
  public static final ErrorCode errorCode = ErrorCode.error("document", "pdfa-consistency-check-failed");

  /**
   * Constructor.
   *
   * @param message the error message
   */
  public PdfAConsistencyCheckException(final String message) {
    super(errorCode, message);
  }

  /**
   * Constructor.
   *
   * @param message the error message
   * @param cause the cause of the error
   */
  public PdfAConsistencyCheckException(final String message, final Throwable cause) {
    super(errorCode, message, cause);
  }

  /** {@inheritDoc} */
  @Override
  public int getHttpStatus() {
    return 403;
  }

}
