package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.TraderTransactionDao;
import com.CitiTeam3.TPS.domain.Sales;
import com.CitiTeam3.TPS.domain.Trader;
import com.CitiTeam3.TPS.domain.TraderTransaction;
import com.CitiTeam3.TPS.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SalesController {
    @Autowired
    SalesService service;

    @RequestMapping("addSales")
    @ResponseBody
    public String addSales(){
        Sales sales=new Sales("123","wh1231y","213123");
        service.save(sales);
        return "success";
    }


}
