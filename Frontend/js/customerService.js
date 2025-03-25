function getServices() {

    let imgUrl2= "images/Service/";
    $.ajax({
        url: "http://localhost:8080/api/v1/service/getAll",
        method: "GET",
        contentType: "application/json",
        success: (res) => {
            services = res.data;

            services.forEach((service) => {
                let image= imgUrl2 + service.imageUrl;
                `
            <div class="container text-sm-start mt-5">
              <div class="row">
                <div class="col align-content-center">
                  <h4 id="timeDuration"><i class="fa-solid fa-clock"></i> ${service.duration}</h4>
                  <h3 class="text-center">${service.name}</h3>
                  <p>${service.description}</p>
                  <div class="text-center">
                    <button class="btn btn-primary rounded-pill mt-3">Book Appointment</button>
                  </div>
                </div>
                <div class="col align-middle ps-5 pt-1">
                  <img src="${image}" class="img-fluid rounded " alt="${service.name}">
                </div>
              </div>
            </div>
          `;
            });
        },
        error: (error) => {
            console.error(error);
            alert("Something went wrong");
        }
    })


}