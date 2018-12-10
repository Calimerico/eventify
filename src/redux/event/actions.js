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
                dispatch({type:'GET_EVENTS_SUCCESS',payload:response});
            } )
            .catch( error => {
                dispatch({type:'GET_EVENTS_FAIL',error:error});
            } );
    }
};

const actions = {
    getEvents:getEvents
}

export default actions;