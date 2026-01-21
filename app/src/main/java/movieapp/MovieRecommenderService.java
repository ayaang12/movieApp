package movieapp;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MovieRecommenderService {
    private final String apiKey = "0R8EPlKofuR32A169zdhQVjkotjeWRt5Vj6KpkKa";

    public String fetchMovies(String genreId, String streamingServices) throws Exception {

        String url = "https://api.watchmode.com/v1/list-titles/?apiKey=" + apiKey + "&genres=" + genreId + "&types=movie" + "&sources=" + streamingServices + "&sort_by=popularity_desc";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
