/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.shadex.zoologix.gui;

import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 *
 * @author AT
 */
public class FeedSchedule extends javax.swing.JPanel {

    public void setId(String id) {
        jTextField1.setText(id);
    }

    public void foodId(String id) {
        jTextField2.setText(id);
    }

    public void medicineId(String id) {
        jTextField3.setText(id);
    }

    public void userId(String id) {
        jTextField4.setText(id);
    }
    
     

    public static HashMap<String, String> venueMap = new HashMap<>();

    private Dashboard dashboard;

    public FeedSchedule(Dashboard dashboard1) {
        initComponents();
        this.dashboard = dashboard1;
        loadVenue();
        loadFeedSchedule();
        jTextField4.setEditable(false);
    }

//    private void loadFeedSchedule() {
//
//        try {
//
//            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feed_shedule` INNER JOIN `animal` ON"
//                    + "`animal`.`id` = `feed_shedule`.`animal_id` INNER JOIN `venue` ON"
//                    + "`venue`.`id` = `feed_shedule`.`venue_id` INNER JOIN `food` ON"
//                    + "`food`.`id` = `feed_shedule`.`food_id` INNER JOIN `medicine` ON"
//                    + "`medicine`.`id` = `feed_shedule`.`medicine_id` INNER JOIN `user` ON"
//                    + "`user`.`id` = `feed_shedule`.`user_id`");
//
//            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//            dtm.setRowCount(0);
//
//            while (resultSet.next()) {
//
//                Vector<String> vector = new Vector<>();
//                vector.add(resultSet.getString("id"));
//                vector.add(resultSet.getString("date"));
//                vector.add(resultSet.getString("animal.name"));
//                vector.add(resultSet.getString("venue.venue"));
//                vector.add(resultSet.getString("food.name"));
//                vector.add(resultSet.getString("medicine.name"));
//                vector.add(resultSet.getString("description"));
//                vector.add(resultSet.getString("user.username"));
//
//                dtm.addRow(vector);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private void loadFeedSchedule() {
    try {
        ResultSet resultSet = MySQL.executeSearch(
            "SELECT feed_shedule.id, feed_shedule.date, animal.name AS animal_name, " +
            "venue.venue, food.name AS food_name, medicine.name AS medicine_name, " +
            "feed_shedule.description, user.username " +
            "FROM feed_shedule " +
            "INNER JOIN animal ON feed_shedule.animal_id = animal.id " +
            "INNER JOIN venue ON feed_shedule.venue_id = venue.id " +
            "INNER JOIN food ON feed_shedule.food_id = food.id " +
            "INNER JOIN medicine ON feed_shedule.medicine_id = medicine.id " +
            "INNER JOIN user ON feed_shedule.user_id = user.id"
        );

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);

        while (resultSet.next()) {
            Vector<String> vector = new Vector<>();
            vector.add(resultSet.getString("id"));
            vector.add(resultSet.getString("date"));
            vector.add(resultSet.getString("animal_name"));
            vector.add(resultSet.getString("venue"));
            vector.add(resultSet.getString("food_name"));
            vector.add(resultSet.getString("medicine_name"));
            vector.add(resultSet.getString("description"));
            vector.add(resultSet.getString("username"));
            dtm.addRow(vector);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void loadVenue() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `venue`");

            Vector<String> vector = new Vector<>();
            vector.add("SELECT");

            while (resultSet.next()) {
                vector.add(resultSet.getString("venue"));
                venueMap.put(resultSet.getString("venue"), resultSet.getString("id"));
            }

            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(1, 50, 32));
        setPreferredSize(new java.awt.Dimension(1190, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 50, 32));
        jLabel2.setText("Animal ID");

        jTextField1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jTextField1.setToolTipText("");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));

        jButton1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton1.setText("SELECT");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 50, 32));
        jLabel3.setText("Food ID");

        jTextField2.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jTextField2.setActionCommand("<Not Set>");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton2.setText("SELECT");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));

        jLabel4.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 50, 32));
        jLabel4.setText("Medicine ID");

        jButton3.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton3.setText("SELECT");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 50, 32));
        jLabel5.setText("Venue");

        jComboBox1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));

        jLabel6.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 50, 32));
        jLabel6.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));
        jScrollPane1.setViewportView(jTextArea1);

        jLabel7.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(1, 50, 32));
        jLabel7.setText("User ID");

        jTextField4.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));

        jButton4.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton4.setText("SELECT");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(1, 50, 32));
        jButton5.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(1, 50, 32));
        jButton6.setText("Update");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32), 2));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(188, 184, 138));
        jButton7.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(1, 50, 32));
        jButton7.setText("Reset");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32), 2));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 50, 32));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Date :");

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Animal ID", "Venue", "Food ID", "Medicine ID", "Description", "User ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton8.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(1, 50, 32));
        jButton8.setText("Report");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 50, 32), 2));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AnimalView animalView = new AnimalView(null, true,false);
        animalView.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FoodView foodView = new FoodView(null, true);
        foodView.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MedicineView medicineView = new MedicineView(null, true);
        medicineView.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        UserView userView = new UserView(null, true);
        userView.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String dt = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        String animalId = jTextField1.getText();
        String venue = String.valueOf(jComboBox1.getSelectedItem());
        String foodId = jTextField2.getText();
        String medicineId = jTextField3.getText();
        String description = jTextArea1.getText();
        String userId = jTextField4.getText();

        if (dt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose the Date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (animalId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a animal", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (foodId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select food", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (userId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select the user", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (venue.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please select a venue", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        {

            try {

                MySQL.executeIUD("INSERT INTO `feed_shedule`(`date`, `animal_id`,`venue_id` , `food_id`, `medicine_id`, `description`, `user_id`) "
                        + "VALUES ('" + dt + "','" + animalId + "','" + venueMap.get(venue) + "','" + foodId + "','" + medicineId + "','" + description + "','" + userId + "') ");

                loadFeedSchedule();
                reset();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        String id = String.valueOf(jTable1.getValueAt(row, 0));
        jTextField1.setText(id);

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

        String venue = String.valueOf(jTable1.getValueAt(row, 3));
        jComboBox1.setSelectedItem(venue);

        String foodId = String.valueOf(jTable1.getValueAt(row, 4));
        jTextField2.setText(foodId);

        String medicineId = String.valueOf(jTable1.getValueAt(row, 5));
        jTextField3.setText(medicineId);

        String des = String.valueOf(jTable1.getValueAt(row, 6));
        jTextArea1.setText(des);

        String userId = String.valueOf(jTable1.getValueAt(row, 7));
        jTextField4.setText(userId);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
//        int row = jTable1.getSelectedRow();
//        
//        if (row != -1) {
//            String dt = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
//            String animalId = jTextField1.getText();
//            String venue = String.valueOf(jComboBox1.getSelectedItem());
//            String foodId = jTextField2.getText();
//            String medicineId = jTextField3.getText();
//            String description = jTextArea1.getText();
//
//            if (animalId.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please select a animal", "Warning", JOptionPane.WARNING_MESSAGE);
//            } else if (foodId.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please select food", "Warning", JOptionPane.WARNING_MESSAGE);
//            } else if (venue.equals("SELECT")) {
//                JOptionPane.showMessageDialog(this, "Please select a venue", "Warning", JOptionPane.WARNING_MESSAGE);
//            } else {
//                int id = Integer.parseInt(String.valueOf(jTable1.getValueAt(row, 0)));
//                try {
//
//                    MySQL.executeIUD("UPDATE `feed_shedule` SET `date` = '" + dt + "',`animal_id` = '" + AnimalView.animalMap.get(animalId) + "' , `venue_id` = '" + venueMap.get(venue) + "' , "
//                            + "`food_id` = '" + FoodView.foodMap.get(foodId) + "' , `medicine_id` = '" + MedicineView.medicineMap.get(medicineId) + "', `description` = '" + description + "' "
//                            + "WHERE `feed_shedule`.`id` = '" +id + "' ");
//
//                    loadFeedSchedule();
//                    reset();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }else{
//        JOptionPane.showMessageDialog(this,"Please Select Row","Warning",JOptionPane.WARNING_MESSAGE);
//        }


 int row = jTable1.getSelectedRow();
    
    if (row != -1) {
        String dt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String animalId = jTextField1.getText();
        String venue = String.valueOf(jComboBox1.getSelectedItem());
        String foodId = jTextField2.getText();
        String medicineId = jTextField3.getText();
        String description = jTextArea1.getText();

        if (animalId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an animal", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } 
        if (foodId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select food", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } 
        if (venue.equals("SELECT")) {
            JOptionPane.showMessageDialog(this, "Please select a venue", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate if the animal ID, venue, food, and medicine exist in the respective maps
        if (!AnimalView.animalMap.containsKey(animalId)) {
            JOptionPane.showMessageDialog(this, "Invalid Animal ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!venueMap.containsKey(venue)) {
            JOptionPane.showMessageDialog(this, "Invalid Venue", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!FoodView.foodMap.containsKey(foodId)) {
            JOptionPane.showMessageDialog(this, "Invalid Food ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!MedicineView.medicineMap.containsKey(medicineId)) {
            JOptionPane.showMessageDialog(this, "Invalid Medicine ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the ID of the selected row
        int id = Integer.parseInt(String.valueOf(jTable1.getValueAt(row, 0)));
        
        try {
            // Update query
            String query = "UPDATE `feed_shedule` SET " +
                           "`date` = '" + dt + "', " +
                           "`animal_id` = '" + AnimalView.animalMap.get(animalId) + "', " +
                           "`venue_id` = '" + venueMap.get(venue) + "', " +
                           "`food_id` = '" + FoodView.foodMap.get(foodId) + "', " +
                           "`medicine_id` = '" + MedicineView.medicineMap.get(medicineId) + "', " +
                           "`description` = '" + description + "' " +
                           "WHERE `feed_shedule`.`id` = '" + id + "'";
            MySQL.executeIUD(query);

            // Reload table and reset fields
            loadFeedSchedule();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
    }



    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        reset();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextArea1.setText("");
        jComboBox1.setSelectedIndex(0);
        jTable1.clearSelection();
    }
}
