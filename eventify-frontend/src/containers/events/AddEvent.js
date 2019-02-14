import React, {Component} from 'react';
import {Table, Form, Button} from 'react-bootstrap';
import Input from './../../components/UI/Input/Input';
import {translate} from 'react-i18next';
import axios from './../../axiosBase'

class AddEvent extends Component {
    state = {
        eventAddForm: {
            eventName: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Enter event name'
                },
                value: '',
                label: 'event.eventName'
            },
            eventPrice: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Enter price'
                },
                value: '',
                label: 'event.price'
            },
            eventType: {
                elementType: 'select',
                elementConfig: {
                    options: [
                        {value: 'concert', displayValue: 'Concert'},
                        {value: 'sport', displayValue: 'Sport'}
                    ]
                },
                value: '',
                label: 'event.type'
            }
            ,eventDate: {
                elementType: 'datePicker',//TODO
                value: '',
                label: 'event.type'
            },eventTime: {
                elementType: 'timePicker',//TODO
                value: '',
                label: 'event.type'
            }
        }
    };

    addEvent = (event) => {
        const eventData = {
            eventName: this.state.eventAddForm.eventName.value,
            eventType: this.state.eventAddForm.eventType.value,
            eventDate: this.state.eventAddForm.eventDate.value,
            eventTime: this.state.eventAddForm.eventTime.value,
        }

        axios.post( 'http://localhost:8762/event/addEvent',eventData )
            .then( response => {
                alert('Sve deluje faking ok')
            } )
            .catch( error => {
                console.log('greska')
                console.log(error)
                this.setState( { error: true } );
            } );
    };

    inputChangedHandler = (event, inputIdentifier) => {
        const updatedEventAddForm = {...this.state.eventAddForm};
        const updatedFormElement = {...updatedEventAddForm[inputIdentifier]};
        switch (inputIdentifier) {//TODO This solution is quite stupid but datepicker don't give me event, it just gives me value
            case 'eventTime':
                updatedFormElement.value = event;
                break;
            case 'eventDate':
                updatedFormElement.value = event;
                break;
            default:
                updatedFormElement.value = event.target.value;
        }

        updatedEventAddForm[inputIdentifier] = updatedFormElement;
        this.setState({
            eventAddForm: updatedEventAddForm
        });
    }


    render() {
        const formElementsArray = [];
        for (let key in this.state.eventAddForm) {
            formElementsArray.push({
                id: key,
                config: this.state.eventAddForm[key]
            })
        }
        const formInputs = formElementsArray.map(element => (
            <Input changed={event => {
                this.inputChangedHandler(event, element.id)
            }} key={element.id} elementType={element.config.elementType}
                   elementConfig={element.config.elementConfig} value={element.config.value} label={element.config.label}/>
        ));
        const {t} = this.props;
        return (
            <div style={{width:'70%',margin:'auto'}}>
                <Form horizontal>
                    {formInputs}
                </Form>
                <Button onClick={this.addEvent}>Button</Button>
            </div>

        )
    }
}
export default translate()(AddEvent);