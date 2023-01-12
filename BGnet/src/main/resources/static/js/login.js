$(document).ready(function () {
    document.getElementById('btn_login').onclick = function (e) {
        let uname = Document.forms.login.username
        let pwd = Document.forms.login.password
        $.ajax({
            url : "/api/login",
            data : {username : uname, password: pwd},
            method : "post",
            success: function(data){
                console.log(data)
            }
        })
    }
});