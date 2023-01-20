$(document).ready(function() {
    let user = window.location.href.slice(39,window.location.href.length)
    $.ajax({
        url: "/api/loadProfile",
        data : {username: user},
        method: "get",
        success: function (data) {
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
                html += "<h4 id=\"" + data.mostRecentPosts[post].game + "\" class=\"game\">" + data.mostRecentPosts[post].game + "</h4><br><hr class=\"w3-clear\">"
                html += "<p>" + data.mostRecentPosts[post].text + "</p>"
                html += "<button type=\"button\" class=\"w3-button w3-theme-d1 w3-margin-bottom\"><i class=\"fa fa-thumbs-up\"></i> Like</button>"
                html += "<button type=\"button\" class=\"view-comments w3-button w3-theme-d2 w3-margin-bottom\" id=\"" + data.mostRecentPosts[post].id + "\"><i class=\"fa fa-comment\"></i> View comments</button></div><br>"
                html += "<button type=\"button\" class=\"admin delete view-comments w3-button w3-theme-d2 w3-margin-bottom\" id=\"deletepost-" + data.mostRecentPosts[post].id + "\"><i class=\"fa fa-comment\"></i>Delete Post</button></div><br>"
                $('#post-container').append(html)
            }
            $('button.view-comments').bind('click', function(event) {
                window.location.href = "http://localhost:8080/commentPage?post=" + event.target.id;
            })
            $('h4.game').bind('click', function (event) {
                $.ajax({
                    url : "/api/gamePageExists",
                    data : {name : event.target.id},
                    method : "get",
                    success: function(data){
                        if(data < 0)
                            alert("It seems this game has been removed")
                        else
                            window.location.href="http://localhost:8080/gamePage"
                    }
                })
            })
            $("#deleteUser").bind('click', function(event){
                if(confirm("Do you really want to ban this user?")) {
                    $.ajax({
                        url: "/api/banUser",
                        method: "get",
                        data: {name: user},
                        success: function (data) {
                            alert("User deleted!")
                            window.location.href = "http://localhost:8080/adminPage"
                        }
                    })
                }
            })
            $(".delete").bind('click', function(event){
                if(confirm("Do you really want to delete this post?")){
                    $.ajax({
                        url: "/api/deletePost",
                        method: "get",
                        data: {id: event.target.id.slice(11)},
                        success: function(data){
                            alert("Post deleted!")
                            window.location.reload()
                        }
                    })
                }
            })
            $(".admin").hide()
            checkAdmin()
        }
    })
})