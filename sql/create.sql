--users
-- auto-generated definition
drop table if exists users;
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

--cars
drop table if exists cars;
create table cars
(
  number varchar(10) not null
    constraint cars_pk
      primary key,
  model  varchar(50) not null,
  color  varchar(50) not null
);

--drivers
drop table if exists drivers;
create table drivers
(
  username varchar(50) not null
    constraint drivers_pk
      primary key,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  phone_number varchar(12) not null,
  car_number varchar(10)
    constraint drivers_cars_number_fk
      references cars,
  driver_real_point varchar(50)
);

--customers
drop table if exists customers;
create table customers
(
  username varchar(50)
    constraint customers_pk
      primary key,
  first_name varchar(50),
  last_name varchar(50),
  phone_number varchar(12)
);

--orders
drop table if exists orders;
create table orders
(
  id_order    serial           not null
    constraint orders_pk
      primary key,
  point_from  varchar(50)      not null,
  point_to    varchar(50)      not null,
  cost        double precision not null,
  weight      varchar(50) not null,
  description varchar(200),
  start_time  timestamp            not null,
  end_time    timestamp ,
  status      varchar(50),
  driver      varchar(50)
    constraint orders_drivers_username_fk
      references drivers,
  customer    varchar(50)      not null
    constraint orders_customers_username_fk
      references customers
);

--operators
drop table if exists operators;
create table operators
(
  username varchar(50) not null
    constraint operators_pk
      primary key,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  phone_number varchar(12) not null
);

--sessions
create table sessions
(
  id serial  not null
    constraint session_id_pkey
      primary key,
  username varchar(50) not null
    constraint login_fk
      references users
      on update cascade on delete cascade,
  time_of_begin timestamp not null,
  time_recent_activity timestamp not null
);


