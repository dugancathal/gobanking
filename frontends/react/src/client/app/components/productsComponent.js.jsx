import React, { Component } from 'react';
import {fetcher} from '../fetcher'

export default class ProductsComponent extends Component {
    componentDidMount() {
        this.retrieveAll();
    }

    retrieveAll() {
        fetcher.get('/products').then(function(products) {
            this.props.all = products;
        });
    }

    render() {
        return <div>Implement me!</div>
    }
}
