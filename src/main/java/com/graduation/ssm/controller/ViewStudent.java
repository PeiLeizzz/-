package com.graduation.ssm.controller;

import com.graduation.ssm.service.StudentService;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 路由功能：接收查看学生相关信息的请求
 */
@RequestMapping(value = "/view", method = RequestMethod.GET)
@Controller
public class ViewStudent {

    @Autowired
    private StudentService studentService;

    /**
     * 查看预选某个课题的学生列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/preSelectStudent", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getTopicList(HttpServletRequest request) {
        System.out.println("收到 /view/preSelectStudent 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        // TODO: 采用 session 获取当前登录的教师id
        String teacher_id = request.getParameter("teacher_id");
        // TODO: 数据库中 topic_id 是 int 类型，会不会有问题？
        String topic_id = request.getParameter("topic_id");

        ResultUtil rs = studentService.getPreSelectStudentList(topic_id);
        return rs;
    }

    /**
     * 查询与某个学生相关的课题数量
     * @param request
     * @return
     */
    @RequestMapping(value = "/studentChoiceCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getStudentChoice(HttpServletRequest request) {
        System.out.println("收到 /view/studentChoiceCount 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题
        // TODO: 采用 session 获取当前登录的学生id
        String student_id = request.getParameter("student_id");

        ResultUtil rs = studentService.getStudentChoiceCount(student_id);
        return rs;
    }
}

