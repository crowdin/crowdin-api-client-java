<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="https://support.crowdin.com/assets/logos/symbol/png/crowdin-symbol-cWhite.png">
    <source media="(prefers-color-scheme: light)" srcset="https://support.crowdin.com/assets/logos/symbol/png/crowdin-symbol-cDark.png">
    <img width="150" height="150" src="https://support.crowdin.com/assets/logos/symbol/png/crowdin-symbol-cDark.png">
  </picture>
</p>

# Crowdin Java client

The Crowdin Java client is a lightweight interface to the Crowdin API that works in any Java environment. It provides common services for making API requests.

Our API is a full-featured RESTful API that helps you to integrate localization into your development process. The endpoints that we use allow you to easily make calls to retrieve information and to execute actions needed.

<div align="center">

[**`API Client Docs`**](https://crowdin.github.io/crowdin-api-client-java/) &nbsp;|&nbsp;
[**`Crowdin API`**](https://developer.crowdin.com/api/v2/) &nbsp;|&nbsp;
[**`Crowdin Enterprise API`**](https://developer.crowdin.com/enterprise/api/v2/)

[![JitPack](https://img.shields.io/jitpack/v/github/crowdin/crowdin-api-client-java?cacheSeconds=3600)](https://jitpack.io/#crowdin/crowdin-api-client-java)
[![](https://jitpack.io/v/crowdin/crowdin-api-client-java/month.svg)](https://jitpack.io/#crowdin/crowdin-api-client-java)
[![](https://jitpack.io/v/crowdin/crowdin-api-client-java/week.svg)](https://jitpack.io/#crowdin/crowdin-api-client-java)
[![Tests](https://github.com/crowdin/crowdin-api-client-java/actions/workflows/basic.yml/badge.svg)](https://github.com/crowdin/crowdin-api-client-java/actions/workflows/basic.yml)
[![codecov](https://codecov.io/gh/crowdin/crowdin-api-client-java/branch/master/graph/badge.svg)](https://codecov.io/gh/crowdin/crowdin-api-client-java)
[![GitHub contributors](https://img.shields.io/github/contributors/crowdin/crowdin-api-client-java?cacheSeconds=5000)](https://github.com/crowdin/crowdin-api-client-java/graphs/contributors)
[![License](https://img.shields.io/github/license/crowdin/crowdin-api-client-java?cacheSeconds=10000)](https://github.com/crowdin/crowdin-api-client-java/blob/master/LICENSE)

</div>

## Installation

### Gradle
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    compile "com.github.crowdin:crowdin-api-client-java:1.30.3"
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.crowdin</groupId>
    <artifactId>crowdin-api-client-java</artifactId>
    <version>1.30.3</version>
</dependency>
```

## Quick Start

```java
import com.crowdin.client.Client;
import com.crowdin.client.core.model.Credentials;

public class ListProjectBranchesExample {

    public static void main(String[] args) {
        Credentials credentials = new Credentials("token", "organization");
        Client client = new Client(credentials);
        client
            .getSourceFilesApi()
            .listBranches(123L, null, 500, null)
            .getData()
            .forEach(branch -> System.out.println(branch.getData()));
    }

}
```

### Sorting results

You can sort the results of `list*` methods by one or multiple fields using the `OrderByField` class.  
If sort direction is not specified, results will be sorted in ascending (`ASC`) order by default.

#### Example: Sort string comments by `id` descending

```java
import com.crowdin.client.Client;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.stringcomments.model.OrderByField;
import com.crowdin.client.stringcomments.model.SortOrder;
import com.crowdin.client.stringcomments.model.StringComment;

import java.util.Collections;
import java.util.List;

public class ListCommentsSortedExample {

    public static void main(String[] args) {
        Credentials credentials = new Credentials("your-token", "your-organization");
        Client client = new Client(credentials);

        OrderByField orderByIdDesc = new OrderByField();
        orderByIdDesc.setFieldName("id");
        orderByIdDesc.setOrderBy(SortOrder.DESC); // Optional: default is ASC

        List<StringComment> comments = client
                .getStringCommentsApi()
                .listStringComments(
                        123L, // projectId
                        null, null, null, null, null, null,
                        Collections.singletonList(orderByIdDesc)
                )
                .getData()
                .stream()
                .map(response -> response.getData())
                .toList();

        comments.forEach(comment -> System.out.println(comment.getId()));
    }

}
```
#### Example: Sort by multiple fields

You can also sort by multiple fields, for example: first by `createdAt`, then by `id`.

```java
import java.util.Arrays;
import com.crowdin.client.stringcomments.model.OrderByField;
import com.crowdin.client.stringcomments.model.SortOrder;

public class ListCommentsSortedExample {

    public static void main(String[] args) {
        OrderByField orderByCreatedAtAsc = new OrderByField();
        orderByCreatedAtAsc.setFieldName("createdAt"); // ASC by default
        
        OrderByField orderByIdDesc = new OrderByField();
        orderByIdDesc.setFieldName("id");
        orderByIdDesc.setOrderBy(SortOrder.DESC);

        List<OrderByField> orderBy = Arrays.asList(orderByCreatedAtAsc, orderByIdDesc);
    }
}
```

### Customization

This client uses [Apache http client](https://hc.apache.org/) and [Jackson json library](https://github.com/FasterXML/jackson).  
Usage of these libraries is wrapped into interfaces and gives possibility to override them and use different libraries for http communication or json transformations.

The library entry point is `com.crowdin.client.Client` and this class has additional constructor where you can specify additional configurations (please refer to javadoc).

#### Http timeouts

Library allows you to configure http requests timeout.

```java
import com.crowdin.client.Client;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;

public class Main {

    public static void main(String[] args) {
        Credentials credentials = new Credentials("token", "organization");
        Client client = new Client(credentials, ClientConfig.builder().httpTimeoutMs(5000).build());
    }

}
```

## GraphQL API

This library also provides possibility to use [GraphQL API](https://support.crowdin.com/developer/graphql-api/)

```java
import com.crowdin.client.Client;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.GraphQLRequest;

import java.util.Map;

public class GraphQLExample {

    public static void main(String[] args) {
        Credentials credentials = new Credentials("token", "organization");
        Client client = new Client(credentials);
        String query = "query { viewer { projects(first: 2) { edges { node { name } } } } }";
        GraphQLRequest request = new GraphQLRequest(query);
        Map<String, Object> response = client.graphql(request).getData();
        System.out.println(response);
    }

}
```

## Seeking Assistance

If you find any problems or would like to suggest a feature, please read the [How can I contribute](/CONTRIBUTING.md#how-can-i-contribute) section in our contributing guidelines.

## Contributing

If you would like to contribute please read the [Contributing](/CONTRIBUTING.md) guidelines.

## License

<pre>
The Crowdin Java client is licensed under the MIT License. 
See the LICENSE file distributed with this work for additional 
information regarding copyright ownership.

Except as contained in the LICENSE file, the name(s) of the above copyright
holders shall not be used in advertising or otherwise to promote the sale,
use or other dealings in this Software without prior written authorization.
</pre>
