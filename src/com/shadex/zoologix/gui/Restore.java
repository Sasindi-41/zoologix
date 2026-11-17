
package com.shadex.zoologix.gui;

import static com.shadex.zoologix.gui.SplashScreen.logger;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Restore extends javax.swing.JDialog {

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Viru@2002";
    private static final String DB_NAME = "zoo_final";
    
    private String backupPath;

    public Restore(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        SetPath.addActionListener(e -> setPathAction());
        Restore.addActionListener(e -> restoreAction());
    }
    
    private void setPathAction() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("SQL Files", "sql"));
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            backupPath = selectedFile.getAbsolutePath();
            jTextField1.setText(backupPath);
        }
    }

    private void restoreAction() {
        if (backupPath == null || backupPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select the SQL file first.");
            return;
        }

        String user = DB_USER;
        String password = DB_PASSWORD;
        String database = DB_NAME;

        try {
            String mysqlPath = "\"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe\"";
            String formattedBackupPath = backupPath.replaceAll("\\\\", "/");

            String command = mysqlPath + " -u" + user + " -p" + password + " " + database + " -e \"source " + formattedBackupPath + "\"";

            Process process = Runtime.getRuntime().exec(command);
            int result = process.waitFor();

            if (result == 0) {
                JOptionPane.showMessageDialog(this, "Restore successful!");
            } else {
                java.io.BufferedReader errorReader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getErrorStream()));
                StringBuilder errorOutput = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorOutput.append(line).append("\n");
                }
                JOptionPane.showMessageDialog(this, "Restore failed. Error: " + errorOutput.toString() + "\nCheck MySQL path, credentials, and SQL file path.");
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.WARNING, "Exception Occured", ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage() + "\nEnsure MySQL is installed and mysql.exe path is correct.");
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        Restore = new javax.swing.JButton();
        SetPath = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restore", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 36), new java.awt.Color(0, 50, 0))); // NOI18N

        Restore.setBackground(new java.awt.Color(0, 51, 0));
        Restore.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Restore.setForeground(new java.awt.Color(255, 255, 255));
        Restore.setText("Restore");

        SetPath.setBackground(new java.awt.Color(0, 51, 0));
        SetPath.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SetPath.setForeground(new java.awt.Color(255, 255, 255));
        SetPath.setText("Set Path");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SetPath, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Restore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SetPath, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(Restore, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Restore;
    private javax.swing.JButton SetPath;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
