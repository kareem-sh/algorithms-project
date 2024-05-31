package Window;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import javax.swing.*;
import java.awt.*;
public class Export_Window extends JFrame {
    private JTextArea textArea;

    public Export_Window() {
        // Set up the frame
        setTitle("File Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        Font font = new Font("Monospaced", Font.PLAIN, 14); // Example: Monospaced, Plain, 14pt
        textArea.setFont(font);  // Make text area read-only
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add scroll pane (which contains the text area) to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Load file content
        loadFileContent("tree.txt");

        // Make the frame visible
        setVisible(true);
    }
    private void loadFileContent(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            textArea.read(reader,null);
        } catch (IOException e) {
            textArea.setText("Failed to load file content: " + e.getMessage());
        }
    }
}
