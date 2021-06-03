package com.graduation.ssm.service;

import com.graduation.ssm.dao.TopicDao;
import com.graduation.ssm.entity.Page;
import com.graduation.ssm.entity.StudentTopicSearch;
import com.graduation.ssm.util.ResultUtil;
import com.graduation.ssm.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service(value = "StudentTopicServiceImpl")
@Transactional
public class StudentTopicServiceImpl implements StudentTopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public ResultUtil getAllTopicList(String student_id, Page p) {
        Topic sTopic = topicDao.getTopicWithStudent(student_id);
        // 第一页置顶显示自己的课题，所以其他课题数量 - 1
        if (p.getPageNow() == 1 && sTopic != null) {
            p.setPageSize(p.getPageSize() - 1);
        }
        else if (sTopic != null) {
            p.setStartPos(p.getStartPos() - 1);
        }

        List<Topic> topics = topicDao.studentGetAllTopicList(student_id, p);
        if (p.getPageNow() == 1 && sTopic != null) {
            topics.add(0, sTopic);
        }

        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(topicDao.studentGetAllTopicCount(student_id));
        if (sTopic != null) {
            resultUtil.setCount(resultUtil.getCount() + 1);
        }
        resultUtil.setData(topics);
        return resultUtil;
    }

    @Override
    public ResultUtil getTopicListBySearch(StudentTopicSearch studentTopicSearch, Page p) {
        List<Topic> topics = topicDao.studentGetTopicListBySearch(studentTopicSearch, p);
        Topic sTopic = topicDao.getTopicWithStudent(studentTopicSearch.getStudent_id());
        if (sTopic != null) {
            for (Iterator<Topic> it = topics.iterator(); it.hasNext();) {
                Topic tmp = it.next();
                if (tmp.getTopic_id().equals(sTopic.getTopic_id())) {
                    topics.set(topics.indexOf(tmp), sTopic);
                }
            }
        }

        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(topicDao.studentGetTopicListCountBySearch(studentTopicSearch));
        resultUtil.setData(topics);
        return resultUtil;
    }

    @Override
    public ResultUtil cancelSelect(String student_id, String topic_id) {
        int res = topicDao.cancelSelect(student_id, topic_id);

        ResultUtil resultUtil = null;

        if (res > 0) resultUtil = ResultUtil.ok("退选成功！");
        else resultUtil = ResultUtil.error("退选出现错误！");

        return resultUtil;
    }

    @Override
    public ResultUtil selectTopic(String student_id, String topic_id) {
        ResultUtil resultUtil = null;

        if (topicDao.getStudentChoice(student_id) > 0) {
            resultUtil = ResultUtil.error("你已选择了其他课题！");
        }
        else if (topicDao.getNowPerson(topic_id) >= topicDao.getMaxPerson(topic_id)) {
            resultUtil = ResultUtil.error("该课题人数已满！");
        }
        else{
            int res = topicDao.selectTopic(student_id, topic_id);
            if (res > 0) resultUtil = ResultUtil.ok("选择成功！");
            else resultUtil = ResultUtil.error("选择出现错误！");
        }

        return resultUtil;
    }
}
