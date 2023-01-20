function createGame(){
    // Rccolta valori
    let name, designer, yearPublished, minPlayers, maxPlayers, playingTime,
        minPlayTime, maxPlayTime, categories,
        description, img;

    $.ajax({
        url: "/api/createGame",
        method: "put",
        data: {name: name, designer:designer, year: yearPublished,
                minPlayers: minPlayers, maxPlayers: maxPlayers, playingTime:playingTime, minPlayTime: minPlayTime,
                maxPlayTime: maxPlayTime, categories: categories, description: description, img: img},
        success: function(data){
            if(data) {
                alert("Data successfully added!")
                // Ricarico la pagina
                window.location.reload();
            }

        }
    })
}