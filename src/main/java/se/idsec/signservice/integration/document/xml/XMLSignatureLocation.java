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
package se.idsec.signservice.integration.document.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.ToString;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;

/**
 * Tells where in an XML document the signature should be inserted. The default is to add it as the last child of the
 * document root element.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class XMLSignatureLocation implements Extensible {

  @Serial
  private static final long serialVersionUID = 8022462697014173727L;

  /** Indicator for first or last child of a selected parent node. */
  private String childPosition;

  /**
   * The XPath expression for selecting the parent node (or {@code null} which means the parent node is the document
   * root element).
   */
  private String xPath;

  /** Extensions for the object. */
  private Extension extension;

  /**
   * Default constructor.
   */
  public XMLSignatureLocation() {
  }

  /**
   * Constructor.
   *
   * @param childPosition indicator for first or last child of a selected parent node
   * @param xPath the XPath expression for selecting the parent node
   * @param extension extensions for the object
   */
  public XMLSignatureLocation(final String childPosition, final String xPath, final Extension extension) {
    this.childPosition = childPosition;
    this.xPath = xPath;
    this.extension = extension;
  }

  /**
   * Gets the indicator for first or last child of a selected parent node.
   *
   * @return the position (first of last)
   */
  public String getChildPosition() {
    return this.childPosition;
  }

  /**
   * Sets the position in the selected parent node where the signature element should be installed (first or last). The
   * default is "last".
   *
   * @param childPosition the position
   */
  public void setChildPosition(final String childPosition) {
    this.childPosition = childPosition != null ? ChildPosition.fromPosition(childPosition).getPosition() : null;
  }

  /**
   * Sets the position in the selected parent node where the signature element should be installed (first or last). The
   * default is "last".
   *
   * @param childPosition the position
   */
  public void setChildPosition(final ChildPosition childPosition) {
    this.childPosition = childPosition != null ? childPosition.getPosition() : null;
  }

  /**
   * Gets the XPath expression for selecting the parent node (or {@code null} which means the parent node is the
   * document root element).
   *
   * @return the XPath expression for locating the parent node of the Signature element
   */
  public String getxPath() {
    return this.xPath;
  }

  /**
   * Assigns the XPath expression for selecting the parent node (or {@code null} which means the parent node is the
   * document root element).
   *
   * @param xPath the XPath expression for locating the parent node of the Signature element
   */
  public void setxPath(final String xPath) {
    this.xPath = xPath;
  }

  /** {@inheritDoc} */
  @Override
  public Extension getExtension() {
    return this.extension;
  }

  /** {@inheritDoc} */
  @Override
  public void setExtension(final Extension extension) {
    this.extension = extension;
  }

  /**
   * Enum for indicating the insertion point within a selected parent node.
   */
  public enum ChildPosition {

    /** Insert the signature as the first child of the selected parent node. */
    FIRST("first"),

    /** Insert the signature as the last child of the selected parent node. */
    LAST("last");

    /**
     * Gets the position in its string representation.
     *
     * @return the position
     */
    public String getPosition() {
      return this.position;
    }

    /**
     * Given a position in its string representation the method returns the corresponding enum constant.
     *
     * @param position the position in its string representation
     * @return the enum constant value
     * @throws IllegalArgumentException if no matching enum constant is found
     */
    public static ChildPosition fromPosition(final String position) throws IllegalArgumentException {
      for (final ChildPosition p : ChildPosition.values()) {
        if (p.getPosition().equalsIgnoreCase(position)) {
          return p;
        }
      }
      throw new IllegalArgumentException("%s is not a supported ChildPosition".formatted(position));
    }

    /**
     * Constructor.
     *
     * @param position the position
     */
    ChildPosition(final String position) {
      this.position = position;
    }

    /** String representation of the position. */
    private final String position;
  }

  /**
   * Builder for {@link XMLSignatureLocation} objects.
   */
  public static class XMLSignatureLocationBuilder implements ObjectBuilder<XMLSignatureLocation> {
    // Lombok

    public XMLSignatureLocationBuilder childPosition(final String childPosition) {
      this.childPosition = childPosition;
      return this;
    }

    public XMLSignatureLocationBuilder childPosition(final ChildPosition childPosition) {
      this.childPosition = childPosition != null ? childPosition.getPosition() : null;
      return this;
    }
  }

}
