package com.sacc.ComprehensiveSystem.modules.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sacc.ComprehensiveSystem.common.enums.Difficulty;



/**
 * 单个题目详情
 * @author gaofan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDetail {
    private Integer id;
    private String title;
    private String disc;
    private Difficulty difficulty;
    /**
     * 选择题
     */
    private String[] choices;
    /**
     * 编程题
     */
    private Integer timeLimit;
    private Integer spaceLimit;
    private String iStandard;
    private String oStandard;
    private IoSample IOSample;

    public Integer getId() {
        return id;
    }

    public QuestionDetail setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QuestionDetail setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDisc() {
        return disc;
    }

    public QuestionDetail setDisc(String disc) {
        this.disc = disc;
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public QuestionDetail setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String[] getChoices() {
        return choices;
    }

    public QuestionDetail setChoices(String[] choices) {
        this.choices = choices;
        return this;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public QuestionDetail setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public Integer getSpaceLimit() {
        return spaceLimit;
    }

    public QuestionDetail setSpaceLimit(Integer spaceLimit) {
        this.spaceLimit = spaceLimit;
        return this;
    }

    public String getiStandard() {
        return iStandard;
    }

    public QuestionDetail setiStandard(String iStandard) {
        this.iStandard = iStandard;
        return this;
    }

    public String getoStandard() {
        return oStandard;
    }

    public QuestionDetail setoStandard(String oStandard) {
        this.oStandard = oStandard;
        return this;
    }

    public IoSample getIOSample() {
        return IOSample;
    }

    public QuestionDetail setIOSample(IoSample IOSample) {
        this.IOSample = IOSample;
        return this;
    }
}
