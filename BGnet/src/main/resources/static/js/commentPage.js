$(document).ready(function() {
    let post = window.location.href.slice(39,window.location.href.length)
    $.ajax({
        url: "/api/loadPostComments",
        data: {id: post},
        method : "get",
        success: function (data) {
            let post = $.parseJSON(data)
            $('#game').empty().append(post.game)
            let html = '<div id="post-' + post.id + '" class="post"><div class="w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right"><br>'
            html += '<img src="img/avatar.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">'
            html += '<span class="w3-right w3-opacity"><i class="fa fa-calendar"></i>' + post.date.slice(0,10) + '</span>'
            html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-comment"></i>' + post.comments + '</span>'
            html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-thumbs-up"></i>' + post.likes + '</span>'
            html += ('<h4 id="' + post.author + '" class="post-author">' + post.author + '</h4><br><hr class="w3-clear">')
            html += ('<p>' + post.text + '</p>')
            html += '<button type="button" class="logged like w3-button w3-theme-d1 w3-margin-bottom" id="like-post-' + post.id + '"><i id="like-post-' + post.id + '" class="fa fa-thumbs-' + ((post.hasLiked) ? 'down' : 'up') + '"></i>' + ((post.hasLiked) ? ' Unlike' : ' Like') + '</button>'
            html += '<button type="button" class="logged make-comment w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-comment"></i> Comment</button></button>'
            html += '<div class="new-comment"></div>'
            html += '</div><br>'
            $('#post-container').append(html)
            $('h4.post-author').bind('click', function(event) {
                window.location.href = "http://localhost:8080/userProfile?user=" + event.target.id;
            })
            let comments = post.listOfComments
            if(comments != null) {
                let comment = 0
                for (comment in comments) {
                    let html = '<div id="comment-' + comment + '" class="comment w3-col m7 comment"><div class="w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right"><br>'
                    html += ('<h4 class="comments-author" id="' + comments[comment].author + '">' + comments[comment].author + '</h4><br><hr class="w3-clear">')
                    html += ('<p>' + comments[comment].text + '</p>')
                    html += '</div><br>'
                    $('#post-' + post.id).append(html)
                }
                $('h4.comments-author').bind('click', function(event) {
                    window.location.href = "http://localhost:8080/userProfile?user=" + event.target.id;
                })
                $('button.make-comment').bind('click', function () {
                    $('button.make-comment').prop("disabled", true)
                    html = "<div class=\"w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right\">"
                    html += "<br>"
                    html += "<textarea id=\"comment-text\" placeholder=\"Your comment:\" rows=\"5\" cols=\"99\"></textarea>"
                    html += "<br>"
                    html += "<button type=\"button\" id=\"submit-" + post.id + "\" class=\"w3-button w3-theme-d2 w3-margin-bottom\"><i class=\"fa fa-comment\"></i> Submit</button></button>"
                    html += "</div><br>"
                    $('div.new-comment').append(html)
                    $('button#submit-' + post.id).bind('click', function(event) {
                        $.ajax({
                            url: "api/addComment",
                            data: {post_id: event.target.id.slice(7, event.target.id.size), game_name: post.game, text: $('textarea#comment-text').val()},
                            method : "post",
                            success: function (data) {
                                if(!data) {
                                    alert("You must be logged to add a comment")
                                    window.location.href = "http://localhost:8080/login"
                                }
                                else
                                    window.location.href = "http://localhost:8080/commentPage?post=" + event.target.id.slice(7, event.target.id.size)
                            }
                        })
                    })
                })
            }
            $('button.like').bind('click', function(event) {
                let __post_id = event.target.id.slice(10)
                $.ajax({
                    url: "/api/likePost",
                    method: "get",
                    data: {post: event.target.id.slice(10), game: post.game},
                    success: function (data) {
                        data = JSON.parse(data)
                        if(data == 0) {
                            alert("You must be logged to like a post!")
                            window.location.href = "http://localhost:8080/login"
                        }
                        else if(data == -1)
                            alert("Something goes wrong!")
                        else
                            window.location.href = "http://localhost:8080/commentPage?post=" + __post_id
                    }
                })
            })
            if(!checkLogged())
                $('button.like').hide()
        }
    })
})