import {fetcher} from '../src/client/app/fetcher'

describe('Fetcher', function(){
    const spiedOn = {};

    beforeEach(function() {
        spiedOn.fetch = window.fetch;
    });

    afterEach(function() {
        window.fetch = spiedOn.fetch;
    });


    it('makes GET requests with the correct parameters', function(){
        spyOn(fetcher, '_fetch');

        fetcher.get('/endpoint');

        expect(fetcher._fetch).toHaveBeenCalledWith('/endpoint', 'GET')
    });

    it('makes POST requests with the correct parameters', function(){
        spyOn(fetcher, '_fetch');

        fetcher.post('/something', {foo: 'bar'});

        expect(fetcher._fetch).toHaveBeenCalledWith('/something', 'POST', {foo: 'bar'})
    });

    it('makes a request with headers', function() {
        spyOn(window, 'fetch');

        fetcher.post('/something', {foo: 'bar'});

        let expectedHeaders = new Headers();
        expectedHeaders.append("Content-Type", "Application/JSON");

        const expectedParameters = {
            method: 'POST',
            body: {foo: 'bar'},
            headers: expectedHeaders
        };

        expect(window.fetch).toHaveBeenCalledWith('localhost:3000/something', expectedParameters);
    });

    it('sets the content type to application/json', function() {
        const initParameters = fetcher._createInit('GET');

        expect(initParameters.headers.get('Content-Type')).toEqual('Application/JSON');
    })
});