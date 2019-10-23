package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.TraderRequestDao;
import com.CitiTeam3.TPS.domain.TraderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderRequestService {

    @Autowired
    TraderRequestDao traderRequestDao;

    public boolean addRequest(TraderRequest request){
        int traderId=traderRequestDao.addRequest(request);
//        List<SalesRequest> matchRequest=traderRequestDao.getMatchedSalesRequest(traderId);
//        for (SalesRequest sq :matchRequest) {
//            if (isPriceMatch(sq.getPrice(),request.getPrice())){
//                matchTwoRequest(traderId,sq.getSalesRequestId());
//                break;
//            }
//        }
        return true;
    }

    double threshod=0.00001;
    private boolean isPriceMatch(double d1,double d2){
        if (Math.abs(d1-d2)<threshod)return true;
        return  false;
    }

    public List<TraderRequest> getTraderRequest(int traderId){
        return traderRequestDao.getAllRequestByTraderId(traderId);
    }

    public boolean matchTwoRequest(int traderId,int requestId){
        return false;
    }
}
