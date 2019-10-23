package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.SalesRequestDao;
import com.CitiTeam3.TPS.dao.TraderRequestDao;
import com.CitiTeam3.TPS.domain.SalesRequest;
import com.CitiTeam3.TPS.domain.Status;
import com.CitiTeam3.TPS.domain.TraderRequest;
import com.CitiTeam3.TPS.domain.TraderTransaction;
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
        traderRequestDao.addRequest(request);
        List<SalesRequest> matchRequest=traderRequestDao.getMatchedSalesRequest(request.getTraderRequestId());
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

    public List<TraderRequest> getTraderRequest(int traderId){
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

    public List<TraderRequest> getTradeHistory(int traderId)
    {
        String sql="select * from traderRequest where traderId = ? order by issueDate desc";
        Object[] args=new Object[1];
        args[0]=traderId;
        List<TraderRequest> query = jdbc.query(sql,args,new RowMapper<TraderRequest>() {
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
                traderRequest.setTraderRequestId(rs.getInt("traderRequestedId"));
                traderRequest.setType(rs.getInt("type"));
                return traderRequest;
            }
        });
        return query;
    }
}
