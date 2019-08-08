package com.sacc.comprehensivesystem.modules.voj.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.modules.messager.MessageSender;
import com.sacc.comprehensivesystem.modules.voj.dao.LanguageDao;
import com.sacc.comprehensivesystem.modules.voj.dao.ProblemDao;
import com.sacc.comprehensivesystem.modules.voj.dao.SubmissionDao;
import com.sacc.comprehensivesystem.modules.voj.entity.Language;
import com.sacc.comprehensivesystem.modules.voj.entity.Problem;
import com.sacc.comprehensivesystem.modules.voj.entity.Submission;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SubmissionServicce {

    @Autowired
    private ProblemDao problemDao;

    @Autowired
    private LanguageDao languageDao;

    @Autowired
    private SubmissionDao submissionDao;

    @Autowired
    private MessageSender messageSender;

    /**
     * 创建提交
     * @param postJson
     * @return
     */
    public Map<String, Object> createSubmission(String postJson) {
        JSONObject jsonObject = new JSONObject(postJson);
        Problem problem = problemDao.getProblem(jsonObject.getLong("problemId"));
        Language language = languageDao.getLanguageUsingSlug(jsonObject.getString("languageSlug"));

        SysUser user = (SysUser) CacheUtils.get("user_info", jsonObject.get("authKey"));
        Submission submission = new Submission(problem, user, language,
                (String) jsonObject.get("code"));

        Map<String, Object> result = (Map<String,Object>) getSubmissionCreationResult(submission);

        boolean isSuccess = (Boolean) result.get("isSuccess");
        if (isSuccess) {
            submissionDao.createSubmission(submission);

            long submissionId = submission.getSubmissionId();
            result.put("submissionId", submissionId);
            result.put("user", user);

            createSubmissionTask(submissionId);
        }
        return result;
    }

    private Map<String, ? extends Object> getSubmissionCreationResult(Submission submission) {
        String code = submission.getCode();
        Map<String, Boolean> result = new HashedMap(4, 1);
        result.put("isProblemExists", submission.getProblem() != null);
        result.put("isLanguageExists", submission.getProblem() != null);
        result.put("isCodeEmpty", code == null || code.length() == 0);
        Boolean isSuccess = result.get("isProblemExists") && result.get("isLanguageExists")
                && !result.get("isCodeEmpty");
        result.put("isSuccess", isSuccess);
        return result;
    }

    public void createSubmissionTask(long submissionId) {
        Map<String, Object> mapMessage = new HashedMap();
        mapMessage.put("event", "SubmissionCreated");
        mapMessage.put("submissionId", submissionId);

        messageSender.sendMessage(mapMessage);
    }


}
