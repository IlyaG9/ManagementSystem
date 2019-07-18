export default class AuthService {

    constructor(serverUrl) {
        this.serverUrl = serverUrl || 'http://localhost:8083/manage';

        this.fetch = this.fetch.bind(this);
        this.login = this.login.bind(this);
        this.logout= this.logout.bind(this);
    }

    setToken(token) {
        localStorage.setItem('token', token)
    }

    getToken() {
        return localStorage.getItem('token')
    }

    login(username, password) {

        return fetch(`${this.serverUrl}/token`, {

            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: "username="+username+"&password="+password
        }).then(response=>response.text()).then(token => {

            if('no token found'==token){
                var error = new Error(token)
                throw error
            }
            this.setToken(token)
            return Promise.resolve(token);
        })
    }

    logout() {
        localStorage.removeItem('token');
    }

    loggedIn() {
        return !!this.getToken();
    }

    checkStatus(response) {
        if (response.status >= 200 && response.status < 300) {
            return response
        } else {
            localStorage.removeItem('token');
            var error = new Error(response.statusText)
            error.response = response
            throw error
        }
    }

    fetch(url, options) {

        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }

        if (this.loggedIn()) {
            headers['Authorization'] = this.getToken();
        }

        return fetch(url, {
            headers,
            options
        })
            .then(this.checkStatus)
            .then(response => response.json())
    }
}