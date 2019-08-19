package com.sacc.comprehensivesystem.modules.competition.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.competition.service.QuestionService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * @author yujinbiao
 */
@RestController
@RequestMapping("/competition")
public class QuestionController {

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
