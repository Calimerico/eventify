/**
 * Created by spasoje on 05-Dec-18.
 */
import axios from './../../axiosBase';
import types from './types';

export const changeFilter = (eventsFilter) => {

    return (dispatch) => {
        dispatch({type:types.CHANGE_FILTER,eventsFilter:eventsFilter});
        //javascript object to request params https://stackoverflow.com/questions/6566456/how-to-serialize-an-object-into-a-list-of-url-query-parameters
        let eventsFilterRequestParams = "";
        if (eventsFilter != null) {
            for (const key in eventsFilter) {
                if (eventsFilterRequestParams !== "") {
                    eventsFilterRequestParams += "&";
                }
                eventsFilterRequestParams += key + "=" + encodeURIComponent(eventsFilter[key]);
            }
        }
        axios.get( 'http://localhost:8762/event/events? ' + eventsFilterRequestParams)
            .then( response => {
                dispatch({type:types.GET_EVENTS_BY_FILTER_SUCCESS,payload:response});
            } )
            .catch( error => {
                dispatch({type:types.GET_EVENTS_BY_FILTER_FAIL,error:error});
            } );
    }
};//TODO This method is for deletion, I don't use it anywhere

export const getEventsByFilter = (eventsFilter) => {

    return (dispatch) => {
        dispatch({type:types.GET_EVENTS_BY_FILTER});
        dispatch({type:types.CHANGE_FILTER_SUCCESS,payload:eventsFilter});
        //javascript object to request params https://stackoverflow.com/questions/6566456/how-to-serialize-an-object-into-a-list-of-url-query-parameters
        let eventsFilterRequestParams = "";
        if (eventsFilter != null) {
            for (const key in eventsFilter) {
                if (eventsFilterRequestParams !== "" && eventsFilter[key]) {
                    eventsFilterRequestParams += "&";
                }
                if (eventsFilter[key] != null) {
                    eventsFilterRequestParams += key + "=" + encodeURIComponent(eventsFilter[key]);
                }
            }
        }
        axios.defaults.headers.common['Content-Type'] = 'application/json';
        const url = 'http://localhost:8762/event/events?' + eventsFilterRequestParams;
        axios.get(url)
            .then( response => {
                dispatch({type:types.GET_EVENTS_BY_FILTER_SUCCESS,payload:response});
            } )
            .catch( error => {
                dispatch({type:types.GET_EVENTS_BY_FILTER_FAIL,error:error});
            } );
    }
};

export const getEventById = (id) => {
    return (dispatch) => {
        dispatch({type:types.GET_EVENT_BY_ID});
        axios.get('http://localhost:8762/event/events/' + id)
            .then( response => {
                dispatch({type:types.GET_EVENT_BY_ID_SUCCESS,payload:response});
            } )
            .catch( error => {
                dispatch({type:types.GET_EVENT_BY_ID_FAIL,error:error});
            } );
    }
};

export const updateEvent = (id, request) => {
    return (dispatch) => {
        dispatch({type:types.UPDATE_EVENT});
        axios.put('http://localhost:8762/event/events/' + id, request)
            .then( response => {
                dispatch({type:types.UPDATE_EVENT_SUCCESS,payload:response});
            } )
            .catch( error => {
                dispatch({type:types.UPDATE_EVENT_FAIL,error:error});
            } );
    }
};

export const deleteEvent = (id) => {
    return (dispatch) => {
        dispatch({type:types.DELETE_EVENT});
        axios.delete('http://localhost:8762/event/events/' + id)
            .then( response => {
                dispatch({type:types.DELETE_EVENT_SUCCESS,payload:id});
            } )
            .catch( error => {
                dispatch({type:types.DELETE_EVENT_FAIL,error:error});
            } );
    }
};


const actions = {
    getEventsByFilter:getEventsByFilter,
    changeFilter:changeFilter,
    getEventById:getEventById,
    updateEvent:updateEvent,
    deleteEvent:deleteEvent
}

export default actions;