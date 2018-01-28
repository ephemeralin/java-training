-------------------------------- create db and tables --------------------------------
--create database tickets;

create table permissions (
  id int not null primary key,
  value varchar(100)
);

create table roles (
  id int not null primary key,
  role_name varchar(100)
);

create table role_sets (
  role_id int not null,
  permission_id int not null,
  foreign key(role_id) references roles(id),
  foreign key(permission_id) references permissions(id),
  unique (role_id, permission_id)
);

create table states (
  id int not null primary key,
  state_name varchar(100)
);

create table categories (
  id int not null primary key,
  category_name varchar(100)
);

create table users (
  id int not null primary key,
  user_name varchar(100),
  role_id int,
  foreign key(role_id) references roles(id)
);

create table tickets (
  id int not null primary key,
  title varchar(150),
  author_id int references users(id),
  assignee_id int references users(id),
  state_id int,
  category_id int,
  foreign key (state_id) references states(id),
  foreign key (category_id) references categories(id)
);

create table comments (
  id int not null primary key,
  ticket_id int,
  comment_text text,
foreign key (ticket_id) references tickets(id)
);

create table files (
id int not null primary key,
ticket_id int,
file_data text,
foreign key (ticket_id) references tickets(id)
);

-------------------------------- enter some data in tables --------------------------------
insert into roles (id, role_name)
values 	(1, 'manager'),
  (2, 'topmanager');

insert into users (id, user_name, role_id)
values 	(1, 'Mark', 1),
(2, 'David', 1),
(3, 'Tom', 2),
(4, 'Carl', 2),
(5, 'John', 2);

insert into states (id, state_name)
values 	(1, 'open'),
(2, 'close');

insert into categories (id, category_name)
values 	(1, 'bug'),
(2, 'feature');

insert into tickets (id, title, author_id, assignee_id, state_id, category_id)
values 	(1, 'Bug in clients management module', 5, 3, 1, 1),
(2, 'Add new field', 4, 2, 2, 2);

insert into comments (id, ticket_id, comment_text)
values 	(1, 1, 'Please, fix it until tomorrow. Thanks.'),
(2, 2, 'Done. Please check.');

insert into files (id, ticket_id, file_data)
values 	(1, '1', 'Some file 1'),
(2, '2', 'Some file 2');

insert into permissions (id, value)
values 	(1, 'create_clients'),
  (2, 'read_clients'),
(3, 'update_clients'),
(4, 'delete_clients');

insert into role_sets (role_id, permission_id)
values 	(1, 2),
  (2, 1),
  (2, 2),
  (2, 3),
  (2, 4);
