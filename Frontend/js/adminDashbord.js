

function getUsers() {
    console.log("get admin users");
    let token = localStorage.getItem("token");
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
getUsers();

