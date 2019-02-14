/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';

const initialState = {
    events:[],
    eventsFilter:{
        priceFrom:null
    },
    loading:null,
    eventById:null
}

const reducer = (state = initialState, action) => {
    switch (action.type) {

        case types.GET_EVENTS_BY_FILTER_SUCCESS:
            return {...state, events:action.payload.data, loading:false};
        case types.GET_EVENTS_BY_FILTER:
            return {...state, loading:true};
        case types.GET_EVENTS_BY_FILTER_FAIL:
            return {...state, loading:false};
        case types.GET_EVENT_BY_ID_SUCCESS:
            return {...state, eventById:action.payload.data, loading:true};
        case types.GET_EVENT_BY_ID:
            return {...state, loading:false};
        case types.GET_EVENT_BY_ID_FAIL:
            return {...state, loading:false};
        case types.CHANGE_FILTER_SUCCESS:
            const eventsFilter = action.eventsFilter;
            return {...state,eventsFilter:eventsFilter};
        default:
            return state;
    }
};

export default reducer;