$(document).ready(function () {
    document.getElementById('btn_login').onclick = function (e) {
        let uname = Document.forms.login.username
        let pwd = Document.forms.login.password
        $.ajax({
            url : "/api/login",
            data : {username : uname, password: pwd},
            method : "post",
            success: function(data) {
                result = JSON.parse(data)
                console.log(data)
                if(result['type'] == 1) {
                    $("#username").text("Username NOT VALID")
                } else if(result['type'] == 2) {
                    $("#password").text("Password NOT VALID")
                } else
                    window.location.href = "http://localhost:8080/home";
            }
        })
    }
});