package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.SalesRepo;
import com.CitiTeam3.TPS.dao.SalesRequestDao;
import com.CitiTeam3.TPS.dao.TraderDao;
import com.CitiTeam3.TPS.domain.*;
import com.CitiTeam3.TPS.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SalesRequestController {
    @Autowired
    SalesRequestDao salesRequestDao;

    @Autowired
    TraderDao traderDao;

    @Autowired
    SalesService service;

    @RequestMapping("addSalesRequest")
    @ResponseBody
    public String addSalesrRequest(HttpServletRequest request, HttpSession session){
        SalesRequest st=new SalesRequest();
        Sales sales=((Sales)session.getAttribute("sales"));
        Trader trader=traderDao.getTraderByUserName(request.getParameter("traderName"));

        st.setTargetId(Integer.valueOf(trader.getTraderId()));
        st.setPrice(Double.valueOf(request.getParameter("price")));
        st.setAmount(Integer.valueOf(request.getParameter("amount")));
        st.setType(Integer.valueOf(request.getParameter("type")));
        st.setCusipId(request.getParameter("cusipId"));
        st.setSalesId(Integer.valueOf(sales.getSalesId()));
        if (salesRequestDao.addRequest(st))return "success";
        else return "false";
    }

    @RequestMapping("getSaleHistory")
    @ResponseBody
    public Map<String, Object> getSalesHistory(HttpSession session){
        Sales sales= (Sales) session.getAttribute("sales");
        List<SalerRequest> list=service.getSaleHistory(Integer.valueOf(sales.getSalesId()));
        Map<String ,Object> model=new HashMap<>();
        model.put("code",0);
        model.put("msg","");
        model.put("count",list.size());
        model.put("data",list);
        return model;
    }


}