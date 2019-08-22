import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import EventSearch from "./EventSearch";

const drawerWidth = 240;

const styles = theme => ({
    root: {
        display: 'flex',
    },
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
        marginTop:85
    }
});
// https://material-ui.com/demos/drawers/ Here is where I copied from
function EventSearchSidebar(props) {
    const { classes } = props;

    return (
        <div className={classes.root}>
            <Drawer
                className={classes.drawer}
                variant="permanent"
                classes={{
                    paper: classes.drawerPaper,
                }}
                anchor="left"
            >
                <Divider />
                <List>
                    <ListItem button key="1">
                        <ListItemIcon><InboxIcon /></ListItemIcon>
                        <ListItemText primary="Event list" />
                    </ListItem>
                </List>
                <EventSearch/>
            </Drawer>
        </div>
    );
}

EventSearchSidebar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(EventSearchSidebar);