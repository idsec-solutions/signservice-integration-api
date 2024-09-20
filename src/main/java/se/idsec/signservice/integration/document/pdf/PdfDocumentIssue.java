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

/**
 * Enumeration of PDF document issues.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public enum PdfDocumentIssue {

  /**
   * The document is unsigned but has an AcroForm that may contain user input form fields. This AcroForm must be
   * rendered and the AcroForm must be deleted before signing.
   */
  ACROFORM_IN_UNSIGNED_PDF,

  /**
   * The document has an encryption dictionary. No changes to the document can be saved before removing all security
   * policies, which means deleting the encryption dictionary.
   */
  ENCRYPTION_DICTIONARY

}
