/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';

const reducer = (state = {events:{}}, action) => {
    switch (action.type) {
        case types.GET_EVENTS_SUCCESS:
            const events = {...state.events};
            action.payload.data.map(event => {
                events.push(event)
            });
            return {...state, events:{...state.events,events:events}};
        case types.FILTER_CHANGED:
            const eventsFilter = action.eventsFilter;
            return {...state,events:{...state.events, eventsFilter:eventsFilter}};
        default:
            return state;
    }
};

export default reducer;