import React, { Component } from 'react'
import { connect } from 'react-redux'

import bankActions from '../actions/bankActions'

export class Bank extends Component {
    constructor(props) {
        super(props)
        this.createAccount = this.createAccount.bind(this)
    }

    createAccount() {
        this.props.dispatch(bankActions.createAccount())
    }

    showAccountInformation() {
        const {accountId} = this.props
        if (accountId) {
            return <p>Account ID: {accountId}</p>
        } else {
            return <button onClick={this.createAccount}>Create a new account</button>
        }
    }

    render() {
        return (
            <div>
                {this.showAccountInformation()}
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        accountId: state.accountId
    }
}

const ConnectedBank = connect(mapStateToProps)(Bank)
export default ConnectedBank