/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.message;

/**
 *
 * @author Kale
 */
public class messageBox extends javax.swing.JFrame {

    int x,y;
    
    private int pass;
    
    public messageBox(String txt){
        
        getDefaultToolkit().beep();
        setUndecorated(true);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0, 255, 0, 0));
        setLocationRelativeTo(null);
        
        initComponents();
        
        txtpass.setText(txt);
        
        btnok.setOpaque(false);
        btnok.setContentAreaFilled(false);
        btnok.setBorderPainted(false);
        
        
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnok = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ok = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtpass = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);

        btnok.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnok.setForeground(new java.awt.Color(255, 255, 255));
        btnok.setText("OK");
        btnok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnokMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnokMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnokMouseExited(evt);
            }
        });
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });
        btnok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnokKeyPressed(evt);
            }
        });
        getContentPane().add(btnok);
        btnok.setBounds(130, 100, 50, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/error.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 50, 30, 30);

        ok.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ok.setForeground(new java.awt.Color(255, 255, 255));
        ok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okMouseClicked(evt);
            }
        });
        getContentPane().add(ok);
        ok.setBounds(130, 100, 50, 30);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btnok.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(130, 100, 50, 30);

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        close.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                closeKeyPressed(evt);
            }
        });
        getContentPane().add(close);
        close.setBounds(270, 5, 20, 20);

        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 300, 30);

        txtpass.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txtpass);
        txtpass.setBounds(40, 50, 240, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/jpanel.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 300, 150);

        setBounds(0, 0, 316, 189);
    }// </editor-fold>//GEN-END:initComponents

    private void closeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_closeKeyPressed
        setVisible(false);
        
        setPass(1);
    }//GEN-LAST:event_closeKeyPressed

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x-this.x, y-this.y);

    }//GEN-LAST:event_jLabel2MouseDragged

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel2MousePressed

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        setVisible(false);
        dispose();
        
        setPass(1);
    }//GEN-LAST:event_closeMouseClicked

    private void okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseClicked
        setVisible(false);
        dispose();
        
        setPass(1);
    }//GEN-LAST:event_okMouseClicked

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        message.setPass(1);
        setVisible(false);
        dispose();
        
        setPass(1);
    }//GEN-LAST:event_btnokActionPerformed

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/closec.png")));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
    }//GEN-LAST:event_closeMouseExited

    private void btnokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnokMouseEntered
        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btnokc.png")));
    }//GEN-LAST:event_btnokMouseEntered

    private void btnokMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnokMouseExited
        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btnok.png")));
    }//GEN-LAST:event_btnokMouseExited

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void btnokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnokKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            setVisible(false);
            dispose();

            setPass(1);
        }
        
    }//GEN-LAST:event_btnokKeyPressed

    private void btnokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnokMouseClicked
        message.setPass(1);
    }//GEN-LAST:event_btnokMouseClicked

    
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
            java.util.logging.Logger.getLogger(messageBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(messageBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(messageBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(messageBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                    new messageBox("Option Window").setVisible(true);
                    
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnok;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel ok;
    private javax.swing.JLabel txtpass;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the pass
     */
    public int getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(int pass) {
        this.pass = pass;
    }

}
