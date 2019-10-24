package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.SalesRequestDao;
import com.CitiTeam3.TPS.dao.TraderRequestDao;
import com.CitiTeam3.TPS.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TraderRequestService {
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    TraderRequestDao traderRequestDao;
    @Autowired
    SalesRequestDao salesRequestDao;
    /**
     *
     * @param request
     * @return whether the request has been matched
     */
    public boolean addRequest(TraderRequest request){
        Number key=traderRequestDao.addRequest(request);
        List<SalesRequest> matchRequest=traderRequestDao.getMatchedSalesRequest(key.intValue());
        for (SalesRequest sq :matchRequest) {
            if (isPriceMatch(sq.getPrice(),request.getPrice())){
                matchTwoRequest(request,sq);
                return true;
            }
        }
        return false;
    }

    double threshod=0.00001;
    private boolean isPriceMatch(double d1,double d2){
        if (Math.abs(d1-d2)<threshod)return true;
        return  false;
    }

    public List<TraderEntity> getTraderRequest(int traderId){
        return traderRequestDao.getAllRequestByTraderId(traderId);
    }

    public void matchTwoRequest(TraderRequest traderRequest,SalesRequest salesRequest){
        traderRequest.setStatus(Status.PROCESSED.getValue());
        salesRequest.setStatus(Status.PROCESSED.getValue());
        traderRequest.setMatchId(salesRequest.getSalesRequestId());
        salesRequest.setMatchId(traderRequest.getTraderRequestId());
        traderRequestDao.updateStatus(traderRequest);
        traderRequestDao.updateMatchedSalesId(traderRequest);
        salesRequestDao.updateStatus(salesRequest);
        salesRequestDao.updateMatchedId(salesRequest);
    }

    public List<SalesRequest> getMatchRequest(int traderRequestId){
        return traderRequestDao.getMatchedSalesRequest(traderRequestId);
    }

    public List<TraderEntity> getTradeHistory(int traderId)
    {
        String sql="select * from traderRequest where traderId = ? order by issueDate desc";
        Object[] args=new Object[1];
        args[0]=traderId;
        List<TraderEntity> query = jdbc.query(sql,args,new RowMapper<TraderEntity>() {
            @Override
            public TraderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                TraderEntity traderEntity=new TraderEntity();
//                traderTransaction1.setTradeOrigSys(rs.getString("origSys"));
                traderEntity.setAmount(rs.getInt("amount"));
                traderEntity.setCusipId(rs.getString("cusipId"));
                traderEntity.setIssueDate(rs.getDate("issueDate"));
                traderEntity.setMatchId(rs.getInt("matchedSalesRequest"));
                traderEntity.setPrice(rs.getDouble("price"));
                traderEntity.setStatus(rs.getInt("status"));
                traderEntity.setTargetId(rs.getInt("traderId"));
                traderEntity.setTraderRequestId(rs.getInt("traderRequestId"));
                traderEntity.setType(rs.getInt("type"));
                traderEntity.setRejectReason(rs.getString("rejectReason"));
                return traderEntity;
            }
        });
        return query;
    }
}
