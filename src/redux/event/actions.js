/**
 * Created by spasoje on 05-Dec-18.
 */
import axios from './../../axiosBase';
import types from './types';

export const getEvents = () => {
    return (dispatch) => {
        dispatch({type:types.GET_EVENTS});
        axios.get( 'http://localhost:8762/event/events')
            .then( response => {
                dispatch({type:types.GET_EVENTS_SUCCESS,payload:response});
            } )
            .catch( error => {
                dispatch({type:types.GET_EVENTS_FAIL,error:error});
            } );
    }
};

export const getEventsByFilter = (eventsFilter) => {
    return (dispatch) => {
        dispatch({type:types.GET_EVENTS_BY_FILTER});
        axios.get( 'http://localhost:8762/event/events?' + eventsFilter)
            .then( response => {
                dispatch({type:types.GET_EVENTS_BY_FILTER_SUCCESS,payload:response});
            } )
            .catch( error => {
                dispatch({type:types.GET_EVENTS_BY_FILTER_FAIL,error:error});
            } );
    }
};

export const changeFilter = (newFilter) => {
    return (dispatch) => {
        dispatch({type:types.FILTER_CHANGED,eventsFilter:newFilter});
    }
};

const actions = {
    getEventsByFilter:getEventsByFilter,
    getEvents:getEvents,
    changeFilter:changeFilter
}

export default actions;