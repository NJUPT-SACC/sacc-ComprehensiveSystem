package com.sacc.ComprehensiveSystem.common.enums;

/**
 * @author goufaan
 */
public enum Difficulty {
    /**
     * 简单
     */
    Easy(1,"简单"),
    /**
     * 中等
     */
    Middle(2,"中等"),
    /**
     * 困难
     */
    Hard(3,"困难");

    private Integer level;
    private String description;

    Difficulty(int level, String desc){
        this.level = level;
        this.description = desc;
    }

    public Integer getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}
