ALTER TABLE `sys_user`
    MODIFY COLUMN `create_by` bigint(20) NULL COMMENT '创建者' AFTER `create_date`;
ALTER TABLE `sys_user`
    MODIFY COLUMN `update_by` bigint(20) NULL AFTER `update_date`;

alter table sys_user modify name varchar(20) default '' not null comment '姓名';

alter table sys_user
    add student_number varchar(20) default '' not null comment '学号' after password;

alter table sys_user
    add grade tinyint default 0 not null comment '年级' after pic_url;

alter table sys_user
    add institution varchar(50) default '' not null comment '学院' after grade;

alter table sys_user
    add major varchar(20) default '' not null comment '专业' after institution;

alter table sys_user
    add gender tinyint default 0 not null comment '1是男生，2是女生' after major;
