import React, {Component} from 'react';
import './App.css';
import Events from '../events/Events';
import Event from '../events/Event';
import MainNavbar from '../../components/UI/Navbar/MainNavbar';
import {Route, Switch, BrowserRouter} from 'react-router-dom';
import AboutUs from './../../components/aboutus/AboutUs';
import ContactUs from '../contactus/ContactUs';
import Careers from './../../components/careers/Careers';
import Login from '../login/Login';
import Register from '../register/Register';
import ScrapEvents from '../scrap/ScrapEvents';
import AddEvent from '../events/AddEvent';
import Layout from "../../components/UI/Layout";

class App extends Component {

    render() {
        return (
            <BrowserRouter>
                <div>

                    <Switch>
                        <Route path = "/contactus" render={(props) => <Layout {...props} header={<MainNavbar/>}/>} />
                        <Route path = "/scrap" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<ScrapEvents/>}/>} />
                        <Route path = "/aboutus" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<AboutUs/>}/>} />
                        <Route path = "/careers" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<Careers/>}/>} />
                        <Route path = "/addEvent" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<AddEvent/>}/>} />
                        <Route path = "/login" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<Login/>}/>} />
                        <Route path = "/register" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<Register/>}/>} />
                        <Route path = "/" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<Events/>}/>} />
                        <Route path = "/events/:id" render={(props) => <Layout {...props} header={<MainNavbar/>} main={<Event/>}/>} />
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
