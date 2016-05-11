export default class Fetcher {
    get (endpoint) {
        this._fetch(endpoint, "GET");
    }
    
    post (endpoint, body) {
        this._fetch(endpoint, "POST", body)
    }
    
    _fetch (endpoint, method, body=null) {
        
    }
    
    _createHeader(method) {
        
    }
}