export let dataHandler = {
    _get: function (url, callback) {
        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
        .then(response => response.json())
        .then(response => callback(response));
    },
    _post: function (url, data, callback) {
        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => callback(response));
    },
    getCart: function (callBack) {
        this._get("/api/cart", callBack);
    },
    addItemToCart: function (productId, quantity, callback) {
        this._post("/api/cart/add", {id: productId, quantity: quantity}, callback);
    },
    decreaseItemFromCart: function (productId, quantity, callback) {
        this._post("/api/cart/decrease", {id: productId, quantity: quantity}, callback);
    },
    removeItemFromCart: function (productId, callback) {
        this._post("/api/cart/remove", {id: productId}, callback);
    }
}