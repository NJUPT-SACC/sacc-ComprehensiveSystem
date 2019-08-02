package com.sacc.ComprehensiveSystem.modules.voj.service;

import com.github.pagehelper.PageHelper;
import com.sacc.ComprehensiveSystem.common.utils.JSONUtils;
import com.sacc.ComprehensiveSystem.common.utils.StringUtils;
import com.sacc.ComprehensiveSystem.modules.voj.dao.CheckpointDao;
import com.sacc.ComprehensiveSystem.modules.voj.dao.ProblemCategoryDao;
import com.sacc.ComprehensiveSystem.modules.voj.dao.ProblemDao;
import com.sacc.ComprehensiveSystem.modules.voj.dao.ProblemTagDao;
import com.sacc.ComprehensiveSystem.modules.voj.entity.Checkpoint;
import com.sacc.ComprehensiveSystem.modules.voj.entity.Problem;
import com.sacc.ComprehensiveSystem.modules.voj.entity.ProblemCategory;
import com.sacc.ComprehensiveSystem.modules.voj.entity.ProblemTag;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    @Autowired
    private CheckpointDao checkpointDao;

    @Autowired
    private ProblemCategoryDao problemCategoryDao;

    @Autowired
    private ProblemTagDao problemTagDao;

    /**
     * 获取题目列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param problemCategary
     * @param problemTag
     * @param isPublicOnly
     * @return
     */
    public List<Problem> prrblemList(String keyword, int pageNum, int pageSize, int problemCategary,
                                     long problemTag, boolean isPublicOnly) {
        PageHelper.startPage(pageNum, pageSize);
        List<Problem> list = problemDao.getProblemsUsingFilters(keyword, problemCategary, problemTag, isPublicOnly);

        return list;
    }

    public boolean createProblem(String problemName, int timeLimit, int memoryLimit, String description,
                                             String hint, String inputFormat, String outputFormat, String sampleInput,
                                             String sampleOutput, String testCases, String problemCategory, String problemTag,
                                             boolean isPublicOnly, boolean isExactlyMatch) {
        Problem problem = new Problem(isPublicOnly, problemName, timeLimit, memoryLimit, description,
                inputFormat, outputFormat, sampleInput, sampleOutput, hint);

        problemDao.createProblem(problem);
        long problemId = problem.getProblemId();

        System.out.println(problemId);
        createTestCases(problemId, testCases, isExactlyMatch);
        createProblemCategoryRelationships(problemId, problemCategory);
        createProblemTags(problemId, problemTag);
        return true;
    }

    private void createTestCases(long problemId, String testCases, boolean isExactlyMatch) {
        JSONArray jsonArray = new JSONArray(testCases);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject testCase = jsonArray.getJSONObject(i);

            int score = 100 / jsonArray.length();
            if ( i == jsonArray.length() - 1 ) {
                score = 100 - score * i;
            }

            String input = testCase.getString("input");
            String output = testCase.getString("output");

            Checkpoint checkPoint = new Checkpoint(problemId, i, isExactlyMatch, score, input, output);
            checkpointDao.createCheckpoint(checkPoint);
        }
    }

    private void createProblemCategoryRelationships(long problemId, String problemCategories) {
        JSONArray jsonArray = new JSONArray(problemCategories);

        if (jsonArray.length() == 0) {
            jsonArray.put("uncategorized");
        }

        for ( int i = 0; i < jsonArray.length(); ++ i ) {
            String problemCategorySlug = jsonArray.getString(i);
            ProblemCategory pc = problemCategoryDao.getProblemCategoryUsingCategorySlug(problemCategorySlug);

            problemCategoryDao.createProblemCategoryRelationship(problemId, pc);
        }
    }

    private void createProblemTags(long problemId, String problemTags) {
        Set<String> problemTagSlugs = new HashSet<>();
        JSONArray jsonArray = new JSONArray(problemTags);

        for ( int i = 0; i < jsonArray.length(); ++ i ) {
            String problemTagName = jsonArray.getString(i);
            String problemTagSlug = StringUtils.getSlug(problemTagName);

            ProblemTag pt = problemTagDao.getProblemTagUsingTagSlug(problemTagSlug);
            if ( pt == null ) {
                pt = new ProblemTag(problemTagSlug, problemTagName);
                problemTagDao.createProblemTag(pt);
            }
            // Fix Bug: Two tags have different tag name but the same tag slug.
            // Example: Hello World / Hello-World
            if ( !problemTagSlugs.contains(problemTagSlug) ) {
                problemTagDao.createProblemTagRelationship(problemId, pt);
                problemTagSlugs.add(problemTagSlug);
            }
        }
    }
}
