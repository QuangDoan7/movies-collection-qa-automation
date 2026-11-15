# Movies Collection - Test Plan

## 1. Introduction

This test plan defines the testing activities for the Movies Collection application.

## 2. Objectives

- To verify that the Movies Collection application meets the functional and validation requirements as specified in the Requirements Analysis document.
- To identify and document any defects or issues in the application.

## 3. Scope

### 3.1 In Scope

- Existing positive functional tests for Guest, User, and Administrator roles.
- Positive and negative testing of user registration and authentication.
- Access control testing for restricted pages.
- Positive and negative testing of Movie CRUD (View, Add, Edit, Delete).
- Input validation testing for registration and movie forms.
- Automated regression testing using Selenium and JUnit 5.
- Planned CI test execution using GitHub Actions, subject to test environment availability.

### 3.2 Out of scope

- Performance testing
- Security penetration testing
- Load testing
- Cross-platform compatibility testing

## 4. Test Items

- The Movies Collection application, including the following components:
  - Home Page
  - Privacy Page
  - Login Page
  - Registration Page
  - Movies Page
    - Movie Details Page
    - Add Movie Page
    - Edit Movie Page
    - Delete Movie Confirmation Page
  - Profile Management Page (for authenticated users)
  - User Management Page (for administrators)

## 5. Test Approach

### Testing Types

- Functional Testing: Verify that the application meets the specified functional requirements.
- Validation Testing: Ensure that the application behaves as expected under various conditions and inputs.
- Positive Testing: Validate that the application functions correctly with valid inputs and expected user actions.
- Negative Testing: Validate that the application handles invalid inputs and unexpected user actions gracefully, providing appropriate error messages and feedback.
- Regression Testing: Re-run previously executed test cases to ensure that new changes or updates have not introduced new defects or issues.
- Automated Testing: Implement automated test scripts for repetitive test cases to improve efficiency and coverage.

### Test Design Techniques

- Equivalence Partitioning: Divide input data into equivalent partitions to reduce the number of test cases while maintaining coverage.
- Boundary Value Analysis: Test the application with input values at the boundaries of valid and invalid ranges
- Error Guessing: Use experience and intuition to identify potential error-prone areas and design test cases accordingly.

## 6. Test Environment

Operating System: Windows 11  
Browser: Google Chrome (Version 157.0.8103.51 (Official Build) (64-bit))  
Database: MoviesDBContext (SQL Server 2019)  
Application: Movies Collection (ASP.NET Core)  
Application URL: https://localhost:7285/  
Selenium: 4.52.0  
Java: JDK 21  

## 7. Test Tools

- Selenium WebDriver: For automated functional testing of the web application.
- IntelliJ IDEA: For test case management and documentation.
- JUnit 5: For test execution and assertions.
- Maven: For dependency management and automated test execution.
- GitHub: For version control and collaboration on test scripts and documentation
- GitHub Actions: For automated test execution and reporting through CI workflows.

## 8. Entry Criteria

- Test environment is set up and configured correctly.
- Test data is prepared and available for testing.
- All functional and validation requirements are documented.

## 9. Exit Criteria

- All planned test cases have been executed.
- All critical defects have been resolved or formally assessed
  and accepted for the current release.
- All identified defects (if any) have been documented with their corresponding severity, priority, and status.
- Test execution results have been documented and reviewed.
- Regression testing has been executed, and any failures
  or known defects have been recorded.
- A Test Summary Report has been prepared.

## 10. Test Deliverables

- Requirements Analysis
- Test Plan
- Test Scenarios
- Test Cases
- Requirements Traceability Matrix
- Test Execution Results
- Defect Reports
- Regression Test Results
- Test Summary Report

## 11. Roles and Responsibilities

As this is an individual project, the roles of Product Owner, Developer, and Tester are all performed by the same individual, who is Thanh Quang Doan.

Responsibilities:
- Test planning
- Test case design
- Test execution
- Defect reporting and tracking
- Regression testing
- Test summary report

## 12. Schedule

| Activity                          | Estimated Duration |
|-----------------------------------|--------------------|
| Requirements Analysis             | Completed          |
| Test Planning                     | 1 day              |
| Test Scenario & Test Case Design  | 1-2 days           |
| Test Execution & Defect Reporting | 1-2 days           |
| Defect Fixing & Retesting         | As needed          |
| Regression Testing                | 1 day              |
| Test Closure & Summary Report     | 1 day              |