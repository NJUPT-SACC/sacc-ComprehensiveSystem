package com.sacc.comprehensivesystem.modules.voj.entity;


/**
 * 程序评测结果的Model.
 * 对应数据库中的voj_judge_results数据表.
 */
 public class JudgeResult {
    /**
     * 评测结果的唯一标识符.
     */
    private int judgeResultId;

    /**
     * 评测结果的英文唯一缩写.
     */
    private String judgeResultSlug;

    /**
     * 评测结果的名称.
     */
    private String judgeResultName;

    /**
     * 唯一的序列化标识符.
     */
    private static final long serialVersionUID = -1572920076997918781L;

    /**
     * 评测结果类的默认构造函数.
     */
    public JudgeResult() { }

    /**
     * 评测结果类的构造函数.
     * @param judgeResultId - 评测结果的唯一标识符
     * @param judgeResultSlug - 评测结果的别名
     * @param judgeResultName - 评测结果的名称
     */
    public JudgeResult(int judgeResultId, String judgeResultSlug, String judgeResultName) {
        this.judgeResultId = judgeResultId;
        this.judgeResultSlug = judgeResultSlug;
        this.judgeResultName = judgeResultName;
    }

    public int getJudgeResultId() {
        return judgeResultId;
    }

    public void setJudgeResultId(int judgeResultId) {
        this.judgeResultId = judgeResultId;
    }

    public String getJudgeResultSlug() {
        return judgeResultSlug;
    }

    public void setJudgeResultSlug(String judgeResultSlug) {
        this.judgeResultSlug = judgeResultSlug;
    }

    public String getJudgeResultName() {
        return judgeResultName;
    }

    public void setJudgeResultName(String judgeResultName) {
        this.judgeResultName = judgeResultName;
    }
}
