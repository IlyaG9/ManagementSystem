import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class Index extends Component {
    onValueChange(evt){
        this.props.onValueChange(evt.target.value);
    }



    render() {
        return (
            <div className="form-group">
                <label className={"sr-only"} htmlFor={this.props.id}>{this.props.name}</label>
                <input id={this.props.id} type={this.props.type} className={"form-control"}
                       placeholder={this.props.placeholder} onChange={this.onValueChange.bind(this)}/>
            </div>
        );
    }

}

export default Index;