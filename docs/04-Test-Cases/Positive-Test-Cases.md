# Positive Functional Test Cases

## Unauthenticated User Tests

### TC-001 - Home Page Access Without Login
| Field                | Description                                                                           |
|----------------------|---------------------------------------------------------------------------------------|
| Test Case ID         | TC-001                                                                                |
| Module               | Home                                                                                  |
| Test Type            | Positive Functional Test                                                              |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`; User is not authenticated. |
| Automation Reference | `NoneLoginTest.testHomePageAccess()`                                                  |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Observe the home page title and heading.

**Expected Result:**
- The browser title should be `Movies Collection`.
- The home page should load successfully, displaying the heading `Greetings!`.

### TC-002 - Movies Page Access Without Login
| Field                | Description                                                                           |
|----------------------|---------------------------------------------------------------------------------------|
| Test Case ID         | TC-002                                                                                |
| Module               | Authentication / Access Control                                                       |
| Test Type            | Positive Functional Test                                                              |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`; User is not authenticated. |
| Automation Reference | `NoneLoginTest.testMoviesPageAccess()`                                                |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Movies** link in the navigation bar.
4. Observe the requirement for authentication and the redirection to the login page.

**Expected Result:**
- The URL should contain `Identity/Account/Login` indicating redirection to the login page.
- The heading on the login page should be `Log in`.
- The email input field should be present on the login page, indicating that the user is prompted to log in before accessing the movies page.

### TC-003 - Privacy Page Access Without Login
| Field                | Description                                                                           |
|----------------------|---------------------------------------------------------------------------------------|
| Test Case ID         | TC-003                                                                                |
| Module               | Privacy                                                                               |
| Test Type            | Positive Functional Test                                                              |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`; User is not authenticated. |
| Automation Reference | `NoneLoginTest.testPrivacyPageAccess()`                                               |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Privacy** link in the navigation bar.
4. Observe the page title and heading.

**Expected Result:**
- The privacy page should load successfully, displaying the heading `Privacy Policy`.
- The content of the privacy page should be visible, which includes the information showing `This website is built as a sample project for learning purposes`.

### TC-004 - Login Page Access Without Login
| Field                | Description                                                                           |
|----------------------|---------------------------------------------------------------------------------------|
| Test Case ID         | TC-004                                                                                |
| Module               | Authentication                                                                        |
| Test Type            | Positive Functional Test                                                              |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`; User is not authenticated. |
| Automation Reference | `NoneLoginTest.testLoginPageAccess()`                                                 |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Observe the page title and heading.

**Expected Result:**
- The URL should contain `Identity/Account/Login` indicating redirection to the login page.
- The heading on the login page should be `Log in`.
- The email input field should be present on the login page, indicating that the user is prompted to log in.

### TC-005 - Register Page Access Without Login
| Field                | Description                                                                           |
|----------------------|---------------------------------------------------------------------------------------|
| Test Case ID         | TC-005                                                                                |
| Module               | Registration                                                                          |
| Test Type            | Positive Functional Test                                                              |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`; User is not authenticated. |
| Automation Reference | `NoneLoginTest.testRegisterPageAccess()`                                              |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Register** link in the navigation bar.
4. Observe the page title and heading.

**Expected Result:**
- The URL should contain `Identity/Account/Register` indicating redirection to the register page.
- The heading on the register page should be `Create a new account`.
- The email input field should be present on the register page, indicating that the user is prompted to register.

### TC-006 - Register New User Account
| Field                | Description                                                                           |
|----------------------|---------------------------------------------------------------------------------------|
| Test Case ID         | TC-006                                                                                |
| Module               | Registration                                                                          |
| Test Type            | Positive Functional Test                                                              |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`; User is not authenticated. |
| Automation Reference | `NoneLoginTest.testRegisterNewUserAccount()`                                          |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Register** link in the navigation bar.
4. Enter `test@mail.com` in the Email field, and `Test123!` in both the Password and Confirm Password fields.
5. Submit the registration form.
6. Verify the redirection to the email confirmation page and check for the message indicating that the user needs to confirm their email.
7. Click `Click here to confirm your account`.
8. Verify that the message `Thank you for confirming your email.` is displayed, indicating that the email has been successfully confirmed.

**Expected Result:**
- The URL should contain `Identity/Account/Register` indicating redirection to the register page.
- The heading on the register page should be `Create a new account`.
- The email input field should be present on the register page, indicating that the user is prompted to register.
- After submitting the registration form, the user should be redirected to the URL containing `Identity/Account/RegisterConfirmation` indicating redirection to the email confirmation page.
- The heading on the email confirmation page should be `Register Confirmation`.
- The message on the email confirmation page should indicate that the user needs to confirm their email.
- After confirmation, the user should see a success message of `Thank you for confirming your email.` indicating that the email has been confirmed successfully.

## Authenticated User Tests

### TC-007 - User Login with Valid Credentials

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-007                                                     |
| Module               | Authentication                                             |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserLogin()`                                 |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.

**Expected Result:**
- The user is redirected to the home page and that the user is logged in successfully with the correct username, which is `Hello userABC123@mail.com!`, displayed in the navigation bar.
- There is a `Logout` link in the navigation bar, indicating that the user is logged in and can log out.

### TC-008 - User's Movies Page Access

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-008                                                     |
| Module               | Movies / Access Control                                    |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserMoviesPageAccess()`                      |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Verify that the user is redirected to the Movies page and that the user's movies are displayed.

**Expected Result:**
- The user is redirected to the Movies page and that the user's movies are displayed.
- The page heading should be `Movies` and the URL should contain `Movies` indicating that the user is on the Movies page.
- The user's movies should be displayed in a grid format, with each movie represented by a card containing the movie's title, release year, genre, and other relevant information.

### TC-009 - User's Movies Page - Title Sorting Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-009                                                     |
| Module               | Movies / Sorting                                           |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserMoviesPageTitleSorting()`                |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Observe that the movies should be sorted in ascending order by title by default.
8. Click on the **Title** sort button to sort the movies by title in descending order.

**Expected Result:**
- By default, after navigating to the Movies page, the movies should be sorted in ascending order by title.
- After clicking the `Title` sort button once, the movies should be sorted in descending order by title.
- The sorting functionality should work correctly, and the movies should be displayed in the correct order based on the selected sorting option.

### TC-010 - User's Movies Page - Release Year Sorting Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-010                                                     |
| Module               | Movies / Sorting                                           |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserMoviesPageReleaseYearSorting()`          |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Click on the **Release Year** sort button to sort the movies by release year in ascending order.
8. Click on the **Release Year** sort button again to sort the movies by release year in descending order.

**Expected Result:**
- By default, after navigating to the Movies page, the movies should be sorted in ascending order by title.
- After clicking the `Release Year` sort button once, the movies should be sorted in ascending order by release year.
- After clicking the `Release Year` sort button again, the movies should be sorted in descending order by release year.
- The sorting functionality should work correctly, and the movies should be displayed in the correct order based on the selected sorting option.

### TC-011 - User's Movies Page - Filter by Genre Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-011                                                     |
| Module               | Movies / Filtering                                         |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserMoviesPageFilterByGenre()`               |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Select each genre from the genre filter dropdown one by one and observe the filtered results.
8. For each selected genre, verify that the displayed movies belong to the selected genre.

**Expected Result:**
- The genre filter dropdown should contain all available genres, including an `All` option.
- By default, all movies should be displayed regardless of genre.
- When a specific genre is selected, the movies displayed on the page should belong to that genre.

### TC-012 - User's Privacy Page Access

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-012                                                     |
| Module               | Privacy                                                    |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserPrivacyPageAccess()`                     |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.
6. Click on the **Privacy** link in the navigation bar.
7. Verify that the Privacy page is displayed correctly.

**Expected Result:**
- The Privacy page should load successfully, displaying the heading `Privacy Policy`.
- The content of the Privacy page should be visible, which includes the information showing `This website is built as a sample project for learning purposes`.

### TC-013 - User's Manage Account Page Access

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-013                                                     |
| Module               | Profile Management                                         |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserProfilePageAccess()`                     |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.
6. Click on the `Hello userABC123@mail.com!` link in the navigation bar.
7. Verify that the Profile page is displayed correctly.

**Expected Result:**
- The Profile page should load successfully, displaying the heading `Manage your account`.
- The content of the Profile page should be visible, which includes the information showing `Change your account settings below. You can also manage your two-factor authentication settings and view your recovery codes.`

### TC-014 - User Logout Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-014                                                     |
| Module               | Authentication                                             |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `UserTest.testUserLogout()`                                |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `userABC123@mail.com` in the Email field and `User123!` in the Password field.
5. Submit the login form.
6. Click on the **Logout** link in the navigation bar.
7. Verify that the user is logged out and redirected to the home page.

**Expected Result:**
- The user should be logged out successfully and redirected to the home page.
- The URL should be `https://localhost:7285/`, with the browser title `Movies Collection` and the heading `Greetings!` displayed on the home page.
- The `Login` link should be visible in the navigation bar, indicating that the user is no longer logged in.

## Admin User Tests

### TC-015 - Admin Login with Valid Credentials

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-015                                                     |
| Module               | Authentication                                             |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminLogin()`                               |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.

**Expected Result:**
- The user is redirected to the home page and that the user is logged in successfully with the correct username, which is `Hello adminABC123@mail.com!`, displayed in the navigation bar.
- There is a `Logout` link in the navigation bar, indicating that the user is logged in and can log out.

### TC-016 - Admin's Movies Page Access

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-016                                                     |
| Module               | Movies / Access Control                                    |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminMoviesPageAccess()`                    |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Verify that the admin is redirected to the Movies page and that the admin's movies are displayed.

**Expected Result:**
- The admin is redirected to the Movies page and that the admin's movies are displayed.
- The page heading should be `Movies` and the URL should contain `Movies` indicating that the admin is on the Movies page.
- The admin's movies should be displayed in a grid format, with each movie represented by a card containing the movie's title, release year, genre, and other relevant information.

### TC-017 - Admin's Movies Page - Title Sorting Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-017                                                     |
| Module               | Movies / Sorting                                           |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminMoviesPageTitleSorting()`              |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Observe that the movies should be sorted in ascending order by title by default.
8. Click on the **Title** sort button to sort the movies by title in descending order.

**Expected Result:**
- By default, after navigating to the Movies page, the movies should be sorted in ascending order by title.
- After clicking the `Title` sort button once, the movies should be sorted in descending order by title.
- The sorting functionality should work correctly, and the movies should be displayed in the correct order based on the selected sorting option.

### TC-018 - Admin's Movies Page - Release Year Sorting Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-018                                                     |
| Module               | Movies / Sorting                                           |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminMoviesPageReleaseYearSorting()`        |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Click on the **Release Year** sort button to sort the movies by release year in ascending order.
8. Click on the **Release Year** sort button again to sort the movies by release year in descending order.

**Expected Result:**
- By default, after navigating to the Movies page, the movies should be sorted in ascending order by title.
- After clicking the `Release Year` sort button once, the movies should be sorted in ascending order by release year.
- After clicking the `Release Year` sort button again, the movies should be sorted in descending order by release year.
- The sorting functionality should work correctly, and the movies should be displayed in the correct order based on the selected sorting option.

### TC-019 - Admin's Movies Page - Filter by Genre Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-019                                                     |
| Module               | Movies / Filtering                                         |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminMoviesPageFilterByGenre()`             |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **Movies** link in the navigation bar.
7. Select each genre from the genre filter dropdown one by one and observe the filtered results.
8. For each selected genre, verify that the displayed movies belong to the selected genre.

**Expected Result:**
- The genre filter dropdown should contain all available genres, including an `All` option.
- By default, all movies should be displayed regardless of genre.
- When a specific genre is selected, the movies displayed on the page should belong to that genre.

### TC-020 - Admin's Privacy Page Access

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-020                                                     |
| Module               | Privacy                                                    |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminPrivacyPageAccess()`                   |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **Privacy** link in the navigation bar.
7. Verify that the Privacy page is displayed correctly.

**Expected Result:**
- The Privacy page should load successfully, displaying the heading `Privacy Policy`.
- The content of the Privacy page should be visible, which includes the information showing `This website is built as a sample project for learning purposes`.

### TC-021 - Admin's Manage Account Page Access

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-021                                                     |
| Module               | Profile Management                                         |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminProfilePageAccess()`                   |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the `Hello adminABC123@mail.com!` link in the navigation bar.
7. Verify that the Profile page is displayed correctly.

**Expected Result:**
- The Profile page should load successfully, displaying the heading `Manage your account`.
- The content of the Profile page should be visible, which includes the information showing `Change your account settings below. You can also manage your two-factor authentication settings and view your recovery codes.`

### TC-022 - Admin Logout Functionality

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-022                                                     |
| Module               | Authentication                                             |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminLogout()`                              |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **Logout** link in the navigation bar.
7. Verify that the admin is logged out and redirected to the home page.

**Expected Result:**
- The admin should be logged out successfully and redirected to the home page.
- The URL should be `https://localhost:7285/`, with the browser title `Movies Collection` and the heading `Greetings!` displayed on the home page.
- The `Login` link should be visible in the navigation bar, indicating that the admin is no longer logged in.

### TC-023 - Admin's User Management Page Access

| Field                | Description                                                |
|----------------------|------------------------------------------------------------|
| Test Case ID         | TC-023                                                     |
| Module               | User Management                                            |
| Test Type            | Positive Functional Test                                   |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`. |
| Automation Reference | `AdminTest.testAdminUserManagementPageAccess()`            |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **User Management** link in the navigation bar.
7. Verify that the User Management page is displayed correctly.

**Expected Result:**
- The User Management page should load successfully with the URL containing `UserManagement` indicating that the admin is on the User Management page.
- The page heading should be `User Management`.

### TC-024 - Admin's User Management Page - Delete User Functionality

| Field                | Description                                                                                                 |
|----------------------|-------------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-024                                                                                                      |
| Module               | User Management                                                                                             |
| Test Type            | Positive Functional Test                                                                                    |
| Preconditions        | Movies Collection is running at `https://localhost:7285/`; User `test@mail.com` available in the user list. |
| Automation Reference | `AdminTest.testAdminUserManagementPageDeleteUser()`                                                         |

**Test Steps:**
1. Open Chrome browser.
2. Navigate to `https://localhost:7285/`.
3. Click on the **Login** link in the navigation bar.
4. Enter `adminABC123@mail.com` in the Email field and `Admin123!` in the Password field.
5. Submit the login form.
6. Click on the **User Management** link in the navigation bar.
7. Verify that the User Management page is displayed correctly.
8. Locate the user `test@mail.com` in the user list and click the `Delete` button next to that user.
9. Confirm the deletion in the confirmation dialog.
10. Verify that the user `test@mail.com` is no longer present in the user list.

**Expected Result:**
- The user `test@mail.com` should be successfully deleted from the user list.


## Authenticated User - Movie CRUD Tests

### TC-025 - User Creates a New Movie

| Field                | Description                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-025                                                                                                |
| Module               | Movies / Create                                                                                       |
| Test Type            | Positive Functional Test                                                                              |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available. |
| Automation Reference | PositiveMovieCRUDTest.testUserCreatingMovie()                                                         |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Click **Add New Movie** and verify the Create page is displayed.
3. Enter the title `Test Movie`, Release Year and Last Watched Year as the current year, Duration `128`, Personal Rating `100`, and the description `This is a test movie description.`
4. Select both `Crime` and `Documentary` genres.
5. Upload `src/test/resources/poster_test_data/popcorn.png` as the poster.
6. Submit the form and return to the Movies page.
7. Locate the movie card with title `Test Movie`.

**Expected Result:**
- The Create page displays the heading `Add New Movie`.
- After submission, the Movies page displays the newly created card with title `Test Movie`.

**Notes:**
This is the first step of an ordered CRUD workflow; `TC-026` through `TC-028` operate on the same movie.

### TC-026 - User Views an Existing Movie

| Field                | Description                                                                                                                               |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-026                                                                                                                                    |
| Module               | Movies / Details                                                                                                                          |
| Test Type            | Positive Functional Test                                                                                                                  |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available; `Test Movie` was created by TC-025. |
| Automation Reference | PositiveMovieCRUDTest.testUserViewingMovie()                                                                                              |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Locate the card whose title is `Test Movie`.
3. Scroll to the card and click its link.
4. Observe the Movie Details page and movie title.

**Expected Result:**
- The browser navigates to the Movie Details page (URL contains `Movies/Details`).
- The page displays the heading `Movie Details` and the movie title `Test Movie`.

**Notes:**
Depends on TC-025 within the ordered CRUD workflow.

### TC-027 - User Edits an Existing Movie

| Field                | Description                                                                                                                            |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-027                                                                                                                                 |
| Module               | Movies / Edit                                                                                                                          |
| Test Type            | Positive Functional Test                                                                                                               |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available; `Test Movie` exists from TC-025. |
| Automation Reference | PositiveMovieCRUDTest.testUserEditingMovie()                                                                                           |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the `Test Movie` card and record the current poster image URL.
3. Click the **Edit** link and verify the Edit page heading.
4. Change the Title to `Movie Title Changed`, Release Year to `1900`, Duration to `130`, and Last Watched Year to `2001`.
5. Replace the genres with `Sci-Fi` and `Western`, set Personal Rating to `1`, and change the description to `This is an updated test movie description.`
6. Upload `src/test/resources/poster_test_data/popcorn_updated.png`.
7. Submit the Edit form and inspect the updated card on the Movies page.

**Expected Result:**
- The Edit page displays `Edit movie named Test Movie`.
- The updated card displays title `Movie Title Changed`, Release Year `1900`, Duration `130 mins`, Last Watched Year `2001`, genres `Sci-Fi` and `Western`, Personal Rating `1`, and the updated description.
- The poster image URL differs from the original URL.

**Notes:**
Depends on TC-025; the renamed movie is subsequently used by TC-028.

### TC-028 - User Deletes an Existing Movie

| Field                | Description                                                                                                                                            |
|----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| Test Case ID         | TC-028                                                                                                                                                 |
| Module               | Movies / Delete                                                                                                                                        |
| Test Type            | Positive Functional Test                                                                                                                               |
| Preconditions        | Movies Collection is running at https://localhost:7285/; the standard test user account is available; movie `Movie Title Changed` exists after TC-027. |
| Automation Reference | PositiveMovieCRUDTest.testUserDeletingMovie()                                                                                                          |

**Test Steps:**
1. Log in as the standard user `userABC123@mail.com` and open the Movies page.
2. Open the card titled `Movie Title Changed`.
3. On Movie Details, click the **Delete** link.
4. Verify the confirmation page asks `Are you sure you want to delete Movie Title Changed?`
5. Confirm deletion by clicking the submit button.
6. Inspect the list of titles on the Movies page.

**Expected Result:**
- The Delete confirmation page displays the expected confirmation heading.
- The user returns to the Movies page after confirmation.
- The title `Movie Title Changed` no longer appears among the displayed movie titles.

**Notes:**
Depends on TC-027 and completes the ordered CRUD workflow.

