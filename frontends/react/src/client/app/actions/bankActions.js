import { dispatch } from 'redux'

import fetcher from '../fetcher'

export const [CREATE_ACCOUNT, SET_ACCOUNT_ID, SET_BALANCE] = ["CREATE_ACCOUNT", "SET_ACCOUNT_ID", "SET_BALANCE"]

const setAccountId = (accountId) => {
    return {
        type: SET_ACCOUNT_ID,
        accountId
    }
}

const setBalance = (balance) => {
    return {
        type: SET_BALANCE,
        balance
    }
}

const createAccount = () => {
    return (dispatch) => {
        fetcher.post('banks').then((response) => {
            dispatch(setAccountId(response.body.id))
        })
    }
}

const createDeposit = (id, amount) => {
    return (dispatch) => {
        fetcher.post(`/banks/${id}/deposit`, {amount: amount}).then((depositResponse) => {
            if (depositResponse.success) {
                fetcher.get(`/banks/${id}`).then((getResponse) => {

                })
            }
        })
    }
}

const bankActions = {
    createAccount,
    createDeposit
}

export default bankActions