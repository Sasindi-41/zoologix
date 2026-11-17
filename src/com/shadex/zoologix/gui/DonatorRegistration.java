
package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import com.shadex.zoologix.validation.Validation;
import java.awt.Color;
import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class DonatorRegistration extends javax.swing.JPanel {

    public JPanel getjPanel1() {
        return this;
    }

    private String currentDonatorId;
    HashMap<String, String> costMap = new HashMap<>();
    int donatorId;

    private Dashboard dashboard;
    public DonatorRegistration(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        loadCostPerDay();
        loadDonator();
        jTextField1.grabFocus();

    }

    public void loadCostPerDay() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `cost_per_day`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("cost"));
                costMap.put(resultSet.getString("cost"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadAnimal(String costId) {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `donation` WHERE `cost_per_day_id`='" + costId + "'");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("animal"));
                costMap.put(resultSet.getString("animal"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadDonator() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `donator_invoice` INNER JOIN `donator` ON `donator`.`id`=`donator_invoice`.`donator_id`"
                    + "INNER JOIN `donation` ON `donation`.`id`=`donator_invoice`.`donation_id`"
                    + "INNER JOIN `cost_per_day` ON `cost_per_day`.`id`=`donation`.`cost_per_day_id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("donator.fullname"));
                vector.add(resultSet.getString("donator.mobile"));
                vector.add(resultSet.getString("donator.email"));
                vector.add(resultSet.getString("cost_per_day.cost"));
                vector.add(resultSet.getString("donation.animal"));
                vector.add(resultSet.getString("donator_invoice.date"));

                model.addRow(vector);
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Donator Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N

        jLabel2.setText("Full Name");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Cost per day");

        jLabel3.setText("Mobile");

        jLabel4.setText("Email");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Animal");

        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Full Name", "Mobile", "Email", "Cost per day", "Animal", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Donator");

        jButton2.setBackground(new java.awt.Color(0, 51, 0));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 811, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel6))
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

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

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(Color.black);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(64, 147, 105));
    }//GEN-LAST:event_jButton1MouseExited

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        String costPerDay = String.valueOf(jComboBox1.getSelectedItem());
        if (!costPerDay.equals("Select")) {
            String costId = costMap.get(costPerDay);

            loadAnimal(costId);
        } else {
            DefaultComboBoxModel emptyModel = new DefaultComboBoxModel(new String[]{"Select"});
            jComboBox2.setModel(emptyModel);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        String fullName = String.valueOf(jTable1.getValueAt(row, 0));
        jTextField1.setText(fullName);

        String mobile = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField2.setText(mobile);
        jTextField2.setEditable(false);

        String email = String.valueOf(jTable1.getValueAt(row, 2));
        jTextField3.setText(email);
        jTextField3.setEditable(false);

        String cost = String.valueOf(jTable1.getValueAt(row, 3));
        jComboBox1.setSelectedItem(cost);

        String animal = String.valueOf(jTable1.getValueAt(row, 4));
        jComboBox2.setSelectedItem(animal);

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `donator` "
                    + " WHERE `fullname`='" + fullName + "' AND `mobile`='" + mobile + "' AND `email`='" + email + "' ");

            while (resultSet.next()) {
                currentDonatorId = resultSet.getString("id");

            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            String search = jTextField5.getText();
            int row = jTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `donator_invoice` INNER JOIN `donator` ON"
                    + " `donator`.`id`=`donator_invoice`.`donator_id`"
                    + "INNER JOIN `donation` ON `donation`.`id`=`donator_invoice`.`donation_id`"
                    + "INNER JOIN `cost_per_day` ON `cost_per_day`.`id`=`donation`.`cost_per_day_id` WHERE `donator`.`fullname`= '" + search + "'");

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("donator.fullname"));
                vector.add(resultSet.getString("donator.mobile"));
                vector.add(resultSet.getString("donator.email"));
                vector.add(resultSet.getString("cost_per_day.cost"));
                vector.add(resultSet.getString("donation.animal"));
                vector.add(resultSet.getString("donator_invoice.date"));

                model.addRow(vector);
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField2.grabFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.grabFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed
    private String generateReceiptNo() {
        long uniqueId = System.currentTimeMillis() % 100000; 
        return "REC-" + uniqueId;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String name = jTextField1.getText();
        String mobile = jTextField2.getText();
        String email = jTextField3.getText();
        String cost = String.valueOf(jComboBox1.getSelectedItem());
        String animal = String.valueOf(jComboBox2.getSelectedItem());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the donator name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validateLand(mobile)) {
            return;
        } else if (validateEmail(email)) {
            return;
        } else if (cost.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the donation cost ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (animal.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the animal  ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `donator` WHERE `email`='" + email + "' OR `mobile`='" + mobile + "'  ");

                if (resultSet.next()) {
                    donatorId = resultSet.getInt("id");
                    JOptionPane.showMessageDialog(this, "This donator already exite ", "Warning", JOptionPane.WARNING_MESSAGE);

                }

                MySQL.executeIUD("INSERT INTO `donator` (`fullname`,`mobile`,`email`)VALUES('" + name + "','" + mobile + "','" + email + "')");

                ResultSet newDonatorResultSet = MySQL.executeSearch("SELECT * FROM `donator`WHERE `email`='" + email + "' OR `mobile`='" + mobile + "'");
                if (newDonatorResultSet.next()) {
                    donatorId = newDonatorResultSet.getInt("id");
                }
                ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `donation` WHERE `animal`='" + animal + "'");

                if (resultSet1.next()) {
                    String donationId = resultSet1.getString("donation.id");

                    MySQL.executeIUD("INSERT INTO `donator_invoice`(`donator_id`,`donation_id`,`date`)VALUES('" + donatorId + "' ,'" + donationId + "','" + formattedDate + "' )");

                    JOptionPane.showMessageDialog(this, "Successfully added ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                }
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String name = jTextField1.getText();
        String mobile = jTextField2.getText();
        String email = jTextField3.getText();
        String cost = String.valueOf(jComboBox1.getSelectedItem());
        String animal = String.valueOf(jComboBox2.getSelectedItem());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the donator name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validateLand(mobile)) {
            return;
        } else if (validateEmail(email)) {
            return;
        } else if (cost.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the donation cost ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (animal.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the animal  ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `donator` WHERE `email`='" + email + "' AND `mobile`='" + mobile + "'  ");

                if (resultSet.next()) {
                    donatorId = resultSet.getInt("id");

                }

                MySQL.executeIUD("UPDATE `donator` SET `fullname`='" + name + "' WHERE `id`='" + donatorId + "'");

                ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `donation` WHERE `animal`='" + animal + "'");

                if (resultSet1.next()) {
                    String donationId = resultSet1.getString("donation.id");

                    MySQL.executeIUD("UPDATE `donator_invoice` SET `donator_id`='" + donatorId + "',`donation_id`='" + donationId + "',`date`='" + formattedDate + "' WHERE `donator_id`='" + donatorId + "' ");

                    JOptionPane.showMessageDialog(this, "Successfully updaded ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                }
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        reset();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
String receiptNo = generateReceiptNo();
        String name = jTextField1.getText();

        System.out.println("currentDonatorId: " + currentDonatorId);
        String amount = String.valueOf(jComboBox1.getSelectedItem());
        String animal = String.valueOf(jComboBox2.getSelectedItem());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Full Name or Company Name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `donator_invoice`  "
                        + " WHERE `donator_invoice`.`donator_id`='" + currentDonatorId + "' ");

                
                if (!resultSet.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(this, "No data found for the specified adopter", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                while (resultSet.next()) {

                    String end = resultSet.getString("donator_invoice.date");

                    String path = "src//com//shadex//zoologix//reports//Donation_P.T.O.jasper";
                    File file = new File(path);
                    if (!file.exists()) {
                        System.out.println("File not found at path: " + file.getAbsolutePath());
                    }

                    HashMap<String, Object> params = new HashMap<>();

                    params.put("Parameter3", formattedDate);
                    params.put("Parameter4", name);
                    params.put("Parameter5", amount);
                    params.put("Parameter7", animal);
                    params.put("Parameter8", amount);
                    params.put("Parameter9", formattedDate);
                    params.put("Parameter10", receiptNo);

                    JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, new JREmptyDataSource());

                    JasperViewer.viewReport(jasperPrint, false);

                }

            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }

        }    }//GEN-LAST:event_jButton5ActionPerformed
    private boolean validateMobile(String value) {
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the contact number ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        } else if (!value.matches(Validation.VALIDATE_MOBILE.validate())) {
            JOptionPane.showMessageDialog(this, "Incorrect contact number ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;

        }
        return false;
    }
    //Mobile vaildation

    private boolean validateLand(String value) {
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the contact number ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        } else if (!value.matches(Validation.VALIDATE_LAND_LINE.validate())) {
            JOptionPane.showMessageDialog(this, "Incorrect contact number ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;

        }
        return false;
    }
//Email vaildation

    private boolean validateEmail(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter the email ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        } else if (!value.matches(Validation.VALIDATE_EMAIL.validate())) {
            JOptionPane.showMessageDialog(this, "Incorrect Email ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;

        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    public void reset() {
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField5.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        loadDonator();
    }
}
