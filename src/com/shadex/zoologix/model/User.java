package com.shadex.zoologix.model;

import java.io.Serializable;

public class User implements Serializable {

    /**
     * @return the salaryId
     */
    public String getSalaryId() {
        return salaryId;
    }

    /**
     * @param salaryId the salaryId to set
     */
    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the getEmployeeId
     */
    public String getGetEmployeeId() {
        return getEmployeeId;
    }

    /**
     * @param getEmployeeId the getEmployeeId to set
     */
    public void setGetEmployeeId(String getEmployeeId) {
        this.getEmployeeId = getEmployeeId;
    }

    /**
     * @return the getFullName
     */
    public String getGetFullName() {
        return getFullName;
    }

    /**
     * @param getFullName the getFullName to set
     */
    public void setGetFullName(String getFullName) {
        this.getFullName = getFullName;
    }

    /**
     * @return the getBasicSalary
     */
    public String getGetBasicSalary() {
        return getBasicSalary;
    }

    /**
     * @param getBasicSalary the getBasicSalary to set
     */
    public void setGetBasicSalary(String getBasicSalary) {
        this.getBasicSalary = getBasicSalary;
    }

    /**
     * @return the getBonus
     */
    public String getGetBonus() {
        return getBonus;
    }

    /**
     * @param getBonus the getBonus to set
     */
    public void setGetBonus(String getBonus) {
        this.getBonus = getBonus;
    }

    /**
     * @return the getDeductions
     */
    public String getGetDeductions() {
        return getDeductions;
    }

    /**
     * @param getDeductions the getDeductions to set
     */
    public void setGetDeductions(String getDeductions) {
        this.getDeductions = getDeductions;
    }

    /**
     * @return the getOTHours
     */
    public String getGetOTHours() {
        return getOTHours;
    }

    /**
     * @param getOTHours the getOTHours to set
     */
    public void setGetOTHours(String getOTHours) {
        this.getOTHours = getOTHours;
    }

    /**
     * @return the getOTRate
     */
    public String getGetOTRate() {
        return getOTRate;
    }

    /**
     * @param getOTRate the getOTRate to set
     */
    public void setGetOTRate(String getOTRate) {
        this.getOTRate = getOTRate;
    }

    /**
     * @return the getOTAmount
     */
    public String getGetOTAmount() {
        return getOTAmount;
    }

    /**
     * @param getOTAmount the getOTAmount to set
     */
    public void setGetOTAmount(String getOTAmount) {
        this.getOTAmount = getOTAmount;
    }

    /**
     * @return the getNetSalary
     */
    public String getGetNetSalary() {
        return getNetSalary;
    }

    /**
     * @param getNetSalary the getNetSalary to set
     */
    public void setGetNetSalary(String getNetSalary) {
        this.getNetSalary = getNetSalary;
    }
    
    private String salaryId;
    private String getEmployeeId;
    private String getFullName;
    private String getBasicSalary;
    private String getBonus;
    private String getDeductions;
    private String getOTHours;
    private String getOTRate;
    private String getOTAmount;
    private String getNetSalary;
    private String designation;

    public User() {
    }

    public User(String salaryId, String getEmployeeId, String getFullName, String getBasicSalary, String getBonus, String getDeductions, String getOTHours, String getOTRate, String getOTAmount, String getNetSalary, String designation) {
        this.salaryId = salaryId;
        this.getEmployeeId = getEmployeeId;
        this.getFullName = getFullName;
        this.getBasicSalary = getBasicSalary;
        this.getBonus = getBonus;
        this.getDeductions = getDeductions;
        this.getOTHours = getOTHours;
        this.getOTRate = getOTRate;
        this.getOTAmount = getOTAmount;
        this.getNetSalary = getNetSalary;
        this.designation = designation;
    }
    
    
    

   
    
    

  
    
    

}
