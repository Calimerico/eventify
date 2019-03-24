/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';

const initialState = {
    events:{},
    eventsFilter:{
        priceFrom:null
    },
    loadings:null,
}

const storeLoadingByAction = (loading, action) => {
    let calls = action.type;
    if (action.type.endsWith("SUCCESS")) {
        calls = action.type.substring(0, action.type.length - 8);
    }
    if (action.type.endsWith("FAIL")) {
        calls = action.type.substring(0, action.type.length - 5);
    }
    return {[calls]:loading};

};

const byId = (resource,prevState) => {
    return {...prevState,[resource.id]:resource};
}

const removeById = (id,prevState) => {
    return {...prevState};
}

const asMap = (resources, prevState) => {
    return resources.reduce((accumulator, currentValue) => {return {...accumulator, [currentValue.id]:currentValue}}, prevState);
}

const reducer = (state = initialState, action) => {
    switch (action.type) {

        case types.GET_EVENTS_BY_FILTER_SUCCESS:
            return {...state, events:asMap(action.payload.data._embedded.eventResourceList,{...state.events}), loadings:storeLoadingByAction(true, action)};
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
        case types.CHANGE_FILTER_SUCCESS:
            const eventsFilter = action.eventsFilter;
            return {...state,eventsFilter:eventsFilter};
        case types.DELETE_EVENT:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.DELETE_EVENT_SUCCESS:
            return {...state,loadings:storeLoadingByAction(false, action), events:removeById(action.payload,...state.events)};
        case types.DELETE_EVENT_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        case types.UPDATE_EVENT:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.UPDATE_EVENT_SUCCESS:
            return {...state,loadings:storeLoadingByAction(false, action), events:byId(action.payload,...state.events)};
        case types.UPDATE_EVENT_FAIL:
            return {...state, loadings:storeLoadingByAction(false, action)};
        default:
            return state;
    }
};

export default reducer;