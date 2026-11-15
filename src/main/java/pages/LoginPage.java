package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By emailInput = By.id("Input_Email");
    private final By passwordInput = By.id("Input_Password");
    private final By loginButton = By.id("login-submit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterEmail(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getEmailErrorMessage() {
        return driver.findElement(By.id("Input_Email-error")).getText();
    }

    public String getInvalidLoginAttempt() {
        return driver.findElement(By.cssSelector("div[role='alert']")).getText();
    }

    public String getPasswordErrorMessage() {
        return driver.findElement(By.id("Input_Password-error")).getText();
    }
}
