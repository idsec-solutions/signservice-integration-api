/*
 * Copyright 2023 IDsec Solutions AB
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * Test cases for ApiVersion.
 *
 * @author Martin Lindström
 */
public class ApiVersionTest {

  private String version;

  public ApiVersionTest() throws Exception {
    final Properties properties = new Properties();
    properties.load(this.getClass().getClassLoader().getResourceAsStream("version.properties"));

    this.version = properties.getProperty("api.version");
    if (this.version.endsWith("-SNAPSHOT")) {
      this.version = this.version.substring(0, this.version.length() - 9);
    }
  }

  @Test
  public void testUid() {
    Assertions.assertEquals(this.version.hashCode(), ApiVersion.SERIAL_VERSION_UID);
  }

  @Test
  public void testVersion() {
    Assertions.assertEquals(this.version, ApiVersion.getVersion(),
        "Expected LibraryVersion.getVersion() to return " + this.version);
  }

}
