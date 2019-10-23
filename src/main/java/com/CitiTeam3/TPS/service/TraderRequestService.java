package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.SalesRequestDao;
import com.CitiTeam3.TPS.dao.TraderRequestDao;
import com.CitiTeam3.TPS.domain.SalesRequest;
import com.CitiTeam3.TPS.domain.Status;
import com.CitiTeam3.TPS.domain.TraderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderRequestService {

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
}
