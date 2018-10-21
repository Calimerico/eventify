import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';
import {withNamespaces} from 'react-i18next';
import {t} from 'react-i18next';
const navbar = () => {
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
                    <LinkContainer to="/"><NavItem eventKey={1}>Home</NavItem></LinkContainer>
                    <LinkContainer to="/aboutus"><NavItem eventKey={2}>About us</NavItem></LinkContainer>
                    <LinkContainer to="/careers"><NavItem eventKey={3}>Careers</NavItem></LinkContainer>
                    <NavDropdown eventKey={4} title="Sign in" id="basic-nav-dropdown">
                        <MenuItem eventKey={4.1}>Sign in</MenuItem>
                        <MenuItem eventKey={4.2}>Register</MenuItem>
                    </NavDropdown>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    )
}

export default withNamespaces()(navbar);
