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
import io.confluent.kafka.connect.salesforce.rest.model.SObjectDescriptor;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SObjectHelperTest {

  @Test
  public void valueSchema() {
    final SObjectDescriptor descriptor = TestData.sObjectDescriptor();
    final Schema schema = SObjectHelper.valueSchema(descriptor);
    assertNotNull(schema);
    assertEquals("io.confluent.kafka.connect.salesforce.Lead", schema.name());
  }

  @Test
  public void convertStruct() {
    final SObjectDescriptor descriptor = TestData.sObjectDescriptor();
    JsonNode jsonNode = TestData.salesForceEvent();
    JsonNode dataNode = jsonNode.get("data");
    JsonNode eventNode = jsonNode.get("event");
    JsonNode sobjectNode = dataNode.get("sobject");
    final Schema schema = SObjectHelper.valueSchema(descriptor);
    Struct struct = new Struct(schema);
    SObjectHelper.convertStruct(sobjectNode, schema, struct);
    assertNotNull(struct);
  }

}
