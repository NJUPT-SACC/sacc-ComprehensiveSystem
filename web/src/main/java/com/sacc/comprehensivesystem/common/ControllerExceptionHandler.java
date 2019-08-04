package com.sacc.comprehensivesystem.common;

import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.assignment.exception.AssignmentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 处理controller抛出的异常，包装成RestResult
 * @author gaofan
 */
@ControllerAdvice
@RestController
public class ControllerExceptionHandler implements ErrorController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @RequestMapping("/error")
    public RestResult<Object> handleException(Exception uncaughtException){
        if (uncaughtException == null){
            return new RestResult<>().setStatus(RestResult.STATUS_OTHERS).setMessage("failure");
        }
        if (uncaughtException instanceof AssignmentException){
            AssignmentException assignmentException = (AssignmentException) uncaughtException;
            return new RestResult<>()
                    .setStatus(assignmentException.getAssignmentExceptionCode().getCode())
                    .setMessage("failure")
                    .setData(assignmentException.getAssignmentExceptionCode().getMessage());
        }else{
            //记录未知的异常
            logger.error("ERROR", uncaughtException);
            return new RestResult<>()
                    .setStatus(RestResult.STATUS_OTHERS)
                    .setMessage("failure")
                    .setData(uncaughtException.getMessage());
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
