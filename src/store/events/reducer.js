/**
 * Created by spasoje on 19-Oct-18.
 */

const eventsReducer = (state = {}, action) => {
    switch (action.type) {

        case 'ADD_EVENT':
            return {
                ...state,
                events: state.events.concat({
                    eventName: 'Pera',
                    description: 'Neki desc',
                    eventPrice: 10
                })
            }
        default:
            return state;
    }
}

export default eventsReducer;