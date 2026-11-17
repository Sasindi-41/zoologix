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
public class EnclosureRegistration extends javax.swing.JPanel {

    private Dashboard dashboard;
    private static HashMap<String, String> enclosureTypeMap = new HashMap<>();
    private static HashMap<String, String> securityTypeMap = new HashMap<>();
    private static HashMap<String, String> enclosureStatusMap = new HashMap<>();
    private static HashMap<String, String> maintenanceStatusMap = new HashMap<>();

    public EnclosureRegistration(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        generateMaintenanceID();
        setTableContentCenter();
        setPlaceholder();
        generateEnclosureID();
        loadEnclosures();
        loadMaintenance();
        loadEnclosureStatus();
        loadEnclosureType();
        loadMaintenanceStatus();
        loadSecurityType();
    }

    private void setPlaceholder() {

        jTextField4.putClientProperty("Jcomponent.roundRect", true);
        jTextField4.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enclosure Name here ... ");
        jTextField4.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField4.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

        jTextField5.putClientProperty("Jcomponent.roundRect", true);
        jTextField5.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enclosure Name here ... ");
        jTextField5.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField5.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

    }

    private void setTableContentCenter() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.setDefaultRenderer(Object.class, render);
        jTable2.setDefaultRenderer(Object.class, render);
    }

    private String generateEnclosureID() {
        String newID = "EN001";

        try {
            String query = "SELECT `id` FROM `enclosure` ORDER BY `id` DESC LIMIT 1";
            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                String lastID = resultSet.getString("id");

                String numericPart = lastID.substring(2);
                int lastNumber = Integer.parseInt(numericPart);

                int nextNumber = lastNumber + 1;

                newID = String.format("EN%03d", nextNumber);

                jTextField1.setText(newID);
                jTextField1.setEditable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newID;
    }

    public void setEnvironmentalFactors(String efid) {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `environmental_factors` WHERE `environmental_factors`.`id` = '" + efid + "' ");
            if (rs.next()) {

                jLabel29.setText(rs.getString("id"));
                jLabel7.setText(rs.getString("temperature"));
                jLabel8.setText(rs.getString("humidity"));
                jLabel9.setText(rs.getString("lighting"));
                jLabel10.setText(rs.getString("air_quality"));
                jLabel12.setText(rs.getString("water_quality"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setEnclosureDetails(String id) {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `enclosure` WHERE `enclosure`.`id` = '" + id + "' ");
            if (rs.next()) {
                jLabel15.setText(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setKeaperDetails(String keeperId) {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperId + "' ");
            if (rs.next()) {

                jLabel26.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel25.setText(rs.getString("id"));
                jLabel27.setText(rs.getString("mobile"));
                jLabel28.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setEmployeeDetails(String employeeId) {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + employeeId + "' ");
            if (rs.next()) {

                jLabel22.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel20.setText(rs.getString("id"));
                jLabel21.setText(rs.getString("mobile"));
                jLabel23.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadEnclosureType() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `enclosure_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                enclosureTypeMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSecurityType() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `security_features`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                securityTypeMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEnclosureStatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `enclosure_status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                enclosureStatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMaintenanceStatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `maintenance_status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                maintenanceStatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEnclosures() {
        try {
            String searchQuery = jTextField4.getText().trim().toLowerCase();
            String query = "SELECT * FROM `enclosure` "
                    + "INNER JOIN `enclosure_type` ON `enclosure`.`enclosure_type_id` = `enclosure_type`.`id` "
                    + "INNER JOIN `security_features` ON `enclosure`.`security_features_id` = `security_features`.`id` "
                    + "INNER JOIN `enclosure_status` ON `enclosure`.`enclosure_status_id` = `enclosure_status`.`id` "
                    + "INNER JOIN `environmental_factors` ON `enclosure`.`environmental_factors_id` = `environmental_factors`.`id` "
                    + "INNER JOIN `maintenance_status` ON `enclosure`.`maintenance_status_id` = `maintenance_status`.`id` "
                    + "INNER JOIN `user` ON `enclosure`.`user_id` = `user`.`id` ";
            if (!searchQuery.isEmpty()) {
                query += " WHERE LOWER(enclosure.name) LIKE '%" + searchQuery + "%'";
            }

            String sort = String.valueOf(jComboBox1.getSelectedItem());

            query += "ORDER BY ";

            if (sort.equals("Enclosure ID ASC")) {
                query += "`enclosure`.`id` ASC";
            } else if (sort.equals("Enclosure ID DESC")) {
                query += "`enclosure`.`id` DESC";
            } else if (sort.equals("Enclosure Name ASC")) {
                query += "`enclosure`.`name` ASC";
            } else if (sort.equals("Enclosure Name DESC")) {
                query += "`enclosure`.`name` DESC";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("size"));
                vector.add(resultSet.getString("enclosure_type.type"));
                vector.add(resultSet.getString("security_features.name"));
                vector.add(resultSet.getString("enclosure_status.status"));
                vector.add(resultSet.getString("maintenance_status.status"));
                vector.add(resultSet.getString("user.id"));
                vector.add(resultSet.getString("environmental_factors.id"));

                model.addRow(vector);
            }
            jTable1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMaintenance() {
        try {
            String searchQuery = jTextField5.getText().trim().toLowerCase();
            String query = "SELECT * FROM `maintenance` "
                    + "INNER JOIN `enclosure` ON `maintenance`.`enclosure_id` = `enclosure`.`id` "
                    + "INNER JOIN `user` ON `maintenance`.`user_id` = `user`.`id` ";
            if (!searchQuery.isEmpty()) {
                query += " WHERE LOWER(enclosure.name) LIKE '%" + searchQuery + "%'";
            }

            String sort = String.valueOf(jComboBox6.getSelectedItem());

            query += "ORDER BY ";

            if (sort.equals("Enclosure ID ASC")) {
                query += "`enclosure`.`id` ASC";
            } else if (sort.equals("Enclosure ID DESC")) {
                query += "`enclosure`.`id` DESC";
            } else if (sort.equals("Enclosure Name ASC")) {
                query += "`enclosure`.`name` ASC";
            } else if (sort.equals("Enclosure Name DESC")) {
                query += "`enclosure`.`name` DESC";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("enclosure.id"));
                vector.add(resultSet.getString("enclosure.name"));
                vector.add(resultSet.getString("maintenance_start_date"));
                vector.add(resultSet.getString("maintenance_end_date"));
                vector.add(resultSet.getString("cost"));
                vector.add(resultSet.getString("description"));
                vector.add(resultSet.getString("user.id"));
                vector.add(resultSet.getString("user.fname") + " " + resultSet.getString("user.lname"));

                model.addRow(vector);
            }
            jTable2.setModel(model);

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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton10 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Enclosure Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 450));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Size", "Enclosure Type", "Security Type", "Enclosure Status", "Maintenance Status", "Keeper ID", "Environmental ID"
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

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enclosure ID ASC", "Enclosure ID DESC", "Enclosure Name ASC", "Enclosure Name DESC" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel11.setText("Enclosure Sort By :");

        jTextField4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 700));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Enclosure ID");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Enclosure Name");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Enclosure Size");

        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Enclosure Type");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Security Type");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Enclosure Status");

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setBackground(new java.awt.Color(0, 51, 0));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Environmental Factors");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("TEMPERATURE");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("HUMIDITY");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setText("LIGHTING");

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel10.setText("AIR QUALITY");

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel12.setText("WATER QUALITY");

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Clear All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Maintenance Status");

        jComboBox5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton14.setBackground(new java.awt.Color(0, 51, 0));
        jButton14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Select Keeper");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel24.setText("Keeper");

        jLabel25.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel25.setText("USER ID");

        jLabel26.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel26.setText("FULL NAME");

        jLabel27.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel27.setText("MOBILE");

        jLabel28.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel28.setText("EMAIL");

        jLabel29.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("ID");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel1))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField3)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(25, 25, 25))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Maintenance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Enclosure ID", "Enclosure Name", "Start Date", "End Date", "Cost", "Description", "Employee ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setShowGrid(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel14.setText(" Sort By :");

        jComboBox6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enclosure ID ASC", "Enclosure ID DESC", "Enclosure Name ASC", "Enclosure Name DESC" }));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 51, 0));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jButton9.setBackground(new java.awt.Color(0, 51, 0));
        jButton9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Select Enclosure");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel15.setText("ENCLOSURE ID");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Maintenance Start Date");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Maintenance End Date");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Cost");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea1);

        jButton10.setBackground(new java.awt.Color(0, 51, 0));
        jButton10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Select Employee");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel20.setText("USER ID");

        jLabel21.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel21.setText("MOBILE");

        jLabel22.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel22.setText("FULL NAME");

        jLabel23.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel23.setText("EMAIL");

        jButton11.setBackground(new java.awt.Color(0, 51, 0));
        jButton11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Add");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 51, 0));
        jButton12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Update");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(0, 51, 0));
        jButton13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Clear All");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("MAINTENANCE ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Enclosure", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String enclosureID = jTextField1.getText();
                String enclosureName = jTextField2.getText();
                String enclosureSize = jTextField3.getText();
                String enclosureType = String.valueOf(jComboBox2.getSelectedItem());
                String securityType = String.valueOf(jComboBox3.getSelectedItem());
                String enclosureStatus = String.valueOf(jComboBox4.getSelectedItem());
                String maintenanceStatus = String.valueOf(jComboBox5.getSelectedItem());
                String keeperID = jLabel25.getText();
                String environmentalFactorsID = jLabel29.getText();

                if (enclosureID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Enclosure ID", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (enclosureName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Enclosure Name", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (enclosureSize.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Enclosure Size", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (enclosureType.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Enter Enclosure Type", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (securityType.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Enter Security Type", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (enclosureStatus.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Enter Enclosure Status", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (maintenanceStatus.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Enter Maintenance Status", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (keeperID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Select a keeper", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (environmentalFactorsID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Select Environmental Factors", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean isFound = false;
                    for (int i = 0; i < jTable1.getRowCount(); i++) {

                        String getEnclosureId = String.valueOf(jTable1.getValueAt(i, 0));
                        String getEnclosureName = String.valueOf(jTable1.getValueAt(i, 1));
                        String getSize = String.valueOf(jTable1.getValueAt(i, 2));
                        String getEnclosureType = String.valueOf(jTable1.getValueAt(i, 3));
                        String getSecurityType = String.valueOf(jTable1.getValueAt(i, 4));
                        String getEnclosureStatus = String.valueOf(jTable1.getValueAt(i, 5));
                        String getMaintenanseStatus = String.valueOf(jTable1.getValueAt(i, 6));
                        String getKeeperId = String.valueOf(jTable1.getValueAt(i, 7));
                        String getEnvironmentalFactorsId = String.valueOf(jTable1.getValueAt(i, 8));

                        if (getEnclosureId.equals(enclosureID) && getEnclosureName.equals(enclosureName) && getSize.equals(enclosureSize)
                                && getEnclosureType.equals(enclosureType) && getSecurityType.equals(securityType) && getEnclosureStatus.equals(enclosureStatus)
                                && getMaintenanseStatus.equals(maintenanceStatus) && getKeeperId.equals(keeperID) && getEnvironmentalFactorsId.equals(environmentalFactorsID)) {
                            JOptionPane.showMessageDialog(this, "Enclosure already Added", "warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;
                            break;
                        }

                    }

                    if (!isFound) {
                        MySQL.executeIUD("UPDATE `enclosure` SET `name` = '" + enclosureName + "', `size` = '" + enclosureSize + "',"
                                + " `enclosure_type_id`  = '" + enclosureTypeMap.get(enclosureType) + "', `security_features_id` = '" + securityTypeMap.get(securityType) + "',"
                                + " `enclosure_status_id` = '" + enclosureStatusMap.get(enclosureStatus) + "', `environmental_factors_id` = '" + environmentalFactorsID + "', `maintenance_status_id` = '" + maintenanceStatusMap.get(maintenanceStatus) + "',"
                                + " `user_id` = '" + keeperID + "' WHERE `id` = '" + enclosureID + "' ");
                        loadEnclosures();
                        reset();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String employee = "employee";
        EmployeeView employeeView = new EmployeeView(null, true, employee);
        employeeView.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        String keeper = "Keeper4";
        KeeperView keeperView = new KeeperView(null, true, keeper);
        keeperView.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String enclosure = "enclosure1";
        EnclosureView enclosureView = new EnclosureView(null, true, enclosure);
        enclosureView.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String environmentalFacters = "ef1";
        EnvironmentalFactors environmentalFactersView = new EnvironmentalFactors(null, true, environmentalFacters);
        environmentalFactersView.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadEnclosures();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        loadEnclosures();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton3.setEnabled(false);
        int row = jTable1.getSelectedRow();

        String enclosureId = String.valueOf(jTable1.getValueAt(row, 0));
        jTextField1.setText(enclosureId);

        String enclosureName = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField2.setText(enclosureName);

        String size = String.valueOf(jTable1.getValueAt(row, 2));
        jTextField3.setText(size);

        String enclosureType = String.valueOf(jTable1.getValueAt(row, 3));
        jComboBox2.setSelectedItem(enclosureType);

        String securityType = String.valueOf(jTable1.getValueAt(row, 4));
        jComboBox3.setSelectedItem(securityType);

        String enclosureStatus = String.valueOf(jTable1.getValueAt(row, 5));
        jComboBox4.setSelectedItem(enclosureStatus);

        String maintenanceStatus = String.valueOf(jTable1.getValueAt(row, 6));
        jComboBox5.setSelectedItem(maintenanceStatus);

        String keeperID = String.valueOf(jTable1.getValueAt(row, 7));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperID + "' ");
            if (rs.next()) {

                jLabel26.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel25.setText(rs.getString("id"));
                jLabel27.setText(rs.getString("mobile"));
                jLabel28.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String efID = String.valueOf(jTable1.getValueAt(row, 8));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `environmental_factors` WHERE `environmental_factors`.`id` = '" + efID + "' ");
            if (rs.next()) {

                jLabel29.setText(rs.getString("id"));
                jLabel7.setText(rs.getString("temperature"));
                jLabel8.setText(rs.getString("humidity"));
                jLabel9.setText(rs.getString("lighting"));
                jLabel10.setText(rs.getString("air_quality"));
                jLabel12.setText(rs.getString("water_quality"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String enclosureID = jTextField1.getText();
            String enclosureName = jTextField2.getText();
            String enclosureSize = jTextField3.getText();
            String enclosureType = String.valueOf(jComboBox2.getSelectedItem());
            String securityType = String.valueOf(jComboBox3.getSelectedItem());
            String enclosureStatus = String.valueOf(jComboBox4.getSelectedItem());
            String maintenanceStatus = String.valueOf(jComboBox5.getSelectedItem());
            String keeperID = jLabel25.getText();
            String environmentalFactorsID = jLabel29.getText();

            if (enclosureID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Enclosure ID", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enclosureName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Enclosure Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enclosureSize.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Enclosure Size", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enclosureType.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select Enclosure Type", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (securityType.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Enter Security Type", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enclosureStatus.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Enter Enclosure Status", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (maintenanceStatus.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Enter Maintenance Status", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (keeperID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Select a keeper", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (environmentalFactorsID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Select Environmental Factors", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `enclosure` WHERE `id` = '" + enclosureID + "' ");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "The Enclosure already added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("INSERT INTO `enclosure` (`id`,`name`,`size`,`enclosure_type_id`,`security_features_id`"
                            + ",`enclosure_status_id`,`environmental_factors_id`,`maintenance_status_id`,`user_id`) "
                            + "VALUES('" + enclosureID + "' ,'" + enclosureName + "' ,'" + enclosureSize + "' ,'" + enclosureTypeMap.get(enclosureType) + "' ,'" + securityTypeMap.get(securityType) + "',"
                            + " '" + enclosureStatusMap.get(enclosureStatus) + "','" + environmentalFactorsID + "','" + maintenanceStatusMap.get(maintenanceStatus) + "' ,'" + keeperID + "'  ) ");
                    loadEnclosures();
                    reset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        loadMaintenance();
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        loadMaintenance();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jButton11.setEnabled(false);
        int row = jTable2.getSelectedRow();

        String id = String.valueOf(jTable2.getValueAt(row, 0));
        jLabel30.setText(id);

        String enclosureId = String.valueOf(jTable2.getValueAt(row, 1));
        jLabel15.setText(enclosureId);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable2.getValueAt(row, 3));
            Date msd = sdf.parse(dateString);
            jDateChooser1.setDate(msd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable2.getValueAt(row, 4));
            Date med = sdf.parse(dateString);
            jDateChooser2.setDate(med);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String cost = String.valueOf(jTable2.getValueAt(row, 5));
        jTextField8.setText(cost);

        String description = String.valueOf(jTable2.getValueAt(row, 6));
        jTextArea1.setText(description);

        String keeperID = String.valueOf(jTable2.getValueAt(row, 7));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperID + "' ");
            if (rs.next()) {

                jLabel22.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel20.setText(rs.getString("id"));
                jLabel21.setText(rs.getString("mobile"));
                jLabel23.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            
            String maintenanceId = jLabel30.getText();
            String enclosureID = jLabel15.getText();
            Date msd = jDateChooser1.getDate();
            Date med = jDateChooser2.getDate();
            String cost = jTextField8.getText();
            String description = jTextArea1.getText();
            String userID = jLabel20.getText();

            if (enclosureID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Select Enclosure", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (msd == null) {
                JOptionPane.showMessageDialog(this, "Please Enter Maintenance Start Date ", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (med == null) {
                JOptionPane.showMessageDialog(this, "Please Enter Maintenance end Date", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (cost.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Cost", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!cost.matches("^(?:[1-9][0-9]*|0)(?:\\.[0-9]+)?$")) {
                JOptionPane.showMessageDialog(this, "Please Enter Cost", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Description", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (userID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select Employee", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                MySQL.executeIUD("INSERT INTO `maintenance` (`id`,`maintenance_start_date`,`maintenance_end_date`,`description`,`cost`,`enclosure_id`,`user_id`) "
                        + "VALUES('" + maintenanceId + "' ,'" + sdf.format(msd) + "' ,'" + sdf.format(msd) + "' ,'" + description + "' ,'" + cost + "','" + enclosureID + "',"
                        + "'" + Integer.parseInt(userID) + "'  ) ");
                loadMaintenance();
                reset1();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private String generateMaintenanceID() {
        String newID = "1"; // Default ID

        try {
            // Query to get the last ID from the database
            String query = "SELECT `id` FROM `maintenance` ORDER BY `id` DESC LIMIT 1"; // Change table name as needed
            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                int lastID = resultSet.getInt("id"); // Assuming ID is an integer
                int nextID = lastID + 1; // Increment the last ID
                newID = String.valueOf(nextID); // Convert to String
            }

            // Set the new ID in the JTextField
            jLabel30.setText(newID);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newID;
    }
    
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        reset1();
        generateMaintenanceID();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int row = jTable2.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Enclosure", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String id = jLabel30.getText();
                String enclosureID = jLabel15.getText();
                Date msd = jDateChooser1.getDate();
                Date med = jDateChooser2.getDate();
                String cost = jTextField8.getText();
                String description = jTextArea1.getText();
                String userID = jLabel20.getText();

                if (enclosureID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Select Enclosure", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (msd == null) {
                    JOptionPane.showMessageDialog(this, "Please Enter Maintenance Start Date ", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (med == null) {
                    JOptionPane.showMessageDialog(this, "Please Enter Maintenance end Date", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (cost.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Cost", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Description", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (userID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select Employee", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    boolean isFound = false;
                    String formattedmsd = new SimpleDateFormat("yyyy-MM-dd").format(msd);
                    String formattedmed = new SimpleDateFormat("yyyy-MM-dd").format(med);

                    for (int i = 0; i < jTable2.getRowCount(); i++) {
                        String getID = String.valueOf(jTable2.getValueAt(i, 0));
                        String getEnclosureID = String.valueOf(jTable2.getValueAt(i, 1));
                        String getMSD = String.valueOf(jTable2.getValueAt(i, 2));
                        String getMED = String.valueOf(jTable2.getValueAt(i, 3));
                        String getCost = String.valueOf(jTable2.getValueAt(i, 4));
                        String getDescription = String.valueOf(jTable2.getValueAt(i, 5));
                        String getUserId = String.valueOf(jTable2.getValueAt(i, 6));

                        if (getID.equals(id) && getEnclosureID.equals(enclosureID) && getMSD.equals(formattedmsd) && getMED.equals(formattedmed)
                                && getCost.equals(cost) && getDescription.equals(description) && getUserId.equals(userID)) {
                            JOptionPane.showMessageDialog(this, "Maintenance details already Added", "warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;
                            break;
                        }
                    }

                    if (!isFound) {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        MySQL.executeIUD("UPDATE `maintenance` SET `maintenance_start_date` = '" + sdf.format(msd) + "', `maintenance_end_date` = '" + sdf.format(med) + "',"
                                + " `description`  = '" + description + "', `cost` = '" + cost + "',"
                                + " `enclosure_id` = '" + enclosureID + "', `user_id` = '" + Integer.parseInt(userID) + "' WHERE `id` = '" + id + "' ");
                        loadMaintenance();
                        reset1();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String path = "src/com/shadex/zoologix/reports/EnclosuresReport.jasper";
        HashMap<String, Object> parameters = new HashMap<>();

        try {

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String path = "src/com/shadex/zoologix/reports/EnclosuresMaintenanceReport.jasper";
        HashMap<String, Object> parameters = new HashMap<>();

        try {

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");

        jLabel25.setText("USER ID");
        jLabel26.setText("FULL NAME");
        jLabel27.setText("MOBILE");
        jLabel28.setText("EMAIL");

        jLabel7.setText("TEMPERATURE");
        jLabel8.setText("HUMIDITY");
        jLabel9.setText("LIGHTING");
        jLabel10.setText("AIR QUALITY");
        jLabel12.setText("WATER QUALITY");
        jLabel29.setText("ID");

        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);

        jButton3.setEnabled(true);
        generateEnclosureID();
    }

    private void reset1() {
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);

        jLabel20.setText("USER ID");
        jLabel22.setText("FULL NAME");
        jLabel21.setText("MOBILE");
        jLabel23.setText("EMAIL");

        jLabel15.setText("ENCLOSURE ID");
        jLabel30.setText("MAINTENANCE ID");

        jTextField8.setText("");
        jTextArea1.setText("");

        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);

        jButton11.setEnabled(true);
    }
}
