import axios from 'axios';

export const AUTH_API_URL = 'http://localhost:8080/library/basicauth/';
export const USERNAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';

class AuthenticationService {

    executeAuthenticationService(username: string, password: string) {
        return axios.get(`${AUTH_API_URL}`,
            {headers: {authorization: this.createBasicAuthToken(username, password)}})
    }

    createBasicAuthToken(username: string, password: string) {
        return 'Basic ' + window.btoa(username + ":" + password)
    }

    registerSuccessfulLogin(username: string, password: string) {
        sessionStorage.setItem(USERNAME_SESSION_ATTRIBUTE_NAME, username);
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    }

    setupAxiosInterceptors(token: any) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USERNAME_SESSION_ATTRIBUTE_NAME);
        if (user === null) {
            return false;
        }
        return true
    }

}

export default new AuthenticationService()