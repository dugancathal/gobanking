import {dispatch} from 'redux'

import fetcher from '../fetcher'

export const [GET_PRODUCTS, SET_PRODUCTS] = ['GET_PRODUCTS', 'SET_PRODUCTS']

const setProducts = (products) => {
    return {
        type: SET_PRODUCTS,
        products
    }
}

export const getProducts = () => {
    return (dispatch) => {
        fetcher.get('products').then((response) => {
            dispatch(setProducts(response.body))
        })
    }
}