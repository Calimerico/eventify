/**
 * Created by spasoje on 05-Dec-18.
 */
import types from './types';

const reducer = (state = {}, action) => {
    switch (action.type) {
        case types.LOGIN_SUCCESS:
            localStorage.setItem("token",action.token);
            window.location.replace("/");
            return state;
        case types.LOGOUT:
            localStorage.removeItem("token");
            window.location.replace("/");
            return state;
        default:
            return state;
    }
};

export default reducer;