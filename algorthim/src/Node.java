package src;

public class Node {
    public String data;
    int width;
    int height;
    public Node left;
    public Node right;

    public Node(){}

   public Node(String data,int width,int height) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.width=width;
        this.height=height;
    }
    private int index = 0;
    public Node buildTree(Rectangle[] characters) {
        if (index >= characters.length) {

            return null;
        }

        Rectangle current = characters[index++];
        Node node = new Node(current.name,current.width,current.height);

        // If the current node is '-' or '|', it is an internal node
        if (current.name.equals("-") || current.name.equals("|")) {

            node.left = buildTree(characters);  // build left subtree
            node.right = buildTree(characters); // build right subtree
        }


        return node;
    }
}