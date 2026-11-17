package com.shadex.zoologix.gui;

public class FMButtons extends javax.swing.JPanel {

    private Dashboard dashboard;
    
    public FMButtons(Dashboard getDashboard) {
        initComponents();
        this.dashboard = getDashboard;
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 51, 0));
        setPreferredSize(new java.awt.Dimension(1190, 35));
        setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(0, 58, 0));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Supplier Management");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 58, 0));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Feed Schedule ");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);

        jButton3.setBackground(new java.awt.Color(0, 58, 0));
        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Food Management");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);

        jButton4.setBackground(new java.awt.Color(0, 58, 0));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Medicine Management");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);

        jButton5.setBackground(new java.awt.Color(0, 58, 0));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Food GRN");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5);

        jButton6.setBackground(new java.awt.Color(0, 58, 0));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Medicine GRN");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        add(jButton6);

        jButton7.setBackground(new java.awt.Color(0, 58, 0));
        jButton7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Medical History");
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(jButton7);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dashboard.removeSupplierManagement();
        dashboard.removeMedicalHistory();
        dashboard.removeMedicineGRN();
        dashboard.removeMedicineManagement();
        dashboard.removeFeedSchedule();
        dashboard.removeFoodGRN();
        dashboard.addFoodManagement();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dashboard.addSupplierManagement();
        dashboard.removeMedicalHistory();
        dashboard.removeMedicineGRN();
        dashboard.removeMedicineManagement();
        dashboard.removeFeedSchedule();
        dashboard.removeFoodGRN();
        dashboard.removeFoodManagement();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dashboard.removeSupplierManagement();
        dashboard.removeMedicalHistory();
        dashboard.removeMedicineGRN();
        dashboard.removeMedicineManagement();
        dashboard.addFeedSchedule();
        dashboard.removeFoodGRN();
        dashboard.removeFoodManagement();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dashboard.removeSupplierManagement();
        dashboard.removeMedicalHistory();
        dashboard.removeMedicineGRN();
        dashboard.addMedicineManagement();
        dashboard.removeFeedSchedule();
        dashboard.removeFoodGRN();
        dashboard.removeFoodManagement();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dashboard.removeSupplierManagement();
        dashboard.removeMedicalHistory();
        dashboard.removeMedicineGRN();
        dashboard.removeMedicineManagement();
        dashboard.removeFeedSchedule();
        dashboard.addFoodGRN();
        dashboard.removeFoodManagement();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dashboard.removeSupplierManagement();
        dashboard.removeMedicalHistory();
        dashboard.addMedicineGRN();
        dashboard.removeMedicineManagement();
        dashboard.removeFeedSchedule();
        dashboard.removeFoodGRN();
        dashboard.removeFoodManagement();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        dashboard.removeSupplierManagement();
        dashboard.addMedicalHistory();
        dashboard.removeMedicineGRN();
        dashboard.removeMedicineManagement();
        dashboard.removeFeedSchedule();
        dashboard.removeFoodGRN();
        dashboard.removeFoodManagement();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    // End of variables declaration//GEN-END:variables
}
