package Window;

import src.BinaryTreeInputGUI;
import src.StringSplitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Window extends JFrame {
    private JButton StringToTree;
    private JButton RecToTree;
    private JButton TreeToRec;

    public Main_Window() {
        setTitle("Binary Tree Input");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() { JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Algorithm 2 Project");
        welcomeLabel.setFont(new Font("poppins", Font.BOLD, 52)); // Larger font
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 100, 0); // Add padding between welcome label and input field
        mainPanel.add(welcomeLabel, gbc);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        StringToTree = new JButton("From String to Tree");
        StringToTree.setFont(new Font("poppins", Font.BOLD, 24)); // Set font size and style
        StringToTree.setBackground(new Color(0, 102, 204)); // Blue background color
        StringToTree.setForeground(Color.WHITE); // White text color
        StringToTree.setFocusPainted(false); // Remove focus border
        StringToTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinaryTreeInputGUI B = new BinaryTreeInputGUI();
                B.setVisible(true);

            }
        });
        RecToTree = new JButton("From Rectangle to Tree");
        RecToTree.setFont(new Font("poppins", Font.BOLD, 24)); // Set font size and style
        RecToTree.setBackground(new Color(0, 102, 204)); // Blue background color
        RecToTree.setForeground(Color.WHITE); // White text color
        RecToTree.setFocusPainted(false); // Remove focus border
        RecToTree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Import_Window iw=new Import_Window();
                iw.setVisible(true);
            }
        });
        TreeToRec = new JButton("From Tree to Rectangle");
        TreeToRec.setFont(new Font("poppins", Font.BOLD, 24)); // Set font size and style
        TreeToRec.setBackground(new Color(0, 102, 204)); // Blue background color
        TreeToRec.setForeground(Color.WHITE); // White text color
        TreeToRec.setFocusPainted(false); // Remove focus border
        TreeToRec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BinaryTreeInput bti=new BinaryTreeInput();
                bti.setVisible(true);

            }
        });
        buttonPanel.add(StringToTree);
        buttonPanel.add(RecToTree);
        buttonPanel.add(TreeToRec);
        gbc.gridy = 2;
        mainPanel.add(buttonPanel, gbc);
        add(mainPanel);
        }
    public static void main(String[] args) {
        StringSplitter.FormRectangle();
        StringSplitter.RectangleCombinations();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });

    }

    }

