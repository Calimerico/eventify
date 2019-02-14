/**
 * Created by spasoje on 21-Oct-18.
 */
import React, {Component} from 'react';
import Card from './../../components/UI/card/Card';
class Cards extends Component {
    state = {
        personMembers: [
            {
                name: 'Spasoje Petronijevic',
                position: 'Founder',
                description: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry\'' +
                's standard dummy text ever since the 1500s,' +
                'when an unknown printer took a galley of type and scrambled it to make a type specimen book.' +
                'It has survived not only five centuries, but also the leap into electronic typesetting,' +
                'remaining essentially unchanged.',
                picture: 'spasoje',
                icons:{
                    linkedin: 'https://www.linkedin.com/in/spasoje-petronijevi%C4%87-5159438a/',
                    mail: 'mailto:spasojepetronijevic@gmail.com',
                    github:'https://github.com/Calimerico',
                    stackoverflow:'https://stackoverflow.com/users/7313698/spasoje'

                }
            },
            {
                name: 'Milica Predic',
                position: 'Sales manager',
                description: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry\'' +
                's standard dummy text ever since the 1500s,' +
                'when an unknown printer took a galley of type and scrambled it to make a type specimen book.' +
                'It has survived not only five centuries, but also the leap into electronic typesetting,' +
                'remaining essentially unchanged.',
                picture: 'milica',
                icons:{
                    mail: 'mailto:predicmilica@gmail.com'
                }
            },
            {
                name: 'Vidan Drobnjak',
                position: 'Dev ops engineer',
                description: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry\'' +
                's standard dummy text ever since the 1500s,' +
                'when an unknown printer took a galley of type and scrambled it to make a type specimen book.' +
                'It has survived not only five centuries, but also the leap into electronic typesetting,' +
                'remaining essentially unchanged.',
                picture: 'vidan',
                icons:{
                    mail: 'mailto:viksi@gmail.com'
                }
            },
            {
                name: 'Milan Jovanovic',
                position: 'Lead generation manager',
                description: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry\'' +
                's standard dummy text ever since the 1500s,' +
                'when an unknown printer took a galley of type and scrambled it to make a type specimen book.' +
                'It has survived not only five centuries, but also the leap into electronic typesetting,' +
                'remaining essentially unchanged.',
                picture: 'milan',
                icons:{
                    mail: 'mailto:viksi@gmail.com'
                }
            },
            {
                name: 'Jelena Grujic',
                position: 'HR manager',
                description: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry\'' +
                's standard dummy text ever since the 1500s,' +
                'when an unknown printer took a galley of type and scrambled it to make a type specimen book.' +
                'It has survived not only five centuries, but also the leap into electronic typesetting,' +
                'remaining essentially unchanged.',
                picture: 'jeca',
                icons:{
                    mail: 'mailto:viksi@gmail.com'
                }
            }
        ]
    }

    render() {
        return(
            <div style={{marginLeft:'4%',marginRight:'4%', marginTop:'2%'}}>
                {
                    this.state.personMembers.map(member => (
                        <Card member={member}/>
                    ))
                }
            </div>



        )
    }
}

export default Cards;