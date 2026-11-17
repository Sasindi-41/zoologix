package com.shadex.zoologix.gui;


import com.formdev.flatlaf.FlatClientProperties;
import com.shadex.zoologix.connection.MySQL;
import static com.shadex.zoologix.gui.Dashboard.feedSchedule;
import static com.shadex.zoologix.gui.Dashboard.medicalHistory;
import static com.shadex.zoologix.gui.Dashboard.salary;
import static com.shadex.zoologix.gui.SplashScreen.logger;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class UserView extends javax.swing.JDialog {

    private String user;

    public UserView(java.awt.Frame parent, boolean modal, String user) {
        super(parent, modal);
        initComponents();
        this.user = user;
        loadUsers();
        setPlaceholders();
        setTableContentCenter();
    }
    
     private void setPlaceholders(){
        jTextField4.putClientProperty("Jcomponent.roundRect", true);
        jTextField4.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search by Employee email ");
        jTextField4.putClientProperty(FlatClientProperties.STYLE, "margin:0,10,0,10");
        jTextField4.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
    }

    private void loadUsers() {
        try {
             String searchQuery = jTextField4.getText().trim().toLowerCase();
            String query = "SELECT * FROM `user`"
                    + "INNER JOIN `usertype` ON `user`.`usertype_id` = `usertype`.`id` "
                    + "INNER JOIN `userstatus` ON `user`.`userstatus_id` = `userstatus`.`id` "
                    + "INNER JOIN `gender` ON `user`.`gender_id` = `gender`.`id` ";
                    
             if (!searchQuery.isEmpty()) {
                query += " WHERE LOWER(user.email) LIKE '%" + searchQuery + "%'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);         

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("fname"));
                vector.add(resultSet.getString("lname"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("userstatus.status"));
                vector.add(resultSet.getString("gender.name"));

                model.addRow(vector);
            }
            jTable1.setModel(model);

        } catch (Exception e) {
           
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", e);
        }
    }
    
     //    placeHolders ------------------- Dewmini 
     private void setTableContentCenter() {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.setDefaultRenderer(Object.class, render);
    }
      //    placeHolders ------------------- Dewmini 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0)), "User View", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 20))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(899, 350));

        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "First Name", "Last Name", "Mobile", "Email", "Status", "Gender"
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

            int row = jTable1.getSelectedRow();
            String id = String.valueOf(jTable1.getValueAt(row, 0));
            String fname = String.valueOf(jTable1.getValueAt(row, 1));
            String lname = String.valueOf(jTable1.getValueAt(row, 2));
            String mobile = String.valueOf(jTable1.getValueAt(row, 3));
            String email = String.valueOf(jTable1.getValueAt(row, 4));

            if (user == "User1") {
                medicalHistory.setUserID(id);
            }else  if (user == "User2") {
                feedSchedule.setUserId(id);
            }else  if (user == "User3") {
                salary.setUserId(id);
                salary.setUserName(fname+" "+lname);
            }

            this.dispose();

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        loadUsers();
    }//GEN-LAST:event_jTextField4KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
