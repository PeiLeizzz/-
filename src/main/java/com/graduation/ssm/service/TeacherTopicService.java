package com.graduation.ssm.service;

import com.graduation.ssm.entity.Page;
import com.graduation.ssm.entity.TeacherTopicSearch;
import com.graduation.ssm.util.ResultUtil;

public interface TeacherTopicService {
    public ResultUtil getTopicListOfTeacher(TeacherTopicSearch teacherTopicSearch, Page p);

    public ResultUtil getTopicList(TeacherTopicSearch teacherTopicSearch, Page p);

    public ResultUtil agreeStudent(String topic_id, String student_id);

    public ResultUtil disagreeStudent(String topic_id, String student_id);

    public ResultUtil deleteTopic(String topic_id);
}
