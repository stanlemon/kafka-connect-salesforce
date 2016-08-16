package io.confluent.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

import java.util.Map;

public class SalesForceEvent {
  @Key("clientId")
  String clientId;
  @Key("data")
  Data data;
  @Key("Channel")
  String channel;

  public String clientId() {
    return this.clientId;
  }

  public void clientId(String clientId) {
    this.clientId = clientId;
  }

  public Data data() {
    return this.data;
  }

  public void data(Data data) {
    this.data = data;
  }

  public String channel() {
    return this.channel;
  }

  public void channel(String channel) {
    this.channel = channel;
  }

  public static class Data {
    @Key("event")
    Event event;
    @Key("sobject")
    Map<String, Object> sobject;

    public Event event() {
      return this.event;
    }

    public void event(Event event) {
      this.event = event;
    }

    public Map<String, Object> sobject() {
      return this.sobject;
    }

    public void sobject(Map<String, Object> sobject) {
      this.sobject = sobject;
    }
  }

  public static class Event {
    @Key("createdDate")
    String createdDate;
    @Key("replayId")
    Long replayId;
    @Key("type")
    String type;

    public String createdDate() {
      return this.createdDate;
    }

    public void createdDate(String createdDate) {
      this.createdDate = createdDate;
    }

    public Long replayId() {
      return this.replayId;
    }

    public void replayId(Long replayId) {
      this.replayId = replayId;
    }

    public String type() {
      return this.type;
    }

    public void type(String type) {
      this.type = type;
    }
  }
}
