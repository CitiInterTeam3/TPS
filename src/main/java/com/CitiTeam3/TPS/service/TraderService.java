package com.CitiTeam3.TPS.service;

import com.CitiTeam3.TPS.dao.TraderDao;
import com.CitiTeam3.TPS.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderService {

    @Autowired
    TraderDao dao;

    /**
     * 根据username和psw判断是否为有效用户，若有效返回相应trader对象，否则返回null
     * @param userName
     * @param psw
     * @return
     */
    public Trader isValid(String userName,String psw){
        Trader trader=dao.getTraderByUserName(userName);
        if (trader!=null&&!trader.getPsw().equals(psw))return null;
        else return trader;
    }
}
