package com.sacc.comprehensivesystem.modules.assignment.dto;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 单个输入输出示例
 * @author gaofan
 */
@Data
@Accessors(chain = true)
public class IoSample {
    private String iSample;
    private String oSample;
}
