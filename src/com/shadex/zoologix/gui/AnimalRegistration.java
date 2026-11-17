package com.shadex.zoologix.gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.shadex.zoologix.connection.MySQL;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import static java.util.Arrays.sort;
import static java.util.Collections.sort;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Dewmini
 */
public class AnimalRegistration extends javax.swing.JPanel {

    private Dashboard dashboard;
    private static HashMap<String, String> animalGenderMap = new HashMap<>();
    private static HashMap<String, String> nativeregionMap = new HashMap<>();
    private static HashMap<String, String> categoryMap = new HashMap<>();
    private static HashMap<String, String> healthstatusMap = new HashMap<>();
    private static HashMap<String, String> enclosureMap = new HashMap<>();
    private static HashMap<String, String> iucncategoryMap = new HashMap<>();

    public AnimalRegistration(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        generateAnimalID();
        loadGender();
        loadNativeRegion();
        loadCategory();
        loadHealthStatus();
        loadIUCNCategory();
        loadEnclosure();
        loadAnimals();
        setPlaceholder();
        setTableContentCenter();
        jButton7.setEnabled(false);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jTextField3.requestFocusInWindow();
            }
        });
    }

    private void setPlaceholder() {

        jTextField1.putClientProperty("Jcomponent.roundRect", true);
        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Animal Name here ... ");
        jTextField1.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

    }

    public void setKeepername(String name) {
        jLabel17.setText(name);
    }

    public void setKeeperID(String id) {
        jLabel16.setText(id);
    }

    public void setKeeperMobile(String mobile) {
        jLabel18.setText(mobile);
    }

    public void setKeeperEmail(String email) {
        jLabel19.setText(email);
    }

    public void setBreedingEventId(String bid) {
        jLabel21.setText(bid);
    }

//    placeHolders ------------------- Dewmini
    private void loadGender() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                animalGenderMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    placeHolders ------------------- Dewmini

//    placeHolders ------------------- Dewmini
    private void loadNativeRegion() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `native_region`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                nativeregionMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    placeHolders ------------------- Dewmini

//    placeHolders ------------------- Dewmini
    private void loadCategory() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `animal_category`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("category"));
                categoryMap.put(resultSet.getString("category"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox6.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    placeHolders ------------------- Dewmini

//    placeHolders ------------------- Dewmini
    private void loadHealthStatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `health_status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                healthstatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    placeHolders ------------------- Dewmini

//    placeHolders ------------------- Dewmini
    private void loadIUCNCategory() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `iucn_category`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("category"));
                iucncategoryMap.put(resultSet.getString("category"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEnclosure() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `enclosure` "
                    + "INNER JOIN `maintenance_status` ON `enclosure`.`maintenance_status_id` = `maintenance_status`.`id` WHERE `maintenance_status_id` = 2");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                enclosureMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateAnimalID() {
        String newID = "1"; // Default ID

        try {
            // Query to get the last ID from the database
            String query = "SELECT `id` FROM `animal` ORDER BY `id` DESC LIMIT 1"; // Change table name as needed
            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                int lastID = resultSet.getInt("id"); // Assuming ID is an integer
                int nextID = lastID + 1; // Increment the last ID
                newID = String.valueOf(nextID); // Convert to String
            }

            // Set the new ID in the JTextField
            jTextField2.setText(newID);
            jTextField2.setEditable(false); // Make it read-only

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newID;
    }

//    placeHolders ------------------- Dewmini 
//    placeHolders ------------------- Dewmini 
    private void loadAnimals() {
        try {
            String searchQuery = jTextField1.getText().trim().toLowerCase();
            String query = "SELECT * FROM `animal` "
                    + "INNER JOIN `enclosure` ON `animal`.`enclosure_id` = `enclosure`.`id` "
                    + "INNER JOIN `health_status` ON `animal`.`health_status_id` = `health_status`.`id` "
                    + "INNER JOIN `gender` ON `animal`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `native_region` ON `animal`.`native_region_id` = `native_region`.`id` "
                    + "INNER JOIN `animal_category` ON `animal`.`animal_category_id` = `animal_category`.`id` "
                    + "INNER JOIN `iucn_category` ON `animal`.`iucn_category_id` = `iucn_category`.`id` "
                    + "INNER JOIN `user` ON `animal`.`user_id` = `user`.`id` ";
            if (!searchQuery.isEmpty()) {
                query += " WHERE LOWER(animal.name) LIKE '%" + searchQuery + "%'  ";  // Case-insensitive search by animal name
            }

            String sort = String.valueOf(jComboBoxsort.getSelectedItem());

            query += "ORDER BY ";

            if (sort.equals("Animal ID ASC")) {
                query += "`animal`.`id` ASC";
            } else if (sort.equals("Animal ID DESC")) {
                query += "`animal`.`id` DESC";
            } else if (sort.equals("Animal Name ASC")) {
                query += "`animal`.`name` ASC";
            } else if (sort.equals("Animal Name DESC")) {
                query += "`animal`.`name` DESC";
            } else if (sort.equals("Gender ASC")) {
                query += "`gender`.`name` ASC";
            } else if (sort.equals("Gender DESC")) {
                query += "`gender`.`name` DESC";
            } else if (sort.equals("Category ASC")) {
                query += "`animal_category`.`id` ASC";
            } else if (sort.equals("Category DESC")) {
                query += "`animal_category`.`id` DESC";
            } else if (sort.equals("IUCN Category ASC")) {
                query += "`iucn_category`.`id` ASC";
            } else if (sort.equals("IUCN Category DESC")) {
                query += "`iucn_category`.`id` DESC";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("scientific_name"));
                vector.add(resultSet.getString("arrived_date"));
                vector.add(resultSet.getString("date_of_birth"));
                vector.add(resultSet.getString("age"));
                vector.add(resultSet.getString("animal_count"));
                vector.add(resultSet.getString("description"));
                vector.add(resultSet.getString("health_status.status"));
                vector.add(resultSet.getString("gender.name"));
                vector.add(resultSet.getString("native_region.name"));
                vector.add(resultSet.getString("animal_category.category"));
                vector.add(resultSet.getString("iucn_category.category"));
                vector.add(resultSet.getString("enclosure.name"));
                vector.add(resultSet.getString("user.id"));

                model.addRow(vector);
            }
            jTable1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableContentCenter() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.setDefaultRenderer(Object.class, render);
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
        jLabel1 = new javax.swing.JLabel();
        jComboBoxsort = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Animal Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Animal ID", "Name", "Scientific Name", "Arrived Date", "Date of Birth", "Age", "Animal Count", "Description", "Health Status", "Gender", "Native Region", "Category", "IUCN Category", "Enclosure Name", "Keeper ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel1.setText("Animal Sort By :");

        jComboBoxsort.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBoxsort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Animal ID ASC", "Animal ID DESC", "Animal Name ASC", "Animal Name DESC", "Gender ASC", "Gender DESC", "Category ASC", "Category DESC", "IUCN Category ASC", "IUCN Category DESC", " ", " " }));
        jComboBoxsort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxsortItemStateChanged(evt);
            }
        });
        jComboBoxsort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxsortActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxsort, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxsort, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(420, 800));

        jPanel2.setPreferredSize(new java.awt.Dimension(420, 1015));

        jLabel2.setText("Animal ID");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Scientific Name");

        jTextField2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Arrived Date");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Date of Birth");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Age");

        jTextField7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Animal Count");

        jTextField8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Health Status");

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Gender");

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Native Region");

        jComboBox5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Category");

        jComboBox6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel15.setText("Keeper");

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Select Keeper");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel16.setText("USER ID");

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel17.setText("FULL NAME");

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel18.setText("MOBILE");

        jLabel19.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel19.setText("EMAIL");

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

        jDateChooser1.setDateFormatString("YYYY-MM-dd");
        jDateChooser1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateChooser1FocusGained(evt);
            }
        });
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
        });
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyReleased(evt);
            }
        });

        jDateChooser2.setDateFormatString("YYYY-MM-dd");
        jDateChooser2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateChooser2FocusGained(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("IUCN Category");

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Enclosure");

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton7.setBackground(new java.awt.Color(0, 51, 0));
        jButton7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Select Breeding Event");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("BREEDING EVENT ID");

        jCheckBox1.setText("Offspring Born Animal");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox6, 0, 275, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(20, 20, 20))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String keeper = "Keeper1";
        KeeperView keeperView = new KeeperView(null, true, keeper);
        keeperView.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        reset();
        generateAnimalID();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton4.setEnabled(false);
        int row = jTable1.getSelectedRow();

        String animalId = String.valueOf(jTable1.getValueAt(row, 0));
        jTextField2.setText(animalId);
        jTextField2.setEditable(false);

        String name = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField3.setText(name);

        String scientificName = String.valueOf(jTable1.getValueAt(row, 2));
        jTextField4.setText(scientificName);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable1.getValueAt(row, 3));
            Date arrivedDate = sdf.parse(dateString);
            jDateChooser1.setDate(arrivedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable1.getValueAt(row, 4));
            Date dateofbirth = sdf.parse(dateString);
            jDateChooser2.setDate(dateofbirth);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String age = String.valueOf(jTable1.getValueAt(row, 5));
        jTextField7.setText(age);

        String animalcount = String.valueOf(jTable1.getValueAt(row, 6));
        jTextField8.setText(animalcount);

        String description = String.valueOf(jTable1.getValueAt(row, 7));
        jTextArea1.setText(description);

        String healthstatus = String.valueOf(jTable1.getValueAt(row, 8));
        jComboBox3.setSelectedItem(healthstatus);

        String gender = String.valueOf(jTable1.getValueAt(row, 9));
        jComboBox4.setSelectedItem(gender);

        String nativeregion = String.valueOf(jTable1.getValueAt(row, 10));
        jComboBox5.setSelectedItem(nativeregion);

        String category = String.valueOf(jTable1.getValueAt(row, 11));
        jComboBox6.setSelectedItem(category);

        String iucncategory = String.valueOf(jTable1.getValueAt(row, 12));
        jComboBox2.setSelectedItem(iucncategory);

        String enclosureName = String.valueOf(jTable1.getValueAt(row, 13));
        jComboBox1.setSelectedItem(enclosureName);

        String keeperID = String.valueOf(jTable1.getValueAt(row, 14));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperID + "' ");
            if (rs.next()) {

                jLabel17.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel16.setText(rs.getString("id"));
                jLabel18.setText(rs.getString("mobile"));
                jLabel19.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal` INNER JOIN `breeding_events` ON `animal`.`breeding_events_id` = `breeding_events`.`id` WHERE `animal`.`id` = '" + animalId + "' ");
            if (rs.next()) {
                if (rs.getString("animal.breeding_events_id") != null) {
                    jCheckBox1.setSelected(true);
                    jLabel21.setText(rs.getString("animal.breeding_events_id"));
                } else {
                    jCheckBox1.setSelected(false);
                    jLabel21.setText("BREEDING EVENT ID");
                }
            } else {
                jCheckBox1.setSelected(false);
                jLabel21.setText("BREEDING EVENT ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (evt.getClickCount() == 2) {

            int row1 = jTable1.getSelectedRow();
            String animalId1 = String.valueOf(jTable1.getValueAt(row1, 0));

            SingleAnimalView singleanimalview = new SingleAnimalView(null, true, animalId1);
            singleanimalview.setVisible(true);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBoxsortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxsortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxsortActionPerformed

    private void jComboBoxsortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxsortItemStateChanged
        loadAnimals();
    }//GEN-LAST:event_jComboBoxsortItemStateChanged

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        loadAnimals();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            String breedingEventId = jLabel21.getText();
            String animalId = jTextField2.getText();
            String name = jTextField3.getText();
            String scientificName = jTextField4.getText();
            Date arrivedDate = jDateChooser1.getDate();
            Date dateofBirth = jDateChooser2.getDate();
            String age = jTextField7.getText();
            String animalCount = jTextField8.getText();
            String description = jTextArea1.getText();
            String healthStatus = String.valueOf(jComboBox3.getSelectedItem());
            String gender = String.valueOf(jComboBox4.getSelectedItem());
            String nativeRegion = String.valueOf(jComboBox5.getSelectedItem());
            String category = String.valueOf(jComboBox6.getSelectedItem());
            String iucnCategory = String.valueOf(jComboBox2.getSelectedItem());
            String enclosureName = String.valueOf(jComboBox1.getSelectedItem());
            String keeperID = jLabel16.getText();
            String keeperName = jLabel17.getText();
            String keeperMobile = jLabel18.getText();
            String keeperEmail = jLabel19.getText();

            if (jCheckBox1.isSelected() && breedingEventId.equals("BREEDING EVENT ID")) {
                JOptionPane.showMessageDialog(this, "Please Select Breeding Event", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Animal Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (scientificName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Scientific Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (arrivedDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter Arrived Date", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (dateofBirth == null) {
                JOptionPane.showMessageDialog(this, "Please enter Animal date of Birth", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (age.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Age", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!age.matches("^[1-9][0-9]*$")) {
                JOptionPane.showMessageDialog(this, "Please Valid Age", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (animalCount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Animal Count", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!animalCount.matches("^[1-9][0-9]*$")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Animal Count", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Description", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (healthStatus.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Health Status", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (gender.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Gender", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (nativeRegion.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Native Region", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (category.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Category", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (iucnCategory.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a IUCN Category", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enclosureName.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Enclosure", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (keeperID.equals("USER ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Keeper", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal` WHERE `id` = '" + animalId + "' ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "The Animal already registered", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (jCheckBox1.isSelected()) {

                    MySQL.executeIUD("INSERT INTO `animal` (`id`,`name`,`scientific_name`,`arrived_date`,`date_of_birth`,`age`,`animal_count`,`description` "
                            + ",`animal_category_id` ,`gender_id` ,`enclosure_id` ,`health_status_id` ,`user_id` ,`native_region_id` ,`iucn_category_id`,`breeding_events_id`) "
                            + "VALUES('" + Integer.parseInt(animalId) + "' ,'" + name + "' ,'" + scientificName + "' ,'" + sdf.format(arrivedDate) + "' ,'" + sdf.format(dateofBirth) + "',"
                            + " '" + Integer.parseInt(age) + "' ,'" + Integer.parseInt(animalCount) + "' ,'" + description + "' ,'" + categoryMap.get(category) + "', '" + animalGenderMap.get(gender) + "',"
                            + " '" + enclosureMap.get(enclosureName) + "' ,'" + healthstatusMap.get(healthStatus) + "','" + keeperID + "','" + nativeregionMap.get(nativeRegion) + "',"
                            + "'" + iucncategoryMap.get(iucnCategory) + "','" + breedingEventId + "'  ) ");
                    loadAnimals();
                    reset();
                } else {
                    MySQL.executeIUD("INSERT INTO `animal` (`id`,`name`,`scientific_name`,`arrived_date`,`date_of_birth`,`age`,`animal_count`,`description` "
                            + ",`animal_category_id` ,`gender_id` ,`enclosure_id` ,`health_status_id` ,`user_id` ,`native_region_id` ,`iucn_category_id`) "
                            + "VALUES('" + Integer.parseInt(animalId) + "' ,'" + name + "' ,'" + scientificName + "' ,'" + sdf.format(arrivedDate) + "' ,'" + sdf.format(dateofBirth) + "',"
                            + " '" + Integer.parseInt(age) + "' ,'" + Integer.parseInt(animalCount) + "' ,'" + description + "' ,'" + categoryMap.get(category) + "', '" + animalGenderMap.get(gender) + "',"
                            + " '" + enclosureMap.get(enclosureName) + "' ,'" + healthstatusMap.get(healthStatus) + "','" + keeperID + "','" + nativeregionMap.get(nativeRegion) + "','" + iucncategoryMap.get(iucnCategory) + "' ) ");
                    loadAnimals();
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
                String breedingEventId = jLabel21.getText();
                String animalId = jTextField2.getText();
                String name = jTextField3.getText();
                String scientificName = jTextField4.getText();
                Date arrivedDate = jDateChooser1.getDate();
                Date dateofBirth = jDateChooser2.getDate();
                String age = jTextField7.getText();
                String animalCount = jTextField8.getText();
                String description = jTextArea1.getText();
                String healthStatus = String.valueOf(jComboBox3.getSelectedItem());
                String gender = String.valueOf(jComboBox4.getSelectedItem());
                String nativeRegion = String.valueOf(jComboBox5.getSelectedItem());
                String category = String.valueOf(jComboBox6.getSelectedItem());
                String iucnCategory = String.valueOf(jComboBox2.getSelectedItem());
                String enclosureName = String.valueOf(jComboBox1.getSelectedItem());
                String keeperID = jLabel16.getText();
                String keeperName = jLabel17.getText();
                String keeperMobile = jLabel18.getText();
                String keeperEmail = jLabel19.getText();

                if (jCheckBox1.isSelected() && breedingEventId.equals("BREEDING EVENT ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select Breeding Event", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Animal Name", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (scientificName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Scientific Name", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (arrivedDate == null) {
                    JOptionPane.showMessageDialog(this, "Please enter Arrived Date", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (dateofBirth == null) {
                    JOptionPane.showMessageDialog(this, "Please enter Animal date of Birth", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (age.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Age", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!age.matches("^[1-9][0-9]*$")) {
                    JOptionPane.showMessageDialog(this, "Please Valid Age", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (animalCount.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Animal Count", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!animalCount.matches("^[1-9][0-9]*$")) {
                    JOptionPane.showMessageDialog(this, "Please Enter Valid Animal Count", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Description", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (healthStatus.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Health Status", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (gender.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Gender", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (nativeRegion.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Native Region", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (category.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Category", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (iucnCategory.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select a IUCN Category", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (enclosureName.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Enclosure", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (keeperID.equals("USER ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Keeper", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean isFound = false;
                    String formattedArrivedDate = new SimpleDateFormat("yyyy-MM-dd").format(arrivedDate);
                    String formattedDateOfBirth = new SimpleDateFormat("yyyy-MM-dd").format(dateofBirth);

                    for (int i = 0; i < jTable1.getRowCount(); i++) {

                        String getAnimalID = String.valueOf(jTable1.getValueAt(i, 0));
                        String getAnimalName = String.valueOf(jTable1.getValueAt(i, 1));
                        String getScientificName = String.valueOf(jTable1.getValueAt(i, 2));
                        String getArrivedDate = String.valueOf(jTable1.getValueAt(i, 3));
                        String getDateofBirth = String.valueOf(jTable1.getValueAt(i, 4));
                        String getAge = String.valueOf(jTable1.getValueAt(i, 5));
                        String getAnimalCount = String.valueOf(jTable1.getValueAt(i, 6));
                        String getDescription = String.valueOf(jTable1.getValueAt(i, 7));
                        String getHealthStatus = String.valueOf(jTable1.getValueAt(i, 8));
                        String getGender = String.valueOf(jTable1.getValueAt(i, 9));
                        String getNativeRegion = String.valueOf(jTable1.getValueAt(i, 10));
                        String getCategory = String.valueOf(jTable1.getValueAt(i, 11));
                        String getIUCNCategory = String.valueOf(jTable1.getValueAt(i, 12));
                        String getEnclosure = String.valueOf(jTable1.getValueAt(i, 13));
                        String getKeeperID = String.valueOf(jTable1.getValueAt(i, 14));

                        if (getAnimalID.equals(animalId) && getAnimalName.equals(name) && getScientificName.equals(scientificName)
                                && getArrivedDate.equals(formattedArrivedDate) && getDateofBirth.equals(formattedDateOfBirth) && getAge.equals(age)
                                && getAnimalCount.equals(animalCount) && getDescription.equals(description) && getHealthStatus.equals(healthStatus)
                                && getGender.equals(gender) && getNativeRegion.equals(nativeRegion) && getCategory.equals(category)
                                && getIUCNCategory.equals(iucnCategory) && getEnclosure.equals(enclosureName) && getKeeperID.equals(keeperID)) {
                            JOptionPane.showMessageDialog(this, "Animal already Added", "warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;
                            break;
                        }

                    }

                    if (!isFound) {
                        String breedingEventIdValue = jCheckBox1.isSelected() ? breedingEventId : "NULL";
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (jCheckBox1.isSelected()) {

                            MySQL.executeIUD("UPDATE `animal` SET `name` = '" + name + "', `scientific_name` = '" + scientificName + "',"
                                    + " `arrived_date`  = '" + sdf.format(arrivedDate) + "', `date_of_birth` = '" + sdf.format(dateofBirth) + "',"
                                    + " `age` = '" + Integer.parseInt(age) + "', `animal_count` = '" + Integer.parseInt(animalCount) + "', `description` = '" + description + "',"
                                    + " `animal_category_id` = '" + categoryMap.get(category) + "' , `gender_id` = '" + animalGenderMap.get(gender) + "' ,"
                                    + " `enclosure_id` = '" + enclosureMap.get(enclosureName) + "' , `health_status_id` = '" + healthstatusMap.get(healthStatus) + "' ,"
                                    + " `user_id` = '" + keeperID + "', `native_region_id` = '" + nativeregionMap.get(nativeRegion) + "' ,"
                                    + " `iucn_category_id` = '" + iucncategoryMap.get(iucnCategory) + "', `breeding_events_id` = '" + breedingEventId + "' WHERE `id` = '" + Integer.parseInt(animalId) + "'");
                            loadAnimals();
                            reset();
                        } else {
                            MySQL.executeIUD("UPDATE `animal` SET `name` = '" + name + "', `scientific_name` = '" + scientificName + "',"
                                    + " `arrived_date`  = '" + sdf.format(arrivedDate) + "', `date_of_birth` = '" + sdf.format(dateofBirth) + "',"
                                    + " `age` = '" + Integer.parseInt(age) + "', `animal_count` = '" + Integer.parseInt(animalCount) + "', `description` = '" + description + "',"
                                    + " `animal_category_id` = '" + categoryMap.get(category) + "' , `gender_id` = '" + animalGenderMap.get(gender) + "' ,"
                                    + " `enclosure_id` = '" + enclosureMap.get(enclosureName) + "' , `health_status_id` = '" + healthstatusMap.get(healthStatus) + "' ,"
                                    + " `user_id` = '" + keeperID + "', `native_region_id` = '" + nativeregionMap.get(nativeRegion) + "' ,"
                                    + " `iucn_category_id` = '" + iucncategoryMap.get(iucnCategory) + "', `breeding_events_id` = NULL WHERE `id` = '" + Integer.parseInt(animalId) + "' ");
                            loadAnimals();
                            reset();

                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { // For example, using Enter key
            jTextField4.grabFocus(); // Request focus for jDateChooser2
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jDateChooser1.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jDateChooser2.requestFocusInWindow();
        }
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked

    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jDateChooser1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jDateChooser2.requestFocusInWindow();
        }
    }//GEN-LAST:event_jDateChooser1KeyReleased

    private void jDateChooser2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooser2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2FocusGained

    private void jDateChooser1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooser1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1FocusGained

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String breedingeventview = "BreedingEvent";
        BreedingEventView breedingEventView = new BreedingEventView(null, true, breedingeventview);
        breedingEventView.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if (jCheckBox1.isSelected()) {
            jButton7.setEnabled(true);
        } else {
            jButton7.setEnabled(false);
            jLabel21.setText("BREEDING EVENT ID");
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBoxsort;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextArea1.setText("");

        jLabel16.setText("USER ID");
        jLabel17.setText("FULL NAME");
        jLabel18.setText("MOBILE");
        jLabel19.setText("EMAIL");
        jLabel21.setText("BREEDING EVENT ID");

        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);

        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox1.setSelectedIndex(0);

        jButton4.setEnabled(true);
        jCheckBox1.setSelected(false);
        jTextField3.grabFocus();
        generateAnimalID();
    }
}
