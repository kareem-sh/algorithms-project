import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BinaryTreeInputGUI extends JFrame {

    private JTextField inputField;
    private JButton drawButton;

    public BinaryTreeInputGUI() {
        setTitle("Binary Tree Input");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
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

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered flow layout with spacing
        JLabel inputLabel = new JLabel("Enter the binary tree string:");
        inputField = new JTextField(30); // Larger input field
        inputField.setFont(new Font("poppins", Font.PLAIN, 24)); // Set font size
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered flow layout with spacing
        drawButton = new JButton("Draw Tree");
        drawButton.setFont(new Font("poppins", Font.BOLD, 24)); // Set font size and style
        drawButton.setBackground(new Color(0, 102, 204)); // Blue background color
        drawButton.setForeground(Color.WHITE); // White text color
        drawButton.setFocusPainted(false); // Remove focus border
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                if (!input.isEmpty()) {
                    Node root = StringSplitter.splitString(input);
                    TreeVisualizer visualizerPanel = new TreeVisualizer(root);
        
                    JFrame frame = new JFrame("Binary Tree Visualizer");
                    frame.getContentPane().add(visualizerPanel);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
        
                    // Close the input GUI
                    BinaryTreeInputGUI.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(BinaryTreeInputGUI.this,
                            "Please enter a binary tree string.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(drawButton);

        // Add components to main panel
        gbc.gridy = 1;
        mainPanel.add(inputPanel, gbc);
        gbc.gridy = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BinaryTreeInputGUI().setVisible(true);
            }
        });
       
      /*  String input = "(A[20,10] | (B[20,10]|C[30,10])) - (D[30,50] | (E[40,30] - F[40,20]))";
        Node root = StringSplitter.splitString(input);
        StringSplitter.export(root);*/
    /*    try {

        StringSplitter s= new StringSplitter();
        ArrayList<Node>arr = new ArrayList<>();
        arr=s.TreeTNodes(root);
        for(Node f:arr)
        {
            System.out.println(f.data);
        }
        StringSplitter.drawTree(root , "tree.txt");
        StringSplitter.SwapDraw(root , "tree2.txt");
       } catch (Exception e) {
     
       } 
      
    
     */
    }
}

