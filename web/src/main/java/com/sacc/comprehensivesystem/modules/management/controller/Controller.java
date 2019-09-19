package com.sacc.comprehensivesystem.modules.management.controller;

import com.sacc.comprehensivesystem.common.entity.RequestParam;
import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBankResult;
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



}
