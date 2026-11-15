package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegativeLoginTest extends BaseTest {
    LoginPage userLogin;

    @BeforeEach
    public void goToLoginPage() {
        navigateToLoginPage();

        userLogin = new LoginPage(driver);
    }

    @ParameterizedTest
    @CsvSource({
        "userABC123", "userABC123@", "@mail.com", "userABC123mail.com", "userABC123@mail"
    })
    public void invalidEmailLoginTest(String email) {
        userLogin.login(email, "userABC123!");

        assertEquals("The Email field is not a valid e-mail address.", userLogin.getEmailErrorMessage());
    }

    @Test
    public void emptyEmailLoginTest()
    {
        userLogin.login("", "Test123!@#");

        assertEquals("The Email field is required.", userLogin.getEmailErrorMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "Abc123!", "Abcdf12345!@#",
            "abc123!@#", "ABC123!@#", "ABCabc!@#", "Abcde!@#$"
    })
    public void invalidPasswordLoginTest(String password) {
        userLogin.login("test@mail.com", password);

        assertEquals("Invalid login attempt.", userLogin.getInvalidLoginAttempt());
    }

    @Test
    public void emptyPasswordLoginTest()
    {
        userLogin.login("userABC123@mail.com", "");
        assertEquals("The Password field is required.", userLogin.getPasswordErrorMessage());
    }

    @Test
    public void wrongPasswordLoginTest()
    {
        userLogin.login("userABC123@mail.com", "adminABC123!");

        assertEquals("Invalid login attempt.", userLogin.getInvalidLoginAttempt());
    }
}
