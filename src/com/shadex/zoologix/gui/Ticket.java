package com.shadex.zoologix.gui;

import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ticket extends javax.swing.JPanel {

    public JPanel getjPanel1() {
        return this;
    }
    private Dashboard dashboard;

    public Ticket(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
        loadTicket();
        setTableContentCenter();
        generateInvoiceID();
        Date();
        addDailyTicketIncome();

    }

    private void loadTicket() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `ticket_price`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("price"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }

    private void setGlobalFont(Font font) {
        setFontForContainer(this, font);
    }

    private void setFontForContainer(Container container, Font font) {
        for (Component component : container.getComponents()) {
            component.setFont(font);
            if (component instanceof Container) {
                setFontForContainer((Container) component, font);
            }
        }
    }

    private void setTableContentCenter() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.setDefaultRenderer(Object.class, render);
        jTable2.setDefaultRenderer(Object.class, render);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1190, 800));

        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 800));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ticketing", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 24), new java.awt.Color(0, 50, 0))); // NOI18N

        jLabel3.setForeground(new java.awt.Color(0, 50, 0));
        jLabel3.setText("DATE");

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jTable2.setBackground(new java.awt.Color(255, 255, 255));
        jTable2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Quantity", "Ticket Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 50, 0));
        jLabel14.setText("Date");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setForeground(new java.awt.Color(0, 50, 0));
        jLabel4.setText("INVOICE ID");

        jLabel5.setForeground(new java.awt.Color(0, 50, 0));
        jLabel5.setText("jLabel5");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 50, 0));
        jLabel7.setText("Select Ticket Type");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 50, 0));
        jLabel6.setText("Name");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 50, 0));
        jLabel8.setText("Quantity");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 50, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add To Invoice");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 50, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 50, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 50, 0));
        jLabel9.setText("Total");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 50, 0));
        jLabel10.setText("Payment");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 50, 0));
        jLabel11.setText("Balance");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("00.00");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("00.00");

        jButton2.setBackground(new java.awt.Color(0, 50, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Ticket");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Ticket Summary");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(173, 173, 173)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(66, 66, 66)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jTextField1)
                                            .addGap(28, 28, 28))
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(16, 16, 16))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(147, 147, 147))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(35, 35, 35))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(27, 27, 27))
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton5)
                                    .addGap(129, 129, 129)))))
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addGap(7, 7, 7)))
                    .addGap(18, 18, 18)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addGap(58, 58, 58)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13))
                    .addGap(18, 18, 18)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(29, 29, 29)
                    .addComponent(jButton5)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(1172, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1033, 1033, 1033))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2))
                        .addGap(38, 38, 38)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14))
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(56, 56, 56))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        if (row != -1) {
            String Name = String.valueOf(jTable1.getValueAt(row, 0));
            jLabel7.setText(Name);

            if (evt.getClickCount() == 3) {

                price Priceview = new price(null, true);
                Priceview.setVisible(true);

            }

        }
    }//GEN-LAST:event_jTable1MouseClicked
    private void Date() {
        Date now = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String formattedDate = sdf.format(now);

        jLabel3.setText(formattedDate);
    }

    private void generateInvoiceID() {
        Date now = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        String formattedDate = sdf.format(now);

        jLabel5.setText("INV-" + formattedDate);
    }
    private double overallTotal = 0.0;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket type from the list.");
            return;
        }

        String ticketName = (String) jTable1.getValueAt(selectedRow, 0);
        double ticketPrice = Double.parseDouble((String) jTable1.getValueAt(selectedRow, 1));

        int count;
        try {
            count = Integer.parseInt(jTextField1.getText());
            if (count <= 0) {
                throw new NumberFormatException("count must be greater than 0");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid count.");
            return;
        }

        double total = ticketPrice * count;

        overallTotal += total;
        jLabel12.setText(String.format("%.2f", overallTotal));

        DefaultTableModel invoiceModel = (DefaultTableModel) jTable2.getModel();
        invoiceModel.addRow(new Object[]{ticketName, count, ticketPrice, String.format("%.2f", total)});

        jTextField1.setText("");
        jLabel7.setText("");
        jTable1.clearSelection();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String text = jTextField2.getText();

        if (text.isEmpty()) {
            jLabel13.setText("00.00");
            return;
        }

        if (!text.matches("\\d+")) {
            text = text.replaceAll("[^\\d]", "");
            jTextField2.setText(text);
            jLabel13.setText("00.00");
            return;
        }

        try {
            double payment = Double.parseDouble(text);
            double total = Double.parseDouble(jLabel12.getText());
            double balance = payment - total;

            jLabel13.setText(String.format("%.2f", balance));
        } catch (NumberFormatException e) {
            jLabel13.setText("00.00");
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String paymentText = jTextField2.getText().trim();

        if (paymentText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please insert payment.");
            return;
        }

        try {
            Double.parseDouble(paymentText);

            printIndividualTickets();

            // Clear fields after printing
            jTextField2.setText("");
            jLabel12.setText("");
            jLabel13.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid payment amount.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        int row = jTable2.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            return;
        }

        try {
            String name = String.valueOf(jTable2.getValueAt(row, 0));
            String count = String.valueOf(jTable2.getValueAt(row, 1));

            jLabel7.setText(name);
            jTextField1.setText(count);

            jButton1.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error retrieving selected row data: " + e.getMessage());
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String name = jLabel7.getText();
        int newQuantity;

        try {
            newQuantity = Integer.parseInt(jTextField1.getText());
            if (newQuantity <= 0) {
                throw new NumberFormatException("count must be greater than 0");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid count.");
            return;
        }

        DefaultTableModel invoiceModel = (DefaultTableModel) jTable2.getModel();
        int rowCount = invoiceModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String ticketName = (String) invoiceModel.getValueAt(i, 0);

            if (ticketName.equals(name)) {
                int currentQuantity = (int) invoiceModel.getValueAt(i, 1);
                double price = (double) invoiceModel.getValueAt(i, 2);

                double oldTotal = currentQuantity * price;
                overallTotal -= oldTotal;

                invoiceModel.setValueAt(newQuantity, i, 1);
                double newTotal = newQuantity * price;
                invoiceModel.setValueAt(String.format("%.2f", newTotal), i, 3);

                overallTotal += newTotal;
                jLabel12.setText(String.format("%.2f", overallTotal));

                jTextField1.setText("");
                jLabel7.setText("");
                jTable2.clearSelection();

                jButton1.setEnabled(true);
                jLabel7.setEnabled(true);

                break;
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed
    private void clear() {
        jTextField2.setText("");
        jLabel3.setText("");
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.");
            return;
        }

        DefaultTableModel invoiceModel = (DefaultTableModel) jTable2.getModel();

        int count = (int) invoiceModel.getValueAt(selectedRow, 1);
        double price = (double) invoiceModel.getValueAt(selectedRow, 2);

        double rowTotal = count * price;
        overallTotal -= rowTotal;

        jLabel12.setText(String.format("%.2f", overallTotal));

        invoiceModel.removeRow(selectedRow);

        jTextField1.setText("");
        jLabel7.setText("");
        jTable2.clearSelection();

        jButton1.setEnabled(true);
        jLabel7.setEnabled(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        TicketSummary TS = new TicketSummary(parentFrame, true);
        TS.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    public void printIndividualTickets() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int qty = model.getRowCount();

        if (qty == 0) {
            JOptionPane.showMessageDialog(this, "No tickets to print.");
            return;
        }

        double totalAmount = 0.0;

        try {
            for (int i = 0; i < qty; i++) {
                String ticketName = (String) model.getValueAt(i, 0);
                double price = Double.parseDouble(model.getValueAt(i, 2).toString());
                int quantity = (int) model.getValueAt(i, 1);

                String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("Parameter1", ticketName);
                parameters.put("Parameter2", formattedDate);
                parameters.put("Parameter3", formattedTime);
                parameters.put("Parameter4", String.format("%.2f", price));

                for (int j = 0; j < quantity; j++) {
                    InputStream reportPath = this.getClass().getResourceAsStream("/com/shadex/zoologix/reports/zoo1.jasper");

                    if (reportPath == null) {
                        JOptionPane.showMessageDialog(this, "Report template not found. Please check the file path.");
                        return;
                    }

                    JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, new JREmptyDataSource());

                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setVisible(true);

                    System.out.println("Ticket: " + ticketName + ", Price: " + String.format("%.2f", price));

                    insertOrUpdateTicketRecord(ticketName, price);
                    if (reportPath != null) {
                        reportPath.close();
                    }
                }

                totalAmount += price * quantity;
            }

            model.setRowCount(0);
            clear();

            overallTotal = 0.0;

        } catch (JRException e) {
            JOptionPane.showMessageDialog(this, "Error printing tickets: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void insertOrUpdateTicketRecord(String ticketName, double price) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_final", "root", "dew@#Mysql12");

            String ticketQuery = "SELECT id FROM ticket_price WHERE Name = '" + ticketName + "'";
            pstmt = conn.prepareStatement(ticketQuery);
            rs = pstmt.executeQuery();

            int ticketId = -1;
            if (rs.next()) {
                ticketId = rs.getInt("id");
            } else {
                String insertTicketQuery = "INSERT INTO ticket_price (Name, Price) VALUES ('" + ticketName + "', " + price + ")";
                pstmt = conn.prepareStatement(insertTicketQuery, Statement.RETURN_GENERATED_KEYS);
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    ticketId = rs.getInt(1);
                }
            }

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dayCountQuery = "SELECT count FROM day_count WHERE Date = '" + date + "' AND ticket_price_id = " + ticketId;
            pstmt = conn.prepareStatement(dayCountQuery);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = Integer.parseInt(rs.getString("count")) + 1;
                String updateQuantityQuery = "UPDATE day_count SET count = " + count + " WHERE Date = '" + date + "' AND ticket_price_id = " + ticketId;
                pstmt = conn.prepareStatement(updateQuantityQuery);
                pstmt.executeUpdate();
            } else {
                String insertDayCountQuery = "INSERT INTO day_count (Date, count, ticket_price_id) VALUES ('" + date + "', '1', " + ticketId + ")";
                pstmt = conn.prepareStatement(insertDayCountQuery);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                /* ignored */ }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                /* ignored */ }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                /* ignored */ }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables


private void addTicketIncome(double amount) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String formatDate = sdf.format(date);
        LocalDate currentDate = LocalDate.parse(formatDate);
        Month month = currentDate.getMonth();
        int year = currentDate.getYear();
        int monthId = 0;
        int yearId = 0;
        switch (month) {
            case JANUARY:
                monthId = 1;
                break;
            case FEBRUARY:
                monthId = 2;
                break;
            case MARCH:
                monthId = 3;
                break;
            case APRIL:
                monthId = 4;
                break;
            case MAY:
                monthId = 5;
                break;
            case JUNE:
                monthId = 6;
                break;
            case JULY:
                monthId = 7;
                break;
            case AUGUST:
                monthId = 8;
                break;
            case SEPTEMBER:
                monthId = 9;
                break;
            case OCTOBER:
                monthId = 10;
                break;
            case NOVEMBER:
                monthId = 11;
                break;
            case DECEMBER:
                monthId = 12;
                break;
            default:
                monthId = 1;
        }

        switch (year) {
            case 2022:
                yearId = 1;
                break;
            case 2023:
                yearId = 2;
                break;
            case 2024:
                yearId = 3;
                break;
            case 2025:
                yearId = 4;
                break;
            default:
                yearId = 4;
        }

        if (String.valueOf(amount).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter  amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!String.valueOf(amount).matches("^(0|[1-9][0-9]*)(\\.[0-9]+)?$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                MySQL.executeIUD("INSERT INTO `income` (`date` , `amount` , `month_id` , `incomesource_id` , `year_id`) VALUES('" + formatDate + "','" + amount + "' ,'" + monthId + "' ,'1' ,'" + yearId + "' )  ");
                logger.log(java.util.logging.Level.INFO,"Ticket income added to db");
            } catch (Exception e) {
                logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
            }
        }

    }


private void addDailyTicketIncome() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String string1 = "06:00 PM";
                    Date time1 = new SimpleDateFormat("hh:mm a").parse(string1);
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(time1);
                    calendar1.add(Calendar.DATE, 1);

                    String string2 = "06:01 PM";
                    Date time2 = new SimpleDateFormat("hh:mm a").parse(string2);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(time2);
                    calendar2.add(Calendar.DATE, 1);

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String currenttime = sdf.format(date);
                    Date d = new SimpleDateFormat("hh:mm a").parse(currenttime);
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(d);
                    calendar3.add(Calendar.DATE, 1);

                    Date x = calendar3.getTime();
                    if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                        Date date3 = new Date();
                        SimpleDateFormat sdf3 = new SimpleDateFormat("YYYY-MM-dd");
                        String today = sdf3.format(date3);

                        ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `income`  WHERE `income`.`date` = '" + today + "' AND `incomesource_id` = '1'  ");
                        if (!rs1.next()) {
                            ResultSet rs = MySQL.executeSearch("SELECT SUM(`ticket_price`.`price`) AS `totalTicketIncome`,`day_count`.`date` FROM `day_count` INNER JOIN `ticket_price` ON `day_count`.`ticket_price_id` = `ticket_price`.`id` WHERE `day_count`.`date` = '" + today + "'");
                            if (rs.next()) {
                                addTicketIncome(Double.parseDouble(rs.getString("totalTicketIncome")));
                            }
                        }
                    }

                } catch (Exception e) {
                    logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
                }
            }
        });

        t.start();
    }


}


