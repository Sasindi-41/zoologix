package com.shadex.zoologix.gui;

import static com.shadex.zoologix.gui.SplashScreen.logger;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Backup extends javax.swing.JDialog {

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Viru@2002";
    private static final String DB_NAME = "zoo_final";

    public Backup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        SetPath.addActionListener(e -> setPathAction());
        Backup.addActionListener(e -> backupAction());
    }
    private String backupPath = "";

    private void setPathAction() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = chooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedDir = chooser.getSelectedFile();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            backupPath = selectedDir.getAbsolutePath() + File.separator + "backup_" + date + ".sql";
            jTextField1.setText(backupPath);
        }
    }

    private void backupAction() {
        if (backupPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please set the backup path first.");
            return;
        }

        String user = DB_USER;
        String password = DB_PASSWORD;
        String database = DB_NAME;

        try {
            String command = "\"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe\" -u" + user + " -p" + password + " " + database + " -r \"" + backupPath + "\"";

            Process process = Runtime.getRuntime().exec(command);
            int result = process.waitFor();

            if (result == 0) {
                JOptionPane.showMessageDialog(this, "Backup successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Backup failed. Check MySQL path and credentials.");
            }

        } catch (Exception ex) {
             logger.log(java.util.logging.Level.WARNING, "Exception Occured", ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage() + "\nEnsure MySQL is installed and mysqldump.exe path is correct.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SetPath = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        Backup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Backup", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 36), new java.awt.Color(0, 50, 0))); // NOI18N

        SetPath.setBackground(new java.awt.Color(0, 51, 0));
        SetPath.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SetPath.setForeground(new java.awt.Color(255, 255, 255));
        SetPath.setText("Set Path");

        Backup.setBackground(new java.awt.Color(0, 51, 0));
        Backup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Backup.setForeground(new java.awt.Color(255, 255, 255));
        Backup.setText("BackUp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Backup, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SetPath, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SetPath, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(Backup, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Backup;
    private javax.swing.JButton SetPath;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
