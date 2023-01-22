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
    for(a in categories){
        console.log(categories[a])
        $("#category").append(
            new Option(categories[a], categories[a])
        )
    }
})

function loadSearchBar(){
    $("#navbar").append("            <div class=\"w3-bar w3-theme-d2 w3-left-align w3-large\" id=\"searchBar\">\n" +
        "                <a href=\"#\" class=\" w3-button w3-padding-large w3-theme-d4\">\n" +
        "                    <i class=\"fa fa-home w3-margin-right\"></i>\n" +
        "                    Logo\n" +
        "                </a>\n" +
        "<input type=\"text\" placeholder=\"Search for a game\" id=\"searchEditText\" class=\"w3-border w3-padding\" style=\"display:inline-block; width:1000px\">" +
        "<select name=\"category\" id=\"category\">\n" +
        "    <option value=\"All\">All Categories</option>\n" +
        "  </select>" +
        "<select name=\"type\" id=\"typeOf\">\n" +
        "    <option value=\"Game\">Game</option>\n" +
        "    <option value=\"User\">User</option>\n" +
        "</select>" +
        "<button onclick=\"searchButton()\" type=\"button\" class=\"w3-button w3-theme\" id=\"searchButton\"><i class=\"fa fa-pencil\"></i> Search</button>" +
        "            </div>")
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
            if(data == 1){
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
            }
            else{
               window.location.href =  "http://localhost:8080/userProfile?user=" + user;
            }
        }
    })
}

function checkAdmin(){
    $.ajax({
        url: "/api/isAdmin",
        method: "get",
        success: function(data){
            if(data == "ok")
                $(".admin").show()
        }
    })
}



function checkLogged(){
    $.ajax({
        url: "/api/isLogged",
        method: "get",
        success: function(data){
            console.log(data)
            if(data === "ok")
                $(".logged").show()
            else {
                $(".logged").hide()
            }
        }
    })
}

