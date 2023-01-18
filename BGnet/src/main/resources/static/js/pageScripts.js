$(document).ready(function(){
    loadSearchBar()
    addListener()
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
}

function addListener(){
    const searchEditText = document.getElementById("searchEditText");
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
}

