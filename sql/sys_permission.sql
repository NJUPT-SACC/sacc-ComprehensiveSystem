create table sacc_frontend.sys_permission
(
    id          int auto_increment comment 'UUID'
        primary key,
    permission  varchar(100)  null comment '权限',
    url         varchar(500)  null comment 'REST API URL地址',
    remarks     varchar(255)  null comment '备注',
    create_date datetime      null comment '生成时间',
    create_by   char(32)      null comment '创建人ID',
    update_date datetime      null comment '修改时间',
    update_by   char(32)      null comment '修改人ID',
    del_flag    int default 0 not null comment '删除标识'
);

create index sys_permission_id_index
    on sacc_frontend.sys_permission (id);