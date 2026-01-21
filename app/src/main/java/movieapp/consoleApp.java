// package movieapp;
// import java.util.Scanner;

// import com.google.gson.JsonArray;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParser;

// public class Main {
//     public static void main(String[] args) throws Exception {
//         Scanner in = new Scanner(System.in);
//         MovieRecommenderService service = new MovieRecommenderService();

//         System.out.print("Enter a genre name: ");
//         String input = in.nextLine().trim().toLowerCase();
//         String genreId = GenreMap.GENRES.get(input);

//         if (genreId == null) {
//             System.out.println("Invalid Genre");
//             return;
//         }

//         String json = service.fetchMovies(genreId);
//         JsonObject root = JsonParser.parseString(json).getAsJsonObject();
//         JsonArray titles = root.getAsJsonArray("titles");

//         System.out.println("Movie reccomendations:");

//         for (int i = 0; i < Math.min(10, titles.size()); i++) {
//             JsonObject movie = titles.get(i).getAsJsonObject();
//             System.out.println(movie.get("title").getAsString());
//         }
//     }
// }
