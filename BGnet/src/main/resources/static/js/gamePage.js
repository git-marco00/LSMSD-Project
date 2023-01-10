$(document).ready(function(){
    $.ajax({
        url : "/api/loadGamePage",
        method : "get",
        success : function(data){
            console.log(data)
            data=JSON.parse(data)

            $('#imgProf').append('<img src='+data.imageUrl+' class="w3-circle" style="height:106px;width:106px" alt="Avatar"/>')
            $('#gameName').text(data.gameName)
            $('#ypub').text(data.yearPublished)
            $('#fol').text('Followers: '+data.followers)
            $('#rat').text('Rating: '+data.ratings)
            $('#des').text('Designer: '+data.designer)
            $('#cat').text('Category: '+data.categories)
            $('#minpmaxp').text('Min players / Max players:'+data.minPlayers + '-' + data.maxPlayers)
            $('#desc').text('Description: '+data.description)
            if(data.followed=="true"){
                $('#followButton').text("Unfollow")
            }
            if(data.rated!=-1){
                $('#ratingValue').text(data.rated)
                $('#rateButton').prop('disabled', true)
            }
            if(data.inCommonFollowers!=null){
                $('#inCommonFollowers').text()
            }

        }
    })
})