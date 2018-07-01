DROP TABLE IF EXISTS cars CASCADE;
DROP TABLE IF EXISTS images CASCADE;
DROP TABLE IF EXISTS transmissions;
DROP TABLE IF EXISTS engines;
DROP TABLE IF EXISTS bodies;

------------------------------- create db and tables --------------------------------
--create database cars;
CREATE TABLE images (
  id     INT NOT NULL PRIMARY KEY,
  name   VARCHAR(100),
  image  BYTEA,
  car_id INT
);

CREATE TABLE transmissions (
  id   INT NOT NULL PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE engines (
  id   INT NOT NULL PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE bodies (
  id   INT NOT NULL PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE cars (
  id              INT NOT NULL PRIMARY KEY,
  transmission_id INT NOT NULL,
  engine_id       INT NOT NULL,
  body_id         INT NOT NULL,
  name            VARCHAR(100),
  image_id        INT,
  FOREIGN KEY (transmission_id) REFERENCES transmissions (id),
  FOREIGN KEY (engine_id) REFERENCES engines (id),
  FOREIGN KEY (body_id) REFERENCES bodies (id),
  FOREIGN KEY (image_id) REFERENCES images (id)
);

ALTER TABLE images
  ADD FOREIGN KEY (car_id) REFERENCES cars (id);

-------------------------------- enter some data in tables --------------------------------
INSERT INTO transmissions (id, name)
VALUES
  (1, 'auto 1'),
  (2, 'manual 1'),
  (3, 'auto 2'),
  (4, 'auto 3'),
  (5, 'manual 2');

INSERT INTO engines (id, name)
VALUES
  (1, 'gas 1'),
  (2, 'diesel 1'),
  (3, 'gas 2'),
  (4, 'gas 3'),
  (5, 'diesel 2');

INSERT INTO bodies (id, name)
VALUES
  (1, 'sedan 1'),
  (2, 'hatchback 1'),
  (3, 'sedan 2'),
  (4, 'sedan 3'),
  (5, 'hatchback 2');

INSERT INTO cars (id, name, transmission_id, engine_id, body_id)
VALUES
  (1, 'mazda 3', 2, 4, 1),
  (2, 'ford focus', 4, 2, 2),
  (3, 'toyota corolla', 5, 1, 5);