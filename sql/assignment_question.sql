create table sacc_frontend.assignment_question
  (
      id            bigint auto_increment comment 'UUID'
          primary key,
      assignment_id bigint                              not null comment '作业ID',
      question_id   bigint                              null comment '选择题ID',
      remarks       varchar(255)                        not null comment '备注',
      create_date   timestamp default CURRENT_TIMESTAMP not null,
      create_by     bigint                              not null comment '创建人ID',
      update_date   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
      update_by     bigint                              not null comment '修改人ID',
      del_flag      tinyint   default 0                 not null
  );

ALTER TABLE `sacc_frontend`.`assignment_question`
ADD COLUMN `question_type` int(11) NOT NULL COMME '题目类型（1选择题，2编程题）' AFTER `assignment_id`;