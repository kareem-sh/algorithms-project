import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StringSplitter {
    
  
    public static Node splitString(String input) {
        input=input.replaceAll("\\s", "");
        return splitStringRecursive(input);
    }

    private static Node splitStringRecursive(String input) {
        int index = findSplitIndex(input);
        if (index == -1) {
            String[] parts = input.split("\\[|,|\\]");
            String data = parts[0];
            int width = Integer.parseInt(parts[1]);
            int height = Integer.parseInt(parts[2]);
            return new Node(data, width, height);
        }
    
        Node node;
    
        char operator = input.charAt(index);
        if (operator == '|' || operator == '-') {
            node = new Node(Character.toString(operator), 0, 0);
        } else {
            String[] parts = input.substring(index, index + 3).split("\\[|,|\\]");
            String data = parts[0].trim();
            int width = Integer.parseInt(parts[1]);
            int height = Integer.parseInt(parts[2]);
            node = new Node(data, width, height);
        }
        if (input.charAt(0) == '(' && input.charAt(index - 1) == ')') {
            node.left = splitStringRecursive(input.substring(1, index - 1));
        } else {
            node.left = splitStringRecursive(input.substring(0, index));
        }
    
        if (input.charAt(index + 1) == '(' && input.charAt(input.length() - 1) == ')') {
            node.right = splitStringRecursive(input.substring(index + 2, input.length() - 1));
        }  else {
            node.right = splitStringRecursive(input.substring(index + 1));
        }
    
        return node;
    }
    

    private static int findSplitIndex(String input) {
        int index = -1;
        int level = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '(') {
                level++;
            } else if (currentChar == ')') {
                level--;
            } else if ((currentChar == '|' || currentChar == '-') && level == 0) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    private static String printInOrder(Node root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            if (root.left != null || root.right != null) {
                sb.append("(");
            }
            sb.append(printInOrder(root.left));
            sb.append(root.data);
            if (root.width != 0 && root.height != 0) {
                sb.append("[").append(root.width).append(",").append(root.height).append("]");
            }
            sb.append(printInOrder(root.right));
            if (root.left != null || root.right != null) {
                sb.append(")");
            }
        }
        return sb.toString();
    
    }

     public static void export(Node r)
     {
      String ans = printInOrder(r);
      System.out.println(ans.substring(1,ans.length()-1));
     }
  
       /* 
     public static void drawTree(Node root, String filePath) throws IOException {
        if (root == null) {
            return;
        }
        int totalWidth = calculateTotalWidth(root);
        int totalHeight = calculateTotalHeight(root);
        char[][] canvas = new char[totalHeight][totalWidth];
        for (char[] row : canvas) {
            Arrays.fill(row, ' ');
        }
        drawNode(root, canvas, 0, 0, totalWidth, totalHeight);
        writeToFile(canvas, filePath);
    }

    private static int calculateTotalWidth(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.data.equals("|")) {
            return calculateTotalWidth(node.left) + calculateTotalWidth(node.right);
        } else if (node.data.equals("-")) {
            return Math.max(calculateTotalWidth(node.left), calculateTotalWidth(node.right));
        } else {
            return node.width;
        }
    }

    private static int calculateTotalHeight(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.data.equals("-")) {
            return calculateTotalHeight(node.left) + calculateTotalHeight(node.right);
        } else if (node.data.equals("|")) {
            return Math.max(calculateTotalHeight(node.left), calculateTotalHeight(node.right));
        } else {
            return node.height;
        }
    }

    private static void drawNode(Node node, char[][] canvas, int x, int y, int totalWidth, int totalHeight) {
        if (node == null) {
            return;
        }
        if (node.data.equals("|")) {
            int leftWidth = calculateTotalWidth(node.left);
            drawNode(node.left, canvas, x, y, leftWidth, totalHeight);
            drawNode(node.right, canvas, x + leftWidth, y, totalWidth - leftWidth, totalHeight);
        } else if (node.data.equals("-")) {
            int leftHeight = calculateTotalHeight(node.left);
            drawNode(node.left, canvas, x, y, totalWidth, leftHeight);
            drawNode(node.right, canvas, x, y + leftHeight, totalWidth, totalHeight - leftHeight);
        } else {
            drawRectangle(canvas, x, y, node.width, node.height, node.data);
        }
    }

    private static void drawRectangle(char[][] canvas, int x, int y, int width, int height, String label) {
        for (int i = y; i < y + height; i++) {
            for (int j = x; j < x + width; j++) {
                if (i == y || i == y + height - 1) {
                    canvas[i][j] = '-';
                } else if (j == x || j == x + width - 1) {
                    canvas[i][j] = '|';
                }
            }
        }
        int labelX = x + (width - label.length()) / 2;
        int labelY = y + height / 2;
        for (int i = 0; i < label.length(); i++) {
            canvas[labelY][labelX + i] = label.charAt(i);
        }
    }

    private static void writeToFile(char[][] canvas, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (char[] row : canvas) {
                writer.write(new String(row));
                writer.newLine();
            }
        }
    }*/
    
    public static void drawTree(Node root, String filePath) throws IOException {
        if (root == null) {
            return;
        }
        int totalWidth = calculateTotalWidth(root);
        int totalHeight = calculateTotalHeight(root);
        char[][] canvas = new char[totalHeight][totalWidth];
        for (char[] row : canvas) {
            Arrays.fill(row, ' ');
        }
        drawNode(root, canvas, 0, 0, totalWidth, totalHeight);
        writeToFile(canvas, filePath);
    }

    private static int calculateTotalWidth(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.data.equals("|")) {
            return calculateTotalWidth(node.left) + calculateTotalWidth(node.right);
        } else if (node.data.equals("-")) {
            return Math.max(calculateTotalWidth(node.left), calculateTotalWidth(node.right));
        } else {
            return node.width;
        }
    }

    private static int calculateTotalHeight(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.data.equals("-")) {
            return calculateTotalHeight(node.left) + calculateTotalHeight(node.right);
        } else if (node.data.equals("|")) {
            return Math.max(calculateTotalHeight(node.left), calculateTotalHeight(node.right));
        } else {
            return node.height;
        }
    }

    private static void drawNode(Node node, char[][] canvas, int x, int y, int totalWidth, int totalHeight) {
        if (node == null) {
            return;
        }
        if (node.data.equals("|")) {
            int leftWidth = calculateTotalWidth(node.left);
            drawNode(node.left, canvas, x, y, leftWidth, totalHeight);
            drawNode(node.right, canvas, x + leftWidth, y, totalWidth - leftWidth, totalHeight);
        } else if (node.data.equals("-")) {
            int leftHeight = calculateTotalHeight(node.left);
            drawNode(node.left, canvas, x, y, totalWidth, leftHeight);
            drawNode(node.right, canvas, x, y + leftHeight, totalWidth, totalHeight - leftHeight);
        } else {
            drawRectangle(canvas, x, y, node.width, node.height, node.data);
        }
    }

    private static void drawRectangle(char[][] canvas, int x, int y, int width, int height, String label) {
        
        for (int i = y; i < y + height; i++) {
            for (int j = x; j < x + width; j++) {
                if ((j==x  && i!=y+height-1) )
                    canvas[i][0]='|';
                if (i==y)
                    canvas[0][j]='-';


              if ( i == y + height - 1) {
                    canvas[i][j] = '-';

                }

              else if ( j == x + width - 1) {
                    canvas[i][j] = '|';
                }
            }
        }
        if(canvas[0][0]=='|')
        canvas[0][0]='-';
        int labelX = x + (width - label.length()) / 2;
        int labelY = y + height / 2;
        for (int i = 0; i < label.length(); i++) {
            canvas[labelY][labelX + i] = label.charAt(i);
        }
    }

    private static void writeToFile(char[][] canvas, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (char[] row : canvas) {
                writer.write(new String(row));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Node SwapTree(Node n){
        Node no=n;
      
      

        return Swap(no);
  }
     
    private static Node Swap(Node New){ 
        int temp;
        if(New!=null){
          if(New.data.equals("-"))
          New.data="|";
        else if(New.data.equals("|"))
        New.data="-";
        else{
         temp=New.height;
         New.height=New.width;
         New.width=temp;
        }
         New.left=Swap(New.left);
         New.right=Swap(New.right);
    }
         return New;
    }

public static void SwapDraw(Node n , String filename) throws IOException{
    StringSplitter.drawTree(SwapTree(n), filename);
}

   public ArrayList<Node> TreeTNodes(Node root){
    ArrayList<Node>arrayList=new ArrayList<>();
    return TreeTNodesProcess(root,arrayList);
   }
   private ArrayList<Node> TreeTNodesProcess(Node root,ArrayList<Node>list){
    if(root!=null){
        if(!root.data.equals("-") && !root.data.equals("|"))
            {
                Node temp=root;
                list.add(temp);
            }
        list=TreeTNodesProcess(root.left,list);
        list=TreeTNodesProcess(root.right,list);
      }
       return list;
   }

}
