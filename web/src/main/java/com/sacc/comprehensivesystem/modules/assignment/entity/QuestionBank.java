package com.sacc.comprehensivesystem.modules.assignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sacc.comprehensivesystem.common.entity.BasicEntity;
import com.sacc.comprehensivesystem.common.enums.Difficulty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 单个问题实体
 * @author goufaan
 */

public class QuestionBank extends BasicEntity {
    private String title;
    private String description;
    @JsonIgnore
    private String choiceA;
    @JsonIgnore
    private String choiceB;
    @JsonIgnore
    private String choiceC;
    @JsonIgnore
    private String choiceD;
    @JsonIgnore
    private String choiceE;
    @JsonIgnore
    private String choiceF;
    @JsonIgnore
    private String correctAnswer;
    private Difficulty difficulty;

    public String[] getChoices(){
        return new String[]{choiceA,choiceB,choiceC,choiceD,choiceE,choiceF};
    }

    public String getTitle() {
        return title;
    }

    public QuestionBank setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuestionBank setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public QuestionBank setChoiceA(String choiceA) {
        this.choiceA = choiceA;
        return this;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public QuestionBank setChoiceB(String choiceB) {
        this.choiceB = choiceB;
        return this;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public QuestionBank setChoiceC(String choiceC) {
        this.choiceC = choiceC;
        return this;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public QuestionBank setChoiceD(String choiceD) {
        this.choiceD = choiceD;
        return this;
    }

    public String getChoiceE() {
        return choiceE;
    }

    public QuestionBank setChoiceE(String choiceE) {
        this.choiceE = choiceE;
        return this;
    }

    public String getChoiceF() {
        return choiceF;
    }

    public QuestionBank setChoiceF(String choiceF) {
        this.choiceF = choiceF;
        return this;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionBank setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public QuestionBank setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }
}
