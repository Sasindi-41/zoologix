package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import com.shadex.zoologix.validation.Validation;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author malini ravindika
 */
public class User extends javax.swing.JPanel {

    HashMap<String, String> userStatusMap = new HashMap<>();
    HashMap<String, String> userTypeMap = new HashMap<>();
    HashMap<String, String> gendersMap = new HashMap<>();
    HashMap<String, String> deparmentMap = new HashMap<>();
    HashMap<String, String> workSheduleMap = new HashMap<>();

     private Dashboard dashboard;
     
    public User(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        loadDepartment();
        loadGender();
        loadUserStatus();
        loadUserType();
        loadWorkShedule();
        loadUsers();
    }

    public void loadUserStatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `userstatus`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                userStatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadUserType() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `usertype`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                userTypeMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadGender() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `gender`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                gendersMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadDepartment() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `department`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("department_name"));
                deparmentMap.put(resultSet.getString("department_name"), resultSet.getString("department_id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadWorkShedule() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `workshedule`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("time"));
                workSheduleMap.put(resultSet.getString("time"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadUsers() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `user` "
                    + "INNER JOIN `usertype` ON `usertype`.`id`=`user`.`usertype_id`"
                    + "INNER JOIN `userstatus` ON `userstatus`.`id`=`user`.`userstatus_id` "
                    + "INNER JOIN `gender` ON `gender`.`id`=`user`.`gender_id`"
                    + "INNER JOIN `department` ON `department`.`department_id`=`user`.`department_department_id` "
                    + "INNER JOIN `workshedule` ON `workshedule`.`id`=`user`.`workshedule_id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("fname"));
                vector.add(resultSet.getString("lname"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("password"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("register_date"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("usertype.type"));
                vector.add(resultSet.getString("userstatus.status"));
                vector.add(resultSet.getString("gender.name"));
                vector.add(resultSet.getString("department.department_name"));
                vector.add(resultSet.getString("workshedule.time"));

                model.addRow(vector);
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel1.setText("User Registration");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("First Name :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Last Name :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Email :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Password :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NIC :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mobile :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("User Type :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("User Status :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Gender :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Department :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Work Shedule :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save User");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Update User");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(38, 38, 38))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)
                            .addComponent(jTextField7)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(22, 22, 22)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jTextField4))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jTextField5))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(86, 86, 86))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "First Name", "Last Name", "Email", "Password", "NIC", "Register Date", "Mobile", "User Type", "User Status", "Gender", "Department", "Work Shedule"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String fname = jTextField2.getText();
        String lname = jTextField3.getText();
        String email = jTextField4.getText();
        String password = jTextField5.getText();
        String nic = jTextField6.getText();
        String mobile = jTextField7.getText();
        String userType = String.valueOf(jComboBox1.getSelectedItem());
        String userStatus = String.valueOf(jComboBox2.getSelectedItem());
        String gender = String.valueOf(jComboBox3.getSelectedItem());
        String department = String.valueOf(jComboBox4.getSelectedItem());
        String workShedule = String.valueOf(jComboBox5.getSelectedItem());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the first name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the last name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the email ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the password ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validateNic(nic)) {
            return;

        } else if (validateMobile(mobile)) {
            return;
        } else if (userType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select user type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (userStatus.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select user status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (gender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (department.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the department", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (workShedule.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the work shedule", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `user` WHERE `mobile`='" + mobile + "' AND `email`='" + email + "' AND `nic`='" + nic + "' ");

                while (resultSet1.next()) {
                    JOptionPane.showMessageDialog(this, "This user already exit.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                MySQL.executeIUD("INSERT INTO `user` (`fname`,`lname`,`email`,`password`,`nic`,`register_date`,`mobile`,"
                        + "`usertype_id`,`userstatus_id`,`gender_id`,`department_department_id`,`workshedule_id`) VALUES"
                        + "('" + fname + "' , '" + lname + "' , '" + email + "', '" + password + "' , '" + nic + "' , '" + formattedDate + "' ,"
                        + " '" + mobile + "' , '" + userTypeMap.get(userType) + "', '" + userStatusMap.get(userStatus) + "' "
                        + ", '" + gendersMap.get(gender) + "' , '" + deparmentMap.get(department) + "' , '" + workSheduleMap.get(workShedule) + "')");
                JOptionPane.showMessageDialog(this, "Successfully Registrated", "Sauccess", JOptionPane.INFORMATION_MESSAGE);
                reset();
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       String fname = jTextField2.getText();
        String lname = jTextField3.getText();
        String email = jTextField4.getText();
        String password = jTextField5.getText();
        String nic = jTextField6.getText();
        String mobile = jTextField7.getText();
        String userType = String.valueOf(jComboBox1.getSelectedItem());
        String userStatus = String.valueOf(jComboBox2.getSelectedItem());
        String gender = String.valueOf(jComboBox3.getSelectedItem());
        String department = String.valueOf(jComboBox4.getSelectedItem());
        String workShedule = String.valueOf(jComboBox5.getSelectedItem());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = new Date();
        String formattedDate = dateFormat.format(regDate);

        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the first name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the last name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the email ", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the password ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (validateNic(nic)) {
            return;

        } else if (validateMobile(mobile)) {
            return;
        } else if (userType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select user type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (userStatus.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select user status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (gender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (department.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the department", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (workShedule.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the work shedule", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `user` WHERE `mobile`='" + mobile + "' AND `email`='" + email + "' AND `nic`='" + nic + "' ");

                while (resultSet1.next()) {

                    MySQL.executeIUD("UPDATE `user` SET `fname`='" + fname + "' , `lname`='" + lname + "',`email`='" + email + "',`password`='" + password + "',`register_date`= '" + formattedDate + "' ,`nic`= '" + nic + "' "
                            + ",`mobile`='" + mobile + "',`usertype_id`='" + userTypeMap.get(userType) + "',`userstatus_id`='" + userStatusMap.get(userStatus) + "',"
                            + "`gender_id`='" + gendersMap.get(gender) + "',`department_department_id`='" + deparmentMap.get(department) + "'  ,`workshedule_id`='" + workSheduleMap.get(workShedule) + "'"
                            + "WHERE `mobile`='" + mobile + "' AND `email`='" + email + "' AND `nic`='" + nic + "'  ");
                    JOptionPane.showMessageDialog(this, "Successfully Updated", "Sauccess", JOptionPane.INFORMATION_MESSAGE);

                }

                reset();
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField4.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            String search = jTextField8.getText();
            int row = jTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `user` "
                    + "INNER JOIN `usertype` ON `usertype`.`id`=`user`.`usertype_id`"
                    + "INNER JOIN `userstatus` ON `userstatus`.`id`=`user`.`userstatus_id` "
                    + "INNER JOIN `gender` ON `gender`.`id`=`user`.`gender_id`"
                    + "INNER JOIN `department` ON `department`.`department_id`=`user`.`department_department_id` "
                    + "INNER JOIN `workshedule` ON `workshedule`.`id`=`user`.`workshedule_id` WHERE `user`.`id`='" + search + "'");

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("fname"));
                vector.add(resultSet.getString("lname"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("password"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("register_date"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("usertype.type"));
                vector.add(resultSet.getString("userstatus.status"));
                vector.add(resultSet.getString("gender.name"));
                vector.add(resultSet.getString("department.department_name"));
                vector.add(resultSet.getString("workshedule.time"));

                model.addRow(vector);
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        
        //String userId=String.valueOf(jTable1.getValueAt(row, 0));

        String fname = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField2.setText(fname);

        String lname = String.valueOf(jTable1.getValueAt(row, 2));
        jTextField3.setText(lname);

        String email = String.valueOf(jTable1.getValueAt(row, 3));
        jTextField4.setText(email);

        String password = String.valueOf(jTable1.getValueAt(row, 4));
        jTextField5.setText(password);

        String nic = String.valueOf(jTable1.getValueAt(row, 5));
        jTextField6.setText(nic);

        String mobile = String.valueOf(jTable1.getValueAt(row, 7));
        jTextField7.setText(mobile);

        String userType = String.valueOf(jTable1.getValueAt(row, 8));
        jComboBox1.setSelectedItem(userType);

        String userstatus = String.valueOf(jTable1.getValueAt(row, 9));
        jComboBox2.setSelectedItem(userstatus);

        String gender = String.valueOf(jTable1.getValueAt(row, 10));
        jComboBox3.setSelectedItem(gender);

        String deparment = String.valueOf(jTable1.getValueAt(row, 11));
        jComboBox4.setSelectedItem(deparment);

        String workShedule = String.valueOf(jTable1.getValueAt(row, 12));
        jComboBox5.setSelectedItem(workShedule);


    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jTextField5.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jTextField6.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        jTextField7.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    reset();
    // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    //Mobile vaildation
    private boolean validateNic(String value) {
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the nic number ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        } else if (!value.matches(Validation.VALIDATE_NIC.validate())) {
            JOptionPane.showMessageDialog(this, "Incorrect nic number ", "Warning", JOptionPane.WARNING_MESSAGE);
            return true;

        }
        return false;
    }

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

    public void reset() {

        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        loadUsers();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
