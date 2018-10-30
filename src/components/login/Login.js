/**
 * Created by spasoje on 30-Oct-18.
 */
import React, {Component} from 'react';
import {translate} from 'react-i18next';
import Input from './../UI/Input/Input'
import {Button,Form, Col} from 'react-bootstrap';

class Login extends Component {

    state = {
        loginForm: {
            email: {
                elementType: 'input',
                elementConfig: {
                    type: 'email',
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

    render() {
        const formElementsArray = [];
        for (let key in this.state.loginForm) {
            formElementsArray.push({
                id: key,
                config: this.state.loginForm[key]
            })
        }
        const formInputs = formElementsArray.map(element => (
            <Input  key={element.id} elementType={element.config.elementType}
                    elementConfig={element.config.elementConfig} value={element.config.value} label={element.config.label}/>
        ))
        return(
            <div style={{width: '25%', margin:'auto'}}>
                <Form horizontal>
                    {formInputs}
                </Form>
                <Col smOffset={2}>
                    <Button>Log in</Button>
                </Col>
            </div>

        )
    }
}

export default translate()(Login);