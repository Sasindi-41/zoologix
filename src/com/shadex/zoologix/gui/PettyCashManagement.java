
package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class PettyCashManagement extends javax.swing.JPanel {

    public JPanel getjPanel1() {
        return this;
    }
    private Dashboard dashboard;
    
    HashMap<String, String> yearMap = new HashMap<>();
    HashMap<String, String> monthMap = new HashMap<>();


    public PettyCashManagement(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        loadYears1();
        loadYears2();
        loadMonths1();
        loadPettyCash("");
        setTableContentCenter();
        jTextField3.setEditable(false);
        jTextField3.setText("0.0");
        showBarChart1();
        showBarChart2();

    }

    // load years---------------------  kavindu
    private void loadYears1() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `year`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("year"));
                yearMap.put(resultSet.getString("year"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    // load months ---------------------  kavindu
    private void loadMonths1() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("month"));
                monthMap.put(resultSet.getString("month"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    // load years 2---------------------  kavindu
    private void loadYears2() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `year`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("year"));
                yearMap.put(resultSet.getString("year"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    // load petty cash --------- Kavindu
    private void loadPettyCash(String year) {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            String query = "SELECT * FROM `pettycash`INNER JOIN `month` \n"
                    + "ON `pettycash`.`month_id` = `month`.`id` INNER JOIN `year`\n"
                    + "ON `pettycash`.`year_id` = `year`.`id`";
            if (!year.isEmpty()) {
                query += " WHERE `pettycash`.`year_id` = '" + yearMap.get(year) + "' ";
            }

            query += "ORDER BY `month`.`id` ASC ";
            ResultSet rs = MySQL.executeSearch(query);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("pettycash.id"));
                v.add(rs.getString("year.year"));
                v.add(rs.getString("month.month"));
                v.add(rs.getString("pettycash.amount"));
                v.add(rs.getString("pettycash.refundedamount"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

    }

    // table content center  ---------------------- kavindu
    private void setTableContentCenter() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.setDefaultRenderer(Object.class, render);
    }

    public void showBarChart1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT SUM(`amount`) AS amount FROM `pettycash` INNER JOIN `month` ON `pettycash`.`month_id` = `month`.`id`";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            while (rs.next()) {
                String none = rs.getString("amount");
                double amount = rs.getDouble("amount");
                dataset.setValue(amount, none, "");
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "Petty Cash Amount",
                "Rs.",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        barChart.setBackgroundPaint(new Color(204, 204, 204));

        barChart.getPlot().setBackgroundPaint(new Color(0, 0, 0, 0));
        barChart.getPlot().setOutlinePaint(new Color(0, 0, 0, 0));

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(10000));
        rangeAxis.setRange(0, rangeAxis.getUpperBound());

        BarRenderer renderer = new BarRenderer();
        Color[] colors = {new Color(0, 0, 255), new Color(71, 119, 113), new Color(101, 149, 143), new Color(141, 189, 183)};
        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesPaint(i, colors[i % colors.length]);
        }
        renderer.setMaximumBarWidth(0.1);

        renderer.setDrawBarOutline(false);

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(2, 2));
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(chartPanel, BorderLayout.CENTER);
        jPanel1.validate();
        jPanel1.repaint();
    }

    public void showBarChart2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT SUM(`refundedamount`) AS refunded FROM `pettycash` INNER JOIN `month` ON `pettycash`.`month_id` = `month`.`id`";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            while (rs.next()) {
                String none = rs.getString("refunded");
                double refunded = rs.getDouble("refunded");
                dataset.setValue(refunded, none, "");
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "Refunded Amount",
                "Rs.",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        barChart.setBackgroundPaint(new Color(204, 204, 204));

        
        barChart.getPlot().setBackgroundPaint(new Color(0, 0, 0, 0));
        barChart.getPlot().setOutlinePaint(new Color(0, 0, 0, 0));

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(10000));
        rangeAxis.setRange(0, rangeAxis.getUpperBound());

        BarRenderer renderer = new BarRenderer();
        Color[] colors = {new Color(255, 0, 0), new Color(71, 119, 113), new Color(101, 149, 143), new Color(141, 189, 183)};
        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesPaint(i, colors[i % colors.length]);
        }
        renderer.setMaximumBarWidth(0.1);

        renderer.setDrawBarOutline(false);

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(2, 2));
        jPanel2.removeAll();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(chartPanel, BorderLayout.CENTER);
        jPanel2.validate();
        jPanel2.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Petty Cash Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1190, 800));

        jLabel2.setText("Select Year :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Select Month :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Amount (Rs.)");

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Refunded Amount (Rs.)");

        jButton2.setBackground(new java.awt.Color(0, 51, 0));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Year", "Month", "Amount (Rs.)", "Refunded Amount (Rs.)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton4.setBackground(new java.awt.Color(0, 51, 0));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Edit Year");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Monthly Refundable Amount");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0");
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 2));

        jButton5.setBackground(new java.awt.Color(0, 51, 0));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Get Full Petty Cash Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel7.setText("Select Year :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(110, 110, 110)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(21, 21, 21)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String year = String.valueOf(jComboBox1.getSelectedItem());
        String month = String.valueOf(jComboBox2.getSelectedItem());
        String amount = jTextField1.getText();

        if (year.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select year", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select month", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!amount.matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `pettycash` WHERE `amount` = '" + amount + "' AND `month_id` = '" + monthMap.get(month) + "' AND `year_id` = '" + yearMap.get(year) + "'  ");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Amount already added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `pettycash` (`amount`,`refundedamount`,`month_id`,`year_id`) "
                            + "VALUES ('" + Double.parseDouble(amount) + "' , '0','" + monthMap.get(month) + "' , '" + yearMap.get(year) + "') ");
                    JOptionPane.showMessageDialog(this, "Petty Cash add success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadPettyCash("");
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            int row = jTable1.getSelectedRow();
            String getYear = String.valueOf(jTable1.getValueAt(row, 1));
            String getMonth = String.valueOf(jTable1.getValueAt(row, 2));
            String getAmount = String.valueOf(jTable1.getValueAt(row, 3));
            String getRefundedAmount = String.valueOf(jTable1.getValueAt(row, 4));

            jComboBox1.setSelectedItem(getYear);
            jComboBox2.setSelectedItem(getMonth);
            jTextField1.setText(getAmount);
            jTextField2.setText(getRefundedAmount);

            Double refundableAmount = Double.parseDouble(getAmount) - Double.parseDouble(getRefundedAmount);
            jTextField3.setText(String.valueOf(refundableAmount));
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select table row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String year = String.valueOf(jComboBox1.getSelectedItem());
            String month = String.valueOf(jComboBox2.getSelectedItem());
            String amount = jTextField1.getText();
            String refundedAmount = jTextField2.getText();

            if (year.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select year", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (month.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select month", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!amount.matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")) {
                JOptionPane.showMessageDialog(this, "Please enter valid amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (refundedAmount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter refunded amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!refundedAmount.matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")) {
                JOptionPane.showMessageDialog(this, "Please enter valid refunded amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                String getId = String.valueOf(jTable1.getValueAt(row, 0));
                String getYear = String.valueOf(jTable1.getValueAt(row, 1));
                String getMonth = String.valueOf(jTable1.getValueAt(row, 2));
                String getAmount = String.valueOf(jTable1.getValueAt(row, 3));
                String getRefundedAmount = String.valueOf(jTable1.getValueAt(row, 4));

                if (getYear.equals(year) && getMonth.equals(month) && getAmount.equals(amount) && getRefundedAmount.equals(refundedAmount)) {
                    JOptionPane.showMessageDialog(this, "Amount already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    try {
                        MySQL.executeIUD("UPDATE `pettycash` SET `amount` = '" + amount + "' ,"
                                + " `refundedamount` = '" + refundedAmount + "' ,"
                                + " `month_id` = '" + monthMap.get(month) + "' , `year_id` = '" + yearMap.get(year) + "'  WHERE `id` = '" + getId + "' ");
                        JOptionPane.showMessageDialog(this, "Petty Cash update success", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadPettyCash("");
                        reset();
                    } catch (Exception e) {
                         logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
                    }

                }

            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        EditYear ey = new EditYear(null, true);
        ey.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String path = "src/com/shadex/zoologix/reports/PettyCashReport.jasper";
        HashMap<String, Object> parameters = new HashMap<>();

        try {

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged

        if (jComboBox3.getSelectedItem() == "Select") {
            loadPettyCash("");
        } else {
            loadPettyCash(String.valueOf(jComboBox3.getSelectedItem()));
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField1.setText("");
        jTable1.clearSelection();
        jTextField2.setText("");
        jTextField3.setText("0.0");
        showBarChart1();
        showBarChart2();
        loadPettyCash("");
    }
}
