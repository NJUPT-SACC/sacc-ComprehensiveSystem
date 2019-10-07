package com.sacc.comprehensivesystem.modules.management.controller;
import com.sacc.comprehensivesystem.common.entity.RequestParam;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBankResult;
import com.sacc.comprehensivesystem.modules.competition.service.CompetitionService;
import com.sacc.comprehensivesystem.modules.management.Service.AssignmentService1;
import com.sacc.comprehensivesystem.modules.management.Service.CompetitionService1;
import com.sacc.comprehensivesystem.modules.voj.service.ProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management")
public class Controller {

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    ProblemService problemService;
    @Autowired
    AssignmentService1 assignmentService;

    @RequestMapping(value = "/problem/get")
    public RestResult getQuestion(/**@PathVariable int page,@PathVariable int row,@PathVariable int difficulty**/@RequestBody String postjson)
    {
        List<QuestionBankResult> result = problemService.getQuestion(postjson);
        if (result != null) {
            return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", result);
        } else {
            return new RestResult(RestResult.STATUS_OTHERS, "调用失败", null);
        }
    }

    @RequestMapping(value = "/problem/create", method = RequestMethod.POST)
    public RestResult createProblem( @RequestBody RequestParam requestParam) {

        try {
            boolean result = problemService.createProblem(requestParam.getProblemName(), requestParam.getTimeLimit(), requestParam.getMemoryLimit(),
                    requestParam.getDescription(), requestParam.getHint(), requestParam.getInputFormat(), requestParam.getOutputFormat(),
                    requestParam.getInputSample(), requestParam.getOutputSample(), requestParam.getTestCases(), requestParam.getProblemCategories(),
                    requestParam.getProblemTags(), requestParam.isPublic(), requestParam.isExactlyMatch());
            if (result) {
                return new RestResult<>(RestResult.STATUS_SUCCESS, "调用成功", null);
            } else {
                return new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
            }
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            return new RestResult<>(RestResult.STATUS_OTHERS, "异常，调用失败", null);
        }

    }

    @RequestMapping(value = "/problem/add")
    public RestResult addProblem(@RequestBody String postjson){
        try{
            boolean result = problemService.addProblem(postjson);
            if (result) {
                return new RestResult<>(RestResult.STATUS_SUCCESS, "调用成功", null);
            } else {
                return new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
            }
        }catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            return new RestResult<>(RestResult.STATUS_OTHERS, "异常，调用失败", null);
        }
    }


    @RequestMapping("/competition/add")
    public RestResult Addcompetition(@RequestBody String postJson){
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
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "作业添加成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "作业添加失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/competition/update")
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
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "作业更新成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "作业更新失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/competition/addquestion")
    public RestResult addQuestion(@RequestBody String postJson)
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
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目添加新成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目添加失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/competition/deletequestion")
    public RestResult deleteQuestion(@RequestBody String postJson)
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
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目删除新成功",null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目删除失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }


    @Autowired
    CompetitionService1 competitionService;

    @RequestMapping("/assignment/add")
    public RestResult AddAssignment(@RequestBody String postJson){
        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.addASsignments(postJson);

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
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/assignment/update")
    public RestResult updateDataA(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.updateAssignment(postJson);

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
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/assignment/addquestion")
    public RestResult updateAddQuestion(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.addQuestion(postJson);

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
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    @RequestMapping("/assignment/delquestion")
    public RestResult updateDelQuestion(@RequestBody String postJson)
    {

        int result=0;

        RestResult<Object> resultt = null;
        try{
            result=assignmentService.delAssignmentQuestion(postJson);

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
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }



}
