$(document).ready(function(){
    categories = ['Card Game', 'Economic', 'Fighting', 'Fantasy', 'City Building',
        'Science Fiction', 'Abstract Strategy', 'Territory Building',
        'Adventure', 'Deduction', 'Miniatures', 'Humor', 'Animals', 'Puzzle',
        'Horror', 'Party Game', 'Bluffing', 'Movies / TV / Radio theme',
        'Medieval', 'Expansion for Base-game', 'Exploration', 'Environmental',
        'Renaissance', 'Ancient', 'Pirates', 'Industry / Manufacturing',
        'Farming', 'Novel-based', 'Nautical', 'Trains', 'Mythology', 'Dice',
        'Murder/Mystery', 'Wargame', 'Action / Dexterity', 'Video Game Theme',
        'Comic Book / Strip', 'Educational', 'Post-Napoleonic', 'Number',
        'Negotiation', 'Civilization', 'Spies/Secret Agents', 'Medical',
        'Space Exploration', 'Game System', 'Real-time', 'Mafia',
        'Children\'s Game', 'American West', 'Pike and Shot', 'Political',
        'Transportation', 'Arabian', 'Word Game']
    loadSearchBar()
    for(a in categories) {
        $("#category").append(
            new Option(categories[a], categories[a])
        )
    }
})
function loadSearchBar(){
    $("#navbar").append("<div class=\"w3-bar w3-theme-d2 w3-left-align w3-large\" id=\"searchBar\" style=\"background-color: #1B4571;\">" +
        "                <a href=\"\\\" class=\" w3-button w3-padding-large w3-theme-d4\">\n" +
        "                    <i class=\"fa fa-home w3-margin-right\"></i>\n" +
        "                </a>\n" +
        "                <a onclick='loadMyProfile()' class=\" w3-button w3-padding-large w3-theme-d4\">\n" +
        "                    <i class=\"fa fa-user-circle w3-margin-right\"></i>\n" +
        "                </a>\n" +
        "<input type=\"text\" placeholder=\"Search for a game\" id=\"searchEditText\" class=\"w3-border w3-padding\" style=\"display:inline-block; width:60.5%;\">" +
        "<select name=\"category\" id=\"category\">" +
        "<option value=\"All\">All Categories</option>" +
        "</select>" +
        "<select name=\"type\" id=\"typeOf\">" +
        "<option value=\"Game\">Game</option>" +
        "<option value=\"User\">User</option>" +
        "</select>" +
        "<button onclick=\"searchButton()\" type=\"button\" class=\"w3-button w3-theme\" id=\"searchButton\" style=\"border-radius: 10px 10px 10px 10px;\"><i class=\"fa fa-pencil\"></i> Search</button>" +
        "</div>")
    addListener()
}
function addListener(){
    const searchEditText = document.getElementById("searchEditText");
    const searchFilter = document.getElementById("typeOf")
    // Execute a function when the user presses a key on the keyboard
    searchEditText.addEventListener("keypress", function(event) {
        // If the user presses the "Enter" key on the keyboard
        if (event.key === "Enter") {
            // Cancel the default action, if needed
            event.preventDefault();
            // Trigger the button element with a click
            document.getElementById("searchButton").click();
        }
    });
    $("#typeOf").change(function(){
       if($("#typeOf").find(":selected").text() == "User")
           $("#category").hide()
        else
           $("#category").show()
    });
}
function searchButton(){
    var concept = $('#typeOf').find(":selected").text();
    var concept2 = $('#category').find(":selected").text();
    if(concept == "Game")
        if(concept2 == "All Categories")
            searchForAGame()
        else
            searchForAGameFiltered(concept2)
    else
        searchForAPerson()
}
function searchForAGame() {
    let searchEditText;
    let text;
    if(arguments[0])
        text = arguments[0];
    else {
        searchEditText = document.getElementById("searchEditText");
        text = searchEditText.value.trim();
    }
    $.ajax({
        url : "/api/gamePageExists",
        data : {name : text},
        method : "get",
        success: function(data){
            if(data == 1) {
                window.location.href="http://localhost:8080/gamePage"
            }
            else if(data >= 0){
                window.location.href= "http://localhost:8080/searchPage"
            }
            else{
                alert("Nessun gioco corrispondente ai parametri di ricerca inseriti");
            }
        }
    })
}
function searchForAGameFiltered(cat){
    let searchEditText = document.getElementById("searchEditText");
    let text = searchEditText.value.trim();
    $.ajax({
        url: "/api/searchGameFiltered",
        data: {name: text, category: cat},
        method : "get",
        success: function (data) {
            if(data == 1){
                window.location.href="http://localhost:8080/gamePage"
            }
            else if(data >= 0){
                $.ajax({
                    url: "/api/loadResultsPage",
                    success: function(data){
                        window.location.href= "http://localhost:8080/searchPage"
                    }
                })
            }
            else{
                alert("Nessun gioco corrispondente ai parametri di ricerca inseriti");
            }
        }
    })
}
function searchForAPerson(){
    let searchEditText;
    let user;
    if(arguments[0])
        user = arguments[0];
    else {
        searchEditText = document.getElementById("searchEditText");
        user = searchEditText.value.trim();
    }
    $.ajax({
        url: "/api/loadProfile",
        data: {username: user},
        method : "get",
        success: function(data) {
            if(data == "no"){
                alert("No user found");
                window.location.reload()
            }
            else{
               window.location.href =  "http://localhost:8080/userProfile?user=" + user;
            }
        }
    })
}
var admin = checkAdmin()
function checkAdmin(){
    let admin = false
    $.ajax({
        url: "/api/isAdmin",
        method: "get",
        async: false,
        success: function(data){
            data = JSON.parse(data)
            if(data) {
                $(".admin").show()
                admin = true
            }
            else {
                $(".admin").hide()
                admin = false
            }
        }
    })
    return admin
}
var logged = checkLogged()
function checkLogged() {
    let logged = false
    $.ajax({
        url: "/api/isLogged",
        method: "get",
        async: false,
        success: function(data) {
            data = JSON.parse(data)
            if (data) {
                $(".logged").show()
                logged = true
            } else $(".logged").hide()
        }
    })
    return logged
}

function loadMyProfile(){
    if(!logged)
        window.location.href = "http://localhost:8080/login"
    else if(checkAdmin())
        window.location.href = "http://localhost:8080/adminPage"
    else{
        $.ajax({
            url: "/api/loadPersonalProfile",
            method : "get",
            success: function(data) {
                if(data == "no"){
                    alert("No user found");
                }
                else{
                    window.location.href =  "http://localhost:8080/userProfile?user=" + data;
                }
            }
        })
    }
}