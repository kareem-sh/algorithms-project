package Window;


import src.Node;
import src.StringSplitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryTreeInput extends JFrame {
    private JTextField nameField, widthField, heightField;
    private JComboBox<Node> parentComboBox;
    private DefaultComboBoxModel<Node> parentComboBoxModel;
    private ArrayList<Node> nodes;
    private Node root;

    public BinaryTreeInput() {
        setTitle("Binary Tree Input");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nodes = new ArrayList<>();
        parentComboBoxModel = new DefaultComboBoxModel<>();

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Node Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Width:"));
        widthField = new JTextField();
        panel.add(widthField);

        panel.add(new JLabel("Height:"));
        heightField = new JTextField();
        panel.add(heightField);

        panel.add(new JLabel("Parent:"));
        parentComboBox = new JComboBox<>(parentComboBoxModel);
        panel.add(parentComboBox);

        JButton addButton = new JButton("Add Node");
        panel.add(addButton);

        JButton saveButton = new JButton("Save Tree");

        panel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    savetree();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNode();
            }
        });

        add(panel);
    }
    private void savetree() throws IOException {


       StringSplitter.ExportToRec(root,"tree.txt");
        Export_Window ew=new Export_Window();
        StringSplitter.SwapDraw(root,"SwapTree.txt");
       ew.setVisible(true);

    }
    private void addNode() {
        String name = nameField.getText();
        int width = Integer.parseInt(widthField.getText());
        int height = Integer.parseInt(heightField.getText());

        Node node = new Node(name, width, height);
        nodes.add(node);

        if (root == null) {
            root = node;
        } else {
            Node parent = (Node) parentComboBox.getSelectedItem();
            if (parent != null) {
                if (parent.left == null) {
                    parent.left = node;
                } else if (parent.right == null) {
                    parent.right = node;
                }
            }
        }

        updateParentComboBox();
        clearFields();

    }

    private Node findNodeByName(String name) {
        for (Node node : nodes) {
            if (node.data.equals(name)) {
                return node;
            }
        }
        return null;
    }

    private void updateParentComboBox() {
        parentComboBoxModel.removeAllElements();
        for (Node node : nodes) {
            if ((node.data.equals("|") || node.data.equals("-")) && (node.left == null || node.right == null)) {
                parentComboBoxModel.addElement(node);
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        widthField.setText("");
        heightField.setText("");
    }






    }
