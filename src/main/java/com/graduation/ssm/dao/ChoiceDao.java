package com.graduation.ssm.dao;

import org.apache.ibatis.annotations.Param;

public interface ChoiceDao {
    /**
     * 查询该学生是否已有课题（包括预选和已选）
     * @param student_id
     * @return
     */
    int getStudentChoiceCount(@Param("student_id") String student_id);

    /**
     * 学生取消预选课题
     * @param student_id
     * @param topic_id
     * @return
     */
    int cancelSelect(@Param("student_id") String student_id, @Param("topic_id") String topic_id);

    /**
     * 学生预选该课题
     * @param student_id
     * @param topic_id
     * @return
     */
    int selectTopic(@Param("student_id") String student_id, @Param("topic_id") String topic_id);

    /**
     * 教师同意学生预选
     * @param student_id
     * @param topic_id
     * @return
     */
    int updateAgree(@Param("student_id") String student_id, @Param("topic_id") String topic_id);

    /**
     * 教师拒绝学生预选
     * @param student_id
     * @param topic_id
     * @return
     */
    int updateDisagree(@Param("student_id") String student_id, @Param("topic_id") String topic_id);
}
