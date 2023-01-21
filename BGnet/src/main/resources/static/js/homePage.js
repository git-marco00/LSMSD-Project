$(document).ready(function() {
    $.ajax({
        url: "/api/landingSuggestedGames",
        method: "get",
        success: function (data) {
            data = JSON.parse(data)
            for(game in data) {
                let src = (data[game].image == "null") ? 'img/svg/img' + getRandomInt(0,9) + '.svg' : data[game].image;
                let html = '<img id="' + data[game].name + '" src="' + src + '" class="suggested-game suggested-game-' + game + ' w3-round w3-button w3-image" style="background-size: cover; border-radius: 75%;" alt="suggested-game-' + game + '">'
                $('div.suggested-game-' + game).append(html)
                $('div.suggested-game-' + game).append('<p>' + data[game].name + '</p><br>')
            }
            $('img.suggested-game').bind('click', function (event) {
                searchForAGame(event.target.id)
            })
        }
    })
    $.ajax({
        url: "/api/landingSuggestedUsers",
        method: "get",
        success: function (data) {
            data = JSON.parse(data)
            for(user in data) {
                let src = (data[user].img == "null") ? 'img/svg/img' + getRandomInt(0,9) + '.svg' : data[user].img;
                let html = '<img id="' + data[user].username + '" src="' + src + '" class="suggested-user suggested-user-' + user + ' w3-round w3-button w3-image" style="background-size: cover; border-radius: 75%; max-width: 35%;" alt="suggested-user-' + user + '">'
                $('div.suggested-user-' + user).append(html)
                $('div.suggested-user-' + user).append('<p>' + data[user].username + '</p><br>')
            }
            $('img.suggested-user').bind('click', function (event) {
                window.location.href = "http://localhost:8080/userProfile?user=" + event.target.id
            })
        }
    })
})

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min) + min);
}