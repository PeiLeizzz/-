package com.graduation.ssm.service;

import com.graduation.ssm.util.ResultUtil;

public interface StudentService {
    public ResultUtil getPreSelectStudentList(String topic_id);

    public ResultUtil getStudentChoiceCount(String student_id);
}
