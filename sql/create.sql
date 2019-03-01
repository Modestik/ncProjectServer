--users
-- auto-generated definition
create table users
(
  username varchar(50)  not null
    constraint users_pk
      primary key,
  password varchar(100) not null,
  role     varchar(20)  not null
);

alter table users
  owner to postgres;

create unique index users_username_uindex
  on users (username);

