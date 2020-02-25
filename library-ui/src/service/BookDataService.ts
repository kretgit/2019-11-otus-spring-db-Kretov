import axios from 'axios';
import {IBook, IEditBook} from "../components/BookComponent";

export const BOOK_API_BASE_URL = 'http://localhost:8080/library/book';

class BookDataService {

    retrieveAllBooks() {
        return axios.get(`${BOOK_API_BASE_URL}`);
    }

    retrieveBook(id: string) {
        return axios.get(`${BOOK_API_BASE_URL}?id=${id}`);
    }

    deleteBook(id: string) {
        return axios.delete(`${BOOK_API_BASE_URL}/delete?id=${id}`);
    }

    createBook(book: IBook) {
        console.log("created");
        return axios.post(`${BOOK_API_BASE_URL}/add`, book);
    }

    updateBook(bookForUpdate: IEditBook) {
        console.log("updated");
        return axios.post(`${BOOK_API_BASE_URL}/update`, bookForUpdate);
    }

}

export default new BookDataService()