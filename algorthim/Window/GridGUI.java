package Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import src.RectangleStitcher;
import src.Rectangle;
public class GridGUI extends JFrame {
    private List<Rectangle> rectangles  ;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField nameField;
    private JTextArea outputArea;

    public GridGUI() {
      rectangles = new ArrayList<>();

        setTitle("Rectangle Grid GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set custom panel with background image
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("Pictures/wallpaperflare.com_wallpaper (22).jpg").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setForeground(Color.WHITE);
        widthField = new JTextField();
        styleTextField(widthField);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setForeground(Color.WHITE);
        heightField = new JTextField();
        styleTextField(heightField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField();
        styleTextField(nameField);

        JButton addButton = new JButton("Add Rectangle");
        styleButton(addButton);
        addButton.addActionListener(new AddButtonListener());

        JButton canFormButton = new JButton("Can Form Rectangle");
        styleButton(canFormButton);
        canFormButton.addActionListener(new CanFormButtonListener());

        JButton generateButton = new JButton("Generate Combinations");
        styleButton(generateButton);
        generateButton.addActionListener(new GenerateButtonListener());

        JButton writeFileButton = new JButton("Write Grid to File");
        styleButton(writeFileButton);
        writeFileButton.addActionListener(new WriteFileButtonListener());

        panel.add(widthLabel);
        panel.add(widthField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(addButton);
        panel.add(canFormButton);
        panel.add(generateButton);
        panel.add(writeFileButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setOpaque(false);
        outputArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        backgroundPanel.add(panel, BorderLayout.NORTH);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(new Color(30, 30, 30, 150));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                String nameText = nameField.getText();
                if (nameText.length() != 1) {
                    outputArea.append("Invalid input! Please enter a single character for name.\n");
                    return;
                }
                String name = nameText;
                rectangles.add(new Rectangle(width, height, name));
                outputArea.append("Added Rectangle: " + name + " (" + width + "x" + height + ")\n");

                // Clear text fields after adding
                widthField.setText("");
                heightField.setText("");
                nameField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid input! Please enter valid numbers for width and height.\n");
            }
        }
    }

    private class CanFormButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean result = RectangleStitcher.canFormRectangle(rectangles);
            outputArea.append("Can form rectangle: " + result + "\n");
        }
    }

    private class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] maxRectangles = new int[1];

            Rectangle.generateCombinations(rectangles, new ArrayList<>(), 0, maxRectangles);
            outputArea.append("The most amount of rectangles that can be formed: " + maxRectangles[0] + "\n");
        }
    }

    private class WriteFileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int targetWidth = RectangleStitcher.getTotalWidth();
                int targetHeight = RectangleStitcher.getTotalHeight();

                Rectangle[][] used = new Rectangle[targetWidth][targetHeight];

                List<Rectangle> remainingRectangles = new ArrayList<>(rectangles);

                boolean success = Rectangle.backtrack(rectangles, targetWidth, targetHeight, 0, 0, remainingRectangles, used);

                if (success) {
                    outputArea.append("Rectangle has been formed successfully\n");

                    Rectangle.printUsedGrid(used, targetWidth, targetHeight);
                    String fileName = "Interface_grid.txt";
                    Rectangle.writeGridToFile(used, fileName);
                    outputArea.append("The grid has been saved to file successfully: " + fileName + "\n");
                    displayFileContents(fileName);
                } else {
                    outputArea.append("The rectangle can't be formed\n");
                }
            } catch (IOException ex) {
                outputArea.append("Error while writing in the file: " + ex.getMessage() + "\n");
            }
        }
    }

    private void displayFileContents(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            outputArea.append("File contents:\n");
            String line;
            while ((line = reader.readLine()) != null) {
                outputArea.append(line + "\n");
            }
        } catch (IOException ex) {
            outputArea.append("Error reading file: " + ex.getMessage() + "\n");
        }
    }
}
