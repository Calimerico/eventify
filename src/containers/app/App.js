import React, {Component} from 'react';
import './App.css';
import Events from '../events/Events';
import AnonymousNavbar from '../../components/UI/Navbar/AnonymousNavbar';
import NormalUserNavbar from '../../components/UI/Navbar/NormalUserNavbar';
import MainNavbar from '../../components/UI/Navbar/MainNavbar';
import {BrowserRouter} from 'react-router-dom';
import {Route} from 'react-router-dom';
import AboutUs from './../../components/aboutus/AboutUs';
import ContactUs from './../../components/contactus/ContactUs';
import Careers from './../../components/careers/Careers';
import Login from './../../components/login/Login';
import Register from './../../components/register/Register';
import AddEvent from '../events/AddEvent';
import SwipeableRoutes from "react-swipeable-routes";

class App extends Component {

    render() {
        return (
            <BrowserRouter>
                <div>
                    {/*<NormalUserNavbar/>*/}
                    {/*<AnonymousNavbar/>*/}
                    <MainNavbar/>
                    <SwipeableRoutes>
                        <Route path = "/" exact component={Events} />
                        <Route path = "/contactus" component={ContactUs} />
                        <Route path = "/aboutus" component={AboutUs} />
                        <Route path = "/careers" component={Careers} />
                        <Route path = "/addEvent" component={AddEvent} />
                        <Route path = "/login" component={Login} />
                        <Route path = "/register" component={Register} />
                    </SwipeableRoutes>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
