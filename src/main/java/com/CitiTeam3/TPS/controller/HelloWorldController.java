package com.CitiTeam3.TPS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("helloworld")
public class HelloWorldController {

    @RequestMapping("hello")
    public String hello(){
        return "hello world";
    }
}
