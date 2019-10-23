package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.domain.Trader;
import com.CitiTeam3.TPS.domain.TraderRequest;
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
public class ComformListController {
    @RequestMapping("/matchedTrade")
    public String getConfirmList(HttpSession session, HttpServletRequest request, Model model){
//        Trader trader= (Trader) session.getAttribute("trader");
//        List<TraderRequest> list=service.getTraderRequest(Integer.valueOf(trader.getTraderId()));
//        Map<String ,Object> model=new HashMap<>();
//        model.put("code",0);
//        model.put("msg","");
//        model.put("count",list.size());
//        model.put("data",list);
        model.addAttribute("traderRequestId",request.getParameter("traderRequestId"));
        return "trader/matchedTrade";
    }
}
