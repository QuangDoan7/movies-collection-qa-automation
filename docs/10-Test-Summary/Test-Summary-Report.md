# Movies Collection - Test Summary Report

## 1. Test Summary Information

**Environment:** Localhost - https://localhost:7285/  
**Operating System:** Windows 11  
**Application:** Movies Collection (ASP.NET Core)  
**Database:** SQL Server 2019 (`MoviesDBContext`)  
**Browser:** Google Chrome (Version 157.0.8103.51 (Official Build) (64-bit))  
**Tools:** Selenium WebDriver, JUnit 5, Maven  
**Final Test Status:** PASS  

## 2. Testing Objective

The objective of this testing effort was to validate the functionality, performance, and reliability of the Movies Collection web application. The tests aimed to ensure that all features work as expected, and that it meets the specified requirements.

## 3. Test Scope

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

## 4. Test Execution Summary

| Metric                    | Result |
|---------------------------|--------|
| Total Test Cases          | 53     |
| Total Test Executed       | 71     |
| Initial Passed            | 69     |
| Initial Failed            | 2      |
| Defects Identified        | 1      |
| Defects Resolved          | 1      |
| Confirmation Tests Passed | 1      |
| Regression Tests Executed | 71     |
| Regression Tests Passed   | 71     |
| New Regression Defects    | 0      |
| Overall Test Status       | PASS   |

## 5. Defects Summary
| Defect ID | Description                                                         | Severity | Priority | Final Status |
|-----------|---------------------------------------------------------------------|----------|----------|--------------|
| DEF-001   | Registration/Login accepts Email without Required Top-level Domain. | Medium   | Medium   | Resolved     |

## 6. Confirmation Testing Summary

| Test Case | Actual Result                                                                                    | Status | Defect ID |
|-----------|--------------------------------------------------------------------------------------------------|--------|-----------|
| TC-029    | Registration rejected `test@domain` and displayed the expected invalid-email validation message. | PASS   | DEF-001   |
| TC-036    | Login rejected `userABC123@mail` and displayed the expected invalid-email validation message.    | PASS   | DEF-001   |

## 7. Regression Testing Summary

| Test Case | Actual Result                                                                                                                                                | Status |
|-----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
| TC-001    | The home page loaded with browser title `Movies Collection` and heading `Greetings!`.                                                                        | PASS   |
| TC-002    | Unauthenticated access to Movies redirected to `/Identity/Account/Login`; login heading and email field were displayed.                                      | PASS   |
| TC-003    | The Privacy page displayed `Privacy Policy` and the expected sample-project information.                                                                     | PASS   |
| TC-004    | The Login page opened with heading `Log in` and the email input field.                                                                                       | PASS   |
| TC-005    | The Registration page opened with heading `Create a new account` and the email input field.                                                                  | PASS   |
| TC-006    | The registration form submitted successfully, redirected to `RegisterConfirmation`, and displayed `Thank you for confirming your email.` after confirmation. | PASS   |
| TC-007    | Valid user credentials authenticated successfully; `Hello userABC123@mail.com!` and the Logout option appeared.                                              | PASS   |
| TC-008    | The authenticated user accessed Movies; the `Movies` heading and movie cards were displayed.                                                                 | PASS   |
| TC-009    | Movie cards were sorted by title ascending by default and descending after selecting the Title sort control.                                                 | PASS   |
| TC-010    | Movie cards were sorted by release year in ascending order and then descending order.                                                                        | PASS   |
| TC-011    | Selecting a genre displayed matching movie cards; the `All` option was available.                                                                            | PASS   |
| TC-012    | The authenticated user opened Privacy and saw `Privacy Policy` and the expected information.                                                                 | PASS   |
| TC-013    | The account management page opened with heading `Manage your account` and its settings information.                                                          | PASS   |
| TC-014    | Logout ended the user session and returned to the home page; the Login option was visible.                                                                   | PASS   |
| TC-015    | Valid administrator credentials authenticated successfully; the administrator greeting and Logout option appeared.                                           | PASS   |
| TC-016    | The administrator accessed Movies; the `Movies` heading and movie cards were displayed.                                                                      | PASS   |
| TC-017    | The administrator observed title sorting ascending by default and descending after selecting Title.                                                          | PASS   |
| TC-018    | The administrator sorted movie cards by release year in ascending and descending order.                                                                      | PASS   |
| TC-019    | The administrator selected genres and saw matching movie cards; `All` was available.                                                                         | PASS   |
| TC-020    | The administrator opened Privacy and saw `Privacy Policy` and the expected information.                                                                      | PASS   |
| TC-021    | The administrator accessed account management and saw `Manage your account` and account settings.                                                            | PASS   |
| TC-022    | Logout ended the administrator session and returned to the home page with the Login option visible.                                                          | PASS   |
| TC-023    | The User Management page opened and displayed heading `User Management`.                                                                                     | PASS   |
| TC-024    | The administrator confirmed deletion of `test@mail.com`; the account no longer appeared in the user list.                                                    | PASS   |
| TC-025    | The `Add New Movie` form accepted valid details and a poster; a `Test Movie` card appeared in Movies.                                                        | PASS   |
| TC-026    | The `Test Movie` card opened `Movies/Details` and displayed `Movie Details` and the correct title.                                                           | PASS   |
| TC-027    | The movie card displayed updated title `Movie Title Changed`, revised details and genres; the poster URL changed.                                            | PASS   |
| TC-028    | Deletion was confirmed for `Movie Title Changed`; the movie was absent from the Movies list afterward.                                                       | PASS   |
| TC-029    | The registration email validator did not reject the missing-TLD input `test@domain` with the required invalid-email message.                                 | PASS   |
| TC-030    | Leaving Registration Email blank displayed `The Email field is required.`.                                                                                   | PASS   |
| TC-031    | Registration with existing email `userABC123@mail.com` was rejected with `Email 'userABC123@mail.com' is already taken.`.                                    | PASS   |
| TC-032    | Registration rejected the tested passwords outside the required length/character policy and displayed the password validation message.                       | PASS   |
| TC-033    | Leaving Registration Password blank displayed `The Password field is required.`.                                                                             | PASS   |
| TC-034    | A mismatched confirmation password displayed `The password and confirmation password do not match.`.                                                         | PASS   |
| TC-035    | Leaving Confirm Password empty while providing a password displayed the password confirmation mismatch message.                                              | PASS   |
| TC-036    | The login email validator did not reject the missing-TLD input `userABC123@mail` with the required invalid-email message.                                    | PASS   |
| TC-037    | Leaving Login Email blank displayed `The Email field is required.`.                                                                                          | PASS   |
| TC-038    | The tested invalid password attempts were rejected with `Invalid login attempt.`.                                                                            | PASS   |
| TC-039    | Leaving Login Password blank displayed `The Password field is required.`.                                                                                    | PASS   |
| TC-040    | An incorrect password for `userABC123@mail.com` was rejected with `Invalid login attempt.`.                                                                  | PASS   |
| TC-041    | Submitting a movie without Title displayed `The Title field is required.`.                                                                                   | PASS   |
| TC-042    | Submitting a movie without Genre displayed `The SelectedGenres field is required.`.                                                                          | PASS   |
| TC-043    | A Release Year later than the current year was rejected with the release-year range validation message.                                                      | PASS   |
| TC-044    | Release Year `1899` was rejected with the release-year range validation message.                                                                             | PASS   |
| TC-045    | A future Last Watched Year was rejected with the last-watched-year range validation message.                                                                 | PASS   |
| TC-046    | Personal Rating `0` was rejected with `Rating must be between 1 and 100.`.                                                                                   | PASS   |
| TC-047    | Personal Rating `101` was rejected with `Rating must be between 1 and 100.`.                                                                                 | PASS   |
| TC-048    | Clearing the Title of an existing movie displayed `The Title field is required.`; test data was cleaned up.                                                  | PASS   |
| TC-049    | Setting an existing movie’s Release Year beyond the current year displayed the range validation message; test data was cleaned up.                           | PASS   |
| TC-050    | Setting an existing movie’s Release Year to `1899` displayed the range validation message; test data was cleaned up.                                         | PASS   |
| TC-051    | Setting an existing movie’s Last Watched Year in the future displayed the range validation message; test data was cleaned up.                                | PASS   |
| TC-052    | Setting an existing movie’s Personal Rating to `0` displayed `Rating must be between 1 and 100.`; test data was cleaned up.                                  | PASS   |
| TC-053    | Setting an existing movie’s Personal Rating to `101` displayed `Rating must be between 1 and 100.`; test data was cleaned up.                                | PASS   |

## 8. Test Closure

All planned test cases have been executed, and the results have been documented. The application has passed all regression tests, indicating that it is functioning as expected. No new defects were identified during this testing cycle. All identified defects were resolved and successfully confirmed through retesting. Therefore, the test cycle is considered complete.

## 9. Conclusion

Based on the test summary report, all test cases have been executed and passed successfully. The application has demonstrated expected behavior across all tested scenarios, and no new defects were identified. The testing objectives have been met, and the application is considered stable within the defined scope.