package com.graduation.ssm.controller;
import com.graduation.ssm.service.UserLoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Login {
    @Autowired
    private UserLoginImpl userLogin;

    /**
     * 访问login页面
     * @return
     */
    @RequestMapping(value = {"","/login"},method = RequestMethod.GET)
    public String init(){
        return "login";
    }

    /**
     * 实现登录接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/userLogin", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    //@ResponseBody
    public String loginByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId=request.getParameter("userId");
        String password=request.getParameter("password");
        String result=userLogin.login(userId,password,request);
        if("success".equals(result))
        {
            /*
             * forward()服务器内部跳转，前端路径不变，但是内部已经变了，相当于在半路改变了request，但仍然是原来的申请，所以是一次申请，用request是直接改申请，与前端无关
             */
            return "forward:/main";
        }
        else {
            /*
             * redirect()前端跳转，这是两次申请，所以前端路径已经改变，用response是告诉前端要改路径
             */
            return "redirect:/login";
        }
    }

}

