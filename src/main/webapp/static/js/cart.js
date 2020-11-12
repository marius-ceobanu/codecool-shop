import {dataHandler} from "./data_handler.js";

function addProduct(evt) {
    let productId = evt.target.parentNode.parentNode.parentNode.dataset["id"];
    dataHandler.addItemToCart(productId, 1, updateCart);
}

function decreaseProduct(evt) {
    let productId = evt.target.parentNode.parentNode.parentNode.dataset["id"];
    dataHandler.decreaseItemFromCart(productId, 1, updateCart);
}

function removeProduct(evt) {
    let productId = evt.target.parentNode.parentNode.dataset["id"];
    dataHandler.removeItemFromCart(productId, updateCart);
}

function getValue(string, field) {
    let sub = string.substring(string.indexOf(field + ": ") + (field + ": ").length);
    let end = sub.indexOf(',');
    return sub.substring(0, end < 0 ? sub.length : end);
}

function refreshProduct(product, productData, quantity) {
    product.children[1].innerText = productData.name;
    product.children[2].innerText = productData.price;
    product.children[4].innerText = quantity;
}

function refreshCart(cart) {
    let children = productContainer.children;

    for (let i = 0; i < children.length; i++) {
        let id = parseInt(children[i].dataset["id"]);

        let found = false;

        for (let item of cart.cartItems) {
            if (item.product.id === id) {
                refreshProduct(children[i], item.product, item.quantity);
                found = true;
            }
        }

        if (!found) {
            productContainer.removeChild(children[i--]);
        }
    }

    checkoutBtn.disabled = children.length === 0;

    refreshCartCount(cart);
    refreshTotal(cart);
}

function refreshCartCount(cart) {
    let count = 0;

    for (let item of cart.cartItems) {
        count += item.quantity;
    }

    cartLabel.hidden = count === 0;

    cartLabel.innerText = count;
}

function refreshTotal(cart) {
    console.log(cart);
    let price = 0;

    for (let item of cart.cartItems) {
        price += item.product.defaultPrice * item.quantity;
    }

    price = Math.round((price + Number.EPSILON) * 100) / 100;
    cartPrice.innerText = price;
    cartTotal.innerText = price;
}

function updateCart() {
    dataHandler.getCart(refreshCart);
}

function init() {
    document.querySelectorAll(".add-btn").forEach(btn => btn.addEventListener("click", addProduct));
    document.querySelectorAll(".decrease-btn").forEach(btn => btn.addEventListener("click", decreaseProduct));
    document.querySelectorAll(".remove-btn").forEach(btn => btn.addEventListener("click", removeProduct));

    productContainer = document.querySelector("#product-container");
    cartLabel = document.querySelector("#lblCartCount");
    cartPrice = document.querySelector("#cart-price");
    cartTotal = document.querySelector("#cart-total");
    checkoutBtn = document.querySelector("#checkout-btn");

    dataHandler.getCart(refreshCartCount);
}

let productContainer;
let cartLabel;

let cartPrice;
let cartTotal;
let checkoutBtn;

init();