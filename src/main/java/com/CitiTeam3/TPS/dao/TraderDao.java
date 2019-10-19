package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TraderDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTrader(Trader trader){
        String sql="insert into trader values(?,?,?)";
        return jdbcTemplate.update(sql,trader.getTraderId(),trader.getPsw(),trader.getUserName());
    }

    public Trader getTraderById(String id){
        String sql="select * from trader where traderId=?";
        Object[] args=new Object[1];
        args[0]=id;
        List<Trader> query = jdbcTemplate.query(sql, args, new RowMapper<Trader>() {
            @Override
            public Trader mapRow(ResultSet rs, int rowNum) throws SQLException {
                Trader trader=new Trader();
                trader.setTraderId(rs.getString("traderId"));
                trader.setUserName(rs.getString("userName"));
                trader.setPsw(rs.getString("password"));
                return trader;
            }
        });
        return  query.size()==0?null:query.get(0);
    }



}
