create table sacc_frontend.assignment_stage
(
    id              bigint auto_increment comment 'UUID'
        primary key,
    assignment_id   bigint                                 not null comment '作业ID',
    user_id         bigint                                 not null comment '用户ID',
    question_id     bigint                                 not null comment '选择题ID',
    question_result char(6)      default ''                not null comment '选择题选项',
    remarks         varchar(255) default ''                not null comment '备注',
    create_date     timestamp    default CURRENT_TIMESTAMP not null,
    create_by       bigint                                 not null comment '创建人ID',
    update_date     timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_by       bigint                                 not null comment '修改人ID',
    save_time       timestamp    default CURRENT_TIMESTAMP not null,
    del_flag        tinyint      default 0                 not null
);