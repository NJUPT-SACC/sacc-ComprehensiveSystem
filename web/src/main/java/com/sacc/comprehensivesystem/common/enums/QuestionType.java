package com.sacc.comprehensivesystem.common.enums;

/**
 * @author goufaan
 */
public enum QuestionType {
    /**
     * 选择题
     */
    MultipleChoice(1,"选择题"),
    /**
     * 编程题
     */
    Programming(2,"编程题");

    Integer type;
    String description;

    QuestionType(int type, String description){
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
