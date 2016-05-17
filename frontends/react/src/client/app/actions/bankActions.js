import { dispatch } from 'redux'

import fetcher from '../fetcher'

export const [CREATE_ACCOUNT, SET_ACCOUNT_ID] = ["CREATE_ACCOUNT", "SET_ACCOUNT_ID"]

const setAccountId = (accountId) => {
    return {
        type: SET_ACCOUNT_ID,
        accountId
    }
}

const createAccount = () => {
    return (dispatch) => {
        fetcher.post('banks').then((response) => {
            dispatch(setAccountId(response.body.id))
        })
    }
}

const bankActions = {
    createAccount
}

export default bankActions