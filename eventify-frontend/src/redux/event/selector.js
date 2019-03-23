/**
 * Created by spasoje on 05-Dec-18.
 */
import { createSelector } from 'reselect'

const extractEventsFilter = state => {
    return state.eventsReducer.eventsFilter;
};
const extractEventId = (state,props) => {
    return props.match.params.id;
};
const extractLoaded = (state,props) => {
    return state.eventsReducer.loading;
};

const extractEvents = state => {
    return state.eventsReducer.events;
};


const getEventsFilter = createSelector(
    extractEventsFilter,
    (eventsFilter) => eventsFilter
);

const getEventsByFilter = createSelector(
    extractEvents,
    (events) => {
        return Object.values(events);
    }
);
const isEventLoaded = createSelector(
    extractLoaded,
    (loading) => loading
);

const getEventById = createSelector(
    extractEvents,
    extractEventId,
    (events, eventId) => {
        if (events == null) {
            return null;
        }
        return events[eventId];
    }
);
const selectors = {
    getEventsFilter:getEventsFilter,
    getEventById:getEventById,
    isEventLoaded:isEventLoaded,
    getEventsByFilter:getEventsByFilter,
};
export default selectors;