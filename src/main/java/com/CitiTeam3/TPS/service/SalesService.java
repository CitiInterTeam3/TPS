package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.SalesRepo;
import com.CitiTeam3.TPS.domain.SalerRequest;
import com.CitiTeam3.TPS.domain.Sales;
import com.CitiTeam3.TPS.domain.TraderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class SalesService {
    @Autowired
    SalesRepo salesRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Sales save(Sales sales){
        return salesRepo.save(sales);
    }

    public List<SalerRequest> getSaleHistory(int saleId)
    {
        String sql="select * from salesRequest where salesId = ? order by issueDate desc";
        Object[] args=new Object[1];
        args[0]=saleId;
        List<SalerRequest> query = jdbcTemplate.query(sql,args,new RowMapper<SalerRequest>() {
            @Override
            public SalerRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
                SalerRequest salerRequest=new SalerRequest();
//                traderTransaction1.setTradeOrigSys(rs.getString("origSys"));
                salerRequest.setAmount(rs.getInt("amount"));
                salerRequest.setCusipId(rs.getString("cusipId"));
                salerRequest.setIssueDate(rs.getDate("issueDate"));
                salerRequest.setMatchId(rs.getInt("mathchedTraderRequest"));
                salerRequest.setPrice(rs.getDouble("price"));
                salerRequest.setStatus(rs.getInt("status"));
                salerRequest.setTargetId(rs.getInt("salesId"));
                salerRequest.setTraderRequestId(rs.getInt("salesRequestId"));
                salerRequest.setType(rs.getInt("type"));
                return salerRequest;
            }
        });
        return query;
    }
}
