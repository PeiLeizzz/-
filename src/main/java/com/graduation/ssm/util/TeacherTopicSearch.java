package com.graduation.ssm.util;

/**
 * 教师搜索课题类
 * 功能：封装教师搜索课题时的各个用于筛选的参数
 * 1. 用于查看教师自己发布的课题
 *  teacher_id: 若是 session 中获取，则是用于查看教师发布的课题
 *  topic_status: 已满 / 未满
 * 2. 用于搜索全部的课题
 * teacher_id, college, topic_name: 搜索时的参数
 */
public class TeacherTopicSearch {
    private String teacher_id;
    private String topic_status;
    private String topic_name;
    private String college;

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTopic_status() {
        return topic_status;
    }

    public void setTopic_status(String topic_status) {
        this.topic_status = topic_status;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
