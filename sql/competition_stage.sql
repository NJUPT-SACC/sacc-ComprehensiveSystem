create table sacc_frontend.competition_stage
(
    id          bigint auto_increment
        primary key,
    user_id     bigint                               not null,
    question_id bigint                               not null,
    result      varchar(6) default ''                not null,
    submit_time timestamp  default CURRENT_TIMESTAMP not null,
    del_flag    tinyint    default 0                 not null
);