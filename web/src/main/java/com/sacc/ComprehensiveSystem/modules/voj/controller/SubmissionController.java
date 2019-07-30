package com.sacc.ComprehensiveSystem.modules.voj.controller;

import com.sacc.ComprehensiveSystem.modules.messager.ApplicationEventListener;
import com.sacc.ComprehensiveSystem.modules.voj.dao.ProblemDao;
import com.sacc.ComprehensiveSystem.modules.voj.entity.Problem;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("submission")
public class SubmissionController {
    private Logger logger = LoggerFactory.getLogger(SubmissionController.class);

    @Autowired
    private ApplicationEventListener submissionEventListener;

    @Autowired
    private ProblemDao problemDao;

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

//    @RequestMapping(value = "/createSubmission", method = RequestMethod.POST)
//    public boolean createSubmission(String postJson) {
//
//    }

}
