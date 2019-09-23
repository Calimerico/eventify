/**
 * Created by spasoje on 05-Dec-18.
 */
import axios from './../../axiosBase';
import types from './types';


export const loadPlaces = (city) => {
    return (dispatch) => {
        dispatch({type:types.GET_ALL_PLACES});
        axios.get('/place/places?city=' + city)
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
        axios.post('/place/places/', request)
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