/**
 * Created by spasoje on 21-Oct-18.
 */
import React, {Component} from 'react';
class Card extends Component {

    render() {
        const styleCard = {
            display: 'inline-block',
            width: '300px',
            fontFamily: '\'Montserrat\', sans-serif',
            marginLeft: '6px',
            marginBottom: '5px'

        }
        const styleCardImage = {
            width: '100%',
            height:'260px',
            display: 'inline-block'
        }
        const styleCardBody = {
            padding: '10px',
            borderRadius: '7px',
            border: '1px gray solid'
        }
        const styleCardIcons = {
            width:'100%',
            textAlign:'right',
            paddingBottom: '5px'
        }
        return(
            <div style={styleCard}>
                <div style={styleCardImage}>
                    <img style={{width: '100%',height: '105%'}} src={require('./../../../resources/pictures/' + this.props.member.picture + '.jpg')} />
                </div>
                <div style={styleCardBody}>
                    <h3>{this.props.member.name}</h3>
                    <h4>{this.props.member.position}</h4>
                    <p>{this.props.member.description}</p>
                    <div style={styleCardIcons}>
                        <a  href="" target="_blank" style={{color: 'black',display:'inline-block', marginLeft:'2px'}}><i className="fa fa-linkedin-square" style={{fontSize:'24px'}}></i></a>
                        <a  href="" target="_blank" style={{color: 'black',display:'inline-block', marginLeft:'2px'}}><i className="fa fa-github" style={{fontSize:'24px'}}></i></a>
                        <a  href="" target="_blank" style={{color: 'black',display:'inline-block', marginLeft:'2px'}}><i className="fa fa-stack-overflow" style={{fontSize:'24px'}}></i></a>
                        <a  href="" target="_blank" style={{color: 'black',display:'inline-block', marginLeft:'2px'}}><i className="fa fa fa-google" style={{fontSize:'24px'}}></i></a>
                    </div>
                </div>

            </div>
        )
    }
}

export default Card;