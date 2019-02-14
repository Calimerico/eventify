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

const extractEventById = state => {
    return state.eventsReducer.eventById;
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
    (events) => events
);
const isEventLoaded = createSelector(
    extractLoaded,
    (loading) => loading
);
const doesEventExist = createSelector(
    extractEvents,
    (events) => true //TODO
);

const getEventById = createSelector(
    extractEventById,
    (event) => {
        return event;
    }
);
const selectors = {
    getEventsFilter:getEventsFilter,
    getEventById:getEventById,
    doesEventExist:doesEventExist,
    isEventLoaded:isEventLoaded,
    getEventsByFilter:getEventsByFilter,
};
export default selectors;