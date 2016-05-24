import { SET_ACCOUNT_ID, SET_BALANCE } from '../actions/bankActions'

export const accountId = (state = null, action) => {
    switch (action.type) {
        case SET_ACCOUNT_ID:
            return action.accountId
        default:
            return state
    }
}

export const balance = (state = null, action) => {
    switch (action.type) {
        case
        SET_BALANCE:
            return action.balance
        default:
            return state
    }
}