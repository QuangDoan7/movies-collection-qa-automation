# Negative Functional Test Cases

## Registration Validation Tests

### TC-029 - Registration Rejects Invalid Email Formats

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-029                                                                              |
| Module               | Registration / Validation                                                           |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeRegistrationTest.invalidEmailRegisterTest(String)                           |

**Test Data:**

- Email input values: `test`, `test@`, `@domain.com`, `testdomain.com`, `test@domain`.

**Test Steps:**
1. Open the Registration page.
2. For each test input, enter the email and valid matching passwords `Test123!` in Password and Confirm Password.
3. Submit the form and read the Email validation message.

**Expected Result:**
- For every invalid email input, the Email field displays `The Email field is not a valid e-mail address.`.

### TC-030 - Registration Rejects Empty Email

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-030                                                                              |
| Module               | Registration / Validation                                                           |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeRegistrationTest.emptyEmailRegisterTest()                                   |

**Test Steps:**
1. Open Registration.
2. Leave Email empty; enter valid matching passwords `Test123!`.
3. Submit and read the Email validation message.

**Expected Result:**
- The Email field displays `The Email field is required.`.

### TC-031 - Registration Rejects Duplicate Email Address

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-031                                                                              |
| Module               | Registration / Validation                                                           |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated; account `userABC123@mail.com` already exists. |
| Automation Reference | NegativeRegistrationTest.duplicateEmailRegisterTest()                               |

**Test Data:**

- Email: `userABC123@mail.com` (already registered).
- Password: `Test123!@#`.
- Confirm Password: `Test123!@#`.

**Test Steps:**
1. Open the Registration page.
2. Enter the existing email address `userABC123@mail.com` in the Email field.
3. Enter `Test123!@#` in Password and Confirm Password.
4. Submit the registration form.
5. Inspect the registration validation summary.

**Expected Result:**
- The registration is rejected, and the validation summary displays `Email 'userABC123@mail.com' is already taken.`.

### TC-032 - Registration Rejects Passwords Not Meeting Policy

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-032                                                                              |
| Module               | Registration / Validation                                                           |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeRegistrationTest.invalidPasswordRegisterTest(String)                        |

**Test Data:**

- Password values: `Abc123!` (too short), `Abcdf12345!@#` (too long), `abc123!@#` (no uppercase), `ABC123!@#` (no lowercase), `ABCabc!@#` (no digit), `Abcde!@#$` (no digit).

**Test Steps:**
1. Open Registration.
2. For each password value, enter email `test@mail.com` and the same value in Password and Confirm Password.
3. Submit and examine the Password validation message.

**Expected Result:**
- The Password field displays `The Password must be 8~12 characters long and contain at least 1 uppercase letter, 1 lowercase letter, 1 numeric character, and 1 special character.`.

### TC-033 - Registration Rejects Empty Password

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-033                                                                              |
| Module               | Registration / Validation                                                           |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeRegistrationTest.emptyPasswordRegisterTest()                                |

**Test Steps:**
1. Open Registration.
2. Enter email `test@mail.com`; leave Password and Confirm Password empty.
3. Submit and inspect Password validation.

**Expected Result:**
- The Password field displays `The Password field is required.`.

### TC-034 - Registration Rejects Mismatched Confirm Password

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-034                                                                              |
| Module               | Registration / Validation                                                           |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeRegistrationTest.mismatchedConfirmPasswordRegisterTest()                    |

**Test Steps:**
1. Open Registration.
2. Enter email `test@mail.com`, Password `Test123!@#`, and Confirm Password `Different123!@#`.
3. Submit and inspect confirmation validation.

**Expected Result:**
- The Confirm Password validation displays `The password and confirmation password do not match.`.

### TC-035 - Registration Rejects Empty Confirm Password

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-035                                                                              |
| Module               | Registration / Validation                                                           |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeRegistrationTest.emptyConfirmPasswordRegisterTest()                         |

**Test Steps:**
1. Open Registration.
2. Enter email `test@mail.com` and Password `Test123!@#`; leave Confirm Password empty.
3. Submit and inspect confirmation validation.

**Expected Result:**
- The Confirm Password validation displays `The password and confirmation password do not match.`.

## Login Validation Tests

### TC-036 - Login Rejects Invalid Email Formats

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-036                                                                              |
| Module               | Authentication / Validation                                                         |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeLoginTest.invalidEmailLoginTest(String)                                     |

**Test Data:**

- Email input values: `userABC123`, `userABC123@`, `@mail.com`, `userABC123mail.com`, `userABC123@mail`.

**Test Steps:**
1. Open Login.
2. For each email input, enter the same test password `userABC123!`.
3. Submit and inspect the Email validation message.

**Expected Result:**
- For every invalid email input, the Email field displays `The Email field is not a valid e-mail address.`.

### TC-037 - Login Rejects Empty Email

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-037                                                                              |
| Module               | Authentication / Validation                                                         |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeLoginTest.emptyEmailLoginTest()                                             |

**Test Steps:**
1. Open Login.
2. Leave Email empty; enter Password `Test123!@#`.
3. Submit and inspect the Email validation message.

**Expected Result:**
- The Email field displays `The Email field is required.`.

### TC-038 - Login Rejects Invalid Password Attempts

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-038                                                                              |
| Module               | Authentication / Validation                                                         |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeLoginTest.invalidPasswordLoginTest(String)                                  |

**Test Data:**

- Password values: `Abc123!`, `Abcdf12345!@#`, `abc123!@#`, `ABC123!@#`, `ABCabc!@#`, `Abcde!@#$`.

**Test Steps:**
1. Open Login.
2. For each password value, enter email `test@mail.com` and the supplied password.
3. Submit and inspect the login error.

**Expected Result:**
- The application displays `Invalid login attempt.`.

### TC-039 - Login Rejects Empty Password

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-039                                                                              |
| Module               | Authentication / Validation                                                         |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeLoginTest.emptyPasswordLoginTest()                                          |

**Test Steps:**
1. Open Login.
2. Enter email `userABC123@mail.com` and leave Password empty.
3. Submit and inspect the Password validation message.

**Expected Result:**
- The Password field displays `The Password field is required.`.

### TC-040 - Login Rejects Incorrect Password

| Field                | Description                                                                         |
|----------------------|-------------------------------------------------------------------------------------|
| Test Case ID         | TC-040                                                                              |
| Module               | Authentication / Validation                                                         |
| Test Type            | Negative Functional Test                                                            |
| Preconditions        | Movies Collection is running at https://localhost:7285/; user is not authenticated. |
| Automation Reference | NegativeLoginTest.wrongPasswordLoginTest()                                          |

**Test Steps:**
1. Open Login.
2. Enter email `userABC123@mail.com` and incorrect password `adminABC123!`.
3. Submit and inspect the authentication error.

**Expected Result:**
- The application displays `Invalid login attempt.`.

## Movie Create Validation Tests

### TC-041 - Create Movie Without Title

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-041                                                                                                |
| Module               | Movies / Create Validation                                                                            |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testCreatingMovieWithoutTitle()                                                 |

**Test Data:**

- Use otherwise valid movie data (Release Year `2017` or `2020` as in automation, Duration `128`, Last Watched Year `2020` or `2025`, Rating `85`, description, genres `Crime` and `Documentary` when required, and poster `popcorn.png`).

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the **Add New Movie** form.
3. Leave Title empty while entering other valid movie fields, select `Crime` and `Documentary`, and upload `popcorn.png`.
4. Submit the form and inspect its validation message.

**Expected Result:**
- The form displays `The Title field is required.` for the affected field.

### TC-042 - Create Movie Without Genre

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-042                                                                                                |
| Module               | Movies / Create Validation                                                                            |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testCreatingMovieWithoutGenres()                                                |

**Test Data:**

- Use otherwise valid movie data (Release Year `2017` or `2020` as in automation, Duration `128`, Last Watched Year `2020` or `2025`, Rating `85`, description, genres `Crime` and `Documentary` when required, and poster `popcorn.png`).

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the **Add New Movie** form.
3. Enter a unique title and other valid fields but do not select a genre.
4. Submit the form and inspect its validation message.

**Expected Result:**
- The form displays `The SelectedGenres field is required.` for the affected field.

### TC-043 - Create Movie With Future Release Year

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-043                                                                                                |
| Module               | Movies / Create Validation                                                                            |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testCreatingMovieWithFutureReleaseYear()                                        |

**Test Data:**

- Use otherwise valid movie data (Release Year `2017` or `2020` as in automation, Duration `128`, Last Watched Year `2020` or `2025`, Rating `85`, description, genres `Crime` and `Documentary` when required, and poster `popcorn.png`).

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the **Add New Movie** form.
3. Enter a unique title and set Release Year to the current year + 1; complete remaining fields with valid values.
4. Submit the form and inspect its validation message.

**Expected Result:**
- The form displays `Release Year must be between 1900 and the current year {year}.` for the affected field.

### TC-044 - Create Movie With Release Year Before 1900

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-044                                                                                                |
| Module               | Movies / Create Validation                                                                            |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testCreatingMovieWithReleaseYearBefore1900()                                    |

**Test Data:**

- Use otherwise valid movie data (Release Year `2017` or `2020` as in automation, Duration `128`, Last Watched Year `2020` or `2025`, Rating `85`, description, genres `Crime` and `Documentary` when required, and poster `popcorn.png`).

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the **Add New Movie** form.
3. Enter a unique title and Release Year `1899`; complete remaining fields with valid values.
4. Submit the form and inspect its validation message.

**Expected Result:**
- The form displays `Release Year must be between 1900 and the current year {year}.` for the affected field.

### TC-045 - Create Movie With Last Watched Year in Future

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-045                                                                                                |
| Module               | Movies / Create Validation                                                                            |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testCreatingMovieWithWatchedYearInTheFuture()                                   |

**Test Data:**

- Use otherwise valid movie data (Release Year `2017` or `2020` as in automation, Duration `128`, Last Watched Year `2020` or `2025`, Rating `85`, description, genres `Crime` and `Documentary` when required, and poster `popcorn.png`).

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the **Add New Movie** form.
3. Enter a unique title, Release Year `2020`, and Last Watched Year equal to the current year + 1; complete remaining fields with valid values.
4. Submit the form and inspect its validation message.

**Expected Result:**
- The form displays `Last Watched Year must be between the release year 2020 and the current year {year}.` for the affected field.

### TC-046 - Create Movie With Rating Below Minimum

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-046                                                                                                |
| Module               | Movies / Create Validation                                                                            |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testCreatingMovieWithPersonalRatingBelow1()                                     |

**Test Data:**

- Use otherwise valid movie data (Release Year `2017` or `2020` as in automation, Duration `128`, Last Watched Year `2020` or `2025`, Rating `85`, description, genres `Crime` and `Documentary` when required, and poster `popcorn.png`).

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the **Add New Movie** form.
3. Enter a unique title and Personal Rating `0`, with all other fields valid.
4. Submit the form and inspect its validation message.

**Expected Result:**
- The form displays `Rating must be between 1 and 100.` for the affected field.

### TC-047 - Create Movie With Rating Above Maximum

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-047                                                                                                |
| Module               | Movies / Create Validation                                                                            |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testCreatingMovieWithPersonalRatingOver100()                                    |

**Test Data:**

- Use otherwise valid movie data (Release Year `2017` or `2020` as in automation, Duration `128`, Last Watched Year `2020` or `2025`, Rating `85`, description, genres `Crime` and `Documentary` when required, and poster `popcorn.png`).

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the **Add New Movie** form.
3. Enter a unique title and Personal Rating `101`, with all other fields valid.
4. Submit the form and inspect its validation message.

**Expected Result:**
- The form displays `Rating must be between 1 and 100.` for the affected field.

## Movie Edit Validation Tests

### TC-048 - Edit Movie With Empty Title

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-048                                                                                                |
| Module               | Movies / Edit Validation                                                                              |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testUserEditingMovieWithEmptyTitle()                                            |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Create a fully populated test movie using a unique `QA-TEST-<UUID>` title.
3. Open its movie card and navigate to the Edit form.
4. Clear the Title field.
5. Submit the form and inspect validation.
6. After testing, delete the test movie in cleanup.

**Expected Result:**
- The Edit form displays `The Title field is required.` for the affected field.

### TC-049 - Edit Movie With Future Release Year

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-049                                                                                                |
| Module               | Movies / Edit Validation                                                                              |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testUserEditingMovieWithFutureReleaseYear()                                     |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Create a fully populated test movie using a unique `QA-TEST-<UUID>` title.
3. Open its movie card and navigate to the Edit form.
4. Set Release Year to the current year + 1.
5. Submit the form and inspect validation.
6. After testing, delete the test movie in cleanup.

**Expected Result:**
- The Edit form displays `Release Year must be between 1900 and the current year {year}.` for the affected field.

### TC-050 - Edit Movie With Release Year Before 1900

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-050                                                                                                |
| Module               | Movies / Edit Validation                                                                              |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testUserEditingMovieWithReleaseYearBefore1900()                                 |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Create a fully populated test movie using a unique `QA-TEST-<UUID>` title.
3. Open its movie card and navigate to the Edit form.
4. Set Release Year to `1899`.
5. Submit the form and inspect validation.
6. After testing, delete the test movie in cleanup.

**Expected Result:**
- The Edit form displays `Release Year must be between 1900 and the current year {year}.` for the affected field.

### TC-051 - Edit Movie With Future Last Watched Year

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-051                                                                                                |
| Module               | Movies / Edit Validation                                                                              |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testUserEditingMovieWithWatchedYearInTheFuture()                                |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Create a fully populated test movie using a unique `QA-TEST-<UUID>` title.
3. Open its movie card and navigate to the Edit form.
4. Set Last Watched Year to the current year + 1.
5. Submit the form and inspect validation.
6. After testing, delete the test movie in cleanup.

**Expected Result:**
- The Edit form displays `Last Watched Year must be between the release year {releaseYear} and the current year {year}.` for the affected field.

### TC-052 - Edit Movie With Rating Below Minimum

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-052                                                                                                |
| Module               | Movies / Edit Validation                                                                              |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testUserEditingMovieWithPersonalRatingBelow1()                                  |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Create a fully populated test movie using a unique `QA-TEST-<UUID>` title.
3. Open its movie card and navigate to the Edit form.
4. Set Personal Rating to `0`.
5. Submit the form and inspect validation.
6. After testing, delete the test movie in cleanup.

**Expected Result:**
- The Edit form displays `Rating must be between 1 and 100.` for the affected field.

### TC-053 - Edit Movie With Rating Above Maximum

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-053                                                                                                |
| Module               | Movies / Edit Validation                                                                              |
| Test Type            | Negative Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | NegativeMovieCRUDTest.testUserEditingMovieWithPersonalRatingOver100()                                 |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Create a fully populated test movie using a unique `QA-TEST-<UUID>` title.
3. Open its movie card and navigate to the Edit form.
4. Set Personal Rating to `101`.
5. Submit the form and inspect validation.
6. After testing, delete the test movie in cleanup.

**Expected Result:**
- The Edit form displays `Rating must be between 1 and 100.` for the affected field.