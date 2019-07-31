package com.sacc.ComprehensiveSystem.modules.assignment.dto;


/**
 * 单个输入输出示例
 * @author gaofan
 */
public class IoSample {
    private String iSample;
    private String oSample;

    public String getiSample() {
        return iSample;
    }

    public IoSample setiSample(String iSample) {
        this.iSample = iSample;
        return this;
    }

    public String getoSample() {
        return oSample;
    }

    public IoSample setoSample(String oSample) {
        this.oSample = oSample;
        return this;
    }
}
