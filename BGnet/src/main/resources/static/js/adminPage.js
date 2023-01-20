$(document).ready(function(){
    $("#addGameDiv").hide()
    $("#showForm").bind('click', function(event){
        $("#addGameDiv").show()
    })

    $("#addButton").bind('click', function(event){
        createGame()
    }
)})


function createGame(){
    // Rccolta valori
    let name, designer, yearPublished, minPlayers, maxPlayers, playingTime,
        minPlayTime, maxPlayTime, categories,
        description, img;

    name = $("#name").val().trim()
    designer = $("#designer").val().trim()
    yearPublished = $("#yearPublished").val().trim()
    minPlayers = $("#minPlayers").val().trim()
    maxPlayers = $("#maxPlayers").val().trim()
    playingTime = $("#playingTime").val().trim()
    minPlayTime = $("#minPlayTime").val().trim()
    maxPlayTime = $("#maxPlayTime").val().trim()
    _categories = $("#categories").val().trim()
    img = $("#imgUrl").val().trim()
    description = $("#description").val().trim()
    console.log(name, designer, yearPublished, minPlayers, maxPlayers, playingTime,
        minPlayTime, maxPlayTime, _categories,
        description, img);
    $.ajax({
        url: "/api/createGame",
        method: "get",
        data: {name: name, designer:designer, year: yearPublished,
                minPlayers: minPlayers, maxPlayers: maxPlayers, playingTime:playingTime, minPlayTime: minPlayTime,
                maxPlayTime: maxPlayTime, categories: _categories, description: description, img: img},
        success: function(data){
            if(data) {
                alert("Data successfully added!")
                // Ricarico la pagina
                window.location.reload();
            }

        }
    })
}
