package com.graduation.ssm.dao;

import com.graduation.ssm.entity.Student;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface StudentDao {
    /**
     * 查看预选某个课题的学生列表
     * @param topic_id
     * @return
     */
    List<Student> getPreSelectStudentList(@Param("topic_id") String topic_id);
}
