    /*import javax.swing.*;
    import java.awt.*;
    class TreePanel extends JPanel {
        private final BinaryNode root;
        private final int nodeRadius = 20; // Set the radius of the circle

        TreePanel(BinaryNode root) {
            this.root = root;
            this.setPreferredSize(new Dimension(800, 600));
            this.setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (root != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setFont(loadFont("Poppins-Regular.ttf", 22f));
                drawTree(g2d, root, getWidth() / 2, 50, getWidth() / 4);
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
                g.drawLine(lineStartX, lineStartY, x - horizontalGap, y + 50);
                drawTree(g, node.left, x - horizontalGap, y + 50, horizontalGap / 2);
            }

            if (node.right != null) {
                // Calculate line start point
                int lineStartX = x + (int) (Math.cos(Math.atan2(50, horizontalGap)) * nodeRadius);
                int lineStartY = y + (int) (Math.sin(Math.atan2(50, horizontalGap)) * nodeRadius);
                g.setColor(Color.BLACK);
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
                return new Font("poppins", Font.PLAIN, 22);
            }
        }
    }*/

    import javax.swing.*;

import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionListener;

    class TreePanel extends JPanel {
        private final BinaryNode root;
        private final int nodeRadius = 22; // Increased node size
        private final int lineWidth = 2; // Increased line width
        private JFrame parentFrame;

        TreePanel(BinaryNode root,JFrame parentFrame) {
            this.root = root;
            this.setBackground(Color.WHITE);
            
            // Set preferred size to match screen dimensions
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setPreferredSize(screenSize);
              // Create the button and customize its appearance
        JButton convertButton = new JButton("Convert to general Tree");
        convertButton.setFont(new Font("Poppins-Regular", Font.PLAIN, 22));
        convertButton.setPreferredSize(new Dimension(300, 50)); // Set preferred size for the button
        convertButton.setBackground(new Color(100, 149, 237)); // Same color as nodes
        convertButton.setForeground(Color.WHITE); // Text color
        convertButton.setFocusPainted(false); // Remove focus border
        convertButton.setBorderPainted(false); // Remove button border
        convertButton.setContentAreaFilled(false); // Remove default background fill
        convertButton.setOpaque(true); // Ensure background color is visible

        convertButton.addActionListener(new ActionListener() {
     

            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                JFrame frame = new JFrame("Binary Tree");
                NaryTreeGUI binaryTreeGUI = new NaryTreeGUI(BFS.convertToNary(root),frame);
               
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(binaryTreeGUI);
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

