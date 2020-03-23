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
package se.idsec.signservice.integration;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.SignerAssertionInformation;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;
import se.idsec.signservice.integration.document.SignedDocument;

/**
 * Representation of the result of a signature operation.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignatureResult implements Extensible {

  /**
   * The ID for the signature operation. This ID corresponds to the RequestID of the SignRequest and SignResponse.
   * 
   * @param id
   *          the operation ID
   * @return the operation ID
   */
  @Setter
  @Getter
  private String id;

  /**
   * The correlation ID. This is the ID that is used in all logging events.
   * 
   * @param correlationId
   *          the correlationId to use for this process
   * @return the correlation ID
   */
  @Setter
  @Getter
  private String correlationId;

  /**
   * The signed documents.
   * 
   * @param signedDocuments
   *          the signed documents
   * @return the signed documents
   */
  @Setter
  @Getter
  @Singular
  private List<SignedDocument> signedDocuments;

  /**
   * Contains information about the signer's "authentication for signature" that was part of the signature operation.
   * 
   * @param signerAssertionInformation
   *          assertion information
   * @return the assertion information
   */
  @Setter
  @Getter
  private SignerAssertionInformation signerAssertionInformation;

  /**
   * Extensions for the object.
   * 
   * @param extension
   *          the extension object to assign
   */
  @Setter
  private Extension extension;

  /** {@inheritDoc} */
  @Override
  public Extension getExtension() {
    return this.extension;
  }

  /**
   * Builder for {@code SignatureResult} objects.
   */
  public static class SignatureResultBuilder implements ObjectBuilder<SignatureResult> {
    // Lombok
  }
}
