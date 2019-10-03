package com.sacc.comprehensivesystem.modules.voj.service;

import com.github.pagehelper.PageHelper;
import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.enums.Difficulty;
import com.sacc.comprehensivesystem.common.utils.StringUtils;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBankResult;
import com.sacc.comprehensivesystem.modules.voj.dao.CheckpointDao;
import com.sacc.comprehensivesystem.modules.voj.dao.ProblemCategoryDao;
import com.sacc.comprehensivesystem.modules.voj.dao.ProblemDao;
import com.sacc.comprehensivesystem.modules.voj.dao.ProblemTagDao;
import com.sacc.comprehensivesystem.modules.voj.entity.Checkpoint;
import com.sacc.comprehensivesystem.modules.voj.entity.Problem;
import com.sacc.comprehensivesystem.modules.voj.entity.ProblemCategory;
import com.sacc.comprehensivesystem.modules.voj.entity.ProblemTag;
import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProblemService {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

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

    public boolean addProblem(String postJosn){
        QuestionBank questionBank=new QuestionBank();
        JSONObject jsonObject = new JSONObject(postJosn);
        questionBank.setTitle(jsonObject.getString("title"));
        questionBank.setDescription(jsonObject.getString("description"));
        questionBank.setChoiceA(jsonObject.getString("choiceA"));
        questionBank.setChoiceB(jsonObject.getString("choiceB"));
        questionBank.setChoiceC(jsonObject.getString("choiceC"));
        questionBank.setChoiceD(jsonObject.getString("choiceD"));
        questionBank.setChoiceE(jsonObject.getString("choiceE"));
        questionBank.setChoiceF(jsonObject.getString("choiceF"));
        questionBank.setCorrectAnswer(jsonObject.getString("correctAnswer"));
        String difficulty =jsonObject.getString("difficulty");
        Difficulty difficulty1=Difficulty.valueOf(difficulty);
        questionBank.setDifficulty(difficulty1);
        int difficultyInt=difficulty1.ordinal();
        /**
         * 获取当前用户
         */
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        questionBank.setCreateBy(sysUser.getCreateBy());
        questionBank.setUpdateBy(sysUser.getUpdateBy());

        try{
            problemDao.insert(questionBank,difficultyInt);
        }catch (Exception e){
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            return false;
        }
        return true;
    }
    public List<QuestionBankResult> getQuestion(String postJosn) {

        JSONObject jsonObject = new JSONObject(postJosn);
        int page=jsonObject.getInt("page");
        int row=jsonObject.getInt("row");
        int difficulty=jsonObject.getInt("difficulty");
        PageHelper.startPage(page, row);
        List<QuestionBankResult> result = problemDao.getQuestion(difficulty);
        return result;
    }
}
