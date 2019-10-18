package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TraderDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTrader(Trader trader){
        System.out.println(jdbcTemplate);
        String sql="insert into trader values(?,?,?)";
        return jdbcTemplate.update(sql,trader.getTraderId(),trader.getPsw(),trader.getUserName());
    }
}
