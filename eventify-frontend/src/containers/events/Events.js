import React from "react";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
// core components
import Fab from '@material-ui/core/Fab';
import Icon from '@material-ui/core/Icon';
import eventSelectors from "../../redux/event/selector";
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import {connect} from "react-redux";
import { Link } from 'react-router-dom';
import eventActions from "../../redux/event/actions";
import DeleteIcon from '@material-ui/icons/Delete';

const styles = {
    cardCategoryWhite: {
        "&,& a,& a:hover,& a:focus": {
            color: "rgba(255,255,255,.62)",
            margin: "0",
            fontSize: "14px",
            marginTop: "0",
            marginBottom: "0"
        },
        "& a,& a:hover,& a:focus": {
            color: "#FFFFFF"
        }
    },
    cardTitleWhite: {
        color: "#FFFFFF",
        marginTop: "0px",
        minHeight: "auto",
        fontWeight: "300",
        fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
        marginBottom: "3px",
        textDecoration: "none",
        "& small": {
            color: "#777",
            fontSize: "65%",
            fontWeight: "400",
            lineHeight: "1"
        }
    }
};

class Events extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            editView: false,
            page: 0,
            rowsPerPage: 5,
            addEventDialogOpen:false,
            newEvent:{}
        };
        this.renderAddEventDialog = this.renderAddEventDialog.bind(this);
        this.handleAddEventDialogClose = this.handleAddEventDialogClose.bind(this);
        this.addEvent = this.addEvent.bind(this);
    }

    handleChangePage = (event, page) => {
        this.setState({ page });
    };

    handleChangeRowsPerPage = event => {
        this.setState({...this.state, rowsPerPage: event.target.value });
    };

    handleAddEventDialogClose() {
        this.setState({...this.state, addEventDialogOpen:false});
    }

    addEvent() {
        const {addEvent} = this.props;
        const {newEvent} = this.state;
        this.setState({...this.state, addEventDialogOpen:false});
        addEvent(newEvent);
    }

    renderAddEventDialog() {
        const {addEventDialogOpen} = this.state;
        return <Dialog
            open={addEventDialogOpen}
            onClose={this.handleAddEventDialogClose}
            aria-labelledby="form-dialog-title"
        >
            <DialogTitle id="form-dialog-title">Add event dialog</DialogTitle>
            <DialogContent>
                <TextField
                    onChange={(event) => this.setState({...this.state, newEvent:{...this.state.newEvent, eventName:event.target.value}})}
                    autoFocus
                    margin="dense"
                    id="name"
                    label="Event name"
                    fullWidth
                />
                <TextField
                    onChange={(event) => this.setState({...this.state, newEvent:{...this.state.newEvent, source:event.target.value}})}
                    autoFocus
                    margin="dense"
                    id="source"
                    label="Event source"
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={this.handleAddEventDialogClose} color="primary">
                    Cancel
                </Button>
                <Button onClick={this.addEvent} color="primary">
                    Add event
                </Button>
            </DialogActions>
        </Dialog>
    }

    render(){
        const { classes,events } = this.props;
        const { page, rowsPerPage, addEventDialogOpen } = this.state;

        return <div>Pera</div>
        // return (
        //     <div>
        //         <GridContainer>
        //             <GridItem xs={12} sm={12} md={12}>
        //                 <Card>
        //                     <CardHeader color="primary">
        //                         <GridContainer>
        //                             <GridItem xs={12} sm={12} md={8}>
        //                                 <h4 className={classes.cardTitleWhite}>Event Table</h4>
        //                                 <p className={classes.cardCategoryWhite}>
        //                                     Here is a subtitle for this table
        //                                 </p>
        //                             </GridItem>
        //                             <GridItem xs={12} sm={12} md={4}>
        //                                 <Fab onClick={() => this.setState({addEventDialogOpen: !addEventDialogOpen})}
        //                                      style={{ float: "right", marginRight: "10px", backgroundColor: "#e0e0e0", width:"40px", height:"40px"}} color="primary"
        //                                      aria-label="Edit">
        //                                     <Icon style={{ color: "black"}}>add_icon</Icon>
        //                                 </Fab>
        //                             </GridItem>
        //                         </GridContainer>
        //                     </CardHeader>
        //                     <CardBody>
        //                         <Table
        //                             tableHeaderColor="primary"
        //                             handleChangePage={this.handleChangePage}
        //                             handleChangeRowsPerPage={this.handleChangeRowsPerPage}
        //                             page={page}
        //                             rowsPerPage={rowsPerPage}
        //                             tableHead={["Name", "Type", "Date and time", "Delete"]}
        //                             tableData={events.map(event => [<Link to={`/events/` + event.id}>{event.eventName}</Link>, event.eventType, event.eventDateTime,
        //                                 <Fab onClick={() => this.props.deleteEvent(event.id)}
        //                                      style={{width:"35px", height:"35px" }} aria-label="Delete">
        //                                     <DeleteIcon/>
        //                                 </Fab>])}
        //                         />
        //                     </CardBody>
        //                 </Card>
        //             </GridItem>
        //         </GridContainer>
        //         {this.renderAddEventDialog()}
        //     </div>
        // );
    }
}


const mapStateToProps = (state,props) => {
    return{
        events:eventSelectors.getEvents(state)
    }
};

const mapDispatchToProps = dispatch => {
    return {
        addEvent: (request) => {
            return dispatch(eventActions.addEvent(request));
        },
        deleteEvent: (id) => {
            return dispatch(eventActions.deleteEvent(id))
        }

    }
};

export default withStyles(styles)(connect(mapStateToProps, mapDispatchToProps)(Events));