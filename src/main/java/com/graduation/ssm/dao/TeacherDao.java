package com.graduation.ssm.dao;

import com.graduation.ssm.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getAllTeacherList(@Param("teacher") Teacher teacher);
}
