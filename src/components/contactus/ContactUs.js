import React, {Component} from 'react';

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

class ContactUs extends Component {
    constructor() {
        super();
        this.state = {id: null};
    }
    handleChange = (event) => {
        this.setState({id: event.target.value});
    }
    handleSubmit = (event) => {
        //Make a network call somewhere
        event.preventDefault();
    }
    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <TextField floatingLabelText="ID Number" onChange={this.handleChange} />
                <Button label="Submit" type="submit" />
            </form>
        )
    }
}

export default ContactUs;