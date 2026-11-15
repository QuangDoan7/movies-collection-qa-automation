package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MoviesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MoviesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<WebElement> getFilmCards() {
        return driver.findElements(By.className("card"));
    }

    public String getFilmTitle(WebElement filmCard) {
        return filmCard.findElement(By.cssSelector("[data-testid='title']")).getText();
    }

    public List<String> getAllFilmTitles(List<WebElement> filmCards) {
        List<String> titles = new ArrayList<>();
        for (WebElement filmCard : filmCards) {
            titles.add(getFilmTitle(filmCard));
        }
        return titles;
    }

    public void clickTitleSortButton() {
        driver.findElement(By.xpath("//a[contains(@href, 'sortOrder=title_desc')]")).click();
    }

    public int getFilmReleaseYear(WebElement filmCard) {
        return Integer.parseInt(filmCard.findElement(By.cssSelector("[data-testid='release-year']")).getText());
    }

    public List<Integer> getAllFilmReleaseYears(List<WebElement> filmCards) {
        List<Integer> releaseYears = new ArrayList<>();
        for (WebElement filmCard : filmCards) {
            releaseYears.add(getFilmReleaseYear(filmCard));
        }
        return releaseYears;
    }

    public void clickYearSortButton() {
        driver.findElement(By.xpath("//a[contains(@href, 'sortOrder=year')]")).click();
    }

    public List<String> getGenreOptions() {
        WebElement filterDropdownElement = driver.findElement(By.cssSelector("select[name='genre']"));
        Select filterDropdown = new Select(filterDropdownElement);

        List<WebElement> filterDropdownOptions = filterDropdown.getOptions();

        List<String> genreOptions = new ArrayList<>();
        String genreOptionText;

        for (WebElement option : filterDropdownOptions) {
            genreOptionText = option.getText();
            genreOptions.add(genreOptionText);
        }
        return genreOptions;
    }

    public void selectEachGenre(String genre) {
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='genre']")));

        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(genre);

        if (genre.equals("All")) {
            wait.until(ExpectedConditions.urlContains("Movies"));
        } else {
            wait.until(ExpectedConditions.urlContains("Movies?genre=" + genre));
        }
    }

    public String getFilmGenre(WebElement filmCard) {
        return filmCard.findElement(By.cssSelector("[data-testid='genre']")).getText();
    }

    public List<String> getAllFilmGenres(List<WebElement> filmCards) {
        List<String> genres = new ArrayList<>();
        for (WebElement filmCard : filmCards) {
            genres.add(getFilmGenre(filmCard));
        }
        return genres;
    }


}
