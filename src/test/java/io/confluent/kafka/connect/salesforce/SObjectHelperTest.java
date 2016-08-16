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
