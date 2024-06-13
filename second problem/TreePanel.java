import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class TreePanel extends JPanel {
    private final BinaryNode root;
    private final int nodeRadius = 22;
    private final int lineWidth = 2;

    TreePanel(BinaryNode root, JFrame parentFrame) {
        this.root = root;
        this.setBackground(Color.WHITE);

        // Set a preferred size based on the expected size of your tree
        // Adjust these dimensions according to your tree's size requirements
        this.setPreferredSize(new Dimension(800, 600));

        JButton convertButton = new JButton("Convert to general Tree");
        convertButton.setFont(new Font("Poppins-Regular", Font.PLAIN, 22));
        convertButton.setPreferredSize(new Dimension(300, 50));
        convertButton.setBackground(new Color(100, 149, 237));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorderPainted(false);
        convertButton.setContentAreaFilled(false);
        convertButton.setOpaque(true);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                JFrame frame = new JFrame("Binary Tree");
                NaryTreeGUI binaryTreeGUI = new NaryTreeGUI(BFS.convertToNary(root), frame);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(binaryTreeGUI);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
                parentFrame.dispose();
            }
        });

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

    private void drawTree(Graphics2D g, BinaryNode node, int x, int y, int horizontalGap) {
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

        if (node.left != null) {
            // Calculate line start point
            int lineStartX = x - (int) (Math.cos(Math.atan2(50, horizontalGap)) * nodeRadius);
            int lineStartY = y + (int) (Math.sin(Math.atan2(50, horizontalGap)) * nodeRadius);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(lineWidth)); // Set line thickness
            g.drawLine(lineStartX, lineStartY, x - horizontalGap, y + 50);
            drawTree(g, node.left, x - horizontalGap, y + 50, horizontalGap / 2);
        }

        if (node.right != null) {
            // Calculate line start point
            int lineStartX = x + (int) (Math.cos(Math.atan2(50, horizontalGap)) * nodeRadius);
            int lineStartY = y + (int) (Math.sin(Math.atan2(50, horizontalGap)) * nodeRadius);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(lineWidth)); // Set line thickness
            g.drawLine(lineStartX, lineStartY, x + horizontalGap, y + 50);
            drawTree(g, node.right, x + horizontalGap, y + 50, horizontalGap / 2);
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

