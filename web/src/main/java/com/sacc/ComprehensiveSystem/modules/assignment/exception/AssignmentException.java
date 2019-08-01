package com.sacc.ComprehensiveSystem.modules.assignment.exception;

/**
 * 作业平台所使用的异常
 * @author gaofan
 */
public class AssignmentException extends RuntimeException  {
    private AssignmentExceptionCode assignmentExceptionCode;

    public AssignmentExceptionCode getAssignmentExceptionCode() {
        return assignmentExceptionCode;
    }

    public AssignmentException setAssignmentExceptionCode(AssignmentExceptionCode assignmentExceptionCode) {
        this.assignmentExceptionCode = assignmentExceptionCode;
        return this;
    }
}
