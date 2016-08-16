Welcome to your new Kafka Connect connector!

# Overview

This Kafka Connect connector integrates with the [Salesforce Streaming Api](https://developer.salesforce.com/docs/atlas.en-us.api_streaming.meta/api_streaming/intro_stream.htm)
to provide realtime updates to Kafka as objects are modified in SalesForce. This is done by registering a [PushTopic](https://developer.salesforce.com/docs/atlas.en-us.api_streaming.meta/api_streaming/working_with_pushtopics.htm) 
for specific objects and fields. This object can be managed externally from the connector or you can specify `salesforce.push.topic.create=true` which will 
query the descriptor for the object specified in `salesforce.object`, generating a PushTopic for all of the fields in the descriptor. The descriptor will contain all of the fields that are available. The dynamically generated PushTopic
will use all of the fields that are available at the time the PushTopic is created. If you manually create a PushTopic the schema will still include all of the fields that are defined in the descriptor.

# Configuration

The following table contains the configuration parameters for the connector. 

| Name                                  | Description                                                                                                                                                                | Type     | Default | Valid Values                             | Importance |
|---------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|---------|------------------------------------------|------------|
| kafka.topic                           | The Kafka topic to write the SalesForce data to.                                                                                                                           | string   |         |                                          | high       |
| salesforce.consumer.key               | The consumer key for the OAuth application.                                                                                                                                | string   |         |                                          | high       |
| salesforce.consumer.secret            | The consumer secret for the OAuth application.                                                                                                                             | password |         |                                          | high       |
| salesforce.object                     | The Salesforce object to create a topic for.                                                                                                                               | string   |         |                                          | high       |
| salesforce.password                   | Salesforce password to connect with.                                                                                                                                       | password |         |                                          | high       |
| salesforce.password.token             | The Salesforce security token associated with the username.                                                                                                                | password |         |                                          | high       |
| salesforce.push.topic.name            | The Salesforce topic to subscribe to. If salesforce.push.topic.create is set to true, a PushTopic with this name will be created.                                          | string   |         |                                          | high       |
| salesforce.username                   | Salesforce username to connect with.                                                                                                                                       | string   |         |                                          | high       |
| salesforce.instance                   | The Salesforce instance to connect to.                                                                                                                                     | string   | ""      |                                          | high       |
| connection.timeout                    | The amount of time to wait while connecting to the Salesforce streaming endpoint.                                                                                          | long     | 30000   |                                          | low        |
| curl.logging                          | If enabled the logs will output the equivalent curl commands. This is a security risk because your authorization header will end up in the log file. Use at your own risk. | boolean  | false   |                                          | low        |
| salesforce.push.topic.create          | Flag to determine if the PushTopic should be created if it does not exist.                                                                                                 | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.create   | Flag to determine if the PushTopic should respond to creates.                                                                                                              | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.delete   | Flag to determine if the PushTopic should respond to deletes.                                                                                                              | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.undelete | Flag to determine if the PushTopic should respond to undeletes.                                                                                                            | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.update   | Flag to determine if the PushTopic should respond to updates.                                                                                                              | boolean  | true    |                                          | low        |
| salesforce.version                    | The version of the salesforce API to use.                                                                                                                                  | string   | latest  | ValidPattern{pattern=^(latest|[\d\.]+)$} | low        |

## Example Configuration

```
name=salesforce
tasks.max=1
connector.class=io.confluent.kafka.connect.salesforce.SalesforceSourceConnector
salesforce.username=
salesforce.password=
salesforce.password.token=
salesforce.consumer.key=
salesforce.consumer.secret=
salesforce.object=Lead
salesforce.push.topic.name=IncomingLeads
kafka.topic=salesforce_leads
curl.logging=false
```

# Building

```
git clone https://github.com/jcustenborder/connect-utils
cd connect-utils
mvn clean install

git clone https://github.com/jcustenborder/kafka-connect-salesforce
cd kafka-connect-salesforce
mvn clean install
```

# Running in development

```
mvn clean package
export CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')"
$CONFLUENT_HOME/bin/connect-standalone $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties config/MySourceConnector.properties
```

# Example Output

This is the output from the `kafka-avro-console-consumer`. This schema is dynamically generated based on the [Object metadata](https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/dome_sobject_basic_info.htm) rest api.

```
{
  "Id": "00Q50000017qdkyEAA",
  "IsDeleted": {
    "boolean": false
  },
  "MasterRecordId": null,
  "LastName": {
    "string": "Lead"
  },
  "FirstName": {
    "string": "Test"
  },
  "Salutation": {
    "string": "Mr."
  },
  "Name": {
    "string": "Test Lead"
  },
  "Title": {
    "string": "Testing"
  },
  "Company": {
    "string": "Some Company"
  },
  "City": {
    "string": "Austin"
  },
  "State": {
    "string": "TX"
  },
  "PostalCode": {
    "string": "78737"
  },
  "Country": {
    "string": "US"
  },
  "Latitude": null,
  "Longitude": null,
  "GeocodeAccuracy": null,
  "Address": null,
  "Phone": null,
  "MobilePhone": null,
  "Fax": null,
  "Email": null,
  "Website": null,
  "PhotoUrl": null,
  "LeadSource": null,
  "Status": {
    "string": "Open - Not Contacted"
  },
  "Industry": {
    "string": "Agriculture"
  },
  "Rating": {
    "string": "Hot"
  },
  "AnnualRevenue": null,
  "NumberOfEmployees": null,
  "OwnerId": {
    "string": "00550000005elXkAAI"
  },
  "IsConverted": {
    "boolean": false
  },
  "ConvertedDate": null,
  "ConvertedAccountId": null,
  "ConvertedContactId": null,
  "ConvertedOpportunityId": null,
  "IsUnreadByOwner": {
    "boolean": false
  },
  "CreatedDate": {
    "long": 1451274632000
  },
  "CreatedById": {
    "string": "00550000005elXkAAI"
  },
  "LastModifiedDate": {
    "long": 1451215561000
  },
  "LastModifiedById": {
    "string": "00550000005elXkAAI"
  },
  "SystemModstamp": {
    "long": 1451215561000
  },
  "LastActivityDate": null,
  "LastViewedDate": null,
  "LastReferencedDate": null,
  "Jigsaw": null,
  "JigsawContactId": null,
  "CleanStatus": {
    "string": "5"
  },
  "CompanyDunsNumber": null,
  "DandbCompanyId": null,
  "EmailBouncedReason": null,
  "EmailBouncedDate": null,
  "SICCode__c": null,
  "ProductInterest__c": {
    "string": "GC1000 series"
  },
  "Primary__c": null,
  "CurrentGenerators__c": null,
  "NumberofLocations__c": null
}
```