import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class NaryTreeGUI extends JPanel {
    private Node root;
    private final int nodeRadius = 30;
    private final int lineWidth = 3; 
    private final int verticalGap = 100; 

    NaryTreeGUI(Node root, JFrame parentFrame) {
        this.root = root;

        this.setBackground(Color.WHITE);

       
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);

        
        JButton convertButton = new JButton("Convert to Binary Tree");
        convertButton.setFont(new Font("Poppins-Regular", Font.PLAIN, 22));
        convertButton.setPreferredSize(new Dimension(300, 50)); 
        convertButton.setBackground(new Color(100, 149, 237)); // Same color as nodes
        convertButton.setForeground(Color.WHITE); // Text color
        convertButton.setFocusPainted(false); // Remove focus border
        convertButton.setBorderPainted(false); // Remove button border
        convertButton.setContentAreaFilled(false); // Remove default background fill
        convertButton.setOpaque(true); 

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Binary Tree");
                TreePanel binaryTreeGUI = new TreePanel(BFS.convertToBinary(root), frame);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new JScrollPane(binaryTreeGUI, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
                frame.setVisible(true);
                parentFrame.dispose(); // Dispose of the current frame
            }
        });

        // Use GridBagLayout to center the button horizontally
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Add space below the button
        gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally
        buttonPanel.add(convertButton, gbc);

        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Add scroll pane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE); // Set background color of scroll pane

        // Add scroll pane to the main frame
        parentFrame.add(scrollPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(loadFont("Poppins-Regular.ttf", 22f));

            // Get the width of the panel
            int width = getWidth();

            // Draw the tree with the full width
            drawTree(g2d, root, width / 2, 50, width / 4);
        }
    }

    private void drawTree(Graphics2D g, Node node, int x, int y, int horizontalGap) {
        if (node == null) return;

        // Draw the circle
        g.setColor(new Color(100, 149, 237));
        g.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);

        // Draw the string centered within the circle
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics();
        int stringWidth = metrics.stringWidth(node.data);
        int stringHeight = metrics.getHeight();
        int stringX = x - stringWidth / 2;
        int stringY = y + stringHeight / 4;
        g.drawString(node.data, stringX, stringY);

        List<Node> children = node.children;
        int numChildren = children.size();
        if (numChildren > 0) {
            int startY = y + nodeRadius;
            int startX = x - (numChildren - 1) * horizontalGap / 2;
            for (int i = 0; i < numChildren; i++) {
                Node child = children.get(i);
                int childX = startX + i * horizontalGap;
                int childY = startY + verticalGap; // Increased vertical position for child nodes
                // Draw line connecting parent and child
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(lineWidth)); // Set line thickness
                g.drawLine(x, startY, childX, childY - nodeRadius);
                // Draw child recursively
                drawTree(g, child, childX, childY, horizontalGap / 2);
            }
        }
    }

    private Font loadFont(String fontPath, float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(fontPath));
            return font.deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Poppins-Regular.ttf", Font.PLAIN, 22);
        }
    }
}
