package com.graduation.ssm.service;

import com.graduation.ssm.dao.TopicDao;
import com.graduation.ssm.util.ResultUtil;
import com.graduation.ssm.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "TopicServiceImpl")
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public ResultUtil getTopicList() {
        List<Topic> topics = topicDao.getAllTopicList();
//        PageInfo<Topic> pageInfo = new PageInfo<Topic>(topics);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(topics.size());
        resultUtil.setData(topics);
        return resultUtil;
    }
}
