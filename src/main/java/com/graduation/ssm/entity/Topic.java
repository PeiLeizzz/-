package com.graduation.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class Topic {
    private String topic_id;
    private String topic_name;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date start_time;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date end_time;
    private int now_person;
    private int new_person;
    private int max_person = 0;
    private String topic_type;
    private Teacher teacher;
    private int choice_state = -1;

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getNow_person() {
        return now_person;
    }

    public void setNow_person(int now_person) {
        this.now_person = now_person;
    }

    public int getNew_person() {
        return new_person;
    }

    public void setNew_person(int new_person) {
        this.new_person = new_person;
    }

    public int getMax_person() {
        return max_person;
    }

    public void setMax_person(int max_person) {
        this.max_person = max_person;
    }

    public String getTopic_type() {
        return topic_type;
    }

    public void setTopic_type(String topic_type) {
        this.topic_type = topic_type;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getChoice_state() {
        return choice_state;
    }

    public void setChoice_state(int choice_state) {
        this.choice_state = choice_state;
    }
}
