import * as React from 'react';
import BookDataService from "../service/BookDataService";

interface IState {
    books: any[];
    message: any;
}

export default class ListBookComponent extends React.Component<{ history: any }, IState> {

    constructor(props: any) {
        super(props);
        this.state = {
            books: [],
            message: null
        };
        this.refreshBooks = this.refreshBooks.bind(this);
        this.onDeleteBookButtonClick = this.onDeleteBookButtonClick.bind(this);
        this.onUpdateBookButtonClick = this.onUpdateBookButtonClick.bind(this);
        this.onAddBookButtonClick = this.onAddBookButtonClick.bind(this);


    }

    componentDidMount() {
        this.refreshBooks();
    }

    public refreshBooks() {
        BookDataService.retrieveAllBooks()
            .then(
                response => {
                    console.log(response.data);
                    this.setState({books: response.data})
                }
            )
    }

    private onDeleteBookButtonClick(id: string) {
        BookDataService.deleteBook(id)
            .then(
                response => {
                    this.setState({message: `Delete of book ${id} Successful`});
                    this.refreshBooks()
                }
            )
            .catch(() => {
                this.setState({message: "you are not allowed to delete book"})
            })
    }

    private onUpdateBookButtonClick(id: string) {
        this.props.history.push(`/library/book/${id}`)
    }


    private onAddBookButtonClick() {
        this.props.history.push(`/library/book/new`)
    }

    render() {
        const books = this.state.books;

        return (

            <div className="container">
                <h3>Book collection</h3>
                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Author</th>
                            <th>Genre</th>
                            <th/>
                            <th/>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            books.map(book =>
                                <tr key={book.id}>
                                    <td><a href={`/library/book/${book.id}`}>{book.id}</a></td>
                                    <td>{book.name}</td>
                                    <td>{book.desc}</td>
                                    <td>{book.authorId}</td>
                                    <td>{book.genreId}</td>
                                    <td>
                                        <button className="btn btn-warning"
                                                onClick={() => this.onUpdateBookButtonClick(book.id)}>Update
                                        </button>
                                    </td>
                                    <td>
                                        <button className="btn btn-none"
                                                onClick={() => this.onDeleteBookButtonClick(book.id)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            )
                        }
                        </tbody>
                    </table>

                    <div className="row">
                        <button className="btn btn-success" onClick={() => this.onAddBookButtonClick()}>Add Book</button>
                    </div>

                </div>
            </div>
        )
    }
}