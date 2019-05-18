/**
 * Created by spasoje on 05-Dec-18.
 */
import { createSelector } from 'reselect'

const extractPlaces = state => {
    return state.placesReducer.places;
};
const getPlaces = createSelector(
    extractPlaces,
    (places) => {
        return places;
    }
);
const selectors = {
    getPlaces:getPlaces
};
export default selectors;