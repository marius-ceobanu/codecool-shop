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

function refreshProduct(product, productData, count) {
    product.children[1].innerText = getValue(productData, "name");

    let price = getValue(productData, "defaultPrice");
    price = price.substring(0, price.indexOf('.') + 2);
    product.children[2].innerText = price + " " + getValue(productData, "defaultCurrency");

    product.children[4].innerText = count;
}

function refreshCart(data) {
    let children = productContainer.children;
    for (let i = 0; i < children.length; i++) {
        let id = children[i].dataset["id"];

        let productData;
        let count;

        for (let k in data) {
            if (data.hasOwnProperty(k)) {
                if (getValue(k, "id") === id) {
                    productData = k;
                    count = data[k];
                    break;
                }
            }
        }

        if (productData != null) {
            refreshProduct(children[i], productData, count);
        } else {
            productContainer.removeChild(children[i]);
            i--;
        }
    }

    console.log(children.length);
    checkoutBtn.disabled = children.length === 0;

    refreshCartCount(data);
}

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

function updateCart() {
    dataHandler.getCart(refreshCart);
}

function init() {
    document.querySelectorAll(".add-btn").forEach(btn => btn.addEventListener("click", addProduct));
    document.querySelectorAll(".decrease-btn").forEach(btn => btn.addEventListener("click", decreaseProduct));
    document.querySelectorAll(".remove-btn").forEach(btn => btn.addEventListener("click", removeProduct));

    productContainer = document.querySelector("#product-container");
    cartLabel = document.querySelector("#lblCartCount");
    checkoutBtn = document.querySelector("#checkout-btn");

    console.log(checkoutBtn);

    dataHandler.getCart(refreshCartCount);
}

let productContainer;
let cartLabel;

let checkoutBtn;

init();