$(document).ready(function(){
    loadSearchBar()
    addListener()
})

function loadSearchBar(){
    $("#searchBar").append("<input type=\"text\" placeholder=\"Search for a game\" id=\"searchEditText\" class=\"w3-border w3-padding\" style=\"display:inline-block; width:1000px\">")
    $("#searchBar").append("<button onclick=\"searchButton()\" type=\"button\" class=\"w3-button w3-theme\" id=\"searchButton\"><i class=\"fa fa-pencil\"></i> Search</button>")
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