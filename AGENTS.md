# AGENTS.md

Java client for the Crowdin API v2 and Crowdin Enterprise API v2, distributed via JitPack (`com.github.crowdin:crowdin-api-client-java`).

Two pins are load-bearing: write Java 8 code (`sourceCompatibility = 8` — no `var`, no `List.of`, no streams-`toList()`), and leave the Gradle wrapper at 7.6.4 (the build uses Gradle-7-only Jacoco APIs; CI validates the wrapper).

## Layout

- `src/main/java/com/crowdin/client/Client.java` — entry point; `@Getter` on the class generates every `get<X>Api()` accessor
- `src/main/java/com/crowdin/client/<resource>/<Resource>Api.java` + `<resource>/model/` — one lowercase package per resource
- `src/main/java/com/crowdin/client/core/` — `CrowdinApi` base class, HTTP client, Jackson setup, shared models
- `src/test/java/com/crowdin/client/<resource>/<Resource>ApiTest.java` — tests on the `TestClient` harness (`framework/` package)
- `src/test/resources/api/<resource>/*.json` — request/response fixtures

## Commands

- Build + test (what CI runs): `./gradlew build`
- Tests only: `./gradlew test`; one class: `./gradlew test --tests "com.crowdin.client.labels.LabelsApiTest"`
- Coverage: `./gradlew jacocoTestReport`

There is no linter or formatter — match the style of neighboring files by hand.

## Adding or changing an endpoint

Fetch the endpoint spec first (see Crowdin API reference below). Then:

1. Implement the method on `<Resource>Api extends CrowdinApi`: build URLs from `this.url`, add query params with `HttpRequestConfig.buildUrlParams(...)` (overloads cap at 14 pairs), declare `throws HttpException, HttpBadRequestException`, and return `ResponseObject<T>` / `ResponseList<T>` (`Void.class` for empty responses).
2. Models are Lombok `@Data` POJOs in `<resource>/model/` — write no getters, setters, or equals by hand. Jackson is configured for field-only visibility and the codebase uses no `@JsonProperty`: field names must match the API JSON keys exactly, or values silently drop. Every list endpoint needs a `<X>ResponseList` with a hand-written static `to(...)` mapping to `ResponseList.of(...)`. Params classes extending `Pagination` need `@EqualsAndHashCode(callSuper = true)`.
3. Enums implement `EnumConverter<T>`: an instance `to(...)` plus a **static** `from(String)` that the deserializer finds by reflection — a missing `from` fails at runtime, not compile time. Polymorphic models need a deserializer registered in `JacksonJsonTransformer`; fields the API returns as `[]` instead of an object need `@JsonDeserialize(using = EmptyArrayToNullDeserializer.class)`.
4. For a new resource, register it in `Client.java` with four edits: the import (alphabetical), a `private final <X>Api <x>Api;` field, and its construction in both constructors (`(Credentials)` and `(Credentials, ClientConfig)`). `@Getter` generates the accessor.
5. Test by extending `TestClient` (the test class is itself a client wired to `TestHttpClient`): implement `getMocks()` returning `RequestMock.build(url, method, requestFile, responseFile)` entries, add fixture JSON under `src/test/resources/api/<resource>/`, and call `this.get<X>Api()...`. Mocks match query params exactly (pre-encode spaces as `%20`) and compare the serialized request body against the fixture — a new optional query param must stay absent when null or existing mocks for that URL break.
6. Javadoc every public method with `@param`/`@return` and a `@see` list linking both the developer.crowdin.com and enterprise operation URLs (CI publishes Javadoc).

A complete new resource looks like the Style Guides commit: the Api class, models with response wrappers, the four `Client.java` edits, tests, and fixtures — no README changes.

## Crowdin API reference

Before implementing or changing any endpoint, fetch its spec from the llms.txt indexes (pick by environment, then project type):

- https://support.crowdin.com/_llms-txt/api/crowdin/file-based.txt — Crowdin API, file-based projects (start here)
- https://support.crowdin.com/_llms-txt/api/crowdin/string-based.txt — Crowdin API, string-based projects
- https://support.crowdin.com/_llms-txt/api/enterprise/file-based.txt — Crowdin Enterprise API, file-based projects
- https://support.crowdin.com/_llms-txt/api/enterprise/string-based.txt — Crowdin Enterprise API, string-based projects

Each index links one spec file per route (e.g. `.../api.projects.strings.get.txt`) with the exact request and response shapes.

## Conventions

- Conventional Commits for commit messages and PR titles; CI lints PR titles.
- PRs target `master`.
- Keep the public API backward compatible.
- Never bump the version by hand — the Release workflow rewrites `build.gradle` and every `x.y.z` occurrence in `README.md`.

## PR checklist

A change is ready when:

1. `./gradlew build` passes (compile + tests),
2. every new or changed endpoint method has a `TestClient` mock test with request/response fixtures, and
3. every new or changed public method has Javadoc with both operation links.
