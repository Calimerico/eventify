/**
 * Created by spasoje on 19-Oct-18.
 */

const initialState = {
    events: [
        {
            eventName: 'Zika',
            description: 'Neki desc',
            eventPrice: 11,
            date:'12/9/2019',
            eventTime:'12:00',
            place:'Sava centar Beograd'
        }
    ]
}

const eventsReducer = (state = initialState, action) => {
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