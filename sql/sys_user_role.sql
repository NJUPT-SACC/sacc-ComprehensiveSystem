create table sacc_frontend.sys_user_role
(
    id          int auto_increment comment 'UUID'
        primary key,
    role_id     int           null comment '角色编号',
    user_id     int           null comment '用户编号',
    remarks     varchar(255)  null comment '备注',
    create_date datetime      null comment '生成时间',
    create_by   char(32)      null comment '创建人ID',
    update_date datetime      null comment '修改时间',
    update_by   char(32)      null comment '修改人ID',
    del_flag    int default 0 not null comment '删除标记'
);