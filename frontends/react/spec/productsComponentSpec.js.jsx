import ProductsComponent from '../src/client/app/components/ProductsComponent.js.jsx'
import ReactTestUtils from 'react-addons-test-utils';
import React from 'react';

import {fetcher} from "../src/client/app/fetcher"

describe("Products", function(){
    describe("Retrieving all products", function() {
        it("Can retrieve all products", function() {
            spyOn(fetcher, 'get').and.returnValue(new Promise(function(){}));

            const products = new ProductsComponent();
            products.retrieveAll();
            
            expect(fetcher.get).toHaveBeenCalledWith('/products');
        });
    });

    it('calls #retrieveAll on componentDidMount', function() {
        spyOn(fetcher, 'get').and.callThrough();

        ReactTestUtils.renderIntoDocument(<ProductsComponent/>);

        expect(fetcher.get).toHaveBeenCalledWith('/products');
    });
});