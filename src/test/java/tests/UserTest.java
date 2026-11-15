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

public class UserTest extends BaseTest {
    LoginPage userLogin;
    MoviesPage userMovies;

    @BeforeEach
    public void loginAsUser() {
        navigateToLoginPage();

        userLogin = new LoginPage(driver);
        userLogin.login("userABC123@mail.com", "userABC123!");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("manage"), "Hello userABC123@mail.com!"));

        userMovies = new MoviesPage(driver);
    }

    @Test
    @DisplayName("User Login Test")
    public void testUserLogin() {
        assertTrue(driver.findElement(By.id("manage")).getText().contains("Hello userABC123@mail.com!"));
        assertEquals("Logout", driver.findElement(By.id("logout")).getText());
    }

    @Test
    @DisplayName("User's Movies Page Access Test")
    public void testUserMoviesPageAccess() {
        navigateToMoviesPage();
        assertEquals("Movies", driver.findElement(By.tagName("h1")).getText());

        List<WebElement> filmCards = userMovies.getFilmCards();
        assertFalse(filmCards.isEmpty(), "No film cards found on the Movies page of sample User account.");
    }

    @Test
    @DisplayName("User's Movies Page - Title Sorting Test")
    public void testUserMoviesPageTitleSorting() {
        navigateToMoviesPage();

        List<WebElement> filmCardsAscending = userMovies.getFilmCards();

        assertFalse(filmCardsAscending.isEmpty(), "No film cards found on the Movies page of sample User account after sorting.");

        List<String> titlesAscending = userMovies.getAllFilmTitles(filmCardsAscending);
        MoviesAssertions.assertTitleSorted(titlesAscending, true);

        userMovies.clickTitleSortButton();
        wait.until(ExpectedConditions.urlContains("Movies?sortOrder=title_desc"));

        List<WebElement> filmCardsDescending = userMovies.getFilmCards();

        assertFalse(filmCardsDescending.isEmpty(), "No film cards found on the Movies page of sample User account after sorting.");

        List<String> titlesDescending = userMovies.getAllFilmTitles(filmCardsDescending);
        MoviesAssertions.assertTitleSorted(titlesDescending, false);

    }

    @Test
    @DisplayName("User's Movies Page - Release Year Sorting Test")
    public void testUserMoviesPageReleaseYearSorting() {
        navigateToMoviesPage();

        userMovies.clickYearSortButton();
        wait.until(ExpectedConditions.urlContains("Movies?sortOrder=year"));

        List<WebElement> filmCardsAscending = userMovies.getFilmCards();
        assertFalse(filmCardsAscending.isEmpty(), "No film cards found on the Movies page of sample User account.");

        List<Integer> releaseYearsAscending = userMovies.getAllFilmReleaseYears(filmCardsAscending);
        MoviesAssertions.assertReleaseYearSorted(releaseYearsAscending, true);

        userMovies.clickYearSortButton();
        wait.until(ExpectedConditions.urlContains("Movies?sortOrder=year_desc"));

        List<WebElement> filmCardsDescending = userMovies.getFilmCards();
        assertFalse(filmCardsDescending.isEmpty(), "No film cards found on the Movies page of sample User account.");

        List<Integer> releaseYearsDescending = userMovies.getAllFilmReleaseYears(filmCardsDescending);
        MoviesAssertions.assertReleaseYearSorted(releaseYearsDescending, false);
    }

    @Test
    @DisplayName("User's Movies Page - Filter by Genre Test")
    public void testUserMoviesPageFilterByGenre() {
        navigateToMoviesPage();

        List<String> genreOptions = userMovies.getGenreOptions();

        assertFalse(genreOptions.isEmpty(), "No genre options found in the filter dropdown.");

        for (String genre : genreOptions) {
            userMovies.selectEachGenre(genre);

            List<WebElement> filteredFilmCards = userMovies.getFilmCards();

            List<String> filmGenres = userMovies.getAllFilmGenres(filteredFilmCards);

            MoviesAssertions.assertGenreFiltered(filmGenres, genre);
        }
    }

    @Test
    @DisplayName("User's Privacy Page Access Test")
    public void testUserPrivacyPageAccess() {
        navigateToPrivacyPage();
        assertEquals("Privacy Policy", driver.findElement(By.tagName("h1")).getText());

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("p"), "This website is built as a sample project for learning purposes"));
        assertTrue(driver.findElement(By.tagName("p")).getText().contains("This website is built as a sample project for learning purposes"));
    }

    @Test
    @DisplayName("User's Manage Account Page Access Test")
    public void testUserProfilePageAccess() {
        navigateToProfileManagementPage();

        assertEquals("Change your account settings", driver.findElement(By.tagName("h2")).getText());
        assertTrue(driver.findElement(By.id("manage")).getText().contains(driver.findElement(By.id("Username")).getText()), "The displayed username does not match the expected username.");
    }

    @Test
    @DisplayName("User Logout Test")
    public void testUserLogout() {
        clickLogoutButton();

        assertEquals("Movies Collection", driver.getTitle());
        assertTrue(driver.findElement(By.id("login")).isDisplayed());
    }
}