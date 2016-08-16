package io.confluent.kafka.connect.salesforce.rest;

import io.confluent.kafka.connect.salesforce.rest.model.ApiVersion;
import io.confluent.kafka.connect.salesforce.rest.model.AuthenticationResponse;
import io.confluent.kafka.connect.salesforce.rest.model.PushTopic;
import io.confluent.kafka.connect.salesforce.rest.model.SObjectDescriptor;
import io.confluent.kafka.connect.salesforce.rest.model.SObjectMetadata;
import io.confluent.kafka.connect.salesforce.rest.model.SObjectsResponse;

import java.util.List;

public interface SalesforceRestClient {
  AuthenticationResponse authenticate();

  void apiVersion(ApiVersion apiVersion);

  List<ApiVersion> apiVersions();

  SObjectsResponse objects();

  SObjectDescriptor describe(SObjectMetadata metadata);

  List<PushTopic> pushTopics();

  void pushTopic(PushTopic pushTopic);
}
