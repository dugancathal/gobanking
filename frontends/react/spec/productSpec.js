import ProductComponent from '../src/client/app/components/productComponent.js.jsx'

describe("Products", function(){
    describe("Retrieving all products", function() {
        it("Can retrieve all products", function() {
            const product = new ProductComponent();
            
            product.retrieveAll();
            
            expect(product.all).toEqual(['some array of products']);
        });
    });
});