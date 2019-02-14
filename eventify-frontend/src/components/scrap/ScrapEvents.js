/**
 * Created by spasoje on 01-Dec-18.
 */
import React, {Component} from 'react';
import axios from './../../axiosBase';
import {Form, Col} from 'react-bootstrap';
import Button from '@material-ui/core/Button';
import { connect } from 'react-redux';

class ScrapEvents extends Component {

    constructor(props) {
        super(props);
        this.scrapEvents = this.scrapEvents.bind(this);
    }

    render() {
        return (
            <div style={{width: '25%', margin: 'auto'}}>
                <Form onSubmit={this.scrapEvents} horizontal>
                    <Col smOffset={2}>
                        <Col smOffset={2}>
                            <Button type="submit">Scrap events</Button>
                        </Col>
                    </Col>
                </Form>

            </div>
        )

    }

    scrapEvents(event) {
        event.preventDefault();//TODO Prevent refreshing page(and refreshing state)
        axios.post( 'http://localhost:8762/scraper/scrap')
            .then( response => {
                console.log('sve je ok');
            } )
            .catch( error => {
                console.log('greska')
                console.log(error)
                this.setState( { error: true } );
            } );
    }
}
const mapDispatchToProps = dispatch => {
    return {
        scrapEvents: () => dispatch({type: 'SCRAP_EVENTS'})
    }
};

export default connect(null,mapDispatchToProps)(ScrapEvents);