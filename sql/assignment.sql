create table sacc_frontend.assignment
(
    id          bigint auto_increment comment 'ID'
        primary key,
    name        varchar(20)  default ''                not null comment '作业名称',
    start_time  timestamp    default CURRENT_TIMESTAMP not null,
    end_time    timestamp    default CURRENT_TIMESTAMP not null,
    remarks     varchar(255) default ''                not null comment '备注',
    create_date timestamp    default CURRENT_TIMESTAMP not null,
    create_by   bigint       default 0                 not null comment '创建人',
    update_date timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_by   bigint                                 null comment '修改人ID',
    del_flag    tinyint      default 0                 not null
);