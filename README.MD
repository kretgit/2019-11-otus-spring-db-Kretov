## Приложение для работы с книгами

Требуется локальная MongoDB.
````
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/otus_data_mongo_reactive
````

#### Поддерживаемые REST вызовы:

получить всех имеющихся авторов:
````
GET/POST http://localhost:8080/author
````

добавить нового автора:
````
POST http://localhost:8080/author/add
{
    "name":"new author name"
}
````

получить все имеющиеся жанры:
````
GET/POST http://localhost:8080/genre
````

добавить новый жанр:
````
POST http://localhost:8080/genre/add
{
    "name":"new genre name",
    "description":"new genre description"
}
````

получить все имеющиеся книги:
````
GET/POST http://localhost:8080/book
````

удалить книгу (требуется ввести ID книги):
````
POST http://localhost:8080/book/delete
{
    "id":"5e577f5b60575c68dc56549d"
}

````

добавить новую книгу:
````
POST http://localhost:8080/book/add
{
    "name": "new book",
    "description": "description of new book",
    "authorId": "5e577f9960575c68dc5654a0",
    "genreId": "5e577fb360575c68dc5654a3"
}
 
````

изменить имеющуюся книгу:
````
POST http://localhost:8080/book/add
{
    "id":"5e577fb360575c68dc5654a4"
    "name": "updated book",
    "description": "updated description of book",
    "authorId": "5e577f9960575c68dc5654a0",
    "genreId": "5e577fb360575c68dc5654a3"
}
````