package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.text.DecimalFormat;
import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.sql.ResultSet;
import java.text.NumberFormat;

public class FinanceDashboard extends javax.swing.JPanel {

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private CategoryPlot categoryPlot;
    private ChartPanel chartPanel;
    
    private Dashboard dashboard;
    public FinanceDashboard(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        initComponents();
        showBarChart();
        showExpensesChart();
        showIncomeRingChart();
        loadFinancialData();

    }

    public void showIncomeRingChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        String currentMonthQuery = "SELECT SUM(amount) AS current_month_income FROM income "
                + "WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(CURRENT_DATE());";
        String lastMonthQuery = "SELECT SUM(amount) AS last_month_income FROM income "
                + "WHERE MONTH(date) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(date) = YEAR(CURRENT_DATE());";

        double currentMonthIncome = 0;
        double lastMonthIncome = 0;

        try {
            ResultSet rsCurrent = MySQL.executeSearch(currentMonthQuery);
            if (rsCurrent.next()) {
                currentMonthIncome = rsCurrent.getDouble("current_month_income");
                if (rsCurrent.wasNull()) {
                    currentMonthIncome = 0;
                }
            }
            System.out.println("[DEBUG] Current Month Income (from DB): " + currentMonthIncome);

            ResultSet rsLast = MySQL.executeSearch(lastMonthQuery);
            if (rsLast.next()) {
                lastMonthIncome = rsLast.getDouble("last_month_income");
                if (rsLast.wasNull()) {
                    lastMonthIncome = 0;
                }
            }
            System.out.println("[DEBUG] Last Month Income (from DB): " + lastMonthIncome);
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

        if (currentMonthIncome == 0 && lastMonthIncome == 0) {
            dataset.setValue("No Income Data", 1);
            System.out.println("[DEBUG] Both current and last month income are zero.");
        } else {
            dataset.setValue("Current Month Income", currentMonthIncome);
            dataset.setValue("Last Month Income", lastMonthIncome);
        }

        JFreeChart ringChart = ChartFactory.createRingChart("Income Comparison", dataset, false, true, false);
        RingPlot plot = (RingPlot) ringChart.getPlot();

        plot.setBackgroundPaint(new Color(246,255,246));
        plot.setSectionPaint("Current Month Income", new Color(0, 51, 0));
        plot.setSectionPaint("Last Month Income", new Color(51, 153, 0));
        plot.setSectionPaint("No Income Data", new Color(51, 255, 0));
        plot.setSectionDepth(0.30f);
        plot.setStartAngle(90);
        plot.setIgnoreNullValues(true);

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}",
                NumberFormat.getNumberInstance(), new DecimalFormat("0.0#'%'")));
        plot.setSimpleLabels(false);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setLabelOutlinePaint(Color.GRAY);
        plot.setLabelShadowPaint(null);

        ChartPanel chartPanel = new ChartPanel(ringChart);
        chartPanel.setPreferredSize(new Dimension(300, 250));

        jPanel26.removeAll();
        jPanel26.setLayout(new BorderLayout());
        jPanel26.add(chartPanel, BorderLayout.CENTER);
        jPanel26.revalidate();
        jPanel26.repaint();
    }

    public void loadFinancialData() {
        String query = "SELECT "
                + "(SELECT SUM(amount) FROM income WHERE YEAR(date) = YEAR(CURDATE())) AS total_income_current_year, "
                + "(SELECT SUM(amount) FROM income WHERE YEAR(date) = YEAR(CURDATE()) AND MONTH(date) = MONTH(CURDATE())) AS total_income_current_month, "
                + "(SELECT SUM(amount) FROM expenses WHERE YEAR(date) = YEAR(CURDATE())) AS total_expenses_current_year, "
                + "(SELECT SUM(amount) FROM expenses WHERE YEAR(date) = YEAR(CURDATE()) AND MONTH(date) = MONTH(CURDATE())) AS total_expenses_current_month;";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            if (rs.next()) {
                double totalIncomeCurrentYear = rs.getDouble("total_income_current_year");
                double totalIncomeCurrentMonth = rs.getDouble("total_income_current_month");
                double totalExpensesCurrentYear = rs.getDouble("total_expenses_current_year");
                double totalExpensesCurrentMonth = rs.getDouble("total_expenses_current_month");

                DecimalFormat formatter = new DecimalFormat("#,##0.00");

                jLabel17.setText(formatter.format(totalIncomeCurrentYear));
                jLabel25.setText(formatter.format(totalIncomeCurrentMonth));
                jLabel27.setText(formatter.format(totalExpensesCurrentYear));
                jLabel29.setText(formatter.format(totalExpensesCurrentMonth));
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    public void showBarChart() {
        dataset = new DefaultCategoryDataset();

        String query = "SELECT \n"
                + "    y.year AS income_year, \n"
                + "    i.source AS income_source, \n"
                + "    COALESCE(SUM(income.amount), 0) AS total_income\n"
                + "FROM \n"
                + "    income\n"
                + "JOIN \n"
                + "    incomesource i ON income.incomesource_id = i.id\n"
                + "JOIN \n"
                + "    year y ON income.year_id = y.id\n"
                + "WHERE \n"
                + "    y.year IN (2022, 2023, 2024)\n"
                + "GROUP BY \n"
                + "    income_year, income_source\n"
                + "ORDER BY \n"
                + "    income_year, income_source;";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            while (rs.next()) {
                int incomeYear = rs.getInt("income_year");
                String incomeSource = rs.getString("income_source");
                double totalIncome = rs.getDouble("total_income");

                dataset.addValue(totalIncome, incomeSource, String.valueOf(incomeYear));
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

        chart = ChartFactory.createBarChart(
                "Zoo Income by Source in Last 3 Years",
                "Year",
                "Income Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        chart.setBackgroundPaint(new Color(246,255,246));

        categoryPlot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();

        renderer.setSeriesPaint(0, new Color(0, 51, 0)); 
        renderer.setSeriesPaint(1, new Color(51,153,0));
        renderer.setSeriesPaint(2, new Color(51, 255, 0));
        renderer.setSeriesPaint(3, new Color(153, 255, 153));
        renderer.setSeriesPaint(4, new Color(0, 153, 51));

        chartPanel = new ChartPanel(chart);
        jPanel7.removeAll();
        jPanel7.setLayout(new BorderLayout());
        jPanel7.add(chartPanel, BorderLayout.CENTER);
        jPanel7.validate();
        jPanel7.repaint();
    }

    public void showExpensesChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT YEAR(e.date) AS expense_year, "
                + "es.source AS expense_source, "
                + "COALESCE(SUM(e.amount), 0) AS total_expenses "
                + "FROM expenses e "
                + "JOIN exepencesource es ON e.exepencesource_id = es.id "
                + "WHERE YEAR(e.date) IN (2022, 2023, 2024) "
                + "GROUP BY expense_year, expense_source "
                + "ORDER BY expense_year, expense_source;";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            while (rs.next()) {
                int expenseYear = rs.getInt("expense_year");
                String expenseSource = rs.getString("expense_source");
                double totalExpenses = rs.getDouble("total_expenses");

                dataset.addValue(totalExpenses, expenseSource, String.valueOf(expenseYear));
            }
        } catch (Exception e) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Zoo Expenses by Source in Last 3 Years",
                "Year",
                "Expense Amount",
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(new Color(246,255,246));

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();

        renderer.setSeriesPaint(0, new Color(13, 43, 29));
        renderer.setSeriesPaint(1, new Color(52, 86, 53));
        renderer.setSeriesPaint(2, new Color(107, 143, 113));
        renderer.setSeriesPaint(3, new Color(174, 195, 176));
        renderer.setSeriesPaint(4, new Color(227, 239, 211));

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel21.setLayout(new BorderLayout());
        jPanel21.add(chartPanel, BorderLayout.CENTER);
        jPanel21.validate();
        jPanel21.repaint();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel22 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel25 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel26 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1190, 800));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Financial Overview", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24))); // NOI18N

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Total Expenses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 18))); // NOI18N
        jPanel21.setPreferredSize(new java.awt.Dimension(590, 390));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Total Expenses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 18))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(590, 390));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Total Expenses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 18))); // NOI18N

        jPanel16.setBackground(new java.awt.Color(51, 153, 0));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("This year");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(51, 153, 0));

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("This Month");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "Total Expenses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 18))); // NOI18N
        jPanel23.setForeground(new java.awt.Color(0, 50, 0));

        jPanel24.setBackground(new java.awt.Color(51, 153, 0));

        jLabel26.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("This year");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(51, 153, 0));

        jLabel28.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("This Month");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator12.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jPanel26.setPreferredSize(new java.awt.Dimension(300, 250));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
