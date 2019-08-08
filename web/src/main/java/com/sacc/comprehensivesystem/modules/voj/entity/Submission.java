package com.sacc.comprehensivesystem.modules.voj.entity;

import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;

import java.util.Date;

public class Submission {
    /**
     * 评测记录的唯一标识符.
     */
    private long submissionId;

    /**
     * 评测对应的试题对象.
     */
    private Problem problem;

    /**
     * 评测提交者的用户对象.
     */
    private SysUser user;

    /**
     * 提交所使用的语言对象.
     */
    private Language language;

    /**
     * 评测提交时间.
     */
    private Date submitTime;

    /**
     * 评测开始执行时间.
     */
    private Date executeTime;

    /**
     * 评测运行总时间.
     */
    private long usedTime;

    /**
     * 评测运行占用最大内存.
     */
    private int usedMemory;

    /**
     * 评测结果.
     */
    private JudgeResult judgeResult;

    /**
     * 评测运行得分.
     */
    private int judgeScore;

    /**
     * 评测运行日志.
     */
    private String judgeLog;

    /**
     * 评测所执行的代码.
     */
    private String code;

    /**
     * 唯一的序列化标识符.
     */
    private static final long serialVersionUID = -1984683070957842963L;

    public Submission() {

    }

    /**
     * 评测记录类的构造函数.
     * @param problem - 评测对应的试题对象
     * @param user - 评测提交者的用户对象
     * @param language - 提交所使用的语言对象
     * @param code - 评测所执行的代码
     */
    public Submission(Problem problem, SysUser user, Language language, String code) {
        this.problem = problem;
        this.user = user;
        this.language = language;
        this.code = code;
    }

    /**
     * 评测记录类的构造函数.
     * @param problem - 评测对应的试题对象
     * @param user - 评测提交者的用户对象
     * @param language - 提交所使用的语言对象
     * @param submitTime - 评测提交时间
     * @param executeTime - 评测开始执行时间
     * @param usedTime - 评测运行总时间
     * @param usedMemory - 评测运行占用最大内存
     * @param judgeResult - 评测结果
     * @param judgeScore - 评测运行得分
     * @param judgeLog - 评测运行日志
     * @param code - 评测所执行的代码
     */
    public Submission(Problem problem, SysUser user, Language language, Date submitTime,  Date executeTime,
                      int usedTime, int usedMemory, JudgeResult judgeResult, int judgeScore,  String judgeLog, String code) {
        this(problem, user, language, code);
        this.submitTime = submitTime;
        this.executeTime = executeTime;
        this.usedTime = usedTime;
        this.usedMemory = usedMemory;
        this.judgeResult = judgeResult;
        this.judgeScore = judgeScore;
        this.judgeLog = judgeLog;
    }

    /**
     * 评测记录类的构造函数.
     * @param submissionId - 评测记录的唯一标识符
     * @param problem - 评测对应的试题对象
     * @param user - 评测提交者的用户对象
     * @param language - 提交所使用的语言对象
     * @param submitTime - 评测提交时间
     * @param executeTime - 评测开始执行时间
     * @param usedTime - 评测运行总时间
     * @param usedMemory - 评测运行占用最大内存
     * @param judgeResult - 评测结果
     * @param judgeScore - 评测运行得分
     * @param judgeLog - 评测运行日志
     * @param code - 评测所执行的代码
     */
    public Submission(long submissionId, Problem problem, SysUser user, Language language, Date submitTime,
                      Date executeTime, int usedTime, int usedMemory, JudgeResult judgeResult, int judgeScore,
                      String judgeLog, String code) {
        this(problem, user, language, submitTime, executeTime, usedTime, usedMemory, judgeResult, judgeScore, judgeLog, code);
        this.submissionId = submissionId;
    }

    public long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(long submissionId) {
        this.submissionId = submissionId;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public long getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(long usedTime) {
        this.usedTime = usedTime;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(int usedMemory) {
        this.usedMemory = usedMemory;
    }

    public JudgeResult getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(JudgeResult judgeResult) {
        this.judgeResult = judgeResult;
    }

    public int getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(int judgeScore) {
        this.judgeScore = judgeScore;
    }

    public String getJudgeLog() {
        return judgeLog;
    }

    public void setJudgeLog(String judgeLog) {
        this.judgeLog = judgeLog;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
