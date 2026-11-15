# Movies Collection - Test Scenarios

## REQ-PUB-001 - Home Page Access

### TS-001

Verify that an unauthenticated user can access the home page and view the "Greetings!" heading.

## REQ-PUB-002 - Privacy Page Access

### TS-002

Verify that an unauthenticated user can access the Privacy page and view its information.

### TS-003

Verify that an authenticated user can access the Privacy page and view its information.

### TS-004

Verify that an administrator can access the Privacy page and view its information.

## REQ-PUB-003 - Login Page Access

### TS-005

Verify that an unauthenticated user can navigate to the Login page and view the email input field.

## REQ-PUB-004 - Registration Page Access

### TS-006

Verify that an unauthenticated user can navigate to the Registration page and view its registration fields.

## REQ-AUTH-001 / REQ-AUTH-002 - User Registration and Confirmation

### TS-007

Verify that a new user can register with valid information, access the registration confirmation page, and confirm the email address.

## REQ-AUTH-003 - User Login

### TS-008

Verify that a registered user can log in with valid credentials and see their email address and the Logout option.

## REQ-AUTH-004 - Administrator Login

### TS-009

Verify that an administrator can log in with valid credentials and see their email address and the Logout option.

## REQ-AUTH-005 - Logout

### TS-010

Verify that an authenticated user can log out and return to the home page with the Login option available.

### TS-011

Verify that an administrator can log out and return to the home page with the Login option available.

## REQ-AUTH-006 - Invalid Login

### TS-012

Verify that login attempts using different invalid passwords are rejected with an appropriate error message.

### TS-013

Verify that login with an empty password is rejected with a required-field validation message.

### TS-014

Verify that login using a registered email address and an incorrect password is rejected.

## REQ-ACC-001 - Restricted Movies Page Access

### TS-015

Verify that an unauthenticated user attempting to access the Movies page is redirected to the Login page.

## REQ-ACC-002 / REQ-MOV-001 - Authenticated Movies Page and Movie Collection Display

### TS-016

Verify that an authenticated user can access the Movies page and see available movie cards.

### TS-017

Verify that an administrator can access the Movies page and see available movie cards.

## REQ-MOV-002 - Title Sorting

### TS-018

Verify that an authenticated user can view movies sorted by title in ascending order by default and switch to descending order.

### TS-019

Verify that an administrator can view movies sorted by title in ascending order by default and switch to descending order.

## REQ-MOV-003 - Release Year Sorting

### TS-020

Verify that an authenticated user can sort movies by release year in ascending and descending order.

### TS-021

Verify that an administrator can sort movies by release year in ascending and descending order.

## REQ-MOV-004 - Genre Filtering

### TS-022

Verify that an authenticated user can filter movie cards by available genres and select the "All" option.

### TS-023

Verify that an administrator can filter movie cards by available genres and select the "All" option.

## REQ-MOV-005 - Add Movie

### TS-024

Verify that an authenticated user can create a movie with valid details and a poster, and that the movie appears in the collection.

## REQ-MOV-006 - View Movie Details

### TS-025

Verify that an authenticated user can open an existing movie card and view the correct movie details.

## REQ-MOV-007 - Edit Movie

### TS-026

Verify that an authenticated user can update an existing movie's information and poster and see the modified values in the collection.

## REQ-MOV-008 - Delete Movie

### TS-027

Verify that an authenticated user can delete an existing movie after confirming deletion and that the movie no longer appears in the collection.

## REQ-PRO-001 - Profile Management Page Access

### TS-028

Verify that an authenticated user can access the account management page and view their account information.

### TS-029

Verify that an administrator can access the account management page and view their account information.

## REQ-ADM-001 - User Management Page Access

### TS-030

Verify that an administrator can access the User Management page and view its heading.

## REQ-ADM-003 - Delete User Account

### TS-031

Verify that an administrator can select an existing user, confirm account deletion, and no longer see that user in the management list.

## REQ-VAL-001 - Email Input Validation

### TS-032

Verify that registration rejects invalid email address formats, including an address without a top-level domain.

### TS-033

Verify that registration rejects an empty email field and displays the required-field validation message.

### TS-034

Verify that login rejects invalid email address formats, including an address without a top-level domain.

### TS-035

Verify that login rejects an empty email field and displays the required-field validation message.

## REQ-VAL-002 - Duplicate Account Registration

### TS-036

Verify that registration using an email address already associated with an existing account is rejected with an appropriate validation error message.

## REQ-VAL-003 - Password Input Validation

### TS-037

Verify that registration rejects passwords violating the required length or character-composition rules.

### TS-038

Verify that registration rejects an empty password field and displays the required-field validation message.

## REQ-VAL-004 - Registration Password Confirmation

### TS-039

Verify that registration rejects a confirmation password that does not match the password.

### TS-040

Verify that registration rejects an empty confirmation password when a password has been entered.

## REQ-VAL-005 - Movie Data Input Validation

### TS-041

Verify that creating a movie without a title is rejected with a required-field validation message.

### TS-042

Verify that creating a movie without selecting any genre is rejected with a required-field validation message.

### TS-043

Verify that creating a movie with a release year later than the current year is rejected.

### TS-044

Verify that creating a movie with a release year earlier than 1900 is rejected.

### TS-045

Verify that creating a movie with a last watched year later than the current year is rejected.

### TS-046

Verify that creating a movie with a personal rating below 1 is rejected.

### TS-047

Verify that creating a movie with a personal rating above 100 is rejected.

### TS-048

Verify that editing a movie with an empty title is rejected with a required-field validation message.

### TS-049

Verify that editing a movie with a release year later than the current year is rejected.

### TS-050

Verify that editing a movie with a release year earlier than 1900 is rejected.

### TS-051

Verify that editing a movie with a last watched year later than the current year is rejected.

### TS-052

Verify that editing a movie with a personal rating below 1 is rejected.

### TS-053

Verify that editing a movie with a personal rating above 100 is rejected.