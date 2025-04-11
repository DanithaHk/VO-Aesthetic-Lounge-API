function getUsers() {
    console.log("get admin users manage");
    let token = localStorage.getItem("token");

    if (!token) {
       alert("log first")
    } else {
        console.log("Token found:", token);
    }
    $.ajax({
        url: "http://localhost:8080/api/v1/user/getAll",
        method: "GET",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + token
        },

        success: (res) => {
            customers = res.data;
            let table = $('#userTable');
            table.empty();
            customers.forEach((customer) => {
                console.log(customer.username);
                console.log(customer.email);
                console.log(customer.role);
                table.append(`
                    <tr>
                        <td>${customer.username}</td>
                        <td>${customer.email}</td>
                        <td>${customer.role}</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-warning"
                                data-bs-toggle="modal" data-bs-target="#updateModal"
                                onclick="edit('${customer.email}', '${customer.role}')"> 
                                 <i class="fa-regular fa-pen-to-square"></i>
                                </button>
                            <button type="button" class="btn btn-danger"
                                onclick="deleteCustomer('${customer.id}')">
                                 <i class="fa-solid fa-trash"></i> 
                                </button>
                        </td>
                    </tr>
                `);
            });
        },
        error: (error) => {
            console.error(error);
            alert("Something went wrong while fetching users.");
        }
    });

}
$(document).ready(function () {
    getUsers();
    $('#btnSave').click(function () {
        $.ajax({
            url: "http://localhost:8080/api/v1/user/register",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "username": $("#username").val(),
                "email": $("#email").val(),
                "password": $("#password").val(),
                "role": $("#role").val()
            }),
            headers: {
                "Authorization": "Bearer " + token
            },
            success: (res) => {
                console.log(res);
                if (res.message === "Success") {
                    console.log("Registration successful");
                    alert("Registration successful");
                    getUsers();
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
const edit = (UserEmail, UserRole) => {
    document.getElementById("update_role").value = UserRole;

    document.getElementById("btnUpdate").onclick = () => {
        $.ajax({
            url: "http://localhost:8080/api/v1/user/update/" + UserEmail,
            method: "PUT", // Using PUT for update (if API supports it)
            contentType: "application/json",
            data: JSON.stringify({
                "role": document.getElementById("update_role").value
            }),
            headers: {
                "Authorization": "Bearer " + token
            },
            success: (res) => {
                if (res.message === "User role updated successfully") {
                    alert("User role updated successfully");
                    $("#updateModal").modal("hide"); // Close the modal
                    location.reload(); // Reload the page to update the table
                } else {
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
const deleteCustomer = (id) => {
    $.ajax({
        url: "http://localhost:8080/api/v1/user/delete/" + id,
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: (res) => {
            if (res.message === "Success") {
                alert("User deleted successfully");
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
getUsers();
