create table sacc_frontend.sys_permission
(
    id          bigint auto_increment comment 'UUID'
        primary key,
    permission  varchar(100) default ''                not null comment '权限',
    url         varchar(500) default ''                not null comment 'REST API URL地址',
    remarks     varchar(255) default ''                not null comment '备注',
    create_date timestamp    default CURRENT_TIMESTAMP not null,
    create_by   bigint                                 not null comment '创建人ID',
    update_date timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_by   bigint                                 not null comment '修改人ID',
    del_flag    tinyint      default 0                 not null comment '删除标识'
);

create index sys_permission_id_index
    on sacc_frontend.sys_permission (id);