package com.graduation.ssm.service;

import com.graduation.ssm.dao.getUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service("UserLoginImpl")
public class UserLoginImpl implements UserLogin {
    @Autowired
    private getUser getuser;
    public String login(String userId, String password, HttpServletRequest request) {
        HashMap<String,Object> param=new HashMap<String, Object>();
        param.put("userId",userId);
        HashMap<String,Object> list=getuser.getUserList(param);
        System.out.println(list);
        if(list == null) return "fail";
        if(userId.equals(list.get("userId"))){
            if(password.equals(list.get("password"))){

                //放入session，此处暂时无用，在下篇文章：配置拦截器的时候才有用，所以先写上
                HttpSession session=request.getSession();
                session.setAttribute("userId",userId);
                //
                //返回正确
                return "success";
            }
        }
        return "fail";
    }
}

