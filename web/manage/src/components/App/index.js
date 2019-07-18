import React, {Component} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import Login from '../Login'
import AuthService from '../../service/AuthService';


class App extends Component {


    constructor(props) {
        super(props);
        this.Auth = new AuthService();
    }

    componentDidMount() {
        if (!this.Auth.loggedIn()) {
            this.props.history.replace('/login')
        } else {
        }
    }

    render() {
        const logoutBtn = this.Auth.loggedIn() ? <button onClick={this.handleLogout.bind(this)}>Logout</button>:'';
        return (
            <div className={'container'}>
                {logoutBtn}
            </div>
        );
    }

   handleLogout(){
        this.Auth.logout();
       this.props.history.replace('/login')
   }

//<Login/>
}

export default App;
