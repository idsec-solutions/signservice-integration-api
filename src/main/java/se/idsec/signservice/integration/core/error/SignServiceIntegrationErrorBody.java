/*
 * Copyright 2019-2020 IDsec Solutions AB
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code SignServiceIntegrationErrorBody} is a representation of a SignService Integration error message when the
 * service is implemented as a REST service.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@JsonInclude(Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignServiceIntegrationErrorBody {

  /**
   * The HTTP status code.
   * 
   * @param status
   *          the HTTP status code
   * @return the HTTP status code
   */
  private int status;

  /**
   * The timestamp as milliseconds since epoch.
   * 
   * @param timestamp
   *          the timestamp for the error
   * @return the timestamp for the error
   */
  private long timestamp;

  /**
   * The error code.
   * 
   * @param errorCode
   *          the error code
   * @return the error code
   */
  private String errorCode;

  /**
   * Validation error details. Only set if the error was due to an input validation failure.
   * 
   * @param validationError
   *          the validation error information
   * @return the validation error information
   */
  private ValidationError validationError;

  /**
   * DSS error details.
   * 
   * @param dssError
   *          DSS error details
   * @return DSS error details
   */
  private DssError dssError;

  /**
   * The error message.
   * 
   * @param message
   *          the error message
   * @return the error message
   */
  private String message;

  /**
   * The requested path.
   * 
   * @param the
   *          requested path
   * @return the requested path
   */
  private String path;

  /**
   * The name of the exception class that was thrown for this error.
   * 
   * @param exceptionClass
   *          exception class
   * @return exception class
   */
  private String exceptionClass;

  /**
   * Representation of validation errors. See {@link InputValidationException}.
   */
  @JsonInclude(Include.NON_NULL)
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ValidationError {

    /**
     * The name of the object that validation failed for.
     * 
     * @param object
     *          object name
     * @return the object name
     */
    private String object;

    /**
     * Underlying errors (field names and error messages).
     * 
     * @param details
     *          map of underlying errors
     * @return map of underlying errors
     */
    private Map<String, String> details;
  }

  /**
   * For DSS errors.
   */
  @JsonInclude(Include.NON_NULL)
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DssError {

    /**
     * The DSS major result code.
     * 
     * @param majorCode
     *          the DSS major result code
     * @return the DSS major result code
     */
    private String majorCode;

    /**
     * The DSS minor result code.
     * 
     * @param minorCode
     *          the DSS minor result code
     * @return the DSS minor result code
     */
    private String minorCode;
  }

}
