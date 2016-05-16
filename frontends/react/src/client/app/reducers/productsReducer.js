import { SET_PRODUCTS } from '../actions/productActions'

export const products = (state = [], action) => {
    switch (action.type) {
        case SET_PRODUCTS:
            return action.products
        default:
            return state
    }
}