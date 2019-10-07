package com.sacc.comprehensivesystem.modules.voj.controller;

import com.sacc.comprehensivesystem.modules.messager.ApplicationEventListener;
import com.sacc.comprehensivesystem.modules.voj.dao.ProblemDao;
import com.sacc.comprehensivesystem.modules.voj.service.ProblemService;
import com.sacc.comprehensivesystem.modules.voj.service.SubmissionServicce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("submission")
public class SubmissionController {
    private Logger logger = LoggerFactory.getLogger(SubmissionController.class);

    @Autowired
    private ApplicationEventListener submissionEventListener;

    @Autowired
    private ProblemDao problemDao;

    @Autowired
    private SubmissionServicce submissionServicce;

    @Autowired
    private ProblemService problemService;
    /**
     * 获取实时的评测结果
     * @param submissionId
     * @return
     */
    @RequestMapping("/getRealTimeJudgeResult")
    public SseEmitter getRealTimeJudgeResultAction(@RequestParam(value = "submissionId")
               Long submissionId) {
        SseEmitter sseEmitter = new SseEmitter();
        submissionEventListener.addSseEmitters(submissionId, sseEmitter);
        try {
            sseEmitter.send("Established");
        } catch (IOException e) {
            logger.error("Error: {}\n{}", e.getMessage(),e.getStackTrace());
            return null;
        }
        return sseEmitter;
    }

    @RequestMapping(value = "/createSubmission", method = RequestMethod.POST)
    public Map<String, Object> createSubmission(String postJson) {
        Map<String, Object> result = submissionServicce.createSubmission(postJson);
        boolean isSuccess = (Boolean) result.get("isSuccess");
        if (isSuccess) {
            logger.info("User: {} submitted code  with SubmissionId {}",result.get("user"),
                    result.get("submissionId"));
        }
        return result;
    }

}
