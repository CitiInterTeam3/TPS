package com.CitiTeam3.TPS.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sales")
public class Sales implements Serializable {
    @Id
    private String salesId;
    private String salesName;
    private String password;

    public Sales(){}

    public Sales(String salesId,String salesName,String password){
        this.salesId=salesId;
        this.salesName=salesName;
        this.password=password;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
