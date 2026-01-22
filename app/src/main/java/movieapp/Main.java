package movieapp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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

            //edited to center output
            JTextPane output = new JTextPane();
            output.setEditable(false);

            StyledDocument doc = output.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            JScrollPane scrollPane = new JScrollPane(output);
            //

            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Genre:"));
            inputPanel.add(genreField);
            inputPanel.add(new JLabel("Streaming service:"));
            inputPanel.add(streamingField);
            inputPanel.add(fetchButton);

            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            fetchButton.addActionListener((ActionEvent e) -> {
                try {
                    String genreInput = genreField.getText().trim().toLowerCase();
                    String streamingInput = streamingField.getText().trim().toLowerCase();

                    String genreId = GenreMap.GENRES.get(genreInput);
                    String streamingId = ServiceMap.SERVICES.get(streamingInput);

                    if (genreId == null) {
                        output.setText("Invalid Genre");
                        return;
                    }

                    if (streamingId == null) {
                        output.setText("Invalid Streaming Service");
                        return;
                    }

                    MovieRecommenderService service = new MovieRecommenderService();

                    // fix id passing
                    String json = service.fetchMovies(genreId, streamingId);

                    JsonObject root = JsonParser.parseString(json).getAsJsonObject();

                    // another check
                    if (!root.has("titles") || !root.get("titles").isJsonArray()) {
                        output.setText("No movies returned for these filters.");
                        return;
                    }

                    JsonArray titles = root.getAsJsonArray("titles");

                    if (titles.size() == 0) {
                        output.setText("No movies found.");
                        return;
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append("20 Movie recommendations:\n\n");

                    for (int i = 0; i < Math.min(20, titles.size()); i++) {
                        JsonObject movie = titles.get(i).getAsJsonObject();
                        sb.append(movie.get("title").getAsString()).append("\n\n");
                    }

                    output.setText(sb.toString());

                } catch (Exception ex) {
                    output.setText("Error fetching movies:\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            });

            frame.setVisible(true);
        });
    }
}
