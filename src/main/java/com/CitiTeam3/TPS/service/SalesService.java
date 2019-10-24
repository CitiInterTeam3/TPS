package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.SalesRepo;
import com.CitiTeam3.TPS.domain.SaleEntity;
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

    public List<SaleEntity> getSaleHistory(int saleId)
    {
        String sql="select * from salesRequest where salesId = ? order by issueDate desc";
        Object[] args=new Object[1];
        args[0]=saleId;
        List<SaleEntity> query = jdbcTemplate.query(sql,args,new RowMapper<SaleEntity>() {
            @Override
            public SaleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                SaleEntity saleEntity=new SaleEntity();
//                traderTransaction1.setTradeOrigSys(rs.getString("origSys"));
                saleEntity.setAmount(rs.getInt("amount"));
                saleEntity.setCusipId(rs.getString("cusipId"));
                saleEntity.setIssueDate(rs.getDate("issueDate"));
                saleEntity.setMatchId(rs.getInt("mathchedTraderRequest"));
                saleEntity.setPrice(rs.getDouble("price"));
                saleEntity.setStatus(rs.getInt("status"));
                saleEntity.setTargetId(rs.getInt("salesId"));
                saleEntity.setSalesRequestId(rs.getInt("salesRequestId"));
                saleEntity.setType(rs.getInt("type"));
                saleEntity.setRejectReason(rs.getString("rejectReason"));

                return saleEntity;
            }
        });
        return query;
    }
}
