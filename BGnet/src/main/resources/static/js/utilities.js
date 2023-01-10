function searchButton(){
    const searchEditText = document.getElementById("searchEditText");
    let text = searchEditText.value;
    $.ajax({
        url : "/api/gamePageExists",
        parameters: text,
        method : "get",
        success: function(data){
            if(data){
                location.href = "localhost:8080/gamePage";
            }
            else{
                console.log("Gioco non trovato");
            }
        }
    })


}