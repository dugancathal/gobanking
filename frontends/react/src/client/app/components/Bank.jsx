import React, { Component } from 'react'
import { connect } from 'react-redux'

import bankActions from '../actions/bankActions'

export class Bank extends Component {
    constructor(props) {
        super(props)
        this.createAccount = this.createAccount.bind(this)
        this.createDeposit = this.createDeposit.bind(this)
    }

    createAccount() {
        this.props.dispatch(bankActions.createAccount())
    }

    createDeposit(amount) {
        this.props.dispatch(bankActions.createDeposit(this.props.accountId, amount))
    }

    showAccountInformation() {
        const {accountId, balance} = this.props
        if (accountId) {
            return (
                <div>
                    <p className="account--id">Account ID: {accountId}</p>
                    <p className="account--balance">Account balance: {balance}</p>

                    <form className="deposit">
                        <h3>Make a Deposit</h3>
                        <input className="deposit--amount" ref="depositAmount"/>
                        <button className="deposit--button" ref="depositButton">Deposit</button>
                    </form>
                </div>
            )
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
        accountId: state.accountId,
        balance: state.balance
    }
}

const ConnectedBank = connect(mapStateToProps)(Bank)
export default ConnectedBank