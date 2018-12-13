/**
 * Created by spasoje on 05-Dec-18.
 */
import { createSelector } from 'reselect'

const extractEventsFilter = state => {
    if (state || state === undefined || state.events || state.events === undefined) {
        return null;
    }
    return state.events.eventsFilter;
}
const getEventsFilter = createSelector(
    extractEventsFilter,
    (eventsFilter) => eventsFilter
)
const selectors = {
    getEventsFilter:getEventsFilter
}
export default selectors;