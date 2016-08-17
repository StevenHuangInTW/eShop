package com.tw.eshop.model;

import java.util.DoubleSummaryStatistics;

/**
 * Created by qbhuang on 16/8/17.
 */
public class OrderEntity {

    private  String name;
    private double price;
    private int qty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
