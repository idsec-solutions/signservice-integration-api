![Logo](img/idsec.png)

# Signature Service Integration Service API

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/se.idsec.signservice.integration/signservice-integration-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/se.idsec.signservice.integration/signservice-integration-api)

Open Source Signature Service Integration Service API.

---

The [Swedish eID Framework](https://docs.swedenconnect.se/technical-framework/) defines a model for Federated Central Signing Services. The **signservice-integration-api** repository defines an API for an Integration Service that is used to create requests sent to the Signature Service, and process responses received from the Signature Service.

The API is implemented as a Java API with interfaces and domain classes. Using these classes we generate JSON which is used to describe the SignService Integration Service REST API.

* [Configuration and policies](configuration.md)

* [Java API](java-api.md)

* [REST API](rest-api.md)


See the [Java API documentation](https://idsec-solutions.github.io/signservice-integration-api/javadoc/) and the [generated project information](https://idsec-solutions.github.io/signservice-integration-api/site/) for details.

### Maven

The **signservice-integration-api** artifact is published to Maven central and a dependency to the
library should be included as follows:

```
<dependency>
  <groupId>se.idsec.signservice.integration</groupId>
  <artifactId>signservice-integration-api</artifactId>
  <version>${signservice-integration.version}</version>
</dependency>
```

### Feedback

Please create an [Issue](https://github.com/idsec-solutions/signservice-integration-api/issues) if you have questions or suggestions.


---

Copyright &copy; 2019-2020, [IDsec Solutions AB](http://www.idsec.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
