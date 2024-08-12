/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Supplier;
import model.MySQL;

/**
 *
 * @author DELL
 */
public class HomePage extends javax.swing.JPanel {

    /**
     * Creates new form UserRegistration
     */
    public HomePage() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255,80));
        setPreferredSize(new java.awt.Dimension(1590, 1060));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255,80));
        jPanel2.setPreferredSize(new java.awt.Dimension(1590, 1060));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0,100));
        jPanel3.setPreferredSize(new java.awt.Dimension(1319, 1020));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204,100));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/file-backup.png"))); // NOI18N
        jButton2.setText("Backup Now");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 350, 70));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jLabel3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel3PropertyChange(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, 540, 40));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel1PropertyChange(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 540, 40));

        jButton3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/calculator.png"))); // NOI18N
        jButton3.setText("Dailt Budget");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 350, 70));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1570, 110));

        jPanel4.setBackground(new java.awt.Color(0, 204, 204,100));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel4.setLayout(new java.awt.CardLayout());
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 790, 920));

        jPanel5.setBackground(new java.awt.Color(0, 204, 204,100));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel5.setLayout(new java.awt.CardLayout());
        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 770, 920));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 1060));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bc.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1460, 1060));
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        if (jLabel3.getText().equals("Expire Products Are Available ")) {
            View_Message1 vm1 = new View_Message1();
            jPanel5.removeAll();
            jPanel5.add(vm1);
            jPanel5.revalidate();
            jPanel5.repaint();
            resize(770, 920);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel3PropertyChange
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dNow = sdf.format(new Date());

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `stock` WHERE `exd` <= ('" + dNow + "'  + INTERVAL 30 DAY)");

            if (rs.next()) {
                jLabel3.setText("Expire Products Are Available ");
                jLabel3.setForeground(Color.red);
            } else {
                jLabel3.setText("Expire Products Are Not Available ");
                jLabel3.setForeground(Color.green);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jLabel3PropertyChange

    private void jLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel1PropertyChange
        // TODO add your handling code here:
        // TODO add your handling code here:

        try {

            ResultSet rs = MySQL.search("SELECT * FROM `stock` WHERE `unit_of_mass_id`='1' AND `qty`<='5' OR  `unit_of_mass_id`='2' AND `qty`<='5' OR  `unit_of_mass_id`='3' AND `qty`<='10' ");

            if (rs.next()) {
                jLabel1.setText("Low Quantity Products Are Available ");
                jLabel1.setForeground(Color.red);
            } else {
                jLabel1.setText("Low Quantity Products Are  Not Available ");
                jLabel1.setForeground(Color.green);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel1PropertyChange

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        if (jLabel1.getText().equals("Low Quantity Products Are Available ")) {
            View_Message vm = new View_Message();
            jPanel5.removeAll();
            jPanel5.add(vm);
            jPanel5.revalidate();
            jPanel5.repaint();
            resize(770, 920);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Backup b = new Backup();
        jPanel4.removeAll();
        jPanel4.add(b);
        jPanel4.revalidate();
        jPanel4.repaint();
        resize(770, 920);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Budget b = new Budget();
        jPanel4.removeAll();
        jPanel4.add(b);
        jPanel4.revalidate();
        jPanel4.repaint();
        resize(770, 920);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
