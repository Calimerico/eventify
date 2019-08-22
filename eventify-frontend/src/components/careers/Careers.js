/**
 * Created by spasoje on 21-Oct-18.
 */
import React from 'react';
import {translate} from 'react-i18next';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import Input from '@material-ui/core/Input';
import { makeStyles } from '@material-ui/styles';

const useStyles = makeStyles({
    root: {
        width:"50%",
        marginLeft: "25%",
        marginTop: "2%",
        fontSize:"200px"
    },
});
const careers = () => {
    const classes = useStyles();
    return(
        <FormControl className={classes.root}>
            <InputLabel htmlFor="my-input">Email address</InputLabel>
            <Input id="my-input"/>
        </FormControl>

    )
}

export default translate()(careers);