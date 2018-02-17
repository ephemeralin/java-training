CREATE TABLE IF NOT EXISTS items (
  id   VARCHAR(20) PRIMARY KEY NOT NULL,
  name VARCHAR(200),
  descr VARCHAR(1000),
  created BIGINT
);
CREATE TABLE IF NOT EXISTS comments (
  comment_text VARCHAR(1000),
  item_id      VARCHAR(20),
  FOREIGN KEY (item_id) REFERENCES items (id)
);