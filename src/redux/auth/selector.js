/**
 * Created by spasoje on 05-Dec-18.
 */

import { createSelector } from 'reselect'
    const getToken = createSelector(
        () => {
            if (localStorage.getItem("token") != null) {
                const base64Url = localStorage.getItem("token").split('.')[1];
                const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
                return JSON.parse(window.atob(base64));
            }
            return null;
        }
    );
    const selectors = {
        getToken:getToken
    };

export default selectors;
