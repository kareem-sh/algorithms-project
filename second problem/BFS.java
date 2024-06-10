import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    
    public void export(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            
            if (!currentNode.children.isEmpty()) {
                System.out.print(currentNode.data + "->");

                List<String> childValues = new ArrayList<>();
                for (Node child : currentNode.children) {
                    childValues.add(child.data);
                    queue.add(child);
                }

                System.out.println(String.join(",", childValues));
            } else {
                for (Node child : currentNode.children) {
                    queue.add(child);
                }
            }
        }
    }

    public void ExportToFile(Node root) {
       try (BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("test.txt"))) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            
            if (!currentNode.children.isEmpty()) {
                bufferedWriter.write(currentNode.data + "->");

                List<String> childValues = new ArrayList<>();
                for (Node child : currentNode.children) {
                    childValues.add(child.data);
                    queue.add(child);
                }
                bufferedWriter.write(String.join(",", childValues)+"\n");
            } else {
                for (Node child : currentNode.children) {
                    queue.add(child);
                }
            }
        }
       } catch (Exception e) {
        
       }
       
    }


    public Node Import(String filename){
     
        Map<String, Node> nodes = new HashMap<>();
        Node root = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line=line.replaceAll("\\s", "");
                String[] parts = line.split("->");
                String parentValue = parts[0];
                Node parentNode = nodes.computeIfAbsent(parentValue, Node::new);
                if (root == null) {
                    root = parentNode;
                }
                if (parts.length > 1) {
                    String[] children = parts[1].split(",");
                    for (String childValue : children) {
                        Node childNode = nodes.computeIfAbsent(childValue, Node::new);
                        parentNode.addChild(childNode);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    } 
    
    public static BinaryNode convertToBinary(Node root) {
        if (root == null) {
            return null;
        }

        BinaryNode binaryNode = new BinaryNode(root.data);
        convert(root, binaryNode);
        return binaryNode;
    }

    private static void convert(Node geniricNode, BinaryNode binaryNode) {
        if (geniricNode.children != null && !geniricNode.children.isEmpty()) {
            binaryNode.right = new BinaryNode(geniricNode.children.get(geniricNode.children.size()-1).data);
            convert(geniricNode.children.get(geniricNode.children.size()-1), binaryNode.right);
            
            BinaryNode current = binaryNode.right;
            for (int i =geniricNode.children.size()-2 ; i>=0; i--) {
                current.left = new BinaryNode(geniricNode.children.get(i).data);
                convert(geniricNode.children.get(i), current.left);
                current = current.left;
            }
        }
    }
    
    
  
    
   public static Node convertToNary(BinaryNode root) {
        if (root == null) {
            return null;
        }

        Node naryNode = new Node(root.data);
        convert(root, naryNode);
        return naryNode;
    }

    private static void convert(BinaryNode binaryNode, Node naryNode) {
        if (binaryNode.right != null) {
            BinaryNode firstChild = binaryNode.right;
            Stack<BinaryNode> stack = new Stack<>();
            stack.push(firstChild);
            convert(firstChild, naryNode.children, stack);

            BinaryNode sibling = firstChild.left;
            while (sibling != null) {
                stack.push(sibling);
                convert(sibling, naryNode.children, stack);
                sibling = sibling.left;
            }

            // Reverse order by popping from the stack
            while (!stack.isEmpty()) {
                BinaryNode child = stack.pop();
                Node childNode = new Node(child.data);
                naryNode.children.add(childNode);
                // Ensure childNode gets its children converted
                convert(child, childNode);
            }
        }
    }

    private static void convert(BinaryNode binaryNode, List<Node> children, Stack<BinaryNode> stack) {
        // This function is a placeholder to avoid the stack argument in the main convert method
    }


  }
