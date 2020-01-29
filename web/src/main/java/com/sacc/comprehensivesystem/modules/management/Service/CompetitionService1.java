package com.sacc.comprehensivesystem.modules.management.Service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionDao;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionQuestionDao;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion;
import com.sacc.comprehensivesystem.modules.management.dao.CompetitionMapper;
import com.sacc.comprehensivesystem.modules.management.dao.CompetitionQuestionMapper;
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

@Component
public class CompetitionService1 {

    @Autowired
    CompetitionMapper competitionDao;
    @Autowired
    CompetitionQuestionMapper competitionQuestionDao;

    static Logger logger = LoggerFactory.getLogger(LoginService.class);


    /**
     * 添加比赛
     * @param postJosn
     * @return
     */
    public int addCompetition(String postJosn) {
        JSONObject jsonObject = new JSONObject(postJosn);

        String strStartTime = jsonObject.getString("competitionStartTime");
        String strEndTime = jsonObject.getString("competitionEndTime");

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
        JSONArray jsonArray = jsonObject.getJSONArray("questionId");
        /**
         * 给competition赋值
         */
        Competition competition = new Competition();
        competition.setLocation(jsonObject.getString("competitionLocation"));
        competition.setName(jsonObject.getString("competitionName"));
        competition.setStartTime(startTime);
        competition.setEndTime(endTime);
        //competition.setUpdateBy(-1l);

        CompetitionQuestion competitionQuestion = new CompetitionQuestion();
        int result = 0;
        try {
            System.out.println(competition);
            String token = SecurityUtils.getSubject().getPrincipal().toString();
            UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
            SysUser sysUser = info.getSysUser();
            competition.setUpdateBy(sysUser.getUpdateBy());
            competition.setCreateBy(sysUser.getCreateBy());
            competitionQuestion.setUpdateBy(sysUser.getUpdateBy());
            competitionQuestion.setCreateBy(sysUser.getCreateBy());
            competitionDao.insertCompetition(competition);
        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        try {
            competitionQuestion.setCompetitionId(competitionDao.findIdByName(competition.getName()));
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    competitionQuestion.setQuestionId((long) (int) jsonObject1.get("qid"));
                    competitionQuestionDao.insertCompetitionQuestion(competitionQuestion);
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
        return result;
    }


    /**
     * 更新比赛信息
     * @param postJosn
     * @return
     */
    public int updateCompetition(String postJosn) {
        int result = 0;
        JSONObject jsonObject = new JSONObject(postJosn);
        Competition competition = new Competition();
        String strStartTime = jsonObject.getString("competitionStartTime");
        String strEndTime = jsonObject.getString("competitionEndTime");

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
        competition.setLocation(jsonObject.getString("competitionLocation"));
        competition.setName(jsonObject.getString("competitionNewName"));
        String rname = jsonObject.getString("competitionOldName");
        competition.setStartTime(startTime);
        competition.setEndTime(endTime);
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        competition.setUpdateBy(sysUser.getUpdateBy());
        competition.setCreateBy(sysUser.getCreateBy());
        Long id = competitionDao.findIdByName(rname);
        competition.setId(id);

        try {
            competitionDao.competitionUpdate(competition);
            result = 1;
        } catch (Exception e) {
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }

        return result;
    }

    public int addCompetitionQuestion(String postJosn) {
        int result = 0;
        JSONObject jsonObject = new JSONObject(postJosn);
        JSONArray jsonArray = jsonObject.getJSONArray("questionId");

        Long competitionId = jsonObject.getLong("competitionId");
        CompetitionQuestion competitionQuestion = new CompetitionQuestion();
        competitionQuestion.setCompetitionId(competitionId);
        //System.out.println(competitionQuestion);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                competitionQuestion.setQuestionId((long) (int) jsonObject1.get("qid"));
                competitionQuestionDao.insertCompetitionQuestion(competitionQuestion);
                result = 1;
            } catch (Exception e) {
                logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                result = 0;
            }
        }
        return result;
    }


    /**
     * 删除比赛中的问题
     * @param postJosn
     * @return
     */
    public int delCompetitionQuestion(String postJosn) {
        int result = 0;
        JSONObject jsonObject = new JSONObject(postJosn);
        JSONArray jsonArray = jsonObject.getJSONArray("questionId");

        Long competitionId = jsonObject.getLong("competitionId");
        CompetitionQuestion competitionQuestion = new CompetitionQuestion();
        competitionQuestion.setCompetitionId(competitionId);
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                Long id = competitionQuestionDao.findIdByCompetitionIdAndQuestionId(competitionId, (long) (int) jsonObject1.get("qid"));
                competitionQuestionDao.deleteQuestion(id);
                result = 1;
            }
        } catch (Exception e) {
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        return result;
    }
}