package io.confluent.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

import java.util.List;

public class QueryResult<T extends List> {
  @Key("totalSize")
  Integer totalSize;

  @Key("done")
  Boolean done;

  @Key("records")
  T records;

  public Integer totalSize() {
    return this.totalSize;
  }

  public void totalSize(Integer totalSize) {
    this.totalSize = totalSize;
  }

  public Boolean done() {
    return this.done;
  }

  public void done(Boolean done) {
    this.done = done;
  }

  public T records() {
    return this.records;
  }

  public void records(T records) {
    this.records = records;
  }
}
