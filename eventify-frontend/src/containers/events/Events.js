import React from "react";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import Table from "components/Table/Table.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardBody from "components/Card/CardBody.jsx";
import Fab from '@material-ui/core/Fab';
import Icon from '@material-ui/core/Icon';
import eventSelectors from "../../redux/event/selector";
import eventActions from "../../redux/event/actions";
import {connect} from "react-redux";
import { Link } from 'react-router-dom';

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

class EventTablePage extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            editView: false,
            page: 0,
            rowsPerPage: 5,
        };
    }

    handleChangePage = (event, page) => {
        this.setState({ page });
    };

    handleChangeRowsPerPage = event => {
        this.setState({...this.state, rowsPerPage: event.target.value });
    };

    render(){
        const { classes,events } = this.props;
        let { editView } = this.state;
        const { page, rowsPerPage } = this.state;

        return (
            <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                    <Card>
                        <CardHeader color="primary">
                            <GridContainer>
                                <GridItem xs={12} sm={12} md={8}>
                                    <h4 className={classes.cardTitleWhite}>Event Table</h4>
                                    <p className={classes.cardCategoryWhite}>
                                        Here is a subtitle for this table
                                    </p>
                                </GridItem>
                                <GridItem xs={12} sm={12} md={4}>
                                    <Fab onClick={() => this.setState({editView: !editView})}
                                         style={{ float: "right", marginRight: "10px", backgroundColor: "#e0e0e0", width:"40px", height:"40px"}} color="primary"
                                         aria-label="Edit">
                                        <Icon style={{ color: "black"}}>add_icon</Icon>
                                    </Fab>
                                </GridItem>
                            </GridContainer>
                        </CardHeader>
                        <CardBody>
                            <Table
                                tableHeaderColor="primary"
                                handleChangePage={this.handleChangePage}
                                handleChangeRowsPerPage={this.handleChangeRowsPerPage}
                                page={page}
                                rowsPerPage={rowsPerPage}
                                tableHead={["Name", "Type", "Date and time"]}
                                tableData={events.map(event => [<Link to={`/events/` + event.id}>{event.eventName}</Link>, event.eventType, event.eventDateTime])}
                            />
                        </CardBody>
                    </Card>
                </GridItem>
            </GridContainer>
        );
    }
}


const mapStateToProps = (state,props) => {
    return{
        events:eventSelectors.getEvents(state)
    }
};

export default withStyles(styles)(connect(mapStateToProps, null)(EventTablePage));