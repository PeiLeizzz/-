package com.graduation.ssm.dao;

import com.graduation.ssm.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TeacherDao {
    /**
     * 查看某个院系的教师列表
     * @param college
     * @return
     */
    List<Teacher> getAllTeacherList(@Param("college") String college);
}
