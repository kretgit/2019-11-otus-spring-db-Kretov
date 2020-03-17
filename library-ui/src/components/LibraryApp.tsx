import * as React from 'react';
import ListBookComponent from "./ListBookComponent";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import BookComponent from "./BookComponent";
import CreateBookComponent from "./CreateBookComponent";
import LoginComponent from "./LoginComponent";
import AuthenticatedRoute from "./AuthenticatedRoute";


export default class LibraryApp extends React.Component {

    render() {
        return (
            <Router>
                <div>
                    <h1>Library Application</h1>
                    <Switch>
                        <Route path="/" exact component={LoginComponent}/>
                        <Route path="/login" exact component={LoginComponent}/>
                        <Route path="/library/login" exact component={LoginComponent}/>
                        <AuthenticatedRoute path="/library" exact component={ListBookComponent}/>
                        <AuthenticatedRoute path="/library/book" exact component={ListBookComponent}/>
                        <AuthenticatedRoute path="/library/book/new" exact component={CreateBookComponent}/>
                        <AuthenticatedRoute path="/library/book/:id" exact component={BookComponent}/>
                    </Switch>
                </div>
            </Router>
        )
    }
}