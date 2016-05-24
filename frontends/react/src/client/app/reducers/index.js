import { combineReducers } from 'redux'
import { products } from './productsReducer'
import { accountId, balance } from './bankReducer'

export const appReducer = combineReducers({products, accountId, balance})
