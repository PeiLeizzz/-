package com.graduation.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main {

    @RequestMapping(value = "/main")
    public String ToMain(){
        return "main";
    }
}


