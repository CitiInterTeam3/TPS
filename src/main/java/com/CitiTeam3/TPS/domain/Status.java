package com.CitiTeam3.TPS.domain;

public enum  Status {
    PENDDING(1),
    PROCESSED(2),
    ACCPETED(3),
    REJECTED(4);
    int value;
    Status(int i){this.value=i;}
    public int getValue(){return value;}
}
