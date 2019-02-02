import React, {Component} from 'react';
import './App.css';
import Events from '../events/Events';
import Event from '../events/Event';
import MainNavbar from '../../components/UI/Navbar/MainNavbar';
import {Route, Switch, BrowserRouter} from 'react-router-dom';
import AboutUs from './../../components/aboutus/AboutUs';
import ContactUs from './../../components/contactus/ContactUs';
import Careers from './../../components/careers/Careers';
import Login from './../../components/login/Login';
import Register from './../../components/register/Register';
import ScrapEvents from './../../components/scrap/ScrapEvents';
import AddEvent from '../events/AddEvent';

class App extends Component {

    render() {
        //TODO Events and events filter should be one component(EventSearch that contains 2 components since they are always going together!)
        return (
            <BrowserRouter>
                <div>
                    <MainNavbar/>
                    <Switch>
                        <Route path = "/contactus" component={ContactUs} />
                        <Route path = "/scrap" component={ScrapEvents} />
                        <Route path = "/aboutus" component={AboutUs} />
                        <Route path = "/careers" component={Careers} />
                        <Route path = "/addEvent" component={AddEvent} />
                        <Route path = "/login" component={Login} />
                        <Route path = "/register" component={Register} />
                        <Route path = "/" exact component={Events} />
                    </Switch>
                    <Route path = "/events/:id" exact component={Event} />
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
