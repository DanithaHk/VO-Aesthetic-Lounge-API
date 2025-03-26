function getServices() {
    let imgUrl2 = "images/Service/";

    $.ajax({
        url: "http://localhost:8080/api/v1/service/getAll",
        method: "GET",
        contentType: "application/json",
        success: (res) => {
            console.log("API Response:", res); // Debugging

            if (!res || !res.data || res.data.length === 0) {
                console.log("No services found!");
                $("#serviceContainer").html("<p class='text-center text-danger'>No services available</p>");
                return;
            }

            let serviceHtml = "";
            let services = res.data;

            services.forEach((service) => {
                let image = imgUrl2 + service.imageUrl;
                console.log(service.appoimentDuration)
                serviceHtml += `
              <div class="container text-sm-start h-50 ">
                  <div class="row  ">
                    <div class="col d-flex flex-column justify-content-center mb-5"> <!-- Ensure top alignment -->
                      <h4 id="timeDuration"><i class="fa-solid fa-clock"></i> ${service.appoimentDuration}</h4>
                      <h3 class="text-center">${service.name}</h3>
                      <p>${service.description}</p>
                      <div class="text-center">
                        <button class="btn btn-primary rounded-pill mt-3 ">Book Appointment</button>
                      </div>
                    </div>
                    <div class="col ps-5 pt-1">
                      <img src="${image}" class="img-fluid rounded w-100 h-75" alt="${service.name}">
                    </div>
                  </div>
                </div>

                `;
            });


            $("#serviceContainer").html(serviceHtml);
        },
        error: (err) => {
            console.log("Error fetching services:", err);
            $("#serviceContainer").html("<p class='text-center text-danger'>Failed to load services</p>");
        }
    });
}


$(document).ready(() => {
    getServices();
});
