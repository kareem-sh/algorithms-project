public class Node {
    String data;
    int width;
    int height;
    Node left;
    Node right;

    Node(String data,int width,int height) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.width=width;
        this.height=height;
    }
}