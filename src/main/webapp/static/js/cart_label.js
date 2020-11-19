import {dataHandler} from "./data_handler.js";

function refreshCartCount(cart) {
    let count = 0;

    for (let item of cart.cartItems) {
        count += item.quantity;
    }

    cartLabel.hidden = count === 0;
    cartLabel.innerText = count;
}

function init() {
    cartLabel = document.querySelector("#lblCartCount");

    dataHandler.getCart(refreshCartCount);
}

let cartLabel;

init();