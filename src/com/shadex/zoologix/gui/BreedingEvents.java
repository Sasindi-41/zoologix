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
public class BreedingEvents extends javax.swing.JPanel {

    private Dashboard dashboard;
    private static HashMap<String, String> pregnancyStatusMap = new HashMap<>();

    public BreedingEvents(Dashboard getDashboard) {
        initComponents();
        generateBreedingEventID();
        setPlaceholder();
        setTableContentCenter();
        loadPrednancyStatus();
        loadBreedingEvents();
        this.dashboard = getDashboard;
    }

    public void setMaleAnimalID(String id) {
        jLabel3.setText(id);
    }

    public void setMaleAnimalName(String name) {
        jLabel16.setText(name);
    }

    public void setFemaleAnimalID(String id) {
        jLabel4.setText(id);
    }

    public void setFemaleAnimalName(String name) {
        jLabel17.setText(name);
    }
    
     public void setKeeperDetails(String id) {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + id + "' ");
            if (rs.next()) {

                jLabel9.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel8.setText(rs.getString("id"));
                jLabel10.setText(rs.getString("mobile"));
                jLabel11.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
      public void setVeterinaryDetails(String id) {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + id + "' ");
            if (rs.next()) {

                jLabel13.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel12.setText(rs.getString("id"));
                jLabel14.setText(rs.getString("mobile"));
                jLabel15.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setAnimalName(String name) {
        jLabel19.setText(name);
    }

    public void setAnimalID(String id) {
        jLabel18.setText(id);
    }

    private void setPlaceholder() {

        jTextField1.putClientProperty("Jcomponent.roundRect", true);
        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Animal Name here ... ");
        jTextField1.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

    }

    private void setTableContentCenter() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.setDefaultRenderer(Object.class, render);
    }

    private void loadPrednancyStatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `pregnancy_status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                pregnancyStatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBreedingEvents() {
        try {
            String searchQuery = jTextField1.getText().trim().toLowerCase();
            String query = "SELECT * FROM `breeding_events` "
                    + "INNER JOIN `pregnancy_status` ON `breeding_events`.`pregnancy_status_id` = `pregnancy_status`.`id` "
                    + "INNER JOIN `animal` AS `animal_male` ON `breeding_events`.`animal_id_male` = `animal_male`.`id` "
                    + "INNER JOIN `animal` AS `animal_female` ON `breeding_events`.`animal_id_female` = `animal_female`.`id` "
                    + "INNER JOIN `user` AS `keeper` ON `breeding_events`.`user_id_keeper` = `keeper`.`id` "
                    + "INNER JOIN `user` AS `vet` ON `breeding_events`.`user_id_vet` = `vet`.`id`";

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("animal_male.id"));
                vector.add(resultSet.getString("animal_female.id"));
                vector.add(resultSet.getString("animal_male.name"));
                vector.add(resultSet.getString("breeding_date"));
                vector.add(resultSet.getString("pregnancy_status.status"));
                vector.add(resultSet.getString("offspring_count"));                
                vector.add(resultSet.getString("keeper.id"));
                vector.add(resultSet.getString("vet.id"));

                model.addRow(vector);
            }
            jTable1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateBreedingEventID() {
        String newID = "B001";

        try {
            String query = "SELECT `id` FROM `breeding_events` ORDER BY `id` DESC LIMIT 1";
            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                String lastID = resultSet.getString("id");

                String numericPart = lastID.substring(1);
                int lastNumber = Integer.parseInt(numericPart);

                int nextNumber = lastNumber + 1;

                newID = String.format("B%03d", nextNumber);

                jTextField2.setText(newID);
                jTextField2.setEditable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newID;
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
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Breeding Events", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1190, 800));
        setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Breeding Event ID");

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Select Male Animal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Select Female Animal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel3.setText("ANIMAL ID");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel4.setText("ANIMAL ID");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Breeding Date");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Pregnancy Status");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Offspring Count");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("USER ID");

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Select Keeper");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setText("FULL NAME");

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel10.setText("MOBILE");

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel11.setText("EMAIL");

        jButton6.setBackground(new java.awt.Color(0, 51, 0));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Select Veterinary");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel12.setText("USER ID");

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel13.setText("FULL NAME");

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel14.setText("MOBILE");

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel15.setText("EMAIL");

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel16.setText("ANIMAL NAME");

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel17.setText("ANIMAL NAME");

        jButton7.setBackground(new java.awt.Color(0, 51, 0));
        jButton7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 51, 0));
        jButton8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Update");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 51, 0));
        jButton9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Clear All");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Breeding Event History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 20))); // NOI18N

        jButton10.setBackground(new java.awt.Color(0, 51, 0));
        jButton10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Select Animal");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel18.setText("ANIMAL ID");

        jLabel19.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel19.setText("ANIMAL NAME");

        jButton11.setBackground(new java.awt.Color(0, 51, 0));
        jButton11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Find");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 51, 0));
        jButton12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Clear All");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(32, 32, 32)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3))))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Breeding Event ID", "Male Animal ID", "Female Animal ID", "Animal Name", "Breeding Date", "Pregnancy Status", "Offspring Count", "Keeper ID", "Veterinary ID"
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

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String path = "src/com/shadex/zoologix/reports/BreedingEventsReport.jasper";
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String femaleanimal = "Femaleanimal";
        FemaleAnimalView femaleanimalView = new FemaleAnimalView(null, true, femaleanimal);
        femaleanimalView.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String veterinary = "Veterinary2";
        VeterinaryView veterinaryView = new VeterinaryView(null, true, veterinary);
        veterinaryView.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String keeper = "Keeper3";
        KeeperView keeperView = new KeeperView(null, true, keeper);
        keeperView.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            String animalId = jLabel18.getText();
            String animalName = jLabel19.getText();

            if (animalId.equals("ANIMAL ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                BreedingEventsHistory breedingEventHistory = new BreedingEventsHistory(null, true, animalId, animalName);
                breedingEventHistory.setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String maleanimal = "Maleanimal";
        MaleAnimalView maleanimalView = new MaleAnimalView(null, true, maleanimal);
        maleanimalView.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        loadBreedingEvents();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        reset();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton7.setEnabled(false);
        int row = jTable1.getSelectedRow();

        String breedingEventID = String.valueOf(jTable1.getValueAt(row, 0));
        jTextField2.setText(breedingEventID);
        jTextField2.setEditable(false);

        String femaleAnimalId = String.valueOf(jTable1.getValueAt(row, 2));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal` WHERE `animal`.`id` = '" + femaleAnimalId + "'");
            if (rs.next()) {
                jLabel4.setText(rs.getString("id"));
                jLabel17.setText(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String maleAnimalId = String.valueOf(jTable1.getValueAt(row, 1));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal` WHERE `animal`.`id` = '" + maleAnimalId + "'");
            if (rs.next()) {
                jLabel3.setText(rs.getString("id"));
                jLabel16.setText(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable1.getValueAt(row, 4));
            Date breedingDate = sdf.parse(dateString);
            jDateChooser1.setDate(breedingDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String pregnancyStatus = String.valueOf(jTable1.getValueAt(row, 5));
        jComboBox2.setSelectedItem(pregnancyStatus);

        String offspringCount = String.valueOf(jTable1.getValueAt(row, 6));
        jTextField3.setText(offspringCount);

        String keeperID = String.valueOf(jTable1.getValueAt(row, 7));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperID + "' ");
            if (rs.next()) {

                jLabel9.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel8.setText(rs.getString("id"));
                jLabel10.setText(rs.getString("mobile"));
                jLabel11.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String vetID = String.valueOf(jTable1.getValueAt(row, 8));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + vetID + "' ");
            if (rs.next()) {

                jLabel13.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel12.setText(rs.getString("id"));
                jLabel14.setText(rs.getString("mobile"));
                jLabel15.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (evt.getClickCount() == 2) {

            int row1 = jTable1.getSelectedRow();
            String breedingEventID1 = String.valueOf(jTable1.getValueAt(row1, 0));

            BreedingEventDetails breedingdetailsView = new BreedingEventDetails(null, true, breedingEventID1);
            breedingdetailsView.setVisible(true);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        reset2();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String animal1 = "Animal4";
        AnimalView animalView = new AnimalView(null, true, animal1);
        animalView.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String breedingID = jTextField2.getText();
            String maleAnimalID = jLabel3.getText();
            String femaleAnimalID = jLabel4.getText();
            String keeperID = jLabel8.getText();
            String vetID = jLabel12.getText();
            Date breedingDate = jDateChooser1.getDate();
            String pregnancyStatus = String.valueOf(jComboBox2.getSelectedItem());
            String offspringCount = jTextField3.getText();

            if (breedingID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Breeding Event ID", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (maleAnimalID.equals("ANIMAL ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (femaleAnimalID.equals("ANIMAL ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (breedingDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter Death Date", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (pregnancyStatus.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Pregnancy Status", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (offspringCount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Offspring Count", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (keeperID.equals("USER ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Keeper", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (vetID.equals("USER ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a Veterinary", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `breeding_events` WHERE `id` = '" + breedingID + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "The Breeding Event Allready Added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    MySQL.executeIUD("INSERT INTO `breeding_events` (`id`,`breeding_date`,`offspring_count`,`pregnancy_status_id`,`animal_id_male` ,`animal_id_female`,`user_id_keeper`,`user_id_vet`) "
                            + "VALUES('" + breedingID + "' ,'" + sdf.format(breedingDate) + "' ,'" + offspringCount + "' ,'" + pregnancyStatusMap.get(pregnancyStatus) + "' ,'" + maleAnimalID + "' ,'" + femaleAnimalID + "', '" + keeperID + "',"
                            + " '" + vetID + "' ) ");
                    loadBreedingEvents();
                    reset();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Breeding Event to Update", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String breedingID = jTextField2.getText();
                String maleAnimalID = jLabel3.getText();
                String femaleAnimalID = jLabel4.getText();
                String keeperID = jLabel8.getText();
                String vetID = jLabel12.getText();
                Date breedingDate = jDateChooser1.getDate();
                String pregnancyStatus = String.valueOf(jComboBox2.getSelectedItem());
                String offspringCount = jTextField3.getText();

                if (breedingID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Breeding Event ID", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (maleAnimalID.equals("ANIMAL ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (femaleAnimalID.equals("ANIMAL ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Animal", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (breedingDate == null) {
                    JOptionPane.showMessageDialog(this, "Please enter Death Date", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (pregnancyStatus.equals("Select")) {
                    JOptionPane.showMessageDialog(this, "Please Select Pregnancy Status", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (offspringCount.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Offspring Count", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (keeperID.equals("USER ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Keeper", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (vetID.equals("USER ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a Veterinary", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean isFound = false;
                    String formattedBreedingDate = new SimpleDateFormat("yyyy-MM-dd").format(breedingDate);

                    for (int i = 0; i < jTable1.getRowCount(); i++) {

                        String getBreedingID = String.valueOf(jTable1.getValueAt(i, 0));
                        String getBreedingDate = String.valueOf(jTable1.getValueAt(i, 4));
                        String getOffspringCount = String.valueOf(jTable1.getValueAt(i, 6));
                        String getPregnancyStatus = String.valueOf(jTable1.getValueAt(i, 5));
                        String getMaleAnimalID = String.valueOf(jTable1.getValueAt(i, 1));
                        String getFemaleAnimalID = String.valueOf(jTable1.getValueAt(i, 2));
                        String getkeeperID= String.valueOf(jTable1.getValueAt(i, 7));
                        String getVetID= String.valueOf(jTable1.getValueAt(i, 8));

                        if (getBreedingID.equals(breedingID) && getBreedingDate.equals(formattedBreedingDate) && getOffspringCount.equals(offspringCount)
                                && getPregnancyStatus.equals(pregnancyStatus) && getMaleAnimalID.equals(maleAnimalID) && getFemaleAnimalID.equals(femaleAnimalID) 
                                && getkeeperID.equals(keeperID) && getVetID.equals(vetID) ) {
                            JOptionPane.showMessageDialog(this, "Breeding Event is already added", "warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;
                            break;
                        }

                    }
                    
                    if (!isFound) {
                        ResultSet rs = MySQL.executeSearch("SELECT * FROM `breeding_events` WHERE `id` = '" + breedingID+ "' ");

                        if (rs.next()) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            MySQL.executeIUD("UPDATE `breeding_events` SET `breeding_date` = '" + sdf.format(breedingDate) + "', `offspring_count` = '" + offspringCount + "', "
                                    + "`pregnancy_status_id` = '" + pregnancyStatusMap.get(pregnancyStatus) + "',"
                                    + "`animal_id_male` = '" + maleAnimalID + "', `animal_id_female` = '" + femaleAnimalID + "', `user_id_keeper` = '" + keeperID + "',"
                                            + " `user_id_vet` = '" + vetID + "' WHERE `id` = '" + breedingID+ "'  ");
                            loadBreedingEvents();
                            reset();
                        } 
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField2.setText("");
        jTextField3.setText("");

        jLabel3.setText("ANIMAL ID");
        jLabel16.setText("ANIMAL NAME");
        jLabel4.setText("ANIMAL ID");
        jLabel17.setText("ANIMAL NAME");

        jLabel8.setText("USER ID");
        jLabel9.setText("FULL NAME");
        jLabel10.setText("MOBILE");
        jLabel11.setText("EMAIL");

        jLabel12.setText("USER ID");
        jLabel13.setText("FULL NAME");
        jLabel14.setText("MOBILE");
        jLabel15.setText("EMAIL");

        jComboBox2.setSelectedIndex(0);

        jDateChooser1.setDate(null);

        jButton7.setEnabled(true);
        generateBreedingEventID();
    }

    private void reset2() {
        jLabel18.setText("ANIMAL ID");
        jLabel19.setText("ANIMAL NAME");
    }
}
