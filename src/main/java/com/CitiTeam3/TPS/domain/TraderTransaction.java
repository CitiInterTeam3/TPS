package com.CitiTeam3.TPS.domain;

import java.util.Date;

public class TraderTransaction {
    private int TradeOrigSys;  //transaction created by SW is 1,transaction created by TW is 2
    private String interId;
    private Date transationDate;
    private String cusip;
    private double price;
    private int quantity;
    private int status;
    private String saleId;
    private String traderId;


    public Date getTransationDate() {
        return transationDate;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStatus() {
        return status;
    }

    public int getTradeOrigSys() {
        return TradeOrigSys;
    }

    public String getCusip() {
        return cusip;
    }

    public String getInterId() {
        return interId;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public void setInterId(String interId) {
        this.interId = interId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTradeOrigSys(int tradeOrigSys) {
        TradeOrigSys = tradeOrigSys;
    }

    public void setTransationDate(Date transationDate) {
        this.transationDate = transationDate;
    }
}
