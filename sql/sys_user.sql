create table sacc_frontend.sys_user
(
    id          int auto_increment comment 'UUID'
        primary key,
    remarks     varchar(255) default '' not null comment '备注',
    login_name  varchar(16)             null comment '登录名',
    name        varchar(100) default '' not null comment '用户名称',
    password    varchar(100) default '' not null comment '密码',
    email       varchar(100) default '' not null comment '邮箱',
    tel         varchar(100) default '' not null comment '电话',
    pic_url     varchar(100) default '' not null comment '头像地址',
    create_date datetime                null comment '生成时间',
    create_by   char(32)     default '' not null comment '创建者',
    update_date datetime                null comment '修改时间',
    update_by   char(32)     default '' null,
    del_flag    int          default 0  not null comment '删除标记'
)
    comment '用户';

create index sys_user_id_index
    on sacc_frontend.sys_user (id);