package com.graduation.ssm.service;

import com.graduation.ssm.util.Page;
import com.graduation.ssm.util.StudentTopicSearch;
import com.graduation.ssm.util.ResultUtil;

public interface StudentTopicService {
    public ResultUtil getAllTopicList(String student_id, Page p);

    public ResultUtil getTopicListBySearch(StudentTopicSearch studentTopicSearch, Page p);

    public ResultUtil cancelSelect(String student_id, String topic_id);

    public ResultUtil selectTopic(String student_id, String topic_id);
}
