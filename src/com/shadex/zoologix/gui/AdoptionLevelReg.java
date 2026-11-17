package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author malini ravindika
 */
public class AdoptionLevelReg extends javax.swing.JDialog {

    HashMap<String, String> animalMap = new HashMap<>();
    HashMap<String, String> paymentMap = new HashMap<>();
    private int benefitId;

    public AdoptionLevelReg(java.awt.Frame parent, boolean modal, String adoptionLevel, String name, String currentAdopterId, String start) {
        super(parent, modal);
        initComponents();
        jTextField2.setText(adoptionLevel);
        jLabel6.setText(name);
        jLabel9.setText(currentAdopterId);
        jLabel11.setText(start);

        loadAnimal();
        loadPaymentMethod();
        loadBenifit();
        calculateDate();
        loadAdopterLevelReg();

    }

    public void loadAnimal() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_animal`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("animal"));
                animalMap.put(resultSet.getString("animal"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadPaymentMethod() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_method`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("pay_method"));
                paymentMap.put(resultSet.getString("pay_method"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadBenifit() {

        String adoptionLevel = jTextField2.getText();
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_levels_and_benefits` "
                    + "INNER JOIN `adoption_levels` ON `adoption_levels`.`id`=`adoption_levels_and_benefits`.`adoption_levels_id`"
                    + "INNER JOIN `adoption_benefit` ON `adoption_benefit`.`id`=`adoption_levels_and_benefits`.`adoption_benefit_id` WHERE `adoption_levels`.`level`='" + adoptionLevel + "'");

            while (resultSet.next()) {
                String benefit = resultSet.getString("adoption_benefit.benefit");
                benefitId = resultSet.getInt("adoption_levels_and_benefits.id");
                jTextArea1.append(benefit + "\n");
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void loadAdopterLevelReg() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_level_reg` INNER JOIN `adopter` ON `adopter`.`id`=`adoption_level_reg`.`adopter_id`"
                    + "INNER JOIN `payment_method` ON `payment_method`.`id`=`adoption_level_reg`.`payment_method_id`"
                    + " INNER JOIN `adoption_levels_and_benefits` ON `adoption_levels_and_benefits`.`id`=`adoption_level_reg`.`adoption_levels_and_benefits_id`"
                    + "INNER JOIN `adoption_levels` ON `adoption_levels`.`id`=`adopter`.`adoption_levels_id` "
                    + "INNER JOIN `adoption_animal` ON `adoption_animal`.`id`=`adoption_level_reg`.`adoption_animal_id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("adopter.id"));
                vector.add(resultSet.getString("adoption_levels.level"));
                vector.add(resultSet.getString("adoption_animal.animal"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("payment_method.pay_method"));
                vector.add(resultSet.getString("start_date"));
                vector.add(resultSet.getString("end_date"));
                vector.add(resultSet.getString("adoption_level_reg.renewal_count"));

                model.addRow(vector);
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void calculateDate() {

        try {
            String start = jLabel11.getText().trim();

            //create end date
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_levels`");
            while (resultSet.next()) {
                String duration = resultSet.getString("duration");
                int intDuration = Integer.parseInt(duration);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate endDate = LocalDate.parse(start, dateFormat);
                LocalDate endDateNew = endDate.plusYears(intDuration);
                String parsedDate = endDateNew.format(dateFormat);
                jLabel15.setText(parsedDate);
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    private boolean amoundValdation() {
        String level = jTextField2.getText();
        String amount = jFormattedTextField1.getText().trim();
        double doubleAmount;
        try {
            doubleAmount = Double.parseDouble(amount);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount format. Please enter a numeric value.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_levels` WHERE `level`='" + level + "'");
            boolean withinRange = false;

            while (resultSet.next()) {
                double minAmount = resultSet.getDouble("min_amount");
                double maxAmount = resultSet.getDouble("max_amount");

                if (doubleAmount >= minAmount && doubleAmount <= maxAmount) {
                    withinRange = true;
                    break;
                }

            }
            if (!withinRange) {
                JOptionPane.showMessageDialog(this, "This amount does not match this adopter level", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AdoptionLevelReg.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Adoption Level Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        jLabel8.setText("ID");

        jLabel4.setText("Adopter");

        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Adopter", "Level", "Animal", "Amount", "PaymentMethod", "StartDate", "StartDate", "Renewal Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(jTable1);

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField1FocusLost(evt);
            }
        });
        jFormattedTextField1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jFormattedTextField1InputMethodTextChanged(evt);
            }
        });
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("ID");

        jLabel7.setText("Animal");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("End Date");

        jLabel3.setText("Adopter");

        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jTextArea1.setDisabledTextColor(new java.awt.Color(204, 255, 204));
        jScrollPane1.setViewportView(jTextArea1);

        jLabel14.setText("Payment  method");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Level");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Name");

        jLabel5.setText("End Date");

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Start Date");

        jLabel10.setText("Start Date");

        jLabel2.setText("Benefits");

        jLabel13.setText("Amount");

        jButton2.setBackground(new java.awt.Color(0, 51, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add");
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel7))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel14))
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(jLabel11))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        String adopter = String.valueOf(jTable1.getValueAt(row, 0));
        jLabel9.setText(adopter);

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adopter` WHERE `id`='" + adopter + "' ");

            while (resultSet.next()) {
                String name = resultSet.getString("name");

                jLabel6.setText(name);
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

        String level = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField2.setText(level);

        String animal = String.valueOf(jTable1.getValueAt(row, 2));
        jComboBox1.setSelectedItem(animal);

        String amount = String.valueOf(jTable1.getValueAt(row, 3));
        jFormattedTextField1.setText(amount);

        String method = String.valueOf(jTable1.getValueAt(row, 4));
        jComboBox2.setSelectedItem(method);

        String date = String.valueOf(jTable1.getValueAt(row, 5));
        jLabel15.setText(date);

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        amoundValdation();
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jFormattedTextField1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jFormattedTextField1InputMethodTextChanged
        amoundValdation();
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1InputMethodTextChanged

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jFormattedTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1FocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            String search = jTextField1.getText();
            int row = jTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_level_reg` INNER JOIN `adopter` ON `adopter`.`id`=`adoption_level_reg`.`adopter_id`"
                    + "INNER JOIN `payment_method` ON `payment_method`.`id`=`adoption_level_reg`.`payment_method_id`"
                    + " INNER JOIN `adoption_levels_and_benefits` ON `adoption_levels_and_benefits`.`id`=`adoption_level_reg`.`adoption_levels_and_benefits_id`"
                    + "INNER JOIN `adoption_levels` ON `adoption_levels`.`id`=`adopter`.`adoption_levels_id` "
                    + "INNER JOIN `adoption_animal` ON `adoption_animal`.`id`=`adoption_level_reg`.`adoption_animal_id` WHERE `adopter`.`id`='" + search + "'");

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("adopter.id"));
                vector.add(resultSet.getString("adoption_levels.level"));
                vector.add(resultSet.getString("adoption_animal.animal"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("payment_method.pay_method"));
                vector.add(resultSet.getString("start_date"));
                vector.add(resultSet.getString("end_date"));
                vector.add(resultSet.getString("adoption_level_reg.renewal_count"));

                model.addRow(vector);
            }

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(64, 147, 105));
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(Color.black);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String name = jLabel6.getText();
        String id = jLabel9.getText();
        String level = jTextField2.getText();
        String animal = String.valueOf(jComboBox1.getSelectedItem());
        String amount = jFormattedTextField1.getText();
        String payMethod = String.valueOf(jComboBox2.getSelectedItem());
        String start = jLabel11.getText();
        String end = jLabel15.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the adopter Id ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (level.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (animal.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter animal", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!amount.equals("^(?:[1-9]\\d*|0(?:\\.\\d+)?|[1-9]\\d*\\.\\d+)$")) {
            JOptionPane.showMessageDialog(this, "Invalid cost value ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (payMethod.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adoption level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (start.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (end.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!amoundValdation()) {
            // If amount is out of range, show a warning

        } else {
            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_level_reg` WHERE `adopter_id`='" + id + "'");

                //if(resultSet.next()){
                //   JOptionPane.showMessageDialog(this,"This adopter already exit.Please renew the registration","Warning",JOptionPane.WARNING_MESSAGE);
                //}else{
                MySQL.executeIUD("UPDATE `adoption_level_reg` SET `adopter_id`='" + id + "',`adoption_levels_and_benefits_id`='" + benefitId + "',`adoption_animal_id`='" + animalMap.get(animal) + "',"
                        + "`amount`='" + amount + "',`payment_method_id`='" + paymentMap.get(payMethod) + "',`start_date`='" + start + "',`end_date`='" + end + "',`renewal_count`='" + 0 + "' WHERE `adopter_id`='" + id + "'");

                JOptionPane.showMessageDialog(this, "Successfully Updated", "Sauccess", JOptionPane.INFORMATION_MESSAGE);
                reset();
                //}

            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String name = jLabel6.getText();
        String id = jLabel9.getText();
        String level = jTextField2.getText();
        String animal = String.valueOf(jComboBox1.getSelectedItem());
        String amount = jFormattedTextField1.getText();
        String payMethod = String.valueOf(jComboBox2.getSelectedItem());
        String start = jLabel11.getText();
        String end = jLabel15.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter name ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the adopter Id ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (level.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (animal.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adopter animal", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!amount.equals("^(?:[1-9]\\d*|0(?:\\.\\d+)?|[1-9]\\d*\\.\\d+)$")) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (payMethod.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the adoption level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (start.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (end.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the city", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!amoundValdation()) {
            // If amount is out of range, show a warning
            JOptionPane.showMessageDialog(this, "This amount does not match this adopter level", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `adoption_level_reg` WHERE `adopter_id`='" + id + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This adopter already exit.Please renew the registration", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("INSERT INTO `adoption_level_reg` (`adopter_id`,`adoption_levels_and_benefits_id`,`adoption_animal_id`,"
                            + "`amount`,`payment_method_id`,`start_date`,`end_date`,`renewal_count`)VALUES('" + id + "','" + benefitId + "'"
                            + " ,'" + animalMap.get(animal) + "', '" + amount + "', '" + paymentMap.get(payMethod) + "','" + start + "','" + end + "' ,'" + 0 + "')");

                    JOptionPane.showMessageDialog(this, "Successfully Registrated", "Sauccess", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                }

            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public void focusLost(FocusEvent e) {

    }

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public void reset() {
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jFormattedTextField1.setText("");
        loadAdopterLevelReg();
    }
}
