package com.sacc.comprehensivesystem.modules.management.controller;

import com.sacc.comprehensivesystem.common.entity.RequestParam;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBankResult;
import com.sacc.comprehensivesystem.modules.management.Service.AssignmentService1;
import com.sacc.comprehensivesystem.modules.management.Service.CompetitionService1;
import com.sacc.comprehensivesystem.modules.voj.service.ProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhongchenyuu
 */

@RestController
@RequestMapping("management")
public class ManagementController {

    Logger logger = LoggerFactory.getLogger(ManagementController.class);

    @Autowired
    ProblemService problemService;

    @Autowired
    AssignmentService1 assignmentService;

    @Autowired
    CompetitionService1 competitionService;


    /**
     * 获取题库列表
     * @param postjson page分页页数，row分页行数，difficult 题目难度
     * @return
     */
    @RequestMapping(value = "/problem/get")
    public RestResult getQuestion(/**@PathVariable int page,@PathVariable int row,@PathVariable int difficulty**/@RequestBody String postjson) {
        List<QuestionBankResult> result = problemService.getQuestion(postjson);
        if (result != null) {
            return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", result);
        } else {
            return new RestResult(RestResult.STATUS_OTHERS, "调用失败", null);
        }
    }

    /**
     * //TODO
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/problem/create", method = RequestMethod.POST)
    public RestResult createProblem(@RequestBody RequestParam requestParam) {

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

    /**
     * 向题库中添加选择题
     * @param postjson titlt ,description 描述，choiceA,choiceB,choiceC,choiceD,choiceE,choiceF,correctAnswer 正确答案,difficulty 题目难度
     * @return
     */
    @RequestMapping(value = "/problem/add")
    public RestResult addProblem(@RequestBody String postjson) {
        try {
            boolean result = problemService.addProblem(postjson);
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

    /**
     * 后台添加比赛
     * @param postJson name 比赛名字，satrtTime 比赛开始时间，endTime比赛结束时间，location 比赛地点，questionId比赛中题目id
     * @return
     */
    @RequestMapping("/competition/add")
    public RestResult Addcompetition(@RequestBody String postJson) {
        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = competitionService.addCompetition(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            result = 0;
        }

        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "作业添加成功", null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "作业添加失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    /**
     * 更新比赛信息
     * @param postJson competitionOldName 老的比赛名字，competitionNewName 新的比赛名字,competitionStartTime 比赛开始时间,competitionEndTime 比赛结束时间，competitionLocation 比赛地址
     * @return
     */
    @RequestMapping("/competition/update")
    public RestResult updateData(@RequestBody String postJson) {

        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = competitionService.updateCompetition(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "作业更新成功", null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "作业更新失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    /**
     * 向比赛中添加题目
     * @param postJson
     * @return
     */
    @RequestMapping("/competition/addquestion")
    public RestResult addQuestion(@RequestBody String postJson) {

        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = competitionService.addCompetitionQuestion(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目添加新成功", null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目添加失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    /**
     * 从比赛中删除题目
     * @param postJson questionId 题目id数组，competitionId 比赛id
     * @return
     */
    @RequestMapping("/competition/deletequestion")
    public RestResult deleteQuestion(@RequestBody String postJson) {

        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = competitionService.delCompetitionQuestion(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目删除新成功", null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目删除失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    /**
     * 添加练习
     * @param postJson
     * @return
     */
    @RequestMapping("/assignment/add")
    public RestResult AddAssignment(@RequestBody String postJson) {
        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = assignmentService.addASsignments(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            result = 0;
        }

        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "练习添加成功", null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "添加练习失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    /**
     * 练习更新
     * @param postJson
     * @return
     */
    @RequestMapping("/assignment/update")
    public RestResult updateDataA(@RequestBody String postJson) {

        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = assignmentService.updateAssignment(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "练习更新成功", null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "练习更新失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }


    /**
     * 向练习中添加问题
     * @param postJson
     * @return
     */
    @RequestMapping("/assignment/addquestion")
    public RestResult updateAddQuestion(@RequestBody String postJson) {

        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = assignmentService.addQuestion(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目添加成功", null);
                break;
            case 0:
                resultt = new RestResult<Object>(RestResult.STATUS_OTHERS, "题目添加失败失败", null);
                break;
            default:
                resultt = new RestResult<>(RestResult.STATUS_OTHERS, "调用失败", null);
        }
        return resultt;
    }

    /**
     * 从比赛中删除问题
     * @param postJson
     * @return
     */
    @RequestMapping("/assignment/delquestion")
    public RestResult updateDelQuestion(@RequestBody String postJson) {

        int result = 0;

        RestResult<Object> resultt = null;
        try {
            result = assignmentService.delAssignmentQuestion(postJson);

        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            result = 0;
        }
        switch (result) {
            case 1:
                resultt = new RestResult<Object>(RestResult.STATUS_SUCCESS, "题目删除成功", null);
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
