/*
 * Copyright 2019 IDsec Solutions AB
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
package se.idsec.signservice.integration.document;

/**
 * An enum that is used when stating how an XML signed document should be compiled.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public enum SignatureDestinationType {

  /** 
   * XPath expression for the parent node under which the signature should be inserted.  
   */
  XML_PARENT_XPATH,
  
  /** 
   * The value "first" or "last" to specify the signature node location within the parent node. 
   */
  XML_NODE_LOCATION,
  
  /** A Base64 encoded XML Schema for the signed XML document */
  XML_SCHEMA,
  
  /** An XML Schema name space URI for the signed XML document */
  XML_SCHEMA_NS;
  
}
