/*
 * Copyright 2019-2022 IDsec Solutions AB
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
package se.idsec.signservice.integration.core;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Test cases for FileResource.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class FileResourceTest {

  private static String stringContents = "For testing DefaultContentLoader";

  private static String expectedContents = Base64.getEncoder().encodeToString(
    stringContents.getBytes(StandardCharsets.UTF_8));

  @Test
  public void testSetContents() throws Exception {
    FileResource fr = new FileResource();
    fr.setContents(stringContents.getBytes(StandardCharsets.UTF_8));
    Assert.assertEquals(expectedContents, fr.getContents());

    fr.setContents(expectedContents);
    Assert.assertEquals(expectedContents, fr.getContents());
  }

  @Test
  public void testEagerlyLoadContentsFalse() throws Exception {
    FileResource fr = FileResource.builder()
      .resource("classpath:testfile.txt")
      .eagerlyLoadContents(false)
      .build();
    fr.afterPropertiesSet();

    // Assert that the contents is not set.
    Field contentsField = fr.getClass().getDeclaredField("contents");
    contentsField.setAccessible(true);
    Assert.assertNull(contentsField.get(fr));

    // Assert the the contents is delivered upon request
    Assert.assertEquals(expectedContents, fr.getContents());

    // Again, assert that it is not saved
    Assert.assertNull(contentsField.get(fr));
  }

  @Test
  public void testEagerlyLoadContentsTrue() throws Exception {
    FileResource fr = FileResource.builder()
      .resource("classpath:testfile.txt")
      .eagerlyLoadContents(true)
      .build();
    fr.afterPropertiesSet();

    // Assert that the contents is set.
    Field contentsField = fr.getClass().getDeclaredField("contents");
    contentsField.setAccessible(true);
    Assert.assertEquals(expectedContents, contentsField.get(fr));
    Assert.assertEquals(expectedContents, fr.getContents());

    // Also verify that things work even if afterPropertiesSet isn't called
    fr = FileResource.builder()
      .resource("classpath:testfile.txt")
      .eagerlyLoadContents(true)
      .build();

    // Not available yet ...
    Assert.assertNull(contentsField.get(fr));

    // getContents delivers the contents
    Assert.assertEquals(expectedContents, fr.getContents());

    // And since it should be saved, it's in the property as well
    Assert.assertEquals(expectedContents, contentsField.get(fr));
  }

  @Test
  public void testJson() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

    FileResource fr = FileResource.builder()
      .contents(expectedContents)
      .description("Test")
      .build();

    String json = writer.writeValueAsString(fr);

    FileResource fr2 = mapper.readValue(json, FileResource.class);
    Assert.assertNotNull(fr2);
    Assert.assertEquals(fr.getContents(), fr2.getContents());
    Assert.assertEquals(fr.getDescription(), fr2.getDescription());

    fr = FileResource.builder()
      .resource("classpath:testfile.txt")
      .description("Test")
      .build();

    json = writer.writeValueAsString(fr);

    fr2 = mapper.readValue(json, FileResource.class);
    Assert.assertNotNull(fr2);
    Assert.assertEquals(fr.getContents(), fr2.getContents());
    Assert.assertEquals(fr.getDescription(), fr2.getDescription());

    // The resource is never sent in JSON
    Assert.assertNotNull(fr.getResource());
    Assert.assertNull(fr2.getResource());
  }

}
