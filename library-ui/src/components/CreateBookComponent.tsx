import * as React from 'react';
import {ComponentState} from 'react';
import BookDataService from '../service/BookDataService';
import {IBook} from "./BookComponent";

export default class CreateBookComponent extends React.Component<{ history: any }, { name: any, desc: any, authorId: any, genreId: any }> {


    constructor(props: any) {
        super(props);

        this.state = {
            name: '',
            desc: '',
            authorId: '',
            genreId: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    private handleChange(event: any) {
        this.setState({[event.target.name]: event.target.value} as ComponentState);
    }

    private handleSubmit(event: any) {
        alert('Создание книги с параметрами: \n' +
            'Наименование: ' + this.state.name + '\n' +
            'Описание: ' + this.state.desc + '\n' +
            'ID автора: ' + this.state.authorId + '\n' +
            'ID жанра: ' + this.state.genreId);
        event.preventDefault();
        this.props.history.push(`/library/`);
    }

    render() {

        return <div>
            <h3>Create new Book</h3>

            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label>Name</label>
                    <input className="form-control" type="text" name="name" value={this.state.name}
                           onChange={this.handleChange}/>
                    <small id="name" className="form-text text-muted">Обязательно для ввода</small>
                </div>
                <div className="form-group">
                    <label>Description</label>
                    <input className="form-control" type="text" name="desc" value={this.state.desc}
                           onChange={this.handleChange}/>
                    <small id="desc" className="form-text text-muted">Обязательно для ввода</small>
                </div>
                <div className="form-group">
                    <label>Author Identifier</label>
                    <input className="form-control" type="text" name="authorId" value={this.state.authorId}
                           onChange={this.handleChange}/>
                    <small id="author" className="form-text text-muted">Необходимо указать идентификатор автора</small>
                </div>
                <div className="form-group">
                    <label>Genre Identifier</label>
                    <input className="form-control" type="text" name="genreId" value={this.state.genreId}
                           onChange={this.handleChange}/>
                    <small id="genre" className="form-text text-muted">Необходимо указать идентификатор жанра</small>
                </div>
                <div>
                    <button className="btn btn-primary" type="submit"
                            onClick={() => this.onSubmitButtonClick({
                                name: this.state.name === '' ? null : this.state.name,
                                desc: this.state.desc === '' ? null : this.state.desc,
                                authorId: this.state.authorId === '' ? null : this.state.authorId,
                                genreId: this.state.genreId === '' ? null : this.state.genreId
                            })}>Save
                    </button>
                </div>
            </form>

            <p/>
            <div className="footer"><a href={`/library/`}>Back to main page</a></div>
        </div>
    }

    private onSubmitButtonClick(book: IBook) {
        console.log(book);
        BookDataService.createBook(book);
    }


}