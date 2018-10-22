import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';
import {translate} from 'react-i18next';
import i18n from 'i18next';

const navbar = (props) => {
    const changeLanguage = lng => {
        i18n.changeLanguage(lng);
    }
    const {t} = props;
    return (
        <Navbar collapseOnSelect>
            <Navbar.Header>
                <Navbar.Brand>
                    <a href="#brand">Eventify</a>
                </Navbar.Brand>
                <Navbar.Toggle />
            </Navbar.Header>
            <Navbar.Collapse>
                <Nav pullRight>
                    <LinkContainer to="/"><NavItem eventKey={1}>{t('navbar.home')}</NavItem></LinkContainer>
                    <LinkContainer to="/aboutus"><NavItem eventKey={2}>{t('navbar.aboutUs')}</NavItem></LinkContainer>
                    <LinkContainer to="/careers"><NavItem eventKey={3}>{t('navbar.careers')}</NavItem></LinkContainer>
                    <NavDropdown eventKey={4} title={i18n.t('navbar.language')} id="basic-nav-dropdown">
                        <MenuItem onClick={() => changeLanguage('sr')} eventKey={4.1}>{t('navbar.changeLanguage.serbian')}</MenuItem>
                        <MenuItem onClick={() => changeLanguage('en')} eventKey={4.2}>{t('navbar.changeLanguage.english')}</MenuItem>
                    </NavDropdown>
                    <NavDropdown eventKey={5} title={t('navbar.signInDrowdown')} id="basic-nav-dropdown">
                        <MenuItem eventKey={5.1}>{t('navbar.signIn')}</MenuItem>
                        <MenuItem eventKey={5.2}>{t('navbar.register')}</MenuItem>
                    </NavDropdown>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    )
}

export default translate()(navbar);
