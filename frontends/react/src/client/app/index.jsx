import React, { Component } from 'react';
import {render} from 'react-dom';
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux'
import thunk from 'redux-thunk'

import { appReducer } from './reducers/index'
import Products from './components/Products.jsx'
import {getProducts} from './actions/productActions'

let store = createStore(appReducer, applyMiddleware(thunk))

render(
    <Provider store={store}>
        <Products />
    </Provider>,
    document.getElementById('app')
);