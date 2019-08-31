create table sacc_frontend.sys_user
(
    id          bigint auto_increment comment 'UUID'
        primary key,
    remarks     varchar(255) default ''                not null comment '备注',
    login_name  varchar(16)  default ''                not null comment '登录名',
    name        varchar(100) default ''                not null comment '用户名称',
    password    varchar(100) default ''                not null comment '密码',
    email       varchar(100) default ''                not null comment '邮箱',
    tel         varchar(100) default ''                not null comment '电话',
    pic_url     varchar(100) default ''                not null comment '头像地址',
    create_date timestamp    default CURRENT_TIMESTAMP not null,
    update_date timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_by   bigint       default -1                not null,
    del_flag    tinyint      default 0                 not null comment '删除标记'
)
    comment '用户';

create index sys_user_id_index
    on sacc_frontend.sys_user (id);

ALTER TABLE `sacc_frontend`.`sys_user`
    MODIFY COLUMN `create_by` bigint(20) NULL COMMENT '创建者' AFTER `create_date`;
ALTER TABLE `sacc_frontend`.`sys_user`
    MODIFY COLUMN `update_by` bigint(20) NULL AFTER `update_date`;
