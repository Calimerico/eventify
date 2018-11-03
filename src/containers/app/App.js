import React, {Component} from 'react';
import './App.css';
import Events from '../events/Events';
import MainNavbar from '../../components/UI/Navbar/MainNavbar';
import {BrowserRouter} from 'react-router-dom';
import {Route} from 'react-router-dom';
import AboutUs from './../../components/aboutus/AboutUs';
import Careers from './../../components/careers/Careers';
import Login from './../../components/login/Login';
import Register from './../../components/register/Register';

class App extends Component {

    render() {
        return (
            <BrowserRouter>
                <div>
                    <MainNavbar/>
                    <Route path = "/" exact component={Events} />
                    <Route path = "/aboutus" component={AboutUs} />
                    <Route path = "/careers" component={Careers} />
                    <Route path = "/login" component={Login} />
                    <Route path = "/register" component={Register} />
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
