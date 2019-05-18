/**
 * Created by spasoje on 09-Dec-18.
 */

export const CALLS = {
    ADD_PLACE:'ADD_PLACE',
    GET_ALL_PLACES:'GET_ALL_PLACES'
};

const actions = {
    ADD_PLACE:CALLS.ADD_PLACE,
    ADD_PLACE_SUCCESS:CALLS.ADD_PLACE + '_SUCCESS',
    ADD_PLACE_FAIL:CALLS.ADD_PLACE + '_FAIL',
    GET_ALL_PLACES:CALLS.GET_ALL_PLACES,
    GET_ALL_PLACES_SUCCESS:CALLS.GET_ALL_PLACES + '_SUCCESS',
    GET_ALL_PLACES_FAIL:CALLS.GET_ALL_PLACES + '_FAIL'
}

export default actions;
