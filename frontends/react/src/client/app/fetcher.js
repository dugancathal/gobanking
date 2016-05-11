export const fetcher = {
    get: function (endpoint) {
        return this._fetch(endpoint, "GET");
    },
    
    post: function (endpoint, body) {
        return this._fetch(endpoint, "POST", body)
    },


    _fetch: function (endpoint, method, body=null) {
        const RAILS_URL = 'localhost:3000';
        return window.fetch(RAILS_URL + endpoint, this._createInit(method, body))
    },

    _createInit: function(method, body) {
        let init = {
            method: method,
            headers: this._createHeaders()
        };

        if (body !== null) {
            init.body = body;
        }

        return init;
    },

    _createHeaders: function() {
        let headers = new Headers();
        headers.append("Content-Type", "Application/JSON");
        return headers;
    }
}
