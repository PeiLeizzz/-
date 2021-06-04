package com.graduation.ssm.util;

/**
 * 学生搜索课题类
 * 功能：封装学生搜索课题时的各个用于筛选的参数
 * student_id: 用于查看学生相关的课题（预选和已选）
 * teacher_id, college, topic_name: 搜索时的参数
 */
public class StudentTopicSearch {
    private String student_id;
    private String teacher_id;
    private String college;
    private String topic_name;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }
}
