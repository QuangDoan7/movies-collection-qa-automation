package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.LoginPage;
import pages.MoviesPage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class PositiveMovieCRUDTest extends BaseTest {
    LoginPage userLogin;
    MoviesPage userMovies;

    @BeforeEach
    public void loginAsUser() {
        navigateToLoginPage();

        userLogin = new LoginPage(driver);
        userLogin.login("userABC123@mail.com", "userABC123!");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("manage"), "Hello userABC123@mail.com!"));

        userMovies = new MoviesPage(driver);
        navigateToMoviesPage();
    }

    @Test
    @Order(1)
    @DisplayName("User Creating a Movie")
    public void testUserCreatingMovie() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys("Test Movie");
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

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Test Movie']"), "Test Movie"));

        assertEquals("Test Movie", driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Test Movie']")).getText());
    }

    @Test
    @Order(2)
    @DisplayName("User Viewing a Movie")
    public void testUserViewingMovie() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Test Movie']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Test Movie']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='Test Movie']"), "Test Movie"));

        assertEquals("Test Movie", driver.findElement(By.xpath("//dl/dt[normalize-space()='Test Movie']")).getText());
    }

    @Test
    @Order(3)
    @DisplayName("User Editing a Movie")
    public void testUserEditingMovie() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Test Movie']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Test Movie']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='Test Movie']"), "Test Movie"));

        WebElement currentPoster = driver.findElement(By.xpath("//img[contains(@class, 'img-thumbnail')]"));
        String currentPosterSrc = currentPoster.getAttribute("src");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Edit')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Edit"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Edit movie named Test Movie"));

        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title")));
        titleInput.clear();
        titleInput.sendKeys("Movie Title Changed");

        WebElement releaseYearInput = driver.findElement(By.id("Movie_ReleaseYear"));
        releaseYearInput.clear();
        releaseYearInput.sendKeys("1900");

        WebElement durationInput = driver.findElement(By.id("Movie_Duration"));
        durationInput.clear();
        durationInput.sendKeys("130");

        WebElement lastWatchedYearInput = driver.findElement(By.id("Movie_LastWatchedYear"));
        lastWatchedYearInput.clear();
        lastWatchedYearInput.sendKeys("2001");

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.deselectAll();
        genreDropdown.selectByVisibleText("Sci-Fi");
        genreDropdown.selectByVisibleText("Western");

        WebElement ratingInput = driver.findElement(By.id("Movie_Rating"));
        ratingInput.clear();
        ratingInput.sendKeys("1");

        WebElement descriptionInput = driver.findElement(By.id("Movie_Description"));
        descriptionInput.clear();
        descriptionInput.sendKeys("This is an updated test movie description.");

        WebElement uploadInput = driver.findElement(By.id("Upload"));
        Path updatedPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn_updated.png").toAbsolutePath();
        uploadInput.sendKeys(updatedPosterPath.toString());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movies"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']"), "Movie Title Changed"));

        assertEquals("Movie Title Changed", driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']")).getText());

        WebElement newReleaseYear = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/../span[@data-testid='release-year']"));
        assertEquals("1900", newReleaseYear.getText());

        WebElement newDuration = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/../span[@data-testid='duration']"));
        assertEquals("130 mins", newDuration.getText());

        WebElement newLastWatchedYear = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/../span[@data-testid='last-watched-year']"));
        assertEquals("2001", newLastWatchedYear.getText());

        WebElement newGenre = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/../span[@data-testid='genre']"));
        assertTrue(newGenre.getText().contains("Sci-Fi") && newGenre.getText().contains("Western"));

        WebElement newRating = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/../span[@data-testid='personal-rating']"));
        assertEquals("1", newRating.getText());

        WebElement newDescription = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/../span[@data-testid='description']"));
        assertEquals("This is an updated test movie description.", newDescription.getText());

        WebElement updatedPoster = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/../../../img"));
        String updatedPosterSrc = updatedPoster.getAttribute("src");

        assertFalse(currentPosterSrc.equals(updatedPosterSrc), "Poster image should be updated.");
    }

    @Test
    @Order(4)
    @DisplayName("User Deleting a Movie")
    public void testUserDeletingMovie() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='Movie Title Changed']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='Movie Title Changed']"), "Movie Title Changed"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Delete')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Delete"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Are you sure you want to delete Movie Title Changed?"));

        assertEquals("Are you sure you want to delete Movie Title Changed?", driver.findElement(By.tagName("h1")).getText());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movies"));

        List<WebElement> allMovieTitlesElements = driver.findElements(By.xpath("//span[@data-testid='title']"));
        List<String> titles = new ArrayList<>();
        for (WebElement element : allMovieTitlesElements) {
            titles.add(element.getText());
        }
        assertFalse(titles.contains("Movie Title Changed"));
    }
}
