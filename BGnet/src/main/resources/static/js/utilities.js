$(document).ready(function(){
    addListener()
})

function searchButton(){
    const searchEditText = document.getElementById("searchEditText");
    let text = searchEditText.value.trim();
    $.ajax({
        url : "/api/gamePageExists",
        data : {name : text},
        method : "get",
        success: function(data){
            console.log(data)
            if(data){
                window.location.href="http://localhost:8080/gamePage"
            }
            else{
                console.log("Gioco non trovato");
            }
        }
    })
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
