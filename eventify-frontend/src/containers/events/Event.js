import React from "react";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
import InputLabel from "@material-ui/core/InputLabel";
// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import CustomInput from "components/CustomInput/CustomInput.jsx";
import Button from "components/CustomButtons/Button.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardAvatar from "components/Card/CardAvatar.jsx";
import CardBody from "components/Card/CardBody.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import Fab from '@material-ui/core/Fab';
import Icon from '@material-ui/core/Icon';
import DeleteIcon from '@material-ui/icons/Delete';
import {connect} from "react-redux";
import eventSelectors from "../../redux/event/selector";
import eventActions from "../../redux/event/actions";
import avatar from "assets/img/faces/marc.jpg";

const styles = {
    cardCategoryWhite: {
        color: "rgba(255,255,255,.62)",
        margin: "0",
        fontSize: "14px",
        marginTop: "0",
        marginBottom: "0"
    },
    cardTitleWhite: {
        color: "#FFFFFF",
        marginTop: "0px",
        minHeight: "auto",
        fontWeight: "300",
        fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
        marginBottom: "3px",
        textDecoration: "none"
    }
};

class EventPage extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            editView: false,
        };
    }

    componentDidMount() {
        const {event, loadEvent, match: {params: {id}}} = this.props;
        if (event) {
            loadEvent(id);
        }
    }

    renderEditEvent(){
        return(
            <div>
                <CardBody>
                    <GridContainer>
                        <GridItem xs={12} sm={12} md={6}>
                            <CustomInput
                                labelText="Username"
                                id="username"
                                formControlProps={{
                                    fullWidth: true
                                }}
                            />
                        </GridItem>
                        <GridItem xs={12} sm={12} md={6}>
                            <CustomInput
                                labelText="Email address"
                                id="email-address"
                                formControlProps={{
                                    fullWidth: true
                                }}
                            />
                        </GridItem>
                    </GridContainer>
                    <GridContainer>
                        <GridItem xs={12} sm={12} md={6}>
                            <CustomInput
                                labelText="First Name"
                                id="first-name"
                                formControlProps={{
                                    fullWidth: true
                                }}
                            />
                        </GridItem>
                        <GridItem xs={12} sm={12} md={6}>
                            <CustomInput
                                labelText="Last Name"
                                id="last-name"
                                formControlProps={{
                                    fullWidth: true
                                }}
                            />
                        </GridItem>
                    </GridContainer>
                    <GridContainer>
                        <GridItem xs={12} sm={12} md={4}>
                            <CustomInput
                                labelText="City"
                                id="city"
                                formControlProps={{
                                    fullWidth: true
                                }}
                            />
                        </GridItem>
                        <GridItem xs={12} sm={12} md={4}>
                            <CustomInput
                                labelText="Country"
                                id="country"
                                formControlProps={{
                                    fullWidth: true
                                }}
                            />
                        </GridItem>
                        <GridItem xs={12} sm={12} md={4}>
                            <CustomInput
                                labelText="Postal Code"
                                id="postal-code"
                                formControlProps={{
                                    fullWidth: true
                                }}
                            />
                        </GridItem>
                    </GridContainer>
                    <GridContainer>
                        <GridItem xs={12} sm={12} md={12}>
                            <InputLabel style={{ color: "#AAAAAA" }}>About me</InputLabel>
                            <CustomInput
                                labelText="Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo."
                                id="about-me"
                                formControlProps={{
                                    fullWidth: true
                                }}
                                inputProps={{
                                    multiline: true,
                                    rows: 5
                                }}
                            />
                        </GridItem>
                    </GridContainer>
                    <CardFooter>
                        <Button color="primary">Update Profile</Button>
                    </CardFooter>
                </CardBody>
            </div>
        )
    }

    renderCardHeader(){
        const { classes } = this.props;
        let {editView} = this.state;
        return(
            <CardHeader color="primary">
                <GridContainer>
                    <GridItem xs={12} sm={12} md={8}>
                        <h4 className={classes.cardTitleWhite}>Edit event</h4>
                        <p className={classes.cardCategoryWhite}>Complete your event</p>
                    </GridItem>
                    <GridItem xs={12} sm={12} md={4}>
                        <Fab style={{ float: "right", width:"40px", height:"40px" }} aria-label="Delete">
                            <DeleteIcon/>
                        </Fab>
                        <Fab onClick={() => this.setState({editView: !editView})}
                             style={{ float: "right", marginRight: "10px", backgroundColor: "#e0e0e0", width:"40px", height:"40px" }} color="primary"
                             aria-label="Edit">
                            <Icon style={{ color: "black" }}>edit_icon</Icon>
                        </Fab>
                    </GridItem>
                </GridContainer>
            </CardHeader>
        )
    }

    renderCardBody(){
        const { classes } = this.props;
        return(
            <div>
                <CardBody>
                    <GridContainer>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}} >Event title</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}} >Event title</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}}>Event titleaaa</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}}>Event titleaaa</p>
                        </GridItem>
                    </GridContainer>
                    <GridContainer>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}} >Event title</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}} >Event title</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}}>Event titleaaa</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}}>Event titleaaa</p>
                        </GridItem>
                    </GridContainer>

                    <GridContainer>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}} >Event title</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}} >Event title</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}}>Event titleaaa</p>
                        </GridItem>
                        <GridItem xs={6} sm={6} md={3}>
                            <p style={{marginTop:"50px"}}>Event titleaaa</p>
                        </GridItem>
                    </GridContainer>
                </CardBody>
            </div>
        )
    }

    render() {
        const { classes } = this.props;
        let {editView} = this.state;
        return (
            <div>
                <GridContainer>
                    <GridItem xs={12} sm={12} md={8}>
                        <Card>
                            {this.renderCardHeader()}
                            {editView ? this.renderEditEvent(): this.renderCardBody()}
                        </Card>
                    </GridItem>

                    <GridItem xs={12} sm={12} md={4}>
                        <Card profile>
                            <CardAvatar profile>
                                <a href="#pablo" onClick={e => e.preventDefault()}>
                                    <img src={avatar} alt="..."/>
                                </a>
                            </CardAvatar>
                            <CardBody profile>
                                <h6 className={classes.cardCategory}>CEO / CO-FOUNDER</h6>
                                <h4 className={classes.cardTitle}>Alec Thompson</h4>
                                <p className={classes.description}>
                                    Don't be scared of the truth because we need to restart the
                                    human foundation in truth And I love you like Kanye loves Kanye
                                    I love Rick Owens’ bed design but the back is...
                                </p>
                                <Button color="primary" round>
                                    Follow
                                </Button>
                            </CardBody>
                        </Card>
                    </GridItem>
                </GridContainer>
            </div>
        );
    }
}

const mapStateToProps = (state,props) => {
    return{
        event:eventSelectors.getEventById(state, props)
    }
};

const mapDispatchToProps = dispatch => {
    return {
        loadEvent: (id) => dispatch(eventActions.getEventById(id)),
        updateEvent: (id, request) => dispatch(eventActions.updateEvent(id, request))
    }
};


export default withStyles(styles)(connect(mapStateToProps, mapDispatchToProps)(EventPage));