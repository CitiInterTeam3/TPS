package com.CitiTeam3.TPS.domain;

public class Product {
    private String cusipId;
    private String total;
    private String IssueDate;
    private String materialDate;
    private String coupon;

    public String getIssueDate() {
        return IssueDate;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getMaterialDate() {
        return materialDate;
    }

    public String getCusipId() {
        return cusipId;
    }

    public String getTotal() {
        return total;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public void setCusipId(String cusipId) {
        this.cusipId = cusipId;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public void setMaterialDate(String materialDate) {
        this.materialDate = materialDate;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
