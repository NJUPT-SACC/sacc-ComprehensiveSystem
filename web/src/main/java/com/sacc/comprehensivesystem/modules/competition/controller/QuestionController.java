package com.sacc.comprehensivesystem.modules.competition.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import com.sacc.comprehensivesystem.common.utils.RestResult;
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
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public RestResult listQuestionById(@PathVariable long id) {
        List result = questionService.listQuestion(id);
        if (result != null) {
            /**JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i< jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                JSONArray list = new JSONArray();
                String choiceA = json.getString("choiceA");
                String choiceB = json.getString("choiceB");
                String choiceC = json.getString("choiceC");
                String choiceD = json.getString("choiceD");
                String choiceE = json.getString("choiceE");
                String choiceF = json.getString("choiceF");
                HashedMap map = new HashedMap(6);
                map.put("choiceA", choiceA);
                map.put("choiceB", choiceB);
                map.put("choiceC", choiceC);
                map.put("choiceD", choiceD);
                map.put("choiceE", choiceE);
                map.put("choiceF", choiceF);
                list.put(map);
                json.remove("choiceA");
                json.remove("choiceB");
                json.remove("choiceC");
                json.remove("choiceD");
                json.remove("choiceE");
                json.remove("choiceF");
                json.put("options", list);
            }
            String string = jsonArray.toString();
            Object object = JSONUtils.readValue(string, Object.class);*/
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
