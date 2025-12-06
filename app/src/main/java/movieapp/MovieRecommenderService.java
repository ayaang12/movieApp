package movieapp;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class MovieRecommenderService {

    private final String apiKey = "APIKEYYYYYYY";

    public String fetchMovies(String genreId) throws Exception {

        String url = "https://api.watchmode.com/v1/list-titles/?apiKey=" + apiKey + "&genres=" + genreId + "&types=movie" + "&sort_by=popularity_desc";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
