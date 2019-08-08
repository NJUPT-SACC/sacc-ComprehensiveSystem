package com.sacc.comprehensivesystem.modules.voj.entity;

public class ProblemCategory {
    /**
     * 试题分类的唯一标识符.
     */
    private int problemCategoryId;

    /**
     * 试题分类的别名.
     */
    private String problemCategorySlug;

    /**
     * 试题分类的名称.
     */
    private String problemCategoryName;

    /**
     * 试题分类的父类.
     */
    private int parentProblemCategoryId;

    /**
     * 唯一的序列化标识符.
     */
    private static final long serialVersionUID = 7093703712360669823L;

    /**
     * ProblemCategory的默认构造函数
     */
    public ProblemCategory() { }

    /**
     * ProblemCategory的构造函数
     */
    public ProblemCategory(String problemCategorySlug,
                           String problemCategoryName, int parentProblemCategoryId) {
        this.problemCategorySlug = problemCategorySlug;
        this.problemCategoryName = problemCategoryName;
        this.parentProblemCategoryId = parentProblemCategoryId;
    }

    /**
     * ProblemCategory的构造函数
     */
    public ProblemCategory(int problemCategoryId, String problemCategorySlug,
                           String problemCategoryName, int parentProblemCategoryId) {
        this(problemCategorySlug, problemCategoryName, parentProblemCategoryId);
        this.problemCategoryId = problemCategoryId;
    }

    public int getProblemCategoryId() {
        return problemCategoryId;
    }

    public void setProblemCategoryId(int problemCategoryId) {
        this.problemCategoryId = problemCategoryId;
    }

    public String getProblemCategorySlug() {
        return problemCategorySlug;
    }

    public void setProblemCategorySlug(String problemCategorySlug) {
        this.problemCategorySlug = problemCategorySlug;
    }

    public String getProblemCategoryName() {
        return problemCategoryName;
    }

    public void setProblemCategoryName(String problemCategoryName) {
        this.problemCategoryName = problemCategoryName;
    }

    public int getParentProblemCategoryId() {
        return parentProblemCategoryId;
    }

    public void setParentProblemCategoryId(int parentProblemCategoryId) {
        this.parentProblemCategoryId = parentProblemCategoryId;
    }


}
