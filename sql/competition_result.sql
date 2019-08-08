create table sacc_frontend.competition_result
(
    id             bigint auto_increment
        primary key,
    user_id        bigint                              not null,
    competition_id bigint                              not null,
    grade          int                                 not null,
    submit_time    timestamp default CURRENT_TIMESTAMP not null comment '最终提交时间',
    del_flag       tinyint   default 0                 not null
)
    comment '最终成绩表';