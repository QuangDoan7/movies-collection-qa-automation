package helpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoviesAssertions {

    public static void assertTitleSorted(List<String> titles, boolean ascending) {
        if (titles.size() < 2) {
            throw new AssertionError("Not enough films to perform sorting assertion. At least 2 titles are required.");
        }

        for (int i = 0; i < titles.size() - 1; i++) {
            String currentTitle = titles.get(i);
            String nextTitle = titles.get(i + 1);

            if (ascending) {
                assertTrue(currentTitle.compareTo(nextTitle) <= 0, "Film titles are not sorted in ascending order by title.");
            } else {
                assertTrue(currentTitle.compareTo(nextTitle) >= 0, "Film titles are not sorted in descending order by title.");
            }
        }
    }

    public static void assertReleaseYearSorted(List<Integer> releaseYears, boolean ascending) {
        if (releaseYears.size() < 2) {
            throw new AssertionError("Not enough films to perform sorting assertion. At least 2 titles are required.");
        }

        for (int i = 0; i < releaseYears.size() - 1; i++) {
            int currentYear = releaseYears.get(i);
            int nextYear = releaseYears.get(i + 1);

            if (ascending) {
                assertTrue(currentYear <= nextYear, "Film release years are not sorted in ascending order.");
            } else {
                assertTrue(currentYear >= nextYear, "Film release years are not sorted in descending order.");
            }
        }
    }

    public static void assertGenreFiltered(
            List<String> filmGenres,
            String selectedGenre) {

        if (filmGenres.isEmpty()) {
            throw new AssertionError("No films found for selected genre: " + selectedGenre);
        }

        if (selectedGenre.equals("All")) {
            return;
        }

        for (String filmGenre : filmGenres) {
            assertTrue(filmGenre.contains(selectedGenre), "Film genre does not contain the selected filter genre: " + selectedGenre);
        }
    }
}
