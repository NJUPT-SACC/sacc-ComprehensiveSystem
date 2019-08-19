package com.sacc.comprehensivesystem.modules.competition.controller;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.utils.JSONUtils;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.service.CompetitionService;
import com.sacc.comprehensivesystem.modules.competition.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
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

    @RequestMapping("/update/competition")
    public RestResult updateData(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=competitionService.updateCompetition(postJson);

        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result=0;
        }
        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "比赛更新成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "比赛更新失败", null);
                break;
        }
        return resultt;
    }

    @RequestMapping("/update/question/add")
    public RestResult updateAddQuestion(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=competitionService.addCompetitionQuestion(postJson);

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
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目添加失败失败", null);
                break;
        }
        return resultt;
    }

    @RequestMapping("/update/question/del")
    public RestResult updateDelQuestion(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=competitionService.delCompetitionQuestion(postJson);

        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result=0;
        }
        switch(result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目删除成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目删除失败", null);
                break;
        }
        return resultt;
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


