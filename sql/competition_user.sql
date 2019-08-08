create table sacc_frontend.competition_user
(
    id             bigint auto_increment
        primary key,
    user_id        bigint                                 not null,
    competition_id bigint                                 not null,
    remarks        varchar(255) default ''                not null,
    create_by      bigint                                 not null,
    create_date    timestamp    default CURRENT_TIMESTAMP not null,
    update_by      bigint                                 not null,
    update_date    timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    del_flag       tinyint      default 0                 not null
);