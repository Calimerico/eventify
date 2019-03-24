import React, {Component} from "react";
import Grid from '@material-ui/core/Grid';
import PropTypes from "prop-types";

class Layout extends Component {

    render() {
        const { header, main, sidebar, footer } = this.props;

        return (<React.Fragment>
                {header}

                <Grid container direction="row" customStyle={{margin: "0px", padding: "8px 20px 8px 8px"}} spacing={0}>
                    <Grid item md={2} lg={2} xl={2}>
                        {sidebar}
                    </Grid>
                    <Grid item sm={12} xs={12} md={sidebar ? 10 : 12} lg={sidebar ? 10 : 12} xl={sidebar ? 10 : 12} customStyle={{padding: "8px"}}>
                        {main}
                    </Grid>
                </Grid>
                {footer}
            </React.Fragment>
        );
    }
}

Layout.propTypes = {
    header: PropTypes.node,
    main: PropTypes.node,
    sidebar: PropTypes.node,
    footer: PropTypes.node
};

export default Layout;