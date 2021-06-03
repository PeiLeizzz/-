package com.graduation.ssm.service;

import com.graduation.ssm.entity.Page;
import com.graduation.ssm.entity.Teacher;
import com.graduation.ssm.util.ResultUtil;

public interface TeacherService {
    public ResultUtil getTeacherList(Teacher teacher);
}
