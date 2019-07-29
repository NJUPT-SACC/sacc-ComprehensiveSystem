package com.sacc.ComprehensiveSystem.modules.voj.entity;

/**
 * 试题标签的实体类
 * 对应数据库中的voj_problem_tags数据表.
 */
public class ProblemTag {
    /**
     * 试题标签的唯一标识符.
     */
    private long problemTagId;

    /**
     * 试题标签的别名.
     */
    private String problemTagSlug;

    /**
     * 试题标签的名称.
     */
    private String problemTagName;

    /**
     * 唯一的序列化标识符.
     */
    private static final long serialVersionUID = 1636220984815816993L;

    public long getProblemTagId() {
        return problemTagId;
    }

    public void setProblemTagId(long problemTagId) {
        this.problemTagId = problemTagId;
    }

    public String getProblemTagSlug() {
        return problemTagSlug;
    }

    public void setProblemTagSlug(String problemTagSlug) {
        this.problemTagSlug = problemTagSlug;
    }

    public String getProblemTagName() {
        return problemTagName;
    }

    public void setProblemTagName(String problemTagName) {
        this.problemTagName = problemTagName;
    }

}
