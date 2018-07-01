-- show all cars with spare parts
SELECT
  c.name,
  b.name AS body,
  e.name AS engine,
  t.name AS transmission
FROM cars AS c
  INNER JOIN bodies b ON c.body_id = b.id
  INNER JOIN engines e ON c.engine_id = e.id
  INNER JOIN transmissions t ON c.transmission_id = t.id;


-- show unused spare parts
SELECT
  b.name AS unused
FROM cars AS c
  RIGHT OUTER JOIN bodies b ON c.body_id = b.id
WHERE c.name IS NULL

UNION ALL

SELECT
  e.name
FROM cars AS c
  RIGHT OUTER JOIN engines e ON c.engine_id = e.id
WHERE c.name IS NULL

UNION ALL

SELECT
  t.name
FROM cars AS c
  RIGHT OUTER JOIN transmissions t ON c.transmission_id = t.id
WHERE c.name IS NULL
