import React, { Component } from 'react';
import { connect } from 'react-redux'

import { getProducts } from '../actions/productActions'

class Products extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(getProducts())
    }

    showProducts() {
        const { products } = this.props

        if (products) {
            return (
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                    { products.map((product) => {
                        return (
                            <tr key={product.id}>
                                <td>{product.name}</td>
                                <td>{product.description}</td>
                                <td>${product.price.money}</td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>
            )
        } else {
            return <div>Nothing to see here.</div>
        }
    }

    render() {
        return this.showProducts()
    }
}

const mapStateToProps = (state) => {
    return {
        products: state.products
    }
}

export default connect(mapStateToProps)(Products)