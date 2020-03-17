import * as React from 'react';
import {Redirect, Route} from 'react-router-dom';
import AuthenticationService from '../service/AuthenticationService';
import {RouteComponentProps} from '@reach/router';

export interface IRouteComponentProps extends RouteComponentProps {
    path: string,
    exact: boolean,
    component: any
}

class AuthenticatedRoute extends React.Component<IRouteComponentProps> {

    render() {
        if (AuthenticationService.isUserLoggedIn()) {
            console.log("send auth headers: " + this.props);
            return <Route {...this.props} />
        } else {
            return <Redirect to='/library/login'/>
        }
    }

}

export default AuthenticatedRoute