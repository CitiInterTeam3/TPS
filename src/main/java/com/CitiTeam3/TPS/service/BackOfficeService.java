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

        sql="select matchedSalesRequest from traderRequest where traderRequestId=?";
        return rowCount;
    }
}
