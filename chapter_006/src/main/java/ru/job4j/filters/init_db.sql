-- create database

CREATE DATABASE shop;

--create tables

CREATE TABLE product_types (
  id   INT PRIMARY KEY NOT NULL,
  name VARCHAR(100)
);

CREATE TABLE products (
  id           INT PRIMARY KEY NOT NULL,
  name         VARCHAR(100),
  type_id      INT,
  expired_date TIMESTAMP WITHOUT TIME ZONE,
  price        DECIMAL,
  quantity     INT,
  FOREIGN KEY (type_id) REFERENCES product_types (id)
);

-- insert some data

INSERT INTO product_types (id, name) VALUES
  (1, 'cheese'),
  (2, 'milk'),
  (3, 'ice-cream'),
  (4, 'meat'),
  (5, 'bread');

INSERT INTO products (id, name, type_id, expired_date, price, quantity) VALUES
  (1, 'Great bread', 5, '2018-02-05 10:00:00', 1.5, 12),
  (2, 'Buns', 5, '2018-02-01 10:00:00', 0.5, 20),
  (3, 'Village pleasure', 2, '2018-05-01 10:00:00', 2.5, 4),
  (4, 'Cheese sun', 1, '2018-02-10 10:00:00', 4.5, 9),
  (5, 'Lucky Cheese', 1, '2018-03-07 10:00:00', 6.0, 7),
  (6, 'Winter cow', 1, '2018-02-17 10:00:00', 3.5, 9),
  (7, 'Just another ice-cream', 3, '2018-02-28 10:00:00', 1.2, 20),
  (8, 'Cold ice-cream', 3, '2018-05-10 10:00:00', 1.5, 14),
  (9, 'Local sweet ice-cream', 3, '2018-02-03 20:00:00', 0.6, 8),
  (10, 'Beef', 4, '2018-02-14 16:00:00', 18, 4),
  (11, 'Pork', 4, '2018-03-01 08:00:00', 13, 10),
  (12, 'Chicken', 4, '2018-02-18 10:00:00', 7, 11);
