/**
 * Created by spasoje on 03-Dec-18.
 */
import axios from './../../axiosBase';

export const scrapEvents = () => {
    return (dispatch) => {
        dispatch({type:'SCRAP_EVENTS'});

        axios.post( '/scraper/scrap')
            .then( response => {
                dispatch({type:'SCRAP_EVENTS_SUCCESS'});
            } )
            .catch( error => {
                dispatch({type:'SCRAP_EVENTS_FAIL',error:error});
            } );
    }
};

const actions = {
    scrapEvents:scrapEvents
}

export default actions;


