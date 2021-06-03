package com.graduation.ssm.service;

import com.graduation.ssm.dao.TeacherDao;
import com.graduation.ssm.dao.TopicDao;
import com.graduation.ssm.entity.Teacher;
import com.graduation.ssm.entity.Topic;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "TeacherServiceImpl")
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TopicDao topicDao;

    @Override
    public ResultUtil getTeacherList(Teacher teacher) {
        List<Teacher> teachers = teacherDao.getAllTeacherList(teacher);
//        PageInfo<Topic> pageInfo = new PageInfo<Topic>(topics);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(teachers.size());
        resultUtil.setData(teachers);
        return resultUtil;
    }
}

