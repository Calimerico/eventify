import {
  primaryColor,
  dangerColor,
  successColor,
  grayColor,
  defaultFont
} from "assets/jss/material-dashboard-react.jsx";

const eventSearchStyle = {
  disabled: {
    "&:before": {
      backgroundColor: "transparent !important"
    }
  },
  underline: {
    "&:hover:not($disabled):before,&:before": {
      borderColor: grayColor[4] + " !important",
      borderWidth: "1px !important"
    },
    "&:after": {
      borderColor: primaryColor[0]
    }
  },
  underlineError: {
    "&:after": {
      borderColor: dangerColor[0]
    }
  },
  underlineSuccess: {
    "&:after": {
      borderColor: successColor[0]
    }
  },
  labelRoot: {
    ...defaultFont,
    color: grayColor[3] + " !important",
    fontWeight: "400",
    fontSize: "14px",
    lineHeight: "1.42857"
  },
  labelRootError: {
    color: dangerColor[0]
  },
  labelRootSuccess: {
    color: successColor[0]
  },
  feedback: {
    position: "absolute",
    top: "18px",
    right: "0",
    zIndex: "2",
    display: "block",
    width: "24px",
    height: "24px",
    textAlign: "center",
    pointerEvents: "none"
  },
  marginTop: {
    marginTop: "16px"
  },
  formControl: {
    marginTop:"20px",
    marginLeft: "20px",
    width: "80%",
    paddingBottom: "10px",
    margin: "27px 0 0 0",
    position: "relative",
    verticalAlign: "unset"
  },
  datePicker: {
    marginLeft: "20px",
    marginBottom: "15px",
    width: "80%"
  },
  textField:{
    marginLeft: "20px",
    marginBottom: "15px",
    width: "70%"
  },
  textField2:{
    marginLeft: "5px",
    marginBottom: "15px",
    width: "70%"
  },
  submit:{
    marginLeft: "20px"
  },
  search:{
    margin:"20px 0px 20px 20px",
    padding: "8px 20px",
  }
};

export default eventSearchStyle;
