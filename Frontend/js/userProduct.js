
function getProducts() {
    let imgUrl2= "images/Product/";
    $.ajax({
        url: "http://localhost:8080/api/v1/product/getAll",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (response) {
            console.log("Products Loaded:", response);
            if (!response.data || response.data.length === 0) {
                $(".product-slider").html("<p class='text-center text-danger'>No products available.</p>");
                return;
            }
            let slider = $(".product-slider");
            response.data.forEach(product => {
                let image= imgUrl2 + product.imageUrl;
                let productCard = `
            <div class="product-card">
                <img src="${image}" class="product-image" alt="${product.name}"  onerror="this.onerror=null; this.src='images/default.jpg';">
                <h5 class="product-title">${product.name}</h5>
                <p class="product-description">${product.description}</p>
                <h6 class="product-price">Rs:${product.price.toFixed(2)}</h6>
                <button class="add-to-cart" 
                        data-id="${product.id}" 
                        data-quantity="1" 
                        data-image="${product.imageUrl}" 
                        data-name="${product.name}">
                    Add to Cart
                </button>
            </div>
        `;
                slider.append(productCard);
            });

            slider.slick({
                slidesToShow: 4,  // Laptop & large screens (default)
                slidesToScroll: 1,
                autoplay: false,
                dots: true,
                arrows: true,
                responsive: [
                    { breakpoint: 1400, settings: { slidesToShow: 3 } }, // Large desktops - 3 cards
                    { breakpoint: 1024, settings: { slidesToShow: 2 } }, // Tablets - 2 cards
                    { breakpoint: 768, settings: { slidesToShow: 1 } }  // Mobile - 1 card
                ]
            });

                    $(".add-to-cart").click(function() {
                        let token = localStorage.getItem("token");
                        // let userId =; // Get user ID from token
                        if (!token) {
                            alert("User not logged in!");
                            return;
                        }
                        let userId = getUserIdFromToken();
                        let productId = $(this).data("id");
                        let quantity = $(this).data("quantity");
                        let image = $(this).data("image");
                        let productName = $(this).data("name");

                        let cartData = {
                            userId: userId,  // Change dynamically
                            productId: productId,
                            quantity: quantity,
                            image: image,
                            productName: productName
                        };

                        $.ajax({
                            url: "http://localhost:8080/api/v1/cart/save",
                            type: "POST",
                            contentType: "application/json",
                            headers: {
                                "Authorization": `Bearer ${localStorage.getItem("token")}`
                            },
                            data: JSON.stringify(cartData),
                            success: function(response) {
                                alert("Product added to cart successfully!");
                                console.log(response);
                            },
                            error: function(xhr, status, error) {
                                console.error("Error:", error);
                                alert("Failed to add product to cart.");
                            }
                        });
                    });
        },
        error: function (xhr, status, error) {
            console.error("Error loading products:", error);
            $(".product-slider").html("<p class='text-center text-danger'> Error loading products. Please try again later.</p>");
        }
    });

}
function getUserIdFromToken() {
    let token = localStorage.getItem("token"); // Get token from localStorage
    if (!token) {
        console.error("No token found!");
        return null;
    }

    try {
        let decodedToken = jwt_decode(token);
        console.log("Decoded Token:", decodedToken); // Debugging
        return decodedToken.userId || null; // Make sure backend sends userId
    } catch (error) {
        console.error("Error decoding token:", error);
        return null;
    }
}

$(document).ready(() => {
    getProducts();
    getUserIdFromToken();
    console.log(localStorage.getItem("token"));

});