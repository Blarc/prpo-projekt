create table uporabnik
(
    id        serial       not null
        constraint uporabnik_pk
            primary key,
    name      varchar(255) not null,
    last_name varchar(255) not null,
    username  varchar(255) not null
);

alter table userA
    owner to postgres;

create unique index uporabnik_id_uindex
    on uporabnik (id);

create unique index uporabnik_username_uindex
    on uporabnik (username);

