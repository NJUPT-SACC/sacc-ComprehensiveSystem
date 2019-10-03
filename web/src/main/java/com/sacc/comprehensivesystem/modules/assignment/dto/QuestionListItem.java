package com.sacc.comprehensivesystem.modules.assignment.dto;

import com.sacc.comprehensivesystem.common.enums.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 作业全部题目列表的单项
 * @author goufaan
 */
@Data
@Accessors(chain = true)
public class QuestionListItem {
    private Long id;
    private String title;
    private Difficulty difficulty;
    private QuestionType questionType;
    private Boolean finish;

}
