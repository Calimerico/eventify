/**
 * Created by spasoje on 19-Oct-18.
 */
import React, {Component} from 'react';
import {Table, Form, Button} from 'react-bootstrap';
import {translate} from 'react-i18next';
import axios from './../../axiosBase'
class Events extends Component {
    state = {

        events: [
            {
            }
        ]
    }

    componentDidMount () {


        axios.get( 'http://localhost:8762/event/events' )
            .then( response => {
                this.setState( { events: response.data } );
            } )
            .catch( error => {
                console.log('greska')
                console.log(error)
                this.setState( { error: true } );
            } );
    }



    render() {

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
                        this.state.events.map(event => (
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
            </div>

        )
    }
}

export default translate()(Events);