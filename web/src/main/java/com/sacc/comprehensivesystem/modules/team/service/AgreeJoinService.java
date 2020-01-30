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
public class AgreeJoinService {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    TeamChangeDao teamChangeDao;

    @Autowired
    Team_userDao team_userDao;
    public int agreeJoin(String postJosn){
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        System.out.println(info);
        JSONObject jsonObject = new JSONObject(postJosn);
        String teamName=jsonObject.getString("team_name");
        Team team = null;
        try {
            team = teamChangeDao.returnTeam(teamName);
        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            return 0;
        }
        if(sysUser.getTeamId()==0) {
            if (team.getMemberBId() == 0) {
                team.setMemberBId(sysUser.getId());
                team.setUpdateBy(sysUser.getId());
                try {
                    teamChangeDao.updateTeamB(team.getId(), sysUser.getId());
                    team_userDao.updateMember(team.getId(), sysUser.getId());
                } catch (Exception e) {
                    e.getStackTrace();
                    logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                    return 0;
                }
            } else {
                team.setMemberCId(sysUser.getId());
                team.setUpdateBy(sysUser.getId());
                try {
                    teamChangeDao.updateTeamC(team.getId(), sysUser.getId());
                    team_userDao.updateMember(team.getId(), sysUser.getId());
                } catch (Exception e) {
                    e.getStackTrace();
                    logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
                    return 0;
                }
            }
        }else {
            return 2;
        }
        return 1;
    }
}
