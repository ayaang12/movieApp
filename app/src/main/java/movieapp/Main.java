package movieapp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;  
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Movie Recommender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JTextField genreField = new JTextField(20);
        JTextField streamingField = new JTextField(20);

        JButton fetchButton = new JButton("Get Recommendations");

        JTextArea output = new JTextArea();
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genreField);
        inputPanel.add(new JLabel("Streaming services (comma-separated):"));
        inputPanel.add(streamingField);
        inputPanel.add(fetchButton);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        fetchButton.addActionListener((ActionEvent e) -> {
                try {
                    String genreinput = genreField.getText().trim().toLowerCase();
                    String genreId = GenreMap.GENRES.get(genreinput);
                    String streamingServices = streamingField.getText().trim().toLowerCase();

                    if (genreId == null) {
                        output.setText("Invalid Genre");
                        return;
                    }

                    if (streamingServices.isEmpty()) {
                        output.setText("Invalid Streaming Service");
                        return;
                    }

                    MovieRecommenderService service = new MovieRecommenderService();
                    String json = service.fetchMovies(genreId, streamingServices);

                    JsonObject root = JsonParser.parseString(json).getAsJsonObject();
                    JsonArray titles = root.getAsJsonArray("titles");

                    StringBuilder sb = new StringBuilder();
                    sb.append("Movie recommendations:\n\n");

                    for (int i = 0; i < Math.min(25, titles.size()); i++) {
                        JsonObject movie = titles.get(i).getAsJsonObject();
                        sb.append(movie.get("title").getAsString()).append("\n");
                    }

                    output.setText(sb.toString());

                } catch (Exception ex) {
                    output.setText("Error fetching movies:\n" + ex.getMessage());
                }
            });

            frame.setVisible(true);
         });
    
}
}