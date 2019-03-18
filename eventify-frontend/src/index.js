import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './containers/app/App';
import * as serviceWorker from './serviceWorker';
import { Provider } from 'react-redux';
import { createStore, combineReducers,applyMiddleware,compose } from 'redux';
import './i18';
import {I18nextProvider} from 'react-i18next';
import thunk from 'redux-thunk';
import logger from 'redux-logger'
import i18n from './i18';
import eventReducer from './redux/event/reducer'
import scrapReducer from './redux/scrap/reducer'
import authReducer from './redux/auth/reducer'


const rootReducer = combineReducers({
    eventsReducer: eventReducer,
    scrapReducer:scrapReducer,
    authReducer:authReducer
});

const store = compose(applyMiddleware(thunk,logger))(createStore)(rootReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

ReactDOM.render( <I18nextProvider i18n={ i18n } ><Provider store={store}><App /></Provider></I18nextProvider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
