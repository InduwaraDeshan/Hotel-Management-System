/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author DELL
 */
public class Other_Areas_Manage extends javax.swing.JPanel {

    /**
     * Creates new form UserRegistration
     */
    public void loadHall() {
        try {
            ResultSet rs = MySQL.search("SELECT `reseption_hall`.`id`,`hall_status`.`name`AS `sname`,`reseption_hall`.`price`,`hall_type`.`name`AS `tname`,`hall_location`.`name`AS `lname` FROM `reseption_hall`\n"
                    + " INNER JOIN `hall_location` ON `hall_location`.`id` = `reseption_hall`.`hall_location_id` \n"
                    + " INNER JOIN `hall_status` ON `hall_status`.`id`=`reseption_hall`.`hall_status_id` INNER JOIN `hall_type` ON `hall_type`.`id`=`reseption_hall`.`hall_type_id` WHERE `reseption_hall`.`id` !='0'");
            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            dtm.setRowCount(0);
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("sname"));
                v.add(rs.getString("tname"));
                v.add(rs.getString("lname"));
                v.add(rs.getString("price"));
                
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadPool() {
        try {
            ResultSet rs = MySQL.search("SELECT `pool`.`id` ,`pool_status`.`name`,`pool`.`Price` FROM  `pool` INNER JOIN `pool_status` ON `pool_status`.`id`=`pool`.`pool_status_id` WHERE `pool_status_id`='1' OR `pool_status_id`='2' AND `pool`.`id` !='0' ");
            DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
            dtm.setRowCount(0);
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("Price"));
                
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadBar() {
        try {
            ResultSet rs = MySQL.search("SELECT `product`.`name` ,`bottel_brand`.`Price`  FROM `stock` INNER JOIN `product` ON `product`.`id`=`stock`.`product_id` INNER JOIN `brand` ON `brand`.`id`=`product`.`brand_id`INNER JOIN `category` ON `category`.`id`=`product`.`category_id`INNER JOIN `bottel_brand`ON `bottel_brand`.`product_id`=`product`.`id` ");
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("name"));
                v.add(rs.getString("price"));
                
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadHallStatus() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `hall_status` ORDER BY `name` ASC ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }
            
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(dcm);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadBottelBrand() {
        try {
            ResultSet rs = MySQL.search(" SELECT `name` FROM `product` WHERE `category_id`IN (SELECT `id` FROM `category` WHERE `name`='Hard Drinks')");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("product.name"));
            }
            
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox4.setModel(dcm);
            this.resize(1600, 970);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setHallStatusButtonListner() {
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = jTable4.getSelectedRow();
                
                if (selectedRow != -1) {
                    
                    String id = jTable4.getValueAt(selectedRow, 0).toString();
                    
                    if (id.equals("1")) {
                        jButton5.setEnabled(false);
                        jButton4.setEnabled(false);
                    } else {
                        jButton5.setEnabled(true);
                        jButton4.setEnabled(true);
                    }
                    
                }
            }
        };
        
        jTable4.getSelectionModel().addListSelectionListener(lsl);
        
    }
    
    public Other_Areas_Manage() {
        initComponents();
        loadHallStatus();
        loadBottelBrand();
        loadHall();
        loadPool();
        loadBar();
        
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
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jTextField9 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255,80));
        setMinimumSize(new java.awt.Dimension(1590, 1060));
        setPreferredSize(new java.awt.Dimension(1590, 1060));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255,80));
        jPanel2.setPreferredSize(new java.awt.Dimension(1590, 1060));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0,0,0,100));
        jPanel3.setPreferredSize(new java.awt.Dimension(1600, 970));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0,100));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel1.setPreferredSize(new java.awt.Dimension(530, 138));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Status");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel15.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Reception Hall Manage");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 370, -1));

        jTable4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Hall No", "Status", "Type", "Location", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 460, 390));

        jTextField9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 310, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Hall Location");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Hall Type");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jButton4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/bin.png"))); // NOI18N
        jButton4.setText("Delete Hall");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 430, 30));

        jComboBox2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 310, -1));

        jButton3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/change.png"))); // NOI18N
        jButton3.setText("Change Status");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 430, 30));

        jTextField10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 310, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Price");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 70, -1));

        jTextField11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 310, -1));

        jButton9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/refresh-page-option.png"))); // NOI18N
        jButton9.setText("Update Price");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 430, 30));

        jButton5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton5.setText(" Add Hall");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 430, 30));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 500, 990));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0,100));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Bar Manage");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 220, -1));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 470, 390));

        jComboBox4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jPanel4.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 310, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Price Per Bottle");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 150, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Select Brand");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jTextField8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel4.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 310, -1));

        jButton7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton7.setText("Add Price Per Bottle");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 470, 30));

        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/refresh-page-option.png"))); // NOI18N
        jButton2.setText("Update Price Per Bottle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 470, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 510, 990));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0,100));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pool Manage");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 250, -1));

        jTable5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Status", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 470, 390));

        jButton6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/change.png"))); // NOI18N
        jButton6.setText("Change Status");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 450, 30));

        jButton8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/refresh-page-option.png"))); // NOI18N
        jButton8.setText("Update Price ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 450, 30));

        jTextField12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 330, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Price");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 70, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 510, 990));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bc.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1460, 1060));
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 1060));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 1060));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 1060));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String price = jTextField8.getText();
        if (price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select Row ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([1-9][0-9]*)|([0][.]([0]+[1-9]+)|([0-9]+))|([1-9][0-9]*[.][0-9]+)").matcher(price).matches()) {
            
            JOptionPane.showMessageDialog(this, "Invalid Price Per Bottle price ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            
            MySQL.iud(" UPDATE `bottel_brand` SET `Price` = " + price + " WHERE `product_id` = " + Integer.parseInt(ubrand_id) + " ");
            jTextField8.setText("");
            this.resize(1600, 970);
            loadBar();
            JOptionPane.showMessageDialog(this, "Price Per Bottle price Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable4.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Hool", "Warning", JOptionPane.WARNING_MESSAGE);
            
        } else {
            String id = jTable4.getValueAt(selectedRow, 0).toString();
            String CurrentStatus = jTable4.getValueAt(selectedRow, 1).toString();
            
            int status;
            
            if (CurrentStatus.equals("Available")) {
                status = 2;
                
            } else {
                status = 1;
            }
            MySQL.iud(" UPDATE `reseption_hall` SET `hall_status_id` = " + status + " WHERE `id` = " + Integer.parseInt(id) + " ");
            loadHall();
            JOptionPane.showMessageDialog(this, "Hall Status Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton3ActionPerformed
    String hno;
    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            int r = jTable4.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Row", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else {
                hno = jTable4.getValueAt(r, 0).toString();
                String price = jTable4.getValueAt(r, 4).toString();
                
                jTextField11.setText(price);
                
                DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
                dtm.removeRow(r);
                
                this.resize(1600, 970);
                
            }
        }
    }//GEN-LAST:event_jTable4MouseClicked
    String ubrand_id;
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int r = jTable2.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Row", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else {
                String price_per_bottle_update = jTable2.getValueAt(r, 1).toString();
                String product = jTable2.getValueAt(r, 0).toString();
                
                try {
                    ResultSet rs = MySQL.search("SELECT *  FROM `product` WHERE `product`.`name`='" + product + "'");
                    
                    rs.next();
                    ubrand_id = rs.getString("product.id");
                    
                    jTextField8.setText(price_per_bottle_update);
                    this.resize(1600, 970);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked
    String pno;
    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int r = jTable5.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Row", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else {
                pno = jTable5.getValueAt(r, 0).toString();
                String price = jTable5.getValueAt(r, 2).toString();
                
                jTextField12.setText(price);
                
                DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
                dtm.removeRow(r);
                
                this.resize(1600, 970);
                
            }
        }
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int r = jTable4.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Room", "Warning", JOptionPane.WARNING_MESSAGE);
            
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Do you want Remove this ", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                
                String no = jTable4.getValueAt(r, 0).toString();
                
                DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
                dtm.removeRow(r);
                
                MySQL.iud("DELETE FROM `reseption_hall` WHERE `id`='" + no + "'");
                loadHall();
                JOptionPane.showMessageDialog(this, "Removed This", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String type = jTextField10.getText();
        String location = jTextField9.getText();
        String status = jComboBox2.getSelectedItem().toString();
        String prices = jTextField11.getText();
        
        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Location", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Hall Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([1-9][0-9]*)|([0][.]([0]+[1-9]+)|([0-9]+))|([1-9][0-9]*[.][0-9]+)").matcher(prices).matches()) {
            
            JOptionPane.showMessageDialog(this, "Invalid Price  ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            MySQL.iud("INSERT INTO `hall_type`(`name`) VALUES ('" + type + "')");
            MySQL.iud("INSERT INTO `hall_location`(`name`) VALUES ('" + location + "')");
            
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `hall_status` WHERE `name`='" + status + "' ");
                ResultSet rs1 = MySQL.search("SELECT * FROM `hall_type` WHERE `name`='" + type + "' ");
                ResultSet rs2 = MySQL.search("SELECT * FROM `hall_location` WHERE `name`='" + location + "' ");
                
                rs.next();
                rs1.next();
                rs2.next();
                String status_id = rs.getString("id");
                String type_id = rs1.getString("id");
                String location_id = rs2.getString("id");
                
                MySQL.iud("INSERT INTO `reseption_hall`(`hall_status_id`,`hall_type_id`,`hall_location_id`,`price`)VALUES('" + status_id + "','" + type_id + "','" + location_id + "','" + prices + "')");
                jTextField10.setText("");
                jTextField9.setText("");
                jTextField11.setText("");
                jComboBox2.setSelectedIndex(0);
                loadHall();
                JOptionPane.showMessageDialog(this, "Added New Hall", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable5.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row", "Warning", JOptionPane.WARNING_MESSAGE);
            
        } else {
            String id = jTable5.getValueAt(selectedRow, 0).toString();
            String CurrentStatus = jTable5.getValueAt(selectedRow, 1).toString();
            
            int status;
            
            if (CurrentStatus.equals("Available")) {
                status = 2;
                
            } else {
                status = 1;
            }
            MySQL.iud(" UPDATE `pool` SET `pool_status_id` = " + status + " WHERE `id` = " + Integer.parseInt(id) + " ");
            loadPool();
            JOptionPane.showMessageDialog(this, "Pool Status Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String product = jComboBox4.getSelectedItem().toString();
        String price = jTextField8.getText();
        
        if (product.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Brand", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([1-9][0-9]*)|([0][.]([0]+[1-9]+)|([0-9]+))|([1-9][0-9]*[.][0-9]+)").matcher(price).matches()) {
            
            JOptionPane.showMessageDialog(this, "Invalid Price Per Bottle price ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `product` WHERE `product`.`name`='" + product + "'");
                
                rs.next();
                String product_id = rs.getString("id");
                
                MySQL.iud("INSERT INTO `bottel_brand`(`product_id`,`Price`) VALUES ('" + product_id + "','" + price + "') ");
                jComboBox4.setSelectedIndex(0);
                jTextField8.setText("");
                loadBar();
                JOptionPane.showMessageDialog(this, "Added Price Per Bottle", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        // TODO add your handling code here:
        String price = jTextField8.getText();
        String text = price + evt.getKeyChar();
        
        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
            
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        // TODO add your handling code here:
        String price = jTextField11.getText();
        String text = price + evt.getKeyChar();
        
        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
            
        }
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String price = jTextField12.getText();
        if (!Pattern.compile("([1-9][0-9]*)|([0][.]([0]+[1-9]+)|([0-9]+))|([1-9][0-9]*[.][0-9]+)").matcher(price).matches()) {
            
            JOptionPane.showMessageDialog(this, "Invalid  price ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            
            MySQL.iud(" UPDATE `pool` SET `Price` = " + price + " WHERE `id` = " + Integer.parseInt(pno) + " ");
            jTextField12.setText("");
            loadPool();
            JOptionPane.showMessageDialog(this, "Update Price", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:

        String price = jTextField11.getText();
        if (!Pattern.compile("([1-9][0-9]*)|([0][.]([0]+[1-9]+)|([0-9]+))|([1-9][0-9]*[.][0-9]+)").matcher(price).matches()) {
            
            JOptionPane.showMessageDialog(this, "Invalid  price ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            
            MySQL.iud(" UPDATE `reseption_hall` SET `price` = " + price + " WHERE `id` = " + Integer.parseInt(hno) + " ");
            jTextField11.setText("");
            loadHall();
            JOptionPane.showMessageDialog(this, "Update Price", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
        // TODO add your handling code here:
        String price = jTextField12.getText();
        String text = price + evt.getKeyChar();
        
        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
            
        }
    }//GEN-LAST:event_jTextField12KeyTyped

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
