import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableHead from '@material-ui/core/TableHead';
import TableCell from '@material-ui/core/TableCell';
import TableFooter from '@material-ui/core/TableFooter';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';

import IconButton from '@material-ui/core/IconButton';
import FirstPageIcon from '@material-ui/icons/FirstPage';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';
import LastPageIcon from '@material-ui/icons/LastPage';
import { connect } from 'react-redux';
import eventActions from './../../redux/event/actions'

import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import TextField from '@material-ui/core/TextField';
import eventSelectors from './../../redux/event/selector'
import Input from '@material-ui/core/Input'
import InputLabel from '@material-ui/core/InputLabel'


const actionsStyles = theme => ({
    root: {
        flexShrink: 0,
        color: theme.palette.text.secondary,
        marginLeft: theme.spacing.unit * 2.5,
    },
});

class TablePaginationActions extends React.Component {
    handleFirstPageButtonClick = event => {
        this.props.onChangePage(event, 0);
    };

    handleBackButtonClick = event => {
        this.props.onChangePage(event, this.props.page - 1);
    };

    handleNextButtonClick = event => {
        this.props.onChangePage(event, this.props.page + 1);
    };

    handleLastPageButtonClick = event => {
        this.props.onChangePage(
            event,
            Math.max(0, Math.ceil(this.props.count / this.props.rowsPerPage) - 1),
        );
    };

    render() {
        const { classes, count, page, rowsPerPage, theme } = this.props;

        return (
            <div className={classes.root}>

                <IconButton
                    onClick={this.handleFirstPageButtonClick}
                    disabled={page === 0}
                    aria-label="First Page"
                >
                    {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
                </IconButton>
                <IconButton
                    onClick={this.handleBackButtonClick}
                    disabled={page === 0}
                    aria-label="Previous Page"
                >
                    {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
                </IconButton>
                <IconButton
                    onClick={this.handleNextButtonClick}
                    disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                    aria-label="Next Page"
                >
                    {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
                </IconButton>
                <IconButton
                    onClick={this.handleLastPageButtonClick}
                    disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                    aria-label="Last Page"
                >
                    {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
                </IconButton>
            </div>
        );
    }
}

TablePaginationActions.propTypes = {
    classes: PropTypes.object.isRequired,
    count: PropTypes.number.isRequired,
    onChangePage: PropTypes.func.isRequired,
    page: PropTypes.number.isRequired,
    rowsPerPage: PropTypes.number.isRequired,
    theme: PropTypes.object.isRequired,
};

const TablePaginationActionsWrapped = withStyles(actionsStyles, { withTheme: true })(
    TablePaginationActions,
);

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
    },
    table: {
        minWidth: 500,
    },
    tableWrapper: {
        overflowX: 'auto',
    },
    tableCell: {
        fontSize:"14px"
    }
});

class CustomPaginationActionsTable extends React.Component {

    constructor(props) {
        super(props);
        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
        this.state = {
            filter:{},
            page: 0,
            rowsPerPage: 5,
        }
    }


    handleChangePage = (event, page) => {
        this.setState({...this.state,page: event.target.value });
    };

    handleChangeRowsPerPage = event => {
        this.setState({...this.state, rowsPerPage: event.target.value });
    };

    onSubmit = event => {
        this.props.getEventsByFilter(this.state.filter);
    };

    onChange = event => {
        this.setState({...this.state,filter:{...this.state.filter,[event.target.name]:event.target.value}});
    };

    render() {
        const { classes } = this.props;
        const { rowsPerPage, page } = this.state;
        let events = this.props.events;
        if (events === null || events === undefined) {//TODO Workaroud for TypeError: events.slice is not a function https://stackoverflow.com/questions/37458004/slice-is-not-a-function-although-called-on-string
            events = [];//TODO This should be fixed with spinner this whole todo is just workaround
        }
        const emptyRows = rowsPerPage - Math.min(rowsPerPage, events.length - page * rowsPerPage);
        return <div>
                <form className={classes.root}>
                        <InputLabel htmlFor="eventType">Event type</InputLabel>
                        <Select
                            value={this.state.filter.eventType}
                            onChange={this.onChange}
                            inputProps={{
                                className:classes.select,
                                name: 'eventType',
                                id: 'eventType',
                            }}
                        >
                            <MenuItem classes={classes.menuItem} value="">
                                <em>All</em>
                            </MenuItem>
                            <MenuItem value="theater">Theater</MenuItem>
                            <MenuItem value="sport">Sport</MenuItem>
                            <MenuItem value="cinema">Cinema</MenuItem>
                        </Select>
                        <TextField
                            id="dateFrom"
                            type="date"
                            className={classes.textField}
                            InputProps={{
                                className: classes.textField,
                            }}
                        />
                        <TextField
                            id="dateTo"
                            type="date"
                            className={classes.textField}
                            InputProps={{
                                className: classes.textField,
                            }}
                        />
                        <Button onClick={this.onSubmit}>Search events</Button>
                </form>
                <Paper className={classes.root}>
                    <div className={classes.tableWrapper}>
                        <Table className={classes.table}>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Event name</TableCell>
                                    <TableCell>Place</TableCell>
                                    <TableCell>Type</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {events.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map(event => {
                                    return (

                                        <TableRow >
                                            <TableCell className={classes.tableCell} component="th" scope="row">
                                                {event.eventName}
                                            </TableCell>
                                            <TableCell className={classes.tableCell} >{event.placeId}</TableCell>
                                            <TableCell className={classes.tableCell} >{event.eventType}</TableCell>
                                        </TableRow>
                                    );
                                })}
                                {emptyRows > 0 && (
                                    <TableRow style={{ height: 48 * emptyRows }}>
                                        <TableCell className={classes.tableCell} colSpan={6} />
                                    </TableRow>
                                )}
                            </TableBody>
                            <TableFooter>
                                <TableRow>
                                    <TablePagination
                                        rowsPerPageOptions={[5, 10, 15]}
                                        colSpan={3}
                                        count={events.length}
                                        rowsPerPage={rowsPerPage}
                                        page={page}
                                        className={classes.tableCell}
                                        onChangePage={this.handleChangePage}
                                        onChangeRowsPerPage={this.handleChangeRowsPerPage}
                                        ActionsComponent={TablePaginationActionsWrapped}
                                    />
                                </TableRow>
                            </TableFooter>
                        </Table>
                    </div>
                </Paper>
            </div>
    }
}

CustomPaginationActionsTable.propTypes = {
    classes: PropTypes.object.isRequired,
};

const mapStateToProps = (state,props) => {
    return{
        events:eventSelectors.getEventsByFilter(state),
        filter:eventSelectors.getEventsFilter(state)
    }
};

const mapDispatchToProps = dispatch => {
    return {
        getEventsByFilter: (newFilter) => dispatch(eventActions.getEventsByFilter(newFilter))
    }
};
export default connect(mapStateToProps, mapDispatchToProps)(withStyles(styles)(CustomPaginationActionsTable));