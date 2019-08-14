package com.sacc.comprehensivesystem.modules.competition.controller;

import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.service.CompetitionService;
import com.sacc.comprehensivesystem.modules.competition.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class QuestionController {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    QuestionService questionService;


    @RequestMapping("list")
    public RestResult listCompetition() {
        List result = questionService.listCompetition();
        if (result != null) {
            return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", result);
        } else {
            return new RestResult(RestResult.STATUS_OTHERS, "调用失败", null);
        }
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public RestResult listQuestionById(@PathVariable long id) {
        List result = questionService.listQuestion(id);
        if (result != null) {
            return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", result);
        } else {
            return new RestResult(RestResult.STATUS_OTHERS, "调用失败", null);
        }
    }

    @Autowired
    CompetitionService competitionService;

    @RequestMapping("/addcompetition")
    public RestResult AddCompetition(@RequestBody String postJson){
        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=competitionService.addCompetition(postJson);

        }catch (Exception e){
            e.getStackTrace();
            result=0;
        }

        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "比赛添加成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "添加比赛失败", null);
                break;
        }
        return resultt;
    }

    @RequestMapping("/update/add")
    public RestResult updateData(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=competitionService.addQuestion(postJson);

        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result=0;
        }
        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目添加成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目比赛失败", null);
                break;
        }
        return resultt;
    }
}


