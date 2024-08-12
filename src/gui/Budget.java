/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author DELL
 */
public class Budget extends javax.swing.JPanel {

    /**
     * Creates new form Budget
     */
    String insert_daily_budget;

    public void updateBudget() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dNow = sdf.format(new Date());
        try {
            ResultSet result = MySQL.search("SELECT * FROM `invoice` WHERE `datetime` LIKE '" + dNow + "%'");
            double invoice_budget = 0;

            if (result.next()) {

                ResultSet rs1 = MySQL.search("SELECT SUM(invoice_payment.payment)AS `payment`,SUM( invoice_payment.balance)AS `balance` FROM invoice_payment INNER JOIN invoice ON invoice.id=invoice_payment.invoice_id WHERE `datetime` LIKE '" + dNow + "%' ");
                rs1.next();
                String payment = rs1.getString("payment");
                String balance = rs1.getString("balance");
                double b = Double.parseDouble(balance);

                if (b < 0) {
                    double b1 = b * -1;
                    invoice_budget = Double.parseDouble(payment) - b1;

                } else {
                    invoice_budget = Double.parseDouble(payment);

                }

            } else {
                invoice_budget = 0;
            }

            ResultSet result1 = MySQL.search("SELECT * FROM `reservation_payment` WHERE `Date` LIKE '" + dNow + "%'");
            double reservation_budget = 0;
            if (result1.next()) {

                ResultSet rs2 = MySQL.search("SELECT SUM(reservation_payment.payment)AS `rpayment`,SUM( reservation_payment.balance)AS `rbalance` FROM reservation_payment WHERE reservation_payment.Date  LIKE '" + dNow + "%' ");
                rs2.next();
                String reservation_payment = rs2.getString("rpayment");
                String reservation_balance = rs2.getString("rbalance");
                double r = Double.parseDouble(reservation_balance);

                if (r < 0) {
                    double r1 = r * -1;
                    reservation_budget = Double.parseDouble(reservation_payment) - r1;

                } else {
                    reservation_budget = Double.parseDouble(reservation_payment);

                }

            } else {
                reservation_budget = 0;
            }

            ResultSet result2 = MySQL.search("SELECT * FROM `grn` WHERE `date` LIKE '" + dNow + "%'");
            double grn_budget = 0;
            if (result2.next()) {

                ResultSet rs3 = MySQL.search("SELECT SUM(grn_payment.payment)AS `gpayment`,SUM( grn_payment.balance)AS `gbalance` FROM grn_payment INNER JOIN grn ON grn.id=grn_payment.grn_id WHERE  grn.date LIKE '" + dNow + "%' ");
                rs3.next();
                String grn_payment = rs3.getString("gpayment");
                String grn_balance = rs3.getString("gbalance");
                double g = Double.parseDouble(grn_balance);

                if (g < 0) {
                    double g1 = g * -1;
                    grn_budget = Double.parseDouble(grn_payment) - g1;

                } else {
                    grn_budget = Double.parseDouble(grn_payment);

                }
            } else {
                reservation_budget = 0;
            }

            ResultSet rs = MySQL.search("SELECT SUM(`user_type_of_payment`.`monthly_payment`)AS `full_salary`  FROM `user_type_of_payment` ");
            rs.next();
            String fsalary = rs.getString("full_salary");
            //one_day_salary
            double oneday_salary = Double.parseDouble(fsalary) / 30;

            //one_day_salary
            double daily_budget = (invoice_budget + reservation_budget) - (grn_budget + oneday_salary);
            insert_daily_budget = String.format("%.0f", daily_budget);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void budget() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dNow = sdf.format(new Date());

        try {

            ResultSet rs5 = MySQL.search("SELECT * FROM `budget` WHERE `date`='" + dNow + "'");
            if (rs5.next()) {

                //update Budget
                updateBudget();
                MySQL.iud("UPDATE `budget` SET `daily income`='" + insert_daily_budget + "'  WHERE `date`='" + dNow + "'");
                dailyBudget();

                //update Budget
            } else {

                updateBudget();

                MySQL.iud("INSERT INTO `budget`(`daily income`,`date`) VALUES ('" + insert_daily_budget + "','" + dNow + "')");
                dailyBudget();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dailyBudget() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `budget`");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("daily income"));
                v.add(rs.getString("date"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Budget() {
        initComponents();
        budget();
        dailyBudget();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(790, 920));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Daily Income", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 750, 470));

        jLabel1.setFont(new java.awt.Font("MV Boli", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/calculator.png"))); // NOI18N
        jLabel1.setText("Daily Budget");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 250, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
