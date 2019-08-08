package com.sacc.comprehensivesystem.modules.voj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Problem {
    /**
     * 试题的唯一标识符.
     */
    private long problemId;

    /**
     * 试题是否公开.
     */
    private boolean isPublic;

    /**
     * 试题名称.
     */
    @NotNull
    private String problemName;

    /**
     * 试题包含的标签.
     */
    private List<ProblemTag> problemTags;

    /**
     * 试题的总提交总数.
     */
    private long totalSubmission;

    /**
     * 试题的提交通过总数.
     */
    private long acceptedSubmission;

    /**
     * 最大运行时间.
     */
    @JsonIgnore
    private int timeLimit;

    /**
     * 最大运行内存.
     */
    @JsonIgnore
    private int memoryLimit;

    /**
     * 试题描述.
     */
    @JsonIgnore
    private String description;

    /**
     * 输入格式.
     */
    @JsonIgnore
    private String inputFormat;

    /**
     * 输出格式.
     */
    @JsonIgnore
    private String outputFormat;

    /**
     * 样例输入.
     */
    @JsonIgnore
    private String sampleInput;

    /**
     * 样例输出.
     */
    @JsonIgnore
    private String sampleOutput;

    /**
     * 试题提示.
     */
    @JsonIgnore
    private String hint;

    /**
     * 唯一的序列化标识符.
     */
    private static final long serialVersionUID = 4703482016721365341L;

    /**
     * Problem的默认构造函数.
     */
    public Problem() { }

    /**
     * Problem的构造函数.
     * @param isPublic - 试题是否公开
     * @param problemName - 试题的名称
     * @param timeLimit - 最大运行时间
     * @param memoryLimit - 最大运行内存
     * @param description - 试题描述
     * @param inputFormat - 试题输入格式描述
     * @param outputFormat - 试题输出描述
     * @param sampleInput - 试题样例输入
     * @param sampleOutput - 试题样例输出
     * @param hint - 试题提示
     */
    public Problem(boolean isPublic, String problemName, int timeLimit, int memoryLimit,
                   String description, String inputFormat, String outputFormat,
                   String sampleInput, String sampleOutput, String hint) {
        this.isPublic = isPublic;
        this.problemName = problemName;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.description = description;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.hint = hint;
    }

    /**
     * Problem类的构造函数.
     * @param problemId - 试题唯一标识符
     * @param isPublic - 试题是否公开
     * @param problemName - 试题的名称
     * @param timeLimit - 最大运行时间
     * @param memoryLimit - 最大运行内存
     * @param description - 试题描述
     * @param inputFormat - 试题输入描述
     * @param outputFormat - 试题输出描述
     * @param sampleInput - 试题样例输入
     * @param sampleOutput - 试题样例输出
     * @param hint - 试题提示
     */
    public Problem(long problemId, boolean isPublic, String problemName, int timeLimit,
                   int memoryLimit, String description, String inputFormat, String outputFormat,
                   String sampleInput, String sampleOutput, String hint) {
        this(isPublic, problemName, timeLimit, memoryLimit, description, inputFormat, outputFormat, sampleInput, sampleOutput, hint);
        this.problemId = problemId;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public List<ProblemTag> getProblemTags() {
        return problemTags;
    }

    public void setProblemTags(List<ProblemTag> problemTags) {
        this.problemTags = problemTags;
    }

    public long getTotalSubmission() {
        return totalSubmission;
    }

    public void setTotalSubmission(long totalSubmission) {
        this.totalSubmission = totalSubmission;
    }

    public long getAcceptedSubmission() {
        return acceptedSubmission;
    }

    public void setAcceptedSubmission(long acceptedSubmission) {
        this.acceptedSubmission = acceptedSubmission;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

}
