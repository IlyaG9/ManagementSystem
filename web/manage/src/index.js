import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './components/App';
import Login from './components/Login';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Route } from 'react-router-dom';



ReactDOM.render(
    <BrowserRouter>
        <div>
            <Route path='/' component={App} />
            <Route path='/login' component={Login} />
        </div>
    </BrowserRouter>, document.getElementById('root'))


