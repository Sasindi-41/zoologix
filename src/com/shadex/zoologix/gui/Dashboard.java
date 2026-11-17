package com.shadex.zoologix.gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;


public class Dashboard extends javax.swing.JFrame {

    public static MainDashboardOri  mainDashboardOri;
    public static FinanceDashboard  financeDashboard;
    public static NoticeUpdate1  noticeUpdate1;
    
    public static AnimalRegistration animalregistration;
    public static BreedingEvents breedingevents;
    public static AddDeathRecords adddeathrecords;
    public static AddExitandReturnRecords addexitandreturnrecords;
    public static EnclosureRegistration enclosureRegistration;
    
    public static MedicalHistory medicalHistory;
    public static FeedSchedule feedSchedule;
    public static FoodGRN foodGRN;
    public static MedicineGRN medicineGRN;
    public static SupplierManagement suplierMangement;
    public static FoodManagement foodManagement;
    public static MedicineManagement medicineManagement;
    
    public static GeneralStatistics generalStatistics;
    public static PettyCashManagement pettyCashManagement;
    public static IncomeManagement incomeManagement;
    public static ExpenseManagement expenseManagement;
    
    public static AdopterRegistration adopterRegistration;
    public static AddAdoption addAdoption;
    public static AddDonation addDonation;
    public static DonatorRegistration donatorRegistration;
    
    public static Ticket ticket;
    
    public static Salary1 salary;
    public static User user;
    public static Attendence1 attendence1;
    

    
    public static ARButtons arButtons;
    public static FMButtons fmButtons;
    public static TMButtons tmButtons;
    public static EMButtons emButtons;
    public static FButtons fButtons;
    public static DButtons dButtons;
    public static DashboardButtons dashboardButtons;
    
    public static String email;
    
    
    public Dashboard(String email) {
        initComponents();
        addMainDashboardOri();
        addDashboardButtons();
        this.email = email;
        jLabel2.setText(email);
        setDate();
    }
    
     private void setDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        jLabel3.setText(sdf.format(date));
    }
//-------------------------------// Dashboard Buttons//----------------------------//
    public void addMainDashboardOri(){
        if(mainDashboardOri == null){
            mainDashboardOri = new MainDashboardOri(this);
            jPanel2.add(mainDashboardOri, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeMainDashboardOri(){
        if(mainDashboardOri != null){
            jPanel2.remove(mainDashboardOri);
            mainDashboardOri = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addFinanceDashboard(){
        if(financeDashboard == null){
            financeDashboard = new FinanceDashboard(this);
            jPanel2.add(financeDashboard, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeFinanceDashboard(){
        if(financeDashboard != null){
            jPanel2.remove(financeDashboard);
            financeDashboard = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addNoticeUpdate1(){
        if(noticeUpdate1 == null){
            noticeUpdate1 = new NoticeUpdate1(this);
            jPanel2.add(noticeUpdate1, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeNoticeUpdate1(){
        if(noticeUpdate1 != null){
            jPanel2.remove(noticeUpdate1);
            noticeUpdate1 = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
     private void removeDashboardPanel(){
        removeMainDashboardOri();
        removeFinanceDashboard();
        removeNoticeUpdate1();
    }
//-------------------------------// Dashboard Buttons//----------------------------//
    
//-------------------------------// AR Buttons//----------------------------//
    public void addAnimalRegistration(){
        if(animalregistration == null){
            animalregistration = new AnimalRegistration(this);
            jPanel2.add(animalregistration, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeAnimalRegistration(){
        if(animalregistration != null){
            jPanel2.remove(animalregistration);
            animalregistration = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addEnclosureRegistration(){
        if(enclosureRegistration == null){
            enclosureRegistration = new EnclosureRegistration(this);
            jPanel2.add(enclosureRegistration, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeEnclosureRegistration(){
        if(enclosureRegistration != null){
            jPanel2.remove(enclosureRegistration);
            enclosureRegistration = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addDeathRecords() {
        if (adddeathrecords == null) {
            adddeathrecords = new AddDeathRecords(this);
            jPanel2.add(adddeathrecords, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }

    public void removeDeathRecords() {
        if (adddeathrecords != null) {
            jPanel2.remove(adddeathrecords);
            adddeathrecords = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addBreedingEvents(){
        if(breedingevents == null){
            breedingevents = new BreedingEvents(this);
            jPanel2.add(breedingevents, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeBreedingEvents(){
        if(breedingevents != null){
            jPanel2.remove(breedingevents);
            breedingevents = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addExitandReturnRecords(){
        if(addexitandreturnrecords == null){
            addexitandreturnrecords = new AddExitandReturnRecords(this);
            jPanel2.add(addexitandreturnrecords, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeExitandReturnRecords(){
        if(addexitandreturnrecords != null){
            jPanel2.remove(addexitandreturnrecords);
            addexitandreturnrecords = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    private void removeAMPanel(){
        removeAnimalRegistration();
        removeBreedingEvents();
        removeDeathRecords();
        removeExitandReturnRecords();
        removeEnclosureRegistration();
    }
//-------------------------------// AR Buttons//----------------------------//
    
//-------------------------------// FM Buttons//----------------------------//
    public void addMedicalHistory(){
        if(medicalHistory == null){
            medicalHistory = new MedicalHistory(this);
            jPanel2.add(medicalHistory, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeMedicalHistory(){
        if(medicalHistory != null){
            jPanel2.remove(medicalHistory);
            medicalHistory = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addMedicineManagement(){
        if(medicineManagement == null){
            medicineManagement = new MedicineManagement(this);
            jPanel2.add(medicineManagement, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeMedicineManagement(){
        if(medicineManagement != null){
            jPanel2.remove(medicineManagement);
            medicineManagement = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addFoodManagement(){
        if(foodManagement == null){
            foodManagement = new FoodManagement(this);
            jPanel2.add(foodManagement, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeFoodManagement(){
        if(foodManagement != null){
            jPanel2.remove(foodManagement);
            foodManagement = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addSupplierManagement(){
        if(suplierMangement == null){
            suplierMangement = new SupplierManagement(this);
            jPanel2.add(suplierMangement, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeSupplierManagement(){
        if(suplierMangement != null){
            jPanel2.remove(suplierMangement);
            suplierMangement = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addMedicineGRN(){
        if(medicineGRN == null){
            medicineGRN = new MedicineGRN(this);
            jPanel2.add(medicineGRN, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeMedicineGRN(){
        if(medicineGRN != null){
            jPanel2.remove(medicineGRN);
            medicineGRN = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addFoodGRN(){
        if(foodGRN == null){
            foodGRN = new FoodGRN(this);
            jPanel2.add(foodGRN, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeFoodGRN(){
        if(foodGRN != null){
            jPanel2.remove(foodGRN);
            foodGRN = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addFeedSchedule(){
        if(feedSchedule == null){
            feedSchedule = new FeedSchedule(this);
            jPanel2.add(feedSchedule, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeFeedSchedule(){
        if(feedSchedule != null){
            jPanel2.remove(feedSchedule);
            feedSchedule = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    private void removeFMPanel(){
        removeFoodManagement();
        removeFeedSchedule();
        removeMedicalHistory();
        removeMedicineGRN();
        removeMedicineManagement();
        removeSupplierManagement();
        removeFoodGRN();
    }
//-------------------------------// FM Buttons//----------------------------//

//-------------------------------// F Buttons//----------------------------//
    public void addGeneralStatistics(){
        if(generalStatistics == null){
            generalStatistics = new GeneralStatistics(this);
            jPanel2.add(generalStatistics, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeGeneralStatistics(){
        if(generalStatistics != null){
            jPanel2.remove(generalStatistics);
            generalStatistics = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addPettyCashManagement(){
        if(pettyCashManagement == null){
            pettyCashManagement = new PettyCashManagement(this);
            jPanel2.add(pettyCashManagement, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removePettyCashManagement(){
        if(pettyCashManagement != null){
            jPanel2.remove(pettyCashManagement);
            pettyCashManagement = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addIncomeManagement(){
        if(incomeManagement == null){
            incomeManagement = new IncomeManagement(this);
            jPanel2.add(incomeManagement, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeIncomeManagement(){
        if(incomeManagement != null){
            jPanel2.remove(incomeManagement);
            incomeManagement = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addExpenseManagement(){
        if(expenseManagement == null){
            expenseManagement = new ExpenseManagement(this);
            jPanel2.add(expenseManagement, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeExpenseManagement(){
        if(expenseManagement != null){
            jPanel2.remove(expenseManagement);
            expenseManagement = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    private void removeFPanel(){
        removeExpenseManagement();
        removeIncomeManagement();
        removeGeneralStatistics();
        removePettyCashManagement();
    }
//-------------------------------// F Buttons//----------------------------//

//-------------------------------// Donation Buttons//----------------------------//
    public void addAdopterRegistration(){
        if(adopterRegistration == null){
            adopterRegistration = new AdopterRegistration(this);
            jPanel2.add(adopterRegistration, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeAdopterRegistration(){
        if(adopterRegistration != null){
            jPanel2.remove(adopterRegistration);
            adopterRegistration = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addAddAdoption(){
        if(addAdoption == null){
            addAdoption = new AddAdoption(this);
            jPanel2.add(addAdoption, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeAddAdoption(){
        if(addAdoption != null){
            jPanel2.remove(addAdoption);
            addAdoption = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addAddDonation(){
        if(addDonation == null){
            addDonation = new AddDonation(this);
            jPanel2.add(addDonation, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeAddDonation(){
        if(addDonation != null){
            jPanel2.remove(addDonation);
            addDonation = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addDonatorRegistration(){
        if(donatorRegistration == null){
            donatorRegistration = new DonatorRegistration(this);
            jPanel2.add(donatorRegistration, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeDonatorRegistration(){
        if(donatorRegistration != null){
            jPanel2.remove(donatorRegistration);
            donatorRegistration = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    private void removeDPanel(){
        removeAddAdoption();
        removeAddDonation();
        removeDonatorRegistration();
        removeAdopterRegistration();
    }
    
//-------------------------------//Dionation Buttons//----------------------------//
    
//-------------------------------//TM Buttons//----------------------------//
    public void addTicket(){
        if(ticket == null){
            ticket = new Ticket(this);
            jPanel2.add(ticket, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeTicket(){
        if(ticket != null){
            jPanel2.remove(ticket);
            ticket = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    private void removeTMPanel(){
        removeTicket();
    }
//-------------------------------//TM Buttons//----------------------------//
    
//-------------------------------//EM Buttons//----------------------------//
    public void addUser(){
        if(user == null){
            user = new User(this);
            jPanel2.add(user, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeUser(){
        if(user != null){
            jPanel2.remove(user);
            user = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void addSalary(){
        if(salary == null){
            salary = new Salary1(this);
            jPanel2.add(salary, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeSalary(){
        if(salary != null){
            jPanel2.remove(salary);
            salary = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    public void addAttendence1(){
        if(attendence1 == null){
            attendence1 = new Attendence1(this);
            jPanel2.add(attendence1, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    public void removeAttendence1(){
        if(attendence1 != null){
            jPanel2.remove(attendence1);
            attendence1 = null;
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }
    
    private void removeEMPanel(){
        removeSalary();
        removeUser();
        removeAttendence1();
    }
//-------------------------------//EM Buttons//----------------------------//
    
//-------------------------------// Button Panels//----------------------------//
    private void addARButtons(){
        if(arButtons == null){
            arButtons = new ARButtons(this);
            jPanel1.add(arButtons, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void removeARButtons(){
        if(arButtons != null){
            jPanel1.remove(arButtons);
            arButtons = null;
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    private void addFMButtons(){
        if(fmButtons == null){
            fmButtons = new FMButtons(this);
            jPanel1.add(fmButtons, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void removeFMButtons(){
        if(fmButtons != null){
            jPanel1.remove(fmButtons);
            fmButtons = null;
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    private void addTMButtons(){
        if(tmButtons == null){
            tmButtons = new TMButtons(this);
            jPanel1.add(tmButtons, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void removeTMButtons(){
        if(tmButtons != null){
            jPanel1.remove(tmButtons);
            tmButtons = null;
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void addEMButtons(){
        if(emButtons == null){
            emButtons = new EMButtons(this);
            jPanel1.add(emButtons, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void removeEMButtons(){
        if(emButtons != null){
            jPanel1.remove(emButtons);
            emButtons = null;
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    private void addFButtons(){
        if(fButtons == null){
            fButtons = new FButtons(this);
            jPanel1.add(fButtons, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void removeFButtons(){
        if(fButtons != null){
            jPanel1.remove(fButtons);
            fButtons = null;
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    private void addDButtons(){
        if(dButtons == null){
            dButtons = new DButtons(this);
            jPanel1.add(dButtons, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void removeDButtons(){
        if(dButtons != null){
            jPanel1.remove(dButtons);
            dButtons = null;
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    private void addDashboardButtons(){
        if(dashboardButtons == null){
            dashboardButtons = new DashboardButtons(this);
            jPanel1.add(dashboardButtons, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    private void removeDashboardButtons(){
        if(dashboardButtons != null){
            jPanel1.remove(dashboardButtons);
            dashboardButtons = null;
            SwingUtilities.updateComponentTreeUI(jPanel1);
        }
    }
    
    
 //-------------------------------// Button Panels//----------------------------//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 51, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(220, 835));

        jButton7.setBackground(new java.awt.Color(0, 58, 0));
        jButton7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Animal Management");
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 58, 0));
        jButton8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Food and Medicine");
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 58, 0));
        jButton9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Ticket Management");
        jButton9.setBorder(null);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 58, 0));
        jButton10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Employee Management");
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 58, 0));
        jButton11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Financing");
        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 58, 0));
        jButton12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Donations");
        jButton12.setBorder(null);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(0, 58, 0));
        jButton13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Backup");
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(0, 58, 0));
        jButton14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Restore");
        jButton14.setBorder(null);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(0, 58, 0));
        jButton15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Logout");
        jButton15.setBorder(null);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/shadex/zoologix/resources/zooMainlogoWhite1.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Date");

        jButton16.setBackground(new java.awt.Color(0, 58, 0));
        jButton16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Dashboard");
        jButton16.setBorder(null);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 35));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1190, 800));
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        addARButtons();
        addAnimalRegistration();
        
        removeFMButtons();
        removeFButtons();
        removeDButtons();
        removeTMButtons();
        removeEMButtons();
        removeDashboardButtons();
        
        removeBreedingEvents();
        removeDeathRecords();
        removeExitandReturnRecords();
        removeEnclosureRegistration();
        
        removeFMPanel();
        removeFPanel();
        removeDPanel();
        removeTMPanel();
        removeDashboardPanel();
        removeEMPanel();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        addFMButtons();
        addSupplierManagement();
        
        removeARButtons();
        removeEMButtons();
        removeTMButtons();
        removeFButtons();
        removeDButtons();
        removeDashboardButtons();
        
        removeFeedSchedule();
        removeFoodGRN();
        removeFoodManagement();
        removeMedicalHistory();
        removeMedicineGRN();
        removeMedicineManagement();
        
        removeAMPanel();
        removeFPanel();
        removeDPanel();
        removeTMPanel();
        removeDashboardPanel();
        removeEMPanel();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        addTMButtons();
        addTicket();
        
        removeARButtons();
        removeEMButtons();
        removeFMButtons();
        removeFButtons();
        removeDButtons();
        removeDashboardButtons();
        
        removeAMPanel();
        removeFPanel();
        removeDPanel();
        removeFMPanel();
        removeDashboardPanel();
        removeEMPanel();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        addEMButtons();
        addUser();
        
        removeSalary();
        removeAttendence1();
        
        removeFMButtons();
        removeARButtons();
        removeTMButtons();
        removeFButtons();
        removeDButtons();
        removeDashboardButtons();
        
        removeAMPanel();
        removeFMPanel();
        removeDPanel();
        removeTMPanel();
        removeDashboardPanel();
        removeFPanel();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        addFButtons();
        addGeneralStatistics();
        
        removeFMButtons();
        removeARButtons();
        removeEMButtons();
        removeTMButtons();
        removeDButtons();
        removeDashboardButtons();
        
        removeIncomeManagement();
        removeExpenseManagement();
        removePettyCashManagement();
        
        removeAMPanel();
        removeFMPanel();
        removeDPanel();
        removeTMPanel();
        removeDashboardPanel();
        removeEMPanel();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        addDButtons();
        addAdopterRegistration();
        
        removeFMButtons();
        removeARButtons();
        removeEMButtons();
        removeTMButtons();
        removeFButtons();
        removeDashboardButtons();
        
        removeAddAdoption();
        removeAddDonation();
        removeDonatorRegistration();
        
        removeAMPanel();
        removeFMPanel();
        removeFPanel();
        removeTMPanel();
        removeDashboardPanel();
        removeEMPanel();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Backup backup = new Backup(this,true );
        backup.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Restore restore = new Restore(this, true);
        restore.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       this.dispose();
       new SystemLogin().setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        addDashboardButtons();
        addMainDashboardOri();
        
        removeFinanceDashboard();
        removeNoticeUpdate1();
        
        removeFMButtons();
        removeARButtons();
        removeEMButtons();
        removeTMButtons();
        removeFButtons();
        removeDButtons();
        
        removeAMPanel();
        removeFMPanel();
        removeFPanel();
        removeTMPanel();
        removeDPanel();
    }//GEN-LAST:event_jButton16ActionPerformed

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
