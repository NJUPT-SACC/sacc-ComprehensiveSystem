create table sacc_frontend.sys_menu
(
    id          int auto_increment comment 'UUID'
        primary key,
    sort        int          null comment '排序',
    is_show     char         null comment '是否在菜单中显示',
    parent_id   int          null comment '父级编号',
    icon        varchar(64)  null comment '图标',
    name        varchar(100) null comment '名称',
    url         varchar(128) null comment '链接',
    remarks     varchar(255) null comment '备注',
    create_date datetime     null comment '生成时间',
    create_by   char(32)     null comment '创建人ID',
    update_date datetime     null comment '修改时间',
    update_by   char(32)     null comment '修改人ID',
    del_flag    int          null comment '删除标识'
);

create index sys_menu_id_index
    on sacc_frontend.sys_menu (id);