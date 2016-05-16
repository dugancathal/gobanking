import { combineReducers } from 'redux'
import { products } from './productsReducer'

export const appReducer = combineReducers({products})
