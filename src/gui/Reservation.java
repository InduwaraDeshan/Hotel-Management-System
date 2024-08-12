/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Reservation extends javax.swing.JPanel {

    /**
     * Creates new form UserRegistration
     */
    int c = SignIn.userId;

    public void loadCustomer(String text) {
        try {
            ResultSet rs = MySQL.search("SELECT `customer`.`nic`,`customer`.`fname`,`customer`.`lname`,`customer`.`contact_no`  FROM `customer`  WHERE `customer`.`nic` LIKE '" + text + "%' ORDER BY `nic` ASC");
            if (rs.next()) {
                String fname = rs.getString("fname");
                String contact = rs.getString("contact_no");
                String nic = rs.getString("nic");
                this.resize(1590, 1060);
                jLabel7.setText(fname);
                jLabel52.setText(contact);
                jLabel53.setText(nic);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void loadRooms() {
        try {
            ResultSet rs = MySQL.search("SELECT `rooms`.`id` ,`room_status`.`name`AS `sname`,`room_clean`.`name` AS `cname`,`room_location`.`name`AS `lname` ,`room_type`.`name`AS `tname` FROM `rooms` INNER JOIN `room_status` ON `room_status`.`id`=`rooms`.`room_status` \n"
                    + "INNER JOIN `room_clean` ON `room_clean`.`id`=`rooms`.`room_clean_id` INNER JOIN `room_type` ON `room_type`.`id` =`rooms`.`room_type_id` INNER JOIN `room_location` ON \n"
                    + "`room_location`.`id`=`rooms`.`room_location_id` WHERE `room_status`.`name`='Available' AND `room_clean`.`name`='clean' AND `rooms`.`id`!='0' ORDER BY `rooms`.`id` ASC ");
            DefaultTableModel dtm = (DefaultTableModel) jTable6.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("lname"));
                v.add(rs.getString("tname"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searckRoom() {

        try {

            String location = jComboBox2.getSelectedItem().toString();
            String type = jComboBox1.getSelectedItem().toString();

            Vector queryVector = new Vector();

            if (location.equals("Select")) {

            } else {
                queryVector.add("`room_location`.`name` = '" + location + "'");
            }

            if (type.equals("Select")) {

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
                    + "`room_location`.`id`=`rooms`.`room_location_id` " + wherequery + " AND `room_status`.`name`='Available' AND `room_clean`.`name`='clean' AND `rooms`.`id` !='0' ");

            DefaultTableModel dtm = (DefaultTableModel) jTable6.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("rooms.id"));
                v.add(rs.getString("lname"));
                v.add(rs.getString("tname"));
                ;
                dtm.addRow(v);
            }

            jTable6.setModel(dtm);

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
            jComboBox2.setModel(dcm);

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
            jComboBox1.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadHall() {
        try {
            ResultSet rs = MySQL.search("SELECT `reseption_hall`.`id`,`hall_status`.`name`AS `sname`,`reseption_hall`.`price`,`hall_type`.`name`AS `tname`,`hall_location`.`name`AS `lname` FROM `reseption_hall`\n"
                    + " INNER JOIN `hall_location` ON `hall_location`.`id` = `reseption_hall`.`hall_location_id` \n"
                    + " INNER JOIN `hall_status` ON `hall_status`.`id`=`reseption_hall`.`hall_status_id` INNER JOIN `hall_type` ON `hall_type`.`id`=`reseption_hall`.`hall_type_id` AND `hall_status`.`name`='Available' AND  `reseption_hall`.`id`!='0'");
            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("lname"));
                v.add(rs.getString("tname"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searckHall() {

        try {

            String location = jComboBox5.getSelectedItem().toString();
            String type = jComboBox4.getSelectedItem().toString();

            Vector queryVector = new Vector();

            if (location.equals("Select")) {

            } else {
                queryVector.add("`hall_location`.`name` = '" + location + "'");
            }

            if (type.equals("Select")) {

            } else {
                queryVector.add("`hall_type`.`name` LIKE '%" + type + "%'");
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

            ResultSet rs = MySQL.search("SELECT `reseption_hall`.`id`,`hall_status`.`name`AS `sname`,`reseption_hall`.`price`,`hall_type`.`name`AS `tname`,`hall_location`.`name`AS `lname` FROM `reseption_hall`\n"
                    + " INNER JOIN `hall_location` ON `hall_location`.`id` = `reseption_hall`.`hall_location_id` \n"
                    + " INNER JOIN `hall_status` ON `hall_status`.`id`=`reseption_hall`.`hall_status_id` INNER JOIN `hall_type` ON `hall_type`.`id`=`reseption_hall`.`hall_type_id` " + wherequery + " AND `hall_status`.`name`='Available' AND  `reseption_hall`.`id`!='0' ");

            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("lname"));
                v.add(rs.getString("tname"));
                ;
                dtm.addRow(v);
            }

            jTable4.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void loadHalltype() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `hall_type` ORDER BY `name` ASC ");
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

    public void loadHallLocation() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `hall_location` ORDER BY `name` ASC ");
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

    public void loadPool() {
        try {
            ResultSet rs = MySQL.search("SELECT `pool`.`id` ,`pool_status`.`name` FROM  `pool` INNER JOIN `pool_status` ON `pool_status`.`id`=`pool`.`pool_status_id` WHERE `pool_status`.`name`='Available' AND `pool`.`id` !='0'");
            DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
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

    public void loadReservations() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `reservation`");
            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("customer_nic"));
                v.add(rs.getString("reservation_start_date"));
                v.add(rs.getString("reservation_type_id"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadBottleBrand() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dNow = sdf.format(new Date());
            ResultSet rs = MySQL.search("SELECT `product`.`name` FROM `product` WHERE `product`.`id` IN (SELECT `stock`.`product_id` FROM `stock` WHERE `stock`.`exd` !='" + dNow + "'  AND \n"
                    + "`stock`.`exd` > '" + dNow + "' \n"
                    + " AND `stock`.`qty`!='0') AND \n"
                    + "`category_id` IN (SELECT `id` FROM `category` WHERE `name`='Hard Drinks') ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("product.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox6.setModel(dcm);
            this.resize(1590, 1060);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    DecimalFormat df = new DecimalFormat("0.00");

    public void updateTotal() {
        double total = 0;

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            String t = jTable2.getValueAt(i, 3).toString();
            total = total + Double.parseDouble(t);
        }
        jLabel55.setText(df.format(total));
    }

    public void resetField() {
        jLabel7.setText("None");
        jLabel52.setText("None");
        jLabel53.setText("None");
        jLabel9.setText("None");
        jLabel44.setText("None");
        jLabel16.setText("");
        jLabel18.setText("");
        jLabel22.setText("");
        jLabel24.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jLabel32.setText("0.00");
        jLabel40.setText("0.00");
        jLabel44.setText("0.00");
        jLabel50.setText("0.00");
        jLabel55.setText("0.00");
        

    }

    public Reservation() {
        initComponents();
        loadRooms();
        loadLocation();
        loadtype();
        loadHall();
        loadHalltype();
        loadHallLocation();
        loadPool();
        loadBottleBrand();
        loadReservations();

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
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255,80));
        setMinimumSize(new java.awt.Dimension(1590, 1060));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1590, 1060));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255,80));
        jPanel2.setPreferredSize(new java.awt.Dimension(1590, 1060));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0,100));
        jPanel3.setPreferredSize(new java.awt.Dimension(1600, 970));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0,0,0,100));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/printer.png"))); // NOI18N
        jButton1.setText("Print To Reservation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 410, 50));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 710, 630, 320));

        jPanel4.setBackground(new java.awt.Color(0,0,0,100));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Reservation ID");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 140, 20));

        jLabel20.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Reservation Details");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("None");
        jLabel9.setOpaque(true);
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 60, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Room ID");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 110, 20));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setOpaque(true);
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, 40, 20));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Reseption Hall ID");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 150, 20));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setOpaque(true);
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, 40, 20));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Pool ID");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 90, 20));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setOpaque(true);
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 110, 40, 20));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Bar ID");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 80, 20));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setOpaque(true);
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, 130, 20));

        jButton7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/refresh-page-option.png"))); // NOI18N
        jButton7.setText("Generate Reservation ID");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 320, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 1020, 200));

        jPanel5.setBackground(new java.awt.Color(0,0,0,100));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel5.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 370, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/searching-bar.png"))); // NOI18N
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NIC");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 50, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("First Name");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 110, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contact No");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("None");
        jLabel7.setOpaque(true);
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 160, -1));

        jLabel25.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Customer Details");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, -1, -1));

        jLabel52.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("None");
        jLabel52.setOpaque(true);
        jPanel5.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 160, -1));

        jLabel33.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("NIC");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 50, -1));

        jLabel53.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("None");
        jLabel53.setOpaque(true);
        jPanel5.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 160, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 520, 200));

        jPanel6.setBackground(new java.awt.Color(0,0,0,100));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Available Rooms");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel26.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Available Rooms");
        jPanel6.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/searching-bar.png"))); // NOI18N
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Type");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 50, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Room Details");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, -1));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 220, -1));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Location");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 80, -1));

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jPanel6.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 220, -1));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("0.00");
        jLabel32.setOpaque(true);
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 120, -1));

        jLabel34.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Room Payment");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 140, -1));

        jButton3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton3.setText("Select Room");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 180, -1));

        jTable6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "R No", "R Location", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jPanel6.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 320, 170));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 370, 460));

        jPanel7.setBackground(new java.awt.Color(0,0,0,100));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Available Reseption Hall ");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel38.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/searching-bar.png"))); // NOI18N
        jPanel7.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, 20));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Type");
        jPanel7.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 50, -1));

        jComboBox5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });
        jPanel7.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 220, -1));

        jLabel36.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Location");
        jPanel7.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 100, -1));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Reseption Hall Details");
        jPanel7.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 200, -1));

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jPanel7.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 220, -1));

        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton2.setText("Select Hall");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 180, -1));

        jLabel40.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("0.00");
        jLabel40.setOpaque(true);
        jPanel7.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 120, -1));

        jLabel39.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Hall Payment");
        jPanel7.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 120, -1));

        jTable4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "H No", "H Location", "Type"
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
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(2).setHeaderValue("Type");
        }

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, 170));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 380, 460));

        jPanel8.setBackground(new java.awt.Color(0,0,0,100));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Available Pool");
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 180, -1));

        jLabel41.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Pool Details");
        jPanel8.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 140, -1));

        jLabel42.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/searching-bar.png"))); // NOI18N
        jPanel8.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, 20));

        jLabel43.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Pool Payment");
        jPanel8.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 140, -1));

        jLabel44.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("0.00");
        jLabel44.setOpaque(true);
        jPanel8.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 120, -1));

        jTable5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P No", "P Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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

        jPanel8.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 330, 170));

        jButton5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton5.setText("Select Pool");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 190, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 370, 460));

        jPanel9.setBackground(new java.awt.Color(0,0,0,100));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Available Bar");
        jPanel9.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 180, -1));

        jLabel45.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Currently Qty");
        jPanel9.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, -1));

        jComboBox6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        jPanel9.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 220, -1));

        jLabel46.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Bar Details");
        jPanel9.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, -1));

        jLabel47.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/searching-bar.png"))); // NOI18N
        jPanel9.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, 20));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Qty", "SID", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jPanel9.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 360, 140));

        jTextField2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPanel9.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 220, -1));

        jLabel48.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Poduct Name");
        jPanel9.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, -1));

        jLabel49.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Price Per Bottle");
        jPanel9.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 150, -1));

        jLabel50.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("0.00");
        jPanel9.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 120, -1));

        jButton4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add Bottle");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 160, -1));

        jButton6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/page.png"))); // NOI18N
        jButton6.setText("Select Bar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 190, -1));

        jLabel51.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Quantity");
        jPanel9.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("None");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 50, -1));

        jLabel54.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Bar Payment");
        jPanel9.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 120, -1));

        jLabel55.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("0.00");
        jPanel9.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 120, -1));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 240, 400, 460));

        jPanel10.setBackground(new java.awt.Color(0,0,0,100));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer NIC", "Booking Date", "Reservation ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jPanel10.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 870, 270));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 710, 910, 320));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bc.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1460, 1060));
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 1070));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 1070));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 1060));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String rid = jLabel9.getText();
        String nic = jLabel53.getText();

        String p1 = jLabel32.getText();
        String p2 = jLabel40.getText();
        String p3 = jLabel44.getText();
        String p4 = jLabel55.getText();

        if (nic.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Select Customer", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (rid.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Generate Reservation ID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            String dNow = sdf.format(new Date());

            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
            String tNow = sdf2.format(new Date());

            double fp1 = Double.parseDouble(p1);
            double fp2 = Double.parseDouble(p2);
            double fp3 = Double.parseDouble(p3);
            double fp4 = Double.parseDouble(p4);

            double payement = fp1 + fp2 + fp3 + fp4;

            MySQL.iud("INSERT INTO `currently_reservation_payment`(`other_payment`,`date`,`customer_nic`) VALUES ('" + payement + "','" + dNow + "','" + nic + "')");

            MySQL.iud("INSERT INTO `reservation`(`customer_nic`,`reservation_start_date`,`reservation_start_time`,`reservation_type_id`,`user_id`) VALUES ('" + nic + "','" + dNow + "','" + tNow + "','" + rid + "','" + SignIn.userId + "')");
            loadReservations();
            resetField();
            this.resize(1590, 1060);
            JOptionPane.showMessageDialog(this, "Reservation Is Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable4.getSelectedRow();
        String price = jLabel40.getText();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Hall", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (price.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Please Select a Hall Type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String id = jTable4.getValueAt(selectedRow, 0).toString();
            jLabel18.setText(id);
            MySQL.iud("UPDATE `reseption_hall` SET `hall_status_id`='2'  WHERE `id`= '" + id + "'");
            loadHall();
            
            DefaultTableModel dtm1 = (DefaultTableModel) jTable4.getModel();
            dtm1.setRowCount(0);
            this.resize(1590, 1060);
            JOptionPane.showMessageDialog(this, "Hall Id Added", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {

            int r = jTable4.getSelectedRow();

            String hno = jTable4.getValueAt(r, 0).toString();
            try {
                ResultSet rs = MySQL.search("SELECT * FROM reseption_hall WHERE  `id`='" + hno + "'");
                rs.next();
                String payment = rs.getString("price");

                jLabel40.setText(payment);
                this.resize(1590, 1060);
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        String product_name = jComboBox6.getSelectedItem().toString();
        String qty = jTextField2.getText();
        String availableQty = jLabel14.getText();
        String sp = jLabel50.getText();

        if (product_name.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select Product", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([1-9][0-9]*)|([0][.]([0]+[1-9]+)|([0-9]+))|([1-9][0-9]*[.][0-9]+)").matcher(qty).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid quantity", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (Double.parseDouble(availableQty) < Double.parseDouble(qty)) {
                JOptionPane.showMessageDialog(this, "Quantity out of stock", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();

                boolean isFound = false;
                int x = -1;

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    String s = jTable2.getValueAt(i, 2).toString();

                    if (s.equals(sid)) {
                        isFound = true;
                        x = i;
                        break;
                    }
                }

                //add
                if (isFound) {
                    int option = JOptionPane.showConfirmDialog(this, "This product is already added.Do you want to update?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {

                        double oldQty = Double.parseDouble(jTable2.getValueAt(x, 1).toString());
                        double finalQty = oldQty + Double.parseDouble(qty);
                        this.resize(1590, 1060);;
                        //Check stock
                        if (Double.parseDouble(availableQty) < finalQty) {
                            JOptionPane.showMessageDialog(this, "Quantity out of stock", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {

                            jTable2.setValueAt(String.valueOf(finalQty), x, 1);
                            double UpdatedItemTotal = finalQty * Double.parseDouble(sp);
                            jTable2.setValueAt(String.valueOf(UpdatedItemTotal), x, 3);
                            updateTotal();

                        }
                        //Check stock

                    }

                    jComboBox6.setSelectedIndex(0);
                    jTextField2.setText("");
                    jLabel14.setText("None");
                    jLabel50.setText("None");

                } else {
                    Vector v = new Vector();
                    v.add(product_name);
                    v.add(qty);
                    v.add(sid);

                    double itemtotal = Integer.parseInt(qty) * Double.parseDouble(sp);

                    v.add(df.format(itemtotal));

                    dtm.addRow(v);
                    jComboBox6.setSelectedIndex(0);
                    jTextField2.setText("");
                    jLabel14.setText("None");
                    jLabel50.setText("None");
                    updateTotal();

                    this.resize(1590, 1060);

                    JOptionPane.showMessageDialog(this, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);

                }

                //add
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {

            int r = jTable5.getSelectedRow();

            String pid = jTable5.getValueAt(r, 0).toString();
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `pool` WHERE `id`= '" + pid + "'");
                rs.next();
                String id = rs.getString("Price");
                jLabel44.setText(id);
                this.resize(1590, 1060);
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable5.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Pool", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            String id = jTable5.getValueAt(selectedRow, 0).toString();
            jLabel22.setText(id);
            MySQL.iud("UPDATE `pool` SET `pool_status_id`='2'  WHERE `id`= '" + id + "'");
            loadPool();
            this.resize(1590, 1060);
            DefaultTableModel dtm1 = (DefaultTableModel) jTable5.getModel();
            dtm1.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Pool Id Added", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String nic = jLabel53.getText();
        if (jTable2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please Add to Product", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (nic.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Add to Customer Details ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure about the collections ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {

                try {

                    for (int i = 0; i < jTable2.getRowCount(); i++) {

                        String sid = jTable2.getValueAt(i, 2).toString();
                        String qty = jTable2.getValueAt(i, 1).toString();
                        String product = jTable2.getValueAt(i, 0).toString();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
                        String dNow = sdf.format(new Date());

                        ResultSet rs3 = MySQL.search("SELECT * FROM `stock` WHERE `stock`.`id` ='" + sid + "'");
                        rs3.next();
                        String addQty = rs3.getString("qty");

                        ResultSet rs = MySQL.search("SELECT `id` FROM `bottel_brand` WHERE `product_id` IN (SELECT id  FROM `product` WHERE `product`.`name` ='" + product + "')");
                        rs.next();
                        String pid = rs.getString("id");

                        double updateQty = Double.parseDouble(addQty) - Double.parseDouble(qty);

                        MySQL.iud("UPDATE `stock` SET `qty` ='" + updateQty + "' WHERE `id`= '" + sid + "'");
                        MySQL.iud("INSERT INTO `mini_bar`(`bottel_brand_id`,`stock_id`,`qty`,`customer_nic`,`date`) VALUES ('" + pid + "','" + sid + "','" + qty + "','" + nic + "','" + dNow + "')");
                    }
                    jLabel24.setText(nic);
                    jComboBox6.setSelectedIndex(0);
                    jTextField2.setText("");
                    jLabel14.setText("None");
                    jLabel50.setText("None");

                    DefaultTableModel dtm1 = (DefaultTableModel) jTable2.getModel();
                    dtm1.setRowCount(0);

                    JOptionPane.showMessageDialog(this, "Added Bar ", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String text = jTextField1.getText();
        loadCustomer(text);
        this.resize(1590, 1060);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        searckRoom();
        String type = jComboBox1.getSelectedItem().toString();
        try {

            ResultSet rs = MySQL.search("SELECT * FROM `room_type` WHERE `name`='" + type + "' ");
            rs.next();
            String payment = rs.getString("Payment");
            this.resize(1590, 1060);
            jLabel32.setText(payment);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        searckRoom();
    }//GEN-LAST:event_jComboBox2ItemStateChanged
    String type_id;
    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        searckHall();

    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
        searckHall();

    }//GEN-LAST:event_jComboBox5ItemStateChanged
    String sid;
    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        // TODO add your handling code here:
        String product = jComboBox6.getSelectedItem().toString();
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `stock` WHERE `product_id` IN (SELECT `id` FROM `product` WHERE `name`='" + product + "')");
            rs.next();
            String qty = rs.getString("qty");
            sid = rs.getString("id");

            ResultSet rs1 = MySQL.search("SELECT * FROM `bottel_brand` WHERE `product_id` IN (SELECT `id` FROM `product` WHERE `name`='" + product + "')");
            rs1.next();
            String price = rs1.getString("Price");
            this.resize(1590, 1060);
            jLabel14.setText(qty);
            jLabel50.setText(price);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        int selectedRow = jTable6.getSelectedRow();
        String price = jLabel32.getText();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Room", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (price.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Please Select a Room Type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String id = jTable6.getValueAt(selectedRow, 0).toString();
            jLabel16.setText(id);
            jLabel18.setText("0");
            jLabel22.setText("0");
            jLabel24.setText("0");
             this.resize(1590, 1060);
            DefaultTableModel dtm1 = (DefaultTableModel) jTable6.getModel();
            dtm1.setRowCount(0);

            MySQL.iud("UPDATE `rooms` SET `room_status`='2', `room_clean_id`='2'  WHERE `id`= '" + id + "'");
           
            loadRooms();

            JOptionPane.showMessageDialog(this, "Room Id Added", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String room = jLabel16.getText();
        String hall = jLabel18.getText();
        String pool = jLabel22.getText();
        String bar = jLabel24.getText();
        String nic = jLabel53.getText();

        if (room.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Add to Resevation", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (nic.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Add to Customer", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            long mTime = System.currentTimeMillis();
            String uniqueId = mTime + "_" + SignIn.userId;

            MySQL.iud("INSERT INTO `reservation_type`(`rooms_id`,`reseption_hall_id`,`pool_id`,`mini_bar_id`,`customer_nic`,`unique_id`) VALUES ('" + room + "','" + hall + "','" + pool + "','" + bar + "','" + nic + "','" + uniqueId + "')");
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `reservation_type` WHERE `unique_id` = '" + uniqueId + "'");
                rs.next();
                String id = rs.getString("id");

                jLabel9.setText(id);
                this.resize(1590, 1060);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
