package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.dao.ProductDao;
import com.CitiTeam3.TPS.dao.TraderDao;
import com.CitiTeam3.TPS.domain.Product;
import com.CitiTeam3.TPS.domain.Trader;
import com.CitiTeam3.TPS.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    TraderDao dao;
    @Autowired
    ProductDao productDao;
    @Autowired
    TraderService service;

    @RequestMapping("/getTrader")
    public String hello(Model mv, HttpServletRequest request){
        String id=request.getParameter("traderId");
        System.out.println("这是交易者信息");
        Trader trader=dao.getTraderById(id);
        if (trader==null){
            mv.addAttribute("msg","no such trader");
            return "error";
        }
        mv.addAttribute("trader",trader);
        return  "test";
    }

    @RequestMapping("/homePage")
    public String homePage(Model mv, HttpServletRequest request){
        return  "homePage";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request){
        String username=request.getParameter("userName");
        Trader trader=service.isValid(request.getParameter("userName"),
                request.getParameter("psw"));
        if (trader!=null){
            request.getSession().setAttribute("trader",trader);
            return "success";
        }
        else return "faild";
    }

    @RequestMapping("/personalHome")
    public String personHome(Model model,HttpSession session){
        Object trader=session.getAttribute("trader");
        if (trader==null){
            model.addAttribute("msg","session time out");
            return "error";
        }else {
            model.addAttribute("trader",trader);
            return "personalHome";
        }
    }

    @RequestMapping("/getProduct")
    public String getProduct(Model mv,HttpServletRequest request)
    {
        String id=request.getParameter("cusipId");
        System.out.println("this is the information of cusip");
        Product product=productDao.getProductId(id);
        mv.addAttribute("product",product);
        return "product";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("trader");
        return "homePage";
    }
}
