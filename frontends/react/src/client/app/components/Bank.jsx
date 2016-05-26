import React, { Component } from 'react'
import { connect } from 'react-redux'

import bankActions from '../actions/bankActions'

export class Bank extends Component {
    constructor(props) {
        super(props)
        this.state = { deposit: 0 };
        this.createAccount = this.createAccount.bind(this)
        this.createDeposit = this.createDeposit.bind(this)
        this.handleDepositChange = this.handleDepositChange.bind(this)
    }

    createAccount() {
        this.props.dispatch(bankActions.createAccount())
    }

    createDeposit(e) {
        e.preventDefault();
        this.props.dispatch(
            bankActions.createDeposit(
              this.props.accountId,
              this.state.deposit
            )
          )
    }

    handleDepositChange(e) {
      this.setState({deposit: parseFloat(e.target.value)});
    }

    showAccountInformation() {
        const {accountId, balance} = this.props
        if (accountId) {
            return (
                <div>
                    <p className="account--id">Account ID: {accountId}</p>
                    <p className="account--balance">Account balance: ${balance}</p>

                    <form className="deposit" onSubmit={this.createDeposit}>
                        <h3>Make a Deposit</h3>
                        <label htmlFor="depositAmount">Deposit Amount</label>
                        <input className="deposit--amount" ref="depositAmount" id="depositAmount" onChange={this.handleDepositChange}/>
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
