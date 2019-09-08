package com.sacc.comprehensivesystem.modules.assignment.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author goufaan
 */
@Data
@Accessors(chain = true)
public class Answer {
    private Long id;
    private String answer;
}
