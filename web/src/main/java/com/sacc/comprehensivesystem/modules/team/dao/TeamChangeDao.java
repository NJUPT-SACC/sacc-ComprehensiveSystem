package com.sacc.comprehensivesystem.modules.team.dao;

import com.sacc.comprehensivesystem.modules.team.entity.Team;

public interface TeamChangeDao {
    void createTeam(Team team);
    String foudByName(String name);
}
