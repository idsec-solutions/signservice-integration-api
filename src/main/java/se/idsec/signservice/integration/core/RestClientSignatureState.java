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
package se.idsec.signservice.integration.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import se.idsec.signservice.integration.ApiVersion;

/**
 * When the Sign Service Integration service is running as a REST service the clients need to have a concrete class so
 * that JSON deserialization will work. This class provides this.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@JsonInclude(Include.NON_NULL)
public class RestClientSignatureState implements SignatureState {

  /** For serializing. */
  @Serial
  private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

  /** The signature operation ID. */
  private String id;

  /** The session state. */
  private LinkedHashMap<String, ?> state;

  /**
   * Default constructor.
   */
  public RestClientSignatureState() {
  }

  /** {@inheritDoc} */
  @Override
  public String getId() {
    return this.id;
  }

  /**
   * Assigns the unique identifier for the signature operation.
   *
   * @param id
   *          signature operation ID
   */
  public void setId(final String id) {
    this.id = id;
  }

  /** {@inheritDoc} */
  @Override
  public Serializable getState() {
    return this.state;
  }

  /**
   * Assigns the session state.
   *
   * @param state
   *          the session state
   */
  public void setState(final LinkedHashMap<String, ?> state) {
    this.state = state;
  }

}
