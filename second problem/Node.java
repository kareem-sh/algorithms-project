import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


class Node {
    String data;
    List<Node> children;

    Node(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    void addChild(Node child) {
        this.children.add(child);
    }


    
        public static void main(String[] args) {
            // Creating a tree with root and some children
         BFS b= new BFS();
        BinaryNode binaryRoot = BFS.convertToBinary(b.Import("tree.txt"));
        // Create and set up the window
        JFrame frame = new JFrame("Tree Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the custom drawing panel
        TreePanel treePanel = new TreePanel(binaryRoot);

       // NaryTreeGUI treePanel = new NaryTreeGUI(b.Import("tree.txt"));
        frame.add(treePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }
    }