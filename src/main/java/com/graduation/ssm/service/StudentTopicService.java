package com.graduation.ssm.service;

import com.graduation.ssm.entity.Choice;
import com.graduation.ssm.entity.Page;
import com.graduation.ssm.entity.StudentTopicSearch;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.stereotype.Service;

public interface StudentTopicService {
    public ResultUtil getAllTopicList(String student_id, Page p);

    public ResultUtil getTopicListBySearch(StudentTopicSearch studentTopicSearch, Page p);

    public ResultUtil cancelSelect(String student_id, String topic_id);

    public ResultUtil selectTopic(String student_id, String topic_id);
}
