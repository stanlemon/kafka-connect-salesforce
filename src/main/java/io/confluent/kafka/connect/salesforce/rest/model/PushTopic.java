package io.confluent.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

import java.math.BigDecimal;
import java.util.Map;

public class PushTopic {
  @Key("attributes")
  Map<String, Object> attributes;
  @Key("Name")
  String name;
  @Key("Query")
  String query;
  @Key("ApiVersion")
  BigDecimal apiVersion;
  @Key("NotifyForOperationCreate")
  Boolean notifyForOperationCreate;
  @Key("NotifyForOperationUpdate")
  Boolean notifyForOperationUpdate;
  @Key("NotifyForOperationUndelete")
  Boolean notifyForOperationUndelete;
  @Key("NotifyForOperationDelete")
  Boolean notifyForOperationDelete;
  @Key("NotifyForFields")
  String notifyForFields;

  public String name() {
    return this.name;
  }

  public void name(String name) {
    this.name = name;
  }

  public String query() {
    return this.query;
  }

  public void query(String query) {
    this.query = query;
  }

  public BigDecimal apiVersion() {
    return this.apiVersion;
  }

  public void apiVersion(BigDecimal apiVersion) {
    this.apiVersion = apiVersion;
  }

  public Boolean notifyForOperationCreate() {
    return this.notifyForOperationCreate;
  }

  public void notifyForOperationCreate(Boolean notifyForOperationCreate) {
    this.notifyForOperationCreate = notifyForOperationCreate;
  }

  public Boolean notifyForOperationUpdate() {
    return this.notifyForOperationUpdate;
  }

  public void notifyForOperationUpdate(Boolean notifyForOperationUpdate) {
    this.notifyForOperationUpdate = notifyForOperationUpdate;
  }

  public Boolean notifyForOperationUndelete() {
    return this.notifyForOperationUndelete;
  }

  public void notifyForOperationUndelete(Boolean notifyForOperationUndelete) {
    this.notifyForOperationUndelete = notifyForOperationUndelete;
  }

  public Boolean notifyForOperationDelete() {
    return this.notifyForOperationDelete;
  }

  public void notifyForOperationDelete(Boolean notifyForOperationDelete) {
    this.notifyForOperationDelete = notifyForOperationDelete;
  }

  public String notifyForFields() {
    return this.notifyForFields;
  }

  public void notifyForFields(String notifyForFields) {
    this.notifyForFields = notifyForFields;
  }
}
