package com.sacc.comprehensivesystem.modules.team.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.modules.team.dao.TeamChangeDao;
import com.sacc.comprehensivesystem.modules.team.dao.Team_userDao;
import com.sacc.comprehensivesystem.modules.team.entity.Team;
import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    TeamChangeDao teamChangeDao;
    @Autowired
    Team_userDao team_userDao;

    public int applicationTeam(String postJosn) {

        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        System.out.println(info);
        JSONObject jsonObject = new JSONObject(postJosn);
        Long competitionId = jsonObject.getLong("competition_id");
        Long leaderId = sysUser.getId();
        Long createBy = sysUser.getId();
        Long updateBy = sysUser.getId();
        String name = jsonObject.getString("team_name");
        Team team = new Team(competitionId, leaderId, updateBy, createBy, name);
        String name2 =  " ";//查重名
        try {
            name2 = teamChangeDao.foudByName(name);
        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            return 0;
        }
        if (name2 == null) {
            try {
                teamChangeDao.createTeam(team);
            } catch (Exception e) {
                e.getStackTrace();
                logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                return 0;
            }
        }else  {
            return 2;
        }
        Long teamId=0l;
        try {
            teamId = teamChangeDao.foudByLeader(leaderId);
            team.setId(teamId);
            team_userDao.updateLeader(team);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            return 0;
        }

        return 1;
    }
}
