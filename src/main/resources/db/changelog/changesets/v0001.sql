create table if not exists "user" (
  id varchar(255) not null primary key,
  email varchar(60) not null,
  password_hash varchar(255) not null,
  salt varchar(255) not null
);

create table if not exists "employee" (
    id varchar(255) not null primary key,
    employee_id varchar(255) not null unique,
    name varchar(255) not null,
    age int
);

insert into employee (id, employee_id, name, age) values('1', '1', 'john doe', 24) on conflict do nothing;
insert into employee (id, employee_id, name, age) values('2', '2', 'jane santos', 21) on conflict do nothing;
insert into employee (id, employee_id, name, age) values('3', '3', 'carl rio', 23) on conflict do nothing;
insert into employee (id, employee_id, name, age) values('4', '4', 'elise highbow', 28) on conflict do nothing;
