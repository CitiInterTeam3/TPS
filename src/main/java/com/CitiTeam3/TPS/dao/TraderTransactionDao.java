package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.TraderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static java.lang.System.currentTimeMillis;

@Component
public class TraderTransactionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int addTraderTransaction(TraderTransaction traderTransaction){
        String sql="insert into transaction values(?,?,?)";
        String interId=String.valueOf(System.currentTimeMillis());
        return jdbcTemplate.update(sql,traderTransaction.getCusip(),traderTransaction.getInterId(),traderTransaction.getPrice());
    }

}
