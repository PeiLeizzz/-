package com.graduation.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FirstController {
    @RequestMapping(value = "test", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public String test(){
        System.out.println("这是我的第一个Controller");
        return "login";
    }
}

