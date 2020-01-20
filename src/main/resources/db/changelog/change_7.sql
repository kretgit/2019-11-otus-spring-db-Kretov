-- добавляем авторов
insert into authors (id, full_name, created, updated) VALUES
  ((select 'A'|| nextval('authors_id_seq')), 'Пушкин Александр Сергеевич', now(), now());

insert into authors (id, full_name, created, updated) VALUES
  ((select 'A'|| nextval('authors_id_seq')), 'Дарья Донцова', now(), now());

insert into authors (id, full_name, created, updated) VALUES
  ((select 'A'|| nextval('authors_id_seq')), 'Эрнест Хемингуэй', now(), now());

-- добавлеям жанры
insert into genres (id, name, "desc", created, updated) VALUES
  ((select 'G'|| nextval('genres_id_seq')), 'Детектив', 'документальное, детектив, карманный детектив, расследования, разоблачения', now(), now());

insert into genres (id, name, "desc", created, updated) VALUES
  ((select 'G'|| nextval('genres_id_seq')), 'Стихи', 'детские стихи, белые стихи, черные стихи, смешные стихи', now(), now());

insert into genres (id, name, "desc", created, updated) VALUES
  ((select 'G'|| nextval('genres_id_seq')), 'Проза', 'нескладные стихи, повести, былины', now(), now());

insert into genres (id, name, "desc", created, updated) VALUES
  ((select 'G'|| nextval('genres_id_seq')), 'Сказки', 'сказка ложь, да в ней намек...', now(), now());

insert into genres (id, name, "desc", created, updated) VALUES
  ((select 'G'|| nextval('genres_id_seq')), 'Зарубежная литература', 'забугорная, запрещенная, ненашенская, как у них...', now(), now());