package com.graduation.ssm.controller;

import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.graduation.ssm.service.StudentTopicService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/post", method = RequestMethod.POST)
@Controller
public class StudentPost {

    @Autowired
    private StudentTopicService studentTopicService;


    /**
     * 学生取消预选
     * @param request
     * @return
     */
    @RequestMapping(value = "/cancelSelect", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil cancelSelect(HttpServletRequest request) {
        System.out.println("收到 /post/cancelSelect 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        // TODO: 采用 session 获取当前登录的学生id
        String student_id = request.getParameter("student_id");
        String topic_id = request.getParameter("topic_id");

        ResultUtil rs = studentTopicService.cancelSelect(student_id, topic_id);
        return rs;
    }

    /**
     * 学生预选该课题
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectTopic", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil selectTopic(HttpServletRequest request) {
        System.out.println("收到 /post/selectTopic 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        // TODO: 采用 session 获取当前登录的学生id
        String student_id = request.getParameter("student_id");
        String topic_id = request.getParameter("topic_id");

        ResultUtil rs = studentTopicService.selectTopic(student_id, topic_id);
        return rs;
    }
}
