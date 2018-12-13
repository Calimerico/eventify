/**
 * Created by spasoje on 30-Oct-18.
 */
import React, {Component} from 'react';
import {translate} from 'react-i18next';

import {Form, Col} from 'react-bootstrap';
import axios from './../../axiosBase';
import PropTypes from 'prop-types';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import LockIcon from '@material-ui/icons/LockOutlined';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import authActions from './../../redux/auth/actions'
import { connect } from 'react-redux';


const styles = theme => ({
    main: {
        width: 'auto',
        display: 'block', // Fix IE 11 issue.
        marginLeft: theme.spacing.unit * 3,
        marginRight: theme.spacing.unit * 3,
        [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
            width: 400,
            marginLeft: 'auto',
            marginRight: 'auto',
        },
    },
    paper: {
        marginTop: theme.spacing.unit * 8,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        padding: `${theme.spacing.unit * 2}px ${theme.spacing.unit * 3}px ${theme.spacing.unit * 3}px`,
    },
    avatar: {
        margin: theme.spacing.unit,
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing.unit
    },
    formLabel: {
        fontSize:13
    },
    submit: {
        marginTop: theme.spacing.unit * 3,
        fontSize:14
    },
});

class Login extends Component {
    //TODO Use this SignIn component when you learn react better! https://material-ui.com/getting-started/page-layout-examples/
    constructor(props) {
        super(props);
        this.state = {
            loginForm: {
                email: {
                    elementType: 'input',
                    elementConfig: {
                        type: 'text',
                    },
                    value: '',
                    label: 'emailLabel'
                },
                password: {
                    elementType: 'input',
                    elementConfig: {
                        type: 'password',
                    },
                    value: '',
                    label: 'passwordLabel'
                },
                rememberMe: {
                    elementType: 'checkbox',
                    value: '',
                    label: 'rememberMeLabel'
                }
            },
            email:null,
            password:null,

        };
        this.inputChangedHandler = this.inputChangedHandler.bind(this);
        this.onEmailChange = this.onEmailChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
        this.onLogin = this.onLogin.bind(this);
    }

    inputChangedHandler = (event, inputIdentifier) =>{
        const updatedLoginForm = {...this.state.loginForm};
        const updatedFormElement = {...updatedLoginForm[inputIdentifier]};
        updatedFormElement.value = event.target.value;
        updatedLoginForm[inputIdentifier] = updatedFormElement;
        this.setState({
            loginForm: updatedLoginForm
        });
    };

    onLogin = (event) => {
        event.preventDefault();//TODO Prevent refreshing page(and refreshing state)
        this.props.login(this.state.email,this.state.password);
    };

    onEmailChange = (event) => {
        debugger;
        this.setState({...this.state, email:event.target.value});
    };

    onPasswordChange = (event) => {
        this.setState({...this.state, password:event.target.value});
    };

    render() {
        const { classes } = this.props;
        const formElementsArray = [];
        for (let key in this.state.loginForm) {
            formElementsArray.push({
                id: key,
                config: this.state.loginForm[key]
            })
        }
        //TODO Use these form inputs to dynamically create form, don't hardcode it!
        const formInputs = formElementsArray.map(element => (
            <Input  key={element.id}
                    classes={classes}
                    elementType={element.config.elementType}
                    elementConfig={element.config.elementConfig}
                    value={element.config.value}
                    label={element.config.label}
                    changed={event => this.inputChangedHandler(event, element.id)}
            />
        ))
        return(
            <main className={classes.main}>
                <CssBaseline />
                <Paper className={classes.paper}>
                    <Avatar className={classes.avatar}>
                        <LockIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <form onSubmit={this.onLogin} className={classes.form}>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="email">Email Address</InputLabel>
                            <Input value={this.state.email} onChange={this.onEmailChange} id="email" name="email" autoComplete="email" autoFocus />
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="password">Password</InputLabel>
                            <Input value={this.state.password} onChange={this.onPasswordChange} name="password" type="password" id="password" autoComplete="current-password" />
                        </FormControl>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className={classes.submit}
                        >
                            Sign in
                        </Button>
                    </form>
                </Paper>
            </main>

        )
    }
}

Login.propTypes = {
    classes: PropTypes.object.isRequired,
};

const mapStateToProps = state => {
    return {

    }
};

const mapDispatchToProps = dispatch => {
    return {
        login: (username,password) => dispatch(authActions.login(username,password))
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(withStyles(styles)(translate()(Login)));