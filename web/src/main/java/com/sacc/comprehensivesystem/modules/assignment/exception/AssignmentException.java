package com.sacc.comprehensivesystem.modules.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 作业平台所使用的异常
 * @author gaofan
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Accessors(chain = true)
public class AssignmentException extends RuntimeException  {
    private AssignmentExceptionCode assignmentExceptionCode;
}
