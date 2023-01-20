$(document).ready(function() {
    $.ajax({
        url: "/api/landing",
        method: "get",
        success: function (data) {
            data = JSON.parse(data)
        }
    })
})

function suggestUsers(){;}

function suggestGames(){;}

function showHomepage(){;}

function goToTheGame(){;}

function goToTheUser(){;}