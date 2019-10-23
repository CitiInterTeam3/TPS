package com.CitiTeam3.TPS.dao;

import com.CitiTeam3.TPS.domain.SalesRequest;
import com.CitiTeam3.TPS.domain.Status;
import com.CitiTeam3.TPS.domain.TraderRequest;
import com.CitiTeam3.TPS.domain.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SalerRequestDao {
    @Autowired
    JdbcTemplate jdbc;

    public int addRequest(TraderRequest request){
        String sql="insert into traderRequest(traderId,price,amount," +
                "type,status,cusipId,issueDate,targetSalesId) values(?,?,?,?,?,?,?,?)";
        Object args[]=new Object[8];
        args[0]=request.getTraderId();
        args[1]=request.getPrice();
        args[2]=request.getAmount();
        args[3]=request.getType();
        args[4]=Status.PENDDING.getValue();
        args[5]=request.getCusipId();
        args[6]=new Date(System.currentTimeMillis());
        args[7]=request.getTargetId();
        jdbc.update(sql,args);
        return  1;
    }

    public List<TraderRequest> getAllRequestByTraderId(int traderId){
        String sql="select * from traderRequest where traderId=?";
        return jdbc.query(sql, new Object[]{traderId}, new RowMapper<TraderRequest>() {
            @Override
            public TraderRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
                TraderRequest request=new TraderRequest();
                request.setTraderRequestId(rs.getInt("traderRequestId"));
                request.setTraderId(rs.getInt("traderId"));
                request.setPrice(rs.getDouble("price"));
                request.setAmount(rs.getInt("amount"));
                request.setType(rs.getInt("type"));
                request.setStatus(rs.getInt("status"));
                request.setMatchId(rs.getInt("matchedSalesRequest"));
                request.setCusipId(rs.getString("cusipId"));
                request.setIssueDate(rs.getDate("issueDate"));
                request.setTargetId(rs.getInt("targetSalesId"));
                return request;
            }
        });
    }

    public TraderRequest getTraderRequestById(int traderRequestId){
        String sql="select * from traderRequest where traderRequestId=?";
        List<TraderRequest> list=jdbc.query(sql, new Object[]{traderRequestId}, new RowMapper<TraderRequest>() {
            @Override
            public TraderRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
                TraderRequest request=new TraderRequest();
                request.setTraderRequestId(rs.getInt("traderRequestId"));
                request.setTraderId(rs.getInt("traderId"));
                request.setPrice(rs.getDouble("price"));
                request.setAmount(rs.getInt("amount"));
                request.setType(rs.getInt("type"));
                request.setStatus(rs.getInt("status"));
                request.setMatchId(rs.getInt("matchedSalesRequest"));
                request.setCusipId(rs.getString("cusipId"));
                request.setIssueDate(rs.getDate("issueDate"));
                request.setTargetId(rs.getInt("targetSalesId"));
                return request;
            }
        });
        return list.size()==0?null:list.get(0);
    }


    public List<SalesRequest>  getMatchedSalesRequest(int requestId){
        TraderRequest request=getTraderRequestById(requestId);
        StringBuilder builder=new StringBuilder();
        builder.append(" select * from salesRequest where ")
                .append(" salesId=? and amount=? and type=? and cusipId=?")
                .append(" and targetTraderId=? and status=?");
        Object args[]=new Object[6];
        args[0]=request.getTargetId();
        args[1]=request.getAmount();
        args[2]=request.getType()== Type.BUY.getValue()?Type.SALE.getValue():Type.BUY.getValue();
        args[3]=request.getCusipId();
        args[4]=request.getTraderId();
        args[5]=request.getStatus();
        return jdbc.query(builder.toString(), args, new RowMapper<SalesRequest>() {
            @Override
            public SalesRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
                SalesRequest request=new SalesRequest();
                request.setSalesRequestId(rs.getInt("salesRequestId"));
                request.setTraderId(rs.getInt("salesId"));
                request.setPrice(rs.getDouble("price"));
                request.setAmount(rs.getInt("amount"));
                request.setType(rs.getInt("type"));
                request.setStatus(rs.getInt("status"));
                request.setMatchId(rs.getInt("matchedTraderRequest"));
                request.setCusipId(rs.getString("cusipId"));
                request.setIssueDate(rs.getDate("issueDate"));
                request.setTargetId(rs.getInt("targetTraderId"));
                return request;
            }
        });
    }

    public boolean updateStatus(TraderRequest request){
        String sql="update traderRequest set status=? where traderRequestId=?";
        return jdbc.update(sql,request.getStatus(),request.getTraderRequestId())==1;
    }

    public boolean updateMatchedSalesId(int traderRequestId,int targetSalesRequestId){
        String sql="update traderRequest set targetSalesId=? where traderRequestId=?";
        return jdbc.update(sql,targetSalesRequestId,traderRequestId)==1;
    }

}
