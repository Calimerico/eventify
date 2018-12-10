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
import authActions from './../../redux/auth/actions'
import { connect } from 'react-redux';


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
        this.onLogin = this.onLogin.bind(this);
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

    onLogin = (event) => {
        event.preventDefault();//TODO Prevent refreshing page(and refreshing state)
        this.props.login(this.state.loginForm.email.value,this.state.loginForm.password.value);
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
                <Form onSubmit={this.onLogin} horizontal>
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

const mapStateToProps = state => {
    return {

    }
};

const mapDispatchToProps = dispatch => {
    return {
        login: (username,password) => dispatch(authActions.login(username,password))
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(withStyles(styles)(translate()(Login)));