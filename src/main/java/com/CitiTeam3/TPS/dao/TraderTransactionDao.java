package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.TraderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
public class TraderTransactionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTraderTransaction(TraderTransaction traderTransaction){
        String sql="insert into transaction (origSys,transactionId,cusip,price,quantity,status,saleId,traderId,belong) values (?,?,?,?,?,?,?,?,?)";
        String interId=String.valueOf(System.currentTimeMillis())+traderTransaction.getSaleId()+traderTransaction.getTraderId();
        traderTransaction.setInterId(interId);
        Date nowDate=new Date();
        traderTransaction.setTransactionDate(nowDate);
        traderTransaction.setStatus("REQUESTED");

        int rowCount= jdbcTemplate.update(sql,traderTransaction.getTradeOrigSys(),traderTransaction.getInterId(),
                traderTransaction.getCusip(),traderTransaction.getPrice(),traderTransaction.getQuantity(),
                traderTransaction.getStatus(),traderTransaction.getSaleId(),traderTransaction.getTraderId(),
                traderTransaction.getTraderId());

        //match
      /*  sql="select * from TPSDB.transaction where status = ? and origSys = ? and quantity = ? and traderId= ? and saleId =? and transactionId=''";
        Object[] args=new Object[5];
        args[0]=traderTransaction.getStatus();
        args[1]=traderTransaction.getTradeOrigSys();
        args[2]=traderTransaction.getQuantity();
        args[3]=traderTransaction.getTraderId();
        args[4]=traderTransaction.getSaleId();
        List<String> query = jdbcTemplate.query(sql, args, new RowMapper<String>() {
            @Override
            public TraderTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                TraderTransaction traderTransaction1=new TraderTransaction();
                traderTransaction1.setTradeOrigSys(rs.getString("traderId"));
                traderTransaction1.setTraderId(rs.getString("userName"));
                traderTransaction1.setCusip(rs.getString("password"));
                traderTransaction1.setPrice(rs.getDouble("traderId"));
                traderTransaction1.setQuantity(rs.getInt("userName"));
                traderTransaction1.setStatus(rs.getString("password"));
                traderTransaction1.setSaleId(rs.getString("userName"));
                traderTransaction1.setTraderId(rs.getString("password"));
                return traderTransaction1;
            }
        });*/


        return rowCount;
    }

}
