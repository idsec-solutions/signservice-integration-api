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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.AuthnRequirements;
import se.idsec.signservice.integration.authentication.SignerIdentityAttribute;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Representation of the user information that is injected into a PDF signature image.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@ToString
@Builder(builderMethodName = "toBuilder")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class VisiblePdfSignatureUserInformation implements Extensible, Serializable {

  @Serial
  private static final long serialVersionUID = 8530156355878335379L;

  /**
   * Name of the signer to be represented in the visible image. This is typically a name of the signer but any suitable
   * identity attribute value may be specified to be part of the signer name. This value is analogous to, and should
   * hold the same value as, a present Name entry in the PDF signature dictionary. If the image template referenced
   * requires a value for signerName, this field is mandatory, otherwise it is optional.
   */
  private SignerName signerName;

  /**
   * Apart from the signer name and signing date, a template may use other fields. This map provides the requested
   * fields and values.
   */
  @Singular
  private Map<String, String> fieldValues;

  /** Extensions for the object. */
  protected Extension extension;

  /**
   * Gets the name of the signer to be represented in the visible image. This is typically a name of the signer but any
   * suitable identity attribute value may be specified to be part of the signer name. This value is analogous to, and
   * should hold the same value as, a present Name entry in the PDF signature dictionary. If the image template
   * referenced requires a value for signerName, this field is mandatory, otherwise it is optional.
   *
   * @return the signer name
   */
  public SignerName getSignerName() {
    return this.signerName;
  }

  /**
   * Assigns the name of the signer to be represented in the visible image. This is typically a name of the signer but
   * any suitable identity attribute value may be specified to be part of the signer name. This value is analogous to,
   * and should hold the same value as, a present Name entry in the PDF signature dictionary. If the image template
   * referenced requires a value for signerName, this field is mandatory, otherwise it is optional.
   *
   * @param signerName the signer name
   */
  public void setSignerName(final SignerName signerName) {
    this.signerName = signerName;
  }

  /**
   * Apart from the signer name and signing date, a template may use other fields. This map provides the requested
   * fields and values.
   *
   * @return a map of fields and their values
   */
  public Map<String, String> getFieldValues() {
    return this.fieldValues;
  }

  /**
   * Apart from the signer name and signing date, a template may use other fields. This map provides the requested
   * fields and values.
   *
   * @param fieldValues a map of fields and their values
   */
  public void setFieldValues(final Map<String, String> fieldValues) {
    this.fieldValues = fieldValues;
  }

  /** {@inheritDoc} */
  @Override
  public void setExtension(final Extension extension) {
    this.extension = extension;
  }

  /** {@inheritDoc} */
  @Override
  public Extension getExtension() {
    return this.extension;
  }

  /**
   * Builder for {@code VisiblePdfSignatureUserInformation} objects.
   */
  public static class VisiblePdfSignatureUserInformationBuilder
      implements ObjectBuilder<VisiblePdfSignatureUserInformation> {
    // Lombok
  }

  /**
   * Class representing the input needed to display the signer name in a visible PDF signature.
   */
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignerName implements Serializable {

    /** For serialization. */
    @Serial
    private static final long serialVersionUID = -3259891506238725426L;

    /**
     * A list of attribute names that refer to some, or all, attributes supplied in
     * {@link AuthnRequirements#setRequestedSignerAttributes(List)} that are the requirements that the signer requires
     * to be validated as part of the signature operation. The values of the given attributes will be part of the signer
     * name field as they appear in the list (separated by a blank). It is possible to change how the information is
     * displayed by assigning a formatting, see {@link #setFormatting(String)}.
     */
    @Singular
    private List<SignerIdentityAttribute> signerAttributes;

    /**
     * Optional string the may be supplied to change how the signer's information is displayed. Each list item from
     * {@link #getSignerAttributes()} is referenced by its order (starting from 0) and prefixed by %. For example, the
     * formatting string {@code "%1 %2 (%3)"} may display something like {@code Jim Smith (ID12345)}.
     */
    private String formatting;

    /**
     * Gets the list of attribute names that refer to some, or all, attributes supplied in
     * {@link AuthnRequirements#setRequestedSignerAttributes(List)} that are the requirements that the signer requires
     * to be validated as part of the signature operation. The values of the given attributes will be part of the signer
     * name field as they appear in the list (separated by a blank). It is possible to change how the information is
     * displayed by assigning a formatting, see {@link #setFormatting(String)}.
     *
     * @return a list of attribute names whose values should be used to display the signer name in the visible PDF
     *     signature
     */
    public List<SignerIdentityAttribute> getSignerAttributes() {
      return this.signerAttributes;
    }

    /**
     * Assigns the list of attribute names that refer to some, or all, attributes supplied in
     * {@link AuthnRequirements#setRequestedSignerAttributes(List)} that are the requirements that the signer requires
     * to be validated as part of the signature operation. The values of the given attributes will be part of the signer
     * name field as they appear in the list (separated by a blank). It is possible to change how the information is
     * displayed by assigning a formatting, see {@link #setFormatting(String)}.
     *
     * @param signerAttributes a list of attribute names whose values should be used to display the signer name in
     *     the visible PDF signature
     */
    public void setSignerAttributes(final List<SignerIdentityAttribute> signerAttributes) {
      this.signerAttributes = signerAttributes;
    }

    /**
     * Gets the string the may be supplied to change how the signer's information is displayed. Each list item from
     * {@link #getSignerAttributes()} is referenced by its order (starting from 0) and prefixed by %. For example, the
     * formatting string {@code "%1 %2 (%3)"} may display something like {@code Jim Smith (ID12345)}.
     *
     * @return the formatting string or null
     */
    public String getFormatting() {
      return this.formatting;
    }

    /**
     * Assigns the string the may be supplied to change how the signer's information is displayed. Each list item from
     * {@link #getSignerAttributes()} is referenced by its order (starting from 0) and prefixed by %. For example, the
     * formatting string {@code "%1 %2 (%3)"} may display something like {@code Jim Smith (ID12345)}.
     *
     * @param formatting the formatting string
     */
    public void setFormatting(final String formatting) {
      this.formatting = formatting;
    }

    /**
     * Builder for {@code SignerName} objects.
     */
    public static class SignerNameBuilder implements ObjectBuilder<SignerName> {
      // Lombok
    }

  }
}
