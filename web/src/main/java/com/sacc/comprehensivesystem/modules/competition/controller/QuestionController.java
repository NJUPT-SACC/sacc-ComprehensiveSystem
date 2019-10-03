package com.sacc.comprehensivesystem.modules.competition.controller;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.competition.service.CompetitionService;
import com.sacc.comprehensivesystem.modules.competition.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.shiro.authz.annotation.RequiresRoles;

import java.util.List;
import java.util.Map;

/**
 * @author yujinbiao
 */
@RestController
@RequestMapping("/competition")
public class QuestionController {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    QuestionService questionService;

    /**
     * 获取比赛列表
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping("list")
    public RestResult listCompetition() {
        List result = questionService.listCompetition();
        if (result != null) {
            return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", result);
        } else {
            return new RestResult(RestResult.STATUS_OTHERS, "调用失败", null);
        }
    }

    /**
     * 获取比赛所有题目
     * @param id
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public RestResult listQuestionById(@PathVariable long id) {
                List<QuestionBank> result = questionService.listQuestion(id);
                if (result != null) {
            return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", result);
        } else {
            return new RestResult(RestResult.STATUS_OTHERS, "调用失败", null);
        }
    }

    /**
     * 提交题目和暂存
     * @param postJson
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/push" ,method = RequestMethod.POST)
    public RestResult push(@RequestBody String postJson) {
        Map<String, Long> map = null;
        map = questionService.saveOrSubmit(postJson);
        if (map != null) {
            return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", map);
        } else {
            return new RestResult(RestResult.STATUS_OTHERS, "重复提交或非法输入", null);
        }
    }

}


