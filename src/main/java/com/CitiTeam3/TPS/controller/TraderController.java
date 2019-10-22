package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.TraderTransactionDao;
import com.CitiTeam3.TPS.domain.Trader;
import com.CitiTeam3.TPS.domain.TraderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TraderController {
    @Autowired
    TraderTransactionDao traderTransactionDao;

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
}
