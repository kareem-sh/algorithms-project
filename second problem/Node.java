import java.util.ArrayList;
import java.util.List;



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
          
      /*    BFS b= new BFS();
        // BinaryNode binaryRoot = BFS.convertToBinary(b.Import("tree.txt"));
   
        JFrame frame = new JFrame("Tree Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
       // TreePanel treePanel = new TreePanel(binaryRoot,frame);

        NaryTreeGUI treePanel = new NaryTreeGUI(b.Import("tree.txt"),frame);
       // binode=BFS.convertToBinary(b.Import("tree.txt"));
       // b.export(BFS.convertToNary(binode));
         frame.add(treePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        treePanel.setOpaque(true); // Content panes must be opaque
        frame.setContentPane(treePanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        frame.setResizable(true);
        // Display the window
        frame.pack();
        frame.setVisible(true);*/
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(welcomewindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(welcomewindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(welcomewindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(welcomewindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new welcomewindow().setVisible(true);
                
                      
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables
    
    
    }
    