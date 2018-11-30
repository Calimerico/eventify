/**
 * Created by spasoje on 29-Nov-18.
 */
import React from 'react';
import AnonymousNavbar from './AnonymousNavbar';
import NormalUserNavbar from './NormalUserNavbar';
import AdminNavbar from './AdminNavbar';

class MainNavbar extends React.Component {

    constructor(props) {
        super(props);
        this.parseJwt = this.parseJwt.bind(this);
    }

    render() {
        const token = this.parseJwt(localStorage.getItem("token"));
        if(token === null){
            return <AnonymousNavbar/>
        }
        if(token.authorities[0] === "ROLE_ADMIN") {
            return <AdminNavbar/>
        }
        return <NormalUserNavbar/>
    }

    parseJwt = (token) => {
        if(token === null) {
            return null;
        }
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        return JSON.parse(window.atob(base64));
    };

}

export default MainNavbar;