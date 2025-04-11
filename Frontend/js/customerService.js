function getServices() {
    let imgUrl2 = "images/Service/";

    $.ajax({
        url: "http://localhost:8080/api/v1/service/getAll",
        method: "GET",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
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
                      <div class="container text-center ">
                            <button class="btn btn-primary rounded-pill mt-3" data-bs-toggle="modal" data-bs-target="#bookingModal">Book Appointment</button>
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

function loadServices() {
    $.ajax({
        url: "http://localhost:8080/api/v1/service/getAll", // Backend API endpoint
        type: "GET",
        dataType: "json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (res) {
            console.log("API Response:", res); // Debugging Response

            let serviceDropdown = $("#serviceName");
            serviceDropdown.empty(); // Clear existing options

            if (!res || !res.data || res.data.length === 0) {
                console.log("No services found!");
                serviceDropdown.append('<option value="">No services available</option>');
                return;
            }

            serviceDropdown.append('<option value="">Select a service</option>');

            res.data.forEach((service) => {
                serviceDropdown.append(`
                    <option value="${service.name}" data-price="${service.price}">
                        ${service.name} - Rs.${service.price}
                    </option>
                `);
            });
        },
        error: function (err) {
            console.error("Error fetching services:", err);
            $("#service").html('<option value="">Failed to load services</option>');
        }
    });
}

function saveBooking() {

        $("#bookAppointmentBtn").click(function (e) {
            e.preventDefault(); // Prevent default form submission

            // Collect user input data
            let userEmail = $("#userEmail").val();
            let appointmentDate = $("#appointmentDate").val();
            let appointmentTime = $("#appointmentTime").val();
            let serviceName = $("#serviceName").val();
            console.log(userEmail)
            console.log(appointmentDate)

            // Validate user input
            if (!userEmail || !appointmentDate || !appointmentTime || !serviceName) {
                alert(" Please fill in all the required fields!");
                return;
            }

            $.ajax({
                url: "http://localhost:8080/api/v1/booking/save", // API endpoint
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    userEmail: userEmail,
                    appointmentDate: appointmentDate,
                    appointmentTime: appointmentTime,
                    serviceName: serviceName
                }),
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                success: (res) => {


                    if (res.message === "Booking Saved Successfully") {
                       getServices();
                        console.log(" Appointment saved successfully!");
                        alert("Appointment saved successfully!");
                    } else if (res.message === "Your email not register with us") {
                        alert(" Error: " + res.message + "\nPlease register before booking an appointment.");
                    } else if (res.message === "The appointment time slot is already taken") {
                        alert(" Error: " + res.message + "\nPlease choose a different time slot.");
                    } else {
                        alert(" Failed: " + (res.message || "Unknown error"));
                    }
                },
                error: (error) => {
                    console.error(error);
                    if (error.responseJSON) {
                        let errorMessage = error.responseJSON.message || "Something went wrong.";
                        alert(" Error: " + errorMessage);
                    } else {
                        alert("Something went wrong. Please try again.");
                    }
                }
            });
        });


}
$(document).ready(() => {
    getServices();
    saveBooking();
    loadServices();
});
