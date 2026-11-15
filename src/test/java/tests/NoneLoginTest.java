package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoneLoginTest extends BaseTest {
    @Test
    @DisplayName("Test Home Page Access Without Login")
    public void testHomePageAccess() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Greetings!"));

        assertEquals("Greetings!", driver.findElement(By.tagName("h1")).getText());
    }

    @Test
    @DisplayName("Test Movies Page Access Without Login")
    public void testMoviesPageAccess() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Movies']")));

        WebElement moviesLink = driver.findElement(By.xpath("//a[text()='Movies']"));
        moviesLink.click();

        wait.until(ExpectedConditions.urlContains("Identity/Account/Login"));
        assertTrue(driver.getCurrentUrl().contains("Identity/Account/Login"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Log in"));
        assertEquals("Log in", driver.findElement(By.tagName("h1")).getText());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Input_Email")));
        assertTrue(driver.findElement(By.id("Input_Email")).isDisplayed());
    }

    @Test
    @DisplayName("Test Privacy Page Access Without Login")
    public void testPrivacyPageAccess() {
        navigateToPrivacyPage();

        assertEquals("Privacy Policy", driver.findElement(By.tagName("h1")).getText());

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("p"), "This website is built as a sample project for learning purposes"));
        assertTrue(driver.findElement(By.tagName("p")).getText().contains("This website is built as a sample project for learning purposes"));
    }

    @Test
    @DisplayName("Test Login Page Access Without Login")
    public void testLoginPageAccess() {
        navigateToLoginPage();

        assertTrue(driver.findElement(By.id("Input_Email")).isDisplayed());
    }

    @Test
    @DisplayName("Test Register Page Access Without Login")
    public void testRegisterPageAccess() {
        navigateToRegisterPage();

        assertTrue(driver.getCurrentUrl().contains("Identity/Account/Register"));

        assertEquals("Create a new account", driver.findElement(By.tagName("h1")).getText());

        assertTrue(driver.findElement(By.id("Input_Email")).isDisplayed());
    }

    @Test
    @DisplayName("Register New User Account Test")
    public void testRegisterNewUserAccount() {
        navigateToRegisterPage();

        WebElement emailInput = driver.findElement(By.id("Input_Email"));
        emailInput.sendKeys("test@mail.com");

        WebElement passwordInput = driver.findElement(By.id("Input_Password"));
        passwordInput.sendKeys("Test123!@#");

        WebElement confirmPasswordInput = driver.findElement(By.id("Input_ConfirmPassword"));
        confirmPasswordInput.sendKeys("Test123!@#");

        WebElement registerButton = driver.findElement(By.id("registerSubmit"));
        registerButton.click();

        wait.until(ExpectedConditions.urlContains("Identity/Account/RegisterConfirmation"));
        assertTrue(driver.getCurrentUrl().contains("Identity/Account/RegisterConfirmation"));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-link")));
        WebElement confirmLink = driver.findElement(By.id("confirm-link"));
        assertTrue(confirmLink.isDisplayed());

        confirmLink.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Confirm email"));

        assertEquals("Thank you for confirming your email.", driver.findElement(By.cssSelector("div[role='alert']")).getText());
    }
}