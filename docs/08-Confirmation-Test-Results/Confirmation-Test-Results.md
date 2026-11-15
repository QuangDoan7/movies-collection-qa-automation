# Movies Collection - Confirmation Test Results

## Confirmation Test Information

**Environment:** Localhost - https://localhost:7285/  
**Operating System:** Windows 11  
**Application:** Movies Collection (ASP.NET Core)  
**Database:** SQL Server 2019 (`MoviesDBContext`)  
**Browser:** Google Chrome (Version 157.0.8103.51 (Official Build) (64-bit))  
**Tools:** Selenium WebDriver, JUnit 5, Maven  

## DEF-001: Registration/Login accepts Email without Required Top-level Domain

**Related Test cases:** TC-029, TC-036  
**Confirmation Test Status:** PASS

| Test Case | Actual Result                                                                                    | Status | Defect ID |
|-----------|--------------------------------------------------------------------------------------------------|--------|-----------|
| TC-029    | Registration rejected `test@domain` and displayed the expected invalid-email validation message. | PASS   | DEF-001   |
| TC-036    | Login rejected `userABC123@mail` and displayed the expected invalid-email validation message.    | PASS   | DEF-001   |

### Conclusion

DEF-001 was successfully fixed. All related failed test cases passed during confirmation testing.