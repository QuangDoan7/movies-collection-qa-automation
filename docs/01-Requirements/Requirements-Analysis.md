# Movies Collection - Requirements Analysis

## 1. System Overview

Movies Collection is an ASP.NET Core web application designed to help registered users manage their personal movie collections.

The application provides authentication, account registration, movie browsing, sorting, genre filtering, profile management, and administrative user management features.

Users can access different features depending on their authentication status and assigned role.

The system supports three user roles:

- Unauthenticated User (Guest): Can access public pages, register an account, and log in.

- Authenticated User: Can access personal movie collection features and account management pages.

- Administrator (Admin): Can access authenticated user features and administrative user management functions.

This document identifies the functional and validation requirements of the Movies Collection application for test planning, test case design, test coverage analysis, and requirements traceability.

## 2. Functional Requirements

### 2.1 Public Access Requirements

REQ-PUB-001: Home Page Access
- The system shall allow unauthenticated users to access the home page.
- The home page shall display the heading "Greetings!".

REQ-PUB-002: Privacy Page Access
- The system shall allow unauthenticated users to access the Privacy page.
- The Privacy page shall display the heading "Privacy Policy" and the application's privacy information.

REQ-PUB-003: Login Page Access
- The system shall allow unauthenticated users to navigate to the Login page.
- The Login page shall display the heading "Log in" and an email input field.

REQ-PUB-004: Registration Page Access
- The system shall allow unauthenticated users to navigate to the Registration page.
- The Registration page shall display the heading "Create a new account" and the required registration fields.

### 2.2 Authentication and Registration Requirements

REQ-AUTH-001: User Registration
- The system shall allow new users to register an account using a valid email address, password, and password confirmation.

REQ-AUTH-002: Registration Confirmation
- After a successful registration submission, the system shall redirect the user to the Registration Confirmation page.
- The system shall provide a mechanism for confirming the registered email address.
- After successful email confirmation, the system shall display the message "Thank you for confirming your email."

REQ-AUTH-003: User Login
- The system shall allow registered users to log in using valid credentials.
- After successful authentication, the system shall display the authenticated user's email address in the navigation bar.

REQ-AUTH-004: Administrator Login
- The system shall allow administrators to log in using valid administrator credentials.
- After successful authentication, the system shall display the administrator's email address in the navigation bar.

REQ-AUTH-005: Logout
- The system shall allow authenticated users and administrators to log out.
- After logout, the system shall redirect the user to the home page.
- The navigation bar shall display the Login option after the session has ended.

REQ-AUTH-006: Invalid Login
- The system shall reject login attempts with invalid credentials and display an appropriate error message.

### 2.3 Access Control Requirements

REQ-ACC-001: Restricted Movies Page Access
- The system shall prevent unauthenticated users from accessing the protected Movies page.
- Unauthenticated users attempting to access the Movies page shall be redirected to the Login page.

REQ-ACC-002: Authenticated Movies Page Access
- The system shall allow authenticated users to access the Movies page.
- The Movies page shall display the heading "Movies" and the available movie cards.

REQ-ACC-003: User Management Access
- The system shall only allow the Administrator role to access the User Management page.

### 2.4 Movies Collection Requirements

REQ-MOV-001: Display Movie Collection
- The system shall display movies in a grid layout.
- Each movie card shall display relevant information, including the movie's title, release year, genre, and description.

REQ-MOV-002: Title Sorting
- The system shall display movies in ascending alphabetical order by title by default.
- The system shall allow users to sort movies by title in descending alphabetical order.

REQ-MOV-003: Release Year Sorting
- The system shall allow users to sort movies by release year in ascending order.
- The system shall allow users to sort movies by release year in descending order.

REQ-MOV-004: Genre Filtering
- The system shall provide a genre filter dropdown containing available movie genres.
- The system shall allow users to filter the displayed movies according to the selected genre.
- The system shall provide an "All" option for viewing movies without a genre-specific filter.

REQ-MOV-005: Add Movie
- The system shall allow authenticated users to add a new movie to their collection.
- Movie information includes title, release year, duration, genre, rating, description, and image upload.
- Title and Genre are required fields.
- The system shall allow users to upload an image as a movie poster.
- After successful creation, the new movie shall appear
  in the Movies collection.

REQ-MOV-006: View Movie Details
- The system shall allow authenticated users to click a movie card to view the movie's details.

REQ-MOV-007: Edit Movie
- The system shall allow authenticated users to edit an existing movie's information.
- Title and Genre are required fields when editing.
- The system shall allow users to update an existing movie poster.
- After a successful update, the modified movie information
  shall be reflected in the Movies collection.

REQ-MOV-008: Delete Movie
- The system shall allow authenticated users to access the Delete option from the movie details page.
- The system shall navigate to a deletion confirmation page.
- The movie shall be deleted only after the user confirms the deletion.

### 2.5 Profile Management Requirements

REQ-PRO-001: Profile Management Page Access
- The system shall allow authenticated users and administrators to access their account management page.
- The account management page shall display the heading "Manage your account".

### 2.6 Administrator User management Requirements

REQ-ADM-001: User Management Page Access
- The system shall allow administrators to access the User Management page.
- The User Management page shall display the heading "User Management".

REQ-ADM-002: View Registered Users
- The system shall provide administrators with a list of registered user accounts.

REQ-ADM-003: Delete User Account
- The system shall allow administrators to select a registered user account for deletion.
- The system shall request confirmation before deleting the selected account.
- After the administrator confirms the deletion, the selected user account shall no longer appear in the User Management list.

## 3. Validation Requirements

### 3.1 Email Validation

REQ-VAL-001: Email Input Validation
- The system shall validate email addresses during user registration and login.
- The Email field is required.
- The email address must contain a local part, an "@" symbol, and a domain name with a top-level domain (TLD)
  (e.g., test@mail.com).
- The system shall reject invalid email addresses and
  display an appropriate validation error message.

REQ-VAL-002: Duplicate Account Registration
- The system shall reject registration using an email address that is already registered.

### 3.2 Password Validation

REQ-VAL-003: Password input validation
- The system shall validate the password input during user registration to ensure it meets the following criteria:
  - Length between 8 and 12 characters
  - At least one uppercase letter
  - At least one lowercase letter
  - At least one numeric digit
  - At least one special character (e.g., !, @, #, $, %, ^, &, *)
- The Password field is required.

REQ-VAL-004: Registration Password Confirmation
- The "Confirm Password" field is required.
- The system shall reject registration when the Password and Confirm Password fields do not match.
- The system shall display an appropriate validation
  error message when password confirmation is invalid.

### 3.3 Movie Data Validation

REQ-VAL-005: Movie Data Input Validation
- The system shall validate movie data during the creation and editing of a movie.
- The following validation rules shall apply:
  - Title and Genre are required fields.
  - Release Year must be between 1900 and the current year.
  - Duration must be a positive number.
  - Last Watched Year, when provided, must be between the movie's Release Year and the current year.
  - Personal Rating must be between 1 and 100 when provided.
  - Description is optional.
- The system shall display an appropriate validation error
  message when invalid movie data is submitted.
- The system shall prevent saving movie data that violates
  the validation rules.