package com.sacc.comprehensivesystem.modules.assignment.dto;

import lombok.Data;

/**
 * @author goufaan
 */
@Data
public class AnswersModel {
    private Boolean isSubmit;
    private Answer[] answers;
}
