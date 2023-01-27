$(document).ready(function() {
    $.ajax({
        url : "/api/loadGamePage",
        method : "get",
        success : function(data) {
            data = JSON.parse(data)
            $('#imgProf').append('<img src=' + data.imageUrl + ' class="w3-circle" style="height:106px;width:106px" alt="Game image"/>')
            $('#gameName').empty().append('<b>' + data.gameName + '</b>')
            $('#ypub').text('Year: ' + data.yearPublished)
            $('#fol').text('Followers: ' + data.followers)
            $('#rat').text('Rating: ' + data.avgRate)
            $('#des').text('Designer: ' + data.designer)
            $('#cat').text('Category: ' + data.categories)
            $('#minpmaxp').text('Min players / Max players: ' + data.minPlayers + ' / ' + data.maxPlayers)
            $('#desc').append('<p> Description: ' + data.description + '</p>')
            if(data.inCommonFollowers!=null){
                for(follower of data.inCommonFollowers){
                    let html = '<div class="inCommonFollower" id="' + follower.name + '" style="float:left; margin-left:10px; margin-bottom:10px">'
                    html += '<p style="font-size:10px">' + follower.name + '</p>'
                    html += '<img src="' + follower.imgUrl + '" class="w3-circle" style="height:50px;width:50px">'
                    html += '</div>'
                    $('#inCommonFollowersContainer').append(html)
                }
            }
            if(!data.followed) {
                $('#unfollowButton').remove()
                $('#followButton').bind('click', function () {
                    $.ajax({
                        url: "/api/followGame",
                        data: {game: data.gameName},
                        method: "get",
                        success: function (data) {
                            data = JSON.parse(data)
                            if(data)
                                window.location.href = "http://localhost:8080/gamePage"
                            else {
                                alert("You must be logged to follow a game!")
                                window.location.href = "http://localhost:8080/login"
                            }
                        }
                    })
                })
            } else {
                $('#followButton').remove()
                $('#unfollowButton').bind('click', function () {
                    $.ajax({
                        url: "/api/unfollowGame",
                        data: {game: data.gameName},
                        method: "get",
                        success: function (data) {
                            data = JSON.parse(data)
                            if(data)
                                window.location.href = "http://localhost:8080/gamePage"
                        }
                    })
                })
            }
            if(data.rated !== false)
                $('#rateButton').prop('disabled', true)
            if(data.inCommonFollowers != null) {
                let follower = 0
                for(follower in data.inCommonFollowers){
                    $('#inCommonFollowers').append(follower)
                }
            }
            if(data.mostRecentPosts != null) {
                let viewAllPosts = '<div class="w3-margin w3"><button type="button" class="w3-button w3-theme-d2 w3-margin-bottom" onClick="getAllPosts()"><class="fa fa-comment"></i> View all posts </button></div>'
                $('#right-column').append(viewAllPosts)
                let post = 0
                for(post in data.mostRecentPosts) {
                    let html = '<div id="post-' + data.mostRecentPosts[post].id + '" class="post"><div class="w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right"><br>'
                    html += '<img src="img/avatar.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">'
                    html += '<span class="w3-right w3-opacity"><i class="fa fa-calendar"></i>' + data.mostRecentPosts[post].date.slice(0,10) + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-comment"></i>' + data.mostRecentPosts[post].comments + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-thumbs-up"></i>' + data.mostRecentPosts[post].likes + '</span>'
                    html += ('<h4 id="' + data.mostRecentPosts[post].author + '" class="author">' + data.mostRecentPosts[post].author + '</h4><br><hr class="w3-clear">')
                    html += ('<p>' + data.mostRecentPosts[post].text + '</p>')
                    html += '<button type="button" class="logged like w3-button w3-theme-d1 w3-margin-bottom" id="like-post-' + data.mostRecentPosts[post].id + '"><i id="like-post-' + data.mostRecentPosts[post].id + '" class="fa fa-thumbs-' + ((data.mostRecentPosts[post].hasLiked) ? 'down' : 'up') + '"></i>' + ((data.mostRecentPosts[post].hasLiked) ? ' Unlike' : ' Like') + '</button>'
                    html += '<button type="button" class="view-comments-' + post + ' view-comments w3-button w3-theme-d2 w3-margin-bottom" id="view-comments-' + data.mostRecentPosts[post].id + '"><i class="fa fa-comment"></i> View comments</button>'
                    html += '<button type="button" class="admin delete w3-button w3-theme-d2 w3-margin-bottom" id="deletepost-' + data.mostRecentPosts[post].id + '"><i class="fa fa-comment"></i> Delete Post</button>'
                    html += '</div><br>'
                    $('#post-container').append(html)
                }
                $('h4.author').bind('click', function(event) {
                    searchForAPerson(event.target.id)
                })
                $('button.view-comments').bind('click', function(event) {
                    window.location.href = "http://localhost:8080/commentPage?post=" + event.target.id.slice(14)
                })
                $('button.like').bind('click', function(event) {
                    $.ajax({
                        url: "/api/likePost",
                        method: "get",
                        data: {post: event.target.id.slice(10), game: data.gameName},
                        success: function (data) {
                            data = JSON.parse(data)
                            if(data == 0) {
                                alert("You must be logged to like a post!")
                                window.location.href = "http://localhost:8080/login"
                            }
                            else if(data == -1)
                                alert("Something goes wrong!")
                            else
                                window.location.href = "http://localhost:8080/gamePage"
                        }
                    })
                })
            }
            $("#deleteButton").bind('click', function(){
                if(confirm("Do you really want to delete this game?")) {
                    $.ajax({
                        url: "/api/deleteGame",
                        method: "get",
                        data: {name: data.gameName},
                        success: function () {
                            alert("Game deleted!")
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
                        success: function() {
                            alert("Post deleted!")
                            window.location.href = "http://localhost:8080/gamePage"
                        }
                    })
                }
            })
            $('#tournamentButton').bind('click', function() {
                window.location.href = "http://localhost:8080/tournamentPage";
            })
            $('#postButton').bind('click', function () {
                $('#postButton').prop("disabled", true)
                html = "<div class=\"w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right\">"
                html += "<br>"
                html += "<textarea id=\"post-text\" placeholder=\"Say Something:\" rows=\"5\" cols=\"89\"></textarea>"
                html += "<br>"
                html += "<button type=\"button\" onclick=\"addPost()\" id=\"submit-" + data.gameName + "\" class=\"w3-button w3-theme-d2 w3-margin-bottom\"><i class=\"fa fa-comment\"></i> Submit</button>"
                html += "</div><br>"
                $('#post-container').prepend(html)
            })
            checkLogged()
            checkAdmin()
            checkRate()
        }
    })
})
function getAllPosts() { window.location.href = "http://localhost:8080/postPage"; }
function likePost(){}
function addPost() {
    $.ajax({
        url: "api/addPost",
        data: {game: $("#gameName").text(), text: $('textarea#post-text').val()},
        method : "get",
        success: function (data) {
            if(data == false)
                alert("Something went wrong")
            else {
                window.location.href = "http://localhost:8080/gamePage"
            }
        }
    })
}
function getCommonFollowers() {}
function rate(){
    let number = $("#ratingValue").val()
    $.ajax({
        url: "/api/rateGame",
        method: "get",
        data: {rate: number},
        success: function(data){
            if(data) {
                $("#ratingValue").hide()
                $("#rateButton").hide()
                window.location.reload()
            }
        }
    })

}
function checkRate() {
    $.ajax({
        url: "/api/haveIVoted",
        method: "get",
        success: function (data) {
            if(data) {
                $("#ratingValue").hide()
                $("#rateButton").hide()
            }
        }
    })
}
function getCommonFollowers(){;}