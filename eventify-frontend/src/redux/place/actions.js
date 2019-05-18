/**
 * Created by spasoje on 05-Dec-18.
 */
import axios from './../../axiosBase';
import types from './types';


export const loadPlaces = (city) => {
    return (dispatch) => {
        dispatch({type:types.GET_ALL_PLACES});
        axios.defaults.headers.common['Content-Type'] = 'application/json';
        axios.get('http://localhost:8762/event/places?city=' + city)
            .then( response => {
                dispatch({type:types.GET_ALL_PLACES_SUCCESS,payload:response.data});
            } )
            .catch( error => {
                dispatch({type:types.GET_ALL_PLACES_FAIL,error:error});
            } );
    }
};

export const addPlace = (request) => {
    return (dispatch) => {
        dispatch({type:types.ADD_PLACE});
        axios.defaults.headers.common['Content-Type'] = 'application/json';
        axios.post('http://localhost:8762/event/places/', request)
            .then( response => {
                dispatch({type:types.ADD_PLACE_SUCCESS,payload:response.data});
            } )
            .catch( error => {
                dispatch({type:types.ADD_PLACE_FAIL,error:error});
            } );
    }
};


const actions = {
    loadPlaces:loadPlaces,
    addPlace:addPlace
}

export default actions;