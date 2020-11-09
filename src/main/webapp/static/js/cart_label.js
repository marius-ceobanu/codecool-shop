import { dataHandler } from "./data_handler.js";

function refreshCartCount(cart) {
    let count = 0;

    for (let k in cart) {
        if (cart.hasOwnProperty(k)) {
            count += cart[k];
        }
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