import { SET_ACCOUNT_ID } from '../actions/bankActions'

export const accountId = ( state = null, action) => {
    switch (action.type) {
        case SET_ACCOUNT_ID:
            return action.accountId
        default:
            return state
    }
}