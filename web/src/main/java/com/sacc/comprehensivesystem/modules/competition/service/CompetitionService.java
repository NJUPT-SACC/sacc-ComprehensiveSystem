package com.sacc.comprehensivesystem.modules.competition.service;

import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionDao;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionQuestionDao;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class CompetitionService {

    @Autowired
    CompetitionDao competitionDao;
    @Autowired
    CompetitionQuestionDao competitionQuestionDao;
    static Logger logger = LoggerFactory.getLogger(LoginService.class);


    public int addCompetition(String postJosn)
    {
        JSONObject jsonObject = new JSONObject(postJosn);

        String strStartTime=jsonObject.getString("startTime");
        String strEndTime=jsonObject.getString("endTime");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date startTime = null;
        Date endTime = null;

        try {
            startTime = df.parse(strStartTime);
            endTime=df.parse(strEndTime);
            System.out.println(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**
         * 将JSONArray转成List类型
         */
        JSONArray jsonArray=jsonObject.getJSONArray("questionId");
        List list=(List)jsonArray.toList();
        Long [] questionId=new Long[list.size()];
        questionId[1]=(Long)list.get(1);

        /**
         * 给competition赋值
         */
        Competition competition = new Competition();
        competition.setLocation(jsonObject.getString("location"));
        competition.setName(jsonObject.getString("name"));
        competition.setStartTime(startTime);
        competition.setEndTime(endTime);
        competition.setUpdateBy(-1l);

        CompetitionQuestion competitionQuestion=new CompetitionQuestion();
        int result=0;
        try{
            System.out.println(competition);
            competitionDao.insertCompetition(competition);
            competitionQuestion.setCompetitionId(competitionDao.findIdByName(competition.getName()));
            for(int i=0;i<list.size();i++){
                questionId[i]=(Long)list.get(i);
                competitionQuestion.setQuestionId(questionId[i]);
                try {
                    competitionQuestionDao.insertCompetitionQuestion(competitionQuestion);
                }catch(Exception e){
                    e.getStackTrace();
                    logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                }
            }

            result=1;
        }catch (Exception e)
        {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result=0;
        }
        System.out.println(result);
        return result;
    }

    public int addQuestion(String postJosn) {
        int result=1;
        JSONObject jsonObject = new JSONObject(postJosn);
        JSONArray jsonArray = jsonObject.getJSONArray("questionId");
        List<Long> list = (List) jsonArray.toList();
        //Long[] questionId = new Long[list.size()];

        Long competitionId = jsonObject.getLong("competitionId");
        CompetitionQuestion competitionQuestion = new CompetitionQuestion();
        competitionQuestion.setCompetitionId(competitionId);
        System.out.println(competitionQuestion);
        for (int i = 0; i < list.size(); i++) {
            try {
                competitionQuestion.setQuestionId(list.get(i));
            }catch (Exception e)
            {
                logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            }
                competitionQuestionDao.insertCompetitionQuestion(competitionQuestion);
                System.out.println(list.get(i));
            }
        return result;
    }
}
