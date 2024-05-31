package src;

import java.io.*;
import java.util.*;

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
     public static void drawTree(TreeNode root, String filePath) throws IOException {
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

    private static int calculateTotalWidth(TreeNode node) {
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

    private static int calculateTotalHeight(TreeNode node) {
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

    private static void drawNode(TreeNode node, char[][] canvas, int x, int y, int totalWidth, int totalHeight) {
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
       ///ANSWER FOR QUESTION NUMBER 3
       public static void ExportToRec(Node n , String filePath) throws IOException {
           drawTree(n,filePath);
       }
       private static void drawTree(Node root, String filePath) throws IOException {
           if (root == null) {
               return;
           }
           int totalWidth = calculateTotalWidth(root);
           int totalHeight = calculateTotalHeight(root);
           char[][] canvas = new char[totalHeight+1][totalWidth+1];
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
            drawRectangle(canvas, x, y, node.width, node.height, node.data );
        }
    }

    private static void drawRectangle(char[][] canvas, int x, int y, int width, int height, String label  ) {


        for (int i = y; i < y + height+1; i++) {
            for (int j = x; j < x + width+1; j++) {


                if (i == y) {
                    canvas[i][j] = '-';
                }
                else if (j == x ) {
                    canvas[i][j] = '|';

                }
                else if((j==canvas[i].length-1))
                    canvas[i][j]='|';
                if (( i ==canvas.length-1))
                    canvas[i][j] = '-';

            }

            canvas[y +1][x + 1] = label.charAt(0);
        }
    }
    private static void writeToFile ( char[][] canvas, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (char[] row : canvas) {
                writer.write(new String(row));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    ///ANSWER FOR QUESTION NUMBER 3
    public static Node ImportToTree (String filename) {
        String[] drawing;

        try (BufferedReader reader = new BufferedReader(new FileReader( filename))) {
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                i++;

            }
            reader.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(filename));
            drawing = new String[i];
            Arrays.fill(drawing, " ");
            i = 0;
            while ((line = reader1.readLine()) != null) {

                drawing[i] = line;
                i++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        int ind = 0;
        Rectangle temp;
        List<Rectangle> rectangles = parseDrawing(drawing);


        Rectangle[] r = rectangles.toArray(new Rectangle[0]);
        for (int i = 0; i < r.length; i++) {

            if (r[i].root) {
                ind = i;
            }
        }
        for (int i = 1; i < ind; i++) {

            if (r[i].name.contains("|") || r[i].name.contains("-")) {
                temp = r[i];
                r[i] = r[i - 1];
                r[i - 1] = temp;
            }
        }
        for (int i = ind + 1; i < r.length; i++) {
            if (r[i].name.contains("|") || r[i].name.contains("-")) {

                temp = r[i];
                r[i] = r[i - 1];
                r[i - 1] = temp;
            }
        }
        temp = r[0];
        r[0] = r[ind];
        for (int i = ind; i > 1; i--) {


            r[i] = r[i - 1];

        }
        if(r.length>1)
            r[1] = temp;
        for (int i = 0; i < r.length; i++)
            System.out.println(r[i].toString());


        Node root;
        Node root1 = new Node();

        root = root1.buildTree(r);

        // Output the tree in pre-order to verify correctness

        return root;
    }


    private static List<Rectangle> parseDrawing (String[]drawing){
        List<Rectangle> rectangles = new ArrayList<>();
        Map<String, Boolean> visited = new HashMap<>();

        //boolean[][] visited = new boolean[drawing.length+1][drawing[1].length()+1];
        for (int row = 1; row < drawing.length - 1; row++) {

            for (int col = 1; col < drawing[row].length() - 1; col++)
                if (isAlpha(drawing[row].charAt(col)))
                    visited.put(String.valueOf(drawing[row].charAt(col)), false);

        }
        //for (int i = 0; i < drawing.length; i++) {
        //   Arrays.fill(visited[i], false);
        // }
        findRectangles(drawing, visited, rectangles);
        return rectangles;
    }
    private static void findRectangles (String[]drawing, Map visited, List < Rectangle > rectangles){
        boolean found = true, found2 = true, map = false;

        for (int row = 1; row < drawing.length - 1; row++) {

            for (int col = 1; col < drawing[row].length() - 1; col++) {

                if (isAlpha(drawing[row].charAt(col))) {
                    map = (boolean) visited.get(String.valueOf(drawing[row].charAt(col)));
                    if (!map) {
                        found = true;
                        String name = String.valueOf(drawing[row].charAt(col));
                        int width = findWidth(drawing, row, col);
                        int height = findHeight(drawing, row, col);
                        markVisited(visited, String.valueOf(drawing[row].charAt(col)));
                        rectangles.add(new Rectangle(name, width, height, false));
                    }
                } else if (isOr(drawing[row].charAt(col)) && found) {
                    if (isOr(drawing[drawing.length - 2].charAt(col)) && row == 1)
                        rectangles.add(new Rectangle(String.valueOf(drawing[row].charAt(col)), 0, 0, true));
                    else
                        rectangles.add(new Rectangle(String.valueOf(drawing[row].charAt(col)), 0, 0, false));
                    found = false;
                } else if (isDash(drawing[row].charAt(col)) && found2) {

                    if (drawing[row].startsWith("-") && drawing[row].endsWith("-")) {

                        rectangles.add(new Rectangle(String.valueOf(drawing[row].charAt(col)), 0, 0, true));
                    } else
                        rectangles.add(new Rectangle(String.valueOf(drawing[row].charAt(col)), 0, 0, false));
                    found2 = false;
                }
            }
            found2 = true;
            found = false;
        }
    }

    private static int findWidth (String[]drawing,int row, int col){
        int start = col;

        while (col < drawing[row].length() && drawing[row].charAt(col) != '|') {
            col++;

        }
        return col - start + 1;
    }

    private static int findHeight (String[]drawing,int row, int col){
        int start = row;
        int f = 1;

        while (row < drawing.length && drawing[row].charAt(col) != '-') {
            if (drawing[row].contains("-")) {

                f++;
            }

            row++;
        }
        System.out.println(f);
        return row - start + 1 ;
    }

    private static void markVisited (Map visited, String alpha){
        visited.replace(alpha, true);
    }

    private static boolean isAlpha ( char c){
        return Character.isLetter(c);
    }

    private static boolean isOr ( char c){
        if (c == '|')
            return true;
        return false;
    }
    private static boolean isDash ( char c){
        if (c == '-')
            return true;
        return false;
    }
///ANSWER FOR QUESTION NUMBER 4
    public static void FormRectangle() {

        List<Rectangle> rectangles = List.of(
                new Rectangle(10, 20, "A"),
                new Rectangle(10, 20, "B"),
                new Rectangle(10, 30, "C"),
                new Rectangle(50, 30, "D"),
                new Rectangle(30, 40, "E"),
                new Rectangle(20, 40, "F")
        );

        boolean result = RectangleStitcher.canFormRectangle(rectangles);
        System.out.println("Can form rectangle: " + result);

    }
///ANSWER FOR QUESTION NUMBER 5
    public static void RectangleCombinations(){
        List<Rectangle> combinations = List.of(
                new Rectangle(1, 1, "A"),
                new Rectangle(2, 1, "B"),
                new Rectangle(4, 3, "C"),
                new Rectangle(3, 1, "D")
        );


        int[] maxRectangles= new int[1];

        Rectangle.generateCombinations(combinations,new ArrayList<>(),0,maxRectangles);
        System.out.println("The most amount of rectangles that can be formed out of these small papers is : " + maxRectangles[0]);



    }
    ///ANSWER FOR QUESTION NUMBER 6
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
System.out.println(New.data);
        
        
    
     New.left=Swap(New.left);
     New.right=Swap(New.right);

    }
       
     return New;
    }

public static void SwapDraw(Node n , String filename) throws IOException{
    StringSplitter.drawTree(SwapTree(n), filename);
   


}



}
