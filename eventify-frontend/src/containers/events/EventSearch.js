import React from "react";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import Grid from "@material-ui/core/Grid";
import Input from "@material-ui/core/Input";
// core components
import eventSearchStyle from "assets/jss/material-dashboard-react/components/eventSearchStyle.jsx";
import DatePickers from "../../components/DatePickers/DatePickers"
import TextField from "@material-ui/core/TextField";
import Button from "components/CustomButtons/Button.jsx";
import eventSelectors from "../../redux/event/selector";
import placeSelectors from "../../redux/place/selector";
import eventActions from "../../redux/event/actions";
import placeActions from "../../redux/place/actions";
import {connect} from "react-redux";

const eventTypes = [
    'Theater',
    'Sport',
    'Cinema'
];

const cities = [
    'Belgrade',
    'Smederevo',
    'Novi sad'
]

class EventSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            eventType: null,
            timeFrom: null,
            timeTo: null,
            priceFrom: null,
            priceTo: null,
            placeId: null,
            city: ""
        }
        this.handleSelectPlaceChange = this.handleSelectPlaceChange.bind(this);
        this.handleSelectCityChange = this.handleSelectCityChange.bind(this);
    }

    handleSelectChange = event => {
        this.setState({ [event.target.name]: event.target.value });
    };

    handleSelectPlaceChange = event => {
        const {places} = this.props;
        debugger;
        this.setState({ placeId: places.find(place => place.names[0] === event.target.value).id});
    };

    handleSelectCityChange = event => {
        const {loadPlaces} = this.props;
        this.setState({ city: event.target.value });
        loadPlaces(event.target.value);
    };

    handleChange = name => event => {
        this.setState({ [name]: event.target.value });
    };

    componentDidMount() {
        const {eventType, timeFrom, timeTo, priceFrom, priceTo, placeId} = this.state;
        const {events, loadEventsByFilter} = this.props;
        if (events) {
            loadEventsByFilter({
                eventType:eventType,
                timeFrom:timeFrom,
                timeTo:timeTo,
                placeId:placeId,
                priceFrom:priceFrom,
                priceTo:priceTo
            })
        }
    }

    render(){
        const { classes, places } = this.props;
        const { placeId } = this.state;
        return (
            <div style={{backgroundColor:"#e0e0e0"}}>
                <Grid container>
                    <Grid item xs={12}>
                        <FormControl className={classes.formControl}>
                            <InputLabel shrink htmlFor="event-type-label-placeholder">
                                Event type
                            </InputLabel>
                            <Select
                                value={this.state.eventType}
                                onChange={this.handleSelectChange}
                                input={<Input name="eventType" id="event-type-label-placeholder" />}
                                displayEmpty
                                name="eventType"
                            >
                                {eventTypes.map(name => (
                                    <MenuItem  value={name} >
                                        {name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <FormControl className={classes.formControl}>
                            <InputLabel shrink htmlFor="city-label-placeholder">
                                Select city
                            </InputLabel>
                            <Select
                                value={this.state.city}
                                onChange={this.handleSelectCityChange}
                                input={<Input name="city" id="city-label-placeholder" />}
                                displayEmpty
                                name="city"
                            >
                                {cities.map(name => (
                                    <MenuItem  value={name} >
                                        {name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <FormControl className={classes.formControl}>
                            <InputLabel shrink htmlFor="place-label-placeholder">
                                Place
                            </InputLabel>
                            <Select
                                value={places.find(p => p.id === placeId) == null ? null : places.find(p => p.id === placeId).names[0]}
                                onChange={this.handleSelectPlaceChange}
                                input={<Input name="placeId" id="place-label-placeholder" />}
                                displayEmpty
                                name="placeId"
                            >
                                {places.map(p => p.names[0]).map(name => (//TODO HARDCODED!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    <MenuItem  value={name} >
                                        {name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <DatePickers
                            className={classes.datePicker}
                            label={"Date from"}
                            value={this.state.selectedDateFrom}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <DatePickers
                            className={classes.datePicker}
                            label={"Date to"}
                            value={this.state.selectedDateTo}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            id="standard-dense"
                            label="Price from"
                            className={classes.textField}
                            onChange={this.handleChange('name')}
                            value={this.state.priceFrom}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            id="standard-dense"
                            label="Price to"
                            className={classes.textField2}
                            onChange={this.handleChange('name')}
                            value={this.state.priceTo}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button color="primary" className={classes.search}>Search</Button>
                        {/*<p className={classes.submit}>Submit button</p>*/}
                    </Grid>
                </Grid>
            </div>
        );
    }
}

/*EventSearch.propTypes = {
  classes: PropTypes.object.isRequired
};*/


const mapStateToProps = (state,props) => {
    return{
        events:eventSelectors.getEvents(state),
        places:placeSelectors.getPlaces(state)
    }
};

const mapDispatchToProps = dispatch => {
    return {
        loadEventsByFilter: (eventsFilter) => dispatch(eventActions.getEventsByFilter(eventsFilter)),
        loadPlaces: (city) => dispatch(placeActions.loadPlaces(city))
    }
};
export default withStyles(eventSearchStyle)(connect(mapStateToProps, mapDispatchToProps)(EventSearch));
