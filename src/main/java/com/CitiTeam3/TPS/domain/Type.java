package com.CitiTeam3.TPS.domain;

public enum  Type {
    BUY(1),
    SALE(2);
    int value;
    Type(int i){this.value=i;}
    public int getValue(){return value;}
}
