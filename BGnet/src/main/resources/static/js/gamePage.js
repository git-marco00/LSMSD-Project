$(document).ready(function() {
    $.ajax({
        url : "/api/loadGamePage",
        method : "get",
        success : function(data) {
            data = JSON.parse(data)
            $('#imgProf').append('<img src=' + data.imageUrl + ' class="w3-circle" style="height:106px;width:106px" alt="Avatar"/>')
            $('#gameName').text(data.gameName)
            $('#ypub').text(data.yearPublished)
            $('#fol').text('Followers: ' + data.followers)
            $('#rat').text('Rating: ' + data.ratings)
            $('#des').text('Designer: ' + data.designer)
            $('#cat').text('Category: ' + data.categories)
            $('#minpmaxp').text('Min players / Max players: ' + data.minPlayers + ' / ' + data.maxPlayers)
            $('#desc').append('<p> Description: ' + data.description + '</p>')
            if(data.followed == "true") {
                $('#followButton').text("Unfollow")
            }
            if(data.rated != -1) {
                $('#ratingValue').text(data.rated)
                $('#rateButton').prop('disabled', true)
            }
            if(data.inCommonFollowers != null) {
                $('#inCommonFollowers').text()
            }
            if(data.mostRecentPosts != null) {
                let post = 0
                for(post in data.mostRecentPosts) {
                    let html = '<div id="post-' + data.mostRecentPosts[post].id + '" class="w3-col m7 post"><div class="w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right"><br>'
                    html += '<img src="img/avatar.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">'
                    html += '<span class="w3-right w3-opacity"><i class="fa fa-calendar"></i>' + data.mostRecentPosts[post].date.slice(0,10) + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-comment"></i>' + data.mostRecentPosts[post].likes + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-thumbs-up"></i>' + data.mostRecentPosts[post].comments + '</span>'
                    html += ('<h4 id="' + data.mostRecentPosts[post].author + '" class="author">' + data.mostRecentPosts[post].author + '</h4><br><hr class="w3-clear">')
                    html += ('<p>' + data.mostRecentPosts[post].text + '</p>')
                    html += '<button type="button" class="w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-thumbs-up"></i>Like</button>'
                    html += '<button type="button" class="view-comments-' + post + ' view-comments w3-button w3-theme-d2 w3-margin-bottom" id="' + data.mostRecentPosts[post].id + '"><i class="fa fa-comment"></i>View comments</button>'
                    html += '</div><br>'
                    $('#post-container').append(html)
                }
                $('h4.author').bind('click', function(event) {
                    window.location.href = "http://localhost:8080/userProfile?user=" + event.target.id;
                })
                $('button.view-comments').bind('click', viewComments(event))
            let viewAllPosts = '<div class="w3-margin w3"><button type="button" class="w3-button w3-theme-d2 w3-margin-bottom" onClick="getAllPosts()"><class="fa fa-comment"></i> View all posts </button></div>'
            $('#right-column').append(viewAllPosts)
        }
    })
})
function getAllPosts() { window.location.href = "http://localhost:8080/postPage"; }

function likePost(){;}

function viewComments(event){
    $.ajax({
        url: "/api/loadPostComments",
        data: {id: event.target.id},
        method : "get",
        success: function (data) {
            for (i in $('.post')) {
                if ($('button.view-comments-' + i).attr('id') != undefined) {
                    if ($('button.view-comments-' + i).attr('id') != event.target.id) {
                        $('#post-' + $('button.view-comments-' + i).attr('id')).remove()
                    } else {
                        $('#' + event.target.id).remove()
                    }
                }
            }
            let comments = $.parseJSON(data);
            if(comments != null) {
                let comment = 0
                for (comment in comments) {
                    let html = '<div id="comment-' + comment + '" class="w3-col m7 comment"><div class="w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right"><br>'
                    html += ('<h4>' + comments[comment].author + '</h4><br><hr class="w3-clear">')
                    html += ('<p>' + comments[comment].text + '</p>')
                    html += '</div><br>'
                    $('#containerPosts').append(html)
                }
            }
        }
    })
}

function addPost(){;}

function getCommonFollowers(){;}

function goToTheUser(){;}

function goToTheTournament(){;}
