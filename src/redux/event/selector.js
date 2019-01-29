/**
 * Created by spasoje on 05-Dec-18.
 */
import { createSelector } from 'reselect'

const extractEventsFilter = state => {
    return state.eventsReducer.eventsFilter;
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
const selectors = {
    getEventsFilter:getEventsFilter,
    getEventsByFilter:getEventsByFilter,
};
export default selectors;