import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Input from '../Input';
import './style.css'
import AuthService from '../../service/AuthService';

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
        this.Auth = new AuthService();
        this.onLoginChange = this.onLoginChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
    }

    render() {
        return (
            <div className="container form-margin-top">
                <div className="row">
                    <div className="col-md-3"></div>
                    <div className="col-md-offset-4 col-md-6">
                        <form className="form-horizontal">
                            <Input id="username" type="text" placeholder="Логин" name="Логин" val={this.state.username}
                                   onValueChange={this.onLoginChange}/>
                            <Input id="password" type="password" placeholder="Пароль" name="Пароль"
                                   val={this.state.password}
                                   onValueChange={this.onPasswordChange}/>
                            <button type="submit" className="btn btn-default" onClick={this.enter.bind(this)}>Войти
                            </button>
                        </form>
                    </div>
                    <div className="col-md-3"></div>
                </div>
            </div>
        );
    }

    onLoginChange(value) {
        this.setState({username: value});
    }

    onPasswordChange(value) {
        this.setState({password: value});
    }

    enter = (e) => {
        e.preventDefault();
        this.Auth.login(this.state.username,this.state.password)
            .then(res =>{
                this.props.history.replace('/');
            })
            .catch(err =>{
                alert(err);
            })
        console.log("login:" + this.state.username + "/password:" + this.state.password);

    }

}

export default Login;
