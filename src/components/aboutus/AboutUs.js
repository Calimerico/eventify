/**
 * Created by spasoje on 21-Oct-18.
 */
import React, {Component} from 'react';
import Cards from './../../containers/cards/Cards';
import {withNamespaces} from 'react-i18next';
const aboutUs = () => {
    const style = {
        position: 'relative',
        top: '10px',
        left: '10px',
        width: '87vw',
        marginLeft: '5%',
        marginRight: '5%',
        textAlign: 'justify',
        textJustify: 'inter-word'
    }
    return (
        <div style={style}>
            <div>
                <h2>Naslov</h2>
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                    when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                    It has survived not only five centuries, but also the leap into electronic typesetting,
                    remaining essentially unchanged. It was popularised in the 1960s with the release of
                    Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing
                    software like Aldus PageMaker including versions of Lorem Ipsum.</p>
            </div>
            <Cards/>
        </div>
    )
}
export default withNamespaces()(aboutUs);

