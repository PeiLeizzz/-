package com.graduation.ssm.service;

import com.graduation.ssm.dao.TeacherDao;
import com.graduation.ssm.entity.Teacher;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用于处理查看教师相关的信息的逻辑
 */
@Service(value = "TeacherServiceImpl")
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    /**
     * 查看某院系的教师列表
     * @param college 院系名称
     * @return
     */
    @Override
    public ResultUtil getTeacherList(String college) {
        ResultUtil resultUtil = null;
        try {
            List<Teacher> teachers = teacherDao.getAllTeacherList(college);
            resultUtil = new ResultUtil();
            resultUtil.setCode(0);
            resultUtil.setCount(teachers.size());
            resultUtil.setData(teachers);
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("获取某院系教师列表出错！");
        }
        return resultUtil;
    }
}

