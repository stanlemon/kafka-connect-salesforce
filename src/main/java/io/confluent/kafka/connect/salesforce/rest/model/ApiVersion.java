package io.confluent.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

import java.math.BigDecimal;

public class ApiVersion implements Comparable<ApiVersion> {
  @Key("label")
  String label;
  @Key("url")
  String url;
  @Key("version")
  String version;

  public String label() {
    return this.label;
  }

  public void label(String label) {
    this.label = label;
  }

  public String url() {
    return this.url;
  }

  public void url(String url) {
    this.url = url;
  }

  public String version() {
    return this.version;
  }

  public void version(String version) {
    this.version = version;
  }

  @Override
  public int compareTo(ApiVersion that) {
    if (null == that) {
      return 1;
    }

    BigDecimal thisVersion = new BigDecimal(this.version).setScale(3);
    BigDecimal thatVersion = new BigDecimal(that.version).setScale(3);

    return thisVersion.compareTo(thatVersion);
  }
}
