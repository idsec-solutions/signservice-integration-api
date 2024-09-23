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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PdfPrepareReport {

  /** The actions that were completed during the prepare operation. */
  private List<PrepareActions> actions;

  /** The warnings detected during the prepare operation. */
  private List<PrepareWarnings> warnings;

  /**
   * Gets the actions that were completed during the prepare operation.
   *
   * @return a list of completed actions
   */
  @Nullable
  public List<PrepareActions> getActions() {
    return this.actions;
  }

  /**
   * Assigns the actions that were completed during the prepare operation.
   *
   * @param actions a list of completed actions
   */
  public void setActions(@Nonnull final List<PrepareActions> actions) {
    this.actions = actions;
  }

  /**
   * Gets the warnings detected during the prepare operation.
   *
   * @return a list of warnings
   */
  @Nullable
  public List<PrepareWarnings> getWarnings() {
    return this.warnings;
  }

  /**
   * Assigns warnings detected during the prepare operation.
   *
   * @param warnings a list of warnings
   */
  public void setWarnings(@Nonnull final List<PrepareWarnings> warnings) {
    this.warnings = warnings;
  }

  /**
   * Enumeration of prepare actions that were completed by the prepare method.
   */
  public enum PrepareActions {

    /**
     * The document was unsigned but had an AcroForm with user input form fields. This AcroForm was flattened.
     */
    FLATTENED_ACROFORM("flattened-acroform"),

    /**
     * The document contained an encryption dictionary. This was removed.
     */
    REMOVED_ENCRYPTION_DICTIONARY("removed-encryption-dictionary");

    private final String value;

    PrepareActions(final String value) {
      this.value = value;
    }

    /**
     * Gets the string value.
     *
     * @return the string value
     */
    @JsonValue
    public String getValue() {
      return this.value;
    }

    /**
     * Creates a {@link PrepareActions} from its string value.
     *
     * @param value the string value
     * @return a {@link PrepareActions}
     */
    @JsonCreator
    public static PrepareActions fromValue(final String value) {
      for (final PrepareActions issue : PrepareActions.values()) {
        if (issue.getValue().equals(value)) {
          return issue;
        }
      }
      throw new IllegalArgumentException("Unknown value: " + value);
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * Enumeration of warnings found during the prepare operation.
   */
  public enum PrepareWarnings {

    /**
     * The document is PDF/A, but the sign page is not.
     */
    PDFA_INCONSISTENCY("pdfa-inconsistency");

    private final String value;

    PrepareWarnings(final String value) {
      this.value = value;
    }

    /**
     * Gets the string value.
     *
     * @return the string value
     */
    @JsonValue
    public String getValue() {
      return this.value;
    }

    /**
     * Creates a {@link PrepareWarnings} from its string value.
     *
     * @param value the string value
     * @return a {@link PrepareWarnings}
     */
    @JsonCreator
    public static PrepareWarnings fromValue(final String value) {
      for (final PrepareWarnings issue : PrepareWarnings.values()) {
        if (issue.getValue().equals(value)) {
          return issue;
        }
      }
      throw new IllegalArgumentException("Unknown value: " + value);
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

}
