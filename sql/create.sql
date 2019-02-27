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


--users
create table users
(
  id_user    serial       not null
    constraint users_pk
      primary key,
  first_name varchar(50)  ,
  last_name  varchar(50)  ,
  username   varchar(100) not null,
  password   varchar(50)  not null,
  role       varchar(50)  not null
);


INSERT INTO users VALUES (DEFAULT,'ADMIN','ADMIN','admin','admin',1);

--
select u.email, 'ROLE_' ||  r.name
  from users u,
       role r
  where u.id_role = r.id_role ;--and u.username = ?;

select email, password , true as enabled from users;