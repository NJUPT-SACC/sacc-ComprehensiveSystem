package com.sacc.comprehensivesystem.modules.team.controller;

import com.sacc.comprehensivesystem.common.utils.RestResult;
import com.sacc.comprehensivesystem.modules.team.entity.Team;
import com.sacc.comprehensivesystem.modules.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TeamController {

    @Autowired
    TeamService agreeJoinService;

    @RequestMapping("team/join")
    public RestResult join(@RequestBody String postJson) {
        int a = agreeJoinService.agreeJoin(postJson);
        if (a == 1) {
            return new RestResult<>(RestResult.STATUS_SUCCESS, "同意成功", null);
        } else if (a == 2) {
            return new RestResult<>(RestResult.STATUS_OTHERS, "对不起，您已加入队伍", null);
        } else {
            return new RestResult<>(RestResult.STATUS_OTHERS, "异常，调用失败", null);
        }
    }

    @RequestMapping("team/getList")
    public RestResult getList() {
        List<Team> list = new ArrayList<>();
        list = agreeJoinService.getList();
        if (list != null) {
            return new RestResult<>(RestResult.STATUS_SUCCESS, "队伍提取成功", list);
        }else {
            return new RestResult<>(RestResult.STATUS_OTHERS, "异常，调用失败", null);
        }
    }
}
