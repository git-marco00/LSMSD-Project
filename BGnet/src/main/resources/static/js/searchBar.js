$(document).ready(function(){
    loadSearchBar()
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
        "    <option value=\"Party\">Party Game</option>\n" +
        "    <option value=\"Classic\">Classic</option>\n" +
        "    <option value=\"Cooperative\">Cooperative</option>\n" +
        "    <option value=\"Competitive\">Competitive</option>\n" +
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
            searchForAGameFiltered()
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
            console.log(data)
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

function searchForAGameFiltered(){
    $.ajax({
        url: "/api/searchGameFiltered",
        data: {name: 1, category: 2},
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
                        document.getElementById("idStupido").innerHTML = "CIao"
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
    let user = document.getElementById("searchEditText").value.trim();
    $.ajax({
        url: "/api/loadProfile",
        data: {username: user},
        method : "get",
        success: function(data) {
            console.log(data)
            if(data == "no"){
                alert("Nessun user trovato");
            }
            else{
               window.location.href =  "http://localhost:8080/userProfile?user=" + user;
            }
        }
    })
}

