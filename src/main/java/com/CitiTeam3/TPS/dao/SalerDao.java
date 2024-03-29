package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.Sales;
import com.CitiTeam3.TPS.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class SalerDao {
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

    public Sales getSalerByUserName(String userName){
        String sql="select * from sales where salesName=?";
        Object[] args=new Object[1];
        args[0]=userName;
        List<Sales> query = jdbcTemplate.query(sql, args, new RowMapper<Sales>() {
            @Override
            public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
                Sales trader=new Sales();
                trader.setSalesId(rs.getString("salesId"));
                trader.setSalesName(rs.getString("salesName"));
                trader.setPassword(rs.getString("password"));
                return trader;
            }
        });
        return  query.size()==0?null:query.get(0);
    }


    public String getPswByUserName(String userName){
        String sql="select password from sales where userName=?";
        Object[] args=new Object[1];
        args[0]=userName;
        List<String> query = jdbcTemplate.query(sql, args, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("password");
            }
        });
        return  query.size()==0?null:query.get(0);
    }

}
