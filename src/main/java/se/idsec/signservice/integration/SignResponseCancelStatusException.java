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
package se.idsec.signservice.integration;

import java.io.Serial;

/**
 * Dedicated exception class to represent a user cancel.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class SignResponseCancelStatusException extends SignResponseErrorStatusException {

  @Serial
  private static final long serialVersionUID = 4079776433989742973L;

  /**
   * Constructor.
   */
  public SignResponseCancelStatusException() {
    super("urn:oasis:names:tc:dss:1.0:resultmajor:ResponderError",
        "http://id.elegnamnden.se/sig-status/1.0/user-cancel",
        "User cancelled signature operation");
  }

}
