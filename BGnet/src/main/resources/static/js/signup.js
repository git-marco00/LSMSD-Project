$(document).ready(function () {
    document.getElementById('btn_signup').onclick = function (e) {
        let firstname_ = document.getElementById("firstname").value
        let lastname_  = document.getElementById("lastname").value
        let username_  = document.getElementById("username").value
        let mail       = document.getElementById("email").value
        let pwd        = document.getElementById("password").value
        let pwd2       = document.getElementById("password2").value
        let state_     = document.getElementById("state").value
        let country_   = document.getElementById("country").value
        let continent_ = document.getElementById("continent").value
        $.ajax({
            url : "/api/signup",
            data : {firstname: firstname_, lastname: lastname_, username : username_, email: mail, password: pwd, password2: pwd2, state: state_, country: country_, continent: continent_},
            dataType : 'json',
            method : "post",
            success: function(data) {
                console.log(data)
                result = JSON.parse(data)
                if(result["type"] == 0) {
                    alert("OK")
                }
                else if(result["type"] == 1) {
                    alert("Passwords ARE DIFFERENT")
                    $("#password").val("")
                    $("#password2").val("")
                } else if(result["type"] == 2) {
                    alert("Username NOT VALID")
                    $("#username").val("")
                    $("#password").val("")
                    $("#password2").val("")
                } else if(result["type"] == 3){
                    alert("E-mail ALREADY IN USE")
                    $("#email").val("")
                    $("#password").val("")
                    $("#password2").val("")
                } else
                    alert("Something goes wrong");
            }
        })
    }
});