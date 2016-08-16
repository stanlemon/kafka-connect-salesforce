package io.confluent.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

public class SalesforceException extends RuntimeException {
  @Key("error")
  String error;
  @Key("error_description")
  String errorDescription;

  public String error() {
    return this.error;
  }

  public void error(String error) {
    this.error = error;
  }

  public String errorDescription() {
    return this.errorDescription;
  }

  public void errorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  @Override
  public String getMessage() {
    return String.format("%s: %s", this.error, this.errorDescription);
  }
}
