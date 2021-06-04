package com.graduation.ssm.service;

import com.graduation.ssm.dao.ChoiceDao;
import com.graduation.ssm.dao.StudentDao;
import com.graduation.ssm.dao.TopicDao;
import com.graduation.ssm.entity.Student;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 用于处理查看学生相关的信息的逻辑
 */
@Service(value = "StudentServiceImpl")
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ChoiceDao choiceDao;

    /**
     * 查看预选某个课题的学生列表
     * @param topic_id 课题 id
     * @return 学生数据集合
     */
    @Override
    public ResultUtil getPreSelectStudentList(String topic_id) {
        ResultUtil resultUtil = null;
        try {
            List<Student> students = studentDao.getPreSelectStudentList(topic_id);
            resultUtil = new ResultUtil();
            resultUtil.setCode(0);
            resultUtil.setCount(students.size());
            resultUtil.setData(students);
        }
        catch (Exception e) {
            resultUtil = ResultUtil.error("获取预选学生列表出错！");
        }
        return resultUtil;
    }

    /**
     * 查询与某个学生相关的课题数量
     * @param student_id
     * @return 课题数量结果，count 为 0 或 1
     */
    @Override
    public ResultUtil getStudentChoiceCount(String student_id) {
        ResultUtil resultUtil = null;
        if (choiceDao.getStudentChoiceCount(student_id) > 0) {
            resultUtil = ResultUtil.error("该学生已选课题");
        }
        else {
            resultUtil = ResultUtil.ok("该学生未选课题！");
        }
        return resultUtil;
    }
}