package io.confluent.kafka.connect.salesforce;

import org.junit.Test;
import io.confluent.kafka.connect.utils.config.MarkdownFormatter;

public class SalesforceSourceConfigTest {

  @Test
  public void toMarkdown() {
    System.out.println(MarkdownFormatter.toMarkdown(SalesforceSourceConfig.conf()));
  }



}
