package com.graduation.ssm.controller;

import com.graduation.ssm.service.TeacherTopicService;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/post", method = RequestMethod.POST)
@Controller
public class TeacherPost {

    @Autowired
    private TeacherTopicService teacherTopicService;


    @RequestMapping(value = "/agreeStudent", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil agreeStudent(HttpServletRequest request) {
        System.out.println("收到 /post/agreeStudent 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        // TODO: 采用 session 获取当前登录的教师id
        String teacher_id = request.getParameter("teacher_id");
        String student_id = request.getParameter("student_id");
        String topic_id = request.getParameter("topic_id");

        ResultUtil rs = teacherTopicService.agreeStudent(student_id, topic_id);
        return rs;
    }

    @RequestMapping(value = "/disagreeStudent", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil disagreeStudent(HttpServletRequest request) {
        System.out.println("收到 /post/disAgreeStudent 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        // TODO: 采用 session 获取当前登录的教师id
        String teacher_id = request.getParameter("teacher_id");
        String student_id = request.getParameter("student_id");
        String topic_id = request.getParameter("topic_id");

        ResultUtil rs = teacherTopicService.disagreeStudent(student_id, topic_id);
        return rs;
    }

    @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil deleteTopic(HttpServletRequest request) {
        System.out.println("收到 /post/deleteTopic 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        // TODO: 采用 session 获取当前登录的教师id
        String teacher_id = request.getParameter("teacher_id");
        String topic_id = request.getParameter("topic_id");

        ResultUtil rs = teacherTopicService.deleteTopic(topic_id);
        return rs;
    }
}
