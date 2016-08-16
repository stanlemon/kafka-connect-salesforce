package io.confluent.kafka.connect.salesforce;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import io.confluent.kafka.connect.salesforce.rest.SalesforceRestClient;
import io.confluent.kafka.connect.salesforce.rest.SalesforceRestClientFactory;
import io.confluent.kafka.connect.salesforce.rest.model.ApiVersion;
import io.confluent.kafka.connect.salesforce.rest.model.PushTopic;
import io.confluent.kafka.connect.salesforce.rest.model.SObjectDescriptor;
import io.confluent.kafka.connect.salesforce.rest.model.SObjectMetadata;
import io.confluent.kafka.connect.salesforce.rest.model.SObjectsResponse;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SalesforceSourceConnector extends SourceConnector {
  private static Logger log = LoggerFactory.getLogger(SalesforceSourceConnector.class);
  List<Map<String, String>> configs = new ArrayList<>();
  private SalesforceSourceConfig config;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    config = new SalesforceSourceConfig(map);

    SalesforceRestClient client = SalesforceRestClientFactory.create(this.config);
    client.authenticate();

    List<ApiVersion> apiVersions = client.apiVersions();
    ApiVersion apiVersion = apiVersions.get(0);
    client.apiVersion(apiVersion);

    SObjectsResponse sObjectsResponse = client.objects();
    SObjectMetadata sObjectMetadata = null;
    SObjectDescriptor sObjectDescriptor = null;

    for (SObjectMetadata metadata : sObjectsResponse.sobjects()) {
      if (this.config.salesForceObject().equalsIgnoreCase(metadata.name())) {
        sObjectMetadata = metadata;
        sObjectDescriptor = client.describe(metadata);
        break;
      }
    }

    Preconditions.checkNotNull(sObjectMetadata, "Could not find metadata for object '%s'", this.config.salesForceObject());
    Preconditions.checkNotNull(sObjectDescriptor, "Could not find descriptor for object '%s'", this.config.salesForceObject());

    List<PushTopic> pushTopics = client.pushTopics();
    PushTopic pushTopic = null;

    for (PushTopic p : pushTopics) {
      if (this.config.salesForcePushTopicName().equals(p.name())) {
        pushTopic = p;
        break;
      }
    }

    if (null == pushTopic && this.config.salesForcePushTopicCreate()) {
      if (log.isWarnEnabled()) {
        log.warn("PushTopic {} was not found.", this.config.salesForcePushTopicName());
      }

      pushTopic = new PushTopic();
      pushTopic.name(this.config.salesForcePushTopicName());

      Set<String> fields = new LinkedHashSet<>();
      for (SObjectDescriptor.Field f : sObjectDescriptor.fields()) {
        if (SObjectHelper.isTextArea(f)) {
          continue;
        }
        fields.add(f.name());
      }

      String query = String.format(
          "SELECT %s FROM %s",
          Joiner.on(',').join(fields),
          sObjectDescriptor.name()
      );
      pushTopic.query(query);
      if (log.isInfoEnabled()) {
        log.info("Setting query for {} to \n{}", pushTopic.name(), pushTopic.query());
      }
      pushTopic.notifyForOperationCreate(this.config.salesForcePushTopicNotifyCreate());
      pushTopic.notifyForOperationUpdate(this.config.salesForcePushTopicNotifyUpdate());
      pushTopic.notifyForOperationDelete(this.config.salesForcePushTopicNotifyDelete());
      pushTopic.notifyForOperationUndelete(this.config.salesForcePushTopicNotifyUndelete());
      pushTopic.apiVersion(new BigDecimal(apiVersion.version()));

      if (log.isInfoEnabled()) {
        log.info("Creating PushTopic {}", pushTopic.name());
      }

      client.pushTopic(pushTopic);
    }

    Preconditions.checkNotNull(pushTopic, "PushTopic '%s' was not found.", this.config.salesForcePushTopicName());

    Map<String, String> taskSettings = new HashMap<>();
    taskSettings.putAll(map);
    taskSettings.put(SalesforceSourceConfig.VERSION_CONF, apiVersion.version());
    this.configs.add(taskSettings);
  }

  @Override
  public Class<? extends Task> taskClass() {
    return SalesforceSourceTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int i) {
    return this.configs;
  }

  @Override
  public void stop() {


  }

  @Override
  public ConfigDef config() {
    return SalesforceSourceConfig.conf();
  }
}
