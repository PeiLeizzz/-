package com.graduation.ssm.entity;

/**
 * 课题选择实体类
 */
public class Choice {
    private String choice_id;
    private String topic_id;
    private String student_id;
    private int choice_state = -1;

    public String getChoice_id() {
        return choice_id;
    }

    public void setChoice_id(String choice_id) {
        this.choice_id = choice_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public int getChoice_state() {
        return choice_state;
    }

    public void setChoice_state(int status) {
        this.choice_state = status;
    }
}
