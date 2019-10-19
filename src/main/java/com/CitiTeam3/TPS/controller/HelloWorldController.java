package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.TraderDao;
import com.CitiTeam3.TPS.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {

    @Autowired
    TraderDao dao;

    @RequestMapping("/getTrader")
    public String hello(Model mv, HttpServletRequest request){
        String id=request.getParameter("traderId");
        Trader trader=dao.getTraderById(id);
        mv.addAttribute("trader",trader);
        return  "test";
    }
}
