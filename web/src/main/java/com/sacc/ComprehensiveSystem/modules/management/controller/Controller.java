package com.sacc.ComprehensiveSystem.modules.management.controller;

import com.sacc.ComprehensiveSystem.common.entity.RequestParam;
import com.sacc.ComprehensiveSystem.common.utils.JSONUtils;
import com.sacc.ComprehensiveSystem.common.utils.RestResult;
import com.sacc.ComprehensiveSystem.modules.voj.service.ProblemService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management")
public class Controller {

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    ProblemService problemService;

    @RequestMapping(value = "/problem/create", method = RequestMethod.POST)
    public RestResult createProblem( @RequestBody RequestParam requestParam) {

        try {
            boolean result = problemService.createProblem(requestParam.getProblemName(), requestParam.getTimeLimit(), requestParam.getMemoryLimit(),
                    requestParam.getDescription(), requestParam.getHint(), requestParam.getInputFormat(), requestParam.getOutputFormat(),
                    requestParam.getInputSample(), requestParam.getOutputSample(), requestParam.getTestCases(), requestParam.getProblemCategories(),
                    requestParam.getProblemTags(), requestParam.isPublic(), requestParam.isExactlyMatch());
            if (result) {
                return new RestResult(RestResult.STATUS_SUCCESS, "调用成功", null);
            } else {
                return new RestResult(RestResult.STATUS_OTHERS, "调用失败", null);
            }
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            return new RestResult(RestResult.STATUS_OTHERS, "异常，调用失败", null);
        }

    }
}
