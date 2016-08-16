package io.confluent.kafka.connect.salesforce.rest;

import io.confluent.kafka.connect.salesforce.SalesforceSourceConfig;

public class SalesforceRestClientFactory {

  public static SalesforceRestClient create(SalesforceSourceConfig config) {
    return new SalesforceRestClientImpl(config);
  }


}
