package com.shadex.zoologix.gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.shadex.zoologix.connection.MySQL;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DEWMINI
 */
public class AddExitandReturnRecords extends javax.swing.JPanel {

    private Dashboard dashboard;
    private String animalId;

    public AddExitandReturnRecords(Dashboard getDashboard) {
        initComponents();
        loadExitRecords();
        loadReturnRecords();
        this.dashboard = getDashboard;
        setTableContentCenter();
        setTableContentCenter2();
        setPlaceholder();
        setPlaceholder2();
    }

    private void setPlaceholder() {

        jTextField4.putClientProperty("Jcomponent.roundRect", true);
        jTextField4.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Animal Name here ... ");
        jTextField4.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField4.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

    }

    private void setPlaceholder2() {

        jTextField5.putClientProperty("Jcomponent.roundRect", true);
        jTextField5.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Animal Name here ... ");
        jTextField5.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField5.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

    }

    public void setAnimalName(String name) {
        jLabel2.setText(name);
    }

    public void setAnimalID(String id) {
        jLabel1.setText(id);
    }

    public void setAnimalName2(String name) {
        jLabel14.setText(name);
    }

    public void setAnimalID2(String id) {
        jLabel13.setText(id);
    }

    public void setKeaperDetails(String keeperId) {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperId + "' ");
            if (rs.next()) {

                jLabel8.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel7.setText(rs.getString("id"));
                jLabel9.setText(rs.getString("mobile"));
                jLabel10.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setKeaperDetails2(String keeperId) {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperId + "' ");
            if (rs.next()) {

                jLabel17.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel20.setText(rs.getString("id"));
                jLabel21.setText(rs.getString("mobile"));
                jLabel18.setText(rs.getString("email"));

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

    private void setTableContentCenter2() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable2.setDefaultRenderer(Object.class, render);
    }

    private void loadExitRecords() {
        try {
            String searchQuery = jTextField4.getText().trim().toLowerCase();

            String query = "SELECT * FROM `animal_exit` "
                    + "INNER JOIN `animal` ON `animal_exit`.`animal_id` = `animal`.`id` "
                    + "INNER JOIN `user` ON `animal_exit`.`user_id` = `user`.`id`";

            if (!searchQuery.isEmpty()) {
                query += " WHERE LOWER(animal.name) LIKE '%" + searchQuery + "%'";  // Case-insensitive search by animal name
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("animal.id"));
                vector.add(resultSet.getString("animal.name"));
                vector.add(resultSet.getString("exit_date"));
                vector.add(resultSet.getString("exit_location"));
                vector.add(resultSet.getString("exit_reason"));
                vector.add(resultSet.getString("user.id"));
                vector.add(resultSet.getString("user.fname") + " " + resultSet.getString("user.lname"));

                model.addRow(vector);
            }
            jTable2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadReturnRecords() {
        try {
            String searchQuery = jTextField5.getText().trim().toLowerCase();

            String query = "SELECT * FROM `animal_return` "
                    + "INNER JOIN `animal` ON `animal_return`.`animal_id` = `animal`.`id` "
                    + "INNER JOIN `user` ON `animal_return`.`user_id` = `user`.`id`";

            if (!searchQuery.isEmpty()) {
                query += " WHERE LOWER(animal.name) LIKE '%" + searchQuery + "%' ";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("animal.id"));
                vector.add(resultSet.getString("animal.name"));
                vector.add(resultSet.getString("return_date"));
                vector.add(resultSet.getString("return_reason"));
                vector.add(resultSet.getString("user.id"));
                vector.add(resultSet.getString("user.fname") + " " + resultSet.getString("user.lname"));

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
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField5 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1190, 800));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Exit Records", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 400));

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
        jLabel3.setText("Exit Date");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Exit Location");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Exit Reason");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        jLabel6.setText("Keeper");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("USER ID");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("FULL NAME");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setText("MOBILE");

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel10.setText("EMAIL");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 71, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jTable2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Animal ID", "Animal Name", "Exit Date", "Exit Location", "Exit Reason", "Keeper ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(jTable2);

        jButton6.setBackground(new java.awt.Color(0, 51, 0));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Return Records", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N

        jButton10.setBackground(new java.awt.Color(0, 51, 0));
        jButton10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Select Animal");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel13.setText("ANIMAL ID");

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel14.setText("ANIMAL NAME");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Return Date");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Return Reason");

        jButton12.setBackground(new java.awt.Color(0, 51, 0));
        jButton12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Clear All");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(0, 51, 0));
        jButton13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Update");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel17.setText("FULL NAME");

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel18.setText("EMAIL");

        jLabel19.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        jLabel19.setText("Keeper");

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel20.setText("USER ID");

        jLabel21.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel21.setText("MOBILE");

        jButton14.setBackground(new java.awt.Color(0, 51, 0));
        jButton14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Add");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Animal ID", "Animal Name", "Return Date", "Return Reason", "Keeper ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jTextField5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 51, 0));
        jButton9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Report");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String animal1 = "Animal1";
        AnimalView animalView = new AnimalView(null, true, animal1);
        animalView.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            String animalID = jLabel1.getText();
            Date exitDate = jDateChooser1.getDate();
            String exitLocation = jTextField2.getText();
            String exitReason = jTextField3.getText();
            String keeperID = jLabel7.getText();

            if (animalID.equals("ANIMAL ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a animal", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (exitDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter Exit Date", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (exitLocation.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Exit Location", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (exitReason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Exit Reason", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (keeperID.equals("USER ID")) {
                JOptionPane.showMessageDialog(this, "Please select a keeper", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                boolean isFound = false;
                String formattedExitDate = new SimpleDateFormat("yyyy-MM-dd").format(exitDate);

                for (int i = 0; i < jTable2.getRowCount(); i++) {

                    String getanimalID = String.valueOf(jTable2.getValueAt(i, 1));
                    String getexitDate = String.valueOf(jTable2.getValueAt(i, 3));
                    String getexitLocation = String.valueOf(jTable2.getValueAt(i, 4));
                    String getexitReason = String.valueOf(jTable2.getValueAt(i, 5));
                    String getkeeperID = String.valueOf(jTable2.getValueAt(i, 6));

                    if (getanimalID.equals(animalID) && getexitDate.equals(formattedExitDate) && getexitLocation.equals(exitLocation)
                            && getexitReason.equals(exitReason) && getkeeperID.equals(keeperID)) {
                        JOptionPane.showMessageDialog(this, "This exit details are already added", "warning", JOptionPane.WARNING_MESSAGE);
                        isFound = true;
                        break;
                    }

                }

                if (!isFound) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `animal_exit` WHERE `exit_date` = '" + sdf.format(exitDate) + "' AND `animal_id` = '" + Integer.valueOf(animalID) + "' ");

                    if (rs1.next()) {
                        JOptionPane.showMessageDialog(this, "Date Already used", "warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        MySQL.executeIUD("INSERT INTO `animal_exit` (`exit_date` , `exit_reason` , `exit_location` , `animal_id`, `user_id`) "
                                + "VALUES ('" + sdf.format(exitDate) + "' , '" + exitReason + "' , '" + exitLocation + "' , '" + Integer.valueOf(animalID) + "', '" + keeperID + "') ");

                        loadExitRecords();
                        reset();
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = jTable2.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Animal to Update", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String id = String.valueOf(jTable2.getValueAt(row, 0));
            try {

                String animalID = jLabel1.getText();
                Date exitDate = jDateChooser1.getDate();
                String exitLocation = jTextField2.getText();
                String exitReason = jTextField3.getText();
                String keeperID = jLabel7.getText();

                if (animalID.equals("ANIMAL ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a animal", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (exitDate == null) {
                    JOptionPane.showMessageDialog(this, "Please enter Exit Date", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (exitLocation.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter Exit Location", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (exitReason.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter Exit Reason", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (keeperID.equals("USER ID")) {
                    JOptionPane.showMessageDialog(this, "Please select a keeper", "warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    boolean isFound = false;
                    String formattedExitDate = new SimpleDateFormat("yyyy-MM-dd").format(exitDate);

                    for (int i = 0; i < jTable2.getRowCount(); i++) {

                        String getanimalID = String.valueOf(jTable2.getValueAt(i, 1));
                        String getexitDate = String.valueOf(jTable2.getValueAt(i, 3));
                        String getexitLocation = String.valueOf(jTable2.getValueAt(i, 4));
                        String getexitReason = String.valueOf(jTable2.getValueAt(i, 5));
                        String getkeeperID = String.valueOf(jTable2.getValueAt(i, 6));

                        if (getanimalID.equals(animalID) && getexitDate.equals(formattedExitDate) && getexitLocation.equals(exitLocation)
                                && getexitReason.equals(exitReason) && getkeeperID.equals(keeperID)) {
                            JOptionPane.showMessageDialog(this, "This exit details are already added", "warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;
                            break;
                        }

                    }

                    if (!isFound) {
                        ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal_exit` WHERE `exit_date` = '" + formattedExitDate + "' AND `animal_id` = '" + Integer.valueOf(animalID) + "' AND `id` != '" + id + "' ");

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(this, "Date Already used", "warning", JOptionPane.WARNING_MESSAGE);
                        } else {

                            MySQL.executeIUD("UPDATE `animal_exit` SET `exit_date` = '" + formattedExitDate + "', `exit_reason` = '" + exitReason + "', `exit_location` = '" + exitLocation + "',"
                                    + " `animal_id` = '" + Integer.valueOf(animalID) + "', `user_id`= '" + keeperID + "' WHERE `id`= '" + id + "' ");

                            loadExitRecords();
                            reset();

                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String path = "src/com/shadex/zoologix/reports/ExitRecordReport.jasper";
        HashMap<String, Object> parameters = new HashMap<>();

        try {

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String path = "src/com/shadex/zoologix/reports/ReturnRecords.jasper";
        HashMap<String, Object> parameters = new HashMap<>();

        try {

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String animal1 = "Animal2";
        AnimalView animalView = new AnimalView(null, true, animal1);
        animalView.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Animal to Update", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String id = String.valueOf(jTable1.getValueAt(row, 0));
            try {

                String animalID = jLabel13.getText();
                Date returnDate = jDateChooser2.getDate();
                String returnReason = jTextField7.getText();
                String keeperID = jLabel20.getText();

                if (animalID.equals("ANIMAL ID")) {
                    JOptionPane.showMessageDialog(this, "Please Select a animal", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (returnDate == null) {
                    JOptionPane.showMessageDialog(this, "Please enter Return Date", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (returnReason.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter Return Reason", "warning", JOptionPane.WARNING_MESSAGE);
                } else if (keeperID.equals("USER ID")) {
                    JOptionPane.showMessageDialog(this, "Please select a keeper", "warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    boolean isFound = false;
                    String formattedReturnDate = new SimpleDateFormat("yyyy-MM-dd").format(returnDate);

                    for (int i = 0; i < jTable1.getRowCount(); i++) {

                        String getanimalID = String.valueOf(jTable1.getValueAt(i, 1));
                        String getreturnDate = String.valueOf(jTable1.getValueAt(i, 3));
                        String getreturnReason = String.valueOf(jTable1.getValueAt(i, 4));
                        String getkeeperID = String.valueOf(jTable1.getValueAt(i, 5));

                        if (getanimalID.equals(animalID) && getreturnDate.equals(formattedReturnDate)
                                && getreturnReason.equals(returnReason) && getkeeperID.equals(keeperID)) {
                            JOptionPane.showMessageDialog(this, "This exit details are already added", "warning", JOptionPane.WARNING_MESSAGE);
                            isFound = true;
                            break;
                        }

                    }

                    if (!isFound) {
                        ResultSet rs = MySQL.executeSearch("SELECT * FROM `animal_return` WHERE `return_date` = '" + formattedReturnDate + "' AND `animal_id` = '" + Integer.valueOf(animalID) + "' AND `id` != '" + id + "' ");

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(this, "Date Already used", "warning", JOptionPane.WARNING_MESSAGE);
                        } else {

                            MySQL.executeIUD("UPDATE `animal_return` SET `return_date` = '" + formattedReturnDate + "', `return_reason` = '" + returnReason + "',"
                                    + " `animal_id` = '" + Integer.valueOf(animalID) + "', `user_id`= '" + keeperID + "' WHERE `id`= '" + id + "' ");

                            loadReturnRecords();
                            reset2();

                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {

            String animalID = jLabel13.getText();
            Date returnDate = jDateChooser2.getDate();
            String returnReason = jTextField7.getText();
            String keeperID = jLabel20.getText();

            if (animalID.equals("ANIMAL ID")) {
                JOptionPane.showMessageDialog(this, "Please Select a animal", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (returnDate == null) {
                JOptionPane.showMessageDialog(this, "Please enter Return Date", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (returnReason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Return Reason", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (keeperID.equals("USER ID")) {
                JOptionPane.showMessageDialog(this, "Please select a keeper", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                boolean isFound = false;
                String formattedReturnDate = new SimpleDateFormat("yyyy-MM-dd").format(returnDate);

                for (int i = 0; i < jTable1.getRowCount(); i++) {

                    String getanimalID = String.valueOf(jTable1.getValueAt(i, 1));
                    String getreturnDate = String.valueOf(jTable1.getValueAt(i, 3));
                    String getreturnReason = String.valueOf(jTable1.getValueAt(i, 4));
                    String getkeeperID = String.valueOf(jTable1.getValueAt(i, 5));

                    if (getanimalID.equals(animalID) && getreturnDate.equals(formattedReturnDate)
                            && getreturnReason.equals(returnReason) && getkeeperID.equals(keeperID)) {
                        JOptionPane.showMessageDialog(this, "This exit details are already added", "warning", JOptionPane.WARNING_MESSAGE);
                        isFound = true;
                        break;
                    }

                }

                if (!isFound) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `animal_return` WHERE `return_date` = '" + sdf.format(returnDate) + "' AND `animal_id` = '" + Integer.valueOf(animalID) + "' ");

                    if (rs1.next()) {
                        JOptionPane.showMessageDialog(this, "Date Already used", "warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        MySQL.executeIUD("INSERT INTO `animal_return` (`return_date` , `return_reason` , `animal_id`, `user_id`) "
                                + "VALUES ('" + sdf.format(returnDate) + "' , '" + returnReason + "' ,'" + Integer.valueOf(animalID) + "', '" + keeperID + "') ");

                        loadReturnRecords();
                        reset2();
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jButton3.setEnabled(false);
        int row = jTable2.getSelectedRow();

        String animalId = String.valueOf(jTable2.getValueAt(row, 1));
        jLabel1.setText(animalId);

        String animalName = String.valueOf(jTable2.getValueAt(row, 2));
        jLabel2.setText(animalName);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable2.getValueAt(row, 3));
            Date exitDate = sdf.parse(dateString);
            jDateChooser1.setDate(exitDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String exitLocation = String.valueOf(jTable2.getValueAt(row, 4));
        jTextField2.setText(exitLocation);

        String exitReason = String.valueOf(jTable2.getValueAt(row, 5));
        jTextField3.setText(exitReason);

        String keeperID = String.valueOf(jTable2.getValueAt(row, 6));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperID + "' ");
            if (rs.next()) {

                jLabel8.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel7.setText(rs.getString("id"));
                jLabel9.setText(rs.getString("mobile"));
                jLabel10.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (evt.getClickCount() == 2) {
            String animalId1 = String.valueOf(jTable2.getValueAt(row, 1));
            String animalName1 = String.valueOf(jTable2.getValueAt(row, 2));
            String keeperID1 = String.valueOf(jTable2.getValueAt(row, 6));

            String erhistory = "ERHistory2";
            ExitandReturnHistory exitandreturnhistory = new ExitandReturnHistory(null, true, erhistory, animalId1, animalName1, keeperID1);
            exitandreturnhistory.setVisible(true);

        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton14.setEnabled(false);
        int row = jTable1.getSelectedRow();

        String animalId = String.valueOf(jTable1.getValueAt(row, 1));
        jLabel13.setText(animalId);

        String animalName = String.valueOf(jTable1.getValueAt(row, 2));
        jLabel14.setText(animalName);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = String.valueOf(jTable1.getValueAt(row, 3));
            Date returnDate = sdf.parse(dateString);
            jDateChooser2.setDate(returnDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ReturnReason = String.valueOf(jTable1.getValueAt(row, 4));
        jTextField7.setText(ReturnReason);

        String keeperID = String.valueOf(jTable1.getValueAt(row, 5));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `user` WHERE `user`.`id` = '" + keeperID + "' ");
            if (rs.next()) {

                jLabel17.setText(rs.getString("fname") + " " + rs.getString("lname"));
                jLabel20.setText(rs.getString("id"));
                jLabel21.setText(rs.getString("mobile"));
                jLabel18.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (evt.getClickCount() == 2) {
            String animalId1 = String.valueOf(jTable1.getValueAt(row, 1));
            String animalName1 = String.valueOf(jTable1.getValueAt(row, 2));
            String keeperID1 = String.valueOf(jTable1.getValueAt(row, 5));

            String erhistory = "ERHistory2";
            ExitandReturnHistory exitandreturnhistory = new ExitandReturnHistory(null, true, erhistory, animalId1, animalName1, keeperID1);
            exitandreturnhistory.setVisible(true);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        reset2();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        loadExitRecords();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        loadReturnRecords();
    }//GEN-LAST:event_jTextField5KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField2.setText("");
        jTextField3.setText("");

        jLabel7.setText("USER ID");
        jLabel8.setText("FULL NAME");
        jLabel9.setText("MOBILE");
        jLabel10.setText("EMAIL");
        jLabel1.setText("ANIMAL ID");
        jLabel2.setText("ANIMAL NAME");

        jDateChooser1.setDate(null);

        jButton3.setEnabled(true);
    }

    private void reset2() {
        jTextField7.setText("");

        jDateChooser2.setDate(null);

        jLabel20.setText("USER ID");
        jLabel17.setText("FULL NAME");
        jLabel21.setText("MOBILE");
        jLabel18.setText("EMAIL");
        jLabel13.setText("ANIMAL ID");
        jLabel14.setText("ANIMAL NAME");

        jButton14.setEnabled(true);
    }
}
