/**
 * Created by spasoje on 02-Feb-19.
 */

import React from 'react';
import { connect } from 'react-redux';
import eventSelectors from './../../redux/event/selector'
import eventActions from './../../redux/event/actions'
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';

const styles = theme => ({
    progress: {
        margin: theme.spacing.unit * 2,
    },
});

class Event extends React.Component {

    constructor(props) {
        super(props);
        this.onUpdateSubmit = this.onUpdateSubmit.bind(this);
    }

    onUpdateSubmit() {
        const {updateEvent, match:{params:{id}}} = this.props;
        updateEvent(id, {eventName:"pera",eventType:"sport",description:"desc"})
    }

    componentDidMount() {
        const {eventLoaded, loadEvent, match:{params:{id}}} = this.props;
        if (!eventLoaded){
            loadEvent(id);
        }
    }

    isPageLoaded() {
        const {event} = this.props;
        return event;
    }

    render() {
        const { classes } = this.props;
        if (this.isPageLoaded()) {
            return <div>
                {"Proba " + this.props.match.params.id + this.props.event.eventName}
                <Button onClick={this.onUpdateSubmit}>Update</Button>
            </div>
        } else {
            return <CircularProgress size={140} className={classes.progress} />
        }
    }
}

const mapStateToProps = (state,props) => {
    return{
        event:eventSelectors.getEventById(state, props),
        eventLoaded:eventSelectors.isEventLoaded(state,props)
    }
};

const mapDispatchToProps = dispatch => {
    return {
        loadEvent: (id) => dispatch(eventActions.getEventById(id)),
        updateEvent: (id, request) => dispatch(eventActions.updateEvent(id, request))
    }
};


export default withStyles(styles)(connect(mapStateToProps, mapDispatchToProps)(Event));