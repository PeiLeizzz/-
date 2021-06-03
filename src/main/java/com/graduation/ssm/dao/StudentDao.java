package com.graduation.ssm.dao;

import com.graduation.ssm.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<Student> getPreSelectStudentList(@Param("topic_id") String topic_id);
}
