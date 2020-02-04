package com.sacc.comprehensivesystem.modules.team.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.service.RegistService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.dao.SysUserDao;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.modules.mail.MailService;
import com.sacc.comprehensivesystem.modules.team.dao.TeamChangeDao;
import com.sacc.comprehensivesystem.modules.team.dao.Team_userDao;
import com.sacc.comprehensivesystem.modules.team.entity.Team;
import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongchenyu
 */

@Service
public class TeamService {
    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    TeamChangeDao teamChangeDao;

    @Autowired
    Team_userDao team_userDao;

    public int agreeJoin(String authKey,String team_name) {
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(authKey);
        SysUser sysUser = info.getSysUser();
        System.out.println(info);
        String teamName = team_name;
        Team team = null;
        try {
            team = teamChangeDao.returnTeam(teamName);
        } catch (Exception e) {
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            return 0;
        }
        if (sysUser.getTeamId() == 0) {
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
        } else {
            return 2;
        }
        return 1;
    }

    @Autowired
    SysUserDao sysUserDao;

    public List<Team> getList() {
        List<Team> list = new ArrayList<>();
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        list = teamChangeDao.getTeam(sysUser.getTeamId());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setMemberB(sysUserDao.findUserNameByUserId(list.get(i).getMemberBId()));
            list.get(i).setMemberC(sysUserDao.findUserNameByUserId(list.get(i).getMemberCId()));
            list.get(i).setLeaderName(sysUserDao.findUserNameByUserId(list.get(i).getLeaderId()));
        }
        return list;
    }

    public List<Team> getAllList() {
        List<Team> list = new ArrayList<>();
        list = teamChangeDao.getAllTeam();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setMemberB(sysUserDao.findUserNameByUserId(list.get(i).getMemberBId()));
            list.get(i).setMemberC(sysUserDao.findUserNameByUserId(list.get(i).getMemberCId()));
            list.get(i).setLeaderName(sysUserDao.findUserNameByUserId(list.get(i).getLeaderId()));
        }
        return list;
    }

    @Autowired
    RegistService registService;
    @Autowired
    MailService mailService;
    public int sendEmail(String postJosn){
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        UserSimpleAuthorizationInfo info = (UserSimpleAuthorizationInfo) CacheUtils.getUserCache(token);
        SysUser sysUser = info.getSysUser();
        System.out.println(info);
        JSONObject jsonObject = new JSONObject(postJosn);
        String team_name = jsonObject.getString("team_name");
        String teamMate_name = jsonObject.getString("teamMate_name");
        SysUser toUser = sysUserDao.findUserByLoginName(teamMate_name);
        String signature = registService.userEmailPost(toUser);
        String to = sysUser.getEmail();
        try {
            mailService.sendTeamConfirmMail(to,"test", signature,team_name);
        }catch (Exception e){
            e.getStackTrace();
            logger.error("Error: {}\n3{}", e.getMessage(), e.getStackTrace());
            return 0;
        }
        return 1;
    }
}
