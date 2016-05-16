import ReactTestUtils from 'react-addons-test-utils';
import React from 'react';
import configureMockStore from 'redux-mock-store'
import { Provider } from 'react-redux'

import Products from '../src/client/app/components/Products.jsx'
import fetcher from "../src/client/app/fetcher"

describe("Products", function () {
    describe("when products are present", () => {
        let returnedProducts, component;

        beforeEach(() => {
            returnedProducts = [
                {name: 'Teddy Bear', id: '5', description: 'Soft and cuddly', price: {money: '9.99'}},
                {name: 'Remote Control', id: '12', description: 'Cold and practical', price: {money: '19.99'}}
            ];
            const initialState = {products: returnedProducts};
            const store = configureMockStore();

            component = ReactTestUtils.renderIntoDocument(
                <Provider store={store(initialState)}>
                    <Products />
                </Provider>);
        })

        it('displays a table of products', function () {
            const tableRows = ReactTestUtils.scryRenderedDOMComponentsWithTag(
                component, 'tr'
            );

            expect(tableRows[1].textContent).toMatch(/Teddy Bear/)
            expect(tableRows[1].textContent).toMatch(/Soft and cuddly/)
            expect(tableRows[1].textContent).toMatch(/\$9.99/)

            expect(tableRows[2].textContent).toMatch(/Remote Control/)
            expect(tableRows[2].textContent).toMatch(/Cold and practical/)
            expect(tableRows[2].textContent).toMatch(/\$19.99/)
        })
    })

    describe("when products are not present", () => {
        it("displays a sad message", () => {
            const initialState = {products: undefined};
            const store = configureMockStore();

            const component = ReactTestUtils.renderIntoDocument(
                <Provider store={store(initialState)}>
                    <Products />
                </Provider>);

            const sadMessage = ReactTestUtils.findRenderedDOMComponentWithTag(
                component, 'div'
            )

            expect(sadMessage.textContent).toMatch(/Nothing to see here/)

        })
    })
});