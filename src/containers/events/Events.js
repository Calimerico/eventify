/**
 * Created by spasoje on 19-Oct-18.
 */
import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Table} from 'react-bootstrap';
import Input from './../../components/UI/Input/Input';
import {translate} from 'react-i18next';
class Events extends Component {
    state = {
        eventAddForm: {
            eventName: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Enter event name'
                },
                value: ''
            },
            eventPrice: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Enter price'
                },
                value: ''
            },
            eventType: {
                elementType: 'select',
                elementConfig: {
                    options: [
                        {value: 'concert', displayValue: 'Concert'},
                        {value: 'sport', displayValue: 'Sport'}
                    ]
                },
                value: ''
            }
        }
    }

    inputChangedHandler(event, inputIdentifier) {
        const updatedEventAddForm = {...this.state.eventAddForm};
        const updatedFormElement = {...updatedEventAddForm[inputIdentifier]};
        updatedFormElement.value = event.target.value;
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
        const {t} = this.props;
        return (
            <div style={{width:'70%',margin:'auto'}}>
                <Table responsive>
                    <thead>
                    <tr>
                        <th>{t('event.eventName')}</th>
                        <th>{t('event.price')}</th>
                        <th>{t('event.date')}</th>
                        <th>{t('event.time')}</th>
                        <th>{t('event.place')}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.props.events.map(event => (
                            <tr>
                                <td>{event.eventName}</td>
                                <td>{event.eventPrice + ' RSD'}</td>
                                <td>{event.date}</td>
                                <td>{event.eventTime}</td>
                                <td>{event.place}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </Table>
                <form>
                    {formElementsArray.map(element => (
                        <Input changed={event => {
                            this.inputChangedHandler(event, element.id)
                        }} key={element.id} elementType={element.config.elementType}
                               elementConfig={element.config.elementConfig} value={element.config.value}/>
                    ))}
                </form>
                <button onClick={this.props.addEvent}>Button</button>
            </div>

        )
    }
}

const mapStateToProps = state => {
    return {
        events: state.eventsReducer.events
    }
};

const mapDispatchToProps = dispatch => {
    return {
        addEvent: (event) => dispatch({type: 'ADD_EVENT'})
    }
};

export default translate()(connect(mapStateToProps, mapDispatchToProps)(Events));