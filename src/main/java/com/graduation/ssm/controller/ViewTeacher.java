package com.graduation.ssm.controller;

import com.graduation.ssm.entity.Teacher;
import com.graduation.ssm.service.TeacherService;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/view", method = RequestMethod.GET)
@Controller
public class ViewTeacher {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/teacherList", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getTopicList(HttpServletRequest request) {
        System.out.println("收到 /view/teacherList 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        String college = request.getParameter("college");

        Teacher teacher = new Teacher();
        teacher.setCollege(college);
        ResultUtil rs = teacherService.getTeacherList(teacher);
        return rs;
    }
}

