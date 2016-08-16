package io.confluent.kafka.connect.salesforce;

/**
 * Created by jeremy on 5/3/16.
 */
class VersionUtil {
  public static String getVersion() {
    try {
      return VersionUtil.class.getPackage().getImplementationVersion();
    } catch (Exception ex) {
      return "0.0.0.0";
    }
  }
}
