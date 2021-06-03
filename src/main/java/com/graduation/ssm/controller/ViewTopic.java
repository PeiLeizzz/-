package com.graduation.ssm.controller;

import com.graduation.ssm.entity.Page;
import com.graduation.ssm.entity.StudentTopicSearch;
import com.graduation.ssm.entity.TeacherTopicSearch;
import com.graduation.ssm.service.TeacherTopicService;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.graduation.ssm.service.StudentTopicService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/view", method = RequestMethod.GET)
@Controller
public class ViewTopic {

    @Autowired
    private StudentTopicService studentTopicService;

    @Autowired
    private TeacherTopicService teacherTopicService;

    /**
     * 学生查看课题，控制因素有：student_id, page, limit
     * 注意：1. 人数已满的课题不显示
     *      2. 已截止的课题不显示
     *      3. 与该学生有关的课题置顶
     * @param request
     * @return
     */
    @RequestMapping(value = "/topicListStudent", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil studentGetTopicList(HttpServletRequest request) {
        System.out.println("收到 /view/topicListStudent 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题

        // TODO: 采用 session 获取当前登录的学生id
        String student_id = request.getParameter("student_id");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        StudentTopicSearch studentTopicSearch = new StudentTopicSearch();
        Page p = new Page();

        if (page != null) p.setPageNow(Integer.parseInt(page));
        if (limit != null) p.setPageSize(Integer.parseInt(limit));
        p.setStartPos(p.computeStartPos());

        ResultUtil rs = studentTopicService.getAllTopicList(student_id, p);
        return rs;
    }

    /**
     * 学生查看（搜索课题），控制因素有：student_id, college, teacher_id, topic_name, page, limit
     * 注意：1. 人数已满的课题不显示
     *      2. 已截止的课题不显示
     *      3. 搜索的情况下与学生有关的课题不用置顶
     * @param request
     * @return
     */
    @RequestMapping(value = "/topicSearch", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getTopicListBySearch(HttpServletRequest request) {
        System.out.println("收到 /view/topicSearch 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题

        // TODO: 采用 session 获取当前登录的学生id
        String student_id = request.getParameter("student_id");
        String college = request.getParameter("college");
        String teacher_id = request.getParameter("teacher_id");
        String topic_name = request.getParameter("topic_name");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        StudentTopicSearch studentTopicSearch = new StudentTopicSearch();
        Page p = new Page();

        if (student_id != null) studentTopicSearch.setStudent_id(student_id);
        if (college != null) studentTopicSearch.setCollege(college);
        if (teacher_id != null) studentTopicSearch.setTeacher_id(teacher_id);
        if (topic_name != null) studentTopicSearch.setTopic_name(topic_name);


        if (page != null) p.setPageNow(Integer.parseInt(page));
        if (limit != null) p.setPageSize(Integer.parseInt(limit));
        p.setStartPos(p.computeStartPos());

        ResultUtil rs = studentTopicService.getTopicListBySearch(studentTopicSearch, p);
        return rs;
    }

    /**
     * 教师查看自己发布的课题，控制因素有：teacher_id, topic_name, topic_status, page, limit
     * @param request
     * @return
     */
    @RequestMapping(value = "/topicOfTeacher", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getTopicListOfTeacher(HttpServletRequest request) {
        System.out.println("收到 /view/topicOfTeacher 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题

        // TODO: 采用 session 获取当前登录的教师 id
        String teacher_id = request.getParameter("teacher_id");
        String topic_status = request.getParameter("topic_status");
        String topic_name = request.getParameter("topic_name");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        TeacherTopicSearch teacherTopicSearch = new TeacherTopicSearch();
        Page p = new Page();

        if (teacher_id != null) teacherTopicSearch.setTeacher_id(teacher_id);
        if (topic_status != null) teacherTopicSearch.setTopic_status(topic_status);
        if (topic_name != null) teacherTopicSearch.setTopic_name(topic_name);

        if (page != null) p.setPageNow(Integer.parseInt(page));
        if (limit != null) p.setPageSize(Integer.parseInt(limit));
        p.setStartPos(p.computeStartPos());

        ResultUtil rs = teacherTopicService.getTopicListOfTeacher(teacherTopicSearch, p);
        return rs;
    }

    /**
     * 教师查看课题，控制因素有：college, teacher_id, topic_name, page, limit
     * 注意：1. 人数已满的课题不显示
     *      2. 已截止的课题不显示
     * @param request
     * @return
     */
    @RequestMapping(value = "/topicListTeacher", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil teacherGetTopicList(HttpServletRequest request) {
        System.out.println("收到 /view/topicListTeacher 请求");
        //response.addHeader("Access-Control-Allow-Origin", "*"); // 解决跨域问题

        String college = request.getParameter("college");
        // TODO: 采用 session 获取当前登录的教师 id
        String teacher_id = request.getParameter("teacher_id"); // 该 teacher_id 不是当前登录的教师的 id
        String topic_name = request.getParameter("topic_name");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        TeacherTopicSearch teacherTopicSearch = new TeacherTopicSearch();
        Page p = new Page();

        if (college != null) teacherTopicSearch.setCollege(college);
        if (teacher_id != null) teacherTopicSearch.setTeacher_id(teacher_id);
        if (topic_name != null) teacherTopicSearch.setTopic_name(topic_name);

        if (page != null) p.setPageNow(Integer.parseInt(page));
        if (limit != null) p.setPageSize(Integer.parseInt(limit));
        p.setStartPos(p.computeStartPos());

        ResultUtil rs = teacherTopicService.getTopicList(teacherTopicSearch, p);
        return rs;
    }
}
