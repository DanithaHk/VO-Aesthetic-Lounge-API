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
    let token = localStorage.getItem("token");

    if (!token) {
        console.error("User is not logged in. Token is missing.");
        return;
    }

    console.log("🚀 Sending Token:", token); // Check if token is being sent

    $.ajax({
        url: "http://localhost:8080/api/v1/cart/getAll",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (response) {
            console.log("✅ Cart Items Loaded:", response);
            displayCartItems(response.data);
        },
        error: function (xhr, status, error) {
            console.error("❌ Error loading cart items:", xhr.status, xhr.responseText || error);
        }
    });
}

/*function displayCartItems(cartItems) {
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
}*/
function displayCartItems(cartItems) {
    let cartTable = $("#cart-items");
    cartTable.empty(); // Clear existing data

    let grandTotal = 0;

    cartItems.forEach(item => {
        let total = item.quantity * item.price;
        grandTotal += total;

        let row = $("<tr>");

        // Securely add image
        let img = $("<td>").append(
            $("<img>").attr("src", item.image).attr("width", "70").attr("alt", "Product")
        );

        let productName = $("<td>").text(item.productName); // Escape text

        let quantityCell = $("<td>").append(
            $("<button>")
                .addClass("btn btn-sm btn-outline-primary")
                .text("-")
                .click(() => updateQuantity(item.cartId, Math.max(1, item.quantity - 1))) // Prevent negative values
        ).append(
            $("<span>").addClass("mx-2").text(item.quantity)
        ).append(
            $("<button>")
                .addClass("btn btn-sm btn-outline-primary")
                .text("+")
                .click(() => updateQuantity(item.cartId, item.quantity + 1))
        );

        let priceCell = $("<td>").text(`$${item.price.toFixed(2)}`);

        let totalCell = $("<td>").addClass("total-price").text(`$${total.toFixed(2)}`);

        let removeButton = $("<td>").append(
            $("<button>")
                .addClass("btn btn-sm btn-danger")
                .html('<i class="fa fa-trash"></i>')
                .click(() => removeCartItem(item.cartId))
        );

        // Append all cells to row
        row.append(img, productName, quantityCell, priceCell, totalCell, removeButton);
        cartTable.append(row);
    });

    $("#grand-total").text(`$${grandTotal.toFixed(2)}`);
}

$(document).ready(function () {
    loadCartItems();


});
