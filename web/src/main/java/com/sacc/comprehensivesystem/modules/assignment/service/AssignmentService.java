package com.sacc.comprehensivesystem.modules.assignment.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.common.service.BasicService;
import com.sacc.comprehensivesystem.modules.assignment.dao.AssignmentMapper;
import com.sacc.comprehensivesystem.modules.assignment.dao.AssignmentQuestionMapper;
import com.sacc.comprehensivesystem.modules.assignment.entity.Assignment;
import com.sacc.comprehensivesystem.modules.assignment.entity.AssignmentQuestion;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion;
import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author goufaan
 */
@Component
public class AssignmentService{

    public String[] getNatureKeys() {
        return new String[]{"name", "startTime", "endTime"};
    }

    @Autowired
    AssignmentMapper assignmentMapper;

    @Autowired
    AssignmentQuestionMapper assignmentQuestionMapper;

    public void customInit(Assignment entity) {

    }
}
