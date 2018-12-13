/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';

const reducer = (state = {}, action) => {
    switch (action.type) {
        case types.LOGIN_SUCCESS:
            localStorage.setItem("token",action.token);
            window.location.replace("/");//TODO This redirect does not work!
            return state;
        case types.LOGOUT:
            localStorage.removeItem("token");
            window.location.replace("/");//TODO This redirect does not work!
            return {...state, token:null};
        case types.REGISTER_SUCCESS:
            return state;//TODO Implement here logic for register
        default:
            return state;
    }
};

export default reducer;