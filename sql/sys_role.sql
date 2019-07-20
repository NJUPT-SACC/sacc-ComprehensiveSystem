create table sacc_frontend.sys_role
(
    id          int auto_increment comment 'UUID',
    name        varchar(100)  null comment '角色名称',
    remarks     varchar(255)  null comment '备注',
    create_date datetime      null comment '生成时间',
    create_by   char(32)      not null comment '创建人ID',
    update_date datetime      null comment '修改时间',
    update_by   char(32)      null comment '修改人ID',
    del_flag    int default 0 not null comment '删除标识',
    constraint sys_role_id_uindex
        unique (id)
);

alter table sacc_frontend.sys_role
    add primary key (id);