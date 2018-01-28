-- 1
SELECT
  p.name,
  p.expired_date,
  p.price,
  p.quantity,
  ptypes.name AS type
FROM products AS p
  INNER JOIN product_types ptypes ON p.type_id = ptypes.id
WHERE ptypes.name = 'cheese';

-- 2
SELECT
  p.name,
  p.expired_date,
  p.price,
  p.quantity,
  ptypes.name AS type
FROM products AS p
  INNER JOIN product_types ptypes ON p.type_id = ptypes.id
WHERE p.name LIKE '%ice-cream%';

-- 3
SELECT
  p.name,
  p.expired_date,
  p.price,
  p.quantity,
  ptypes.name AS type
FROM products AS p
  INNER JOIN product_types ptypes ON p.type_id = ptypes.id
WHERE p.expired_date BETWEEN '2018-02-01' AND '2018-02-28';

-- 4
SELECT
  p.name,
  p.expired_date,
  p.price,
  p.quantity,
  ptypes.name AS type
FROM products AS p
  INNER JOIN product_types ptypes ON p.type_id = ptypes.id
ORDER BY price DESC
LIMIT 1;

-- 5
SELECT
  sum(p.quantity) as quantity,
  ptypes.name AS type
FROM products AS p
  INNER JOIN product_types ptypes ON p.type_id = ptypes.id
WHERE ptypes.name = 'cheese'
GROUP BY ptypes.name;

-- 6
SELECT
  p.name,
  p.expired_date,
  p.price,
  p.quantity,
  ptypes.name AS type
FROM products AS p
  INNER JOIN product_types ptypes ON p.type_id = ptypes.id
WHERE ptypes.name = 'cheese' or ptypes.name = 'milk';

-- 7
SELECT * FROM (
                SELECT
                  sum(p.quantity) AS count,
                  ptypes.name     AS type
                FROM products AS p
                  INNER JOIN product_types ptypes ON p.type_id = ptypes.id
                GROUP BY ptypes.name
              ) as types_count
WHERE count < 30;

--8
SELECT
  p.name,
  ptypes.name AS type
FROM products AS p
  INNER JOIN product_types ptypes ON p.type_id = ptypes.id;