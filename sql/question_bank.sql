create table sacc_frontend.question_bank
(
    id             bigint auto_increment comment 'UUID'
        primary key,
    title          varchar(20)  default ''                not null comment '题目简称',
    description    varchar(255) default ''                not null comment '题干描述',
    choice_a       varchar(255) de-fault ''                not null comment 'A选项',
    choice_b       varchar(255) default ''                not null comment 'B选项',
    choice_c       varchar(255) default ''                not null comment 'C选项',
    choice_d       varchar(255) default ''                not null comment 'D选项',
    choice_e       varchar(255) default ''                not null comment 'E选项',
    choice_f       varchar(255) default ''                not null comment 'F选项',
    correct_answer char(6)      default ''                not null comment '正确选项',
    remarks        varchar(255) default ''                not null comment '备注',
    create_date    timestamp    default CURRENT_TIMESTAMP not null,
    create_by      bigint                                 not null comment '创建人ID',
    update_date    timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_by      bigint                                 not null comment '修改人ID',
    del_flag       tinyint      default 0                 not null
);
ALTER TABLE `sacc_frontend`.`question_bank`
    ADD COLUMN `difficulty` int(11) NOT NULL COMMENT '难度（1简单，2中等，3困难）' AFTER `description`;