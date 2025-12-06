package movieapp;
import java.util.Scanner;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        MovieRecommenderService service = new MovieRecommenderService();

        System.out.print("Enter a genre name: ");
        String genre = scanner.nextLine();

        String json = service.fetchMovies(genre);

        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonArray titles = root.getAsJsonArray("titles");
        System.out.println("Top ten movies:");

        for (int i = 0; i < Math.min(10, titles.size()); i++) {
            JsonObject movie = titles.get(i).getAsJsonObject();
            System.out.println(movie.get("title").getAsString());
        }
    }
}
