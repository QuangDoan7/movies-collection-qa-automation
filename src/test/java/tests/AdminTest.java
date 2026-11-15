package tests;

import helpers.MoviesAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.MoviesPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest extends BaseTest {
    LoginPage adminLogin;
    MoviesPage adminMovies;

    @BeforeEach
    public void loginAsAdmin() {
        navigateToLoginPage();

        adminLogin = new LoginPage(driver);
        adminLogin.login("adminABC123@mail.com", "adminABC123!");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("manage"), "Hello adminABC123@mail.com!"));

        adminMovies = new MoviesPage(driver);
    }

    @Test
    @DisplayName("Admin Login Test")
    public void testAdminLogin() {
        assertTrue(driver.findElement(By.id("manage")).getText().contains("Hello adminABC123@mail.com!"));
        assertEquals("Logout", driver.findElement(By.id("logout")).getText());
    }

    @Test
    @DisplayName("Admin's Movies Page Access Test")
    public void testAdminMoviesPageAccess() {
        navigateToMoviesPage();
        assertEquals("Movies", driver.findElement(By.tagName("h1")).getText());

        List<WebElement> filmCards = adminMovies.getFilmCards();
        assertFalse(filmCards.isEmpty(), "No film cards found on the Movies page of sample User account.");
    }

    @Test
    @DisplayName("Admin's Movies Page - Title Sorting Test")
    public void testAdminMoviesPageTitleSorting() {
        navigateToMoviesPage();

        List<WebElement> filmCardsAscending = adminMovies.getFilmCards();
        assertFalse(filmCardsAscending.isEmpty(), "No film cards found on the Movies page of sample User account.");

        List<String> titlesAscending = adminMovies.getAllFilmTitles(filmCardsAscending);
        MoviesAssertions.assertTitleSorted(titlesAscending, true);

        adminMovies.clickTitleSortButton();
        wait.until(ExpectedConditions.urlContains("Movies?sortOrder=title_desc"));

        List<WebElement> filmCardsDescending = adminMovies.getFilmCards();
        assertFalse(filmCardsDescending.isEmpty(), "No film cards found on the Movies page of sample User account after sorting.");

        List<String> titlesDescending = adminMovies.getAllFilmTitles(filmCardsDescending);
        MoviesAssertions.assertTitleSorted(titlesDescending, false);
    }

    @Test
    @DisplayName("Admin's Movies Page - Release Year Sorting Test")
    public void testAdminMoviesPageReleaseYearSorting() {
        navigateToMoviesPage();

        adminMovies.clickYearSortButton();
        wait.until(ExpectedConditions.urlContains("Movies?sortOrder=year"));

        List<WebElement> filmCardsAscending = adminMovies.getFilmCards();
        assertFalse(filmCardsAscending.isEmpty(), "No film cards found on the Movies page of sample User account.");

        List<Integer> releaseYearsAscending = adminMovies.getAllFilmReleaseYears(filmCardsAscending);
        MoviesAssertions.assertReleaseYearSorted(releaseYearsAscending, true);

        adminMovies.clickYearSortButton();
        wait.until(ExpectedConditions.urlContains("Movies?sortOrder=year_desc"));

        List<WebElement> filmCardDescending = adminMovies.getFilmCards();
        assertFalse(filmCardDescending.isEmpty(), "No film cards found on the Movies page of sample User account.");

        List<Integer> releaseYearsDescending = adminMovies.getAllFilmReleaseYears(filmCardDescending);
        MoviesAssertions.assertReleaseYearSorted(releaseYearsDescending, false);
    }

    @Test
    @DisplayName("Admin's Movies Page - Filter by Genre Test")
    public void testAdminMoviesPageFilterByGenre() {
        navigateToMoviesPage();

        List<String> genreOptions = adminMovies.getGenreOptions();

        assertFalse(genreOptions.isEmpty(), "No genre options found in the filter dropdown.");

        for (String genre : genreOptions) {
            adminMovies.selectEachGenre(genre);

            List<WebElement> filteredFilmCards = adminMovies.getFilmCards();

            List<String> filmGenres = adminMovies.getAllFilmGenres(filteredFilmCards);

            MoviesAssertions.assertGenreFiltered(filmGenres, genre);
        }
    }

    @Test
    @DisplayName("Admin's Privacy Page Access Test")
    public void testAdminPrivacyPageAccess() {
        navigateToPrivacyPage();
        assertEquals("Privacy Policy", driver.findElement(By.tagName("h1")).getText());

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("p"), "This website is built as a sample project for learning purposes"));
        assertTrue(driver.findElement(By.tagName("p")).getText().contains("This website is built as a sample project for learning purposes"));
    }

    @Test
    @DisplayName("Admin's Manage Account Page Access Test")
    public void testAdminProfilePageAccess() {
        navigateToProfileManagementPage();

        assertEquals("Change your account settings", driver.findElement(By.tagName("h2")).getText());
        assertTrue(driver.findElement(By.id("manage")).getText().contains(driver.findElement(By.id("Username")).getText()), "The displayed username does not match the expected username.");
    }

    @Test
    @DisplayName("Admin Logout Test")
    public void testAdminLogout() {
        clickLogoutButton();

        assertEquals("Movies Collection", driver.getTitle());
        assertTrue(driver.findElement(By.id("login")).isDisplayed());
    }

    @Test
    @DisplayName("Admin's User Management Page Access Test")
    public void testAdminUserManagementPageAccess() {
        navigateToUserManagementPage();

        assertTrue(driver.getCurrentUrl().contains("UserManagement"), "The current URL does not contain 'UserManagement' after navigating to the User Management page.");

        assertEquals("User Management", driver.findElement(By.tagName("h1")).getText(), "The page heading does not match 'User Management' after navigating to the User Management page.");
    }

    @Test
    @DisplayName("Admin's User Management Page - Delete User Test")
    public void testAdminUserManagementPageDeleteUser() {
        navigateToUserManagementPage();

        WebElement testUserElement = driver.findElement(By.xpath("//td[normalize-space()='test@mail.com']"));

        WebElement deleteTestUserButton = driver.findElement(By.xpath("//tr[td[normalize-space()='test@mail.com']]/td[@class='text-center']/form[@method='post']/button[@type='submit']"));

        deleteTestUserButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        wait.until(ExpectedConditions.invisibilityOf(testUserElement));

        assertTrue(driver.findElements(By.xpath("//td[normalize-space()='test@mail.com']")).isEmpty(), "The test user 'test@mail.com' is still displayed in the user management table after deletion.");

    }
}
