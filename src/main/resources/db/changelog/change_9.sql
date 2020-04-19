/* добавляем пользвователей с ролями и открытами паролями
демонстрация использования массивов для удобства хранения ролей в одной таблице
 */
insert into users (id, username, password, enabled, roles, created, updated) VALUES
  ((select 'U'|| nextval('users_id_seq')), 'user', 'user', true, '{ROLE_USER}', now(), now());

insert into users (id, username, password, enabled, roles, created, updated) VALUES
  ((select 'U'|| nextval('users_id_seq')), 'admin', 'admin', true, array_cat(ARRAY['ROLE_USER'],ARRAY['ROLE_ADMIN']), now(), now());