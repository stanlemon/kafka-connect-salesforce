package io.confluent.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

public class AuthenticationResponse {
  @Key("access_token")
  String accessToken;
  @Key("instance_url")
  String instanceUrl;
  @Key("id")
  String id;
  @Key("token_type")
  String tokenType;
  @Key("issuedAt")
  String issuedAt;
  @Key("signature")
  String signature;


  public String accessToken() {
    return this.accessToken;
  }

  public void accessToken(String access_token) {
    this.accessToken = access_token;
  }

  public String instance_url() {
    return this.instanceUrl;
  }

  public void instance_url(String instance_url) {
    this.instanceUrl = instance_url;
  }

  public String id() {
    return this.id;
  }

  public void id(String id) {
    this.id = id;
  }

  public String tokenType() {
    return this.tokenType;
  }

  public void tokenType(String token_type) {
    this.tokenType = token_type;
  }

  public String issuedAt() {
    return this.issuedAt;
  }

  public void issuedAt(String issued_at) {
    this.issuedAt = issued_at;
  }

  public String signature() {
    return this.signature;
  }

  public void signature(String signature) {
    this.signature = signature;
  }
}
