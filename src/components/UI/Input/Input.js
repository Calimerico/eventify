/**
 * Created by spasoje on 20-Oct-18.
 */
import React from 'react';
import {FormGroup, Col, FormControl, Checkbox, ControlLabel} from 'react-bootstrap';
import {translate} from 'react-i18next';
const input = (props) => {
    let inputElement = null;
    const {t} = props;
    switch (props.elementType) {
        case ( 'input' ):
            inputElement = <FormGroup controlId ={props.key}>
                <Col componentClass={ControlLabel} sm={2}>
                    {t('labels.' + props.label)}
                </Col>
                <Col sm={10}>
                    <FormControl {...props.elementConfig} value={props.value}/>
                </Col>
            </FormGroup>;
            break;
            // TODO This text area should be converted to react bootstrap
        case ( 'textarea' ):
            inputElement = <textarea
                {...props.elementConfig}
                value={props.value} />;
            break;
        case ( 'select' ):
            inputElement = <FormGroup controlId ={props.key}>
                <Col componentClass={ControlLabel} sm={2}>
                    {t('labels.' + props.label)}
                </Col>
                <Col sm={10}>
                    <FormControl componentClass="select" placeholder={props.elementConfig.placeholder}>
                        {props.elementConfig.options.map(option => (
                            <option key={option.value} value={option.value}>
                                {option.displayValue}
                            </option>
                        ))}
                    </FormControl>
                </Col>
            </FormGroup>;
            break;
        case ( 'checkbox' ):
            inputElement = <FormGroup>
                <Col smOffset={2}>
                    <Checkbox>{t('labels.' + props.label)}</Checkbox>
                </Col>
                </FormGroup>;
            break;
        default:
            inputElement = <FormGroup controlId ={props.key}>
                <Col componentClass={ControlLabel} sm={2}>
                    {t('labels.' + props.label)}
                </Col>
                <Col sm={10}>
                    <FormControl {...props.elementConfig} value={props.value}/>
                </Col>
            </FormGroup>;

    }

    return (
        <div>
            {inputElement}
        </div>
    )
}

export default translate()(input);