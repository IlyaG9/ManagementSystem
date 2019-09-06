import React, {Component} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import Sidebar from '../Sidebar'
import UserList from '../UserList'
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
        const sideBar = this.Auth.loggedIn() ?<Sidebar/>:'';
        const userList = this.Auth.loggedIn() ?<UserList/>:'';
        return (
            <div>
                {sideBar}
            </div>
        );
    }


}

export default App;
