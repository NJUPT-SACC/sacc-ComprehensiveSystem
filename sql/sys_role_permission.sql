create table sacc_frontend.sys_role_permission
(
    id            int auto_increment comment 'UUID'
        primary key,
    role_id       int           null comment '角色ID',
    permission_id int           null comment 'Permission_ID',
    remarks       varchar(255)  null comment '备注',
    create_date   datetime      null comment '创建时间',
    create_by     char(32)      null comment '创建人',
    update_date   datetime      null comment '修改时间',
    update_id     char(32)      null comment '修改人ID',
    del_flag      int default 0 not null comment '删除标识'
);