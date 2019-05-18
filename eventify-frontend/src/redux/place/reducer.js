/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';
import {storeLoadingByAction, asMap, byId, removeById} from './../util';

const initialState = {
    places:[],
    loadings:null,
}

const reducer = (state = initialState, action) => {
    switch (action.type) {

        case types.GET_ALL_PLACES_SUCCESS:
            return {...state, places:action.payload, loadings:storeLoadingByAction(true, action)};
        case types.GET_ALL_PLACES:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.GET_ALL_PLACES_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.ADD_PLACE:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.ADD_PLACE_SUCCESS:
            return {...state,loadings:storeLoadingByAction(false, action), places:byId(action.payload,state.places)};
        case types.ADD_PLACE_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        default:
            return state;
    }
};

export default reducer;