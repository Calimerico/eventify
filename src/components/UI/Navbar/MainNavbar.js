import React, {Component} from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';
import {translate} from 'react-i18next';
import i18n from 'i18next';

class MainNavbar extends Component {

    state = {
        mainNavbar: {
            home: {
                type: 'normalLink',
                label: 'navbar.home',
                to: '/'
            },
            aboutUs: {
                type: 'normalLink',
                label: 'navbar.aboutUs',
                to: '/aboutus'
            },
            careers: {
                type: 'normalLink',
                label: 'navbar.careers',
                to: '/careers'
            },
            addEvent: {
                type: 'normalLink',
                label: 'navbar.addEvent',
                to: '/addEvent'
            },
            languageDropdown: {
                type: 'dropdown',
                label: 'navbar.language',
                links: [
                    {
                        label: 'navbar.changeLanguage.serbian',
                        typeOfLink: 'onClick',
                        toLanguage: 'sr'
                    },
                    {
                        label: 'navbar.changeLanguage.english',
                        typeOfLink: 'onClick',
                        toLanguage: 'en'
                    }
                ]
            },
            signInDropdown: {
                type: 'dropdown',
                label: 'navbar.signInDrowdown',
                links: [
                    {
                        label: 'navbar.signIn',
                        typeOfLink: 'to',
                        to:"/login"
                    },
                    {
                        label: 'navbar.register',
                        typeOfLink: 'to',
                        to:"/register"
                    }
                ]
            }
        }

    }

    changeLanguage = lng => {
        i18n.changeLanguage(lng);
    };

    render() {
        const {t} = this.props;
        const navbarElementsArray = [];
        for (let key in this.state.mainNavbar) {
            navbarElementsArray.push({
                id: key,
                config: this.state.mainNavbar[key]
            })
        }
        const navbarElements = navbarElementsArray.map(element => {
            if (element.config.type === 'normalLink') {
                return (
                    <LinkContainer to={element.config.to}><NavItem eventKey={element.key}>{t(element.config.label)}</NavItem></LinkContainer>
                )
            } else {
                return (
                    <NavDropdown eventKey={element.key} title={t(element.config.label)} id="basic-nav-dropdown">
                        {element.config.links.map(link => {
                            if (link.typeOfLink === 'to') {
                                return(
                                    <LinkContainer to={link.to}><MenuItem >{t(link.label)}</MenuItem></LinkContainer>
                                )
                            } else {
                                return(
                                    <MenuItem onClick={this.changeLanguage.bind(this, link.toLanguage)}>{t(link.label)}</MenuItem>
                                )
                            }
                        })}
                    </NavDropdown>
                )
            }
        });
        return(
            <Navbar collapseOnSelect>
                <Navbar.Header>
                    <Navbar.Brand>
                        <a href="#brand">Eventify</a>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>

                <Navbar.Collapse>
                    <Nav pullRight>
                        {navbarElements}
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        )
    }
}

export default translate()(MainNavbar);
