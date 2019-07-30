package com.sacc.ComprehensiveSystem.modules.assignment.exception;

/**
 * @author gaofan
 */
public enum AssignmentExceptionCode{
    /**
     *
     */
    ASSIGNMENT_NOT_EXIST(3000,"抱歉，该作业不存在。"),
    ASSIGNMENT_EXPIRED(3001,"抱歉，该作业已过提交期限。"),
    ASSIGNMENT_NOT_START_YET(3002, "抱歉，该作业还未开始。")
    ;

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public AssignmentExceptionCode setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AssignmentExceptionCode setMessage(String message) {
        this.message = message;
        return this;
    }

    AssignmentExceptionCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
