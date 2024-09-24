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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents the profile settings for how a PDF document is prepared.
 * <p>
 * See
 * {@link se.idsec.signservice.integration.ExtendedSignServiceIntegrationService#preparePdfSignaturePage(String, byte[],
 * PdfSignaturePagePreferences)}.
 * </p>
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdfPrepareSettings implements Serializable {

  @Serial
  private static final long serialVersionUID = -9090581375259732911L;

  /** A {@link PdfPrepareSettings} with default settings. */
  public static final PdfPrepareSettings DEFAULT = new PdfPrepareSettings();

  /**
   * Defines if PDF/A consistency check is done when adding a sign page to a document to be signed. If this setting is
   * enabled, and an attempt to add a sign page that is not PDF/A to a PDF/A document an error will be raised. If the
   * setting is not enabled, the document being signed will be reverted into a non-PDF/A document.<br /> The default is
   * {@code false}.
   */
  @Builder.Default
  private boolean enforcePdfaConsistency = false;

  /**
   * Defines whether the prepare PDF functions should flatten PDF acroforms in the document being signed.
   */
  @Builder.Default
  private boolean allowFlattenAcroForms = false;

  /**
   * Defines if documents with an encryption dictionary should have it removed.
   */
  @Builder.Default
  private boolean allowRemoveEncryptionDictionary = false;

  /**
   * Tells whether a PDF/A consistency check is done when adding a sign page to a document to be signed.
   *
   * @return {@code true} if checks are made, and {@code false otherwise}
   */
  public boolean isEnforcePdfaConsistency() {
    return this.enforcePdfaConsistency;
  }

  /**
   * Defines if PDF/A consistency check is done when adding a sign page to a document to be signed. If this setting is
   * enabled, and an attempt to add a sign page that is not PDF/A to a PDF/A document an error will be raised. If the
   * setting is not enabled, the document being signed will be reverted into a non-PDF/A document. The default is
   * {@code false}.
   *
   * @param enforcePdfaConsistency whether a PDF/A consistency check is done when adding a sign page to a document
   *     to be signed
   */
  public void setEnforcePdfaConsistency(final boolean enforcePdfaConsistency) {
    this.enforcePdfaConsistency = enforcePdfaConsistency;
  }

  /**
   * Tells whether the prepare PDF functions should flatten PDF acroforms in the document being signed.
   *
   * @return {@code true} if acroforms should be flattened, and {@code false} if the occurence of such a form should
   *     lead to an error
   */
  public boolean isAllowFlattenAcroForms() {
    return this.allowFlattenAcroForms;
  }

  /**
   * Assigns  whether the prepare PDF functions should flatten PDF acroforms in the document being signed. The default
   * is {@code false}.
   *
   * @param allowFlattenAcroForms {@code true} if acroforms should be flattened, and {@code false} if the occurence
   *     of such a form should lead to an error
   */
  public void setAllowFlattenAcroForms(final boolean allowFlattenAcroForms) {
    this.allowFlattenAcroForms = allowFlattenAcroForms;
  }

  /**
   * Tells if documents with an encryption dictionary should have it removed.
   *
   * @return {@code true} if encryption dictionaries should be removed from documents, and {@code false} if an error
   *     should be reported
   */
  public boolean isAllowRemoveEncryptionDictionary() {
    return this.allowRemoveEncryptionDictionary;
  }

  /**
   * Assigns if documents with an encryption dictionary should have it removed. The default is {@code false}.
   *
   * @param allowRemoveEncryptionDictionary {@code true} if encryption dictionaries should be removed from
   *     documents, and {@code false} if an error should be reported
   */
  public void setAllowRemoveEncryptionDictionary(final boolean allowRemoveEncryptionDictionary) {
    this.allowRemoveEncryptionDictionary = allowRemoveEncryptionDictionary;
  }

  /**
   * Builder for {@code PdfPrepareSettings} objects.
   */
  public static class PdfPrepareSettingsBuilder implements ObjectBuilder<PdfPrepareSettings> {
    // Lombok
  }

}
