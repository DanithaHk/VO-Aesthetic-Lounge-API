console.log("admin booking");


function getBookings() {
    $.ajax({
        url: "http://localhost:8080/api/v1/booking/getAll", // Your API endpoint for appointments
        method: "GET",
        success: (res) => {
            console.log(res);  // Log the response for debugging
            let appointments = res.data;  // Assuming the data comes in `data` field
            let table = $('#BookingTable');  // Assuming you have a table with id 'appointmentsTable'
           table.empty();  // Clear the table before appending new data

            appointments.forEach((appointment) => {
                let appointmentTime = appointment.appointmentTime || "N/A";  // Default to "N/A" if time is missing
                let appointmentDate = appointment.appointmentDate || "N/A";  // Default to "N/A" if date is missing

                table.append(`
                <tr>
                    <td>${appointment.serviceName}</td>
                     <td>${appointment.appointmentDate}</td>
                     <td>${appointment.appointmentTime}</td>
                    <td>${appointment.userEmail}</td>
                    <td>${appointment.price}</td>
                    <td class="text-center">
                       <button type="button" class="btn btn-warning"
                            data-bs-toggle="modal" data-bs-target="#updateModal"
                            onclick="editAppointment('${appointment.id}', '${appointment.serviceName}', '${appointment.userEmail}')">
                             <i class="fa-regular fa-pen-to-square"></i>
                       </button>

                        <button type="button" class="btn btn-danger" onclick="deleteAppointment(${appointment.id})">
                             <i class="fa-solid fa-trash"></i> 
                        </button>
                    </td>
                </tr>
            `);
            });
        },
        error: (error) => {
            console.error("Error fetching appointments:", error);
        }
    });

}
$(document).ready(function () {

    getBookings();
    $('#btnSave').click(function () {
        console.log($("#appointmentTime").val())
        console.log($("#serviceName").val())
        console.log($("#appointmentDate").val())

        console.log($("#userEmail").val())
        $.ajax({
            url: "http://localhost:8080/api/v1/booking/save",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "serviceName": $("#serviceName").val(),
                "appointmentDate": $("#appointmentDate").val(),
                "appointmentTime": $("#appointmentTime").val(),
                "userEmail":$("#userEmail").val()

            }),
            success: (res) => {
                console.log(res);
                if (res.message === "Success") {
                    getBookings();
                    console.log("appointment save successful");
                    getBookings();
                    alert("appointment save successful");

                } else {
                    getBookings();
                    alert("Failed: " + (res.message || "Unknown error"));
                }
            },
            error: (error) => {
                console.error(error);
                alert("Something went wrong");
            }
        })
    });
});

const editAppointment = (id,name,userEmail) => {

    $("#update_service_name").val(name);
    $("#update_service_userEmail").val(userEmail);
    console.log(name)
    console.log(userEmail)
   /* document.getElementById("update_service_appointmentDate").value = appointmentDate;
    document.getElementById("update_service_appointmentTime").value = appointmentTime;
*/
    document.getElementById("btnUpdate").onclick = () => {
        $.ajax({
            url: "http://localhost:8080/api/v1/booking/update/" + id,
            method: "PUT", // Using PUT for update (if API supports it)
            contentType: "application/json",
            data: JSON.stringify({
                "appointmentDate": document.getElementById("update_service_appointmentDate").value,
                "appointmentTime": document.getElementById("update_service_appointmentTime").value
            }),
            success: (res) => {
                if (res.message === "Appointment updated successfully") {
                    getBookings();
                    alert("Appointment updated successfully");
                    $("#updateModal").modal("hide"); // Close the modal
                    location.reload(); // Reload the page to update the table
                } else {
                    getBookings()
                    alert("Failed: " + (res.message || "Unknown error"));
                }
            },
            error: (error) => {
                console.error(error);
                alert("Something went wrong while updating.");
            }
        });
    };
};

const deleteAppointment = (id) => {
    $.ajax({
        url: "http://localhost:8080/api/v1/booking/delete/" + id,
        method: "DELETE",
        success: (res) => {
            if (res.message === "Success") {
                alert("Appointment deleted successfully");
                location.reload();
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

getBookings();

