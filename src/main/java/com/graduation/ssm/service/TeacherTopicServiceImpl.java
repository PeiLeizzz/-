package com.graduation.ssm.service;

import com.graduation.ssm.dao.StudentDao;
import com.graduation.ssm.dao.TopicDao;
import com.graduation.ssm.entity.Page;
import com.graduation.ssm.entity.Student;
import com.graduation.ssm.entity.TeacherTopicSearch;
import com.graduation.ssm.entity.Topic;
import com.graduation.ssm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service(value = "TeacherTopicServiceImpl")
@Transactional
public class TeacherTopicServiceImpl implements TeacherTopicService {

    @Autowired
    private TopicDao topicDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public ResultUtil getTopicListOfTeacher(TeacherTopicSearch teacherTopicSearch, Page p) {
        List<Topic> topics = topicDao.getTopicListOfTeacherBySearch(teacherTopicSearch, p);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(topicDao.getTopicListCountOfTeacherBySearch(teacherTopicSearch));
        System.out.println(resultUtil.getCount());
        resultUtil.setData(topics);
        return resultUtil;
    }

    @Override
    public ResultUtil getTopicList(TeacherTopicSearch teacherTopicSearch, Page p) {
        List<Topic> topics = topicDao.teacherGetTopicList(teacherTopicSearch, p);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(topicDao.teacherGetTopicListCount(teacherTopicSearch));
        System.out.println(resultUtil.getCount());
        resultUtil.setData(topics);
        return resultUtil;
    }

    @Override
    public ResultUtil agreeStudent(String student_id, String topic_id) {
        // 先通过 topic_id检查人数是否已满
        // 再检查该学生是否已经取消预选（用 try-catch 也行）
        // 如果都满足，则可以同意，更新 choice_state = 1
        // 同意后若课题人数已满，则拒绝所有其他学生
        ResultUtil resultUtil = null;
        try {
            int now_person = topicDao.getNowPerson(topic_id);
            int max_person = topicDao.getMaxPerson(topic_id);
            if (now_person >= max_person) {
                resultUtil = ResultUtil.error("课题人数已满，同意失败！");
            }
            else {
                Topic tmp = topicDao.getTopicWithStudent(student_id);
                if (tmp == null) {
                    resultUtil = ResultUtil.error("该学生已经取消对于该课题的预选，同意失败！");
                    return resultUtil;
                }
                String t_id = tmp.getTopic_id();
                int state = tmp.getChoice_state();
                if (!t_id.equals(topic_id)) {
                    resultUtil = ResultUtil.error("该学生已经取消对于该课题的预选，同意失败！");
                }
                else if (state == 1) {
                    resultUtil = ResultUtil.error("您已同意该学生的预选，同意失败，请刷新页面！");
                }
                else {
                    int res = topicDao.updateAgree(student_id, topic_id);
                    if (res > 0) {
                        resultUtil = ResultUtil.ok("同意成功！");
                        if (now_person + 1 == max_person) {
                            List<Student> students = studentDao.getPreSelectStudentList(topic_id);
                            for (Iterator<Student> it = students.iterator(); it.hasNext();) {
                                Student stmp = it.next();
                                disagreeStudent(stmp.getId(), topic_id);
                            }
                        }
                    }
                    else {
                        resultUtil = ResultUtil.error("同意时出错！");
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("同意时出错！");
        }

        return resultUtil;
    }

    @Override
    public ResultUtil disagreeStudent(String student_id, String topic_id) {
        // 先检查该学生是否已经取消预选，如果没有
        // 检查 choice_state 是否是 1（代表当前页面是旧页面，在其他页面已同意该学生请求）
        // 如果不是 1，则删除 choice 中数据
        ResultUtil resultUtil = null;
        try {
            Topic tmp = topicDao.getTopicWithStudent(student_id);
            if (tmp == null) {
                resultUtil = ResultUtil.error("该学生已经取消对于该课题的预选，同意失败！");
                return resultUtil;
            }
            String t_id = tmp.getTopic_id();
            int state = tmp.getChoice_state();
            if (!t_id.equals(topic_id)) {
                resultUtil = ResultUtil.error("该学生已经取消对于该课题的预选，拒绝失败！");
            }
            else if (state == 1) {
                resultUtil = ResultUtil.error("您已同意该学生的预选，拒绝失败，请刷新页面！");
            }
            else {
                int res = topicDao.updateDisagree(student_id, topic_id);
                if (res > 0) {
                    resultUtil = ResultUtil.ok("拒绝成功！");
                }
                else {
                    resultUtil = ResultUtil.error("拒绝时出错！");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("拒绝时出错！");
        }

        return resultUtil;
    }

    @Override
    public ResultUtil deleteTopic(String topic_id) {
        // 如果该课题已有接收的学生，则不能删除；
        // 如果该课题有学生报名，则拒绝所有报名（即删除 choice 表中的所有依赖）;
        // 先删除 topic_info，再删除 topic
        ResultUtil resultUtil = null;
        try {
            int now_person = topicDao.getNowPerson(topic_id);
            if (now_person > 0) {
                resultUtil = ResultUtil.error("该课题已接收学生，不能删除！");
            }
            else {
                List<Student> students = studentDao.getPreSelectStudentList(topic_id);
                for (Iterator<Student> it = students.iterator(); it.hasNext();) {
                    Student stmp = it.next();
                    disagreeStudent(stmp.getId(), topic_id);
                }
                topicDao.deleteTopicInfo(topic_id);
                int res = topicDao.deleteTopic(topic_id);
                if (res > 0) {
                    resultUtil = ResultUtil.ok("删除成功！");
                }
                else {
                    resultUtil = ResultUtil.error("删除课题时出错！");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            resultUtil = ResultUtil.error("删除课题时出错！");
        }
        return resultUtil;
    }
}
