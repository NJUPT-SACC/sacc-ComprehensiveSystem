package com.sacc.ComprehensiveSystem.modules.voj.service;

import com.sacc.ComprehensiveSystem.modules.voj.dao.LanguageDao;
import com.sacc.ComprehensiveSystem.modules.voj.dao.ProblemDao;
import com.sacc.ComprehensiveSystem.modules.voj.entity.Language;
import com.sacc.ComprehensiveSystem.modules.voj.entity.Problem;
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

    /**
    public Map<String, Object> createSubmission(String postJson) {
        JSONObject jsonObject = new JSONObject(postJson);
        Problem problem = problemDao.getProblem(jsonObject.getLong("problemId"));
        Language language = languageDao.getLanguageUsingSlug(jsonObject.getString("languageSlug"));

    }**/
}
