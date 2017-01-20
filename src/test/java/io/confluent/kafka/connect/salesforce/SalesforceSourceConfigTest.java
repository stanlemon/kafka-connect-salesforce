package io.confluent.kafka.connect.salesforce;


import io.confluent.kafka.connect.utils.config.MarkdownFormatter;
import org.junit.jupiter.api.Test;

public class SalesforceSourceConfigTest {

  @Test
  public void toMarkdown() {
    System.out.println(MarkdownFormatter.toMarkdown(SalesforceSourceConfig.conf()));
  }


}
