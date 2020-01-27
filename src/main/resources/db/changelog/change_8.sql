-- добавляем авторов
insert into authors (id, full_name) VALUES
  ((select nextval('authors_id_seq')), 'Пушкин Александр Сергеевич');

insert into authors (id, full_name) VALUES
  ((select nextval('authors_id_seq')), 'Дарья Донцова');

insert into authors (id, full_name) VALUES
  ((select nextval('authors_id_seq')), 'Эрнест Хемингуэй');

-- добавлеям жанры
insert into genres (id, name, description) VALUES
  ((select nextval('genres_id_seq')), 'Детектив', 'документальное, детектив, карманный детектив, расследования, разоблачения');

insert into genres (id, name, description) VALUES
  ((select nextval('genres_id_seq')), 'Стихи', 'детские стихи, белые стихи, черные стихи, смешные стихи');

insert into genres (id, name, description) VALUES
  ((select nextval('genres_id_seq')), 'Проза', 'нескладные стихи, повести, былины');

insert into genres (id, name, description) VALUES
  ((select nextval('genres_id_seq')), 'Сказки', 'сказка ложь, да в ней намек...');

insert into genres (id, name, description) VALUES
  ((select nextval('genres_id_seq')), 'Зарубежная литература', 'забугорная, запрещенная, ненашенская, как у них...');