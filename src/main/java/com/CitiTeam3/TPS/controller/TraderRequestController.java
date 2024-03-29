package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.SalerDao;
import com.CitiTeam3.TPS.dao.SalesRepo;
import com.CitiTeam3.TPS.dao.TraderTransactionDao;
import com.CitiTeam3.TPS.domain.*;
import com.CitiTeam3.TPS.service.TraderRequestService;
import com.CitiTeam3.TPS.service.TraderService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TraderRequestController {
    @Autowired
    TraderTransactionDao traderTransactionDao;

    @Autowired
    SalerDao salerDao;

    @Autowired
    SalesRepo salesRepo;

    @Autowired
    TraderRequestService service;

    @RequestMapping("/addTraderTrans")
    @ResponseBody
    public String saveTraderTransaction(HttpSession session,HttpServletRequest httpServletRequest){
        TraderTransaction traderTransaction=new TraderTransaction();

        traderTransaction.setTradeOrigSys(httpServletRequest.getParameter("origSys"));
        traderTransaction.setSaleId(httpServletRequest.getParameter("saleId"));
        traderTransaction.setPrice(Double.valueOf(httpServletRequest.getParameter("price")));
        traderTransaction.setQuantity(Integer.valueOf(httpServletRequest.getParameter("quantity")));
        traderTransaction.setCusip(httpServletRequest.getParameter("cusipId"));

        Trader trader= (Trader) session.getAttribute("trader");

        traderTransaction.setTraderId(trader.getTraderId());
        int modifyCount= traderTransactionDao.addTraderTransaction(traderTransaction);
        System.out.println(modifyCount);
        if(modifyCount!=0)
            return "success";
        else
            return "fail";
    }

    @RequestMapping("addTraderRequest")
    @ResponseBody
    public String addTraderRequest(HttpServletRequest request,HttpSession session){
        TraderRequest tr=new TraderRequest();
        String traderId=((Trader)session.getAttribute("trader")).getTraderId();
        //Sales sales=salerDao.getSalerByUserName(request.getParameter("salesName"));
        Sales sales=salesRepo.getBySalesName(request.getParameter("salesName"));
        if (sales==null)return "no such sales";
        tr.setTargetId(Integer.valueOf(sales.getSalesId()));
        tr.setPrice(Double.valueOf(request.getParameter("price")));
        tr.setAmount(Integer.valueOf(request.getParameter("amount")));
        tr.setType(Integer.valueOf(request.getParameter("type")));
        tr.setCusipId(request.getParameter("cusipId"));
        tr.setTraderId(Integer.valueOf(traderId));
        boolean ismatched=service.addRequest(tr);
        return "success";
    }

    @RequestMapping("getConfirmList")
    @ResponseBody
    public Map<String, Object> getConfirmList(HttpSession session){
        Trader trader= (Trader) session.getAttribute("trader");
        List<TraderEntity> list=service.getTraderRequest(Integer.valueOf(trader.getTraderId()));
        Map<String ,Object> model=new HashMap<>();
        model.put("code",0);
        model.put("msg","");
        model.put("count",list.size());
        model.put("data",list);
        return model;
    }

    @RequestMapping("getMatchedSalesRequest")
    @ResponseBody
    public Map<String, Object> getMatchSalesRequest(HttpServletRequest request){
        List<SalesRequest> list=service.getMatchRequest(Integer.valueOf(request.getParameter("traderRequestId")));
        Map<String ,Object> model=new HashMap<>();
        model.put("code",0);
        model.put("msg","");
        model.put("count",list.size());
        model.put("data",list);
        return model;
    }

    @RequestMapping("/getTraderHistory")
    @ResponseBody
    public Map<String, Object> getTraderHistory(HttpSession session){
        Trader trader= (Trader) session.getAttribute("trader");
        List<TraderEntity> list=service.getTradeHistory(Integer.valueOf(trader.getTraderId()));
        Map<String ,Object> model=new HashMap<>();
        model.put("code",0);
        model.put("msg","");
        model.put("count",list.size());
        model.put("data",list);
        return model;
    }

    @RequestMapping("matchRequest")
    @ResponseBody
    public String matchRequest(HttpServletRequest request){
        int traderRequestId=Integer.valueOf(request.getParameter("traderRequestId"));
        int salesRequestId=Integer.valueOf(request.getParameter("salesRequestId"));
        TraderRequest traderRequest=new TraderRequest();
        traderRequest.setTraderRequestId(traderRequestId);
        SalesRequest salesRequest=new SalesRequest();
        salesRequest.setSalesRequestId(salesRequestId);
        try{
            service.matchTwoRequest(traderRequest,salesRequest);
            return "success";
        }catch (DataAccessException e){
            e.printStackTrace();
            return "failed";
        }

    }
}
