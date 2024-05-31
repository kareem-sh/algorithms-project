package Window;

import src.StringSplitter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import javax.swing.*;
import java.awt.*;
public class Export_String extends JFrame {
    private JLabel textArea;

    public Export_String() {
        // Set up the frame
        setTitle("File Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a text area
        textArea = new JLabel();
        Font font = new Font("Monospaced", Font.PLAIN, 14); // Example: Monospaced, Plain, 14pt
        textArea.setFont(font);  // Make text area read-only


        // Add scroll pane (which contains the text area) to the frame
        add(textArea, BorderLayout.CENTER);

        // Load file content


        // Make the frame visible
        setVisible(true);
    }
    private void loadFileContent() {
    }
}
