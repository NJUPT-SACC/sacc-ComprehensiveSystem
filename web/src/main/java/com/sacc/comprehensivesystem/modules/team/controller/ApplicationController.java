package com.sacc.comprehensivesystem.modules.team.controller;

import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.team.service.ApplicationService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongchenyu
 */


@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @RequestMapping("/teamApplication")
    public RestResult application(@RequestBody String postJson) {
        int result = applicationService.applicationTeam(postJson);
        if (result == 1) {
            return new RestResult<>(RestResult.STATUS_SUCCESS, "调用成功", null);
        } else if(result ==2 ) {
            return new RestResult<>(RestResult.STATUS_OTHERS, "队伍名称重复", null);
        }else {
            return new RestResult<>(RestResult.STATUS_OTHERS, "异常，调用失败", null);
        }
    }
}
