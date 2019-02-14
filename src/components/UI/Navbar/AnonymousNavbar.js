/**
 * Created by spasoje on 16-Nov-18.
 */
import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Home from '@material-ui/icons/Home';
import HowToReg from '@material-ui/icons/HowToReg';
import PermIdentity from '@material-ui/icons/PermIdentity';
import Work from '@material-ui/icons/Work';
import Phone from '@material-ui/icons/Phone';
import PersonPinIcon from '@material-ui/icons/PersonPin';
import {NavLink} from 'react-router-dom';
const styles = theme => ({
    root: {
        flexGrow: 1,
        width: '100%',
        backgroundColor: theme.palette.background.paper,
    },
    tabLabel: {
        fontSize: "11px"
    },
    tabContainer: {
        fontSize: "13px"
    }
});

class ScrollableTabsButtonForce extends React.Component {
    state = {
        value: 0,
    };

    handleChange = (event, value) => {
        this.setState({ value });
    };

    render() {
        const { classes } = this.props;
        const { value } = this.state;
        //TODO Add change language option in anonymous menu!

        return (
            <div className={classes.root}>
                <AppBar position="static" color="default">
                    <Tabs
                        value={value}
                        onChange={this.handleChange}
                        scrollable
                        scrollButtons="on"
                        indicatorColor="primary"
                        textColor="primary"
                    >

                        <Tab label={<span className={classes.tabLabel}>Home</span>}  icon={<Home />} component={NavLink} to="/"/>
                        <Tab label={<span className={classes.tabLabel}>Scrap events</span>}  icon={<Home />} component={NavLink} to="/scrap"/>
                        <Tab label={<span className={classes.tabLabel}>Contact us</span>} icon={<Phone />} component={NavLink} to="/contactus"/>
                        <Tab label={<span className={classes.tabLabel}>About us</span>} icon={<PersonPinIcon />} component={NavLink} to="/aboutus"/>
                        <Tab label={<span className={classes.tabLabel}>Careers</span>} icon={<Work />} component={NavLink} to="/careers"/>
                        <Tab label={<span className={classes.tabLabel}>Login</span>} icon={<PermIdentity />} component={NavLink} to="/login"/>
                        <Tab label={<span className={classes.tabLabel}>Register</span>} icon={<HowToReg />} component={NavLink} to="/register"/>
                    </Tabs>
                </AppBar>
            </div>
        );
    }
}

ScrollableTabsButtonForce.propTypes = {
    classes: PropTypes.object.isRequired,
    theme: PropTypes.object.isRequired
};

export default withStyles(styles, { withTheme: true })(ScrollableTabsButtonForce);