update books
set author_id = :author_id,
  genre_id    = :genre_id,
  updated     = now()
where id = :book_id
