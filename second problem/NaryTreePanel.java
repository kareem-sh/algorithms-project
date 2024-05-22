import javax.swing.*;
import java.awt.*;
import java.util.List;

class NaryTreeGUI extends JPanel {
    private Node root;
    private final int nodeRadius = 30; // Increased node size
    private final int lineWidth = 3; // Increased line width
    private final int verticalGap = 150; // Increased vertical gap

    NaryTreeGUI(Node root) {
        this.root = root;
        this.setBackground(Color.WHITE);

        // Set preferred size to match screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
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
