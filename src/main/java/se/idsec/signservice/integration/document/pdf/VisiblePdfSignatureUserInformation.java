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
package se.idsec.signservice.integration.document.pdf;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import se.idsec.signservice.integration.authentication.AuthnRequirements;
import se.idsec.signservice.integration.authentication.SignerIdentityAttribute;
import se.idsec.signservice.integration.core.Extensible;
import se.idsec.signservice.integration.core.Extension;
import se.idsec.signservice.integration.core.ObjectBuilder;

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
public class VisiblePdfSignatureUserInformation implements Extensible {

  /**
   * Name of the signer to be represented in the visible image. This is typically a name of the signer but any suitable
   * identity attribute value may be specified to be part of the signer name. This value is analogous to, and should
   * hold the same value as, a present Name entry in the PDF signature dictionary. If the image template referenced
   * requires a value for signerName, this field is mandatory, otherwise it is optional.
   * 
   * @param signerName
   *          the signer name
   * @return the signer name
   */
  @Getter
  @Setter
  private SignerName signerName;

  /**
   * Apart from the signer name and signing date, a template may use other fields. This map provides the requested
   * fields and values.
   * 
   * @param fieldValues
   *          a map of fields and their values
   * @return a map of fields and their values
   */
  @Getter
  @Setter
  @Singular
  private Map<String, String> fieldValues;

  /** Extensions for the object. */
  protected Extension extension;

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
  public static class VisiblePdfSignatureUserInformationBuilder implements ObjectBuilder<VisiblePdfSignatureUserInformation> {
    // Lombok
  }

  /**
   * Class representing the input needed to display the signer name in a visible PDF signature.
   */
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignerName {

    /**
     * A list of attribute names that refer to some, or all, attributes supplied in
     * {@link AuthnRequirements#setRequestedSignerAttributes(List)} that are the requirements that the signer requires
     * to be validated as part of the signature operation. The values of the given attributes will be part of the signer
     * name field as they appear in the list (separated by a blank). It is possible to change how the information is
     * displayed by assigning a formatting, see {@link #setFormatting(String)}.
     * 
     * @param signerAttributes
     *          a list of attribute names whose values should be used to display the signer name in the visible PDF
     *          signature
     * @return a list of attribute names whose values should be used to display the signer name in the visible PDF
     *         signature
     */
    @Getter
    @Setter
    @Singular
    private List<SignerIdentityAttribute> signerAttributes;

    /**
     * Optional string the may be supplied to change how the signer's information is displayed. Each list item from
     * {@link #getSignerAttributes()} is referenced by its order (starting from 0) and prefixed by %. For example, the
     * formatting string {@code "%1 %2 (%3)"} may display something like {@code Jim Smith (ID12345)}.
     * 
     * @param formatting
     *          the formatting string
     * @return the formatting string
     */
    @Getter
    @Setter
    private String formatting;

    /**
     * Builder for {@code SignerName} objects.
     */
    public static class SignerNameBuilder implements ObjectBuilder<SignerName> {
      // Lombok
    }

  }
}
