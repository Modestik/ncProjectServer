create table orders
(
  id          serial           not null
    constraint orders_pk
      primary key,
  name        varchar(50)      not null,
  start_long  double precision not null,
  start_width double precision not null,
  end_long    double precision not null,
  end_width   double precision not null,
  status      varchar(50)      not null
);

alter table orders
  owner to postgres;


--role
create table role
(
  id_role serial      not null
    constraint role_pk
      primary key,
  name    varchar(50) not null
);

alter table role
  owner to postgres;

INSERT INTO role VALUES (DEFAULT,'ADMIN');
INSERT INTO role VALUES (DEFAULT,'CUSTOMER');
INSERT INTO role VALUES (DEFAULT,'OPERATOR');
INSERT INTO role VALUES (DEFAULT,'DRIVER');

--users
create table users
(
  id_user    serial       not null
    constraint users_pk
      primary key,
  first_name varchar(50)  not null,
  last_name  varchar(50)  not null,
  email      varchar(100) not null,
  password   varchar(50)  not null,
  id_role    serial       not null
    constraint users_role_id_role_fk
      references role
);

alter table users
  owner to postgres;

INSERT INTO users VALUES (DEFAULT,'ADMIN','ADMIN','admin@gmail.com','123',1);

--
select u.email, 'ROLE_' ||  r.name
  from users u,
       role r
  where u.id_role = r.id_role ;--and u.username = ?;

select email, password , true as enabled from users;