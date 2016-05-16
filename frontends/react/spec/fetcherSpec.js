import fetcher from '../src/client/app/fetcher'

describe('Fetcher', function(){
    it('makes GET requests with the correct parameters', function(){
        spyOn(fetcher, '_fetch');

        fetcher.get('endpoint');

        expect(fetcher._fetch).toHaveBeenCalledWith('endpoint', 'GET')
    });

    it('makes POST requests with the correct parameters', function(){
        spyOn(fetcher, '_fetch');

        fetcher.post('something', {foo: 'bar'});

        expect(fetcher._fetch).toHaveBeenCalledWith('something', 'POST', {foo: 'bar'})
    });

    it('sets the content type to application/json', function() {
        const initParameters = fetcher._createInit('GET');

        expect(initParameters.headers.get('Content-Type')).toEqual('Application/JSON');
    })
});