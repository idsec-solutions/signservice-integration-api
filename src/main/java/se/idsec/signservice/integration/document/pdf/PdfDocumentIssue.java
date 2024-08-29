package se.idsec.signservice.integration.document.pdf;

/**
 * Enumeration of PDF document issues
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public enum PdfDocumentIssue {
  /**
   * The document is unsigned but has an AcroForm that may contain user input form fields.
   * This AcroForm must be rendered and the AcroForm must be deleted before signing
   */
  ACROFORM_IN_UNSIGNED_PDF,

  /**
   * The document has an encryption dictionary.
   * No changes to the document can be saved before removing all security policies, which means deleting the encryption dictionary
   */
  ENCRYPTION_DICTIONARY

}
