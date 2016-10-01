DELETE FROM meal;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meal (user_id, date_time, description, calories) VALUES
  (100000, now(), 'Завтрак', 350),
  (100000, now(), 'Обед', 700),
  (100000, now(), 'Ужин', 650),
  (100001, now(), 'Завтрак', 500),
  (100001, now(), 'Обед', 700),
  (100001, now(), 'Ужин', 800);