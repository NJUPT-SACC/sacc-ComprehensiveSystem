create table sacc_frontend.competition
(
    id          bigint auto_increment
        primary key,
    name        varchar(20)  default ''                not null,
    remarks     varchar(255) default ''                not null,
    location    varchar(50)  default ''                not null,
    start_time  timestamp                              not null,
    end_time    timestamp                              not null,
    create_date timestamp    default CURRENT_TIMESTAMP not null,
    create_by   bigint                                 not null,
    update_date timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    update_by   bigint                                 not null,
    del_flag    tinyint      default 0                 not null
);