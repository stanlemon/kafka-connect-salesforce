package io.confluent.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

public class SObjectsResponse {
  @Key("encoding")
  String encoding;
  @Key("maxBatchSize")
  Integer maxBatchSize;
  @Key("sobjects")
  List<SObjectMetadata> sobjects = new ArrayList<>();

  public String encoding() {
    return this.encoding;
  }

  public void encoding(String encoding) {
    this.encoding = encoding;
  }

  public Integer maxBatchSize() {
    return this.maxBatchSize;
  }

  public void maxBatchSize(Integer maxBatchSize) {
    this.maxBatchSize = maxBatchSize;
  }

  public List<SObjectMetadata> sobjects() {
    return this.sobjects;
  }

  public void sobjects(List<SObjectMetadata> sobjects) {
    this.sobjects = sobjects;
  }
}
