import ReactTestUtils from 'react-addons-test-utils'
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

            const p = ReactTestUtils.findRenderedDOMComponentWithTag(component, 'p')

            expect(p).not.toBeNull()
            expect(p.textContent).toMatch(/Account ID: 12/)
        })
    })
})
