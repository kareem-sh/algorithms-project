/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 import javax.swing.JFrame;


 /**
  *
  * @author Fouad
  */
 public class path extends javax.swing.JFrame {
     /**
      * Creates new form path
      */
     public path() {
         initComponents();
         this.setSize(773, 400);
     }
 
     /**
      * This method is called from within the constructor to initialize the form.
      * WARNING: Do NOT modify this code. The content of this method is always
      * regenerated by the Form Editor.
      */

     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {
 
         jLabel1 = new javax.swing.JLabel();
         jTextField1 = new javax.swing.JTextField();
         jButton1 = new javax.swing.JButton();
         jButton2 = new javax.swing.JButton();
         jLabel2 = new javax.swing.JLabel();
 
         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         getContentPane().setLayout(null);
 
         jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
         jLabel1.setForeground(new java.awt.Color(255, 255, 255));
         jLabel1.setText("Please enter the path :");
         getContentPane().add(jLabel1);
         jLabel1.setBounds(50, 100, 289, 26);
 
         jTextField1.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jTextField1ActionPerformed(evt);
             }
         });
         getContentPane().add(jTextField1);
         jTextField1.setBounds(50, 140, 266, 22);
 
         jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
         jButton1.setText("BACK");
         jButton1.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButton1ActionPerformed(evt);
             }
         });
         getContentPane().add(jButton1);
         jButton1.setBounds(0, 330, 96, 25);
 
         jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
         jButton2.setText("NEXT");
         jButton2.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButton2ActionPerformed(evt);
             }
         });
         getContentPane().add(jButton2);
         jButton2.setBounds(280, 330, 71, 25);
 
         jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/what-are-data-structures-and-algorithms.png"))); // NOI18N
         getContentPane().add(jLabel2);
         jLabel2.setBounds(0, 0, 770, 360);
 
         pack();
         setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents
 
     private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
         // No need to handle this event for your current requirement
     }//GEN-LAST:event_jTextField1ActionPerformed
 
     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
         this.setVisible(false);
         new afterwelcome().setVisible(true);
     }//GEN-LAST:event_jButton1ActionPerformed
 
     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         // Retrieve text from the text field
         String s = jTextField1.getText();
         BFS b = new BFS();
         JFrame frame = new JFrame("Tree Visualizer");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         NaryTreeGUI treePanel = new NaryTreeGUI(b.Import(s), frame);
        frame.add(treePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        treePanel.setOpaque(true); // Content panes must be opaque
        frame.setContentPane(treePanel);
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
         frame.setResizable(true);
         // Display the window
         frame.pack();
         frame.setVisible(true);


      
       
       
     }//GEN-LAST:event_jButton2ActionPerformed
 
     /**
      * @param args the command line arguments
      */
     public static void main(String args[]) {
         /* Set the Nimbus look and feel */
         //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
         /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
          * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
          */
         try {
             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                 if ("Nimbus".equals(info.getName())) {
                     javax.swing.UIManager.setLookAndFeel(info.getClassName());
                     break;
                 }
             }
         } catch (ClassNotFoundException ex) {
             java.util.logging.Logger.getLogger(path.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
             java.util.logging.Logger.getLogger(path.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             java.util.logging.Logger.getLogger(path.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
             java.util.logging.Logger.getLogger(path.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }
         //</editor-fold>
 
         /* Create and display the form */
         java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
                 new path().setVisible(true);
             }
         });
     }
 
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JTextField jTextField1;
     // End of variables declaration//GEN-END:variables
 }
 