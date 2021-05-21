package com.graduation.ssm.controller;

import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.graduation.ssm.service.TopicService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ViewTopic {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getTopicList() {
        System.out.println("收到 view 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        ResultUtil rs = topicService.getTopicList();
        return rs;
    }
}
