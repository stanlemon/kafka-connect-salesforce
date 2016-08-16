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
