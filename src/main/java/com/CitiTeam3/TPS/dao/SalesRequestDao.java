package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.SalesRequest;
import com.CitiTeam3.TPS.domain.Status;
import com.CitiTeam3.TPS.domain.TraderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class SalesRequestDao {
    @Autowired
    JdbcTemplate jdbc;

    public boolean addRequest(SalesRequest request){
        String sql="insert into salesRequest(salesId,price,amount," +
                "type,status,cusipId,issueDate,targetTraderId) values(?,?,?,?,?,?,?,?)";
        Object args[]=new Object[8];
        args[0]=request.getSalesId();
        args[1]=request.getPrice();
        args[2]=request.getAmount();
        args[3]=request.getType();
        args[4]= Status.PENDDING.getValue();
        args[5]=request.getCusipId();
        args[6]=new Date(System.currentTimeMillis());
        args[7]=request.getTargetId();
        return  jdbc.update(sql,args)==1;
    }


}
