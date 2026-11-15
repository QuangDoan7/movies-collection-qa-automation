package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegativeRegistrationTest extends BaseTest {
    RegisterPage userRegister;

    @BeforeEach
    public void goToRegisterPage() {
        navigateToRegisterPage();

        userRegister = new RegisterPage(driver);
    }

    @ParameterizedTest
    @CsvSource({
        "test", "test@", "@domain.com", "testdomain.com", "test@domain"
    })
    public void invalidEmailRegisterTest(String email) {
        userRegister.register(email, "Test123!", "Test123!");

        assertEquals("The Email field is not a valid e-mail address.", userRegister.getEmailErrorMessage());
    }

    @Test
    public void emptyEmailRegisterTest()
    {
        userRegister.register("", "Test123!", "Test123!");

        assertEquals("The Email field is required.", userRegister.getEmailErrorMessage());
    }

    @Test
    public void duplicateEmailRegisterTest() {
        navigateToRegisterPage();

        WebElement emailInput = driver.findElement(By.id("Input_Email"));
        emailInput.sendKeys("userABC123@mail.com");

        WebElement passwordInput = driver.findElement(By.id("Input_Password"));
        passwordInput.sendKeys("Test123!@#");

        WebElement confirmPasswordInput = driver.findElement(By.id("Input_ConfirmPassword"));
        confirmPasswordInput.sendKeys("Test123!@#");

        WebElement registerButton = driver.findElement(By.id("registerSubmit"));
        registerButton.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='registerForm']/div[1]/ul/li"), "Email 'userABC123@mail.com' is already taken."));
        assertEquals("Email 'userABC123@mail.com' is already taken.", driver.findElement(By.xpath("//*[@id='registerForm']/div[1]/ul/li")).getText());
    }



    @ParameterizedTest
    @CsvSource({
            "Abc123!", "Abcdf12345!@#",
            "abc123!@#", "ABC123!@#", "ABCabc!@#", "Abcde!@#$"
    })
    public void invalidPasswordRegisterTest(String password) {
        userRegister.register("test@mail.com", password, password);

        assertEquals("The Password must be 8~12 characters long and contain at least 1 uppercase letter, 1 lowercase letter, 1 numeric character, and 1 special character.", userRegister.getPasswordErrorMessage());
    }

    @Test
    public void emptyPasswordRegisterTest()
    {
        userRegister.register("test@mail.com", "", "");
        assertEquals("The Password field is required.", userRegister.getPasswordErrorMessage());
    }

    @Test
    public void mismatchedConfirmPasswordRegisterTest()
    {
        userRegister.register("test@mail.com", "Test123!@#", "Different123!@#");

        assertEquals("The password and confirmation password do not match.", userRegister.getPasswordConfirmErrorMessage());
    }

    @Test
    public void emptyConfirmPasswordRegisterTest()
    {
        userRegister.register("test@mail.com", "Test123!@#", "");
        assertEquals("The password and confirmation password do not match.", userRegister.getPasswordConfirmErrorMessage());
    }
}
