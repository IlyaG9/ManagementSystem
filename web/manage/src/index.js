import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './components/App';
import Login from './components/Login';
import UserList from './components/UserList';
import { BrowserRouter, Route } from 'react-router-dom';



ReactDOM.render(
    <BrowserRouter>
        <div>
            <Route path='/' component={App} />
            <Route path='/login' component={Login} />
            <Route path='/users' component={UserList} />
        </div>
    </BrowserRouter>, document.getElementById('root'))


