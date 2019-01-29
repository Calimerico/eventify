/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';

const initialState = {
    events:[],
    eventsFilter:{
        priceFrom:null
    }
}

const reducer = (state = initialState, action) => {
    switch (action.type) {

        case types.GET_EVENTS_BY_FILTER_SUCCESS:
            return {...state, events:action.payload.data};
        case types.GET_EVENTS_BY_FILTER:
            return {...state};
        case types.GET_EVENTS_BY_FILTER_FAIL:
            return {...state};
        case types.CHANGE_FILTER_SUCCESS:
            const eventsFilter = action.eventsFilter;
            return {...state,eventsFilter:eventsFilter};
        default:
            return state;
    }
};

export default reducer;