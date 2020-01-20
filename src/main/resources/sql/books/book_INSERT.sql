insert into books(id, name, "desc", genre_id, author_id, created, updated)
VALUES (:id, :name, :desc, :genre_id, :author_id, now(), now());