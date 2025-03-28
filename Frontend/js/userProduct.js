function getProducts() {
    let imgUrl2= "images/Product/";
    $.ajax({
        url: "http://localhost:8080/api/v1/product/getAll",
        method: "GET",
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
                <button class="add-to-cart" data-id="${product.id}">Add to Cart</button>
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

            $(".add-to-cart").click(function () {
                let productId = $(this).data("id");
                let productName = $(this).closest(".product-card").find(".product-title").text();
                alert("Product " + productName + " added to cart!");
            });
        },
        error: function (xhr, status, error) {
            console.error("Error loading products:", error);
            $(".product-slider").html("<p class='text-center text-danger'>⚠️ Error loading products. Please try again later.</p>");
        }
    });

}
$(document).ready(() => {
    getProducts();
});