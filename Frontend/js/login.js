const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
});

$(document).ready(function () {
    $("#btn-sign-up").click(function () {

        let username = $("#nameSignUp").val().trim();
        let email = $("#emailSignUp").val().trim();
        let password = $("#passwordSignUp").val().trim();

        if (!username || !email || !password) {
            alert("All fields are required!");
            return;
        }

        $.ajax({
            url: "http://localhost:8080/api/v1/user/register",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "username": username,
                "email": email,
                "password": password,
                "role": "USER"
            }),
            success: (res) => {
                console.log("sgdhfjk    "+res.data.token);
                window.localStorage.setItem("token", res.data.token);
                if (res.message === "Success") {
                    console.log("Registration successful");
                    alert("Registration successful");
                    window.location.href = "../index.html";
                } else {
                    alert("Failed: " + (res.message || "Unknown error"));
                }
            },
            error: (error) => {
                console.error(error);
                alert("Something went wrong");
            }
        });
    });
    $("#btn-login").click(function () {
        $.ajax({
            url: "http://localhost:8080/api/v1/auth/authenticate",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "email": $("#emailSignIn").val(),
                "password": $("#passwordSignIn").val()
            }),

            success: (res) => {
                console.log(res.data.token);
                console.log(res.data);
                console.log(res.data.role);
                window.localStorage.setItem("token", res.data.token);
                if (res.message === "Success") {
                    if (res.data.role === "ADMIN") {
                        window.location.href = "adminDashboard.html";
                    } else if (res.data.role === "USER") {
                        window.location.href = "index.html";
                    }
                    alert("Login successful");
                } else {
                    alert("Failed: " + (res.message || "Unknown error"));
                }
            },
            error: (error) => {
                console.error(error);
                alert("Something went wrong");
            }
        });
    })
});