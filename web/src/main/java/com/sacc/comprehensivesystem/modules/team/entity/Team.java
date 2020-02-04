package com.sacc.comprehensivesystem.modules.team.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zhongchenyu
 */

public class Team {
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long competitionId;
    @JsonIgnore
    private long leaderId;
    @JsonIgnore
    private long memberBId=0;
    @JsonIgnore
    private long memberCId=0;

    private String memberB;

    private String memberC;
    @JsonIgnore
    private long updateBy;
    @JsonIgnore
    private long createBy;

    private String name;

    private String leaderName;

    public Team( long competitionId, long leaderId, long memberBId, long memberCId, long updateBy, long createBy) {
        this.competitionId = competitionId;
        this.leaderId = leaderId;
        this.memberBId = memberBId;
        this.memberCId = memberCId;

        this.createBy = createBy;
    }

    public Team(long competitionId, long leaderId,Long updateBy, long createBy,String name) {
        this.competitionId = competitionId;
        this.leaderId = leaderId;
        this.updateBy = updateBy;
        this.createBy = createBy;
        this.name = name;
    }

    public Team(long id,long leaderId,String name,long createBy,Long updateBy,long competitionId,long memberBId, long memberCId) {

        this.competitionId = competitionId;
        this.leaderId = leaderId;
        this.updateBy = updateBy;
        this.createBy = createBy;
        this.name = name;
        this.id = id;
        this.memberBId = memberBId;
        this.memberCId = memberCId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public Team setLeaderName(String leaderName) {
        this.leaderName = leaderName;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getMemberB() {
        return memberB;
    }

    public Team setMemberB(String memberB) {
        this.memberB = memberB;
        return this;
    }

    public String getMemberC() {
        return memberC;
    }

    public Team setMemberC(String memberC) {
        this.memberC = memberC;
        return this;
    }

    public long getId() {
        return id;
    }

    public Team setId(long id) {
        this.id = id;
        return this;
    }

    public long getCompetitionId() {
        return competitionId;
    }

    public Team setCompetitionId(long competitionId) {
        this.competitionId = competitionId;
        return this;
    }

    public long getLeaderId() {
        return leaderId;
    }

    public Team setLeaderId(long leaderId) {
        this.leaderId = leaderId;
        return this;
    }

    public long getMemberBId() {
        return memberBId;
    }

    public Team setMemberBId(long memberBId) {
        this.memberBId = memberBId;
        return this;
    }

    public long getMemberCId() {
        return memberCId;
    }

    public Team setMemberCId(long memberCId) {
        this.memberCId = memberCId;
        return this;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public Team setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public long getCreateBy() {
        return createBy;
    }

    public Team setCreateBy(long createBy) {
        this.createBy = createBy;
        return this;
    }
}
