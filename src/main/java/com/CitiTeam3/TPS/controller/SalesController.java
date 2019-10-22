package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.domain.Sales;
import com.CitiTeam3.TPS.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SalesController {
    @Autowired
    SalesService service;

    @RequestMapping("addSales")
    @ResponseBody
    public String addSales(){
        Sales sales=new Sales("1001","why","123");
        service.save(sales);
        return "success";
    }
}
