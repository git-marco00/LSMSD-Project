$(document).ready(function () {
    document.getElementById('btn_signup').onclick = function (e) {
        let firstname_ = Document.forms.signup.firstname
        let lastname_  = Document.forms.signup.lastname
        let username_  = Document.forms.signup.username
        let mail       = Document.forms.signup.email
        let pwd        = Document.forms.signup.password
        let pwd2       = Document.forms.signup.password2
        let state_     = Document.forms.signup.state
        let country_   = Document.forms.signup.country
        let continent_ = Document.forms.signup.continent
        $.ajax({
            url : "/api/signup",
            data : {firstname: firstname_, lastname: lastname_, username : username_, email: mail, password: pwd, password2: pwd2, state: state_, country: country_, continent: continent_},
            method : "post",
            success: function(data) {
                result = JSON.parse(data)
                if(result['type'] == 0) {
                    window.location.href = "http://localhost:8080/home";
                }
                else if(result['type'] == 1) {
                    $("#password").text("Passwords ARE DIFFERENT")
                    $("#password2").text("Password ARE DIFFERENT")
                } else if(result['type'] == 2) {
                    $("#username").text("Username NOT VALID")
                } else if(result['type'] == 3){
                    $("#email").text("E-mail ALREADY IN USE")
                } else
                    window.location.href = "http://localhost:8080/signupFailed";
            }
        })
    }
});