package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.TraderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TraderTransactionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTraderTransaction(TraderTransaction traderTransaction){
        String interId=String.valueOf(System.currentTimeMillis())+traderTransaction.getSaleId()+traderTransaction.getTraderId();
        traderTransaction.setInterId(interId);
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String time=sdf.format(date);

        traderTransaction.setTransactionDate(time);
        traderTransaction.setStatus("REQUESTED");

        int rowCount=save(traderTransaction);

        //match
        String sql="select * from transaction where status = ? and origSys = ? and quantity = ? and traderId= ? and saleId =? and belong=?";
        Object[] args=new Object[5];
        args[0]=traderTransaction.getStatus();
        args[1]=traderTransaction.getTradeOrigSys();
        args[2]=traderTransaction.getQuantity();
        args[3]=traderTransaction.getTraderId();
        args[4]=traderTransaction.getSaleId();
        args[5]=traderTransaction.getSaleId();
        List<TraderTransaction> query = jdbcTemplate.query(sql, args, new RowMapper<TraderTransaction>() {
            @Override
            public TraderTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                TraderTransaction traderTransaction1=new TraderTransaction();
                traderTransaction1.setTradeOrigSys(rs.getString("origSys"));
                traderTransaction1.setTraderId(rs.getString("traderId"));
                traderTransaction1.setCusip(rs.getString("cusip"));
                traderTransaction1.setPrice(rs.getDouble("price"));
                traderTransaction1.setQuantity(rs.getInt("quantity"));
                traderTransaction1.setStatus("PENDING");
                traderTransaction1.setSaleId(rs.getString("saleId"));
                traderTransaction1.setTraderId(rs.getString("traderId"));
                return traderTransaction1;
            }
        });
        TraderTransaction traderTransaction2;
        if(query.size()>0)
        {
            traderTransaction2=query.get(0);
        }
        else
        {
            traderTransaction.setStatus("PENDING");
            save(traderTransaction);
            return -1;   //stand it's status in pending
        }
        traderTransaction2.setStatus("TPS_PROCESSED");
        traderTransaction2.setPrice(traderTransaction.getPrice());
        saveSale(traderTransaction2);

        traderTransaction.setStatus("TPS_PROCESSED");
        save(traderTransaction);
        return rowCount;
    }

    public int  save(TraderTransaction traderTransaction)
    {
        String sql="insert into transaction (origSys,transactionId,cusip,price,quantity,status,saleId,traderId,belong) values (?,?,?,?,?,?,?,?,?)";


        int rowCount= jdbcTemplate.update(sql,traderTransaction.getTradeOrigSys(),traderTransaction.getInterId(),
                traderTransaction.getCusip(),traderTransaction.getPrice(),traderTransaction.getQuantity(),
                traderTransaction.getStatus(),traderTransaction.getSaleId(),traderTransaction.getTraderId(),
                traderTransaction.getTraderId());
        return rowCount;
    }

    public int saveSale(TraderTransaction traderTransaction)
    {
        String sql="insert into transaction (origSys,transactionId,cusip,price,quantity,status,saleId,traderId,belong) values (?,?,?,?,?,?,?,?,?)";


        int rowCount= jdbcTemplate.update(sql,traderTransaction.getTradeOrigSys(),traderTransaction.getInterId(),
                traderTransaction.getCusip(),traderTransaction.getPrice(),traderTransaction.getQuantity(),
                traderTransaction.getStatus(),traderTransaction.getSaleId(),traderTransaction.getTraderId(),
                traderTransaction.getSaleId());
        return rowCount;
    }



}
