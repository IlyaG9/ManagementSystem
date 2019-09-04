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
        this.Auth.fetch(this.Auth.serverUrl + "/user/list").then(json=>{
            console.log("users:"+json);
            this.setState({
                    users: json
                }
            );
        });
    }

    componentWillMount() {



    }


    render() {
        const ul=this.state.users.map(u=><div className="col-md-3 text-center" key={u.id}>{u.name}</div>);
        return (

            <div className="margin-from-left">
                {ul}
            </div>
        );
    }
}