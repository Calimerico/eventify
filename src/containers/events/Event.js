/**
 * Created by spasoje on 02-Feb-19.
 */

import React from 'react';

class Event extends React.Component {

    render() {
        return "Proba " + this.props.match.params.id;
    }
}

export default Event;