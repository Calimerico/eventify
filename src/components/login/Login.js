/**
 * Created by spasoje on 30-Oct-18.
 */
import React, {Component} from 'react';
import {translate} from 'react-i18next';
import Input from './../UI/Input/Input'
import {Form, Col} from 'react-bootstrap';
import axios from './../../axiosBase';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            loginForm: {
                email: {
                    elementType: 'input',
                    elementConfig: {
                        type: 'text',
                    },
                    value: '',
                    label: 'emailLabel'
                },
                password: {
                    elementType: 'input',
                    elementConfig: {
                        type: 'password',
                    },
                    value: '',
                    label: 'passwordLabel'
                },
                rememberMe: {
                    elementType: 'checkbox',
                    value: '',
                    label: 'rememberMeLabel'
                }
            }
        }
        this.inputChangedHandler = this.inputChangedHandler.bind(this);
        this.login = this.login.bind(this);
    }

    inputChangedHandler = (event, inputIdentifier) =>{
        const updatedLoginForm = {...this.state.loginForm};
        const updatedFormElement = {...updatedLoginForm[inputIdentifier]};
        updatedFormElement.value = event.target.value;
        updatedLoginForm[inputIdentifier] = updatedFormElement;
        this.setState({
            loginForm: updatedLoginForm
        });
    };

    login = (event) => {
        event.preventDefault();//TODO Prevent refreshing page(and refreshing state)
        axios.defaults.headers.common['Content-Type'] = 'application/json';
        axios.post( 'http://localhost:8762/auth', {username:this.state.loginForm.email.value,password:this.state.loginForm.password.value} )
            .then( response => {
                let authorizationTOken = response.headers.authorization;//TODO
                localStorage.setItem("token",authorizationTOken);
                window.location.replace("/");
            } )
            .catch( error => {
                console.log('greska')
                console.log(error)
                this.setState( { error: true } );
            } );
    };

    render() {
        const { classes } = this.props;
        const formElementsArray = [];
        for (let key in this.state.loginForm) {
            formElementsArray.push({
                id: key,
                config: this.state.loginForm[key]
            })
        }
        const formInputs = formElementsArray.map(element => (
            <Input  key={element.id}
                    elementType={element.config.elementType}
                    elementConfig={element.config.elementConfig}
                    value={element.config.value}
                    label={element.config.label}
                    changed={event => this.inputChangedHandler(event, element.id)}
            />
        ))
        return(
            <div style={{width: '25%', margin:'auto'}}>
                <Form onSubmit={this.login} horizontal>
                    {formInputs}
                    <Col smOffset={2}>
                        <Button variant="contained" className={classes.button}>
                            <span className={classes.buttonLabel}>Login</span>
                        </Button>
                        <Col smOffset={2}>
                            <Button type="submit">Log in2</Button>
                        </Col>
                    </Col>
                </Form>

            </div>

        )
    }
}
const styles = theme => ({
    button: {
        margin: theme.spacing.unit,
    },
    input: {
        display: 'none',
    },
    buttonLabel: {
        fontSize: "13px"
    }
});

Login.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(translate()(Login));