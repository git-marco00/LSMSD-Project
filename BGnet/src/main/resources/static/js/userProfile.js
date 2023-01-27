$(document).ready(function() {
    let user = window.location.href.slice(39,window.location.href.length)
    $.ajax({
        url: "/api/loadProfile",
        data : {username: user},
        method: "get",
        success: function (data) {
            data = JSON.parse(data)
            if(!data) {
                alert("No user found");
                return
            }
            $('#username').text(data.username)
            $('#img').append('<img src="' + data.img + '" class="w3-circle" style="height:106px;width:106px" alt="Profile picture"/>')
            $('#firstname').text("First Name: "+data.firstName)
            $('#lastname').text("Last Name: "+data.lastName)
            $('#followers').text("Followers: "+data.followers)
            $('#regYear').text("Year of registration: "+data.yearRegistered)
            $('#email').text("Email: "+data.email)
            $('#stateOfProvince').text("State of province: "+data.stateOfProvince)
            $('#country').text("Country: "+data.country)
            $('#continent').text("Continent: "+data.continent)

            if(data.isMyself){
                $('#UserButtons').remove()
                $('#inCommonStuffContainer').remove()
                $('#tournamentsH').text("My tournaments:")
            }

            // follow/unfollow button
            if(!data.isMyself) {
                if (data.isFollowed) {
                    $('#followButton').remove()
                    $('#unfollowButton').bind('click', function () {
                        $.ajax({
                            url: "/api/unfollowUser",
                            data: {user: data.username},
                            method: "get",
                            success: function (data) {
                                data = JSON.parse(data)
                                if (data) {
                                    window.location.reload()
                                }
                            }
                        })
                    })
                } else {
                    $('#unfollowButton').remove()
                    $('#followButton').bind('click', function () {
                        $.ajax({
                            url: "/api/followUser",
                            data: {user: data.username},
                            method: "get",
                            success: function (data) {
                                data = JSON.parse(data)
                                if (data)
                                    window.location.reload()
                                else {
                                    alert("You must be logged to follow a user!")
                                    window.location.href = "http://localhost:8080/login"
                                }
                            }
                        })
                    })
                }
            }
            if(data.mostRecentPosts!=null) {
                for (post in data.mostRecentPosts) {
                    html = "<div id=\"post-" + data.mostRecentPosts[post].id + "-game-" + data.mostRecentPosts[post].game + "\" class=\"w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right\"><br>"
                    html += "<span class=\"w3-right w3-opacity\"><i class=\"fa fa-calendar\"></i>" + data.mostRecentPosts[post].date.slice(0, 10) + "</span>"
                    html += "<span class=\"w3-right w3-opacity w3-margin-right\"><i class=\"fa fa-comment\"></i>" + data.mostRecentPosts[post].comments.toString() + "</span>"
                    html += "<span class=\"w3-right w3-opacity w3-margin-right\"><i class=\"fa fa-thumbs-up\"></i>" + data.mostRecentPosts[post].likes + "</span>"
                    html += "<h4 id=\"" + data.mostRecentPosts[post].game + "\" class=\"game\">" + data.mostRecentPosts[post].game + "</h4><br><hr class=\"w3-clear\">"
                    html += "<p>" + data.mostRecentPosts[post].text + "</p>"
                    html += '<button type="button" class="logged like-' + post + ' w3-button w3-theme-d1 w3-margin-bottom" id="like-post-' + data.mostRecentPosts[post].id + '"><i id="like-post-' + data.mostRecentPosts[post].id + '" class="fa fa-thumbs-' + ((data.mostRecentPosts[post].hasLiked) ? 'down' : 'up') + '"></i>' + ((data.mostRecentPosts[post].hasLiked) ? ' Unlike' : ' Like') + '</button>'
                    html += "<button type=\"button\" class=\"view-comments w3-button w3-theme-d2 w3-margin-bottom\" id=\"" + data.mostRecentPosts[post].id + "\"><i class=\"fa fa-comment\"></i> View comments</button></div><br>"
                    html += "<button type=\"button\" class=\"admin delete view-comments w3-button w3-theme-d2 w3-margin-bottom\" id=\"deletepost-" + data.mostRecentPosts[post].id + "\"><i class=\"fa fa-comment\"></i>Delete Post</button></div><br>"
                    $('#post-container').append(html)
                    $('button.like-' + post).bind('click', function (event) {
                        let __username = data.username
                        let __post = event.target.id.slice(10)
                        let __game = data.mostRecentPosts[post].game
                        $.ajax({
                            url: "/api/likePost",
                            method: "get",
                            data: {post: __post, game: __game},
                            success: function (data) {
                                data = JSON.parse(data)
                                if (data == 0) {
                                    alert("You must be logged to like a post!")
                                    window.location.href = "http://localhost:8080/login"
                                } else if (data == -1)
                                    alert("Something goes wrong!")
                                else
                                    window.location.href = "http://localhost:8080/userProfile?user=" + __username
                            }
                        })
                    })
                }
            }
            if(!data.isMyself) {
                if (data.inCommonFollowers != null) {
                    for (follower of data.inCommonFollowers) {
                        let html = '<div class="inCommonFollowers" id="' + follower.name + '" style="float:left; margin-left:10px; margin-bottom:10px">'
                        html += '<p style="font-size:7px">' + follower.name + '</p>'
                        html += '<img src="' + follower.imgUrl + '" class="w3-circle" style="height:40px;width:40px">'
                        html += '</div>'
                        $('#inCommonFollowersContainer').append(html)
                    }
                }
            }
            if (data.inCommonTournaments != null) {
                for (tournament of data.inCommonTournaments) {
                    let html = '<div>'
                    html += '<p> Game: ' + tournament.tournamentGame + '</p>'
                    html += '<p> Terminated: ' + tournament.isClosed + '</p>'
                    html += '<p> Date: ' + tournament.date + '</p>'
                    html += '<hr class="w3-clear">'
                    html += '</div>'
                    $('#inCommonTournamentsContainer').append(html)
                }
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
            checkLogged()
        }
    })
})