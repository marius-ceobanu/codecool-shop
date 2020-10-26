import { dataHandler } from "./data_handler.js";

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

function refreshProduct(product, productData, count) {
    console.log(productData);
    // product.children[1].innerText = productData.name;
}

function refreshCart(data) {
    let children = productContainer.children;
    for (let i = 0; i < children.length; i++) {
        let id = children[i].dataset["id"];

        let productData;
        let count;

        for (let k in data) {
            if (data.hasOwnProperty(k)) {
                // console.log(k);
                // console.log(k.substring(4, k.indexOf(',')));
                if (k.substring(4, k.indexOf(',')) === id) {
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
}

function updateCart(data) {
    dataHandler.getCart(refreshCart);
}

function init() {
    document.querySelectorAll(".add-btn").forEach(btn => btn.addEventListener("click", addProduct));
    document.querySelectorAll(".decrease-btn").forEach(btn => btn.addEventListener("click", decreaseProduct));
    document.querySelectorAll(".remove-btn").forEach(btn => btn.addEventListener("click", removeProduct));

    productContainer = document.querySelector("#product-container");
}

let productContainer;

init();