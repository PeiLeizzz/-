package com.graduation.ssm.dao;

import com.graduation.ssm.entity.*;
import com.graduation.ssm.util.Page;
import com.graduation.ssm.util.StudentTopicSearch;
import com.graduation.ssm.util.TeacherTopicSearch;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TopicDao {
    /**
     * 查询和学生有关的课题（预选或已选）
     * @param student_id
     * @return
     */
    Topic getTopicWithStudent(String student_id);

    /**
     * 学生端查询所有课题个数
     * @param student_id
     * @return
     */
    int studentGetAllTopicCount(@Param("student_id") String student_id);

    /**
     * 学生端展示所有课题
     * @param student_id
     * @param p
     * @return
     */
    List<Topic> studentGetAllTopicList(@Param("student_id") String student_id, @Param("page") Page p);

    /**
     * 学生端查询所有满足条件的课题个数
     * @param studentTopicSearch
     * @return
     */
    int studentGetTopicListCountBySearch(@Param("search") StudentTopicSearch studentTopicSearch);

    /**
     * 学生端查询所有满足条件的课题
     * @param studentTopicSearch
     * @param p
     * @return
     */
    List<Topic> studentGetTopicListBySearch(@Param("search") StudentTopicSearch studentTopicSearch, @Param("page") Page p);

    /**
     * 教师端查询所有满足条件的课题个数（教师发布的）
     * @param teacherTopicSearch
     * @return
     */
    int getTopicListCountOfTeacherBySearch(@Param("search") TeacherTopicSearch teacherTopicSearch);

    /**
     * 教师端查询所有满足条件的课题（教师发布的）
     * @param teacherTopicSearch
     * @param p
     * @return
     */
    List<Topic> getTopicListOfTeacherBySearch(@Param("search") TeacherTopicSearch teacherTopicSearch, @Param("page") Page p);

    /**
     * 教师端查询所有满足条件的课题个数
     * @param teacherTopicSearch
     * @return
     */
    int teacherGetTopicListCount(@Param("search") TeacherTopicSearch teacherTopicSearch);

    /**
     * 教师端查询所有满足条件的课题
     * @param teacherTopicSearch
     * @param p
     * @return
     */
    List<Topic> teacherGetTopicList(@Param("search") TeacherTopicSearch teacherTopicSearch, @Param("page") Page p);

    /**
     * 查询当前该课题已有多少人
     * @param topic_id
     * @return
     */
    int getNowPerson(@Param("topic_id") String topic_id);

    /**
     * 查询该课题可接收的最大人数
     * @param topic_id
     * @return
     */
    int getMaxPerson(@Param("topic_id") String topic_id);

    /**
     * 删除课题详情
     * @param topic_id
     * @return
     */
    int deleteTopicInfo(@Param("topic_id") String topic_id);

    /**
     * 教师删除课题
     * @param topic_id
     * @return
     */
    int deleteTopic(@Param("topic_id") String topic_id);
}
