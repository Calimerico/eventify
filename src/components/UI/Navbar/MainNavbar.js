/**
 * Created by spasoje on 29-Nov-18.
 */
import React from 'react';
import AnonymousNavbar from './AnonymousNavbar';
import NormalUserNavbar from './NormalUserNavbar';
import AdminNavbar from './AdminNavbar';
import authSelectors from './../../../redux/auth/selector'
import { connect } from 'react-redux';
class MainNavbar extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        debugger;
        const token = this.props.token;
        if(token === null){
            return <AnonymousNavbar/>
        }
        if(token.authorities[0] === "ROLE_ADMIN") {
            return <AdminNavbar/>
        }
        return <NormalUserNavbar/>
    }

}

const mapStateToProps = state => {
    return {
        token: authSelectors.getToken(state)
    }
};

export default connect(mapStateToProps,null)(MainNavbar);