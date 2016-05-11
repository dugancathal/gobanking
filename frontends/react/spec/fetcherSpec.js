import Fetcher from '../src/client/app/fetcher'

describe('Fetcher', function(){
    
    it('makes GET requests with the correct parameters', function(){
        const fetcher = new Fetcher();

        spyOn(fetcher, '_fetch');

        fetcher.get('/endpoint');

        expect(fetcher._fetch).toHaveBeenCalledWith('/endpoint', 'GET')
    });

    it('makes POST requests with the correct parameters', function(){
        const fetcher = new Fetcher();

        spyOn(fetcher, '_fetch');

        fetcher.post('/something', {foo: 'bar'});

        expect(fetcher._fetch).toHaveBeenCalledWith('/something', 'POST', {foo: 'bar'})
    });
});