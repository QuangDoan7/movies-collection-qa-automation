package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUpEach() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String baseUrl = System.getenv().getOrDefault(
                "MOVIES_BASE_URL",
                "https://localhost:7285/"
        );

        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDownEach() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void navigateToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
        wait.until(ExpectedConditions.urlContains("Identity/Account/Login"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Log in"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Input_Email")));
    }

    public void navigateToMoviesPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Movies']"))).click();
        wait.until(ExpectedConditions.urlContains("Movies"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movies"));
    }

    public void navigateToPrivacyPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Privacy']"))).click();
        wait.until(ExpectedConditions.urlContains("Privacy"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Privacy Policy"));
    }

    public void navigateToProfileManagementPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("manage"))).click();
        wait.until(ExpectedConditions.urlContains("Identity/Account/Manage"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Manage your account"));
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout"))).click();
        wait.until(ExpectedConditions.titleIs("Movies Collection"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
    }

    public void navigateToRegisterPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register"))).click();
        wait.until(ExpectedConditions.urlContains("Identity/Account/Register"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Create a new account"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Input_Email")));
    }

    public void navigateToUserManagementPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='User Management']"))).click();
        wait.until(ExpectedConditions.urlContains("UserManagement"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "User Management"));
    }

    public void creatingFullDetailedMovie(String testTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys(testTitle);
        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys(String.valueOf(java.time.Year.now().getValue()));
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys(String.valueOf(java.time.Year.now().getValue()));

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.selectByVisibleText("Crime");
        genreDropdown.selectByVisibleText("Documentary");

        driver.findElement(By.id("Movie_Rating")).sendKeys("100");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.urlContains("Movies"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movies"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='"+ testTitle +"']"), testTitle));
    }

    public void deleteTestMovie(String testTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testTitle + "']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testTitle + "']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='" + testTitle + "']"), testTitle));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Delete')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Delete"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Are you sure you want to delete " + testTitle + "?"));

        assertEquals("Are you sure you want to delete " + testTitle + "?", driver.findElement(By.tagName("h1")).getText());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movies"));
    }
}
