import { combineReducers } from 'redux'
import { products } from './productsReducer'
import { accountId } from './bankReducer'

export const appReducer = combineReducers({products, accountId})
