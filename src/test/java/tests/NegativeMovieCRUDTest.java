package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.LoginPage;

import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NegativeMovieCRUDTest extends BaseTest {
    LoginPage userLogin;

    private String testMovieTitle;
    private boolean movieCreated;

    private void createMovieForEditing() {
        creatingFullDetailedMovie(testMovieTitle);
        movieCreated = true;
    }

    @BeforeEach
    public void loginAsUser() {
        movieCreated = false;
        navigateToLoginPage();

        userLogin = new LoginPage(driver);
        userLogin.login("userABC123@mail.com", "userABC123!");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("manage"), "Hello userABC123@mail.com!"));

        navigateToMoviesPage();

        testMovieTitle = "QA-TEST-" + UUID.randomUUID();
    }

    @Test
    @DisplayName("User Creating a Movie Without Title")
    public void testCreatingMovieWithoutTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys("2017");
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys("2020");

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.selectByVisibleText("Crime");
        genreDropdown.selectByVisibleText("Documentary");

        driver.findElement(By.id("Movie_Rating")).sendKeys("85");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Movie_Title-error"), "The Title field is required."));

        assertEquals("The Title field is required.", driver.findElement(By.id("Movie_Title-error")).getText());
    }

    @Test
    @DisplayName("User Creating a Movie Without Selecting a Genre")
    public void testCreatingMovieWithoutGenres() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys(testMovieTitle);
        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys("2017");
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys("2020");

        driver.findElement(By.id("Movie_Rating")).sendKeys("85");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("SelectedGenres-error"), "The SelectedGenres field is required."));

        assertEquals("The SelectedGenres field is required.", driver.findElement(By.id("SelectedGenres-error")).getText());
    }

    @Test
    @DisplayName("User Creating a Movie With Release Year in the Future")
    public void testCreatingMovieWithFutureReleaseYear() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys(testMovieTitle);
        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys(String.valueOf(java.time.Year.now().getValue() + 1));
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys("2020");

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.selectByVisibleText("Crime");
        genreDropdown.selectByVisibleText("Documentary");

        driver.findElement(By.id("Movie_Rating")).sendKeys("85");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']"), "Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + "."));

        assertEquals("Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + ".", driver.findElement(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']")).getText());
    }

    @Test
    @DisplayName("User Creating a Movie With Release Year Before 1900")
    public void testCreatingMovieWithReleaseYearBefore1900() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys(testMovieTitle);
        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys("1899");
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys("2020");

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.selectByVisibleText("Crime");
        genreDropdown.selectByVisibleText("Documentary");

        driver.findElement(By.id("Movie_Rating")).sendKeys("85");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']"), "Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + "."));

        assertEquals("Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + ".", driver.findElement(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']")).getText());
    }

    @Test
    @DisplayName("User Creating a Movie With Last Watched Year in the Future")
    public void testCreatingMovieWithWatchedYearInTheFuture() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys(testMovieTitle);

        int releaseYear = 2020;
        int lastWatchedYear = java.time.Year.now().getValue() + 1;
        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys(String.valueOf(releaseYear));
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys(String.valueOf(lastWatchedYear));

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.selectByVisibleText("Crime");
        genreDropdown.selectByVisibleText("Documentary");

        driver.findElement(By.id("Movie_Rating")).sendKeys("85");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@data-valmsg-for='Movie.LastWatchedYear']"), "Last Watched Year must be between the release year " + releaseYear + " and the current year " + (java.time.Year.now().getValue()) + "."));

        assertEquals("Last Watched Year must be between the release year " + releaseYear + " and the current year " + (java.time.Year.now().getValue()) + ".", driver.findElement(By.xpath("//span[@data-valmsg-for='Movie.LastWatchedYear']")).getText());
    }

    @Test
    @DisplayName("User Creating a Movie With Personal Rating Below 1")
    public void testCreatingMovieWithPersonalRatingBelow1() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys(testMovieTitle);

        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys("2020");
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys("2025");

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.selectByVisibleText("Crime");
        genreDropdown.selectByVisibleText("Documentary");

        driver.findElement(By.id("Movie_Rating")).sendKeys("0");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Movie_Rating-error"), "Rating must be between 1 and 100."));

        assertEquals("Rating must be between 1 and 100.", driver.findElement(By.id("Movie_Rating-error")).getText());
    }

    @Test
    @DisplayName("User Creating a Movie With Personal Rating Over 100")
    public void testCreatingMovieWithPersonalRatingOver100() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Movies/Create']"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Create"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Add New Movie"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title"))).sendKeys(testMovieTitle);

        driver.findElement(By.id("Movie_ReleaseYear")).sendKeys("2020");
        driver.findElement(By.id("Movie_Duration")).sendKeys("128");
        driver.findElement(By.id("Movie_LastWatchedYear")).sendKeys("2025");

        Select genreDropdown = new Select(driver.findElement(By.id("SelectedGenres")));
        genreDropdown.selectByVisibleText("Crime");
        genreDropdown.selectByVisibleText("Documentary");

        driver.findElement(By.id("Movie_Rating")).sendKeys("101");
        driver.findElement(By.id("Movie_Description")).sendKeys("This is a test movie description.");

        Path testPosterPath = Path.of("src", "test", "resources", "poster_test_data", "popcorn.png").toAbsolutePath();
        driver.findElement(By.id("Upload")).sendKeys(testPosterPath.toString());

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Movie_Rating-error"), "Rating must be between 1 and 100."));

        assertEquals("Rating must be between 1 and 100.", driver.findElement(By.id("Movie_Rating-error")).getText());
    }

    @Test
    @DisplayName("User Editing a Movie With Empty Title")
    public void testUserEditingMovieWithEmptyTitle() {
        createMovieForEditing();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='" + testMovieTitle + "']"), testMovieTitle));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Edit')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Edit"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Edit movie named " + testMovieTitle));

        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Movie_Title")));
        titleInput.clear();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Movie_Title-error"), "The Title field is required."));

        assertEquals("The Title field is required.", driver.findElement(By.id("Movie_Title-error")).getText());
    }

    @Test
    @DisplayName("User Editing a Movie With Future Release Year")
    public void testUserEditingMovieWithFutureReleaseYear() {
        createMovieForEditing();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='" + testMovieTitle + "']"), testMovieTitle));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Edit')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Edit"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Edit movie named " + testMovieTitle));

        WebElement releaseYearInput = driver.findElement(By.id("Movie_ReleaseYear"));
        releaseYearInput.clear();
        releaseYearInput.sendKeys(String.valueOf(java.time.Year.now().getValue() + 1));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']"), "Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + "."));

        assertEquals("Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + ".", driver.findElement(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']")).getText());
    }

    @Test
    @DisplayName("User Editing a Movie With Release Year Before 1900")
    public void testUserEditingMovieWithReleaseYearBefore1900() {
        createMovieForEditing();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='" + testMovieTitle + "']"), testMovieTitle));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Edit')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Edit"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Edit movie named " + testMovieTitle));

        WebElement releaseYearInput = driver.findElement(By.id("Movie_ReleaseYear"));
        releaseYearInput.clear();
        releaseYearInput.sendKeys(String.valueOf(1899));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']"), "Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + "."));

        assertEquals("Release Year must be between 1900 and the current year " + (java.time.Year.now().getValue()) + ".", driver.findElement(By.xpath("//span[@data-valmsg-for='Movie.ReleaseYear']")).getText());
    }

    @Test
    @DisplayName("User Editing a Movie With Watched Year in the Future")
    public void testUserEditingMovieWithWatchedYearInTheFuture() {
        createMovieForEditing();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='" + testMovieTitle + "']"), testMovieTitle));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Edit')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Edit"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Edit movie named " + testMovieTitle));

        WebElement releaseYearInput = driver.findElement(By.id("Movie_ReleaseYear"));
        int releaseYear = Integer.parseInt(releaseYearInput.getAttribute("value"));

        WebElement lastWatchedYearInput = driver.findElement(By.id("Movie_LastWatchedYear"));
        lastWatchedYearInput.clear();
        lastWatchedYearInput.sendKeys(String.valueOf(java.time.Year.now().getValue() + 1));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@data-valmsg-for='Movie.LastWatchedYear']"), "Last Watched Year must be between the release year " + releaseYear + " and the current year " + (java.time.Year.now().getValue()) + "."));

        assertEquals("Last Watched Year must be between the release year " + releaseYear + " and the current year " + (java.time.Year.now().getValue()) + ".", driver.findElement(By.xpath("//span[@data-valmsg-for='Movie.LastWatchedYear']")).getText());
    }

    @Test
    @DisplayName("User Editing a Movie With Personal Rating Below 1")
    public void testUserEditingMovieWithPersonalRatingBelow1() {
        createMovieForEditing();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='" + testMovieTitle + "']"), testMovieTitle));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Edit')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Edit"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Edit movie named " + testMovieTitle));

        WebElement ratingInput = driver.findElement(By.id("Movie_Rating"));
        ratingInput.clear();
        ratingInput.sendKeys("0");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Movie_Rating-error"), "Rating must be between 1 and 100."));

        assertEquals("Rating must be between 1 and 100.", driver.findElement(By.id("Movie_Rating-error")).getText());
    }

    @Test
    @DisplayName("User Editing a Movie With Personal Rating Over 100")
    public void testUserEditingMovieWithPersonalRatingOver100() {
        createMovieForEditing();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']")));

        WebElement testMovieCard = driver.findElement(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']/ancestor::a"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(testMovieCard).perform();

        wait.until(ExpectedConditions.elementToBeClickable(testMovieCard)).click();

        wait.until(ExpectedConditions.urlContains("Movies/Details"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Movie Details"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//dl/dt[normalize-space()='" + testMovieTitle + "']"), testMovieTitle));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Movies/Edit')]"))).click();

        wait.until(ExpectedConditions.urlContains("Movies/Edit"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Edit movie named " + testMovieTitle));

        WebElement ratingInput = driver.findElement(By.id("Movie_Rating"));
        ratingInput.clear();
        ratingInput.sendKeys("101");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Movie_Rating-error"), "Rating must be between 1 and 100."));

        assertEquals("Rating must be between 1 and 100.", driver.findElement(By.id("Movie_Rating-error")).getText());
    }

    @AfterEach
    public void deleteTestMovieIfAny() {
        if (!movieCreated) { return; }

        navigateToMoviesPage();

        if (!driver.findElements(By.xpath("//div[@class='card-body']/p/span[normalize-space()='" + testMovieTitle + "']")).isEmpty()) {
            deleteTestMovie(testMovieTitle);
        }
    }
}
