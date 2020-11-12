import {dataHandler} from "./data_handler.js";

function refreshCartCount(cart) {
    let count = 0;

    for (let item of cart.cartItems) {
        count += item.quantity;
    }

    cartLabel.hidden = count === 0;
    cartLabel.innerText = count;
}

function updateCartCount() {
    dataHandler.getCart(refreshCartCount)
}

function addToCart(evt) {
    let id = evt.target.dataset["id"];
    dataHandler.addItemToCart(id, 1, updateCartCount);
}

function init() {
    document.querySelectorAll(".add-btn").forEach(btn => btn.addEventListener("click", addToCart));

    cartLabel = document.querySelector("#lblCartCount");
    updateCartCount();
}

let cartLabel;

init();