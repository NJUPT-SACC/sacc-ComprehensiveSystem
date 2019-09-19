package com.sacc.comprehensivesystem.modules.assignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sacc.comprehensivesystem.common.entity.BasicEntity;

public class QuestionBankResult  extends BasicEntity {
    private String title;
    private String description;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String choiceE;
    private String choiceF;
    @JsonIgnore
    private String correctAnswer;
    private int difficulty;

   public String[] getChoices() {
        return new String[]{choiceA, choiceB, choiceC, choiceD, choiceE, choiceF};
    }
    public String getTitle() {
        return title;
    }

    public QuestionBankResult setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuestionBankResult setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getChoiceE() {
        return choiceE;
    }

    public void setChoiceE(String choiceE) {
        this.choiceE = choiceE;
    }

    public String getChoiceF() {
        return choiceF;
    }

    public void setChoiceF(String choiceF) {
        this.choiceF = choiceF;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionBankResult setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public  QuestionBankResult setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }
}


