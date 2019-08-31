package com.sacc.comprehensivesystem.modules.assignment.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author goufaan
 */

public class Answer {
    private Long id;
    private String answer;

    public Long getId() {
        return id;
    }

    public Answer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Answer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
