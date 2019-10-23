package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.ProductDao;
import com.CitiTeam3.TPS.dao.SalerDao;
import com.CitiTeam3.TPS.dao.TraderTransactionDao;
import com.CitiTeam3.TPS.domain.Sales;
import com.CitiTeam3.TPS.domain.Trader;
import com.CitiTeam3.TPS.domain.TraderTransaction;
import com.CitiTeam3.TPS.service.SalerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SaleController {
    @Autowired
    SalerDao dao;
    @Autowired
    ProductDao productDao;
    @Autowired
    SalerService service;
    @Autowired
    TraderTransactionDao traderTransactionDao;
    @RequestMapping("/SalesInput")
    public String SalesInput(Model mv, HttpSession session){
        return "addSaleTrans";
    }

    @RequestMapping("/SalerLogin")
    public String salerLogin()
    {
        return "/sale/salerHomePage";
    }
    @RequestMapping("/loginForSales")
    @ResponseBody
    public String login(HttpServletRequest request){
        String username=request.getParameter("userName");
        Sales trader=service.isValid(request.getParameter("userName"),
                request.getParameter("psw"));
        if (trader!=null){

            request.getSession().setAttribute("sales",trader);
            return "success";
        }
        else return "faild";
    }

    @RequestMapping("/sale/salerPersonalHome")
    public String personHome(Model model,HttpSession session){
        Object sales=session.getAttribute("sales");
        if (sales==null){
            model.addAttribute("msg","session time out");
            return "error";
        }else {
            model.addAttribute("sales",sales);
            return "sale/salerPersonalHome";
        }
    }
    @RequestMapping("/sale/inputTradePage")
    public String getInputTradePage(){
        return "sale/addSaleTrans";
    }
    @RequestMapping("/addSaleTrans")
    @ResponseBody
    public String saveTraderTransaction(HttpSession session, HttpServletRequest httpServletRequest){
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
        if(modifyCount>0)
            return "success";
        else
            return "fail";
    }
}
