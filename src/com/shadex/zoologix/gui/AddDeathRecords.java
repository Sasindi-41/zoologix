package com.shadex.zoologix.gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.shadex.zoologix.connection.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DEWMINI
 */
public class AddDeathRecords extends javax.swing.JPanel {

    private Dashboard dashboard;
    private static HashMap<String, String> autopsyperformedMap = new HashMap<>();

    public AddDeathRecords(Dashboard getDashboard) {
        initComponents();
        loadDeathRecords();
        loadAutopsyPerformed();
        setTableContentCenter();
        setPlaceholder();
        this.dashboard = getDashboard;
    }

    private void setPlaceholder() {

        jTextField2.putClientProperty("Jcomponent.roundRect", true);
        jTextField2.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Animal Name here ... ");
        jTextField2.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField2.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

    }

    public void setVeterinaryDetails(String id) {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + id + "' ");
            if (rs.next()) {

                jLabel11.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel10.setText(rs.getString("id"));
                jLabel12.setText(rs.getString("mobile"));
                jLabel13.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAnimalName(String name) {
        jLabel2.setText(name);
    }

    public void setAnimalID(String id) {
        jLabel1.setText(id);
    }

    public void setKeaperDetails(String keeperId) {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperId + "' ");
            if (rs.next()) {

                jLabel7.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel6.setText(rs.getString("id"));
                jLabel8.setText(rs.getString("mobile"));
                jLabel9.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setTableContentCenter() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.setDefaultRenderer(Object.class, render);
    }

    private void loadAutopsyPerformed() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `autopsy_performed`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                autopsyperformedMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDeathRecords() {
        try {
            String searchQuery = jTextField2.getText().trim().toLowerCase();

            String query = "SELECT * FROM `animal_death` "
                    + "INNER JOIN `autopsy_performed` ON `animal_death`.`autopsy_performed_id` = `autopsy_performed`.`id` "
                    + "INNER JOIN `animal` ON `animal_death`.`animal_id` = `animal`.`id` "
                    + "INNER JOIN `user` AS `keeper` ON `animal_death`.`user_id_keeper` = `keeper`.`id` "
                    + "INNER JOIN `user` AS `vet` ON `animal_death`.`user_id_vet` = `vet`.`id` ";

            if (!searchQuery.isEmpty()) {
                query += " WHERE LOWER(animal.name) LIKE '%" + searchQuery + "%'  ";  // Case-insensitive search by animal name
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("animal.id"));
                vector.add(resultSet.getString("animal.name"));
                vector.add(resultSet.getString("death_date"));
                vector.add(resultSet.getString("cause_of_death"));
                vector.add(resultSet.getString("autopsy_performed.name"));
                vector.add(resultSet.getString("keeper.id"));
                vector.add(resultSet.getString("keeper.fname") + " " + resultSet.getString("keeper.lname"));
                vector.add(resultSet.getString("vet.id"));
                vector.add(resultSet.getString("vet.fname") + " " + resultSet.getString("vet.lname"));

                model.addRow(vector);
            }
            jTable1.setModel(model);

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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Death Records", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1190, 800));
        setLayout(new java.awt.BorderLayout());

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Select Animal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel1.setText("ANIMAL ID");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel2.setText("ANIMAL NAME");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Death Date");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Cause of Death");

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Autopsy Performed");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setText("USER ID");

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("FULL NAME");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("MOBILE");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setText("EMAIL");

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Select Veterinary");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel10.setText("USER ID");

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel11.setText("FULL NAME");

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel12.setText("MOBILE");

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel13.setText("EMAIL");

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 51, 0));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Clear All");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel15.setText("Keeper");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(303, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        jTextField2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Animal ID", "Animal Name", "Death Date", "Cause of Death", "Autopsy Performed", "Keeper ID", "Keeper Name", "Veterinary ID", "Veterinary Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton8.setBackground(new java.awt.Color(0, 51, 0));
        jButton8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String veterinary = "Veterinary1";
        VeterinaryView veterinaryView = new VeterinaryView(null, true, veterinary);
        veterinaryView.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String path = "src/com/shadex/zoologix/reports/AnimalDeathReportReport.jasper";
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
        String animal1 = "Animal3";
        AnimalView animalView = new AnimalView(null, true, animal1);
        animalView.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        reset();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton4.setEnabled(false);
        int row = jTable1.getSelectedRow();

        String animalId = String.valueOf(jTable1.getValueAt(row, 0));
        jLabel1.setText(animalId);

        String animalName = String.valueOf(jTable1.getValueAt(row, 1));
        jLabel2.setText(animalName);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable1.getValueAt(row, 2));
            Date deathdate = sdf.parse(dateString);
            jDateChooser1.setDate(deathdate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String causeofdeath = String.valueOf(jTable1.getValueAt(row, 3));
        jTextField1.setText(causeofdeath);

        String autopsyPerformed = String.valueOf(jTable1.getValueAt(row, 4));
        jComboBox1.setSelectedItem(autopsyPerformed);

        String keeperID = String.valueOf(jTable1.getValueAt(row, 5));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperID + "' ");
            if (rs.next()) {

                jLabel7.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel6.setText(rs.getString("id"));
                jLabel8.setText(rs.getString("mobile"));
                jLabel9.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String vetID = String.valueOf(jTable1.getValueAt(row, 7));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + vetID + "' ");
            if (rs.next()) {

                jLabel11.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel10.setText(rs.getString("id"));
                jLabel12.setText(rs.getString("mobile"));
                jLabel13.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        loadDeathRecords();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            String animalID = jLabel1.getText();
            Date deathDate = jDateChooser1.getDate();
            String causeofdeath = jTextField1.getText();
            String autopsyPerformed = String.valueOf(jComboBox1.getSelectedItem());
            String keeperID = jLabel6.getText();
            String vetID = jLabel10.getText();

            if (animalID.equals("ANIMAL ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (deathDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter Death Date", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (causeofdeath.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Cause Of Death", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (autopsyPerformed.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Status", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (vetID.equals("USER ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal_death` WHERE `animal_id` = '" + Integer.valueOf(animalID) + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "The Animal already Dead", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    MySQL.executeIUD("INSERT INTO `animal_death` (`death_date`,`cause_of_death`,`autopsy_performed_id`,`animal_id`,`user_id_keeper`,`user_id_vet`) "
                            + "VALUES('" + sdf.format(deathDate) + "' ,'" + causeofdeath + "' ,'" + autopsyperformedMap.get(autopsyPerformed) + "' ,'" + Integer.valueOf(animalID) + "' ,'" + keeperID + "',"
                            + " '" + vetID + "' ) ");
                    loadDeathRecords();
                    reset();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Animal to Update", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String animalID = jLabel1.getText();
                Date deathDate = jDateChooser1.getDate();
                String causeofdeath = jTextField1.getText();
                String autopsyPerformed = String.valueOf(jComboBox1.getSelectedItem());
                String keeperID = jLabel6.getText();
                String vetID = jLabel10.getText();

                if (animalID.equals("ANIMAL ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (deathDate == null) {
                    JOptionPane.showMessageDialog(this, "Please enter Death Date", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (causeofdeath.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Cause Of Death", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (autopsyPerformed.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Status", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (vetID.equals("USER ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean isFound = false;
                    String formattedDeathDate = new SimpleDateFormat("yyyy-MM-dd").format(deathDate);

                    for (int i = 0; i < jTable1.getRowCount(); i++) {

                        String getAnimalID = String.valueOf(jTable1.getValueAt(i, 0));
                        String getDeathdate = String.valueOf(jTable1.getValueAt(i, 3));
                        String getCauseofDeath = String.valueOf(jTable1.getValueAt(i, 4));
                        String getAutpsperformed = String.valueOf(jTable1.getValueAt(i, 5));
                        String getKeeperID = String.valueOf(jTable1.getValueAt(i, 6));
                        String getVetID = String.valueOf(jTable1.getValueAt(i, 8));

                        if (getAnimalID.equals(animalID) && getDeathdate.equals(formattedDeathDate) && getCauseofDeath.equals(causeofdeath)
                                && getAutpsperformed.equals(autopsyPerformed) && getKeeperID.equals(keeperID) && getVetID.equals(vetID)) {
                            JOptionPane.showMessageDialog(this, "Animal death is already added", "warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;
                            break;
                        }

                    }

                    if (!isFound) {
                        ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal` WHERE `id` = '" + Integer.valueOf(animalID) + "' ");

                        if (rs.next()) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                            MySQL.executeIUD("UPDATE `animal_death` SET `death_date` = '" + sdf.format(deathDate) + "', `cause_of_death` = '" + causeofdeath + "', "
                                    + "`autopsy_performed_id` = '" + autopsyperformedMap.get(autopsyPerformed) + "',"
                                    + "`user_id_keeper` = '" + keeperID + "', `user_id_vet` = '" + vetID + "' WHERE `animal_id` = '" + Integer.valueOf(animalID) + "'");
                            loadDeathRecords();
                            reset();
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");

        jLabel6.setText("USER ID");
        jLabel7.setText("FULL NAME");
        jLabel8.setText("MOBILE");
        jLabel9.setText("EMAIL");
        jLabel10.setText("USER ID");
        jLabel11.setText("FULL NAME");
        jLabel12.setText("MOBILE");
        jLabel13.setText("EMAIL");
        jLabel1.setText("ANIMAL ID");
        jLabel2.setText("ANIMAL NAME");

        jDateChooser1.setDate(null);

        jComboBox1.setSelectedIndex(0);
        jButton4.setEnabled(true);
    }
}
