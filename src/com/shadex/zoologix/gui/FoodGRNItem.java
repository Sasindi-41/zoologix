package com.shadex.zoologix.gui;

import java.util.Date;

public class FoodGRNItem {

    private String foodId;
    private String foodName;
    private double qty;
    private double price;
    private Date mfd;
    private Date exp;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getMfd() {
        return mfd;
    }

    public void setMfd(Date mfd) {
        this.mfd = mfd;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

}
