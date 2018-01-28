-------------------------------- create db and tables --------------------------------
--create database tickets;

CREATE TABLE permissions (
  id    INT NOT NULL PRIMARY KEY,
  value VARCHAR(100)
);

CREATE TABLE roles (
  id        INT NOT NULL PRIMARY KEY,
  role_name VARCHAR(100)
);

CREATE TABLE role_sets (
  role_id       INT NOT NULL,
  permission_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES roles (id),
  FOREIGN KEY (permission_id) REFERENCES permissions (id),
  UNIQUE (role_id, permission_id)
);

CREATE TABLE states (
  id         INT NOT NULL PRIMARY KEY,
  state_name VARCHAR(100)
);

CREATE TABLE categories (
  id            INT NOT NULL PRIMARY KEY,
  category_name VARCHAR(100)
);

CREATE TABLE users (
  id        INT NOT NULL PRIMARY KEY,
  user_name VARCHAR(100),
  role_id   INT,
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE tickets (
  id          INT NOT NULL PRIMARY KEY,
  title       VARCHAR(150),
  author_id   INT REFERENCES users (id),
  assignee_id INT REFERENCES users (id),
  state_id    INT,
  category_id INT,
  FOREIGN KEY (state_id) REFERENCES states (id),
  FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE comments (
  id           INT NOT NULL PRIMARY KEY,
  ticket_id    INT,
  comment_text TEXT,
  FOREIGN KEY (ticket_id) REFERENCES tickets (id)
);

CREATE TABLE files (
  id        INT NOT NULL PRIMARY KEY,
  ticket_id INT,
  file_data TEXT,
  FOREIGN KEY (ticket_id) REFERENCES tickets (id)
);

-------------------------------- enter some data in tables --------------------------------
INSERT INTO roles (id, role_name)
VALUES (1, 'manager'),
  (2, 'topmanager');

INSERT INTO users (id, user_name, role_id)
VALUES (1, 'Mark', 1),
  (2, 'David', 1),
  (3, 'Tom', 2),
  (4, 'Carl', 2),
  (5, 'John', 2);

INSERT INTO states (id, state_name)
VALUES (1, 'open'),
  (2, 'close');

INSERT INTO categories (id, category_name)
VALUES (1, 'bug'),
  (2, 'feature');

INSERT INTO tickets (id, title, author_id, assignee_id, state_id, category_id)
VALUES (1, 'Bug in clients management module', 5, 3, 1, 1),
  (2, 'Add new field', 4, 2, 2, 2);

INSERT INTO comments (id, ticket_id, comment_text)
VALUES (1, 1, 'Please, fix it until tomorrow. Thanks.'),
  (2, 2, 'Done. Please check.');

INSERT INTO files (id, ticket_id, file_data)
VALUES (1, '1', 'Some file 1'),
  (2, '2', 'Some file 2');

INSERT INTO permissions (id, value)
VALUES (1, 'create_clients'),
  (2, 'read_clients'),
  (3, 'update_clients'),
  (4, 'delete_clients');

INSERT INTO role_sets (role_id, permission_id)
VALUES (1, 2),
  (2, 1),
  (2, 2),
  (2, 3),
  (2, 4);
