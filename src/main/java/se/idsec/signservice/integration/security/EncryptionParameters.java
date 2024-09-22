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
package se.idsec.signservice.integration.security;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.idsec.signservice.integration.ApiVersion;

/**
 * Representation of encryption algorithms to be used when encrypting a sign message for an Identity Provider.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public interface EncryptionParameters extends Serializable {

  /**
   * Returns the data encryption algorithm that should be used when encrypting the sign message for a given Identity
   * Provider.
   *
   * @return the data encryption algorithm
   */
  String getDataEncryptionAlgorithm();

  /**
   * Returns the key transport encryption algorithm that should be used when encrypting the sign message for a given
   * Identity Provider.
   *
   * @return the key transport encryption algorithm
   */
  String getKeyTransportEncryptionAlgorithm();

  /**
   * If {@link #getKeyTransportEncryptionAlgorithm()} returns {@code http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p} or
   * {@code http://www.w3.org/2009/xmlenc11#rsa-oaep}, this method returns the RSA OAEP parameters to use.
   *
   * @return the RSA OAEP parameters, or {@code null} if {@link #getDataEncryptionAlgorithm()} does not return a RSA
   *           OEAP algorithm
   */
  RSAOAEPParameters getRsaOaepParameters();

  /**
   * Representation of parameters for RSA OAEP key transport algorithm(s).
   */
  @Builder
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  @JsonInclude(Include.NON_NULL)
  class RSAOAEPParameters implements Serializable {

    @Serial
    private static final long serialVersionUID = ApiVersion.SERIAL_VERSION_UID;

    /** Digest method algorithm URI. */
    private String digestMethod;

    /** Mask generation function (MGF) algorithm URI. */
    private String maskGenerationFunction;

    /** Base64-encoded OAEPParams value. */
    private String oaepParams;
  }

}
