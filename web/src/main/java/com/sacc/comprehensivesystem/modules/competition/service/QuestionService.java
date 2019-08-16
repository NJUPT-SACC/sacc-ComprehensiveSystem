package com.sacc.comprehensivesystem.modules.competition.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionDao;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionResultDao;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionResult;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionStage;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    @Autowired
    CompetitionDao competitionDao;

    @Autowired
    CompetitionResultDao competitionResultDao;

    public List<Competition> listCompetition() {
        List<Competition> result = new ArrayList<>();
        result = competitionDao.listCompetition();
        return result;
    }

    public List<QuestionBank> listQuestion(long id) {
        List<QuestionBank> result = competitionDao.queryQuestion(id);
        return result;
    }

    @Transactional(readOnly = false)
    public Map<String, Long> saveOrSubmit(String postJson)  {
        JSONObject jsonObject = new JSONObject(postJson);
        JSONArray resultList = jsonObject.getJSONArray("data");
        int submitType = jsonObject.getInt("type");
        long userId = jsonObject.getLong("uid");
        long competitionId = jsonObject.getLong("cid");

//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode node = objectMapper.readTree(postJson);
//        Iterator<String> filedNames = node.fieldNames();
//        while (filedNames.hasNext()) {
//            String filedName = filedNames.next();
//
//        }

        Map<Long, CompetitionStage> list = new HashedMap();
        for (int i = 0; i < resultList.length(); i++) {
            JSONObject json = resultList.getJSONObject(i);

            CompetitionStage pb = new CompetitionStage();
            pb.setQuestionId((long)(int) json.get("qid"));
            pb.setResult((String)json.get("result"));
            pb.setUserId(userId);
            list.put(pb.getQuestionId(), pb);
        }
        //保存
        if (submitType == 1) {
            competitionDao.deleteOldResult(userId);

            competitionDao.save(list);
            return new HashedMap();
        }

        //检查是否重复提交
        if (competitionDao.checRepeat(userId, competitionId) != null) {
            return null;
        }

        //非法输入
        if (submitType != 2) {
            return null;
        }

        List<QuestionBank> rightAnswer = competitionDao.getRightAnswer(competitionId);

        int totalScore = 0;
        //开始判题
        competitionDao.deleteOldResult(userId);
        for (QuestionBank item : rightAnswer) {
            CompetitionStage result = list.get(item.getId().longValue());
            char[] re = result.getResult().toCharArray();
            char[] an = item.getCorrectAnswer().toCharArray();
            int total = an.length, right = 0;
            for (char r :re) {
                for (char a : an) {
                    if (r == a) {
                        right++;
                    }
                }
            }
            totalScore += caiculate(total, right);
        }
        competitionDao.save(list);

        CompetitionResult competitionResult = new CompetitionResult();
        competitionResult.setScore(totalScore);
        competitionResult.setUserId(userId);
        competitionResult.setCompetitionId(competitionId);
        competitionResultDao.saveResult(competitionResult);

        Map<String ,Long> map = new HashedMap();
        map.put("uid", userId);
        map.put("score", Long.valueOf(totalScore));
        return map;
    }

    private int caiculate(int total, int right) {
        return 10;
    }
}
