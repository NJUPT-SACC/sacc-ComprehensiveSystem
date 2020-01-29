ALTER TABLE `sacc_frontend`.sys_user_team
    ADD COLUMN `competition_id` bigint(20)  default 0   not null comment '队伍id';
ALTER TABLE `sacc_frontend`.sys_user_team
    ADD COLUMN `team_name` varchar(255) default 0   not null comment '队伍id';