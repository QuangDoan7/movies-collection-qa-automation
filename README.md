# Movies Collection - QA Automation Portfolio

A **software quality assurance and UI test automation project** for the Movies Collection ASP.NET Core web application. The project demonstrates requirements-based test design, Selenium automation with Java, positive and negative testing, defect reporting, and traceability from requirements to execution results.

## Project Overview

**Application under test:** Movies Collection, a web application that supports account registration/login, movie browsing, sorting and filtering, movie CRUD operations, profile management, and administrator user management.

**Testing approach:** Functional UI testing using Selenium WebDriver, Java, JUnit, and Maven, organized with Page Object Model (POM) components and reusable helpers.

The source application and the automation tests are separate projects. This repository contains the **QA automation framework and test documentation**; the ASP.NET Core application and its SQL Server database must be running for browser tests to work.

## Tools and Technologies

| Area                   | Stack                                                                                                   |
|------------------------|---------------------------------------------------------------------------------------------------------|
| Automation             | Selenium WebDriver, Java 21                                                                             |
| Test framework         | JUnit / JUnit Platform                                                                                  |
| Build tool             | Maven                                                                                                   |
| IDE                    | IntelliJ IDEA                                                                                           |
| Browser                | Google Chrome                                                                                           |
| Application under test | ASP.NET Core (.NET 9), EF Core, SQLite                                                                  |
| Test design            | Positive/negative functional testing, equivalence partitioning, boundary value analysis, error guessing |
| Test organization      | Page Object Model, base test class, helpers, positive and negative suites                               |

Exact project dependency versions are defined in `pom.xml`.

## Test Coverage

The portfolio documents **53 test cases**, grouped into:

- **28 positive cases:** Guest, authenticated user, administrator, and an ordered movie Create → View → Edit → Delete workflow.
- **25 negative cases:** Registration and login validation (including duplicate email) plus Movie Create/Edit form validation.

The suite includes seven test classes:

```text
NoneLoginTest
UserTest
AdminTest
PositiveMovieCRUDTest
NegativeRegistrationTest
NegativeLoginTest
NegativeMovieCRUDTest
```

`PositiveTestSuite` and `NegativeTestSuite` provide logical suite groupings. Some tests are parameterized, so a documented test case can result in more than one JUnit test invocation.

## Documented Test Results

The provided Test Execution Results record the following outcome:

| Metric                | Result |
|-----------------------|--------|
| Documented test cases | 53     |
| Total test executions | 71     |
| PASS                  | 69     |
| FAIL                  | 2      |
| Pass rate             | 97.18% |

The recorded failures are **TC-029** (registration email validation) and **TC-036** (login email validation), linked to **DEF-001**: email inputs without the application-required top-level domain were not rejected by format validation.

These are the outcomes recorded in the available execution document, **not a claim that the defect remains present in the latest application build**. See the confirmation testing, regression testing, and defect reports for the status of any subsequent fixes.

## Repository Structure

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
│       │   ├── helpers/
│       │   ├── tests/
│       │   └── testsuites/
│       └── resources/poster_test_data/
├── docs/
│   ├── 01-Requirements/
│   ├── 02-Test-Plan/
│   ├── 03-Test-Scenarios/
│   ├── 04-Test-Cases/
│   ├── 05-Traceability-Matrix/
│   ├── 06-Test-Execution/
│   ├── 07-Defect-Reports/
│   ├── 08-Confirmation-Test-Results/
│   ├── 09-Regression-Testing/
│   ├── 10-Test-Summary/
│   └── 11-Automation-Testing/
│       └── Automation-Test-Documentation.md
└── README.md
```

## How to Run the Tests

### Prerequisites

1. **Java JDK 21** and **Maven** (or a configured Maven runner in IntelliJ IDEA).
2. **Google Chrome** with a compatible WebDriver environment.
3. The **Movies Collection ASP.NET Core app** running at `https://localhost:7285/`.
4. A working **SQL Server database** and the test accounts/data expected by the automation.
5. A trusted local development HTTPS certificate if the browser otherwise blocks the app.

### Execution

Open the automation project in IntelliJ IDEA and run a JUnit test class or the positive/negative suite. Alternatively, from the project root:

```bash
mvn "-Dtest=PositiveTestSuite" test; if ($LASTEXITCODE -eq 0) { mvn "-Dtest=NegativeTestSuite" test }
```

Maven Surefire reports are normally generated under `target/surefire-reports/` after test execution.

## QA Documentation

| Document                         | Location                                                                                                              |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Requirements Analysis            | [Requirements-Analysis.md](docs/01-Requirements/Requirements-Analysis.md)                                             |
| Test Plan                        | [Test-Plan.md](docs/02-Test-Plan/Test-Plan.md)                                                                        |
| Test Scenarios                   | [Test-Scenarios.md](docs/03-Test-Scenarios/Test-Scenarios.md)                                                         |
| Positive Test Cases              | [Positive-Test-Cases.md](docs/04-Test-Cases/Positive-Test-Cases.md)                                                   |
| Negative Test Cases              | [Negative-Test-Cases.md](docs/04-Test-Cases/Negative-Test-Cases.md)                                                   |
| Requirements Traceability Matrix | [Requirements-Traceability-Matrix](docs/05-Traceability-Matrix/Movies-Collection-Requirements-Traceability-Matrix.md) |
| Test Execution Results           | [Test-Execution-Results.md](docs/06-Test-Execution/Test-Execution-Results.md)                                         |
| Defect Reports                   | [Defect-Reports.md](docs/07-Defect-Reports/Defect-Reports.md)                                                         |
| Confirmation Test Results        | [Confirmation-Test-Results.md](docs/08-Confirmation-Test-Results/Confirmation-Test-Results.md)                        |
| Regression Testing               | [Regression-Testing.md](docs/09-Regression-Testing/Regression-Testing.md)                                             |
| Test Summary Report              | [Test-Summary-Report.md](docs/10-Test-Summary/Test-Summary-Report.md)                                                 |
| Automation Test Documentation    | [Automation-Test-Documentation.md](docs/11-Automation-Testing/Automation-Test-Documentation.md)                       |

## Automation Design Notes

- Page objects encapsulate commonly used Login, Registration, and Movies interactions.
- `BaseTest` initializes and closes the Chrome browser and supplies navigation/movie helpers.
- Explicit waits reduce timing-related issues in UI interactions.
- Negative movie tests use UUID-based titles and cleanup to limit test-data collisions.
- Poster upload tests use fixtures under `src/test/resources/poster_test_data/`.
- The positive CRUD test is an intentional **Create → View → Edit → Delete** sequence using shared test data.

See [Automation Test Documentation](docs/11-Automation-Testing/Automation-Test-Documentation.md) for the implementation details and limitations.

## CI/CD and Limitations

**GitHub Actions is a potential future integration**, not claimed as implemented in this README. The automation currently depends on a locally available browser, application, database, and test fixtures; those dependencies need an explicit setup for CI.

The current scope is UI functional testing. Performance, load, penetration, and cross-platform testing are not covered.

## Purpose

This repository is a personal **QA / Software Testing portfolio project** demonstrating automated UI testing as well as the supporting QA documentation workflow: requirements analysis, test planning, test design, execution, defect reporting, confirmation testing, regression testing, and test closure.
