package com.CitiTeam3.TPS.controller;

import com.CitiTeam3.TPS.domain.TraderRequest;
import com.CitiTeam3.TPS.service.BackOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BackOfficeController {
    @Autowired
    BackOfficeService service;

    @RequestMapping("getProcessedTransaction")
    @ResponseBody
    public Map<String, Object> getProcessedTransaction(){
        List<TraderRequest> list=service.getProcessedTransaction();
        Map<String ,Object> model=new HashMap<>();
        model.put("code",0);
        model.put("msg","");
        model.put("count",list.size());
        model.put("data",list);
        return model;
    }
    @RequestMapping("backOffice")
    public String backOffice()
    {
        return "backOffice";
    }

    @RequestMapping("backOfficeAccept")
    @ResponseBody
    public String login(HttpServletRequest request){
//        Trader trader=service.isValid(request.getParameter("userName"),
//                request.getParameter("psw"));
//        if (trader!=null){
//            request.getSession().setAttribute("trader",trader);
//            return "success";
//        }
//        else return "faild";
        return "hello134243";
    }
    
    
}
