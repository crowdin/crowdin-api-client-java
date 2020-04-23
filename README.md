[<p align='center'><img src='https://support.crowdin.com/assets/logos/crowdin-dark-symbol.png' data-canonical-src='https://support.crowdin.com/assets/logos/crowdin-dark-symbol.png' width='200' height='200' align='center'/></p>](https://crowdin.com)

# Crowdin Java client

The Crowdin Java client is a lightweight interface to the Crowdin API v2 that works in any Java environment. It provides common services for making API requests.

Our API is a full-featured RESTful API that helps you to integrate localization into your development process. The endpoints that we use allow you to easily make calls to retrieve information and to execute actions needed.

For more about Crowdin API v2 see the [documentation](https://support.crowdin.com/api/v2/).

## Status

[![JitPack](https://img.shields.io/jitpack/v/github/crowdin/crowdin-api-client-java?cacheSeconds=3600)](https://jitpack.io/#crowdin/crowdin-api-client-java)
[![](https://jitpack.io/v/crowdin/crowdin-api-client-java/month.svg)](https://jitpack.io/#crowdin/crowdin-api-client-java)
[![](https://jitpack.io/v/crowdin/crowdin-api-client-java/week.svg)](https://jitpack.io/#crowdin/crowdin-api-client-java)
[![GitHub contributors](https://img.shields.io/github/contributors/crowdin/crowdin-api-client-java?cacheSeconds=5000)](https://github.com/crowdin/crowdin-api-client-java/graphs/contributors)
[![License](https://img.shields.io/github/license/crowdin/crowdin-api-client-java?cacheSeconds=10000)](https://github.com/crowdin/crowdin-api-client-java/blob/master/LICENSE)

[![Azure DevOps builds (branch)](https://img.shields.io/azure-devops/build/crowdin/crowdin-api-client-java/21/master?cacheSeconds=3600&logo=azure-devops)](https://dev.azure.com/crowdin/crowdin-api-client-java/_build/latest?definitionId=21&branchName=master)
[![Azure DevOps tests (branch)](https://img.shields.io/azure-devops/tests/crowdin/crowdin-api-client-java/21/master?cacheSeconds=3600)](https://dev.azure.com/crowdin/crowdin-api-client-java/_build/latest?definitionId=21&branchName=master)
[![codecov](https://codecov.io/gh/crowdin/crowdin-api-client-java/branch/master/graph/badge.svg)](https://codecov.io/gh/crowdin/crowdin-api-client-java)

## Installation

### Gradle
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    compile "com.github.crowdin:crowdin-api-client-java:1.1.0"
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
    <version>1.1.0</version>
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

### Customization

This client uses [Apache http client](https://hc.apache.org/) and [Jackson json library](https://github.com/FasterXML/jackson).  
Usage of these libraries is wrapped into interfaces and gives possibility to override them and use different libraries for http communication or json transformations.
The library entry point is `com.crowdin.client.Client` and this class has additional constructor where you can specify additional configurations (please refer to javadoc).

## Contribution
We are happy to accept contributions to the Crowdin Java client. To contribute please do the following:
1. Fork the repository on GitHub.
2. Decide which code you want to submit. Commit your changes and push to the new branch.
3. Ensure that your code adheres to standard conventions, as used in the rest of the library.
4. Submit a pull request with your patch on Github.

## Seeking Assistance
If you find any problems or would like to suggest a feature, please feel free to file an issue on Github at [Issues Page](https://github.com/crowdin/crowdin-api-client-java/issues).

Need help working with Crowdin Java client or have any questions?
[Contact Customer Success Service](https://crowdin.com/contacts).

## License
<pre>
The Crowdin Java client is licensed under the MIT License. 
See the LICENSE file distributed with this work for additional 
information regarding copyright ownership.

Except as contained in the LICENSE file, the name(s) of the above copyright
holders shall not be used in advertising or otherwise to promote the sale,
use or other dealings in this Software without prior written authorization.
</pre>
