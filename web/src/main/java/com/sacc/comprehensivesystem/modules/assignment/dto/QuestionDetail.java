package com.sacc.comprehensivesystem.modules.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sacc.comprehensivesystem.common.enums.Difficulty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 单个题目详情
 * @author gaofan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class QuestionDetail {
    private Long id;
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

}
