package com.sacc.comprehensivesystem.modules.voj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 编程语言的Model.
 * 对应数据库中的voj_languages数据表.
 */
public class Language {
    /**
     * 编程语言的唯一标识符.
     */
    private int languageId;

    /**
     * 编程语言的别名.
     */
    private String languageSlug;

    /**
     * 编程语言的名称.
     */
    private String languageName;

    /**
     * 编程语言的编译命令.
     */
    @JsonIgnore
    private String compileCommand;

    /**
     * 编程语言的运行命令.
     */
    @JsonIgnore
    private String runCommand;

    /**
     * 唯一的序列化标识符
     */
    private static final long serialVersionUID = 9065824880175832696L;

    /**
     * 编程语言的默认构造函数.
     */
    public Language() { }

    /**
     * 编程语言的构造函数.
     * @param languageSlug - 编程语言的英文缩写
     * @param languageName - 编程语言的名称
     * @param compileCommand - 编程语言的编译命令
     * @param runCommand - 编程语言对应程序执行命令
     */
    public Language(String languageSlug, String languageName, String compileCommand, String runCommand) {
        this.languageSlug = languageSlug;
        this.languageName = languageName;
        this.compileCommand = compileCommand;
        this.runCommand = runCommand;
    }

    /**
     * 编程语言的构造函数.
     * @param languageId - 编程语言的唯一标识符
     * @param languageSlug - 编程语言的英文缩写
     * @param languageName - 编程语言的名称
     * @param compileCommand - 编程语言的编译命令
     * @param runCommand - 编程语言对应程序执行命令
     */
    public Language(int languageId, String languageSlug, String languageName, String compileCommand, String runCommand) {
        this(languageSlug, languageName, compileCommand, runCommand);
        this.languageId = languageId;
    }


    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageSlug() {
        return languageSlug;
    }

    public void setLanguageSlug(String languageSlug) {
        this.languageSlug = languageSlug;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCompileCommand() {
        return compileCommand;
    }

    public void setCompileCommand(String compileCommand) {
        this.compileCommand = compileCommand;
    }

    public String getRunCommand() {
        return runCommand;
    }

    public void setRunCommand(String runCommand) {
        this.runCommand = runCommand;
    }

}
