/**
 * Created by spasoje on 09-Dec-18.
 */

export const CALLS = {
    GET_EVENTS_BY_FILTER:'GET_EVENTS_BY_FILTER',
    UPDATE_EVENT:'UPDATE_EVENT',
    GET_EVENT_BY_ID:'GET_EVENT_BY_ID',
    ADD_EVENT:'ADD_EVENT'
};

const actions = {
    GET_EVENTS_BY_FILTER:CALLS.GET_EVENTS_BY_FILTER,
    GET_EVENTS_BY_FILTER_SUCCESS:CALLS.GET_EVENTS_BY_FILTER + '_SUCCESS',
    GET_EVENTS_BY_FILTER_FAIL:CALLS.GET_EVENTS_BY_FILTER + '_FAIL',
    UPDATE_EVENT:CALLS.UPDATE_EVENT,
    UPDATE_EVENT_SUCCESS:CALLS.UPDATE_EVENT + '_SUCCESS',
    UPDATE_EVENT_FAIL:CALLS.UPDATE_EVENT + '_FAIL',
    GET_EVENT_BY_ID:CALLS.GET_EVENT_BY_ID,
    GET_EVENT_BY_ID_SUCCESS:CALLS.GET_EVENT_BY_ID + '_SUCCESS',
    GET_EVENT_BY_ID_FAIL:CALLS.GET_EVENT_BY_ID + '_FAIL',
    CHANGE_FILTER_SUCCESS:'CHANGE_FILTER_SUCCESS',
    ADD_EVENT:CALLS.ADD_EVENT,
    ADD_EVENT_SUCCESS:CALLS.ADD_EVENT + '_SUCCESS',
    ADD_EVENT_FAIL:CALLS.ADD_EVENT + '_FAIL'
}

export default actions;
