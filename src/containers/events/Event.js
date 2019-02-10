/**
 * Created by spasoje on 02-Feb-19.
 */

import React from 'react';
import { connect } from 'react-redux';
import eventSelectors from './../../redux/event/selector'
import eventActions from './../../redux/event/actions'
import Button from '@material-ui/core/Button';

class Event extends React.Component {

    constructor(props) {
        super(props);
        const {eventExist,eventLoaded, loadEvent} = this.props;
        if (eventExist && !eventLoaded){
            loadEvent(this.props.match.params.id);
        }
        this.onUpdateSubmit = this.onUpdateSubmit.bind(this);
    }

    onUpdateSubmit() {
        this.props.updateEvent(this.props.match.params.id, {eventName:"pera",eventType:"sport",description:"desc"})
    }

    render() {
        if (this.props.event !== null) {
            return <div>
                {"Proba " + this.props.match.params.id + this.props.event.eventName}
                <Button onClick={this.onUpdateSubmit}>Update</Button>
            </div>
        }
        return "Loading";

    }
}

const mapStateToProps = (state,props) => {
    return{
        event:eventSelectors.getEventById(state, props),
        eventExist:eventSelectors.doesEventExist(state,props),
        eventLoaded:eventSelectors.isEventLoaded(state,props)
    }
};

const mapDispatchToProps = dispatch => {
    return {
        loadEvent: (id) => dispatch(eventActions.getEventById(id)),
        updateEvent: (id, request) => dispatch(eventActions.updateEvent(id, request))
    }
};


export default connect(mapStateToProps, mapDispatchToProps)(Event);