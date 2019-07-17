import React, {Component }from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import Login from '../Login'

class App extends Component{

  render() {
    return (
        <div className={'container'}>
          <Login/>
        </div>
    );
  }
}

export default App;
