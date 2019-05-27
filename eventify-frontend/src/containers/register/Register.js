/**
 * Created by spasoje on 30-Oct-18.
 */
import React, {Component} from 'react';
import {translate} from 'react-i18next';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import LockIcon from '@material-ui/icons/LockOutlined';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import PropTypes from 'prop-types';
import authActions from '../../redux/auth/actions'
import { withStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {
            registerForm: {
                email: {
                    elementType: 'input',
                    elementConfig: {
                        type: 'email',
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
                firstName: {
                    elementType: 'input',
                    elementConfig: {
                        type: 'text',
                    },
                    value: '',
                    label: 'firstNameLabel'
                    // TODO NE menja se labela kad promenim jezik, treba da se pozove neki setState ili tako nesto. Jedno resenje je da gore stavim label: 'labelName'
                },
                lastName: {
                    elementType: 'input',
                    elementConfig: {
                        type: 'text',
                    },
                    value: '',
                    label: 'lastNameLabel'
                },
            },
            newUser: {
                email:null,
                username:null,
                password:null,
                firstName:null,
                lastName:null,
                sex:null,
            }



        };
        this.onEmailChange = this.onEmailChange.bind(this);
        this.onUserNameChange = this.onUserNameChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
        this.onFirstNameChange = this.onFirstNameChange.bind(this);
        this.onLastNameChange = this.onLastNameChange.bind(this);
        this.onSexChange = this.onSexChange.bind(this);
        this.onRegister = this.onRegister.bind(this);
    }


    //TODO Try to improve this onxyzChange handling, all of them do the same thing...try something [name]: name
    onEmailChange = (event) => {
        this.setState({...this.state, newUser: {...this.state.newUser, email:event.target.value}});
    };

    onUserNameChange = (event) => {
        this.setState({...this.state, newUser: {...this.state.newUser, username:event.target.value}});
    };

    onPasswordChange = (event) => {
        this.setState({...this.state, newUser: {...this.state.newUser, password:event.target.value}});
    };

    onFirstNameChange = (event) => {
        this.setState({...this.state, newUser: {...this.state.newUser, firstName:event.target.value}});
    };

    onLastNameChange = (event) => {
        this.setState({...this.state, newUser: {...this.state.newUser, lastName:event.target.value}});
    };

    onSexChange = (event) => {//TODO This should be select field, not string input
        this.setState({...this.state, newUser: {...this.state.newUser, sex:event.target.value}});
    };

    onRegister = (event) => {
        event.preventDefault();//TODO Prevent refreshing page(and refreshing state)
        this.props.register(this.state.newUser);
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
        //TODO The same as in login form, use these, dont hardcode fields!
        const formInputs = formElementsArray.map(element => (
            <Input  key={element.id} elementType={element.config.elementType}
                   elementConfig={element.config.elementConfig} value={element.config.value} label={element.config.label}/>
        ))
        return(
            <main className={classes.main}>
                <CssBaseline />
                <Paper className={classes.paper}>
                    <Avatar className={classes.avatar}>
                        <LockIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Register
                    </Typography>
                    <form onSubmit={this.onRegister} className={classes.form}>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="email">Email Address</InputLabel>
                            <Input value={this.state.email} onChange={this.onEmailChange} id="email" name="email" type="email" autoFocus />
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="userName">Username</InputLabel>
                            <Input value={this.state.userName} onChange={this.onUserNameChange} name="userName" id="userName" />
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="password">Password</InputLabel>
                            <Input value={this.state.password} onChange={this.onPasswordChange} name="password" type="password" id="password"/>
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="firstName">First name</InputLabel>
                            <Input value={this.state.firstName} onChange={this.onFirstNameChange} name="firstName" id="firstName"/>
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="lastName">Last name</InputLabel>
                            <Input value={this.state.lastName} onChange={this.onLastNameChange} name="lastName" id="lastName"/>
                        </FormControl>
                        <FormControl margin="normal" fullWidth>
                            <InputLabel className={classes.formLabel} htmlFor="sex">Sex</InputLabel>
                            <Input value={this.state.sex} onChange={this.onSexChange} name="sex" id="sex"/>
                        </FormControl>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className={classes.submit}
                        >
                            Register
                        </Button>
                    </form>
                </Paper>
            </main>

        )
    }
}
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

Register.propTypes = {
    classes: PropTypes.object.isRequired,
};

const mapStateToProps = state => {
    return {

    }
};

const mapDispatchToProps = dispatch => {
    return {
        register: (newUser) => dispatch(authActions.register(newUser))
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(withStyles(styles)(translate()(Register)));