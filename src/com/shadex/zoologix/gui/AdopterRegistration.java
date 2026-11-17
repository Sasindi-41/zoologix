
package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import com.shadex.zoologix.validation.Validation;
import java.awt.Color;
import java.awt.Window;
import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class AdopterRegistration extends javax.swing.JPanel {

    public JPanel getjPanel1() {
        return this;
    }

    private String currentAdopterId;
    private Dashboard dashboard;

    HashMap<String, String> statusMap = new HashMap<>();
    HashMap<String, String> countryMap = new HashMap<>();
    HashMap<String, String> adopterTypeMap = new HashMap<>();
    HashMap<String, String> adopterLevelMap = new HashMap<>();

    
    public AdopterRegistration(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        loadStatus();
        loadAdopterType();
        loadCountry();
        loadAdoptionLevel();
        loadAdopters();
    }

    public void loadStatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `status`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                statusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadAdopterType() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adopter_type`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                adopterTypeMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadCountry() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `country`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("country"));
                countryMap.put(resultSet.getString("country"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadAdoptionLevel() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_levels`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("level"));
                adopterLevelMap.put(resultSet.getString("level"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadAdopters() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adopter` INNER JOIN `country` ON `country`.`id`=`adopter`.`country_id`"
                    + "INNER JOIN `status` ON `status`.`id`=`adopter`.`status_id` INNER JOIN `adopter_type` ON `adopter_type`.`id`=`adopter`.`adopter_type_id`"
                    + "INNER JOIN `adoption_levels` ON `adoption_levels`.`id`=`adopter`.`adoption_levels_id` ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("adopter_type.type"));
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("addressline1"));
                vector.add(resultSet.getString("addressline2"));
                vector.add(resultSet.getString("city"));
                vector.add(resultSet.getString("country.country"));
                vector.add(resultSet.getString("adoption_levels.level"));
                vector.add(resultSet.getString("status.status"));
                vector.add(resultSet.getString("reg_date"));

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
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1188, 770));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1400, 800));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Adoption Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N

        jLabel7.setText("Address Line2");

        jLabel2.setText("Adopter Type");

        jLabel9.setText("Country");

        jLabel5.setText("Email");

        jLabel4.setText("Mobile");

        jLabel8.setText("City");

        jLabel6.setText("Address Line1");

        jLabel3.setText("Name");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel11.setText("Status");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel13.setText("Adoption level");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Print Reciept");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 51, 0));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Adoption Level");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 51, 0));
        jButton7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 51, 0));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Adoption Renewal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Name", "Mobile", "Email", "Address line1", "Address line2", "City", "Country", "Level", "Status", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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

        jButton1.setBackground(new java.awt.Color(1, 50, 32));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
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

        jLabel10.setText("Adopter");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel7)
                                .addComponent(jLabel13))
                            .addGap(8, 8, 8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 193, Short.MAX_VALUE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 438, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(131, 131, 131))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
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
        jTextField4.grabFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            String name = jTextField1.getText();
            String mobile = jTextField2.getText();
            String email = jTextField3.getText();
            String adoptionLevel = String.valueOf(jComboBox4.getSelectedItem());

            if (adoptionLevel.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Enter the adopter level", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adopter` WHERE `name`='" + name + "' AND `mobile`='" + mobile + "' AND `email`='" + email + "' ");

            while (resultSet.next()) {
                currentAdopterId = resultSet.getString("id");
                String start = resultSet.getString("reg_date");
                if (adoptionLevel.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Select the level", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter the adopter name", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    Window parentWindow = SwingUtilities.getWindowAncestor(this);

                    AdoptionLevelReg level = new AdoptionLevelReg((java.awt.Frame) parentWindow, true, adoptionLevel, name, currentAdopterId, start);
                    level.setVisible(true);

                }
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(Color.black);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(64, 147, 105));
    }//GEN-LAST:event_jButton1MouseExited

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String adopterType = String.valueOf(jComboBox1.getSelectedItem());
        if (adopterType.equals("Corparate Adopters")) {
            jLabel3.setText("Company Name");
            jLabel4.setText("Land Number");
        } else {
            jLabel3.setText("Name");
            jLabel4.setText("Mobile");
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed
    private boolean validateContactNumber(String value, String adopterType) {
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the contact number ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (adopterType.equals("Corporate Adopters")) {
            if (!value.matches(Validation.VALIDATE_LAND_LINE.validate())) {
                JOptionPane.showMessageDialog(this, "Incorrect landline number ", "Warning", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        } else {
            if (!value.matches(Validation.VALIDATE_MOBILE.validate())) {
                JOptionPane.showMessageDialog(this, "Incorrect mobile number ", "Warning", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        currentAdopterId = null;

        int row = jTable1.getSelectedRow();

        String type = String.valueOf(jTable1.getValueAt(row, 0));
        jComboBox1.setSelectedItem(type);

        String name = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField1.setText(name);

        String mobile = String.valueOf(jTable1.getValueAt(row, 2));
        jTextField2.setText(mobile);
        jTextField2.setEditable(false);

        String email = String.valueOf(jTable1.getValueAt(row, 3));
        jTextField3.setText(email);
        jTextField3.setEditable(false);

        String address1 = String.valueOf(jTable1.getValueAt(row, 4));
        jTextField4.setText(address1);

        String address2 = String.valueOf(jTable1.getValueAt(row, 5));
        jTextField5.setText(address2);

        String city = String.valueOf(jTable1.getValueAt(row, 6));
        jTextField6.setText(city);

        String country = String.valueOf(jTable1.getValueAt(row, 7));
        jComboBox3.setSelectedItem(country);

        String level = String.valueOf(jTable1.getValueAt(row, 8));
        jComboBox4.setSelectedItem(level);

        String status = String.valueOf(jTable1.getValueAt(row, 9));
        jComboBox2.setSelectedItem(status);

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adopter` INNER JOIN `adoption_level_reg` ON `adoption_level_reg`.`adopter_id`=`adopter`.`id`"
                    + " WHERE `name`='" + name + "' AND `mobile`='" + mobile + "' AND `email`='" + email + "' ");

            while (resultSet.next()) {
                currentAdopterId = resultSet.getString("adopter.id");
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String search = jTextField8.getText();
            int row = jTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adopter` INNER JOIN `country` ON `country`.`id`=`adopter`.`country_id`"
                    + "INNER JOIN `status` ON `status`.`id`=`adopter`.`status_id` INNER JOIN `adopter_type` ON `adopter_type`.`id`=`adopter`.`adopter_type_id`"
                    + "INNER JOIN `adoption_levels` ON `adoption_levels`.`id`=`adopter`.`adoption_levels_id` WHERE `adopter`.`name`='" + search + "'");

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("adopter_type.type"));
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("addressline1"));
                vector.add(resultSet.getString("addressline2"));
                vector.add(resultSet.getString("city"));
                vector.add(resultSet.getString("country.country"));
                vector.add(resultSet.getString("adoption_levels.level"));
                vector.add(resultSet.getString("status.status"));
                vector.add(resultSet.getString("reg_date"));

                model.addRow(vector);
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField2.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jTextField5.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jTextField6.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            String name = jTextField1.getText();
            String mobile = jTextField2.getText();
            String email = jTextField3.getText();
            String adoptionLevel = String.valueOf(jComboBox4.getSelectedItem());

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adopter` INNER JOIN `adoption_level_reg` ON `adoption_level_reg`.`adopter_id`=`adopter`.`id`"
                    + " WHERE `name`='" + name + "' AND `mobile`='" + mobile + "' AND `email`='" + email + "' ");
            boolean haveReg = false;
            while (resultSet.next()) {

                haveReg = true;

                currentAdopterId = resultSet.getString("id");
                String start = resultSet.getString("reg_date");
                String renewalCount = resultSet.getString("adoption_level_reg.renewal_count");

                if (adoptionLevel.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Select the level", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter the adopter name", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    Window parentWindow = SwingUtilities.getWindowAncestor(this);

                    AdoptionRenewal renewal = new AdoptionRenewal((java.awt.Frame) parentWindow, true, adoptionLevel, name, currentAdopterId, start, renewalCount);
                    renewal.setVisible(true);

                }
            }
            if (!haveReg) {
                JOptionPane.showMessageDialog(this, "This adopter not registered adoption level", "Warning", JOptionPane.WARNING_MESSAGE);

            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    private String generateReceiptNo() {
        long uniqueId = System.currentTimeMillis() % 100000; // Extract the last 5 digits of the current timestamp
        return "REC-" + uniqueId; // Example: REC-54321
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String receiptNo = generateReceiptNo();
        String name = jTextField1.getText();
        String level = String.valueOf(jComboBox4.getSelectedItem());
        String status = String.valueOf(jComboBox2.getSelectedItem());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Full Name or Company Name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (level.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adoption level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_level_reg` INNER JOIN `adoption_animal` ON "
                        + "`adoption_animal`.`id`=`adoption_level_reg`.`adoption_animal_id` WHERE `adopter_id`='" + currentAdopterId + "' ");

                // Ensure the ResultSet has data
                if (!resultSet.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(this, "No data found for the specified adopter", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                while (resultSet.next()) {
                    String amount = resultSet.getString("amount");
                    String animal = resultSet.getString("adoption_animal.animal");
                    String end = resultSet.getString("end_date");

                    String path = "src/com/shadex/zoologix/reports/Adoption_P.T.O.jasper";
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
                    params.put("Parameter11", end);

                    JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, new JREmptyDataSource());

                    JasperViewer.viewReport(jasperPrint, false);
                }

            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       String adopterType = String.valueOf(jComboBox1.getSelectedItem());
        String name = jTextField1.getText();
        String mobile = jTextField2.getText();
        String email = jTextField3.getText();
        String addressLine1 = jTextField4.getText();
        String addressLine2 = jTextField5.getText();
        String city = jTextField5.getText();
        String country = String.valueOf(jComboBox3.getSelectedItem());
        String level = String.valueOf(jComboBox4.getSelectedItem());
        String status = String.valueOf(jComboBox2.getSelectedItem());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (adopterType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Full Name or Company Name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validateContactNumber(mobile, adopterType)) {
            return;

        } else if (addressLine1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Address Line 1 ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (addressLine2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Address Line 1 ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (level.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adoption level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `adopter` WHERE `mobile`='" + mobile + "' AND `email`='" + email + "' ");

                while (resultSet1.next()) {
                    JOptionPane.showMessageDialog(this, "This adopter already exit.Please renew the registration", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                MySQL.executeIUD("INSERT INTO `adopter` (`name`,`mobile`,`email`,`reg_date`,`addressline1`,`addressline2`,"
                        + "`city`,`country_id`,`status_id`,`adopter_type_id`,`adoption_levels_id`) VALUES"
                        + "('" + name + "' , '" + mobile + "' , '" + email + "', '" + formattedDate + "' , '" + addressLine1 + "' , '" + addressLine2 + "' , '" + city + "', '" + countryMap.get(country) + "' , '" + statusMap.get(status) + "' , '" + adopterTypeMap.get(adopterType) + "' , '" + adopterLevelMap.get(level) + "')");
                JOptionPane.showMessageDialog(this, "Successfully Registrated", "Sauccess", JOptionPane.INFORMATION_MESSAGE);
                reset();
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         String adopterType = String.valueOf(jComboBox1.getSelectedItem());
        String name = jTextField1.getText();
        String contact = jTextField2.getText();
        String email = jTextField3.getText();
        String addressLine1 = jTextField4.getText();
        String addressLine2 = jTextField5.getText();
        String city = jTextField6.getText();
        String country = String.valueOf(jComboBox3.getSelectedItem());
        String level = String.valueOf(jComboBox4.getSelectedItem());
        String status = String.valueOf(jComboBox2.getSelectedItem());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (adopterType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Full Name or Company Name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validateContactNumber(contact, adopterType)) {
            return;
        } else if (addressLine1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Address Line 1 ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (addressLine2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Address Line 1 ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (level.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adoption level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                MySQL.executeIUD("UPDATE `adopter` SET `name`='" + name + "' , `mobile`='" + contact + "',`email`='" + email + "',`addressline1`='" + addressLine1 + "',`addressline2`= '" + addressLine2 + "' "
                        + ",`city`='" + city + "',`country_id`='" + countryMap.get(country) + "',`status_id`='" + statusMap.get(status) + "',"
                        + "`adopter_type_id`='" + adopterTypeMap.get(adopterType) + "',`adoption_levels_id`='" + adopterLevelMap.get(level) + "'"
                        + "WHERE `mobile`='" + contact + "' AND `email`='" + email + "'");
                JOptionPane.showMessageDialog(this, "Successfully Updated", "Sauccess", JOptionPane.INFORMATION_MESSAGE);
                reset();
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        reset();
    }//GEN-LAST:event_jButton7ActionPerformed
    //Mobile vaildation
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
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

    public void reset() {

        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        loadAdopters();
    }
}
