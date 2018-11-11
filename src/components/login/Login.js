/**
 * Created by spasoje on 30-Oct-18.
 */
import React, {Component} from 'react';
import {translate} from 'react-i18next';
import Input from './../UI/Input/Input'
import {Button,Form, Col} from 'react-bootstrap';
import axios from './../../axiosBase'

class Login extends Component {

    state = {
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
        localStorage.setItem("role","USER")
        //TODO This is the solution. Enable when you solve cors problem
        // axios.defaults.headers.common['Content-Type'] = 'application/json';
        // axios.post( 'http://localhost:8762/auth', {username:this.state.email,password:this.state.password} )
        //     .then( response => {
        //         debugger;
        //         this.setState( { events: response.data } );
        //     } )
        //     .catch( error => {
        //         console.log('greska')
        //         console.log(error)
        //         this.setState( { error: true } );
        //     } );
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
                        <Button type="submit">Log in</Button>
                    </Col>
                </Form>

            </div>

        )
    }
}

export default translate()(Login);