let imgUrl2= "images/Product/";
console.log(typeof jQuery);

function getProducts() {
    let token = localStorage.getItem("token");
    if (!token) {
        alert("log first")
    } else {
        console.log("Token found:", token);
    }
    $.ajax({
        url: "http://localhost:8080/api/v1/product/getAll",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: (res) => {
            console.log(res);
            products = res.data;
            let table = $('#productTable');
            table.empty();
            products.forEach((product) => {
                    let image= imgUrl2 + product.imageUrl;
                table.append(`

                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.stockQuantity}</td>
                        <td><img src="${image}" width="100" height="100" alt="Sevice Image"
                            onerror="this.onerror=null; this.src='images/default.jpg';">        </td>
                        <td class="text-center">
                            <button type="button" class="btn btn-danger"
                                data-bs-toggle="modal" data-bs-target="#updateModal"
                                onclick="edit('${product.name}', '${product.description}', '${product.price}', '${product.stockQuantity}', '${product.imageUrl}')">
                                 <i class="fa-regular fa-pen-to-square"></i>
                                </button>
                            <button type="button" class="btn btn-warning"
                                onclick="deleteProduct('${product.name}')">
                                 <i class="fa-solid fa-trash"></i> 
                            </button>
                        </td>
                    </tr>
                `);
            }
            );
        },
        error: (error) => {
            console.error(error);
        }
    })
}
$(document).ready(function () {
    getProducts();
    $('#btnSave').click(function () {
        /*   console.log(fileName);*/
        let fileName = $("#image")[0]?.files[0]?.name || null;
        console.log(fileName);

        $.ajax({
            url: "http://localhost:8080/api/v1/product/save",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "name": $("#name").val(),
                "description": $("#description").val(),
                "price": $("#price").val(),
                "stockQuantity":$("#qty").val(),
                "imageUrl": fileName
            }),
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            success: (res) => {
                console.log(res);
                if (res.message === "Success") {
                    getProducts();
                    console.log("Product save successful");
                    alert("Product save successful");

                } else {
                    alert("Failed: " + (res.message || "Unknown error"));
                }
            },
            error: (error) => {
                console.error(error);
                alert("Something went wrong");
            }
        })
    })
});
