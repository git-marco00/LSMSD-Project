function requestPostPage(pageNumber){
    $.ajax({
        url : "/api/getPost",
        data : {page : pageNumber},
        method : "get",
        parameters : 0,
        success : function(data){
            let posts = $.parseJSON(data);
            if(posts != null) {
                let post = 0
                $('#game').text(posts[post].game)
                for(post in posts) {
                    console.log(posts[post])
                    let html = '<div class="w3-col m7"><div class="w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right"><br>'
                    html += '<img src="img/avatar.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">'
                    html += '<span class="w3-right w3-opacity"><i class="fa fa-calendar"></i>' + posts[post].date.slice(0,10) + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-comment"></i>' + posts[post].likes + '</span>'
                    html += '<span class="w3-right w3-opacity w3-margin-right"><i class="fa fa-thumbs-up"></i>' + posts[post].comments + '</span>'
                    html += ('<h4>' + posts[post].author + '</h4><br><hr class="w3-clear">')
                    html += ('<p>' + posts[post].text + '</p>')
                    html += '<button type="button" class="w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-thumbs-up"></i>Like</button>'
                    html += '<button type="button" class="w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-comment"></i> View comments </button>'
                    html += '</div><br>'
                    $('#containerPosts').append(html)
                }
            }
        }
    })
}

$(document).ready(function (){
    requestPostPage(0);
})