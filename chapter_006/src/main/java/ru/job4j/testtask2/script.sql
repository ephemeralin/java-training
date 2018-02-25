-- create tables
CREATE TABLE company
(
  id   INTEGER NOT NULL PRIMARY KEY,
  name CHARACTER VARYING
);

CREATE TABLE person
(
  id         INTEGER NOT NULL PRIMARY KEY,
  name       CHARACTER VARYING,
  company_id INTEGER,
  FOREIGN KEY (company_id) REFERENCES company (id)
);

-- insert data
INSERT INTO company (id, name)
VALUES
  (1, 'microsoft'),
  (2, 'google'),
  (3, 'twitter'),
  (4, 'samsung'),
  (5, 'apple');

INSERT INTO person (id, name, company_id)
VALUES
  (1, 'petr', 5),
  (2, 'slava', 2),
  (3, 'sasha', 1),
  (4, 'natasha', 2),
  (5, 'roma', 5),
  (6, 'pasha', 4),
  (7, 'vika', 3);

-- select query 1
-- Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
SELECT *
FROM person AS p
  INNER JOIN company AS c ON p.company_id = c.id
WHERE p.company_id <> 5;

-- select query 2
-- Select the name of the company with the maximum number of persons + number of persons in this company
SELECT
  name,
  count
FROM
  (SELECT
     count(p.id) AS count,
     c.name      AS name
   FROM person AS p
     INNER JOIN company c ON p.company_id = c.id
   GROUP BY c.id, c.name) AS rates
ORDER BY count DESC
LIMIT 1;
