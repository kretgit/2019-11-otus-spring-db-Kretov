import * as React from 'react';
import {ComponentState} from 'react';
import AuthenticationService from '../service/AuthenticationService';

export interface ILogin {
    username: string,
    password: string,
    hasLoginFailed: boolean,
    showSuccessMessage: boolean
}

export default class LoginComponent extends React.Component<{ history: any }, ILogin> {

    constructor(props: any) {
        super(props);

        this.state = {
            username: 'admin',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.loginClicked = this.loginClicked.bind(this);
    }


    private handleChange(event: any) {
        this.setState({[event.target.name]: event.target.value} as ComponentState);
    }


    private loginClicked() {

        const credentials = (this.state as ILogin);

        /*
        пример принудительной передачи аутентификацинных данных
        if (credentials.username === 'admin' && credentials.password === 'password') {
            //AuthenticationService.registerSuccessfulLogin(credentials.username, credentials.password);
            this.props.history.push(`/library/`)
            //this.setState({showSuccessMessage: true});
            //this.setState({hasLoginFailed: false})
        }
        else {
            this.setState({showSuccessMessage: false});
            this.setState({hasLoginFailed: true})
        }
        */

        AuthenticationService
            .executeAuthenticationService(credentials.username, credentials.password)
            .then(() => {
                AuthenticationService.registerSuccessfulLogin(credentials.username, credentials.password);
                this.props.history.push(`/library`)
            }).catch(() => {
            this.setState({showSuccessMessage: false});
            this.setState({hasLoginFailed: true})
        })
    }

    render() {

        const credentials = (this.state as ILogin);

        return (
            <div>
                <h1>Login</h1>
                <div className="container">
                    {credentials.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                    {credentials.showSuccessMessage && <div className="alert alert-success">Login Successful</div>}
                    <br/>
                    <div>
                        Username: <input type="text" name="username" value={credentials.username}
                                         onChange={this.handleChange}/>
                    </div>
                    <br/>
                    <div>
                        Password: <input type="password" name="password" value={credentials.password}
                                         onChange={this.handleChange}/>
                    </div>
                    <br/>
                    <div>
                        <button className="btn btn-success" onClick={this.loginClicked}>Login</button>
                    </div>
                </div>
            </div>
        )
    }

}