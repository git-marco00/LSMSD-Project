$(document).ready(function(){
    $.ajax({
        url : "/api/loadGamePage",
        method : "get",
        success : function(data){
            // console.log(data)
            data=JSON.parse(data)
            $('#imgProf').append('<img src='+data.imageUrl+' class="w3-circle" style="height:106px;width:106px" alt="Avatar"/>')
            $('#gameName').text(data.gameName)
            $('#ypub').text(data.yearPublished)
            $('#fol').text('Followers: '+data.followers)
            $('#rat').text('Rating: '+data.ratings)
            $('#des').text('Designer: '+data.designer)
            $('#cat').text('Category: '+data.categories)
            $('#minpmaxp').text('Min players / Max players:'+data.minPlayers + '-' + data.maxPlayers)
            $('#desc').append('<p> Description: ' +data.description + '</p>')
            if(data.followed=="true"){
                $('#followButton').text("Unfollow")
            }
            if(data.rated != -1){
                $('#ratingValue').text(data.rated)
                $('#rateButton').prop('disabled', true)
            }
            if(data.inCommonFollowers != null) {
                $('#inCommonFollowers').text()
            }
            if(data.mostRecentPosts != null)
            {
                for(post in data.mostRecentPosts) {
                    $('#posts').append(" <div className=\"w3-col m7\">" +
                        "<div className=\"w3-container w3-card w3-white w3-round w3-margin-left w3-margin-right\"><br>" +
                        "<img src=\"img/avatar.png\" alt=\"Avatar\" className=\"w3-left w3-circle w3-margin-right\" style=\"width:60px\">" +
                        "<span className=\"w3-right w3-opacity\"><i className=\"fa fa-calendar\"></i>" + post.timestamp + "</span>" +
                        "<span className=\"w3-right w3-opacity w3-margin-right\"><i className=\"fa fa-comment\"></i>12</span>" +
                        "<span className=\"w3-right w3-opacity w3-margin-right\"><i className=\"fa fa-thumbs-up\"></i>24</span>" +
                        "<h4>" + post.author + "</h4><br> <hr className=\"w3-clear\"> " +
                        "<p>" + post.text + "</p>" +
                        "<button type=\"button\" className=\"w3-button w3-theme-d1 w3-margin-bottom\"><i className=\"fa fa-thumbs-up\"></i>Like</button>" +
                        "<button type=\"button\" className=\"w3-button w3-theme-d2 w3-margin-bottom\"><i className=\"fa fa-comment\"></i> View comments </button>" +
                        "</div>")
                }
            }
        }
    })
})

function getAllPosts(){
    window.location.href = "http://localhost:8080/postPage";
}