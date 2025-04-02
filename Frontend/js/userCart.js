/*

function increaseQuantity(btn) {
    let quantitySpan = btn.previousElementSibling;
    let quantity = parseInt(quantitySpan.innerText);
    quantity++;
    quantitySpan.innerText = quantity;
    updateTotal(btn, quantity);
}

function decreaseQuantity(btn) {
    let quantitySpan = btn.nextElementSibling;
    let quantity = parseInt(quantitySpan.innerText);
    if (quantity > 1) {
        quantity--;
        quantitySpan.innerText = quantity;
        updateTotal(btn, quantity);
    }
}

function updateTotal(btn, quantity) {
    let price = parseFloat(btn.closest("tr").children[3].innerText.replace("$", ""));
    let totalCell = btn.closest("tr").children[4];
    totalCell.innerText = `$${(quantity * price).toFixed(2)}`;
    updateGrandTotal();
}

function updateGrandTotal() {
    let totalElements = document.querySelectorAll(".total-price");
    let grandTotal = 0;
    totalElements.forEach(el => {
        grandTotal += parseFloat(el.innerText.replace("$", ""));
    });
    document.getElementById("grand-total").innerText = `$${grandTotal.toFixed(2)}`;
}
// Function to get userId from JWT token
*/

function loadCartItems() {
    let token =  localStorage.getItem("token");

    console.log(token)

    if (!token) {
        console.error("please log frist ");
        return;
    }

    $.ajax({
        url: `http://localhost:8080/api/cart/getAll`, // Backend endpoint to get cart items
        type: "GET",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("token")}` // Pass token in header
        },
        success: function (response) {
            console.log("Cart Items Loaded:", response);
            displayCartItems(response.data);
        },
        error: function (xhr, status, error) {
            console.error("Error loading cart items:", error);
        }
    });
}
function displayCartItems(cartItems) {
    let cartTable = $("#cart-items");
    cartTable.empty(); // Clear existing data

    let grandTotal = 0;

    cartItems.forEach(item => {
        let total = item.quantity * item.price;
        grandTotal += total;

        let row = `
            <tr>
                <td><img src="${item.image}" width="70" alt="Product"></td>
                <td>${item.productName}</td>
                <td>
                    <button class="btn btn-sm btn-outline-primary" onclick="updateQuantity(${item.cartId}, ${item.quantity - 1})">-</button>
                    <span class="mx-2">${item.quantity}</span>
                    <button class="btn btn-sm btn-outline-primary" onclick="updateQuantity(${item.cartId}, ${item.quantity + 1})">+</button>
                </td>
                <td>$${item.price.toFixed(2)}</td>
                <td class="total-price">$${total.toFixed(2)}</td>
                <td><button class="btn btn-sm btn-danger" onclick="removeCartItem(${item.cartId})"><i class="fa fa-trash"></i></button></td>
            </tr>
        `;
        cartTable.append(row);
    });

    $("#grand-total").text(`$${grandTotal.toFixed(2)}`);
}
$(document).ready(function () {
    loadCartItems();


});
