package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.SalerDao;
import com.CitiTeam3.TPS.dao.TraderDao;
import com.CitiTeam3.TPS.domain.Sales;
import com.CitiTeam3.TPS.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalerService {
    @Autowired
    SalerDao dao;

    /**
     * 根据username和psw判断是否为有效用户，若有效返回相应trader对象，否则返回null
     * @param userName
     * @param psw
     * @return
     */
    public Sales isValid(String userName, String psw){
        Sales trader=dao.getSalerByUserName(userName);
        if (trader!=null&&!trader.getPassword().equals(psw))return null;
        else return trader;
    }
}
