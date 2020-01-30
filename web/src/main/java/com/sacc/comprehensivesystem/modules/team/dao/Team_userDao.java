package com.sacc.comprehensivesystem.modules.team.dao;

import com.sacc.comprehensivesystem.modules.team.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Team_userDao {
     void updateLeader(Team team);
     void updateMember(@Param("team_id") Long teamId,@Param("user_id") Long userId);
}
