package com.graduation.ssm.service;

import com.graduation.ssm.dao.ChoiceDao;
import com.graduation.ssm.dao.TopicDao;
import com.graduation.ssm.util.Page;
import com.graduation.ssm.util.StudentTopicSearch;
import com.graduation.ssm.util.ResultUtil;
import com.graduation.ssm.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;

/**
 * 用于处理学生与课题、选择之间交互的逻辑
 */
@Service(value = "StudentTopicServiceImpl")
@Transactional
public class StudentTopicServiceImpl implements StudentTopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private ChoiceDao choiceDao;

    /**
     * 学生获取所有课题列表（非搜索）
     * @param student_id 学生 id
     * @param p 分页控制对象
     * @return 课题列表数据集合
     */
    @Override
    public ResultUtil getAllTopicList(String student_id, Page p) {
        ResultUtil resultUtil = null;
        try {
            // 先查询有没有与自己相关的课题，如果有，需要在第一页置顶，并且需要处理课题数量
            Topic sTopic = topicDao.getTopicWithStudent(student_id);
            // 第一页置顶显示自己的课题，所以其他课题数量 - 1
            if (p.getPageNow() == 1 && sTopic != null) {
                p.setPageSize(p.getPageSize() - 1);
            }
            // 如果学生预选 / 已选课题，但不在第一页，需要将该页第一条数据位置提前一条
            // 因为在第一页时，少获取了一条数据
            else if (sTopic != null) {
                p.setStartPos(p.getStartPos() - 1);
            }

            List<Topic> topics = topicDao.studentGetAllTopicList(student_id, p);
            if (p.getPageNow() == 1 && sTopic != null) {
                topics.add(0, sTopic);
            }

            resultUtil = new ResultUtil();
            resultUtil.setCode(0);
            resultUtil.setCount(topicDao.studentGetAllTopicCount(student_id));
            if (sTopic != null) {
                resultUtil.setCount(resultUtil.getCount() + 1);
            }
            resultUtil.setData(topics);
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("学生获取所有课题列表出错！");
        }
        return resultUtil;
    }

    /**
     * 学生搜索课题列表
     * @param studentTopicSearch 教师搜索课题对象
     * @param p 分页控制对象
     * @return 课题列表数据集合
     */
    @Override
    public ResultUtil getTopicListBySearch(StudentTopicSearch studentTopicSearch, Page p) {
        // 搜索时，不需要区分是否是该学生的课题
        // 分页搜索完成后，搜索与该学生有关的课题
        // 检查一下该页有没有某个课题是该学生的课题
        // 如果有，将该课题换成刚刚搜索到的与该学生相关的课题（主要是为了改变该课题的 choice 状态）
        ResultUtil resultUtil = null;
        try {
            List<Topic> topics = topicDao.studentGetTopicListBySearch(studentTopicSearch, p);
            Topic sTopic = topicDao.getTopicWithStudent(studentTopicSearch.getStudent_id());
            if (sTopic != null) {
                for (Iterator<Topic> it = topics.iterator(); it.hasNext(); ) {
                    Topic tmp = it.next();
                    if (tmp.getTopic_id().equals(sTopic.getTopic_id())) {
                        topics.set(topics.indexOf(tmp), sTopic);
                    }
                }
            }

            resultUtil = new ResultUtil();
            resultUtil.setCode(0);
            resultUtil.setCount(topicDao.studentGetTopicListCountBySearch(studentTopicSearch));
            resultUtil.setData(topics);
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("学生搜索课题列表出错！");
        }
        return resultUtil;
    }

    /**
     * 学生取消选择某个课题
     * @param student_id 学生 id
     * @param topic_id 课题 id
     * @return 取消选择是否成功结果
     */
    @Override
    public ResultUtil cancelSelect(String student_id, String topic_id) {
        ResultUtil resultUtil = null;
        try {
            int res = choiceDao.cancelSelect(student_id, topic_id);
            if (res > 0) {
                resultUtil = ResultUtil.ok("退选成功！");
            }
            else {
                resultUtil = ResultUtil.error("退选出现错误！");
            }
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("退选出现错误！");
        }

        return resultUtil;
    }

    /**
     * 学生选择某个课题
     * @param student_id 学生 id
     * @param topic_id 课题 id
     * @return 选择是否成功结果
     */
    @Override
    public ResultUtil selectTopic(String student_id, String topic_id) {
        ResultUtil resultUtil = null;

        try {
            if (choiceDao.getStudentChoiceCount(student_id) > 0) {
                resultUtil = ResultUtil.error("你已选择了其他课题！");
            }
            else if (topicDao.getNowPerson(topic_id) >= topicDao.getMaxPerson(topic_id)) {
                resultUtil = ResultUtil.error("该课题人数已满！");
            }
            else {
                int res = choiceDao.selectTopic(student_id, topic_id);
                if (res > 0) {
                    resultUtil = ResultUtil.ok("选择成功！");
                }
                else {
                    resultUtil = ResultUtil.error("选择出现错误！");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("选择出现错误！");
        }

        return resultUtil;
    }
}
