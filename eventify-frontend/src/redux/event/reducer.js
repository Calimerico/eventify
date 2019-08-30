/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';
import {storeLoadingByAction, asMap, byId, removeById} from './../util';

const initialState = {
    events:{},
    eventsFilter:{

    },
    lastUsedFilter: {

    },
    loadings:null,
}

const reducer = (state = initialState, action) => {
    switch (action.type) {

        case types.GET_EVENTS_BY_FILTER_SUCCESS:
            return {...state, events:asMap(action.payload.data._embedded.resources), loadings:storeLoadingByAction(true, action)};
        case types.GET_EVENTS_BY_FILTER:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.GET_EVENTS_BY_FILTER_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.GET_EVENT_BY_ID_SUCCESS:
            return {...state, events:byId(action.payload.data,{...state.events}), loadings:storeLoadingByAction(true, action)};
        case types.GET_EVENT_BY_ID:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.GET_EVENT_BY_ID_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.DELETE_EVENT:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.DELETE_EVENT_SUCCESS:
            return {...state,loadings:storeLoadingByAction(false, action), events:removeById(action.payload,state.events)};
        case types.DELETE_EVENT_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.ADD_EVENT:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.ADD_EVENT_SUCCESS:
            return {...state,loadings:storeLoadingByAction(false, action), events:byId(action.payload,state.events)};
        case types.ADD_EVENT_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.UPDATE_EVENT:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.UPDATE_EVENT_SUCCESS:
            return {...state,loadings:storeLoadingByAction(false, action), events:byId(action.payload,state.events)};
        case types.UPDATE_EVENT_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.CHANGE_FILTER:
            return {...state, eventsFilter:action.payload};
        case types.UPDATE_LAST_USED_FILTER:
            return {...state, lastUsedFilter:action.payload};
        default:
            return state;
    }
};

export default reducer;