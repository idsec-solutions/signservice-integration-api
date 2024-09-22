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
package se.idsec.signservice.integration.core;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;

/**
 * A {@link ContentLoader} that is used to load file resource contents. If Spring is in the classpath the
 * {@code org.springframework.core.io.DefaultResourceLoader} class is used. Otherwise, we use our own (limited)
 * implementation.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
@Slf4j
public class DefaultContentLoader implements ContentLoader {

  /**
   * If Spring is on the classpath this property holds an instance of the
   * {@code org.springframework.core.io.DefaultResourceLoader} class.
   */
  private Object springContentLoader = null;

  /** The getResource method of DefaultResourceLoader. */
  private Method getResourceMethod = null;

  /**
   * Constructor.
   */
  public DefaultContentLoader() {
    try {
      // If we have the Spring core jar in the classpath we want to use the Spring way of loading resources ...
      //
      final Class<?> clazz = Class.forName("org.springframework.core.io.DefaultResourceLoader");
      final Constructor<?> ctor = clazz.getConstructor();
      this.springContentLoader = ctor.newInstance();
      this.getResourceMethod = this.springContentLoader.getClass().getMethod("getResource", String.class);
    }
    catch (final Exception e) {
      log.info("Spring does not seem to be on the classpath - {}", e.getMessage());
    }
  }

  /** {@inheritDoc} */
  @Override
  public byte[] loadContent(final String resource) throws IOException {

    if (resource == null) {
      throw new IOException("resource is null");
    }

    if (this.springContentLoader != null && this.getResourceMethod != null) {
      try {
        final String _resource = resource.startsWith("/") ? "file://" + resource : resource;
        final Object springResource = this.getResourceMethod.invoke(this.springContentLoader, _resource);

        // Next. Invoke the getInputStream method of the resulting resource object.
        try (final InputStream is = (InputStream) springResource.getClass().getMethod("getInputStream")
            .invoke(springResource)) {
          return readAllBytes(is);
        }
      }
      catch (final Exception e) {
        throw new IOException("Could not load " + resource, e);
      }
    }
    else {
      if (resource.startsWith("classpath:")) {
        String _resource = resource.substring(10);
        if (!_resource.startsWith("/")) {
          _resource = "/" + _resource;
        }
        final InputStream is = this.getClass().getResourceAsStream(_resource);
        if (is == null) {
          throw new IOException("File not found - " + resource);
        }
        try (is) {
          return readAllBytes(is);
        }
      }
      else if (resource.startsWith("file://")) {
        try (final InputStream is = Files.newInputStream(new File(resource.substring(7)).toPath())) {
          return readAllBytes(is);
        }
      }
      else {
        try (final InputStream is = Files.newInputStream(new File(resource).toPath())) {
          return readAllBytes(is);
        }
      }
    }
  }

  private static byte[] readAllBytes(final InputStream is) throws IOException {
    final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int nRead;
    final byte[] data = new byte[4096];
    while ((nRead = is.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }
    buffer.flush();
    return buffer.toByteArray();
  }

}
