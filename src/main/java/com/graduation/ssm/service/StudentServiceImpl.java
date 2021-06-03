package com.graduation.ssm.service;

import com.graduation.ssm.dao.StudentDao;
import com.graduation.ssm.dao.TeacherDao;
import com.graduation.ssm.dao.TopicDao;
import com.graduation.ssm.entity.Student;
import com.graduation.ssm.entity.Teacher;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "StudentServiceImpl")
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TopicDao topicDao;

    @Override
    public ResultUtil getPreSelectStudentList(String topic_id) {
        List<Student> students = studentDao.getPreSelectStudentList(topic_id);
//        PageInfo<Topic> pageInfo = new PageInfo<Topic>(topics);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(students.size());
        resultUtil.setData(students);
        return resultUtil;
    }

    @Override
    public ResultUtil getStudentChoice(String student_id) {
        ResultUtil resultUtil = null;
        if (topicDao.getStudentChoice(student_id) > 0) {
            resultUtil = ResultUtil.error();
        }
        else {
            resultUtil = ResultUtil.ok();
        }
        return resultUtil;
    }
}