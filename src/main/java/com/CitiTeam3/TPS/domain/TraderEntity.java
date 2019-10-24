package com.CitiTeam3.TPS.domain;

import java.sql.Date;

public class TraderEntity {
    int traderRequestId;
    int TraderId;
    double price;
    int amount;
    int type;
    int status;
    int matchId;
    String cusipId;
    Date issueDate;
    int targetId;
    String rejectReason;

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }


    public int getTraderRequestId() {
        return traderRequestId;
    }

    public void setTraderRequestId(int traderRequestId) {
        this.traderRequestId = traderRequestId;
    }

    public int getTraderId() {
        return TraderId;
    }

    public void setTraderId(int traderId) {
        TraderId = traderId;
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
