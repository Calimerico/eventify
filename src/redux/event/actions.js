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
};//TODO This method is for deletion

export const getEventsByFilter = (eventsFilter) => {

    return (dispatch) => {
        dispatch({type:types.GET_EVENTS_BY_FILTER});
        dispatch({type:types.CHANGE_FILTER_SUCCESS,payload:eventsFilter});
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



const actions = {
    getEventsByFilter:getEventsByFilter,
    changeFilter:changeFilter
}

export default actions;