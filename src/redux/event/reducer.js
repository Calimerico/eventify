/**
 * Created by spasoje on 05-Dec-18.
 */

const reducer = (state = {}, action) => {
    switch (action.type) {
        case 'GET_EVENTS_SUCCESS':
            const events = {...state.events};
            action.payload.data.map(event => {
                events.push(event)
            });
            return {...state, events:events};
        default:
            return state;
    }
};

export default reducer;