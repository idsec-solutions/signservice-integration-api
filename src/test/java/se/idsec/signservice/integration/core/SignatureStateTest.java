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

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Tests for JSON serializing/deserializing of SignatureState.
 *
 * @author Martin Lindström (martin@idsec.se)
 * @author Stefan Santesson (stefan@idsec.se)
 */
public class SignatureStateTest {

  @Test
  public void testJson() throws Exception {

    final SignatureState state = new SignatureState() {

      @Serial
      private static final long serialVersionUID = 1L;

      @Override
      public String getId() {
        return "123456";
      }

      @Override
      public Serializable getState() {
        return DummyState.builder().stringField("dummy").intField(42).build();
      }

    };
    final DummyObject object = DummyObject.builder().object("Hello").state(state).build();

    final ObjectMapper mapper = new ObjectMapper();
    final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

    final String json = writer.writeValueAsString(object);

    final DummyObject object2 = mapper.readValue(json, DummyObject.class);

    Assertions.assertTrue(object2.getState() instanceof RestClientSignatureState);
    Assertions.assertEquals(object.getState().getId(), object2.getState().getId());

    final DummyState ds2 = mapper.convertValue(object2.getState().getState(), DummyState.class);
    Assertions.assertNotNull(ds2);
    Assertions.assertEquals(object.getState().getState(), ds2);
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class DummyObject {
    private String object;
    private SignatureState state;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class DummyState implements Serializable {
    @Serial
    private static final long serialVersionUID = 4163340005958632268L;
    private String stringField;
    private int intField;
  }

}
