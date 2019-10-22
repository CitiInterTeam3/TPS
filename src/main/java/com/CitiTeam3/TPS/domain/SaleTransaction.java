package com.CitiTeam3.TPS.domain;

public class SaleTransaction {
    private String tradeOrigSys;
    private String interId;
    private String transactionDate;
    private String cusip;
    private double price;
    private int quantity;
    private String status;
    private String saleId;
    private String traderId;

    public String getInterId() {
        return interId;
    }

    public String getCusip() {
        return cusip;
    }

    public String getTradeOrigSys() {
        return tradeOrigSys;
    }

    public String getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getSaleId() {
        return saleId;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTradeOrigSys(String tradeOrigSys) {
        this.tradeOrigSys = tradeOrigSys;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInterId(String interId) {
        this.interId = interId;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
