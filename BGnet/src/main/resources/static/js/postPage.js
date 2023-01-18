function requestPostPage(pageNumber){
    $.ajax({
        url : "/api/getPost",
        data : {page : pageNumber},
        method : "get",
        parameters : 0,
        success : function(data){
            let posts = $.parseJSON(data);
            if (posts != null) {
                let post = 0
                $('#game').text(posts[post].game)
                for (post in posts) {
                    let html = '<div id="post-' + posts[post].id + '" class="w3-col m7 post"><div class="w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right"><br>'
                    html += '<img src="img/avatar.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">'
                    html += '<span class="w3-right w3-opacity"><i class="fa fa-calendar"></i>' + posts[post].date.slice(0, 10) + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-comment"></i>' + posts[post].likes + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-thumbs-up"></i>' + posts[post].comments + '</span>'
                    html += ('<h4>' + posts[post].author + '</h4><br><hr class="w3-clear">')
                    html += ('<p>' + posts[post].text + '</p>')
                    html += '<p id="_id" style="display: none;">' + posts[post].id + '</p>'
                    html += '<button type="button" class="w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-thumbs-up"></i>Like</button>'
                    html += '<button type="button" class="view-comments-' + post + ' view-comments w3-button w3-theme-d2 w3-margin-bottom" id="' + posts[post].id + '"><i class="fa fa-comment"></i>View comments</button>'
                    html += '</div><br>'
                    $('#containerPosts').append(html)
                }
                $('.view-comments').bind('click', function(event) {
                    $.ajax({
                        url: "/api/loadPostComments",
                        data: {id: event.target.id},
                        method : "get",
                        success: function (data) {
                            for (j in $('.post')) {
                                if ($('button.view-comments-' + j).attr('id') != undefined) {
                                    if ($('button.view-comments-' + j).attr('id') != event.target.id) {
                                        $('#post-' + $('button.view-comments-' + j).attr('id')).remove()
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
                })
            }
        }
    })
}
$(document).ready(function (){
    loadNumberOfPages();
    requestPostPage(0);
})
function precPage() {
    let page = parseInt($('#page').text()) - 1
    if(page <= 0)
        return
    $('#containerPosts').empty()
    requestPostPage(page - 1);
    $('#page').text(page)
    $('#succPage').prop("disabled", false)
}
function succPage() {
    let page = parseInt($('#page').text()) + 1
    $('#containerPosts').empty()
    requestPostPage(page - 1)
    $('#page').text(page)
    if($('#page').text() == $("#howManyPages").attr("class")){
        $("#succPage").prop("disabled", true)
    }
}

function loadNumberOfPages(){
    $.ajax({
        url: "/api/getPages",
        success: function(data){
            $("#howManyPages").attr("class", data)
        }
    })
}

function likePost(){;}

function viewComments(){;}

function addPost(){;}

function goToTheUser(){;}

function backToThePage(){;
}

