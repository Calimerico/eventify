/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';

const initialState = {
    events:[],
    eventsFilter:{
        priceFrom:null
    },
    loadings:null,
    eventById:null
}

const storeLoadingByAction = (loading, action) => {
    let calls = action.type;
    debugger;
    if (action.type.endsWith("SUCCESS")) {
        calls = action.type.substring(0, action.type.length - 8);
    }
    if (action.type.endsWith("FAIL")) {
        calls = action.type.substring(0, action.type.length - 5);
    }
    return {[calls]:loading};

};

const reducer = (state = initialState, action) => {
    switch (action.type) {

        case types.GET_EVENTS_BY_FILTER_SUCCESS:
            return {...state, events:action.payload.data._embedded.eventResourceList, loadings:storeLoadingByAction(true, action)};
        case types.GET_EVENTS_BY_FILTER:
            return {...state, loadings:storeLoadingByAction(true, action)};
        case types.GET_EVENTS_BY_FILTER_FAIL:
            return {...state, loadings:false};
        case types.GET_EVENT_BY_ID_SUCCESS:
            return {...state, eventById:action.payload.data._embedded, loadings:true};
        case types.GET_EVENT_BY_ID:
            return {...state, loadings:false};
        case types.GET_EVENT_BY_ID_FAIL:
            return {...state, loadings:false};
        case types.CHANGE_FILTER_SUCCESS:
            const eventsFilter = action.eventsFilter;
            return {...state,eventsFilter:eventsFilter};
        default:
            return state;
    }
};

export default reducer;