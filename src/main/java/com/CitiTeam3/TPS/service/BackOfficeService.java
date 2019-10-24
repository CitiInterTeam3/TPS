package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.TraderRequestDao;
import com.CitiTeam3.TPS.domain.TraderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BackOfficeService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TraderRequestDao traderRequestDao;

    public List<TraderRequest> getProcessedTransaction()
    {
        String sql="select * from traderRequest where status=2";
        List<TraderRequest> query = jdbcTemplate.query(sql,new RowMapper<TraderRequest>() {
            @Override
            public TraderRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
                TraderRequest traderRequest=new TraderRequest();
//                traderTransaction1.setTradeOrigSys(rs.getString("origSys"));
                traderRequest.setAmount(rs.getInt("amount"));
                traderRequest.setCusipId(rs.getString("cusipId"));
                traderRequest.setIssueDate(rs.getDate("issueDate"));
                traderRequest.setMatchId(rs.getInt("matchedSalesRequest"));
                traderRequest.setPrice(rs.getDouble("price"));
                traderRequest.setStatus(rs.getInt("status"));
                traderRequest.setTargetId(rs.getInt("traderId"));
                traderRequest.setTraderRequestId(rs.getInt("traderRequestId"));
                traderRequest.setType(rs.getInt("type"));
                return traderRequest;
            }
        });
        return query;
    }

    public int accepted(int traderRequestId)
    {
        String sql="update traderRequest set status=3 where traderRequestId=?";
        int rowCount=jdbcTemplate.update(sql,traderRequestId);
        Object []args=new Object[1];
        args[0]=traderRequestId;
        sql="select matchedSalesRequest from traderRequest where traderRequestId=?";
        List<Integer> query=jdbcTemplate.query(sql,args,new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer saleRequestId;
                saleRequestId=rs.getInt("matchedSalesRequest");
                return saleRequestId;
            }
        });
        sql="update salesRequest set status=3 where salesRequestId=?";
        if(query.size()<1)
            return 0;
        rowCount=jdbcTemplate.update(sql,query.get(0));
        return rowCount;
    }

    public int rejected(int traderRequestId,String reason)
    {
        String sql="update traderRequest set status=4, rejectReason=? where traderRequestId= ?";
        int rowCount=jdbcTemplate.update(sql,reason,traderRequestId);


        Object []args=new Object[1];
        args[0]=traderRequestId;
        sql="select matchedSalesRequest from traderRequest where traderRequestId=?";
        List<Integer> query=jdbcTemplate.query(sql,args,new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer saleRequestId;
                saleRequestId=rs.getInt("matchedSalesRequest");
                return saleRequestId;
            }
        });
        if(query.size()<1)
            return 0;
        sql="update salesRequest set status=4, rejectReason=? where salesRequestId= ?";
        jdbcTemplate.update(sql,reason,query.get(0));
        return rowCount;
    }
}
