# Contributing

:tada: First off, thanks for taking the time to contribute! :tada:

The Crowdin API client provides methods that essentially call Crowdin's APIs. This makes it much easier for other developers to make calls to Crowdin's APIs, as the client abstracts a lot of the work required. In short, the API client provides a lightweight interface for making API requests to Crowdin.

The following is a set of guidelines for contributing to Crowdin Java Client. These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

This project and everyone participating in it are governed by the [Code of Conduct](/CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

## How can I contribute?

### Star this repo

It's quick and goes a long way! :stars:

### Reporting Bugs

This section guides you through submitting a bug report for Crowdin Java Client. Following these guidelines helps maintainers, and the community understand your report :pencil:, reproduce the behavior :computer:, and find related reports :mag_right:.

When you are creating a bug report, please include as many details as possible.

#### How Do I Submit a Bug Report?

Bugs are tracked as [GitHub issues](https://github.com/crowdin/crowdin-api-client-java/issues/).

Explain the problem and include additional details to help reproduce the problem:

* **Use a clear and descriptive title** for the issue to identify the problem.
* **Describe the exact steps which reproduce the problem** in as many details as possible. Don't just say what you did, but explain how you did it.
* **Describe the behavior you observed after following the steps** and point out what exactly is the problem with that behavior.
* **Explain which behavior you expected to see instead and why.**

Include details about your environment.

### Suggesting Enhancements

This section guides you through submitting an enhancement suggestion for Crowdin Java Client. Following these guidelines helps maintainers and the community understand your suggestion :pencil: and find related suggestions :mag_right:.

When you are creating an enhancement suggestion, please include as many details as possible.

#### How Do I Submit an Enhancement Suggestion?

Enhancement suggestions are tracked as [GitHub issues](https://github.com/crowdin/crowdin-api-client-java/issues/).

Create an issue on that repository and provide the following information:

* **Use a clear and descriptive title** for the issue to identify the suggestion.
* **Provide a step-by-step description of the suggested enhancement** in as many details as possible.
* **Describe the current behavior** and **explain which behavior you expected to see instead** and why.
* **Explain why this enhancement would be useful** to most Java Client users.

### Your First Code Contribution

Unsure where to begin contributing to Crowdin Java Client? You can start by looking through these `good-first-issue` and `help-wanted` issues:

* [Good first issue](https://github.com/crowdin/crowdin-api-client-java/issues?q=is%3Aopen+is%3Aissue+label%3A%22good+first+issue%22) - issues which should only require a small amount of code, and a test or two.
* [Help wanted](https://github.com/crowdin/crowdin-api-client-java/issues?q=is%3Aopen+is%3Aissue+label%3A%22help+wanted%22) - issues which should be a bit more involved than `Good first issue` issues.

#### Getting Started

To contribute to the Crowdin Java Client, please ensure you have the following installed:

1. **Java Development Kit (JDK)**: This project requires JDK 8 or higher.
2. **Build Tool**: To build the project, use Gradle version 6 or higher.
3. **Dependencies**: The project includes the Apache HTTP client for handling requests and the Jackson library for JSON parsing.

Follow these steps to get started:

1. Fork and clone the repository.
2. Set up an API token and organization ID through your Crowdin account, which will be needed to authenticate API requests.
3. Run the following commands to build and test the project:
   ```bash
   ./gradlew build
   ./gradlew test

Once your setup is complete, you're ready to start working on the project.

#### Pull Request Checklist

Before sending your pull requests, make sure you followed the list below:

- Read these guidelines.
- Read [Code of Conduct](/CODE_OF_CONDUCT.md).
- Ensure that your code adheres to standard conventions, as used in the rest of the project.
- Ensure that there are unit tests for your code.
- Run unit tests.

> **Note**
> This project uses the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification for commit messages and PR titles.

#### Philosophy of code contribution

- Include unit tests when you contribute new features, as they help to a) prove that your code works correctly, and b) guard against future breaking changes to lower the maintenance cost.
- Bug fixes also generally require unit tests, because the presence of bugs usually indicates insufficient test coverage.