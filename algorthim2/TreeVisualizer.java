import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
public class TreeVisualizer extends JPanel {
    private Map<Node, Point> nodePositions;
    private static final Color NODE_COLOR = new Color(0, 102, 204);
    private static final Color LINE_COLOR = Color.BLACK;
    private static final int NODE_RADIUS = 40;
    private static final int LINE_THICKNESS = 3;

    public TreeVisualizer(Node root) {
        nodePositions = new HashMap<>();
        calculateNodePositions(root, 950, 50, 400);
        setLayout(new BorderLayout());

        // Add back button to the bottom right
        JButton backButton = new JButton("Back");
        backButton.setBackground(NODE_COLOR); // Set background color
        backButton.setForeground(Color.WHITE); // Set text color
        backButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = getParent();
                while (parent != null && !(parent instanceof JFrame)) {
                    parent = parent.getParent();
                }
                if (parent instanceof JFrame) {
                    JFrame frame = (JFrame) parent;
                    frame.dispose();
                }
                new BinaryTreeInputGUI().setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void calculateNodePositions(Node node, int x, int y, int xOffset) {
        if (node == null) return;
        nodePositions.put(node, new Point(x, y));
        int nextXOffset = xOffset / 2;
        calculateNodePositions(node.left, x - xOffset, y + 150, nextXOffset);
        calculateNodePositions(node.right, x + xOffset, y + 150, nextXOffset);
    }

    private void drawNode(Graphics2D g, Node node, Point point) {
        g.setColor(NODE_COLOR);
        g.fillOval(point.x - NODE_RADIUS, point.y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(LINE_THICKNESS));
        g.drawOval(point.x - NODE_RADIUS, point.y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);

        Font font = new Font("Arial", Font.BOLD, 16);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics();
        int nameWidth = metrics.stringWidth(node.data);
        int nameHeight = metrics.getHeight();
        int nameX = point.x - nameWidth / 2;
        int nameY = point.y + nameHeight / 4;
        g.drawString(node.data, nameX, nameY);

        String dimensions = "[" + node.width + ", " + node.height + "]";
        int dimWidth = metrics.stringWidth(dimensions);
        int dimX = point.x - dimWidth / 2;
        int dimY = point.y + nameHeight / 4 + 20;
        g.drawString(dimensions, dimX, dimY);
    }

    private void drawEdge(Graphics2D g, Point from, Point to) {
        g.setColor(LINE_COLOR);
        g.setStroke(new BasicStroke(LINE_THICKNESS));

        double angle = Math.atan2(to.y - from.y, to.x - from.x);
        int x = (int) (to.x - NODE_RADIUS * Math.cos(angle));
        int y = (int) (to.y - NODE_RADIUS * Math.sin(angle));

        g.drawLine(from.x, from.y, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Map.Entry<Node, Point> entry : nodePositions.entrySet()) {
            Node node = entry.getKey();
            Point point = entry.getValue();
            drawNode(g2d, node, point);
            if (node.left != null) {
                Point leftPoint = nodePositions.get(node.left);
                drawEdge(g2d, new Point(point.x, point.y + NODE_RADIUS), leftPoint);
            }
            if (node.right != null) {
                Point rightPoint = nodePositions.get(node.right);
                drawEdge(g2d, new Point(point.x, point.y + NODE_RADIUS), rightPoint);
            }
        }
    }

 /*   public static void main(String[] args) {
        String input = "(A[20,10]|(B[20,10]|C[30,10]))-(D[30,50]|(E[40,30]-F[40,20]))";
        Node root = StringSplitter.splitString(input);

        JFrame frame = new JFrame("Binary Tree Visualizer");
        TreeVisualizer visualizer = new TreeVisualizer(root);
        frame.add(visualizer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/
}
