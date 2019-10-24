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
    public String backOfficeAccept(HttpServletRequest request){
        int rowCount=service.accepted(Integer.valueOf(request.getParameter("traderRequestId")));
        if(rowCount>0)
            return "success";
        else
            return "fail";
    }

    @RequestMapping("backOfficeReject")
    @ResponseBody
    public String backOfficeReject(HttpServletRequest request){
        int rowCount=service.rejected(Integer.valueOf(request.getParameter("traderRequestId")),request.getParameter("rejectReason"));
        if(rowCount>0)
            return "success";
        else
            return "fail";
    }
    
    
}
