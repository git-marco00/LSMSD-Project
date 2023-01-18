$(document).ready(function() {
    let user = window.location.href.slice(39,window.location.href.length)
    $.ajax({
        url: "/api/loadProfile",
        data : {username: user},
        method: "get",
        success: function (data) {
            console.log(data)
            data = JSON.parse(data)
            $('#username').text(data.username)
            $('#img').append('<img src="' + data.img + '" class="w3-circle" style="height:106px;width:106px" alt="Profile picture"/>')
            $('#firstname').empty().text(data.firstName)
            $('#lastname').empty().text(data.lastName)
            for(post in data.mostRecentPosts) {
                html = "<div class=\"w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right\"><br>"
                html += "<span class=\"w3-right w3-opacity\"><i class=\"fa fa-calendar\"></i>" + data.mostRecentPosts[post].date.slice(0,10) + "</span>"
                html += "<span class=\"w3-right w3-opacity w3-margin-right\"><i class=\"fa fa-comment\"></i>" + data.mostRecentPosts[post].comments.toString() + "</span>"
                html += "<span class=\"w3-right w3-opacity w3-margin-right\"><i class=\"fa fa-thumbs-up\"></i>" + data.mostRecentPosts[post].likes + "</span>"
                html += "<h4>" + data.mostRecentPosts[post].game + "</h4><br><hr class=\"w3-clear\">"
                html += "<p>" + data.mostRecentPosts[post].text + "</p>"
                html += "<button type=\"button\" class=\"w3-button w3-theme-d1 w3-margin-bottom\"><i class=\"fa fa-thumbs-up\"></i>Like</button>"
                html += "<button type=\"button\" class=\"w3-button w3-theme-d2 w3-margin-bottom\"><i class=\"fa fa-comment\"></i>View comments</button></div><br>"
                $('#post-container').append(html)
            }
        }
    })
})