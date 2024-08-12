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
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author DELL
 */
public class Room_Manage extends javax.swing.JPanel {

    /**
     * Creates new form UserRegistration
     */
    int c = SignIn.userId;

    public void loadRoomType() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_type`");
            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("payment"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadRooms() {
        try {
            ResultSet rs = MySQL.search("SELECT `rooms`.`id` ,`room_status`.`name`AS `sname`,`room_clean`.`name` AS `cname`,`room_location`.`name`AS `lname` ,`room_type`.`name`AS `tname` FROM `rooms` INNER JOIN `room_status` ON `room_status`.`id`=`rooms`.`room_status` \n"
                    + "INNER JOIN `room_clean` ON `room_clean`.`id`=`rooms`.`room_clean_id` INNER JOIN `room_type` ON `room_type`.`id` =`rooms`.`room_type_id` INNER JOIN `room_location` ON \n"
                    + "`room_location`.`id`=`rooms`.`room_location_id` WHERE `rooms`.`id` !='0' ORDER BY `rooms`.`id` ASC ");
            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("sname"));
                v.add(rs.getString("cname"));
                v.add(rs.getString("tname"));
                v.add(rs.getString("lname"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadRoomLocation() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_location`");
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLocation() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_location` ORDER BY `name` ASC ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox4.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadClean() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_clean` ORDER BY `name` ASC ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadClean2() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_clean` ORDER BY `name` ASC ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox5.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadtype() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_type` ORDER BY `name` ASC ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox6.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStatus() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_status` ORDER BY `name` ASC ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(dcm);
            jComboBox3.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStatus2() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `room_status` ORDER BY `name` ASC ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);

            jComboBox3.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searckRoom() {

        try {

            String available = jComboBox3.getSelectedItem().toString();
            String clean = jComboBox5.getSelectedItem().toString();
            String location = jComboBox4.getSelectedItem().toString();
            String type = jComboBox6.getSelectedItem().toString();
            String no = jTextField8.getText();

            Vector queryVector = new Vector();

            if (available.equals("Select")) {
                loadRooms();
            } else {
                queryVector.add("`room_status`.`name` = '" + available + "'");
            }

            if (clean.equals("Select")) {
                loadRooms();
            } else {
                queryVector.add("`room_clean`.`name` = '" + clean + "'");
            }
            if (location.equals("Select")) {
                loadRooms();
            } else {
                queryVector.add("`room_location`.`name` = '" + location + "'");
            }

            if (no.isEmpty()) {
                loadRooms();
            } else {
                queryVector.add("`rooms`.`id` LIKE '%" + no + "%'");
            }

            if (type.equals("Select")) {
                loadRooms();
            } else {
                queryVector.add("`room_type`.`name` LIKE '%" + type + "%'");
            }

            String wherequery = "WHERE";

            for (int i = 0; i < queryVector.size(); i++) {
                wherequery += " ";
                wherequery += queryVector.get(i);
                wherequery += " ";
                if (i != queryVector.size() - 1) {
                    wherequery += "AND";
                }
            }

            ResultSet rs = MySQL.search("SELECT `rooms`.`id` ,`room_status`.`name`AS `sname`,`room_clean`.`name` AS `cname`,`room_location`.`name`AS `lname` ,`room_type`.`name`AS `tname` FROM `rooms` INNER JOIN `room_status` ON `room_status`.`id`=`rooms`.`room_status` \n"
                    + "INNER JOIN `room_clean` ON `room_clean`.`id`=`rooms`.`room_clean_id` INNER JOIN `room_type` ON `room_type`.`id` =`rooms`.`room_type_id` INNER JOIN `room_location` ON \n"
                    + "`room_location`.`id`=`rooms`.`room_location_id` " + wherequery + " AND `rooms`.`id` !='0' ");

            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("rooms.id"));
                v.add(rs.getString("sname"));
                v.add(rs.getString("cname"));
                v.add(rs.getString("tname"));
                v.add(rs.getString("lname"));
                ;
                dtm.addRow(v);
            }

            jTable3.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Room_Manage() {
        initComponents();
        loadRoomType();
        loadRoomLocation();
        loadClean();
        loadStatus();
        loadRooms();
        loadLocation();
        loadtype();
        loadClean2();
        loadStatus2();

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
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255,80));
        setPreferredSize(new java.awt.Dimension(1600, 970));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255,80));
        jPanel2.setPreferredSize(new java.awt.Dimension(1590, 1060));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0,0,0,100));
        jPanel3.setPreferredSize(new java.awt.Dimension(1600, 970));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 255, 255,100));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Clean / Dirty");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton1.setText("Add Room");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 350, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("None");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 60, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("None");
        jLabel12.setOpaque(true);
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 60, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Location Id");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 170, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Room Status");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox2PropertyChange(evt);
            }
        });
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 170, -1));

        jLabel15.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Add / Update Rooms ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 320, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Room Type Id");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 740, 210));

        jLabel5.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/interior-design.png"))); // NOI18N
        jLabel5.setText("Room Management ");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        jPanel4.setBackground(new java.awt.Color(51, 255, 255,100));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Add Rooms Location");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 310, -1));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Location Name"
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

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 360, 200));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Loaction");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jTextField4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPanel4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 250, -1));

        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton2.setText("Add Location");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 180, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 60, 400, 420));

        jPanel5.setBackground(new java.awt.Color(51, 255, 255,100));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Add Rooms Type");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 250, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Payment");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Room Type");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jTextField6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 250, -1));

        jButton3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton3.setText("Add Type");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 180, -1));

        jTextField7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 250, -1));

        jTable4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Typr Name", "Payment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 360, 200));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 400, 420));

        jPanel6.setBackground(new java.awt.Color(51, 255, 255,100));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Room Search");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 190, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Location");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Room No");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Available Rooms ");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Clean Room");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jTextField8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel6.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 510, -1));

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jPanel6.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 510, -1));

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jPanel6.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 510, -1));

        jComboBox5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });
        jPanel6.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 510, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Type");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jComboBox6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        jPanel6.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 510, -1));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 740, 200));

        jTable3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Room Number", "Room Status", "Clean / Dirty", "Room Type", "Room Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 1560, 450));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 970));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 970));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bc.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1460, 1060));
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 970));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String type_id = jLabel11.getText();
        String location_id = jLabel12.getText();
        String clean = jComboBox1.getSelectedItem().toString();
        String status = jComboBox2.getSelectedItem().toString();

        if (type_id.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Select Room Type", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (location_id.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Select Room location", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (clean.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Room Clean OR Dirty", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Room Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                this.resize(1600, 970);
                ResultSet rs = MySQL.search("SELECT * FROM `room_clean` WHERE `name`='" + clean + "'");
                ResultSet rs1 = MySQL.search("SELECT * FROM `room_status` WHERE `name`='" + status + "'");

                rs.next();
                rs1.next();
                String clean_id = rs.getString("id");
                String status_id = rs1.getString("id");

                if (jButton1.getText().equals("Add Room")) {
                    MySQL.iud("INSERT INTO `rooms`(`room_status`,`room_clean_id`,`room_type_id`,`room_location_id`) VALUES ('" + status_id + "','" + clean_id + "','" + type_id + "','" + location_id + "')");
                    jLabel11.setText("None");
                    jLabel12.setText("None");
                    jComboBox1.setSelectedIndex(0);
                    jComboBox2.setSelectedIndex(0);
                    loadRooms();
                    this.resize(1600, 970);
                    JOptionPane.showMessageDialog(this, "New Room Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                } else if (jButton1.getText().equals("Update Room")) {

                    MySQL.iud("UPDATE `rooms` SET `room_status`='" + status_id + "' , `room_clean_id`='" + clean_id + "',`room_type_id`='" + type_id + "',`room_location_id`='" + location_id + "'  WHERE `id`= '" + rno + "'");
                    jLabel11.setText("None");
                    jLabel12.setText("None");
                    jComboBox1.setSelectedIndex(0);
                    jComboBox2.setSelectedIndex(0);
                    loadRooms();
                    this.resize(1600, 970);
                    JOptionPane.showMessageDialog(this, "This Room Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    jButton1.setText("Add Room");
                }
                this.resize(1600, 970);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String location = jTextField4.getText();

        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Location", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet rs = MySQL.search("SELECT * FROM  `room_location`   WHERE `room_location`.`name`='" + location + "';");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "location alredy exists", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {
                    MySQL.iud("INSERT INTO `room_location`(`name`)VALUES('" + location + "')");
                    loadRoomLocation();
                    jTextField4.setText("");
                    this.resize(1600, 970);
                    JOptionPane.showMessageDialog(this, "Location Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String type = jTextField7.getText();
        String payment = jTextField6.getText();

        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("(0)|([1-9][0-9]*)|([0][.]([0]+[1-9]+)|([0-9]+))|([1-9][0-9]*[.][0-9]+)").matcher(payment).matches()) {

            JOptionPane.showMessageDialog(this, "Invalid Payment ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet rs = MySQL.search("SELECT * FROM  `room_type`   WHERE `room_type`.`name`='" + type + "';");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Type alredy exists", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {
                    MySQL.iud("INSERT INTO `room_type`(`name`,`payment`)VALUES('" + type + "','" + payment + "')");
                    loadRoomType();
                    jTextField6.setText("");
                    jTextField7.setText("");
                    this.resize(1600, 970);
                    JOptionPane.showMessageDialog(this, "Room Type Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
        String payment = jTextField6.getText();
        String text = payment + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();

        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 1) {
            int r = jTable4.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Row", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {
                String type_id = jTable4.getValueAt(r, 0).toString();

                jLabel11.setText(type_id);
                this.resize(1600, 970);

            }
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int r = jTable2.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Row", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {
                String location_id = jTable2.getValueAt(r, 0).toString();

                jLabel12.setText(location_id);
                this.resize(1600, 970);

            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        // TODO add your handling code here:
        String no = jTextField8.getText();
        String text = no + evt.getKeyChar();

        if (!Pattern.compile("[1-9][0-9]*").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
        searckRoom();
        this.resize(1600, 970);
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        searckRoom();
        this.resize(1600, 970);
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
        searckRoom();
        this.resize(1600, 970);
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        searckRoom();
        this.resize(1600, 970);
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        // TODO add your handling code here:
        searckRoom();
        this.resize(1600, 970);
    }//GEN-LAST:event_jComboBox6ItemStateChanged
    String rno;
    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int r = jTable3.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Room", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {
                int option = JOptionPane.showConfirmDialog(this, "Do you want Update this Room", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {

                    rno = jTable3.getValueAt(r, 0).toString();
                    String status = jTable3.getValueAt(r, 1).toString();
                    String clean = jTable3.getValueAt(r, 2).toString();

                    String type = jTable3.getValueAt(r, 3).toString();
                    String location = jTable3.getValueAt(r, 4).toString();

                    DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                    dtm.removeRow(r);
                    try {
                        ResultSet rs = MySQL.search("SELECT * FROM  `room_type`   WHERE `room_type`.`name`='" + type + "';");
                        ResultSet rs1 = MySQL.search("SELECT * FROM  `room_location`   WHERE `room_location`.`name`='" + location + "';");
                        rs.next();
                        String type_id = rs.getString("id");
                        String location_id = rs.getString("id");

                        jButton1.setText("Update Room");
                        jLabel11.setText(type_id);
                        jLabel12.setText(location_id);
                        jComboBox1.setSelectedItem(clean);
                        jComboBox2.setSelectedItem(status);
                        this.resize(1600, 970);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(this, "Added to text Fields .Please Update this Account.", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        }

    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        // TODO add your handling code here:
        this.resize(1600, 970);
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox2PropertyChange
        // TODO add your handling code here:
        this.resize(1600, 970);
    }//GEN-LAST:event_jComboBox2PropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
