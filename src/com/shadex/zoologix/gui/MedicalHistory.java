
package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class MedicalHistory extends javax.swing.JPanel {

    public void setFeedScheduleId(String id) {
        jTextField3.setText(id);
    }

    public void setUserID(String userId) {
        jTextField4.setText(userId);
    }

    public static HashMap<String, String> healthStatusMap = new HashMap<>();
    public static HashMap<String, String> animalMap = new HashMap<>();

    public static HashMap<String, String> userMap = new HashMap<>();

    private Dashboard dashboard;
    

    public MedicalHistory(Dashboard dashboard1) {
        initComponents();
        this.dashboard = dashboard1;
        loadHealthStatus();
        loadMedicalHistory();
        cannotEdit();
    }
    public void setAnimalID(String id) {
        jTextField1.setText(id);
    }

    private void cannotEdit() {
        jTextField1.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);

    }

    private void loadMedicalHistory() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `medical_history` INNER JOIN `animal` ON"
                    + " `medical_history`.`animal_id` = `animal`.`id` INNER JOIN `health_status` ON "
                    + " `medical_history`.`health_status_id` = `health_status`.`id` INNER JOIN `feed_shedule` ON"
                    + " `medical_history`.`feed_shedule_id` = `feed_shedule`.`id` INNER JOIN `user` ON"
                    + " `medical_history`.`user_id` = `user`.`id`");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("animal.name"));
                vector.add(resultSet.getString("health_status.status"));
                vector.add(resultSet.getString("diagnosis"));
                vector.add(resultSet.getString("feed_shedule.id"));
                vector.add(resultSet.getString("user.email"));
                healthStatusMap.put(resultSet.getString("health_status.status"), resultSet.getString("health_status.id"));
                animalMap.put(resultSet.getString("animal.name"), resultSet.getString("animal.id"));
                userMap.put(resultSet.getString("user.email"), resultSet.getString("user.id"));

                dtm.addRow(vector);

            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

    }

    
    public void loadHealthStatus() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `health_status`");

            Vector<String> vector = new Vector<>();
            vector.add("SELECT");

            while (resultSet.next()) {

                vector.add(resultSet.getString("status"));
                healthStatusMap.put(resultSet.getString("status"), resultSet.getString("id"));

            }

            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1190, 800));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Medical History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 800));

        jTextField1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 50, 32));
        jLabel2.setText("Animal ID");

        jLabel5.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 50, 32));
        jLabel5.setText("Health Status");

        jComboBox1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel6.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 50, 32));
        jLabel6.setText("Diagnosis");

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Select Feed Schedule");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 50, 32));
        jLabel4.setText(" Feed Schedule ID");

        jTextField3.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(1, 50, 32));
        jLabel7.setText("User ID");

        jTextField4.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Select User");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 51, 0));
        jButton6.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 51, 0));
        jButton7.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Animal ID", "Health Status", "Diagnosis", "Feed Schedule ID", "User ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 50, 32));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Date :");

        jButton8.setBackground(new java.awt.Color(0, 51, 0));
        jButton8.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Select Animal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)))
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(338, 338, 338))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(21, 21, 21)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(97, 97, 97))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addGap(337, 337, 337))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1190, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FeedScheduleView feedScheduleView = new FeedScheduleView(null, true);
        feedScheduleView.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String user = "User1";
        UserView userView = new UserView(null, true, user);
        userView.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String dt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String animalId = jTextField1.getText();
        String healthStatus = String.valueOf(jComboBox1.getSelectedItem());
        String diagnosis = jTextArea1.getText();
        String feedSchedule = jTextField3.getText();
        String userId = jTextField4.getText();

        if (dt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose the Date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (animalId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a animal", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (healthStatus.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please select health Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (diagnosis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add the diagnosis", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (feedSchedule.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select the feedSchedule", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (userId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select the user", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `medical_history` WHERE `date` = '" + dt + "' && `animal_id` = '" + animalMap.get(animalId) + "' && `health_status_id` = '" + healthStatusMap.get(healthStatus) + "' "
                    + "&& `diagnosis` = '" + diagnosis + "' && `feed_shedule_id` = '" + feedSchedule + "' && `user_id` = '" + userMap.get(userId) + "'");
                
            

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Medical History already added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `medical_history` (`date`,`animal_id`,`health_status_id`,`diagnosis`,`feed_shedule_id`,`user_id`) "
                        + "VALUES ('" + dt + "', '" + animalId + "','" + healthStatusMap.get(healthStatus) + "','" + diagnosis + "','" + feedSchedule + "','" + userId + "')");

                       
                    loadMedicalHistory();
                    reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {

            int row = jTable1.getSelectedRow();

            if (row != -1) {

                int id = Integer.parseInt(String.valueOf(jTable1.getValueAt(row, 0)));
                Date dt = jDateChooser1.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = sdf.format(dt);
                String animalId = jTextField1.getText();
                String healthStatus = String.valueOf(jComboBox1.getSelectedItem());
                String diagnosis = jTextArea1.getText();
                String feedSchedule = jTextField3.getText();
                String user = jTextField4.getText();

                if (dt == null) {
                    JOptionPane.showMessageDialog(this, "Please choose the Date", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (animalId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select a animal", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (healthStatus.equals("SELECT")) {
                    JOptionPane.showMessageDialog(this, "Please select health Status", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (diagnosis.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please add the diagnosis", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (feedSchedule.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select the feedSchedule", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (user.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select the user", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `medical_history`");

                    if (resultSet.next()) {
                        String date = String.valueOf(jTable1.getValueAt(row, 1));
                        String getAnimal = String.valueOf(jTable1.getValueAt(row, 2));
                        String getHealthStatus = String.valueOf(jTable1.getValueAt(row, 3));
                        String getDiagnosis = String.valueOf(jTable1.getValueAt(row, 4));
                        String getFeedSchedule = String.valueOf(jTable1.getValueAt(row, 5));
                        String getUser = String.valueOf(jTable1.getValueAt(row, 6));

                        if (date.equals(String.valueOf(formatDate)) && getAnimal.equals(animalId) && getHealthStatus.equals(healthStatus)
                            && getDiagnosis.equals(diagnosis) && getFeedSchedule.equals(feedSchedule)
                            && getUser.equals(user)) {

                            JOptionPane.showMessageDialog(this, "This Medical Record is already added", "Warning", JOptionPane.WARNING_MESSAGE);

                        } else {

                            MySQL.executeIUD("UPDATE `medical_history` SET `date` = '" + formatDate + "', `animal_id` = '" + animalMap.get(animalId) + "',"
                                + "`health_status_id` = '" + healthStatusMap.get(healthStatus) + "', `diagnosis` = '" + diagnosis + "',"
                                + "`feed_shedule_id` = '" + feedSchedule + "', `user_id` = '" + userMap.get(user) + "' WHERE `id` = '" + id + "'");

                            JOptionPane.showMessageDialog(this, "This Medical Record update success", "Success", JOptionPane.INFORMATION_MESSAGE);
                            loadMedicalHistory();
                            reset();

                        }

                    }else {
                        JOptionPane.showMessageDialog(this, "Medical History record not added", "Warning", JOptionPane.WARNING_MESSAGE);

                    }

                }

            } else {
                JOptionPane.showMessageDialog(this, "Select a row", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        reset();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable1.getValueAt(row, 1));
            Date dt = sdf.parse(dateString);
            jDateChooser1.setDate(dt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String animalId = String.valueOf(jTable1.getValueAt(row, 2));
        jTextField1.setText(animalId);
        String healthStatus = String.valueOf(jTable1.getValueAt(row, 3));
        jComboBox1.setSelectedItem(healthStatus);
        String diagnosis = String.valueOf(jTable1.getValueAt(row, 4));
        jTextArea1.setText(diagnosis);
        String feedSchedule = String.valueOf(jTable1.getValueAt(row, 5));
        jTextField3.setText(feedSchedule);
        String user = String.valueOf(jTable1.getValueAt(row, 6));
        jTextField4.setText(user);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String path = "src/com/shadex/zoologix/reports/MedicalHistoryReport.jasper";

        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            System.out.println("Report file not found at path: " + path);
            return;
        }

        HashMap<String, Object> parameters = new HashMap<>();

        try {
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String animal1 = "Anima6";
        AnimalView animalView = new AnimalView(null, true, animal1);
        animalView.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextArea1.setText("");
        jComboBox1.setSelectedIndex(0);
        jDateChooser1.setDate(null);
        jTable1.clearSelection();
    }
}
