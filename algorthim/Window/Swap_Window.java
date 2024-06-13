package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import src.BinaryTreeInputGUI;
import src.StringSplitter;
import src.Node;
import src.TreeVisualizer;

public class Swap_Window extends JFrame {
    private JButton Tree;
    private JButton Rec;

    private JButton Str;

    public Swap_Window(Node n  ) throws IOException {
        setTitle("Swap Mode");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        Node node=StringSplitter.SwapTree(n);

        JPanel panel = new JPanel(new GridLayout(1, 3));
        Tree = new JButton("Swap Tree");
        panel.add(Tree);

        Rec = new JButton("Swap Rectangle");

        panel.add(Rec);

        Str = new JButton("Swap String");
        panel.add(Str);

        Tree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeVisualizer tv=new TreeVisualizer(node,Swap_Window.this);
                JFrame frame = new JFrame("Binary Tree Visualizer");
                frame.getContentPane().add(tv);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                // Close the input GUI



            }
        });

        Rec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StringSplitter.ExportToRec(node,"SwapTree.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Export_Window ew=new Export_Window("SwapTree.txt",node);
                ew.setVisible(true);

            }
        });
        Str.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Export_String es=new Export_String(node) ;
                es.setVisible(true);

            }
        });

        add(panel);
        setVisible(true);
    }

}


