import React, {Component} from 'react';
import AuthService from '../../service/AuthService';
import './style.css'

export default class UserList extends Component {

    constructor(props) {
        super(props);
        this.Auth = new AuthService();
        this.state = {
            users: []

        }
   
    }

    componentWillMount() {

     this.Auth.fetch(this.Auth.serverUrl + "/user/list").then(json=>{
     
            this.setState({
                    users:this.state.users.concat(json)
                }
            );
        });

    }


    render() {
        const rows=[];
        for (let index = 0; index < this.state.users.length; index++) {
            const user = this.state.users[index];
            rows.push(<div key={user.userName} className="col-md-3 text-center" >{user.userName}</div>);
            
        }
        return (

            <div key="userList" className="margin-from-left">
                {rows}
            </div>
        );
    }
}