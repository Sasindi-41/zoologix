
package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EditIncomeExpenses extends javax.swing.JDialog {

    private static HashMap<String, String> monthMap = new HashMap<>();
    private static HashMap<String, String> yearMap = new HashMap<>();
    private static HashMap<String, String> incomeSourceMap = new HashMap<>();
    private static HashMap<String, String> exepenceSourceMap = new HashMap<>();
    private String incomeOrExpense;
    private int sID = 0;
    
    private JDateChooser jDateChooser1;

    public EditIncomeExpenses(java.awt.Frame parent, boolean modal, String incomeOrExpense) {
        super(parent, modal);
        initComponents();
        getIncomeOrExpense(incomeOrExpense);
        this.incomeOrExpense = incomeOrExpense;
        loadYears1();
        loadYears();
        
        jDateChooser1 = new JDateChooser();
    }
    
    // updated
    public void setDate(Date date) { 
        jDateChooser1.setDate(date); 
    }
    public JDateChooser getDateChooser() {
        return jDateChooser1;
    }


    public void setMonth(String month) {

        jComboBox1.setSelectedItem(month);
    }

    public void setYear(String year) {

        jComboBox2.setSelectedItem(year);
    }

    public void setSource(String source) {

        jComboBox3.setSelectedItem(source);
    }

    public void setAmount(String amount) {

        jTextField2.setText(amount);
    }

    public void setSID(int id) {

        this.sID = id;
    }

    public JButton setAddButton() {

        return jButton1;
    }

    private void getIncomeOrExpense(String getIncomeOrExpense) {
        if (getIncomeOrExpense.equals("income")) {
            this.setTitle("Edit Income");
            jLabel7.setText("Edit Income");
            jLabel4.setText("Income Source :");
            loadIncomeSource();
        } else if (getIncomeOrExpense.equals("expense")) {
            this.setTitle("Edit Expense");
            this.jLabel7.setText("Edit Expenses");
            jLabel4.setText("Expense Source :");
            loadExpenseSource();
        }
    }

    // load months ---------------------  kavindu
    private void loadYears1() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("month"));
                monthMap.put(resultSet.getString("month"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    // load years---------------------  kavindu
    private void loadYears() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `year`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("year"));
                yearMap.put(resultSet.getString("year"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    // load sources ---------------------  kavindu
    private void loadIncomeSource() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `incomesource`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("source"));
                incomeSourceMap.put(resultSet.getString("source"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    private void loadExpenseSource() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `exepencesource`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("source"));
                exepenceSourceMap.put(resultSet.getString("source"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2.setForeground(new java.awt.Color(0, 50, 0));
        jLabel2.setText("Date :");

        jLabel3.setForeground(new java.awt.Color(0, 50, 0));
        jLabel3.setText("Month : ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setForeground(new java.awt.Color(0, 50, 0));
        jLabel4.setText("Income Source :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setForeground(new java.awt.Color(0, 50, 0));
        jLabel5.setText("Year :");

        jLabel6.setForeground(new java.awt.Color(0, 50, 0));
        jLabel6.setText("Amount :");

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 51, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 50, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Edit Income");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Date date = jDateChooser1.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fomatDate = dateFormat.format(date);
        String month = String.valueOf(jComboBox1.getSelectedItem());
        String year = String.valueOf(jComboBox2.getSelectedItem());
        String source = String.valueOf(jComboBox3.getSelectedItem());
        String amount = String.valueOf(jTextField2.getText());

        if (String.valueOf(date) == "null") {
            JOptionPane.showMessageDialog(this, "Please Select a date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Month", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (year.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Year", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (source.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a source", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a Amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!amount.matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                if (incomeOrExpense.equals("income")) {
                    MySQL.executeIUD("INSERT INTO `income` (`date`,`amount`,`month_id`,`incomesource_id`,`year_id`) "
                            + "VALUES('" + fomatDate + "','" + Double.parseDouble(amount) + "','" + monthMap.get(month) + "' , '" + incomeSourceMap.get(source) + "' , '" + yearMap.get(year) + "') ");
                    JOptionPane.showMessageDialog(this, "Income added success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();

                } else if (incomeOrExpense.equals("expense")) {
                    MySQL.executeIUD("INSERT INTO `expenses` (`date`,`amount`,`month_id`,`exepencesource_id`,`year_id`) "
                            + "VALUES('" + fomatDate + "','" + Double.parseDouble(amount) + "','" + monthMap.get(month) + "' , '" + exepenceSourceMap.get(source) + "' , '" + yearMap.get(year) + "') ");
                    JOptionPane.showMessageDialog(this, "Expense added success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                }
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Date date = jDateChooser1.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fomatDate = dateFormat.format(date);
        String month = String.valueOf(jComboBox1.getSelectedItem());
        String year = String.valueOf(jComboBox2.getSelectedItem());
        String source = String.valueOf(jComboBox3.getSelectedItem());
        String amount = String.valueOf(jTextField2.getText());

        if (String.valueOf(date) == "null") {
            JOptionPane.showMessageDialog(this, "Please Select a date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Month", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (year.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Year", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (source.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a source", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a Amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!amount.matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                if (incomeOrExpense.equals("income")) {
                    ResultSet rs = MySQL.executeSearch("SELECT * FROM `income` WHERE `date` = '" + fomatDate + "'"
                            + " AND `amount` = '" + Double.parseDouble(amount) + "' AND `month_id` = '" + monthMap.get(month) + "' AND `incomesource_id` = '" + incomeSourceMap.get(source) + "' AND `year_id` = '" + yearMap.get(year) + "' AND `income`.`id` = '" + sID + "'  ");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Details already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {

                        MySQL.executeIUD(" UPDATE `income` SET `date` = '" + fomatDate + "' ,"
                                + "`amount` = '" + Double.parseDouble(amount) + "' ,`month_id` = '" + monthMap.get(month) + "' ,"
                                + "`incomesource_id` = '" + incomeSourceMap.get(source) + "',`year_id` = '" + yearMap.get(year) + "'"
                                + " WHERE `income`.`id` = '" + sID + "' ");
                        JOptionPane.showMessageDialog(this, "Income updated success", "Success", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }

                } else if (incomeOrExpense.equals("expense")) {
                    ResultSet rs = MySQL.executeSearch("SELECT * FROM `expenses` WHERE `date` = '" + fomatDate + "'"
                            + " AND `amount` = '" + Double.parseDouble(amount) + "' AND `month_id` = '" + monthMap.get(month) + "' AND `exepencesource_id` = '" + exepenceSourceMap.get(source) + "' AND `year_id` = '" + yearMap.get(year) + "' AND `expenses`.`id` = '" + sID + "'  ");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Details already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                    
                           MySQL.executeIUD(" UPDATE `expenses` SET `date` = '" + fomatDate + "' ,"
                            + "`amount` = '" + Double.parseDouble(amount) + "' ,`month_id` = '" + monthMap.get(month) + "' ,"
                            + "`exepencesource_id` = '" + exepenceSourceMap.get(source) + "',`year_id` = '" + yearMap.get(year) + "'"
                            + " WHERE `expenses`.`id` = '" + sID + "' ");
                    JOptionPane.showMessageDialog(this, "Expense updated success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                    }
                 
                }
            } catch (Exception e) {
                 logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void reset() {
        jDateChooser1.setDate(null);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jTextField2.setText("");
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
