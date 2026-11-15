# Movies Collection - Defect Reports

## DEF-001: Registration/Login accepts Email without Required Top-level Domain

**Related Requirement:** `REQ-VAL-001`

**Related Test Cases:**
- `TC-029` - Registration Rejects Invalid Email Formats
- `TC-036` - Login Rejects Invalid Email Formats

**Automation References:**
- `NegativeRegistrationTest.invalidEmailRegisterTest(String)`
- `NegativeLoginTest.invalidEmailLoginTest(String)`

**Severity:** Medium

**Priority:** Medium

**Status:** Closed

### Description:
The system currently allows users to register and log in using email addresses that do not have a required top-level domain (TLD). For example, users can register with an email like "user@example" instead of "user@example.com". This behavior violates the email format validation rules and can lead to issues with user account management and communication.

### Preconditions:
- Browser is open and the Movies Collection application is accessible.
- The user is on the Registration or Login page.

### Steps to Reproduce:
- For registration:
1. Navigate to the Registration page.
2. Enter an email address without a TLD in the email field (e.g., "user@example").
3. Fill in the required password and password confirmation fields (e.g. "Test123!@#").
4. Click the "Register" button.
5. Observe the system's response.

- For login:
1. Navigate to the Login page.
2. Enter an email address without a TLD in the email field (e.g., "user@example").
3. Enter a valid password (e.g. "Test123!@#").
4. Click the "Log in" button.
5. Observe the system's response.

### Expected Result:
- The system should reject the registration or login attempt and display an appropriate error message indicating that the email address is invalid due to the missing top-level domain.

### Actual Result:
- The Registration form does not display an email format validation error for an address without a TLD.
- The Login form does not display an email format validation error for an address without a TLD.
- Both forms allow the invalid email format to proceed to subsequent processing.