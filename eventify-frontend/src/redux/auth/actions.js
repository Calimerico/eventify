/**
 * Created by spasoje on 05-Dec-18.
 */
import axios from './../../axiosBase';
import types from './types';

export const login = (username,password) => {
    return (dispatch) => {
        dispatch({type:types.LOGIN});
        axios.defaults.headers.common['Content-Type'] = 'application/json';
        axios.post( 'http://localhost:8762/auth/login', {username:username,password:password} )
            .then( response => {
                dispatch({type:types.LOGIN_SUCCESS,token:response.headers.authorization});
            } )
            .catch( error => {
                dispatch({type:types.LOGIN_FAIL,error:error});
            } );
    }
};

export const register = (newUser) => {
    return (dispatch) => {
        dispatch({type:types.REGISTER});
        axios.defaults.headers.common['Content-Type'] = 'application/json';
        axios.post( 'http://localhost:8762/auth/register', newUser )
            .then( response => {
                dispatch({type:types.REGISTER_SUCCESS,token:response.headers.authorization});
            } )
            .catch( error => {
                dispatch({type:types.REGISTER_FAIL,error:error});
            } );
    }
};
export const logout = () => {
    return (dispatch) => {
        dispatch({type:types.LOGOUT});
    }
};
const actions = {
    logout:logout,
    login:login,
    register:register
}

export default actions;