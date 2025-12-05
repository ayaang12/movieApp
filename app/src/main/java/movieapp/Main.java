package movieapp;

import java.util.Scanner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        MovieRecommenderService service = new MovieRecommenderService();

        System.out.print("Enter a genre ID: ");
        String genre = scanner.nextLine();

        String json = service.fetchMovies(genre);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(json);

        JsonNode titles = tree.get("titles");

        System.out.println("Top ten movies:");

        for (int i = 0; i < Math.min(10, titles.size()); i++) {
            System.out.println(titles.get(i).get("title").asText());
        }
    }
}

