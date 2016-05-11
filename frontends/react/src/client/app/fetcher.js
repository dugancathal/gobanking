export default class Fetcher {
    get (endpoint) {
        this._fetch(endpoint, "GET");
    }
    
    post (endpoint, body) {
        this._fetch(endpoint, "POST", body)
    }
    
    _fetch (endpoint, method, body=null) {
        window.fetch(endpoint, this._createInit(method, body))
    }
    
    _createInit(method, body) {
        let init = {
            method: method,
            headers: this._createHeaders()
        };
        
        if (body !== null) {
            init.body = body;
        }
        
        return init;
    }

    _createHeaders() {
        let headers = new Headers();
        headers.append("Content-Type", "Application/JSON");
        return headers;
    }
}