import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './containers/app/App';
import * as serviceWorker from './serviceWorker';
import eventsReducer from './store/events/reducer';
import { Provider } from 'react-redux';
import { createStore, combineReducers } from 'redux';
import './i18';
import {I18nextProvider} from 'react-i18next';
import i18n from './i18';

const rootReducer = combineReducers({
    eventsReducer: eventsReducer
});

const store = createStore(rootReducer);

ReactDOM.render( <I18nextProvider i18n={ i18n } ><Provider store={store}><App /></Provider></I18nextProvider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
