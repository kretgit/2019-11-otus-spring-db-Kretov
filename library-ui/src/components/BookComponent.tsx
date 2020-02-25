import * as React from 'react';
import {ComponentState} from 'react';
import BookDataService from '../service/BookDataService';


export interface IBook {
    id?: string,
    name: string,
    desc: string,
    authorId?: string,
    genreId?: string
}

export interface IEditBook {
    id: any,
    authorId?: any,
    genreId?: any
}

export default class BookComponent extends React.Component<{ match: any, history: any }, IBook & IEditBook> {

    constructor(props: any) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            name: '',
            desc: '',
            authorId: '',
            genreId: '',
        };

        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        if (this.state.id == null) {
            return
        }

        BookDataService.retrieveBook(this.state.id)
            .then(response => this.setState({
                id: response.data.id,
                name: response.data.name,
                desc: response.data.desc,
                authorId: response.data.authorId,
                genreId: response.data.genreId,
            }))
    }

    private handleChange(event: any) {
        this.setState({[event.target.name]: event.target.value} as ComponentState);
    }

    render() {

        let {id, name, desc, authorId, genreId} = this.state;

        return <div>
            <h3>Book <b>{id}</b></h3>

            <form>
                <div className="form-group">
                    <label>Name</label>
                    <input className="form-control" type="text" name="name" placeholder={name} readOnly={true}/>
                    <small id="name" className="form-text text-muted">Поле недоступно для редактирования</small>
                </div>
                <div className="form-group">
                    <label>Description</label>
                    <input className="form-control" type="text" name="desc" placeholder={desc} readOnly={true}/>
                    <small id="desc" className="form-text text-muted">Поле недоступно для редактирования</small>
                </div>
                <div className="form-group">
                    <label>Author Identifier</label>
                    <input className="form-control" type="text" name="authorId" placeholder={authorId}
                           readOnly={false} value={this.state.authorId} onChange={this.handleChange}/>
                    <small id="name" className="form-text text-muted">Необходимо указать идентификатор автора, например
                        "A100". Обнулять неудопустимо.
                    </small>
                </div>
                <div className="form-group">
                    <label>Genre Identifier</label>
                    <input className="form-control" type="text" name="genreId" placeholder={genreId}
                           readOnly={false} value={this.state.genreId} onChange={this.handleChange}/>
                    <small id="name" className="form-text text-muted">Необходимо указать идентификатор жанра, например
                        "G100". Обнулять недопустимо.
                    </small>
                </div>
                <div>
                    <button className="btn btn-primary" type="submit"
                            onClick={() => this.onSubmitButtonClick({
                                id: this.state.id,
                                authorId: this.state.authorId,// === '' ? null : this.state.authorId,
                                genreId: this.state.genreId// === '' ? null : this.state.genreId,
                            })}>Save
                    </button>
                </div>
            </form>

            <p/>
            <div className="footer"><a href={`/library/`}>Back to main page</a></div>
        </div>
    }

    private onSubmitButtonClick(bookForUpdate: IEditBook) {
        console.log(bookForUpdate);
        BookDataService.updateBook(bookForUpdate);
    }

}