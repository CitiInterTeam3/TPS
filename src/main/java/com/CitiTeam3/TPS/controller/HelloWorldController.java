package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.TraderDao;
import com.CitiTeam3.TPS.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloWorldController {

    @Autowired
    TraderDao dao;

    @RequestMapping("/hello")
    public String hello(){

        Trader trader=new Trader();
        trader.setUserName("why");
        trader.setTraderId("1001");
        trader.setPsw("123");
        int i=dao.addTrader(trader);
        if (i>0)return "success";
        return "false";
    }
}
