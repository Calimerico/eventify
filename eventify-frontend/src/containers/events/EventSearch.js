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
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

//TODO This should be read from backend EventType
const eventTypes = [
    'THEATER',
    'SPORT',
    'CINEMA'
];
//TODO This should be read from backend City or something like that
const cities = [
    'Belgrade',
    'Smederevo',
    'Novi sad'
]

class EventSearch extends React.Component {
    constructor(props) {
        super(props);
        this.handleSelectEventTypeChange = this.handleSelectEventTypeChange.bind(this);
        this.handleSelectPlaceChange = this.handleSelectPlaceChange.bind(this);
        this.handleSelectCityChange = this.handleSelectCityChange.bind(this);
        this.loadEvents = this.loadEvents.bind(this);
        this.searchEvents = this.searchEvents.bind(this);
        this.handleFromDateChange = this.handleFromDateChange.bind(this);
        this.handleToDateChange = this.handleToDateChange.bind(this);
        this.handlePriceFromChange = this.handlePriceFromChange.bind(this);
        this.handlePriceToChange = this.handlePriceToChange.bind(this);
    }

    handleSelectEventTypeChange = event => {
        const {changeFilter, filter} = this.props;
        const newFilter = Object.assign({},{...filter},{[event.target.name]: event.target.value})
        changeFilter(newFilter);
    };

    handleSelectPlaceChange = event => {
        const {places, changeFilter, filter} = this.props;
        const newFilter = Object.assign({},{...filter},{placeId: places.find(place => place.names[0] === event.target.value).id})
        changeFilter(newFilter);
    };

    handleSelectCityChange = event => {
        const {loadPlaces, changeFilter, filter} = this.props;
        const newFilter = Object.assign({},{...filter},{city: event.target.value});
        changeFilter(newFilter);
        loadPlaces(event.target.value);
    };

    handleFromDateChange = event => {
        const {changeFilter, filter} = this.props;
        changeFilter(Object.assign({},{...filter},{timeFrom: event}));
    };

    handleToDateChange = event => {
        const {changeFilter, filter} = this.props;
        changeFilter(Object.assign({},{...filter},{timeTo: event}));
    };

    handlePriceFromChange = event => {
        const {changeFilter, filter} = this.props;
        const newValue = event.target.value;
        changeFilter(Object.assign({},{...filter},{priceFrom: newValue == null || newValue === "" ? null : parseInt(newValue)}));
    };

    handlePriceToChange = event => {
        const {changeFilter, filter} = this.props;
        const newValue = event.target.value;
        changeFilter(Object.assign({},{...filter},{priceTo: newValue == null || newValue === "" ? null : parseInt(newValue)}));
    };

    searchEvents() {
        if (this.shouldLoadEvents()) {
            this.loadEvents();
        }
    }

    loadEvents() {
        const {filter:{eventType, timeFrom, timeTo, priceFrom, priceTo, placeId}} = this.props;
        const {events, loadEventsByFilter} = this.props;
        if (events) {
            loadEventsByFilter({
                eventType:eventType,
                timeFrom:timeFrom == null ? null : new Date(timeFrom._d.getTime() - timeFrom._d.getTimezoneOffset()*60000).toISOString(),
                timeTo:timeTo == null ? null : new Date(timeTo._d.getTime() - timeTo._d.getTimezoneOffset()*60000).toISOString(),
                placeId:placeId,
                priceFrom:priceFrom,
                priceTo:priceTo
            })
        }
    }

    componentDidMount() {
        this.loadEvents();
    }


    shouldLoadEvents() {
        const {lastUsedFilter, filter} = this.props;
        return filter.eventType !== lastUsedFilter.eventType ||
               filter.city !== lastUsedFilter.city ||
               ((lastUsedFilter.timeFrom == null && filter.timeFrom != null) || (filter.timeFrom != null && lastUsedFilter.timeFrom != null && filter.timeFrom.isBefore(lastUsedFilter.timeFrom))) ||
               ((lastUsedFilter.timeTo == null && filter.timeTo != null) || (filter.timeTo != null && lastUsedFilter.timeTo != null && filter.timeTo.isAfter(lastUsedFilter.timeTo))) ||
               ((lastUsedFilter.priceFrom == null && filter.priceFrom != null) || (lastUsedFilter.priceFrom != null && filter.priceFrom != null && filter.priceFrom < lastUsedFilter.priceFrom)) ||
               ((lastUsedFilter.priceTo == null && filter.priceTo != null) || (lastUsedFilter.priceTo != null && filter.priceTo != null && filter.priceTo > lastUsedFilter.priceTo));
    }

    render(){
        const { classes, places, filter: { eventType, city, placeId, timeFrom, timeTo, priceFrom, priceTo } } = this.props;
        return (
            <div style={{backgroundColor:"#e0e0e0"}}>
                <Grid container>
                    <Grid item xs={12}>
                        <FormControl className={classes.formControl}>
                            <InputLabel shrink htmlFor="event-type-label-placeholder">
                                Event type
                            </InputLabel>
                            <Select
                                value={eventType}
                                onChange={this.handleSelectEventTypeChange}
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
                            <InputLabel shrink htmlFor="city-label-placeholder">
                                Select city
                            </InputLabel>
                            <Select
                                value={city}
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
                            <DatePicker
                                selected={timeFrom}
                                name="timeFrom"
                                onChange={this.handleFromDateChange}
                            />
                            <DatePicker
                                selected={timeTo}
                                name="timeTo"
                                onChange={this.handleToDateChange}
                            />
                            <TextField
                                id="standard-dense"
                                label="Price from"
                                className={classes.textField}
                                onChange={this.handlePriceFromChange}
                                value={priceFrom}
                            />
                            <TextField
                                id="standard-dense"
                                label="Price to"
                                className={classes.textField2}
                                onChange={this.handlePriceToChange}
                                value={priceTo}
                            />
                            <Button color="primary" onClick={this.searchEvents} className={classes.search}>Search</Button>
                        </FormControl>
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
        events:eventSelectors.getEventsByFilter(state),
        places:placeSelectors.getPlaces(state),
        filter:eventSelectors.getEventsFilter(state),
        lastUsedFilter: eventSelectors.getLastUsedFilter(state)
    }
};

const mapDispatchToProps = dispatch => {
    return {
        loadEventsByFilter: (eventsFilter) => dispatch(eventActions.getEventsByFilter(eventsFilter)),
        loadPlaces: (city) => dispatch(placeActions.loadPlaces(city)),
        changeFilter: (eventFilter) => dispatch(eventActions.changeFilter(eventFilter))
    }
};
export default withStyles(eventSearchStyle)(connect(mapStateToProps, mapDispatchToProps)(EventSearch));
