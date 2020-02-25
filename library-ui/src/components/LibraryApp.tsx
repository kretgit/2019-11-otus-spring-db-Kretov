import * as React from 'react';
import ListBookComponent from "./ListBookComponent";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import BookComponent from "./BookComponent";
import CreateBookComponent from "./CreateBookComponent";

export default class LibraryApp extends React.Component {
    render() {
        return (
            <Router>
                <div>
                    <h1>Library Application</h1>
                    <Switch>
                        <Route path = "/" exact component = {ListBookComponent} />
                        <Route path = "/library" exact component = {ListBookComponent} />
                        <Route path = "/library/book" exact component = {ListBookComponent} />
                        <Route path = "/library/book/new" exact component = {CreateBookComponent} />
                        <Route path = "/library/book/:id" exact component = {BookComponent} />
                    </Switch>
                </div>
            </Router>
        )
    }
}