/**
 * Created by spasoje on 30-Oct-18.
 */
import React, {Component} from 'react';
import {translate} from 'react-i18next';
import Input from './../UI/Input/Input'
import {Button,Form, Col} from 'react-bootstrap';

class Register extends Component {
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
            firstName: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                },
                value: '',
                label: 'firstNameLabel'
                // TODO NE menja se labela kad promenim jezik, treba da se pozove neki setState ili tako nesto. Jedno resenje je da gore stavim label: 'labelName'
            },
            lastName: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                },
                value: '',
                label: 'lastNameLabel'
            },
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

export default translate()(Register);