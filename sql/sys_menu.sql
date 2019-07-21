create table sacc_frontend.sys_menu
(
    id          bigint auto_increment comment 'UUID'
        primary key,
    sort        bigint                                 not null comment '排序',
    is_show     char         default ''                not null comment '是否在菜单中显示',
    parent_id   bigint                                 not null comment '父级编号',
    icon        varchar(64)  default ''                not null comment '图标',
    name        varchar(100) default ''                not null comment '名称',
    url         varchar(128) default ''                not null comment '链接',
    remarks     varchar(255) default ''                not null comment '备注',
    create_date timestamp    default CURRENT_TIMESTAMP not null,
    create_by   bigint                                 not null comment '创建人ID',
    update_date timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_by   bigint                                 not null comment '修改人ID',
    del_flag    tinyint      default 0                 not null comment '删除标识'
);

create index sys_menu_id_index
    on sacc_frontend.sys_menu (id);