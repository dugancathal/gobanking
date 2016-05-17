import React, { Component } from 'react';
import {render} from 'react-dom';
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux'
import thunk from 'redux-thunk'
import { Router, Route, Link, browserHistory } from 'react-router'

import { appReducer } from './reducers/index'
import Products from './components/Products.jsx'
import Bank from './components/Bank.jsx'

let store = createStore(appReducer, applyMiddleware(thunk))

class App extends Component {
    render() {
        return (
            <div className="container">
                <h1>GoBanking</h1>
                <nav className="main-nav">
                    <Link to="/products" className="main-nav--item">Products</Link>
                    <Link to="/bank" className="main-nav--item">Bank</Link>
                </nav>
                {this.props.children}
            </div>
        )
    }
}

render((
    <Provider store={store}>
        <Router history={browserHistory}>
            <Route path="/" component={App}>
                <Route path="products" component={Products}/>
                <Route path="bank" component={Bank}/>
            </Route>
        </Router>
    </Provider>
), document.getElementById('app'))
