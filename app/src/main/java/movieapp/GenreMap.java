package movieapp;

import java.util.Map;

public class GenreMap {
/**
 * A lookup table that converts user-provided genre names into the
 * corresponding Watchmode genre IDs.
 *
 * <p>This map allows users to input genres using readable names
 * (for example, {@code "comedy"} or {@code "science fiction"}),
 * which are then translated into the numeric genre identifiers
 * required by the Watchmode API.</p>
 *
 * <p>Using this mapping removes the need for users to know or remember
 * Watchmode-specific IDs and ensures valid API requests are constructed.</p>
 */
    public static final Map<String, String> GENRES = Map.ofEntries(
        Map.entry("action", "1"),
        Map.entry("action & adventure", "39"),
        Map.entry("adult", "30"),
        Map.entry("adventure", "2"),
        Map.entry("animation", "3"),
        Map.entry("anime", "33"),
        Map.entry("biography", "31"),
        Map.entry("comedy", "4"),
        Map.entry("crime", "5"),
        Map.entry("documentary", "6"),
        Map.entry("drama", "7"),
        Map.entry("family", "8"),
        Map.entry("fantasy", "9"),
        Map.entry("food", "34"),
        Map.entry("game show", "28"),
        Map.entry("history", "10"),
        Map.entry("horror", "11"),
        Map.entry("kids", "21"),
        Map.entry("music", "12"),
        Map.entry("musical", "32"),
        Map.entry("mystery", "13"),
        Map.entry("nature", "36"),
        Map.entry("news", "22"),
        Map.entry("reality", "23"),
        Map.entry("romance", "14"),
        Map.entry("sci-fi & fantasy", "40"),
        Map.entry("science fiction", "15"),
        Map.entry("soap", "25"),
        Map.entry("sports", "29"),
        Map.entry("supernatural", "37"),
        Map.entry("talk", "26"),
        Map.entry("thriller", "17"),
        Map.entry("travel", "35"),
        Map.entry("tv movie", "38"),
        Map.entry("war", "18"),
        Map.entry("war & politics", "41"),
        Map.entry("western", "19")
    );
}
