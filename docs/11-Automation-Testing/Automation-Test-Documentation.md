# Movies Collection - Automation Test Documentation

## 1. Overview

This document describes the automated UI testing approach for **Movies Collection**, an ASP.NET Core application for browsing and managing movies. The automation project uses Selenium WebDriver with Java and JUnit to verify functionality through the web browser.

The objective is to make the automated tests understandable, repeatable in the configured local environment, and traceable to the project's requirements, scenarios, test cases, and defect records.

## 2. Automation Scope

The automated tests cover:

- **Guest:** Home, Privacy, Login and Registration pages; restricted Movies page access; successful registration and email confirmation.
- **Authenticated user:** Login, logout, Movies page access, title and release-year sorting, genre filtering, Privacy and profile management.
- **Administrator:** Login, logout, Movies page, sorting and filtering, Privacy, profile management, and user management (including deletion).
- **Positive movie CRUD:** Create, view details, edit, and delete a movie, including poster upload and replacement.
- **Negative registration and login:** Invalid or empty email, duplicate email, password rules, confirmation mismatch, and unsuccessful login attempts.
- **Negative movie input validation:** Missing required fields, invalid release or last-watched years, and ratings outside the allowed range on Create/Edit forms.

The automated test coverage is documented through **53 test case IDs**, `TC-001` through `TC-053`. Parameterized test methods may execute multiple input values under one documented test case.

Performance, load, penetration, and cross-platform testing are outside the current scope.

## 3. Technology Stack

| Component                       | Technology                                                                 |
|---------------------------------|----------------------------------------------------------------------------|
| Application under test          | Movies Collection (ASP.NET Core)                                           |
| Browser automation              | Selenium WebDriver (Java)                                                  |
| Programming language            | Java 21                                                                    |
| Test framework                  | JUnit 5 / JUnit Platform (see `pom.xml` for installed dependency versions) |
| Build and dependency management | Maven                                                                      |
| Browser                         | Google Chrome                                                              |
| IDE                             | IntelliJ IDEA                                                              |
| Application database            | SQL Server (`MoviesDBContext`)                                             |
| Test target                     | `https://localhost:7285/`                                                  |

**Version note:** Runtime/browser and dependency versions can change. The Maven `pom.xml` is the source of truth for the project's current Selenium, JUnit, and Surefire versions. The Test Plan and Test Execution Results record the environments used for specific executions.

## 4. Automation Framework Architecture

The project uses the **Page Object Model (POM)** alongside reusable test setup and helper methods.

```text
movies-collection-qa-automation/
├── pom.xml
├── src/
│   ├── main/java/pages/
│   │   ├── LoginPage.java
│   │   ├── RegisterPage.java
│   │   └── MoviesPage.java
│   └── test/
│       ├── java/
│       │   ├── extensions/
│       │   │   └── WebDriverProvider.java
│       │   ├── helpers/
│       │   │   └── MoviesAssertions.java
│       │   ├── tests/
│       │   │   ├── BaseTest.java
│       │   │   ├── NoneLoginTest.java
│       │   │   ├── UserTest.java
│       │   │   ├── AdminTest.java
│       │   │   ├── PositiveMovieCRUDTest.java
│       │   │   ├── NegativeRegistrationTest.java
│       │   │   ├── NegativeLoginTest.java
│       │   │   └── NegativeMovieCRUDTest.java
│       │   └── testsuites/
│       │       ├── PositiveTestSuite.java
│       │       └── NegativeTestSuite.java
│       └── resources/poster_test_data/
│           ├── popcorn.png
│           └── popcorn_updated.png
└── docs/
```

### 4.1 Page Objects

The Page Object classes encapsulate selected UI interactions and element access for Login, Registration, and Movies. This reduces duplication and helps localize selector maintenance when the application UI changes.

### 4.2 BaseTest and helpers

`BaseTest` creates a `ChromeDriver`, navigates to `https://localhost:7285/`, and initializes a `WebDriverWait` with a 10-second timeout before each test. It closes the browser after each test and provides navigation, login-related, and movie-data helper operations. `MoviesAssertions` contains reusable movie-list assertions.

The tests combine page-object methods with direct Selenium interactions when appropriate. This is a pragmatic POM-based structure rather than a claim that every UI action has been extracted into a page object.

### 4.3 Synchronization and assertions

Tests use Selenium `WebDriverWait` with `ExpectedConditions` for elements, text, page titles, and URLs. Assertions are made using JUnit. Some movie-card interactions use Selenium `Actions` to scroll an element into view before clicking it.

## 5. Test Class and Suite Organization

| Group    | Test class                 | Primary coverage                                  |
|----------|----------------------------|---------------------------------------------------|
| Positive | `NoneLoginTest`            | Guest access and registration                     |
| Positive | `UserTest`                 | Authenticated user functionality                  |
| Positive | `AdminTest`                | Administrator functionality                       |
| Positive | `PositiveMovieCRUDTest`    | Create → View → Edit → Delete workflow            |
| Negative | `NegativeRegistrationTest` | Registration field validation and duplicate email |
| Negative | `NegativeLoginTest`        | Login input and authentication errors             |
| Negative | `NegativeMovieCRUDTest`    | Create/Edit movie input validation                |

Two JUnit suite classes group these tests:

- `PositiveTestSuite`: `NoneLoginTest`, `UserTest`, `AdminTest`, `PositiveMovieCRUDTest`.
- `NegativeTestSuite`: `NegativeRegistrationTest`, `NegativeLoginTest`, `NegativeMovieCRUDTest`.

**Ordered workflow:** `PositiveMovieCRUDTest` deliberately uses ordered test methods against one movie: Create, then View, then Edit, then Delete. These methods are dependent on the workflow's preceding state. Run the entire class in order when validating this flow, rather than treating a single middle method as independently executable.

## 6. Test Data Management

- Existing test users and an administrator account must be present in the application database for login and role-based tests.
- The positive registration flow creates and confirms a test account; the administrator deletion scenario uses a known test account. These scenarios affect database state and may require resetting or preparing test data before rerunning.
- Positive movie CRUD tests operate on a shared movie (initially titled `Test Movie`) and complete the workflow by deleting it.
- Negative movie tests use a unique title beginning with `QA-TEST-` and a generated UUID to reduce collisions. Edit-validation tests create a movie as a precondition and use cleanup logic to delete test data when applicable.
- Local poster fixtures are stored at `src/test/resources/poster_test_data/`.
- Parameterized registration and login tests exercise several invalid input values under one test method/test case ID.

Test credentials are currently used by the local automation code. For a public repository or shared environment, replace hardcoded credentials with appropriate configuration or environment variables and ensure that only non-sensitive test accounts are used.

## 7. Test Environment and Prerequisites

Before running the automation:

1. Install **JDK 21**, Maven (or use the project's configured Maven runner), and Google Chrome.
2. Make sure **Movies Collection** and its SQL Server database are available locally.
3. Start the ASP.NET Core application so that `https://localhost:7285/` is accessible in Chrome. Trust the local development HTTPS certificate where necessary.
4. Ensure the required test accounts and baseline movie data exist in the database.
5. Use a Chrome/WebDriver-compatible local setup. Selenium Manager can provision browser drivers in supported environments.

**Important:** These tests are not currently environment-independent: the URL is configured directly in `BaseTest`, and several tests depend on specific local accounts/data. Tests that create or delete accounts or movies may change subsequent run conditions.

## 8. Running the Tests

### 8.1 IntelliJ IDEA

Open the Maven project in IntelliJ, wait for dependencies to resolve, and run a test class or one of the two suite classes through the IDE's JUnit run configuration. For the positive movie CRUD workflow, run the whole `PositiveMovieCRUDTest` class.

### 8.2 Maven

From the automation project's root directory:

```bash
mvn "-Dtest=PositiveTestSuite" test; if ($LASTEXITCODE -eq 0) { mvn "-Dtest=NegativeTestSuite" test }
```

Maven Surefire reports are normally generated under `target/surefire-reports/` after test execution.

## 9. Results, Defects, and Traceability

Automation results are interpreted using the separate QA documents rather than duplicated as an authoritative execution log here.

- `docs/03-Test-Scenarios/Test-Scenarios.md`: requirement-linked test scenarios.
- `docs/04-Test-Cases/Positive-Test-Cases.md` and `Negative-Test-Cases.md`: steps, test data, expected results, and automation references.
- `docs/05-Traceability-Matrix/Movies-Collection-Requirements-Traceability-Matrix.md`: Requirement → Scenario → Test Case links.
- `docs/06-Test-Execution/Test-Execution-Results.md`: recorded observed outcomes and PASS/FAIL statuses.
- `docs/07-Defect-Reports/Defect-Reports.md`: defect descriptions, reproduction steps, and status.
- `docs/08-Confirmation-Test-Results/Confirmation-Test-Results.md`: results of retesting fixes.
- `docs/09-Regression-Testing/Regression-Testing.md`: regression activities and results.
- `docs/10-Test-Summary/Test-Summary-Report.md`: overall testing summary.

The documented execution results provided for this portfolio record **53 test cases: 51 PASS and 2 FAIL** (96.23% pass rate). The two failures, **TC-029** and **TC-036**, relate to **DEF-001** (email addresses without the application-required top-level domain). Treat these as results of the documented execution, not as a claim about the application's current state after subsequent fixes; consult confirmation and regression records for newer outcomes.

## 10. CI/CD Status

The automation project is Maven-based and can be integrated into a CI workflow. **A GitHub Actions workflow is not established by the source reviewed for this documentation**, so CI execution is not presented as implemented. The current framework assumes a locally running ASP.NET Core application, SQL Server, and Chrome environment; these dependencies must be provisioned before reliable CI execution.

## 11. Known Limitations and Maintenance Notes

- Selenium UI tests are designed for Chrome on the configured Windows/local development environment; cross-browser coverage is not established.
- Some tests use database state or ordered workflows and therefore require prepared data and careful reruns.
- The 53 documented test case IDs do not necessarily equal 53 JUnit executions: parameterized tests produce multiple invocations.
- Page selectors and exact validation messages may require maintenance when the ASP.NET UI changes.
- `BaseTest` contains the local application URL; a configurable base URL would be useful if the project is later deployed to another environment.
- Confirmation and regression results must be updated whenever defects are fixed or automation is changed.

## 12. Maintenance and Change Control

When a requirement or UI behavior changes, update the relevant test script, test scenario/test case, traceability mapping, and execution evidence. If a defect is fixed, rerun its related tests and record confirmation results before updating the defect status. Re-run affected regression tests and document any new failures.
