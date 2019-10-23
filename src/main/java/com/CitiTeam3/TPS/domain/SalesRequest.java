package com.CitiTeam3.TPS.domain;

import java.sql.Date;

public class SalesRequest {


    int SalesRequestId;
    int salesId;
    double price;
    int amount;
    int type;
    int status;
    int matchId;
    String cusipId;
    Date issueDate;
    int targetId;


    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getSalesRequestId() {
        return SalesRequestId;
    }

    public void setSalesRequestId(int salesRequestId) {
        SalesRequestId = salesRequestId;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getCusipId() {
        return cusipId;
    }

    public void setCusipId(String cusipId) {
        this.cusipId = cusipId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
