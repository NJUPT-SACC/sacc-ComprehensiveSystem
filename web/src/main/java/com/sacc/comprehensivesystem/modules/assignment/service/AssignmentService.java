package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.modules.assignment.dao.AssignmentMapper;
import com.sacc.comprehensivesystem.modules.assignment.dao.AssignmentQuestionMapper;
import com.sacc.comprehensivesystem.modules.assignment.entity.Assignment;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion;
import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author goufaan
 */
@Component
public class AssignmentService extends BasicService<Assignment> {
    @Override
    public String[] getNatureKeys() {
        return new String[]{"name", "startTime", "endTime"};
    }

    @Autowired
    AssignmentMapper assignmentMapper;

    @Autowired
    AssignmentQuestionMapper assignmentQuestionMapper;

    @Override
    public void customInit(Assignment entity) {

    }

    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    public int addASsignments(String postJosn) {
        JSONObject jsonObject = new JSONObject(postJosn);

        String strStartTime = jsonObject.getString("startTime");
        String strEndTime = jsonObject.getString("endTime");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date startTime = null;
        Date endTime = null;

        try {
            startTime = df.parse(strStartTime);
            endTime = df.parse(strEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**
         * 将JSONArray转成List类型
         */
        JSONArray jsonArrayM = jsonObject.getJSONArray("questionIdM");
        JSONArray jsonArrayP = jsonObject.getJSONArray("questionIdP");
        /**
         * 给competition赋值
         */
        Assignment assignment = new Assignment();
        assignment.setName(jsonObject.getString("name"));
        assignment.setStartTime(startTime);
        assignment.setEndTime(endTime);
        //competition.setUpdateBy(-1l);

        AssignmentQuestion assignmentQuestion = new AssignmentQuestion();
        int result = 0;
        try {

            String token = SecurityUtils.getSubject().getPrincipal().toString();
            UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
            SysUser sysUser = info.getSysUser();
             assignment.setUpdateBy(sysUser.getUpdateBy());
             assignment.setCreateBy(sysUser.getCreateBy());
             assignmentQuestion.setUpdateBy(sysUser.getUpdateBy());
             assignmentQuestion.setCreateBy(sysUser.getCreateBy());
             /**assignment.setUpdateBy(1l);
            assignment.setCreateBy(1l);
            assignmentQuestion.setUpdateBy(1l);
            assignmentQuestion.setCreateBy(1l);**/
            assignmentMapper.insertAssignment(assignment);
            System.out.println(assignment);
        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        try {
            assignmentQuestion.setAssignmentId(assignmentMapper.findIdByName(assignment.getName()));
            for (int i = 0; i < jsonArrayM.length(); i++) {
                try {
                    JSONObject jsonObject1 = jsonArrayM.getJSONObject(i);
                    assignmentQuestion.setQuestionId((long) (int) jsonObject1.get("qid"));
                    assignmentQuestion.setQuestionTypeId(1);
                    assignmentQuestionMapper.insertAssignmentQuestion(assignmentQuestion);
                    result = 1;
                } catch (Exception e) {
                    e.getStackTrace();
                    logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                    result = 0;
                }
            }
            try {
                assignmentQuestion.setAssignmentId(assignmentMapper.findIdByName(assignment.getName()));
                for (int i = 0; i < jsonArrayP.length(); i++) {
                    try {
                        JSONObject jsonObject1 = jsonArrayP.getJSONObject(i);
                        assignmentQuestion.setQuestionId((long) (int) jsonObject1.get("qid"));
                        assignmentQuestion.setQuestionTypeId(2);
                        assignmentQuestionMapper.insertAssignmentQuestion(assignmentQuestion);
                        result = 1;
                    } catch (Exception e) {
                        e.getStackTrace();
                        logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                        result = 0;
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
                logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                result = 0;
            }

        } catch (Exception e) {
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            e.printStackTrace();
        }
        return result;
    }

    public int updateAssignment(String postJosn) {
        int result = 0;
        JSONObject jsonObject = new JSONObject(postJosn);
        Assignment assignment = new Assignment();
        String strStartTime = jsonObject.getString("startTime");
        String strEndTime = jsonObject.getString("endTime");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date startTime = null;
        Date endTime = null;
        try {
            startTime = df.parse(strStartTime);
            endTime = df.parse(strEndTime);
            System.out.println(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assignment.setName(jsonObject.getString("name"));
        String rname = jsonObject.getString("rname");
        assignment.setStartTime(startTime);
        assignment.setEndTime(endTime);
       String token = SecurityUtils.getSubject().getPrincipal().toString();
         UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
         SysUser sysUser = info.getSysUser();
         assignment.setUpdateBy(sysUser.getUpdateBy());
         assignment.setCreateBy(sysUser.getCreateBy());
        /**assignment.setUpdateBy(1l);
        assignment.setCreateBy(1l);**/
        Long id = assignmentMapper.findIdByName(rname);
        assignment.setId(id);

        try {
            assignmentMapper.update(assignment);
            result = 1;
        } catch (Exception e) {
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }

        return result;
    }

    public int addQuestion(String postJosn) {
        int result = 0;
        JSONObject jsonObject = new JSONObject(postJosn);
        AssignmentQuestion assignmentQuestion = new AssignmentQuestion();
        assignmentQuestion.setRemarks("afd");/**要修改**/
        /**
         * 用户验证
         */
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        assignmentQuestion.setUpdateBy(sysUser.getUpdateBy());
        assignmentQuestion.setCreateBy(sysUser.getCreateBy());

        Long assignmentId = jsonObject.getLong("assignmentId");
        assignmentQuestion.setAssignmentId(assignmentId);
        assignmentQuestion.setAssignmentId(assignmentId);
        JSONArray jsonArrayM = jsonObject.getJSONArray("questionIdM");
        JSONArray jsonArrayP = jsonObject.getJSONArray("questionIdP");
            for (int i = 0; i < jsonArrayM.length(); i++) {
                try {
                    JSONObject jsonObject1 = jsonArrayM.getJSONObject(i);
                    assignmentQuestion.setQuestionId((long) (int) jsonObject1.get("qid"));
                    assignmentQuestion.setQuestionTypeId(1);
                    assignmentQuestionMapper.insertAssignmentQuestion(assignmentQuestion);
                    result = 1;
                } catch (Exception e) {
                    e.getStackTrace();
                    logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                    result = 0;
                }
            }
                for (int i = 0; i < jsonArrayP.length(); i++) {
                    try {
                        JSONObject jsonObject1 = jsonArrayP.getJSONObject(i);
                        assignmentQuestion.setQuestionId((long) (int) jsonObject1.get("qid"));
                        assignmentQuestion.setQuestionTypeId(2);
                        assignmentQuestionMapper.insertAssignmentQuestion(assignmentQuestion);
                        result = 1;
                    } catch (Exception e) {
                        e.getStackTrace();
                        logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                        result = 0;
                    }
        }
        return result;
    }
    public int delAssignmentQuestion(String postJosn) {
        int result = 0;
        JSONObject jsonObject = new JSONObject(postJosn);
        JSONArray jsonArray = jsonObject.getJSONArray("questionId");

        Long assignmentId = jsonObject.getLong("assignmentId");
        AssignmentQuestion assignmentQuestion = new AssignmentQuestion();
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        assignmentQuestion.setUpdateBy(sysUser.getUpdateBy());
        assignmentQuestion.setAssignmentId(assignmentId);
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                Long id = assignmentQuestionMapper.findIdByAssignmentIdAndQuestionId((long) (int) jsonObject1.get("qid"),assignmentId );
                assignmentQuestionMapper.deleteQuestion(id);
                result = 1;
            }
        } catch (Exception e) {
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        return result;
    }
}
