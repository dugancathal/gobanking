import ReactTestUtils from 'react-dom/test-utils'
import React from 'react'
import configureMockStore from 'redux-mock-store'
import { Provider } from 'react-redux'

import ConnectedBank, { Bank } from '../src/client/app/components/Bank.jsx'
import bankActions from '../src/client/app/actions/bankActions'

describe("Bank", () => {
    describe("Creating a new bank account", () => {
        it("dispatches an action to create a new account", () => {
            const bankComponent = new Bank()

            const dispatchSpy = jasmine.createSpy()
            bankComponent.props = {dispatch: dispatchSpy}
            spyOn(bankActions, 'createAccount')

            bankComponent.createAccount()

            expect(dispatchSpy).toHaveBeenCalled()
            expect(bankActions.createAccount).toHaveBeenCalled()
        })

        it("displays a button to create an account if no account is present", () => {
            const initialState = {accountId: null};
            const store = configureMockStore();

            const component = ReactTestUtils.renderIntoDocument(
                <Provider store={store(initialState)}>
                    <ConnectedBank />
                </Provider>);

            const button = ReactTestUtils.findRenderedDOMComponentWithTag(component, 'button')

            expect(button).not.toBeNull()
            expect(button.textContent).toEqual("Create a new account")
        })

        it("displays account ID if account is present", () => {
            const initialState = {accountId: "12"}
            const store = configureMockStore()

            const component = ReactTestUtils.renderIntoDocument(
                <Provider store={store(initialState)}>
                    <ConnectedBank />
                </Provider>
            )

            const accountIdElement = ReactTestUtils.findRenderedDOMComponentWithClass(component, 'account--id')

            expect(accountIdElement).not.toBeNull()
            expect(accountIdElement.textContent).toMatch(/Account ID: 12/)
        })

        it("displays balance if account is present", () => {
            const initialState = {accountId: "12", balance: 150}
            const store = configureMockStore()

            const component = ReactTestUtils.renderIntoDocument(
                <Provider store={store(initialState)}>
                    <ConnectedBank />
                </Provider>
            )

            const balanceElement = ReactTestUtils.findRenderedDOMComponentWithClass(component, 'account--balance')

            expect(balanceElement).not.toBeNull()
            expect(balanceElement.textContent).toMatch(/150/)
        })
    })

    describe("Depositing money into a bank account", () => {
        it("Displays a deposit money form if an account is present", () => {
            const initialState = {accountId: "12"}
            const store = configureMockStore()

            const component = ReactTestUtils.renderIntoDocument(
                <Provider store={store(initialState)}>
                    <ConnectedBank />
                </Provider>
            )
            const form = ReactTestUtils.findRenderedDOMComponentWithClass(component, 'deposit')
            expect(form).not.toBeNull()
            expect(form.textContent).toMatch(/Make a Deposit/)

            const input = ReactTestUtils.findRenderedDOMComponentWithClass(component, 'deposit--amount')
            expect(input).not.toBeNull()

            const button = ReactTestUtils.findRenderedDOMComponentWithClass(component, 'deposit--button')
            expect(button).not.toBeNull()
            expect(button.textContent).toMatch(/Deposit/)
        })
    })

    describe("#createDeposit", () => {
        it("dispatches an action to create a deposit", () => {
            const bankComponent = new Bank()

            const dispatchSpy = jasmine.createSpy()
            bankComponent.props = {dispatch: dispatchSpy, accountId: 'myId'}
            spyOn(bankActions, 'createDeposit')

            bankComponent.state = {deposit: 9001}
            bankComponent.createDeposit(new Event())

            expect(dispatchSpy).toHaveBeenCalled()
            expect(bankActions.createDeposit).toHaveBeenCalledWith('myId', 9001)
        })
    })
})
