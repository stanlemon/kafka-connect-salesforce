/**
 * Copyright (C) 2016 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.confluent.kafka.connect.salesforce;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import io.confluent.kafka.connect.salesforce.rest.model.SObjectDescriptor;

import java.io.IOException;
import java.io.InputStream;

public class TestData {
  private static final JsonFactory jsonFactory = new JacksonFactory();
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static SObjectDescriptor sObjectDescriptor() {
    try (InputStream inputStream = TestData.class.getResourceAsStream("sobjectdescriptor.json")) {
      return jsonFactory.fromInputStream(inputStream, SObjectDescriptor.class);
    } catch (IOException ex) {
      throw new IllegalStateException("Could not read", ex);
    }
  }

  public static JsonNode salesForceEvent() {
    try (InputStream inputStream = TestData.class.getResourceAsStream("salesforceevent.json")) {
      return objectMapper.readTree(inputStream);
    } catch (IOException ex) {
      throw new IllegalStateException("Could not read", ex);
    }
  }

}
