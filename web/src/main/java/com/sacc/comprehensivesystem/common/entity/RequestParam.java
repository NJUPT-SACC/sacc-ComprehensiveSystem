package com.sacc.comprehensivesystem.common.entity;

public class RequestParam {
    private String problemName;
    private int timeLimit;
    private int memoryLimit;
    private String description;
    private String hint;
    private String inputFormat;
    private String outputFormat;
    private String inputSample;
    private String outputSample;
    private String testCases;
    private String problemCategories;
    private String problemTags;
    private boolean isPublic;
    private boolean isExactlyMatch;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
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

    public String getInputSample() {
        return inputSample;
    }

    public void setInputSample(String inputSample) {
        this.inputSample = inputSample;
    }

    public String getOutputSample() {
        return outputSample;
    }

    public void setOutputSample(String outputSample) {
        this.outputSample = outputSample;
    }

    public String getTestCases() {
        return testCases;
    }

    public void setTestCases(String testCases) {
        this.testCases = testCases;
    }

    public String getProblemCategories() {
        return problemCategories;
    }

    public void setProblemCategories(String problemCategories) {
        this.problemCategories = problemCategories;
    }

    public String getProblemTags() {
        return problemTags;
    }

    public void setProblemTags(String problemTags) {
        this.problemTags = problemTags;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isExactlyMatch() {
        return isExactlyMatch;
    }

    public void setExactlyMatch(boolean exactlyMatch) {
        isExactlyMatch = exactlyMatch;
    }
}
