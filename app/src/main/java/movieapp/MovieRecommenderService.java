package movieapp;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Fetches a list of movies from the Watchmode API filtered by genre.
 *
 * <p>This method sends an HTTP GET request to the Watchmode {@code list-titles}
 * endpoint and retrieves movie titles sorted by popularity in descending order.
 * The response is returned as a raw JSON string.</p>
 *
 * @param genreId the Watchmode genre ID used to filter movies
 * @param streamingId the Watchmode streaming service ID (currently unused)
 * @return a JSON-formatted {@link String} containing the API response
 * @throws Exception if an error occurs while sending the HTTP request or receiving the response
 */
public class MovieRecommenderService {
    private final String apiKey = "API_KEY_HERE";

    public String fetchMovies(String genreId, String streamingId) throws Exception {

        String url = "https://api.watchmode.com/v1/list-titles/?apiKey=" + apiKey + "&genres=" + genreId + "&source_ids=" + streamingId + "&types=movie" + "&sort_by=popularity_desc";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
