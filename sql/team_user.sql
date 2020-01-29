create table sacc_frontend.sys_user
(
    id          bigint auto_increment comment 'UUID'
        primary key,
    team_name   varchar(255) default ''       not null  comment '队伍名称',
    teamleader_id bigint(10) default ''       not null  comment '队长id',
    teammate_1    bigint(10) default ''       comment '队员1',
    teammate_2    bigint(10) default ''       comment '队员2',
    teammate_3    bigint(10) default ''       comment '队员3',
    teammate_4    bigint(10) default ''       comment '队员4',
    competition_id bigint(10) default ''      not null comment '比赛id',
    create_date timestamp    default CURRENT_TIMESTAMP not null,
    update_date timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    del_flag    tinyint      default 0                 not null comment '删除标记'
)