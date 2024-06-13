import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryTreeInput extends JFrame {
    private JTextField nameField;
    private JComboBox<BinaryNode> parentComboBox;
    private DefaultComboBoxModel<BinaryNode> parentComboBoxModel;
    private JRadioButton leftButton;
    private JRadioButton rightButton;
    private ButtonGroup directionGroup;
    private ArrayList<BinaryNode> nodes;
    private BinaryNode root;

    public BinaryTreeInput() {
        setTitle("Binary Tree Input");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nodes = new ArrayList<>();
        parentComboBoxModel = new DefaultComboBoxModel<>();

        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel.add(new JLabel("Node Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Parent:"));
        parentComboBox = new JComboBox<>(parentComboBoxModel);
        panel.add(parentComboBox);

        panel.add(new JLabel("Position:"));
        JPanel radioPanel = new JPanel(new FlowLayout());
        leftButton = new JRadioButton("Left");
        rightButton = new JRadioButton("Right");
        directionGroup = new ButtonGroup();
        directionGroup.add(leftButton);
        directionGroup.add(rightButton);
        radioPanel.add(leftButton);
        radioPanel.add(rightButton);
        panel.add(radioPanel);

        JButton addButton = new JButton("Add Node");
        panel.add(addButton);
   
        JButton deleteButton = new JButton("Delete Node");
        panel.add(deleteButton);

       
        JButton saveButton = new JButton("Save Tree");
        panel.add(saveButton);
        
        JButton convertAndSaveButton = new JButton("Convert and Save to File");
        panel.add(convertAndSaveButton);

       
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveTree();
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

        convertAndSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new path_2(root).setVisible(true);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNode();
            }
        });

        add(panel);
    }

    private void saveTree() throws IOException {
        JFrame frame = new JFrame("Tree Visualizer");
        TreePanel treePanel = new TreePanel(root, frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(treePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        treePanel.setOpaque(true); // Content panes must be opaque
        frame.setContentPane(treePanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        frame.setResizable(true);
        // Display the window
        frame.pack();
        frame.setVisible(true);
    }

    private void addNode() {
        String name = nameField.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Node name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BinaryNode node = new BinaryNode(name);
        nodes.add(node);

        if (root == null) {
            root = node;
        } else {
            BinaryNode parent = (BinaryNode) parentComboBox.getSelectedItem();
            if (parent != null) {
                if (leftButton.isSelected()) {
                    if (parent.left == null) {
                        parent.left = node;
                    } else {
                        JOptionPane.showMessageDialog(this, "Left position of parent node is already occupied.", "Error", JOptionPane.ERROR_MESSAGE);
                        nodes.remove(node);
                        return;
                    }
                } else if (rightButton.isSelected()) {
                    if (parent.right == null) {
                        parent.right = node;
                    } else {
                        JOptionPane.showMessageDialog(this, "Right position of parent node is already occupied.", "Error", JOptionPane.ERROR_MESSAGE);
                        nodes.remove(node);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a position (left or right).", "Error", JOptionPane.ERROR_MESSAGE);
                    nodes.remove(node);
                    return;
                }
            }
        }

        updateParentComboBox();
        clearFields();
    }

    private void deleteNode() {
        BinaryNode node = (BinaryNode) parentComboBox.getSelectedItem();
        if (node == null) {
            JOptionPane.showMessageDialog(this, "Please select a node to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (node == root) {
            root = null;
        } else {
            for (BinaryNode parent : nodes) {
                if (parent.left == node) {
                    parent.left = null;
                    break;
                } else if (parent.right == node) {
                    parent.right = null;
                    break;
                }
            }
        }
        nodes.remove(node);
        updateParentComboBox();
    }

    private void updateParentComboBox() {
        parentComboBoxModel.removeAllElements();
        for (BinaryNode node : nodes) {
            if (node.left == null || node.right == null) {
                parentComboBoxModel.addElement(node);
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        directionGroup.clearSelection();
    }

  
}
