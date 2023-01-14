function searchButton(){
    const searchEditText = document.getElementById("searchEditText");
    let text = searchEditText.value.trim();
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
                $.ajax({
                    url: "/api/loadResultsPage",
                    success: function(data){
                        console.log(data)
                    }
                })
                // Qui dovrà andare alla pagina dei risultati
                console.log(data)
            }
            else{
                alert("Nessun gioco corrispondente ai parametri di ricerca inseriti");
            }
        }
    })
}


