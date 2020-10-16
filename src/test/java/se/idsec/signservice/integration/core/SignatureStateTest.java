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
package se.idsec.signservice.integration.core;

import java.io.Serializable;

import org.junit.Test;

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
    
    SignatureState state = new SignatureState() {
      
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
    DummyObject object = DummyObject.builder().object("Hello").state(state).build();
    
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

    String json = writer.writeValueAsString(object);
    System.out.println(json);
    
    DummyObject object2 = mapper.readValue(json, DummyObject.class);
    System.out.println(object2.getState().getState());
    
    String json2 = writer.writeValueAsString(object);
    System.out.println(json2);
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
    private static final long serialVersionUID = 4163340005958632268L;
    private String stringField;
    private int intField;
  }

}
