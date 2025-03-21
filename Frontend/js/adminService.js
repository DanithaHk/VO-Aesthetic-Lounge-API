console.log("admin service");
let imgUrl2= "images/Service/";
function getServices() {


    $.ajax({
        url: "http://localhost:8080/api/v1/service/getAll",
        method: "GET",
        contentType: "application/json",
        success: (res) => {
            services = res.data;
            let table = $('#serviceTable');
            table.empty();
            services.forEach((service) => {
                let image= imgUrl2 + service.imageUrl;
                table.append(`
                    <tr>
                        <td>${service.name}</td>
                        <td>${service.description}</td>
                        <td>${service.price}</td>
                        <td>${service.appoimentDuration}</td>
                        <td>
                            <img src="${image}" width="100" height="100" alt="Sevice Image"
                            onerror="this.onerror=null; this.src='images/default.jpg';">
                        </td>
                        
                        <td class="text-center">
                            <button type="button" class="btn btn-danger"
                               data-bs-toggle="modal" data-bs-target="#updateModal"
                                onclick="edit('${service.id}','${service.name}', '${service.description}', '${service.price}', '${service.appoimentDuration}', '${service.imageUrl}')">
                                 <i class="fa-regular fa-pen-to-square"></i>
                                </button>
                            <button type="button" class="btn btn-warning"
                                onclick="deleteService('${service.id}')">
                                 <i class="fa-solid fa-trash"></i> 
                            </button>
                        </td>
                    </tr>
                `);
            });
        },
        error: (error) => {
            console.error(error);
            alert("Something went wrong");
        }
    })


}


$(document).ready(function () {
    getServices();
    $('#btnSave').click(function () {
     /*   console.log(fileName);*/
        let fileName = $("#image")[0]?.files[0]?.name || null;
        console.log(fileName);
        let selectedDuration = $("#duration").val();

        console.log("Selected Duration:", selectedDuration);
        $.ajax({
            url: "http://localhost:8080/api/v1/service/save",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "name": $("#name").val(),
                "description": $("#description").val(),
                "price": $("#price").val(),
                "appoimentDuration": convertToLocalTime($("#duration").val()),
                "imageUrl": fileName
            }),
            success: (res) => {
                console.log(res);
                if (res.message === "Success") {
                    console.log("service save successful");
                    alert("service save successful");
                    getServices();
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
const edit = (ServiceID, name, description, price, appoimentDuration, imageUrl) => {
    $("#update_name").val(name);
    $("#update_description").val(description);
    $("#update_price").val(price);
    $("#update_duration").val(appoimentDuration);

    // Show current image
    $("#Update_currentImage").attr("src", imgUrl2 + imageUrl).on("error", function () {
        $(this).attr("src", "assets/images/default.jpg");
    });

    $("#update_imageUpload").val("");

    $(document).ready(function() {
        $("#btnUpdate").off("click").on("click", function () {
            let fileName = $("#Update_imageUpload")[0]?.files[0]?.name || null;
            console.log($("#update_imageUpload").val())

            let serviceData = {
                name: $("#update_name").val(),
                description: $("#update_description").val(),
                price: $("#update_price").val(),
                appoimentDuration: $("#update_duration").val(),
                image:  $("#update_imageUpload").val() // Default to existing image
            };



            // Send update request
            updateService(ServiceID, serviceData);
        });
    });
}

function convertToLocalTime(input) {
    let hours = 0, minutes = 0;

    if (input.includes("hour")) {
        let hourMatch = input.match(/(\d+)hour/); // Extract hours
        if (hourMatch) hours = parseInt(hourMatch[1]);
    }

    if (input.includes("min")) {
        let minMatch = input.match(/(\d+)min/); // Extract minutes
        if (minMatch) minutes = parseInt(minMatch[1]);
    }

    return `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:00`;
}
const updateService = (ServiceID,serviceData) => {

    $.ajax({
        url: "http://localhost:8080/api/v1/service/update/" + ServiceID,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(serviceData),
        success: (res) => {
            alert("Hotel updated successfully!");
            $("#updateModal").modal("hide");
            location.reload(); // Refresh the page to reflect changes
        },
        error: (error) => {
            console.error(error);
            alert("Something went wrong while updating.");
        }
    });
};
const deleteService = (id) => {
    $.ajax({
        url: "http://localhost:8080/api/v1/hotel/delete/" + id,
        method: "DELETE",
        success: (res) => {
            if (res.message === "Success") {
                alert("User deleted successfully");
                location.reload(); // Reload the page to update the table
            } else {
                alert("Failed: " + (res.message || "Unknown error"));
            }
        },
        error: (error) => {
            console.error(error);
            alert("Something went wrong while deleting.");
        }
    });
};

