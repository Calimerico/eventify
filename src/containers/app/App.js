import React, {Component} from 'react';
import './App.css';
import Events from '../events/Events';
import Navbar from '../../components/UI/Navbar/Navbar';
import {BrowserRouter} from 'react-router-dom';
import {Route} from 'react-router-dom';
import AboutUs from './../../components/aboutus/AboutUs';
import Careers from './../../components/careers/Careers';

class App extends Component {

    render() {
        return (
            <BrowserRouter>
                <div>
                    <Navbar/>
                    <Route path = "/" exact component={Events} />
                    <Route path = "/aboutus" component={AboutUs} />
                    <Route path = "/careers" component={Careers} />
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
