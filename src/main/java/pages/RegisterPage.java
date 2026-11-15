package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;

    private final By emailInput = By.id("Input_Email");
    private final By passwordInput = By.id("Input_Password");
    private final By confirmPasswordInput = By.id("Input_ConfirmPassword");
    private final By registerButton = By.id("registerSubmit");

    private final By emailErrorMessage = By.id("Input_Email-error");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void register(String email, String password, String confirmPassword) {
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickRegisterButton();
    }

    public String getEmailErrorMessage() {
        return driver.findElement(emailErrorMessage).getText();
    }

    public String getPasswordErrorMessage() {
        return driver.findElement(By.id("Input_Password-error")).getText();
    }

    public String getPasswordConfirmErrorMessage() {
        return driver.findElement(By.id("Input_ConfirmPassword-error")).getText();
    }
}
